package ir.sharif.aichallenge.server.logic.model.cell;

import ir.sharif.aichallenge.server.logic.model.ant.Ant;
import ir.sharif.aichallenge.server.logic.model.ant.AntType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private Cell baseCell;
    private Cell wallCell;
    private Cell emptyCell;
    private Cell breadEmptyCell;
    private Cell grassEmptyCell;

    private Ant soldierAnt;
    private Ant workerAnt;

    @BeforeEach
    void setUp() {
        baseCell = new BaseCell(0, 0);
        wallCell = new Cell(0, 1, CellType.WALL);
        emptyCell = new Cell(0, 2, CellType.EMPTY);
        breadEmptyCell = new Cell(0, 3, CellType.EMPTY);
        grassEmptyCell = new Cell(0, 4, CellType.EMPTY);
        soldierAnt = new Ant(0, 0, 0, 0, AntType.SCORPION);
        workerAnt = new Ant(1, 0, 0, 0, AntType.QUEEN);
        baseCell.addAnt(soldierAnt);
        baseCell.addAnt(workerAnt);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isBase() {
        assertTrue(baseCell.isBase());
        assertFalse(wallCell.isBase());
        assertFalse(emptyCell.isBase());
        assertFalse(breadEmptyCell.isBase());
        assertFalse(grassEmptyCell.isBase());
    }

    @Test
    void getWorkerAnts() {
        List<Ant> workerAnts = baseCell.getWorkerAnts();
        assertEquals(1, workerAnts.size());
        assertEquals(workerAnt, workerAnts.get(0));
        assertFalse(workerAnts.contains(soldierAnt));
    }

    @Test
    void getEmptyWorkerAnts() {
        List<Ant> workerAnts = wallCell.getWorkerAnts();
        assertEquals(0, workerAnts.size());
    }
}