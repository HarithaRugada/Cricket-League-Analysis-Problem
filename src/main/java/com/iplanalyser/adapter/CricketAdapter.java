package com.iplanalyser.adapter;

import com.iplanalyser.dao.CricketDAO;
import com.iplanalyser.model.MostRunsCSV;
import com.iplanalyser.model.MostWicketsCSV;
import com.iplanalyser.exception.CricketAnalyserException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CricketAdapter extends RuntimeException{
    public abstract Map<String, CricketDAO> getCricketData(String...csvFilePath) throws IOException;

    public <E> Map<String, CricketDAO> getCricketData(Class<E> cricketCSVClass, String csvFilePath) {
        Map<String, CricketDAO> cricketMap = new HashMap<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> cricketCsvIterator = icsvBuilder.getCSVFileIterator(reader,cricketCSVClass);
            Iterable<E> cricketCsvIterable = ()->cricketCsvIterator;

            if(cricketCSVClass.getName().equals("com.cricketLeague.MostRunsCSV")){
                StreamSupport.stream(cricketCsvIterable.spliterator(),false)
                        .map(MostRunsCSV.class::cast)
                        .forEach(mostRunsCsv -> cricketMap.put(mostRunsCsv.player,new CricketDAO(mostRunsCsv)));
            }
            else if(cricketCSVClass.getName().equals("com.cricketLeague.MostWicketsCSV")){
                StreamSupport.stream(cricketCsvIterable.spliterator(),false)
                        .map(MostWicketsCSV.class::cast)
                        .forEach(mostWicketsCsv -> cricketMap.put(mostWicketsCsv.bowlerName,new CricketDAO(mostWicketsCsv)));
            }
            return cricketMap;
        }catch (IOException e){
            throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }
}
