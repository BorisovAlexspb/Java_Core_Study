package edu.hw9;


import edu.hw9.Task2.FileCounter;
import edu.hw9.Task2.FileExtensionSearcher;
import edu.hw9.Task2.FileSizeSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Task2Test {
    @Test
    @DisplayName("file counter test")
    void fileCounterTest() {
        File directory = new File("src/test/java/edu/hw9/resourses");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileCounter fileCounter = new FileCounter(directory, 2);
        List<File> files = forkJoinPool.invoke(fileCounter);
        Collections.sort(files);
        List<File> expected = new ArrayList(List.of(
            new File("src/test/java/edu/hw9/resourses")
        ));
        Collections.sort(expected);
        Assertions.assertEquals(expected,files);
    }

    @Test
    @DisplayName("file extensions searcher test")
    void fileExtensionsSearchTest() {
        File directory = new File("src/test/java/edu/hw9/resourses");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileExtensionSearcher fileExtensionSearcher = new FileExtensionSearcher(directory, "java");
        List<File> files = forkJoinPool.invoke(fileExtensionSearcher);
        List<File> expected = new ArrayList(List.of(
            new File("src/test/java/edu/hw9/resourses/Bond.java"),
            new File("src/test/java/edu/hw9/resourses/CheckingAccount.java")
        ));
        Collections.sort(expected);
        Assertions.assertEquals(expected,files);
    }

    @Test
    @DisplayName("file sizes searcher test")
    void fileSizeSearchTest() {
        File directory = new File("src/test/java/edu/hw9/resourses");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileSizeSearcher fileSizeSearcher = new FileSizeSearcher(directory, 1000);
        List<File> files = forkJoinPool.invoke(fileSizeSearcher);
        Collections.sort(files);
        List<File> expected = new ArrayList<>(List.of(
            new File("src/test/java/edu/hw9/resourses/Bond.java")
        ));
        Collections.sort(expected);
        Assertions.assertEquals(expected, files);
    }
}
