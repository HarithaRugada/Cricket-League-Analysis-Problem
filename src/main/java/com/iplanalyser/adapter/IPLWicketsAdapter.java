package com.iplanalyser.adapter;

import com.iplanalyser.dao.CricketDAO;
import com.iplanalyser.model.MostWicketsCSV;

import java.io.IOException;
import java.util.Map;

public class IPLWicketsAdapter extends CricketAdapter {

    @Override
    public Map<String, CricketDAO> getCricketData(String... csvFilePath) throws IOException {
        Map<String, CricketDAO> cricketDTOMap = super.getCricketData(MostWicketsCSV.class,csvFilePath[0]);
        return cricketDTOMap;
    }
}
