#include "Heap.hpp"
#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>

using namespace std;

int main(){

Max_Min Algorithm(true);
Max_Min Algorithm2(false);
vector<int> valores;
vector<int> valores2;
int tam;

srand(42);

for(int i = 0; i <= 1000000; i++){
    int num = rand() % 101;
    valores.push_back(num);
    valores2.push_back(num);
}

tam = valores.size();

clock_t inicio = clock();
Algorithm.Heapsort(valores,tam);
clock_t fim1 = clock() - inicio;

clock_t inicio2 = clock();
Algorithm2.Heapsort(valores2,tam);
clock_t fim2 = clock() - inicio2;


cout << "Tempo gasto: " << (float)fim1/CLOCKS_PER_SEC << "\n";
cout << "Tempo gasto: " << (float)fim2/CLOCKS_PER_SEC << "\n";
/*cout << "Vetor ordenado: ";
for(int i = 0; i < tam ; i++){
    cout << valores[i] << " ";
}

*/
    return 0;
}
