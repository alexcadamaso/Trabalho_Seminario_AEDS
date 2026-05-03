#include "Maximo.hpp"
#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>

using namespace std;

int main(){

Maximo Algorithm;
vector<int> valores;
int tam;

srand(42);

for(int i = 0; i <= 1000000; i++){
    int num = rand() % 101;
    valores.push_back(num);
}

tam = valores.size();

clock_t inicio = clock();

Algorithm.Heapsort(valores,tam);
clock_t fim1 = clock() - inicio;

cout << "Tempo gasto: " << (float)fim1/CLOCKS_PER_SEC << "\n";
/*cout << "Vetor ordenado: ";
for(int i = 0; i < tam ; i++){
    cout << valores[i] << " ";
}

*/
    return 0;
}
