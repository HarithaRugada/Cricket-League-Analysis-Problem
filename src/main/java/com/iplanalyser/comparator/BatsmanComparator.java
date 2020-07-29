package com.iplanalyser.comparator;

import com.iplanalyser.dao.CricketDAO;

import java.util.Comparator;

public class BatsmanComparator implements Comparator<CricketDAO> {
    public int compare(CricketDAO player1, CricketDAO player2){
        return (player1.fours + player1.sixes) - (player2.fours + player2.sixes);
    }
}
