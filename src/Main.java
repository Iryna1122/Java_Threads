import java.util.Arrays;
import java.util.Scanner;
public class Main {

    private static int[] array;
    private static int min;
    private static int max;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter len of Array: -> ");
        int len=scanner.nextInt();

        array = new int[len]; // WARNING!!!!

        for (int i = 0; i<len; i++)
        {
            System.out.print("Element "+ (i+1) + ": ");
            array[i] = scanner.nextInt();
        }

        MinThread minThread = new MinThread();
        MaxThread maxThread = new MaxThread();

        minThread.start();
        maxThread.start();

        try {
            // Очікуємо завершення обох потоків
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);

    }

    static class MaxThread extends Thread
    {
        @Override
        public void run()
        {
            synchronized (array){
                max=array[0];
                for (int i = 1; i<array.length; i++)
                {
                    if(array[i]>max)
                    {
                        max=array[i];
                    }
                }
            }
        }
    }

    static class MinThread extends Thread
    {
        @Override
        public void run()
        {
            synchronized (array)
            {
                min=array[0];
                for(int i = 0; i<array.length; i++)
                {
                    if(array[i]<min)
                    {
                        min=array[i];
                    }
                }
            }
        }
    }
}