#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

// Utilisation d'un Arbre pour resoudre le PB mentionné.

// création de ma structure Arbre
struct Arbre
{
    Arbre* Avt_A = NULL;
    Arbre* Apres_A = NULL;
    long long valid = 0;
};

// encodage de chaînes de caractères
string encode(string input)
{
    string result;
	
    for (auto& cha : input)
    {
        switch (cha )
        {
            case 'A': result += ".-"; break;
            case 'B': result += "-..."; break;
            case 'C': result += "-.-."; break;
            case 'D': result += "-.."; break;
            case 'E': result += "."; break;
            case 'F': result += "..-."; break;
            case 'G': result += "--."; break;
            case 'H': result += "...."; break;
            case 'I': result += ".."; break;
            case 'J': result += ".---"; break;
            case 'K': result += "-.-"; break;
            case 'L': result += ".-.."; break;
            case 'M': result += "--"; break;
            case 'N': result += "-."; break;
            case 'O': result += "---"; break;
            case 'P': result += ".--."; break;
            case 'Q': result += "--.-"; break;
            case 'R': result += ".-."; break;
            case 'S': result += "..."; break;
            case 'T': result += "-"; break;
            case 'U': result += "..-"; break;
            case 'V': result += "...-"; break;
            case 'W': result += ".--"; break;
            case 'X': result += "-..-"; break;
            case 'Y': result += "-.--"; break;
            case 'Z': result += "--.."; break;
            default: throw runtime_error("ERROR");
        }
    }
    
    return result;
}


// Initialisation ....
string L;

Arbre* root = new Arbre();

map<long long, long long> cache;

long long solve(long long pos)
{
    Arbre* node = root;
    long long resultat = 0;
    while (pos < L.size()) {
        if ((L[pos] == '.') && (node->Avt_A != NULL))
            node = node->Avt_A;
        else if ((L[pos] == '-') && (node->Apres_A != NULL))
            node = node->Apres_A;
        else
            break;
        
        if (node->valid > 0) {
            if (pos == L.size()-1)
                resultat += node->valid;
            else {
                if (cache.find(pos+1) != cache.end()) {
                    resultat += node->valid *  cache[pos+1];
                }
                else {
                    long long res = solve(pos+1);
                    if (res > 0) {
                        resultat += node->valid * res;
                        cache[pos+1] = res;
                    }
                }
            }
        }
        
        ++pos;
    }

    return resultat;
}


///----DEBUT MES FONCTIONS
// ma fonction insert()
void insert(Arbre* node, string dictWord, long long pos = 0)
{
    while (pos < dictWord.size()) {
        if (dictWord[pos] == '.') {
            if (node->Avt_A == NULL)
                node->Avt_A = new Arbre();            
            node = node->Avt_A;
        }
        else {
            if (node->Apres_A == NULL)
                node->Apres_A = new Arbre();            
            node = node->Apres_A;
        }        
        pos += 1;
    }    
    node->valid += 1;
}

// ma fonction deleteTree()
void deleteTree(Arbre* node)
{
    if (node->Avt_A != NULL)
        deleteTree(node->Avt_A);

    if (node->Apres_A != NULL)
        deleteTree(node->Apres_A);
    
    delete node;
}

///--FIN-FONCTIONS

///--DEBUT-MAIN
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    cin >> L; cin.ignore();
    int N;
    cin >> N; cin.ignore();
    for (int i = 0; i < N; i++) {
        string W;
        cin >> W; cin.ignore();
        insert(root, encode(W));
    }
	// affiche output en appellant ma fonction resoudrePB
    cout << solve(0) << endl;
    
    // supprime l'élément à la racine de mon arbre
    deleteTree(root);
}
///--FIN-MAIN