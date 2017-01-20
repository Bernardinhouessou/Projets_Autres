#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    int lightX; // the X position of the light of power
    int lightY; // the Y position of the light of power
    int initialTX; // Thor's starting X position
    int initialTY; // Thor's starting Y position
    cin >> lightX >> lightY >> initialTX >> initialTY; cin.ignore();

    // game loop
    while (1) {
        int remainingTurns; // The level of Thor's remaining energy, representing the number of moves he can still make.
        cin >> remainingTurns; cin.ignore();
        
        //  the move To Be Made 
        string moveToBeMade= ""; 		
		//
         if (initialTY > lightY)
        {    
            moveToBeMade += "N";
            initialTY --;
        }
        else if (initialTY < lightY)
        { 
            moveToBeMade += "S";
            initialTY ++;
        }        
        //
        if (initialTX > lightX)
        {            
            moveToBeMade += "W";
            initialTX --;
        }
        else if (initialTX < lightX)
        { 
            moveToBeMade += "E";
            initialTX ++;
        }        
        //
        cout << moveToBeMade << endl;
        
    }
}
