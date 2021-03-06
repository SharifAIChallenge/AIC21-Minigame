package ir.sharif.aichallenge.server.logic.dto.payloads;

import java.util.ArrayList;

import ir.sharif.aichallenge.server.common.util.Log;
import ir.sharif.aichallenge.server.logic.config.ConstConfigs;
import ir.sharif.aichallenge.server.logic.model.Game;
import ir.sharif.aichallenge.server.logic.model.ant.Ant;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;

public class GameConfigDTO {
    int map_width;
    int map_height;
    int ant_type;
    BaseDTO[] bases;
    int base_x;
    int base_y;
    int health_kargar;
    int health_sarbaaz;
    int attack_distance;
    int view_distance;
    int generate_kargar;
    int generate_sarbaaz;
    int rate_death_resource;

    public GameConfigDTO(Game game, int antId) {
        Ant ant = game.getAntByID(antId);
        this.map_width = game.getMap().getXAxisLength();
        this.map_height = game.getMap().getYAxisLength();
        this.ant_type = ant.getAntType().getValue();
        this.base_x = 0;
        this.base_y = 0;
        this.health_kargar = ConstConfigs.QUEEN_ANT_INITIAL_HEALTH;
        this.health_sarbaaz = ConstConfigs.SOLDIER_ANT_INITIAL_HEALTH;
        this.attack_distance = ConstConfigs.ANT_MAX_ATTACK_DISTANCE;
        this.generate_kargar = ConstConfigs.GENERATE_WORKER_BREAD_AMOUNT;
        this.generate_sarbaaz = ConstConfigs.GENERATE_SOLDIER_GRASS_AMOUNT;
        this.rate_death_resource = 1;
        this.view_distance = ConstConfigs.ANT_MAX_VIEW_DISTANCE;
        ArrayList<BaseDTO> bases = new ArrayList<>();
        for (Cell base : game.getColony(ant.getColonyId()).getBaseCells()) {
            bases.add(new BaseDTO(base.getX(), base.getY()));
        }
        this.bases = bases.toArray(new BaseDTO[] {});
    }

    public static class BaseDTO {
        public int x;
        public int y;

        public BaseDTO(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
