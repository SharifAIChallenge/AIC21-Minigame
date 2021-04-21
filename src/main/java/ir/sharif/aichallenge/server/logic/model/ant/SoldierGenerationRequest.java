package ir.sharif.aichallenge.server.logic.model.ant;

import ir.sharif.aichallenge.server.logic.model.cell.Cell;

public class SoldierGenerationRequest {
    private int xPosition;
    private int yPosition;

    public SoldierGenerationRequest(Cell base) {
        this.xPosition = base.getX();
        this.yPosition = base.getY();
    }

    public SoldierGenerationRequest(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
