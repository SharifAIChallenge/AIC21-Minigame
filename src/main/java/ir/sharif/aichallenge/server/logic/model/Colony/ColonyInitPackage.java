package ir.sharif.aichallenge.server.logic.model.Colony;

import ir.sharif.aichallenge.server.logic.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class ColonyInitPackage {
    private int id;
    private List<Cell> baseCells;
    private Cell queenStartPosition;

    public ColonyInitPackage(int id) {
        this.id = id;
        baseCells = new ArrayList<>();
    }

    public void addBaseCell(Cell cell) {
        baseCells.add(cell);
    }

    public int getId() {
        return id;
    }

    public List<Cell> getBaseCells() {
        return baseCells;
    }

    public Cell getQueenStartPosition() {
        return queenStartPosition;
    }

    public void setQueenStartPosition(Cell queenStartPosition) {
        this.queenStartPosition = queenStartPosition;
    }
}
