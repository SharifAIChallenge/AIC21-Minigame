package ir.sharif.aichallenge.server.logic.model.cell;

public class ExternalCell {
    private Cell cell;
    private int colonyId;
    private boolean hasInitialAnt;

    public ExternalCell(Cell cell, int colonyId, boolean hasInitialAnt) {
        this.cell = cell;
        this.colonyId = colonyId;
        this.hasInitialAnt = hasInitialAnt;
    }

    public Cell getCell() {
        return cell;
    }

    public int getColonyId() {
        return colonyId;
    }

    public boolean hasInitialAnt() {
        return hasInitialAnt;
    }
}
