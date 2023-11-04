package com.gametracker.softball;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SoftballGame {
    private String documentId;
    private String homeTeamName;
    private String awayTeamName;
    private Integer currentInning;
    private String gameStatus;
//    private Score score;
//    private TimeStamp startDateTime;
//    private TimeStamp lastUpdated;
}
