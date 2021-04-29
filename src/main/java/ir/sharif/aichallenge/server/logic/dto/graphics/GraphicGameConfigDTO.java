package ir.sharif.aichallenge.server.logic.dto.graphics;

import java.util.List;

import ir.sharif.aichallenge.server.engine.config.Configs;
import ir.sharif.aichallenge.server.logic.config.ConstConfigs;
import ir.sharif.aichallenge.server.logic.model.cell.BaseCell;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;
import ir.sharif.aichallenge.server.logic.model.map.GameMap;

public class GraphicGameConfigDTO {
    public int map_height;
    public int map_width;
    public CellTypeDTO[] cells_type;
    public int base_health = 0;
    public int queen_health;
    public int scorpion_health;
    public String team0_name = Configs.FIRST_TEAM_NAME;
    public String team1_name = Configs.SECOND_TEAM_NAME;
    public int winner;
    public int shift_x;
    public int shift_y;

    public GraphicGameConfigDTO(GameMap map, List<Cell> firstColonyBases, List<Cell> secondColonyBases) {
        this.map_height = map.getYAxisLength();
        this.map_width = map.getXAxisLength();
        this.queen_health = ConstConfigs.QUEEN_ANT_INITIAL_HEALTH;
        this.scorpion_health = ConstConfigs.SOLDIER_ANT_INITIAL_HEALTH;
        this.cells_type = new CellTypeDTO[map.getXAxisLength() * map.getYAxisLength()];
        int index = 0;
        for (int x = 0; x < map.getXAxisLength(); x++) {
            for (int y = 0; y < map.getYAxisLength(); y++) {
                Cell cell = map.getCell(x, y);
                this.cells_type[index++] = new CellTypeDTO(y, x, cell.cellType, 0);
            }
        }
        for (CellTypeDTO cell : this.cells_type) {
            for (Cell base1 : firstColonyBases) {
                if (base1.getX() == cell.col && base1.getY() == cell.row) {
                    cell.cell_type = 0;
                }
            }
            for (Cell base2 : secondColonyBases) {
                if (base2.getX() == cell.col && base2.getY() == cell.row) {
                    cell.cell_type = 1;
                }
            }
        }
        shift_x = ConstConfigs.SHIFT_X;
        shift_y = ConstConfigs.SHIFT_Y;
    }
}