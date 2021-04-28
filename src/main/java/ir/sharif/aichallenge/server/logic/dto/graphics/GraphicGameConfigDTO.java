package ir.sharif.aichallenge.server.logic.dto.graphics;

import ir.sharif.aichallenge.server.engine.config.Configs;
import ir.sharif.aichallenge.server.logic.config.ConstConfigs;
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

    public GraphicGameConfigDTO(GameMap map) {
        this.map_height = map.getYAxisLength();
        this.map_width = map.getXAxisLength();
        this.queen_health = ConstConfigs.QUEEN_ANT_INITIAL_HEALTH;
        this.scorpion_health = ConstConfigs.SOLDIER_ANT_INITIAL_HEALTH;
        this.cells_type = new CellTypeDTO[map.getXAxisLength() * map.getYAxisLength()];
        int index = 0;
        for (int x = 0; x < map.getXAxisLength(); x++) {
            for (int y = 0; y < map.getYAxisLength(); y++) {
                this.cells_type[index++] = new CellTypeDTO(y, x, map.getCell(x, y).getCellType());
            }
        }
        shift_x = ConstConfigs.SHIFT_X;
        shift_y = ConstConfigs.SHIFT_Y;
    }
}