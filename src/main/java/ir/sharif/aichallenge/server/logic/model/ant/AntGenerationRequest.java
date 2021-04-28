package ir.sharif.aichallenge.server.logic.model.ant;

import ir.sharif.aichallenge.server.logic.model.cell.Cell;

public class AntGenerationRequest {
    private boolean isQueen;
    private int xPosition;
    private int yPosition;

    public AntGenerationRequest(Cell base, boolean isQueen) {
        this.xPosition = base.getX();
        this.yPosition = base.getY();
        this.isQueen = isQueen;
    }

    public AntGenerationRequest(int xPosition, int yPosition, boolean isQueen) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isQueen = isQueen;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public boolean isQueen() {
        return isQueen;
    }
}
