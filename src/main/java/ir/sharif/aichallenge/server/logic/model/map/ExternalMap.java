package ir.sharif.aichallenge.server.logic.model.map;

import ir.sharif.aichallenge.server.logic.model.Colony.ColonyInitPackage;
import ir.sharif.aichallenge.server.logic.model.cell.BaseCell;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalMap {
    private Cell[][] cells;
    private HashMap<Integer, ColonyInitPackage> colonyInitPackages;

    public ExternalMap(Cell[][] cells) {
        this.cells = cells;
        this.colonyInitPackages = new HashMap<>();
    }

    public void addInitPackage(ColonyInitPackage initPackage) {
        colonyInitPackages.put(initPackage.getId(), initPackage);
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public HashMap<Integer, ColonyInitPackage> getColonyInitPackages() {
        return colonyInitPackages;
    }

    public ColonyInitPackage getColonyInitPackage(int colonyId){
        return colonyInitPackages.get(colonyId);
    }
}
