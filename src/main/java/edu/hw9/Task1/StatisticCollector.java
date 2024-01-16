package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatisticCollector {

    private final Queue<Metric> statistics = new ConcurrentLinkedQueue<>();
    private final ExecutorService executorService;

    public StatisticCollector(int threadNumber) {
        executorService = Executors.newFixedThreadPool(threadNumber);
    }

    public void push(String metricName, double[] values) {
        executorService.execute(
            putMetric(metricName, values)
        );
    }

    private Runnable putMetric(String metricName, double[] values) {
        return () -> {
            double sum = 0.0;
            double max = Double.NEGATIVE_INFINITY;
            double min = Double.POSITIVE_INFINITY;

            for (double value : values) {
                if (value > max) {
                    max = value;
                }

                if (value < min) {
                    min = value;
                }

                sum += value;
            }

            statistics.add(new Metric(
                metricName, sum, sum / values.length, max, min
            ));
        };
    }

    public List<Metric> stats() {
        return new ArrayList<>(statistics);
    }

}
