import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BubbleApp 
{
    /**
     * Using an int provided by the user, create a random array of 
     * that size with values ranging between 1 and 100.
     * 
     * @param arrayLength Size of array based on user input.
     * @return Array of random ints from 1 to 100.
     */
    public static int[] createRandomArray (int arrayLength)
    {
        int[] array = new int[arrayLength];
        Random random = new Random();

        for (int i = 0; i < arrayLength; i++)
        {
            array[i] = random.nextInt(100);
        }

        return array;
    }

    /**
     * Writes each index of a given array onto each line a .txt file.
     * 
     * @param array Array to write.
     * @param filename  File to write values onto.
     */
    public static void writeArrayToFile (int[] array, String filename)
    {
        try (FileWriter fileWriter = new FileWriter(filename))
        {
            for (int i = 0; i < array.length; i++)
            {
                fileWriter.write(array[i] + "\n");
            }
        }
        catch(IOException e)
        {
            
        }
    }

    /**
     * Insert each value of a file line-by-line into an array.
     * 
     * @param filename File to read from.
     * @return Array of ints.
     */
    public static int[] readFileToArray(String filename)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try 
        {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                String s = scanner.nextLine();
                arrayList.add(Integer.parseInt(s));
            }

            scanner.close();
        }
        catch (IOException e)
        {

        }

        int[] array = new int[arrayList.size()];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = arrayList.get(i);
        }

        return array;
    }

    /**
     * Uses bubble sort method to sort array in ascending order.
     * 
     * @param array Array to be sorted.
     */
    public static void bubbleSort(int[] array)
    {
        for (int i = array.length; i > 1; i--)
        {
            for (int j = 0; j < i - 1; j++)
            {
                if (array[j] > array[j + 1])
                {    
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception 
    {
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Welcome! Please enter the size of an array you wish to sort:"); 
        String userInput = scanner.next();
        int arrayLength = 0;

        while(!(userInput.equals("-1")))
        {
            try
            {
            // Create random array and write it to a file. 
            arrayLength = Integer.parseInt(userInput);
            int[] array = createRandomArray(arrayLength); 
            writeArrayToFile(array, "unsortedData.txt");
            
            // Create an array of the unsorted original array from the file it was written to.
            int[] array2 = readFileToArray("unsortedData.txt");
            
            // Sort the new array and store it in another file.
            bubbleSort(array2); 
            writeArrayToFile(array2, "sortedData.txt");

            // Print out unsorted and sorted arrays.
            System.out.println("Unsorted array: " + Arrays.toString(array));
            System.out.println("Sorted array: " + Arrays.toString(array2));

            System.out.println("If you wish to make another array, enter another value, or exit by typing '-1':");
            }
            catch (NumberFormatException e)  // Handles non-digit inputs.
            {
                System.out.println("Input must be a number. Please try again.");
            }
            catch (NegativeArraySizeException e2)  // Handles negative inputs.
            {
                System.out.println("Input must be positive. Please try again.");
            }

            userInput = scanner.next();
        }

        System.out.println("Goodbye!");

        scanner.close();
    }
}
