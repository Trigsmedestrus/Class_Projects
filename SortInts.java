//Becky Fox
//Section: AB
//Data Abstractions Assignment# 2
//SortInts Class

//A program that takes two filenames as arguments, and sorts the
//integers in the first file and stores the sorted integers in the
//second file.

//Param: input = textfile name that contains a list of integers with each integer separated by a new line.
//Param: output = textfile name that will contain the sorted list of all the integers. If there is already
//a file with this name, it will get overwritten, otherwise it will create a new textfile with this name.
//Pre: if exactly two arguments are not given, then this will throw an illegal argument exception and
//will exit the program.
//Post: Creates a new textfile containing the integers from the input file in sorted order.

import java.io.*;
import java.util.*;

public class SortInts {
   public static void main(String[] args) {
      if (args.length < 2)
         throw new IllegalArgumentException("You must enter exactly two arguments!");
      else {
         try {
            Scanner input = new Scanner(new File(args[0]));
            PrintStream output = new PrintStream(new File(args[1]));
            BinarySortingTree t = new BinarySortingTree();
            
            while (input.hasNext()) {
               t.insert(input.nextInt());
            }
            t.sortData(output);
                        
         } catch (FileNotFoundException e) {
            System.out.println("File: " + args[0] + " cannot be found.  " + e.getMessage());    
         }    
      }
   }
}
