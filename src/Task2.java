import java.util.Scanner;

public class Task2 {
    private static int[] array;
    private static int sum;
    private static double average;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter len of array: ->");
        int len = scanner.nextInt();
        array=new int[len];
        System.out.println("Enter elements of array: ");

        for(int i = 0; i<array.length; i++)
        {
            System.out.print("El: " + (i+1) + ": ");
            array[i]=scanner.nextInt();
        }

        SumElements sumElements = new SumElements();
        AverageThread averThread = new AverageThread();

        sumElements.start();
        averThread.start();

        try {
            // Очікуємо завершення обох потоків
            sumElements.join();
            averThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }

    static class SumElements extends Thread
    {
        @Override
        public void run()
        {
            synchronized (array){
                sum = 0;
                for(int i = 0; i < array.length; i++)
                {
                    sum+=array[i];
                }
            }
        }
    }

    static class AverageThread extends Thread
    {
        @Override
        public void run()
        {

            synchronized (array)
            {
                double total = 0;
                for(int i = 0; i < array.length; i++)
                {
                    total+=array[i];
                }
                average = total/array.length;
            }
        }
    }
}
//D:\SomeDir\notes.txt