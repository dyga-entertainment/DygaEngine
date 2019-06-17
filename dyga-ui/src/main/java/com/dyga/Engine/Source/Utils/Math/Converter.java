package com.dyga.Engine.Source.Utils.Math;

public class Converter {

    public static long convertMillisecToNanosec(long milliseconds) {
        return milliseconds * 1000000L;
    }

    public static long convertNanosecToMillisec(long nanoseconds) {
        return nanoseconds / 1000000L;
    }

    public static double convertNanosecToSeconds(double nanoseconds) {
        return nanoseconds / 1000000000L;
    }

    public static double convertSecondsToNanosec(double seconds) {
        return seconds * 1000000000L;
    }
}
