package com.dyga.Engine.Source.Utils;

import java.text.DecimalFormat;

import static com.dyga.Engine.Source.Utils.Math.Converter.convertNanosecToSeconds;
import static com.dyga.Engine.Source.Utils.Math.Converter.convertSecondsToNanosec;

public class GameStatsHelper {

    // Let the program record stats every 1 second (roughly)
    private static final long MAX_STATS_INTERVAL_NANOSEC = 1000000000L;

    // number of FPS values stored to get an average
    public static final int NUM_FPS = 10;

    // used for gathering statistics
    private static long statsIntervalNs = 0L;    // in ns

    private static long totalElapsedTimeNs = 0L;

    private static long frameCount = 0;

    private static long statsCount = 0;

    private static long framesSkipped = 0L;
    private static long totalFramesSkipped = 0L;

    private static double fpsStore[];
    private static double upsStore[];

    // Stats variables
    private static int timeSpentInGameSec = 0;
    private static double averageFPS = 0.0;
    private static double averageUPS = 0.0;

    private static DecimalFormat timedf = new DecimalFormat("0.####");  // 4 dp
    private static DecimalFormat df = new DecimalFormat("0.##");  // 2 dps

    // initialise timing elements
    public void initStatsVariables() {
        fpsStore = new double[NUM_FPS];
        upsStore = new double[NUM_FPS];
        for (int i=0; i < NUM_FPS; i++) {
            fpsStore[i] = 0.0;
            upsStore[i] = 0.0;
        }
    }

    /* The statistics:
       - the summed periods for all the iterations in this interval
         (period is the amount of time a single frame iteration should take),
         the actual elapsed time in this interval,
         the error between these two numbers;

       - the total frame count, which is the total number of calls to run();

       - the frames skipped in this interval, the total number of frames
         skipped. A frame skip is a game update without a corresponding render;

       - the FPS (frames/sec) and UPS (updates/sec) for this interval,
         the average FPS & UPS over the last NUM_FPSs intervals.

     The data is collected every MAX_STATS_INTERVAL  (1 sec). */
    public static long storeStats(long frameTimeNs, long gameStartTime, long prevStatsTimeNs) {
        long result = prevStatsTimeNs;

        // increment the number of frame
        frameCount++;

        // We should retrieve the stat only when we update the number of time decided the game.
        statsIntervalNs += frameTimeNs;

        // We only want to stats every seconds
        if (statsIntervalNs >= MAX_STATS_INTERVAL_NANOSEC) {

            // Cache the current time
            long timeNowNs = java.lang.System.nanoTime();

            // STAT 1 : Compute how much long in the game so far
            timeSpentInGameSec = (int)convertNanosecToSeconds(timeNowNs - gameStartTime);  // ns --> secs

            // Compute how much real time this 1 seconds (last stats collection) has took !
            long realElapsedTimeNs = timeNowNs - prevStatsTimeNs;
            totalElapsedTimeNs += realElapsedTimeNs;

            // Compute error
            double timingError =
                ((double)(realElapsedTimeNs - statsIntervalNs) / statsIntervalNs) * 100.0;

            // Number of frame skipped
            totalFramesSkipped += framesSkipped;

            // STAT 2 : calculate the latest FPS and UPS
            double actualFPS = 0;
            double actualUPS = 0;
            if (totalElapsedTimeNs > 0) {
                actualFPS = convertSecondsToNanosec((double)frameCount / totalElapsedTimeNs);
                actualUPS = convertSecondsToNanosec(((double)frameCount + totalFramesSkipped) / totalElapsedTimeNs);
            }

            // Store the latest FPS and UPS
            fpsStore[ (int)statsCount % NUM_FPS ] = actualFPS;
            upsStore[ (int)statsCount % NUM_FPS ] = actualUPS;
            statsCount = statsCount+1;

            // Total the stored FPSs and UPSs (basic average mean)
            double totalFPS = 0.0;
            double totalUPS = 0.0;
            for (int i=0; i < NUM_FPS; i++) {
                totalFPS += fpsStore[i];
                totalUPS += upsStore[i];
            }

            // obtain the average FPS and UPS
            if (statsCount < NUM_FPS) {
                averageFPS = totalFPS/statsCount;
                averageUPS = totalUPS/statsCount;
            } else {
                averageFPS = totalFPS / NUM_FPS;
                averageUPS = totalUPS / NUM_FPS;
            }

            System.out.println(timedf.format( (double) convertNanosecToSeconds(statsIntervalNs)) + " " +
                timedf.format((double) convertNanosecToSeconds(realElapsedTimeNs)) + "s " +
                df.format(timingError) + "% " +
                frameCount + "c " +
                framesSkipped + "/" + totalFramesSkipped + " skip; " +
                df.format(actualFPS) + " " + df.format(averageFPS) + " afps; " +
                df.format(actualUPS) + " " + df.format(averageUPS) + " aups" );

            // Cache the value for next stats
            framesSkipped = 0;
            result = timeNowNs;
            statsIntervalNs = 0L;   // reset
        }
        return result;
    }

    public int getTimeSpentInGameSec() {
        return timeSpentInGameSec;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

}
