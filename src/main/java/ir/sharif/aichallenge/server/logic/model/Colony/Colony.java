package ir.sharif.aichallenge.server.logic.model.Colony;

import ir.sharif.aichallenge.server.logic.handlers.exceptions.GameActionException;
import ir.sharif.aichallenge.server.logic.handlers.exceptions.InvalidAntForColonyException;
import ir.sharif.aichallenge.server.logic.model.ant.Ant;
import ir.sharif.aichallenge.server.logic.model.ant.AntType;
import ir.sharif.aichallenge.server.logic.model.cell.BaseCell;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;
import ir.sharif.aichallenge.server.logic.model.chatbox.ChatBox;
import ir.sharif.aichallenge.server.logic.model.chatbox.ChatMessage;

import java.util.HashMap;
import java.util.List;

public class Colony {
    private static Integer colonyUUID = 1000;

    private int id;
    private int baseAttackerId;
    private Cell base;
    private int baseHealth;
    private HashMap<Integer, Ant> ants;
    private ChatBox chatBox;
    private int toBeGeneratedWorkersCount;
    private int toBeGeneratedSoldiersCount;
    private int allWorkerAntsGeneratedCount;
    private int allSoldierAntsGeneratedCount;
    private List<ChatMessage> allMessagesThisTurn;

    public Colony(int id, int baseAttackerId, BaseCell base, int baseHealth) {
        this(id, baseAttackerId);
        this.base = base;
        this.baseHealth = baseHealth;
    }

    Colony(int id, int baseAttackerId) {
        this.id = id;
        chatBox = new ChatBox();
        this.baseAttackerId = baseAttackerId;
        ants = new HashMap<>();
        allSoldierAntsGeneratedCount = 0;
        allWorkerAntsGeneratedCount = 0;
    }

    void setBaseCell(BaseCell baseCell, int initialBaseHealth) {
        base = baseCell;
        baseHealth = initialBaseHealth;
    }

    public List<ChatMessage> getAllMessagesThisTurn() {
        return allMessagesThisTurn;
    }

    public void setAllMessagesThisTurn(List<ChatMessage> allMessagesThisTurn) {
        this.allMessagesThisTurn = allMessagesThisTurn;
    }

    public void setToBeGeneratedSoldiersCount(int toBeGeneratedSoldiersCount) {
        this.toBeGeneratedSoldiersCount = toBeGeneratedSoldiersCount;
    }

    public void setToBeGeneratedWorkersCount(int toBeGeneratedWorkersCount) {
        this.toBeGeneratedWorkersCount = toBeGeneratedWorkersCount;
    }

    public void addNewAnt(Ant ant) throws GameActionException {
        if (ant.getColonyId() != this.id) {
            throw new InvalidAntForColonyException("");
        }
        if (ant.getAntType() == AntType.WORKER)
            allWorkerAntsGeneratedCount++;
        else
            allSoldierAntsGeneratedCount++;
        this.ants.put(ant.getId(), ant);
    }

    public int getId() {
        return id;
    }

    public Ant getAnt(int antId) {
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

    public void decreaseBaseHealth(int amount) {
        baseHealth -= amount;
    }

    public static Integer generateNewID() {
        return colonyUUID++;
    }

    public void removeAnt(int id) {
        ants.remove(id);
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getToBeGeneratedWorkersCount() {
        return toBeGeneratedWorkersCount;
    }

    public int getToBeGeneratedSoldiersCount() {
        return toBeGeneratedSoldiersCount;
    }

    public int getAllSoldierAntsGeneratedCount() {
        return allSoldierAntsGeneratedCount;
    }

    public int getAllAntsGeneratedCount() {
        return allSoldierAntsGeneratedCount + allWorkerAntsGeneratedCount;
    }

    public int getBaseAttackerId() {
        return baseAttackerId;
    }
}
