package com.iplanalyser.exception;

public class CricketAnalyserException extends RuntimeException {
    public enum ExceptionType {
        CRICKET_CSV_FILE_PROBLEM, CRICKET_DATA_NOT_FOUND, IPL_FILE_PROBLEM
    }

    public ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
