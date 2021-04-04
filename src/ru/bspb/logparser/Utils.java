package ru.bspb.logparser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    private static String patternToString (String str, String ptrn){
        Pattern pattern = Pattern.compile(ptrn);
        Matcher matcher = pattern.matcher(str);
        String text = "";
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            text = str.substring(start,end);
            break;
        }
        return text;
    }

    private static ArrayList<String> filesToStringsArray(String dir) throws IOException {
        Path path = Path.of(dir);
        File file = new File(dir);
        ArrayList<String> stringsArray = new ArrayList<>();
        for (File fileArray : file.listFiles((directory, name) -> name.contains(".log"))) {
            Path readFile = Path.of(dir+fileArray.getName());
            for (String readAllLine : Files.readAllLines(readFile)) {
                stringsArray.add(readAllLine);
            }
        }
        return stringsArray;
    }

    public static void PrintLogByLevel (String dir, LogLevel level) throws IOException {
        ArrayList<LogEntry> str = new ArrayList<>();
        str.addAll(splitStringsWithFilename(dir));

        splitStringsWithFilename(dir).stream()
                .filter(o -> o.getLevel().equals(level))
                .forEach(System.out::println);
    }

    private static ArrayList<LogEntry> splitStringsWithFilename(String dir) throws IOException {
        Path path = Path.of(dir);
        File file = new File(dir);
        ArrayList<LogEntry> log = new ArrayList<>();
        String timeStampPattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}";
        String logLevelPattern = "[A-Z]{3,}";
        String messagePattern = "\\[.+";

        for (File fileArray : file.listFiles((directory, name) -> name.contains(".log"))) {
            Path readFile = Path.of(dir+fileArray.getName());
            for (String readAllLine : Files.readAllLines(readFile)) {
                String timeStamp = patternToString(readAllLine, timeStampPattern);
                LogLevel logLevel = LogLevel.getLevelByName(patternToString(readAllLine, logLevelPattern));
                String message = patternToString(readAllLine, messagePattern);
                log.add(new LogEntry(timeStamp, logLevel, message));
            }
        }
        return log;
    }


    public static void printTheSameStings (String dir) throws IOException{
        ArrayList<String> str = new ArrayList<>();
        str.addAll(filesToStringsArray(dir));
        Path path = Path.of(dir);
        File file = new File(dir);
        System.out.println("-------------------------------------Повторяющиеся строки-------------------------------------");
        Iterator<String> iterator = str.iterator();
        while (iterator.hasNext()){
            String string = iterator.next();
            int count = Collections.frequency(str, string);
            if (count == 1) {
                iterator.remove();
            }
        }
        dublicateRemove(str);

        for (String ss: str) {
            for (File fileArray : file.listFiles((directory, name) -> name.contains(".log"))) {
                Path readFile = Path.of(dir + fileArray.getName());
                int place = 1;
                for (String readAllLine : Files.readAllLines(readFile)) {
                    if (readAllLine.contains(ss)){
                        System.out.println(ss + " - в файле " + dir + fileArray.getName() + ", строка " + place);;
                    }
                    place++;
                }
            }
        }
    }

    private static void dublicateRemove (ArrayList<String> o){
        Set<String> uniq = new LinkedHashSet<>(o);
        o.clear();
        o.addAll(uniq);
    }


}


