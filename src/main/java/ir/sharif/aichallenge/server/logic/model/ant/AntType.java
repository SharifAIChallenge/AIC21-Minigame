package ir.sharif.aichallenge.server.logic.model.ant;

public enum AntType {
    SCORPION, QUEEN;

    public int getValue() {
        switch (this) {
        case QUEEN:
            return 1;
        case SCORPION:
            return 0;
        default:
            return 1;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case QUEEN:
            return "queen";
        case SCORPION:
            return "scorpion";
        default:
            return "none";
        }
    }
}
