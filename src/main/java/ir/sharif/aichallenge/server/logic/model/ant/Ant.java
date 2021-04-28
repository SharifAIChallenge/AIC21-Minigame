package ir.sharif.aichallenge.server.logic.model.ant;

import ir.sharif.aichallenge.server.logic.config.ConstConfigs;

public class Ant {
    private int id;
    private int colonyId;
    private int health;
    private AntType antType;
    // TODO: use Cell instead of x, y
    private int xPosition;
    private int yPosition;
    // just used for worker ant

    public Ant(int id, int colonyId, int xPosition, int yPosition, AntType antType) {
        this.id = id;
        this.colonyId = colonyId;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.antType = antType;
        setInitialHealth(antType);
    }

    private void setInitialHealth(AntType antType) {
        health = antType == AntType.QUEEN ? ConstConfigs.QUEEN_ANT_INITIAL_HEALTH
                : ConstConfigs.SOLDIER_ANT_INITIAL_HEALTH;
    }

    public void moveTo(int newX, int newY) {
        xPosition = newX;
        yPosition = newY;
    }

    public int getId() {
        return id;
    }

    public int getColonyId() {
        return colonyId;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public AntType getAntType() {
        return antType;
    }
}
