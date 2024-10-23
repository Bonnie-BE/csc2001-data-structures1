/**
* A Binary Search Tree class that implements Generic class.
*/

import java.io.*;
import java.util.*;
/**
* GenericsKbBSTApp class with main.
*/
public class GenericsKbBSTApp {
/**
* A binary search tree root node.
*/
    private Node root;
   
/**
* A class that creates a node of the Generic.
* A binary search tree node.
*/
    private class Node {
        private Generic generic;
        private Node left, right;
        
/**
* Constructor to create node.
* @param generic the Generic object to store a node.
*/
        public Node(Generic generic) {
            this.generic = generic;
            left = right = null;
        }
    }
    
/**
* Creates an empty binary search tree.
*/  
    public GenericsKbBSTApp() {
      root = null;
    }
    
/**
* A new Generic object is inserted in the binary search tree, beginning at the given node 
or an existing one is updated based on the criteria.
* @param root the node to begin inserting at
* @param generic the Generic object to insert
* @return the root where it was inserted
*/
    private Node insert(Node root, Generic generic) {
        if (root == null) {
            return new Node(generic);
        }
        int compare = generic.getTerm().compareTo(root.generic.getTerm());
        if (compare < 0) {
            root.left = insert(root.left, generic);
        }
        else if (compare > 0) {
            root.right = insert(root.right, generic);
        }
        else {
            if(generic.getCfScore() > root.generic.getCfScore()) {
                root.generic.setStatement(generic.getStatement());
                root.generic.setCfScore(generic.getCfScore());
            }
            else if(generic.getCfScore() == root.generic.getCfScore()) {
                if (generic.getStatement().compareTo(root.generic.getStatement()) > 0) {
                    root.generic.setStatement(generic.getStatement());
                }
            }
        }
        return root;
    }
    
/**
* A new Generic object is inserted in the binary search tree.
* @param generic the Generic object the user wants to insert
*/    
        public void insertion(Generic generic) {
        root = insert(root, generic);
    }
    
/**
* Searches for a node with the given term in the binary search tree that has a root at the given node.
* @param root the root of the binary search tree the user wants to search
* @param term the term of the generic the user wants to search for
* @return null if a node does not exist, else the node that has the generic with the given term
*/
    private Generic searchByTerm(Node root, String term) {
        if (root == null) {
            return null;
        }
        int compare = term.compareTo(root.generic.getTerm());

        if (compare < 0) {
            return searchByTerm(root.left, term);
        } else if (compare > 0) {
            return searchByTerm(root.right, term);
        }
        else {
            return root.generic;
        }
    }
    
/**
* Searches for a Generic object in the binary search tree 
* @param term the term of the Generic object you want to search for
* @return the generic with the given term
*/
    public Generic searchingByTerm(String term) {
        return searchByTerm(root, term);
    }
    
/**
* Searches for a node with the given term and statement in the binary search tree that has a root at the given node.
* @param root the root of the binary search tree the user wants to search
* @param term the term of the generic the user wants to search for
* @param statement the statement of the generic the user wants to search for
* @return null if a node does not exist, else the node that has the generic with the given term and statement
*/
    private Generic searchByTermStatement(Node root, String term, String statement) {
        if (root == null) {
            return null;
        }
        int compareTerm = term.compareTo(root.generic.getTerm());
        int compareStatement = statement.compareTo(root.generic.getStatement());

        if (compareTerm < 0 || (compareTerm == 0 && compareStatement < 0)) {
            return searchByTermStatement(root.left, term, statement);
        } else if (compareTerm > 0 || (compareTerm == 0 && compareStatement > 0)) {
            return searchByTermStatement(root.right, term, statement);
        }
        else {
            return root.generic;
        }
    }
    
/**
* Searches for a Generic object in the binary search tree 
* @param term the term of the Generic object you want to search for
* @param statement the statement of the Generic object you want to search for
* @return the generic with the given term and statement
*/
    public Generic searchingByTermStatement(String term, String statement){
        return searchByTermStatement(root, term, statement);
    }
    
/**
*This is the main method of the GenericsKbBSTApp class, that provides a user interface that then uses the methods of GenericsKbBSTApp class.
*The main method constists of options for loading information into the knowledge base, adding to/updating and searching the knowledge base,
all the options done using binary search tree, and exiting the program.
*@param args an array of command-line arguments
*@throws FileNotFoundException if the given file cannot be found
*/
    public static void main(String[] args) throws FileNotFoundException{

        GenericsKbBSTApp bstApp = new GenericsKbBSTApp();

        try(Scanner kb = new Scanner(System.in)) {
            boolean quit = false;
            while(!quit) {
                System.out.println("Choose an action from the menu:");
                System.out.println("1. Load a knowledge base from a file");
                System.out.println("2. Add/Update a new statement to the knowledge base");
                System.out.println("3. Search for an item in the knowledge base by term");
                System.out.println("4. Search for a item in the knowledge base by term and sentence");
                System.out.println("5. Quit");
                System.out.println("6. Help");
                System.out.print("Enter your choice: ");

                int option = kb.nextInt();
                kb.nextLine();

                switch(option) {
                    case 1:
                        System.out.print("Enter the filename: ");
                        String filename = kb.nextLine();
                        try {
                            File fileReader = new File(filename);
                            Scanner scanner = new Scanner(fileReader);

                            while (scanner.hasNextLine()) {
                                String[] newLine = scanner.nextLine().split("\\t");
                                String term = newLine[0];
                                String statement = newLine[1];
                                double cfScore = Double.parseDouble(newLine[2]);
                                Generic generic = new Generic(term, statement, cfScore);
                                bstApp.insertion(generic);
                            }
                            System.out.println("Done.");
                            System.out.println("Knowledge base loaded successfully.");
                            scanner.close();
                        } catch (Exception e) {
                            System.out.println("File not found!");
                        }
                        break;
                    case 2:
                        System.out.print("Enter the term: ");
                        String termAdd = kb.nextLine();
                        System.out.print("Enter the statement: ");
                        String statementAdd = kb.nextLine();
                        System.out.print("Enter the confidence score: ");
                        double cfScoreAdd = kb.nextDouble();
                        Generic generic1 = new Generic(termAdd, statementAdd, cfScoreAdd);
                        bstApp.insertion(generic1);
                        System.out.println("Done.");
                        break;
                    case 3:
                        System.out.print("Enter the term to search: ");
                        String termSearch = kb.nextLine();
                        Generic result = bstApp.searchingByTerm(termSearch);
                        if (result != null) {
                            System.out.println("Term found: " + result);
                        }
                        else {
                            System.out.println("Term not found in the knowledge base.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter the term: ");
                        String term2 = kb.nextLine().toLowerCase();
                        System.out.print("Enter the statement to search for: ");
                        String statement2 = kb.nextLine();
                        Generic result2 = bstApp.searchingByTermStatement(term2, statement2);
                        if(result2 != null) {
                            System.out.println("Term & Statement found: " + result2);
                        }
                        else {
                            System.out.println("Term & Statement not found in the knowledge base.");
                        }
                        break;
                    case 5:
                        quit = true;
                        break;
                    
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
