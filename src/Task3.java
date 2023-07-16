import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Task3 {
    private static List<Integer> numbers;
    private static AtomicInteger evenCount = new AtomicInteger(0);
    private static AtomicInteger oddCount = new AtomicInteger(0);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу: ");
        String filePath = scanner.nextLine();

        // read from file
        numbers = readNumbersFromFile(filePath);

        // create threads
        EvenThread evenThread = new EvenThread();
        OddThread oddThread = new OddThread();
        evenThread.start();
        oddThread.start();

        try {
            // Очікуємо завершення обох потоків
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Виведення результатів
        System.out.println("Кількість парних елементів: " + evenCount);
        System.out.println("Кількість непарних елементів: " + oddCount);
    }

    private static List<Integer> readNumbersFromFile(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                numbers.add(number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    static class EvenThread extends Thread {
        @Override
        public void run() {
            try (PrintWriter writer = new PrintWriter(new FileWriter("even_numbers.txt"))) {
                for (int number : numbers) {
                    if (number % 2 == 0) {
                        writer.println(number);
                        evenCount.incrementAndGet();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class OddThread extends Thread {
        @Override
        public void run() {
            try (PrintWriter writer = new PrintWriter(new FileWriter("odd_numbers.txt"))) {
                for (int number : numbers) {
                    if (number % 2 != 0) {
                        writer.println(number);
                        oddCount.incrementAndGet();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


//D:\SomeDir\notes.txt
