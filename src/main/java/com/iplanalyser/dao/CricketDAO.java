package com.iplanalyser.dao;

import com.iplanalyser.model.MostRunsCSV;
import com.iplanalyser.model.MostWicketsCSV;

public class CricketDAO {
    public double average;
    public double strikeRate;
    public int runs;
    public int fours;
    public int sixes;
    public int fourWickets;
    public int fiveWickets;
    public String player;
    public double economy;
    public int totalWickets;

    public CricketDAO(MostRunsCSV mostRunsCSV) {
        runs = mostRunsCSV.runs;
        average = mostRunsCSV.battingAverage;
        strikeRate = mostRunsCSV.strikeRate;
        fours = mostRunsCSV.fours;
        sixes = mostRunsCSV.sixes;
        player=mostRunsCSV.player;
    }

    public CricketDAO(MostWicketsCSV mostWicketsCSV) {
        average = mostWicketsCSV.bowlingAverage;
        fourWickets = mostWicketsCSV.fourWickets;
        fiveWickets = mostWicketsCSV.fiveWickets;
        player=mostWicketsCSV.bowlerName;
        strikeRate=mostWicketsCSV.strikeRate;
        player=mostWicketsCSV.bowlerName;
        economy = mostWicketsCSV.economy;
        totalWickets = mostWicketsCSV.totalWickets;
    }
}
