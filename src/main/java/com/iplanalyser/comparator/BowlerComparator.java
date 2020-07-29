package com.iplanalyser.comparator;

import com.iplanalyser.dao.CricketDAO;

import java.util.Comparator;

public class BowlerComparator implements Comparator<CricketDAO> {
    public int compare(CricketDAO player1, CricketDAO player2) {
        return (player1.fourWickets + player1.fiveWickets) - (player2.fourWickets + player2.fiveWickets);
    }
}
