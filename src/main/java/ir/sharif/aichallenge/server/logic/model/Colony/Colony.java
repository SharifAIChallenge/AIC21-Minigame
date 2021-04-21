package ir.sharif.aichallenge.server.logic.model.Colony;

import ir.sharif.aichallenge.server.logic.handlers.exceptions.GameActionException;
import ir.sharif.aichallenge.server.logic.handlers.exceptions.InvalidAntForColonyException;
import ir.sharif.aichallenge.server.logic.model.ant.Ant;
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
    private List<Cell> bases;
    private HashMap<Integer, Ant> ants;
    private ChatBox chatBox;
    private int toBeGeneratedSoldiersCount;
    private List<ChatMessage> allMessagesThisTurn;

    public Colony(int id, int baseAttackerId) {
        this.id = id;
        chatBox = new ChatBox();
        this.baseAttackerId = baseAttackerId;
        ants = new HashMap<>();
    }

    void addBaseCell(BaseCell baseCell) {
        // TODO ADD to bases
        base = baseCell;
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

    public void addNewAnt(Ant ant) throws GameActionException {
        if (ant.getColonyId() != this.id) {
            throw new InvalidAntForColonyException("");
        }

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

    public static Integer generateNewID() {
        return colonyUUID++;
    }

    public void removeAnt(int id) {
        ants.remove(id);
    }

    public int getToBeGeneratedSoldiersCount() {
        return toBeGeneratedSoldiersCount;
    }

    public int getBaseAttackerId() {
        return baseAttackerId;
    }
}
