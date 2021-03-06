package ir.sharif.aichallenge.server.logic.model.map;

import ir.sharif.aichallenge.server.logic.config.ConstConfigs;
import ir.sharif.aichallenge.server.logic.model.ant.Ant;
import ir.sharif.aichallenge.server.logic.model.ant.MoveType;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;
import ir.sharif.aichallenge.server.logic.model.cell.CellType;

import java.util.*;

public class GameMap {
    //cells[yAxisLength][xAxisLength]
    private Cell[][] cells;
    private int yAxisLength;
    private int xAxisLength;

    public GameMap(Cell[][] cells, int yAxisLength, int xAxisLength) {
        this.cells = cells;
        this.yAxisLength = yAxisLength;
        this.xAxisLength = xAxisLength;
    }

    GameMap() {

    }

    void setCells(Cell[][] cells, int yAxisLength, int xAxisLength) {
        this.yAxisLength = yAxisLength;
        this.xAxisLength = xAxisLength;
        this.cells = cells;
    }

    void setCell(int xPosition, int yPosition, Cell cell) {
        cells[yPosition][xPosition] = cell;
    }

    public Cell getCell(int xPosition, int yPosition) {
        return cells[((yPosition % yAxisLength) + yAxisLength) % yAxisLength][((xPosition % xAxisLength) + xAxisLength) % xAxisLength];
    }

    public Cell[] getAntViewableCells(int xPosition, int yPosition) {
        return getAroundCells(xPosition, yPosition, ConstConfigs.ANT_MAX_VIEW_DISTANCE);
    }

    public Cell[] getAttackableCells(int xPosition, int yPosition, int maxDistance) {
        return getAroundCells(xPosition, yPosition, maxDistance);
    }

    private Cell[] getAroundCells(int xPosition, int yPosition, int maxDistance) {
        List<Cell> aroundCells = new ArrayList<>();
        for (int x = xPosition - maxDistance; x <= xPosition + maxDistance; x++) {
            for (int y = yPosition - maxDistance; y <= yPosition + maxDistance; y++) {
                if (getManhattanDistance(x, y, xPosition, yPosition) > maxDistance)
                    continue;
                aroundCells.add(getCell(x, y));
            }
        }
        return aroundCells.toArray(new Cell[0]);
    }

    public int getYAxisLength() {
        return yAxisLength;
    }

    public int getXAxisLength() {
        return xAxisLength;
    }

    public int get???BorderlessManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.min(Math.abs(x1 - x2), xAxisLength - Math.abs(x1 - x2)) +
                Math.min(Math.abs(y1 - y2), yAxisLength - Math.abs(y1 - y2));
    }

    private int getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public List<Cell> getAllCells() {
        List<Cell> allCells = new ArrayList<>();
        for (Cell[] rowCells : cells) {
            allCells.addAll(Arrays.asList(rowCells));
        }
        return allCells;
    }

    public void changeAntCurrentCell(Ant ant, int moveType, double noise) {
        int newX = ant.getXPosition();
        int newY = ant.getYPosition();

        Random random = new Random();
        int randomNumber = random.nextInt();
        if (0 <= randomNumber && randomNumber < noise)
            moveType = (moveType + 1) % 4 + 1;
        else if (noise <= randomNumber && randomNumber < 2 * noise)
            moveType = (moveType + 2) % 4 + 1;
        if (2 * noise <= randomNumber && randomNumber < 3 * noise)
            moveType = (moveType + 3) % 4 + 1;

        switch (moveType) {
            case MoveType.UP:
                newY -= 1;
                break;
            case MoveType.DOWN:
                newY += 1;
                break;
            case MoveType.LEFT:
                newX -= 1;
                break;
            case MoveType.RIGHT:
                newX += 1;
                break;
            default:
                return;
        }
        newX = newX % getXAxisLength();
        newY = newY % getYAxisLength();
        Cell targetCell = getCell(newX, newY);
        if (targetCell.cellType == CellType.WALL)
            return;

        changeAntCurrentCell(ant, targetCell);
    }

    private void changeAntCurrentCell(Ant ant, Cell targetCell) {
        getCell(ant.getXPosition(), ant.getYPosition()).removeAnt(ant);
        ant.moveTo(targetCell.getX(), targetCell.getY());
        targetCell.addAnt(ant);
    }
}