package com.iplanalyser.adapter;

import com.iplanalyser.dao.CricketDAO;
import com.iplanalyser.model.MostRunsCSV;

import java.io.IOException;
import java.util.Map;

public class IPLRunsAdapter extends CricketAdapter {
    @Override
    public Map<String, CricketDAO> getCricketData(String... csvFilePath) throws IOException {
        Map<String, CricketDAO> cricketCsvMap = super.getCricketData(MostRunsCSV.class,csvFilePath[0]);
        return cricketCsvMap;
    }
}
