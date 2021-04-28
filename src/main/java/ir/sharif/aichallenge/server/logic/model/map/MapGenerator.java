package ir.sharif.aichallenge.server.logic.model.map;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import ir.sharif.aichallenge.server.common.util.Log;
import ir.sharif.aichallenge.server.logic.config.ConstConfigs;
import ir.sharif.aichallenge.server.logic.model.Colony.Colony;
import ir.sharif.aichallenge.server.logic.model.cell.BaseCell;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;
import ir.sharif.aichallenge.server.logic.model.cell.CellType;
import ir.sharif.aichallenge.server.logic.utility.JsonUtility;
import org.json.simple.parser.ParseException;

public class MapGenerator {

    public static MapGeneratorResult generateFromFile(String fileName) {
        // int height = ConstConfigs.MAP_HEIGHT;
        // int width = ConstConfigs.MAP_WIDTH;
        try {
            ExternalMap externalMap = JsonUtility.readMapFromFile(fileName);

            GameMap gameMap = new GameMap(externalMap.getCells(), ConstConfigs.MAP_HEIGHT, ConstConfigs.MAP_WIDTH);

            Colony firstColony = new Colony(0, externalMap.getColonyInitPackage(0));
            Colony secondColony = new Colony(1, externalMap.getColonyInitPackage(1));

            HashMap<Integer, Colony> colonies = new HashMap<>();
            colonies.put(0, firstColony);
            colonies.put(1, secondColony);
            return new MapGeneratorResult(gameMap, colonies);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class MapGeneratorResult {
        public GameMap map;
        public HashMap<Integer, Colony> colonies;

        MapGeneratorResult(GameMap map, HashMap<Integer, Colony> colonies) {
            this.map = map;
            this.colonies = colonies;
        }
    }
}
