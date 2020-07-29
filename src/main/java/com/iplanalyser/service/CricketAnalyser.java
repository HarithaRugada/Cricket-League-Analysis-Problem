package com.iplanalyser.service;

import com.google.gson.Gson;
import com.iplanalyser.comparator.BatsmanComparator;
import com.iplanalyser.comparator.BowlerComparator;
import com.iplanalyser.dao.CricketDAO;
import com.iplanalyser.adapter.CricketAdapterFactory;
import com.iplanalyser.enums.SortedField;
import com.iplanalyser.exception.CricketAnalyserException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {
    List<CricketDAO> cricketDAOList;
    Map<SortedField, Comparator<CricketDAO>> sortedMap;
    Map<String, CricketDAO> cricketDTOMap;

    public enum IPL {RUNS, WICKETS};

    public CricketAnalyser() {
        this.cricketDAOList = new ArrayList<>();
        this.sortedMap = new HashMap<>();
        this.cricketDTOMap = new HashMap<>();

        Comparator<CricketDAO> avgStrike = Comparator.comparing(res -> res.average);
        this.sortedMap.put(SortedField.AVG_STRIKE, avgStrike.thenComparing(res -> res.strikeRate));

        this.sortedMap.put(SortedField.AVERAGE, Comparator.comparing(IplFields -> IplFields.average));
        this.sortedMap.put(SortedField.STRIKE_RATE, Comparator.comparing(IplFields -> IplFields.strikeRate));
        this.sortedMap.put(SortedField.ECONOMY, Comparator.comparing(IplFields -> IplFields.economy));
        this.sortedMap.put(SortedField.STRIKE_WITH_WICKETS, new BowlerComparator().thenComparing(IplFields -> IplFields.strikeRate));
        this.sortedMap.put(SortedField.MAXIMUM_HIT, Comparator.comparing(IplFields -> IplFields.fours + IplFields.sixes));
        this.sortedMap.put(SortedField.BEST_STRIKE, new BatsmanComparator().thenComparing(res -> res.strikeRate));

        Comparator<CricketDAO> avgBowl = Comparator.comparing(res -> res.average);
        this.sortedMap.put(SortedField.BOWLING_AVERAGE, avgBowl.thenComparing(res -> res.strikeRate));

        Comparator<CricketDAO> maxWickets = Comparator.comparing(res -> res.totalWickets);
        this.sortedMap.put(SortedField.MAXIMUM_WICKETS, maxWickets.thenComparing(res -> res.average));

        Comparator<CricketDAO> maxRuns = Comparator.comparing(res -> res.runs);
        this.sortedMap.put(SortedField.MAX_RUNS, maxRuns.thenComparing(res -> res.average));
    }

    public int getCricketDataFile(IPL ipl, String... csvFilePath) throws IOException {
        cricketDTOMap = CricketAdapterFactory.getCricketData(ipl, csvFilePath);
        cricketDAOList = cricketDTOMap.values().stream().collect(Collectors.toList());
        return cricketDTOMap.size();
    }


    public String getSortedCricketData(SortedField sortedField) {
        if (cricketDAOList == null || cricketDAOList.size() == 0) {
            throw new CricketAnalyserException("No Data", CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(this.sortedMap.get(sortedField));
        Collections.reverse(cricketDAOList);
        String sortedIplData = new Gson().toJson(cricketDAOList);
        return sortedIplData;
    }

    private void sort (Comparator<CricketDAO> cricketComparator) {
        for (int i = 0; i < cricketDAOList.size() - 1; i++) {
            for (int j = 0; j < cricketDAOList.size() - i - 1; j++) {
                CricketDAO run1 = cricketDAOList.get(j);
                CricketDAO run2 = cricketDAOList.get(j + 1);
                if (cricketComparator.compare(run1, run2) > 0) {
                    cricketDAOList.set(j, run2);
                    cricketDAOList.set(j + 1, run1);
                }
            }
        }
    }
}

