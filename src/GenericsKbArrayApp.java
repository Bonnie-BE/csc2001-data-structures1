/**@author [Bonani]
@version 1.0
@since [24/02/2024]
*/

import java.util.*;
import java.io.*;
/**
 * GenericsKbArrayApp class with main.
 */
public class GenericsKbArrayApp {
    private String[][] genericsArray; /**Array that will be storing the data from the file*/
    private int row; /**Counter that keeps track of the array elements*/

/**
* Constructor to create array and assign a counter for lines/rows.
*/
    public GenericsKbArrayApp() {
        this.genericsArray = new String[1000000][3];
        this.row = 0;
    }
    
/**
* Reads in the file into the array and stores in the knowledge base(array) line by line.
* @param filename the filename read in(input by user), in order to extract the information to load in the knowledge base
*/
    public void addToArray(String filename) {

        try {
            File fileReader = new File(filename);
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\t");

                String term = words[0];
                boolean existInArray = false;
                for (int i = 0; i < row; i++) {
                    if (genericsArray[i][0].equals(term)) {
                        existInArray = true;
                        break;
                    }
                }
                if(!existInArray) {
                    genericsArray[row] = words;
                    row++;
                }
            }
            scanner.close();
            System.out.println("Knowledge base loaded successfully.");
        }
        catch(Exception e){
               System.out.println("File not found!");
            }
        }
        
/**
* An existing statement is updated based on the criteria/condition.
* @param term the term of the statement to be updated
* @param statement the statement to be updated
* @param cf_score the confidence score of the statement to be updated 
*/
    public void updateArray(String term, String statement, String cf_score) {

        for(int i = 0; i<row; i++) {
            double cf_score_new = Double.parseDouble(cf_score);
            double c_score_array = Double.parseDouble(genericsArray[i][2]);
            
            if (genericsArray[i][0].equals(term) && cf_score_new > c_score_array) {
                genericsArray[i][1] = statement;
                genericsArray[i][2] = cf_score;
                System.out.println("Statement for term " + term + " has been updated.");
            }
            else if (genericsArray[i][0].equals(term) && cf_score_new <= c_score_array) {
               System.out.println("Statement for term " + term + " has not been updated, confidence score is less than that in the knowledge base.");
            }
            else {
                genericsArray[i][0] = term;
                genericsArray[i][1] = statement;
                genericsArray[i][2] = cf_score;

            }
        }
    }
    
/**
* Searches for the given term in the array and print if found, with it's corresponding statement and confidence score.
* @param term the term of the statement the user wants to search for
*/
    public void searchTermArray(String term) {
        String print_statement = "";
        if(genericsArray == null) {
            System.out.println("Statement not found!");
        }
        else {
            for(int i = 0; i<row; i++) {
                if (genericsArray[i][0].equals(term)) {
                  print_statement = "Statement found: " + genericsArray[i][1] + " (Confidence score: " + genericsArray[i][2] + ")";
                }
                else {
                  print_statement = "Statement not found!";
   
                }
            }
            System.out.println(print_statement);
        }
    }
    
    
    
    
    
/**
* Searches for the given term and statement in the array and print if found, with it's corresponding statement and confidence score.
* @param term the term of the statement the user wants to search for
* @param statement the statement the user wants to search for
*/
    public void searchTermStatementArray(String term, String statement) {
        String print_statement = "";
        if(genericsArray == null) {
            System.out.println("Statement not found!");
        }
        else {
            for(int i = 0; i<row; i++) {
                if (genericsArray[i][0].equals(term) && genericsArray[i][1].equals(statement)) {
                    print_statement = "The statement was found and has a confidence score of " + genericsArray[i][2];
                }
                else {
                    print_statement = "Statement not found!";
                
                }
            }
            System.out.println(print_statement);
        }
    }
    
/**
*This is the main method of the GenericsKbArrayApp class, that provides a user interface that then uses the methods of GenericsKbArrayApp class.
*The main method constists of options for loading information into the knowledge base, updating and searching the knowledge base,
all the options done using an array, and exiting the program.
*@param args an array of command-line arguments
*@throws FileNotFoundException if the given file cannot be found
*/
    public static void main(String[] args) throws FileNotFoundException{

        GenericsKbArrayApp arrayApp = new GenericsKbArrayApp();

        try(Scanner kb = new Scanner(System.in)) {
            boolean quit = false;
            while(!quit) {
                System.out.println("Choose an action from the menu:");
                System.out.println("1. Load a knowledge base from a file");
                System.out.println("2. Update a statement in the knowledge base");
                System.out.println("3. Search for an item in the knowledge base by term");
                System.out.println("4. Search for a item in the knowledge base by term and sentence");
                System.out.println("5. Quit");
                System.out.println("6. Help.");
                System.out.print("Enter your choice: ");

                int option = kb.nextInt();
                kb.nextLine();
                switch(option) {
                    case 1:
                        System.out.print("Enter the filename: ");
                        String filename = kb.nextLine();
                        arrayApp.addToArray(filename);
                        break;
                    case 2:
                        System.out.print("Enter the term: ");
                        String term = kb.nextLine().toLowerCase();
                        System.out.print("Enter the statement: ");
                        String statement = kb.nextLine();
                        System.out.print("Enter the confidence score: ");
                        String cf_score = kb.nextLine();
                        arrayApp.updateArray(term, statement, cf_score);
                        break;
                    case 3:
                        System.out.print("Enter the term to search: ");
                        String term1 = kb.nextLine().toLowerCase();
                        arrayApp.searchTermArray(term1);
                        break;
                    case 4:
                        System.out.print("Enter the term: ");
                        String term2 = kb.nextLine().toLowerCase();
                        System.out.print("Enter the statement to search for: ");
                        String statement2 = kb.nextLine();
                        arrayApp.searchTermStatementArray(term2, statement2);
                        break;
                    case 5:
                        quit = true;
                    case 6:
                         try {
                              Scanner help = new Scanner(new FileInputStream("help.txt"));
                              
                              while (help.hasNextLine()) {
                                 System.out.println(help.nextLine());
                              }
                              System.out.println("Press Any Key and then ENTER to Exit");
                              kb.nextLine();
                         }
                         catch(FileNotFoundException e) {
                              System.out.println(e.getMessage());
                         }
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
