package ru.bspb.logparser;

import java.util.HashMap;
import java.util.Map;

public enum LogLevel {
    INFO ("INFO"),
    WARN ("WARN"),
    DEBUG ("DEBUG"),
    FATAL ("FATAL"),
    ERROR ("ERROR"),
    TRACE ("TRACE");

    LogLevel(String name){
        this.name = name;
    }

    private final String name;

    public String getName(){
        return name;
    }

    private static final Map<String, LogLevel> map = new HashMap<>();

    static {
        for (LogLevel level : values()){
            map.put(level.getName(), level);
        }
    }

    public static LogLevel getLevelByName (String name){
        return map.get(name);
    }

}
