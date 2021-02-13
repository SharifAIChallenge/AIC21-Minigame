package ir.sharif.aichallenge.server.logic.model;

import ir.sharif.aichallenge.server.logic.config.ConstConfigs;
import ir.sharif.aichallenge.server.logic.handlers.exceptions.GameActionException;
import ir.sharif.aichallenge.server.logic.handlers.exceptions.InvalidAntForColonyException;
import ir.sharif.aichallenge.server.logic.model.ant.Ant;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;
import ir.sharif.aichallenge.server.logic.model.chatbox.ChatBox;

import java.util.HashMap;
import java.util.List;

public class Colony {
    private int id;
    private int gainedBread = 0;
    private int gainedGrass = 0;
    private Cell base;
    private int baseHealth;
    private HashMap<String, Ant> ants;
    private ChatBox chatBox;

    public Colony(int id, Cell base, int baseHealth) {
        this.id = id;
        addBread(ConstConfigs.COLONY_INITIAL_BREAD);
        addGrass(ConstConfigs.COLONY_INITIAL_GRASS);
        this.base = base;
        this.baseHealth = baseHealth;
        ants = new HashMap<>();
    }

    public void addNewAnt(Ant ant) throws GameActionException {
        if (ant.getColonyId() != this.id) {
            throw new InvalidAntForColonyException("");
        }
        this.ants.put(ant.getId(), ant);
    }

    public void addBread(int amount) {
        gainedBread += amount;
        if (gainedBread >= ConstConfigs.GENERATE_WORKER_BREAD_AMOUNT) {
            generateWorker();
            gainedBread -= ConstConfigs.GENERATE_WORKER_BREAD_AMOUNT;
        }
    }

    public void addGrass(int amount) {
        gainedGrass += amount;
        if (gainedGrass >= ConstConfigs.GENERATE_SOLDIER_GRASS_AMOUNT) {
            generateSoldier();
            gainedGrass -= ConstConfigs.GENERATE_SOLDIER_GRASS_AMOUNT;
        }
    }

    private void generateSoldier() {
    }

    private void generateWorker() {
    }

    public int getId() {
        return id;
    }

    public Ant getAnt(String antId) {
        return ants.get(antId);
    }

    public List<Ant> getAnts() {
        return List.copyOf(ants.values());
    }

    public ChatBox getChatBox() {
        return chatBox;
    }

    public Cell getBase() {
        return base;
    }

    public void decreaseBaseHealth(int amount){
        baseHealth -= amount;
    }
}
