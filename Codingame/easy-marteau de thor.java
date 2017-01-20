
/**
thorX ← initialTX
thorY ← initialTY
tant que vrai faire
	lire remainingTurns
	directionX ← ""
	directionY ← ""
	si thorY est plus grand que lightY alors
		directionY ← "N"
		thorY ← thorY - 1
	sinon si thorY est plus petit que lightY alors
		directionY ← "S"
		thorY ← thorY + 1
	fin si
	si thorX est plus grand que lightX alors
		directionX ← "W"
		thorX ← thorX - 1
	sinon si thorX est plus petit que lightX alors
		directionX ← "E"
		thorX ← thorX + 1
	fin si
	afficher la concatenation de directionY et de directionX
fin tant que

-------------
**/
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTX = in.nextInt(); // Thor's starting X position
        int initialTY = in.nextInt(); // Thor's starting Y position

        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.

            //  the move To Be Made 
            String moveToBeMade= "";         
             
            // Allez en Haut ou en bas
            if (initialTY < lightY) {
                moveToBeMade += "S";
                initialTY++;
            } else if (initialTY > lightY) {
                moveToBeMade += "N";
                initialTY--;
            }
            
            // Allez à droite ou à gauche
           if (initialTX < lightX) {
                    moveToBeMade += "E";
                    initialTX++;
                } else if (initialTX > lightX) {
                    moveToBeMade += "W";
                    initialTX--;
            }
 
            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(moveToBeMade);
        }
    }
}