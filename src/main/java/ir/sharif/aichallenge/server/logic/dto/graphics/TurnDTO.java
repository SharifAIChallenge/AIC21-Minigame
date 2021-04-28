package ir.sharif.aichallenge.server.logic.dto.graphics;

import java.util.List;

public class TurnDTO {
    public int turn_num;
    public int base0_health = 0;
    public int base1_health = 0;
    public List<CellDTO> cells;
    public List<AttackDTO> attacks;

    public int team0_alive_scorpions = 5;
    public int team0_total_scorpions = 40;
    public int team1_alive_scorpions = 5;
    public int team1_total_scorpions = 40;

    public List<ChatElementDTO> trivial_chat_box_0;
    public List<ChatElementDTO> trivial_chat_box_1;
    public List<ChatElementDTO> important_chat_box_0;
    public List<ChatElementDTO> important_chat_box_1;

}
