package ir.sharif.aichallenge.server.logic.model.cell;

import ir.sharif.aichallenge.server.logic.model.ant.Ant;
import ir.sharif.aichallenge.server.logic.model.ant.AntType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cell {
    private int xPosition;
    private int yPosition;
    public CellType cellType;
    private List<Ant> ants;


    public Cell(int xPosition, int yPosition, CellType cellType) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.cellType = cellType;
        ants = new ArrayList<>();
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public void addAnt(Ant ant) {
        ants.add(ant);
    }

    public void removeAnt(Ant ant) {
        ants.remove(ant);
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }

    public CellType getCellType() {
        return cellType;
    }

    public boolean isBase() {
        return cellType == CellType.BASE;
    }

    public List<Ant> getWorkerAnts() {
        return ants.stream().filter(x -> x.getAntType() == AntType.WORKER).collect(Collectors.toList());
    }
}
