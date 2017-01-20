#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <limits>
#include <sstream>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    int n; // the number of temperatures to analyse
    cin >> n; cin.ignore();
    string temps; // the n temperatures expressed as integers ranging from -273 to 5526
    getline(cin, temps);
    //
    istringstream issvar(temps);
    // consigne_entrée_ligne2
    int minTemps = numeric_limits<int>::max();
    for (int i = 0; i<n ;i++)
    {
        int nombre;
        issvar >> nombre;
        
        if ( abs(nombre) <= abs(minTemps) )
            minTemps = nombre;
    }
    
    // consigne_sortie
     if (n >0)    cout << minTemps <<endl;
     else         cout << 0 << endl;
}