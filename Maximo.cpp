#include "Maximo.hpp"
#include <iostream>
#include <algorithm>

using namespace std;

void Maximo::Max_Heapify(vector<int> &valores, int tamanho,int i){

    int esquerda = 2 * i + 1;
    int direita = 2 * i + 2;
    int maior = i;

    if(esquerda < tamanho && valores[esquerda] > valores[maior]){
        maior = esquerda;
    } else {
        maior = i;
    }
    if(direita < tamanho && valores[direita] > valores[maior]){
        maior = direita;
    }
    if(maior != i){
        swap(valores[maior],valores[i]);
        Max_Heapify(valores,tamanho,maior);
    }

}
 void Maximo::Build_Max_Heap(vector<int> &valores, int tamanho){
    for(int i = tamanho/2 - 1; i >= 0; i--){
        Max_Heapify(valores,tamanho,i);
    }
 }
 void Maximo::Heapsort(vector<int> &valores,int tamanho){

    Build_Max_Heap(valores,tamanho);

    while(tamanho > 1){
    
       int ultimo = tamanho - 1;
      
       swap(valores[0],valores[ultimo]); 
       tamanho--;
       Max_Heapify(valores,tamanho,0);
      
    }
 
 }


