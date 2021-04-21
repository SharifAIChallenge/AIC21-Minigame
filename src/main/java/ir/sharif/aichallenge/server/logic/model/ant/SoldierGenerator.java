package ir.sharif.aichallenge.server.logic.model.ant;

import ir.sharif.aichallenge.server.logic.config.ConstConfigs;
import ir.sharif.aichallenge.server.logic.model.AntRepository;
import ir.sharif.aichallenge.server.logic.model.Colony.Colony;

import java.util.List;

public class SoldierGenerator {
    private final AntRepository antRepository;
    private int soldierGenerationCycleLength;
    private int soldierGenerationRate;

    public SoldierGenerator(AntRepository antRepository){
        this.antRepository = antRepository;
        soldierGenerationRate = ConstConfigs.SOLDIER_GENERATION_RATE;
        soldierGenerationCycleLength = ConstConfigs.SOLDIER_GENERATION_CYCLE_LENGTH;
    }

    public void GenerateSoldiers(int turn){
        if (turn % soldierGenerationCycleLength != 0)
            return;

        List<Colony> colonies = antRepository.getColonies();
        for (Colony colony : colonies) {
            colony.pushSoldierGenerationRequest(soldierGenerationRate);
        }
    }
}
