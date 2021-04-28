package ir.sharif.aichallenge.server.logic.utility;

import ir.sharif.aichallenge.server.common.util.Log;
import ir.sharif.aichallenge.server.logic.config.ConstConfigs;
import ir.sharif.aichallenge.server.logic.model.Colony.ColonyInitPackage;
import ir.sharif.aichallenge.server.logic.model.cell.BaseCell;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;
import ir.sharif.aichallenge.server.logic.model.cell.CellType;
import ir.sharif.aichallenge.server.logic.model.cell.ExternalCell;
import ir.sharif.aichallenge.server.logic.model.map.ExternalMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonUtility {

    public static ExternalMap readMapFromFile(String fileName) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            JSONObject map = (JSONObject) jsonParser.parse(reader);
            try {
                ConstConfigs.MAP_HEIGHT = ((Long) map.get("MAP_HEIGHT")).intValue();
                ConstConfigs.MAP_WIDTH = ((Long) map.get("MAP_WIDTH")).intValue();
                try {
                    ConstConfigs.SHIFT_X = ((Long) map.get("SHIFT_X")).intValue();
                } catch (Exception ignore) {
                }
                try {
                    ConstConfigs.SHIFT_Y = ((Long) map.get("SHIFT_Y")).intValue();
                } catch (Exception ignore) {
                }
            } catch (Exception e) {
                Log.e("JsonUtility", "MAP_HEIGHT or MAP_WIDTH is not available in map.json!");
                System.exit(-1);
            }
            JSONArray cells = (JSONArray) map.get("cells_type");
            int height = ConstConfigs.MAP_HEIGHT;
            int width = ConstConfigs.MAP_WIDTH;
            Cell[][] mapCells = new Cell[height][width];
            ExternalMap externalMap = new ExternalMap(mapCells);

            ColonyInitPackage colonyInitPackage0 = new ColonyInitPackage(0);
            ColonyInitPackage colonyInitPackage1 = new ColonyInitPackage(1);

            for (Object o : cells) {
                ExternalCell externalCell = parseCellObject((JSONObject) o);
                // (cell.getY() + " " + cell.getX());
                mapCells[externalCell.getCell().getY()][externalCell.getCell().getX()] = externalCell.getCell();

                if (externalCell.getCell().isBase()) {
                    if (externalCell.getColonyId() == 0) {
                        colonyInitPackage0.addBaseCell(externalCell.getCell());
                    } else {
                        colonyInitPackage1.addBaseCell(externalCell.getCell());
                    }
                }

                if (externalCell.getCell().cellType == CellType.EMPTY && externalCell.hasInitialAnt()) {
                    if (externalCell.getColonyId() == 0) {
                        colonyInitPackage0.setQueenStartPosition(externalCell.getCell());
                    } else {
                        colonyInitPackage1.setQueenStartPosition(externalCell.getCell());
                    }
                }
            }

            externalMap.addInitPackage(colonyInitPackage0);
            externalMap.addInitPackage(colonyInitPackage1);

            return externalMap;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static ExternalCell parseCellObject(JSONObject jsonCell) {
        // (yPosition);
        int yPosition = ((Long) jsonCell.get("row")).intValue();

        // (xPosition);
        int xPosition = ((Long) jsonCell.get("col")).intValue();

        // (cellType);
        int colonyId = -1;
        int cell_type;
        boolean hasInitialAnt = false;
        int cell_type_value = ((Long) jsonCell.get("cell_type")).intValue();
        switch (cell_type_value) {
            case 0:
                colonyId = 0;
                cell_type = 0;
                break;
            case 1:
                colonyId = 1;
                cell_type = 0;
                break;
            case 2:
            case 3:
                cell_type = cell_type_value - 1;
                break;
            case 4:
            case 5:
                colonyId = cell_type_value - 4;
                hasInitialAnt = true;
                cell_type = 1;
                break;
            default:
                throw new RuntimeException("invalid jsonCell type in map.");
        }

        CellType cellType = CellType.getCellType(cell_type);

        Cell cell;
        if (cellType == CellType.BASE) {
            cell = new BaseCell(xPosition, yPosition);
        } else {
            cell = new Cell(xPosition, yPosition, cellType);
        }

        return new ExternalCell(cell, colonyId, hasInitialAnt);
    }
}
