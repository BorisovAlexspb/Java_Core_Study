package edu.hw9.Task2;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileExtensionSearcher extends RecursiveTask<List<File>> {
    private final File directory;
    private final String extension;

    public FileExtensionSearcher(File directory, String extension) {
        this.directory = directory;
        this.extension = extension;
    }

    @Override
    protected List<File> compute() {
        File[] files = directory.listFiles();
        List<File> fitExtensionFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getAbsolutePath().endsWith("." + extension)) {
                    fitExtensionFiles.add(file);
                } else {
                    FileExtensionSearcher task = new FileExtensionSearcher(file, extension);
                    task.fork();
                    fitExtensionFiles.addAll(task.join());
                }
            }
        }
        return fitExtensionFiles;
    }
}
