package ir.sharif.aichallenge.server.logic.model;

import ir.sharif.aichallenge.server.logic.model.Colony.Colony;
import ir.sharif.aichallenge.server.logic.model.Game.QuickResult;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameJudge {
    private final AntRepository antRepository;

    public GameJudge(AntRepository antRepository) {
        this.antRepository = antRepository;
    }

    public Colony getWinner() {
        List<Colony> aliveColonies = getAliveColonies(antRepository.getColonies());
        switch (aliveColonies.size()) {
        case 0:
            return null;
        case 1:
            return aliveColonies.get(0);
        default:
            return getWinnerByQueenHealth(aliveColonies);
        }
    }

    private Colony getWinnerByQueenHealth(List<Colony> colonies) {
        Colony colony1 = colonies.get(0);
        Colony colony2 = colonies.get(1);
        if (colony1.getQueen().getHealth() > colony2.getQueen().getHealth()) {
            return colony1;
        } else if (colony1.getQueen().getHealth() < colony2.getQueen().getHealth()) {
            return colony2;
        } else {
            return null;
        }
    }

    private List<Colony> getAliveColonies(List<Colony> colonies) {
        return colonies.stream().filter(x -> x.getQueen().getHealth() > 0).collect(Collectors.toList());
    }
}

// class ColonyComparator implements Comparator<Colony> {
//
// @Override
// public int compare(Colony o1, Colony o2) {
// if (o1.getQueen().getHealth() != o2.getQueen().getHealth())
// return o1.getQueen().getHealth() - o2.getQueen().getHealth();
// if (o1.getAnts().size() != o2.getAnts().size())
// return o1.getAnts().size() - o2.getAnts().size();
// if (o1.getAllAntsGeneratedCount() != o2.getAllAntsGeneratedCount())
// return o1.getAllAntsGeneratedCount() - o2.getAllAntsGeneratedCount();
// if (o1.getAllSoldierAntsGeneratedCount() !=
// o2.getAllSoldierAntsGeneratedCount())
// return o1.getAllSoldierAntsGeneratedCount() -
// o2.getAllSoldierAntsGeneratedCount();
// return 0;
// }
// }
