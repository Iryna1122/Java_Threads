import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Task4 {

    private static boolean isWordFound;
    private static int wordCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу: ");
        String filePath = scanner.nextLine();
        System.out.print("Введіть слово для пошуку: ");
        String wordToFind = scanner.nextLine();

        SearchThread searchThread = new SearchThread(filePath, wordToFind);

        searchThread.start();

        try {
            searchThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (isWordFound) {
            System.out.println("Слово '" + wordToFind + "' існує " + wordCount + " разів.");
        } else {
            System.out.println("Слово '" + wordToFind + "' не існує.");
        }
    }

    static class SearchThread extends Thread {

        private String filePath;
        private String wordToFind;

        public SearchThread(String path, String word) {
            this.filePath = path;
            this.wordToFind = word;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(wordToFind)) {
                        synchronized (Task4.class) {
                            isWordFound = true;
                            wordCount++;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
