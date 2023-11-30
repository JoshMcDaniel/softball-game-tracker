package com.gametracker.softball;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SoftballGame {
    private String documentId;
    private String homeTeamName;
    private String awayTeamName;
    private String gameStatus;
    private String startDateTime;
    private List<Map<String, Integer>> innings;
    private int strikesAllowed;
    private int strikes;
    private int balls;
    private int outs;

    // Default constructor with default values for other fields
    public SoftballGame(SoftballGameCreate gameData) {
        this.homeTeamName = gameData.getHomeTeamName();
        this.awayTeamName = gameData.getAwayTeamName();
        this.startDateTime = gameData.getStartDateTime();
        this.strikesAllowed = gameData.getStrikesAllowed();

        this.gameStatus = "SCHEDULED";
        this.innings = new ArrayList<>();
        this.strikes = 0;
        this.balls = 0;
        this.outs = 0;

        // Set ID dynamically
        String homeTeamId = gameData.getHomeTeamName().replaceAll(" ", "-").toLowerCase();
        String awayTeamId = gameData.getAwayTeamName().replaceAll(" ", "-").toLowerCase();
        this.documentId = homeTeamId + "-vs-" + awayTeamId + "_T_" + gameData.getStartDateTime();
    }
}
