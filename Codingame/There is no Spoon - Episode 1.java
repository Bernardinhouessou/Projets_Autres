import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Don't let the machines win. You are humanity's last hope...
**/

// creation de ma classe Node 
class Node{
   int x;
   int y;

   // constructor de la classe Node
   public Node(int x, int y){
       this.x = x;
       this.y = y;
   } 
}


class Player {
    static int width, height;
    static String[] rows;
    static List<Node> nodes;


    // 3 coordonnés (noeud, son voisin de droite, son voisin du bas)) 
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        init(in);
        parseNodes();
        printNodes();
    }
    
    //Initializes variables from input
    private static void init(Scanner in){
        width = in.nextInt(); // the number of cells on the X axis
        height = in.nextInt(); // the number of cells on the Y axis
        in.nextLine();
        rows = new String[height];
        nodes = new ArrayList<Node>();
        
        for (int i = 0; i < height; i++) {
            rows[i] = in.nextLine(); // width characters, each either 0 or .
        }
    }
    
    //parses nodes from the input
    private static void parseNodes(){
        for (int y = 0; y < height; y++){
            char[] cols = rows[y].toCharArray();
            for (int x = 0; x < width; x++){
                char c = cols[x];
                if (c=='0') createNode(x,y);
            }
        }
    }
    
    // create a node at x,y and adds it to list
    // does nothing if out of bounds, or isn't a node
    private static void createNode(int x, int y){
        System.err.println("Creating Node for "+x+","+y);
        if (x >= width || y >= height || !isNode(x,y)){
            System.err.println("Not a node");
            return;
        }
        int rx = x+1;
        int ry = y;
        
        int bx = x;
        int by = y+1;
        
        Node n = new Node(x,y);
        
        System.err.println("Finished creating Node for "+x+","+y);
        System.err.println();
        nodes.add(n);
    }
    
    
    //check if the x,y coordinates is a node, according to the input
    private static boolean isNode(int x, int y){
        char c = rows[y].toCharArray()[x];
        boolean out = (c=='0');
        System.err.println("isNode: "+out);
        return out;
    }
    
    private static void printNodes(){
        for (Node n : nodes){
            String out = n.x+" "+n.y+" ";
            out += rightNeighbor(n.x,n.y);
            out += bottomNeighbor(n.x,n.y);
            System.out.println(out);
        }
    }
    
    // return the coordinates of the closest neighbor to the right
    // if a null node is reached, return -1,-1
    private static String rightNeighbor(int x, int y){
        for(int i = x+1; i<width; i++){
            char c = rows[y].toCharArray()[i];
            if (c=='0') return i+" "+y+" ";
        }
        return -1+" "+-1+" ";
    }
    
    // return the coordinates of the closest neighbor below
    // if a null node is reached, return -1,-1
    // n can be the closest neighbor
    private static String bottomNeighbor(int x, int y){
        for(int i = y+1; i<height; i++){
            char c = rows[i].toCharArray()[x];
            if (c=='0') return x+" "+i+" ";
        }
        return -1+" "+-1+" ";
    }
}