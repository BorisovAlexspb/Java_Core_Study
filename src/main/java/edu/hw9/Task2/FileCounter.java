package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileCounter extends RecursiveTask<List<File>> {
    private final File directory;
    private final int amount;

    public FileCounter(File directory, int amount) {
        this.directory = directory;
        this.amount = amount;
    }

    @Override
    protected List<File> compute() {
        int counter = 0;
        File[] files = directory.listFiles();
        List<File> fitAmountDirectories = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    counter++;
                } else {
                    FileCounter task = new FileCounter(file, amount);
                    task.fork();
                    fitAmountDirectories.addAll(task.join());
                }
            }
        }
        if (counter >= amount) {
            fitAmountDirectories.add(directory);
        }
        return fitAmountDirectories;
    }
}
