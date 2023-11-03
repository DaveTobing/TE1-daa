import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DatasetGenerator {
    public static void main(String[] args) {
        generateAndSaveDataset(9, "sorted");
        generateAndSaveDataset(9, "random");
        generateAndSaveDataset(9, "reversed");

        generateAndSaveDataset(13, "sorted");
        generateAndSaveDataset(13, "random");
        generateAndSaveDataset(13, "reversed");

        generateAndSaveDataset(16, "sorted");
        generateAndSaveDataset(16, "random");
        generateAndSaveDataset(16, "reversed");
    }

    public static void generateAndSaveDataset(int power, String datasetType) {
        int size = (int) Math.pow(2, power);
        int[] dataset = generateDataset(size, datasetType);
        String fileName = "";

        if (power == 9){
            fileName = "dataset_kecil_" + datasetType + ".txt";
        }
        else  if (power == 13){
            fileName = "dataset_sedang_" + datasetType + ".txt";
        }
        else  if (power == 16){
            fileName = "dataset_besar_" + datasetType + ".txt";
        }
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int value : dataset) {
                writer.write(value + "\n");
            }
            writer.close();
            System.out.println("Dataset saved to " + fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] generateDataset(int size, String datasetType) {
        int[] dataset = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            dataset[i] = random.nextInt(Integer.MAX_VALUE);
        }

        if (datasetType.equals("sorted")) {
            Arrays.sort(dataset);
        }

        else if (datasetType.equals("reversed")) {
            for (int i = 0; i < size / 2; i++) {
                int temp = dataset[i];
                dataset[i] = dataset[size - 1 - i];
                dataset[size - 1 - i] = temp;
            }
        }

        return dataset;
    }
}
