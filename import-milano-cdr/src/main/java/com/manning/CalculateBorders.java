package com.manning;

import org.apache.commons.io.FileUtils;
import org.joda.time.format.DateTimeFormat;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 * User: sergey.sheypak
 * Date: 01.03.15
 * Time: 12:01
 */
public class CalculateBorders {

    public static String OUTPUT_PROPERTIES = "oozie.action.output.properties";

    public static final String PATH_SPLITTER = "/";

    public static void main(String... args) throws ParseException, IOException {
        long start = ymdPathToMillis(args[0]);
        long end = getNextDay(start);
        String properties = createContents(start, end);
        writeToFile(properties);
    }

    public static String createContents(long start, long end){
        return new StringBuilder("start=").append(start).append("\n")
                         .append("end=").append(end)
                         .toString();
    }


    public static void writeToFile(String contents) throws IOException {
        File file = new File("output.txt");
        FileUtils.writeStringToFile(file, contents);
        System.setProperty(OUTPUT_PROPERTIES, file.getAbsolutePath());
    }



    public static long getNextDay(long start){
        return start + TimeUnit.DAYS.toMillis(1);
    }

    public static long ymdPathToMillis(String path)throws ParseException {
        String ymd = pathToYMD(path);
        return parseYMD(ymd);
    }

    public static long parseYMD(String ymd) throws ParseException {
        return DateTimeFormat.forPattern("yyyy/MM/dd").parseDateTime(ymd).getMillis();
    }

    public static String pathToYMD(String path){
        String[] splitted = path.split(PATH_SPLITTER);
        return new StringBuilder().append(splitted[splitted.length-3]).append(PATH_SPLITTER)
                                  .append(splitted[splitted.length - 2]).append(PATH_SPLITTER)
                                  .append(splitted[splitted.length - 1]).toString();
    }
}
