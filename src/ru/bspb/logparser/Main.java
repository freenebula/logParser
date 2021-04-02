package ru.bspb.logparser;

import java.io.IOException;

public class Main {


    public static void main (String[] args) throws IOException {
        String sourceDir = "data\\";
        Utils.PrintLogByLevel(sourceDir, LogLevel.INFO);
        Utils.printTheSameStings(sourceDir);

    }


}