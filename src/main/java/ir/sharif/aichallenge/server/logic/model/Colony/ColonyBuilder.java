package ir.sharif.aichallenge.server.logic.model.Colony;

import ir.sharif.aichallenge.server.logic.model.cell.Cell;

import java.util.List;

public class ColonyBuilder {
    private Colony colony;

    public ColonyBuilder(int id) {
        colony = new Colony(id);
    }

    public ColonyBuilder setBaseCells(List<Cell> baseCells){
        colony.setBaseCells(baseCells);
        return this;
    }

    public Colony getColony() {
        return colony;
    }
}