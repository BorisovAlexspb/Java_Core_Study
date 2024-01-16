package edu.hw9.Task2;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileSizeSearcher extends RecursiveTask<List<File>> {
    private final File directory;
    private final long size;

    public FileSizeSearcher(File directory, long size) {
        this.directory = directory;
        this.size = size;
    }

    @Override
    protected List<File> compute() {
        File[] files = directory.listFiles();
        List<File> fitSizeFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.length() > size) {
                    fitSizeFiles.add(file);
                } else {
                    FileSizeSearcher task = new FileSizeSearcher(file, size);
                    task.fork();
                    fitSizeFiles.addAll(task.join());
                }
            }
        }
        return fitSizeFiles;
    }
}
