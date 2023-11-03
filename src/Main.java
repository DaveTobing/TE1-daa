import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Create an array of file names containing datasets
            String[] files = {"dataset_kecil_random.txt", "dataset_kecil_reversed.txt" , "dataset_kecil_sorted.txt",
                    "dataset_sedang_random.txt", "dataset_sedang_reversed.txt" , "dataset_sedang_sorted.txt",
                    "dataset_besar_random.txt", "dataset_besar_reversed.txt" , "dataset_besar_sorted.txt"};

            for (String filename : files) {
                // Read the dataset from the text file
                int[] dataset = readDataset(filename);

                // Measure HeapSort execution time and memory usage
                long HeapStart = System.nanoTime();
                System.gc();
                System.gc();
                System.gc();
                long heapSortStartMemoryUsage = getMemoryUsage();
                HeapSort heapSort = new HeapSort();
                heapSort.sort(dataset);
                long heapSortEndMemoryUsage = getMemoryUsage();
                long HeapEnd = System.nanoTime();

                double heapSortExecutionTime = ((double) (HeapEnd - HeapStart) / 1_000_000);
                double heapSortMemoryUsage = (double) ((heapSortEndMemoryUsage - heapSortStartMemoryUsage) / 1024);

                System.out.println("HeapSort on " + filename);
                System.out.println("Execution Time (ms): " + heapSortExecutionTime);
                System.out.println("Memory Usage (KB): " + heapSortMemoryUsage);


                // Restore the dataset for ShellSort
                dataset = readDataset(filename);

                // Measure randomizedShellSort execution time and memory usage
                long ShellStart = System.nanoTime();
                System.gc();
                System.gc();
                System.gc();
                long shellSortStartMemoryUsage = getMemoryUsage();
                ShellSort.randomizedShellSort(dataset);
                long shellSortEndMemoryUsage = getMemoryUsage();
                long ShellEnd = System.nanoTime();
                double shellSortExecutionTime = (double) ((ShellEnd - ShellStart) / 1_000_000);
                double shellSortMemoryUsage = (double) ((shellSortEndMemoryUsage - shellSortStartMemoryUsage) / 1024);

                System.out.println("RandomizedShellSort on " + filename);
                System.out.println("Execution Time (ms): " + shellSortExecutionTime);
                System.out.println("Memory Usage (KB): " + shellSortMemoryUsage);

                System.out.println("--------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read the dataset from a text file and return it as an integer array
    public static int[] readDataset(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        StringBuilder content = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            content.append(line).append(" ");
        }

        reader.close();

        String[] strValues = content.toString().trim().split(" ");
        int[] dataset = new int[strValues.length];

        for (int i = 0; i < strValues.length; i++) {
            dataset[i] = Integer.parseInt(strValues[i]);
        }

        return dataset;
    }

    // Get the current memory usage in bytes
    public static long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
