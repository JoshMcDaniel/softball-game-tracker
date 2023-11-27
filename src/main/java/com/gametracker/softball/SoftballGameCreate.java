package com.gametracker.softball;

import lombok.Data;

@Data
public class SoftballGameCreate {
    private String homeTeamName;
    private String awayTeamName;
    private String startDateTime;
    private Integer strikesAllowed;
}
