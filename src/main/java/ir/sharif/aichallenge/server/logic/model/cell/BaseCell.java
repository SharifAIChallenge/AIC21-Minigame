package ir.sharif.aichallenge.server.logic.model.cell;

import ir.sharif.aichallenge.server.logic.model.Colony.Colony;
import ir.sharif.aichallenge.server.logic.model.ant.Ant;

import java.util.List;

public class BaseCell extends Cell {
    private Colony colony;

    public BaseCell(int xPosition, int yPosition) {
        super(xPosition, yPosition, CellType.BASE);
    }

    public Colony getColony() {
        return colony;
    }

    public void setColony(Colony colony) {
        this.colony = colony;
    }
}
