package com.iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class MostRunsCSV {
    @CsvBindByName(column = "Avg",required = true)
    public double battingAverage;
}
