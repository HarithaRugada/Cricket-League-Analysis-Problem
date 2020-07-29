package com.iplanalyser;

import com.google.gson.Gson;
import com.iplanalyser.dao.CricketDAO;
import com.iplanalyser.enums.SortedField;
import com.iplanalyser.service.CricketAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CricketAnalyserTest {

    private static final String IPL_2019_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_2019_MOST_WICKETS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";


    @Test
    public void givenIPLMostRunsCSVFile_ReturnsTopBattingAverage_InIPL2019() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.RUNS, IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.AVERAGE);
        CricketDAO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsTopStrikingRate_InIPL2019() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.RUNS, IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
        CricketDAO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsCricketer_WhoHitMaximum4sAnd6s_InIPL2019() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.RUNS, IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.MAXIMUM_HIT);
        CricketDAO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsCricketer_WhoHasMaximumStrikeRateWithMaximum4sAnd6s() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.RUNS, IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.BEST_STRIKE);
        CricketDAO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_When_MaximumAverage_Returns_BestStrikeRate() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.RUNS, IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.AVG_STRIKE);
        CricketDAO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnAverage_ReturnsMaximumRuns() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.RUNS, IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.MAX_RUNS);
        CricketDAO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("David Warner", mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostWicketsCSVFile_ReturnsTopBowlingAverage_InIPL2019() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.WICKETS, IPL_2019_MOST_WICKETS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.AVERAGE);
        CricketDAO[] mostWicketsCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWicketsCsv[0].player);
    }

    @Test
    public void givenIPLMostWicketsCSVFile_WhenSorted_ReturnsTopStrikingRates() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.WICKETS, IPL_2019_MOST_WICKETS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
        CricketDAO[] mostWicketsCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWicketsCsv[0].player);
    }

    @Test
    public void givenIPLMostWicketsCSVFile_WhenSorted_ReturnsBestEconomy() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.WICKETS, IPL_2019_MOST_WICKETS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.ECONOMY);
        CricketDAO[] mostWicketsCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Ben Cutting", mostWicketsCsv[0].player);
    }

    @Test
    public void givenIPLMostWicketsCSVFile_WhenSortedOn4wAnd5w_ReturnsBestStrikeRate() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.WICKETS, IPL_2019_MOST_WICKETS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.STRIKE_WITH_WICKETS);
        CricketDAO[] mostWicketsCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Lasith Malinga", mostWicketsCsv[0].player);
    }

    @Test
    public void givenIPLMostWicketsCSVFile_WhenSortedOnAverage_ReturnsBestStrikeRate() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.WICKETS, IPL_2019_MOST_WICKETS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.BOWLING_AVERAGE);
        CricketDAO[] mostWicketsCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWicketsCsv[0].player);
    }

    @Test
    public void givenIPLMostWicketsCSVFile_WhenSortedOnMaximumWickets_ReturnsBestAverage() throws IOException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(CricketAnalyser.IPL.WICKETS, IPL_2019_MOST_WICKETS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.MAXIMUM_WICKETS);
        CricketDAO[] mostWicketsCsv = new Gson().fromJson(sortedCricketData, CricketDAO[].class);
        Assert.assertEquals("Imran Tahir", mostWicketsCsv[0].player);
    }
}