package ir.sharif.aichallenge.server.logic.model.Colony;

import ir.sharif.aichallenge.server.logic.handlers.exceptions.GameActionException;
import ir.sharif.aichallenge.server.logic.handlers.exceptions.InvalidAntForColonyException;
import ir.sharif.aichallenge.server.logic.model.ant.Ant;
import ir.sharif.aichallenge.server.logic.model.ant.SoldierGenerationRequest;
import ir.sharif.aichallenge.server.logic.model.cell.BaseCell;
import ir.sharif.aichallenge.server.logic.model.cell.Cell;
import ir.sharif.aichallenge.server.logic.model.chatbox.ChatBox;
import ir.sharif.aichallenge.server.logic.model.chatbox.ChatMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Colony {
    private static Integer colonyUUID = 1000;

    private int id;
    private List<Cell> bases;
    private HashMap<Integer, Ant> ants;
    private Ant queen;
    private ChatBox chatBox;
    private List<ChatMessage> allMessagesThisTurn;
    private List<SoldierGenerationRequest> soldierGenerationRequests;

    public Colony(int id) {
        this.id = id;
        chatBox = new ChatBox();
        ants = new HashMap<>();
        soldierGenerationRequests = new ArrayList<>();
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

    public List<Cell> getBaseCells() {
        return this.bases;
    }

    public void pushSoldierGenerationRequest(int xPosition, int yPosition) {
        soldierGenerationRequests.add(new SoldierGenerationRequest(xPosition, yPosition));
    }

    public void pushSoldierGenerationRequest(int count) {
        Collections.shuffle(bases);
        for (int i = 0; i < count; i++) {
            if (i >= bases.size())
                return;

            Cell base = bases.get(i);
            soldierGenerationRequests.add(new SoldierGenerationRequest(base));
        }
    }

    public SoldierGenerationRequest popSodlierGenerationRequest() {
        if (!hasAnyPendingSoldierGenerationRequest())
            return null;

        return soldierGenerationRequests.remove(0);
    }

    public boolean hasAnyPendingSoldierGenerationRequest() {
        return soldierGenerationRequests.size() > 0;
    }

    public Ant getQueen() {
        return queen;
    }

    public Cell getRandomBase() {
        Random random = new Random();
        return bases.get(random.nextInt(bases.size()));
    }
}
