package ru.bspb.logparser;

public class LogEntry {
    private String timeStamp;
    private LogLevel level;
    private String message;

    public LogEntry(String timeStamp, LogLevel level, String message){
        this.timeStamp = timeStamp;
        this.level = level;
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel logLevel) {
        this.level = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString (){
        return getTimeStamp() + " " + getLevel() + " " + getMessage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntry log = (LogEntry) o;
        return getTimeStamp().equals(log.getTimeStamp()) && getLevel().equals(log.getLevel().toString()) && getMessage().equals(log.getMessage());
    }
}
