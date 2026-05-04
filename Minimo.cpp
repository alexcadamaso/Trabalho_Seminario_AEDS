#include "Minimo.hpp"
#include <iostream>
#include <algorithm>

using namespace std;

void Minimo::Min_Heapify(vector<int> &valores, int tamanho,int i){

    int esquerda = 2 * i + 1;
    int direita = 2 * i + 2;
    int menor = i;

    if(esquerda < tamanho && valores[esquerda] < valores[menor]){
        menor = esquerda;
    } else {
        menor = i;
    }
    if(direita < tamanho && valores[direita] < valores[menor]){
        menor = direita;
    }
    if(menor != i){
        swap(valores[menor],valores[i]);
        Min_Heapify(valores,tamanho,menor);
    }

}
 void Minimo::Build_Min_Heap(vector<int> &valores, int tamanho){
    for(int i = tamanho/2 - 1; i >= 0; i--){
        Min_Heapify(valores,tamanho,i);
    }
}
 void Minimo::Heapsort(vector<int> &valores,int tamanho){

    Build_Min_Heap(valores,tamanho);

    while(tamanho > 1){
    
       int ultimo = tamanho - 1;
      
       swap(valores[0],valores[ultimo]); 
       tamanho--;
       Min_Heapify(valores,tamanho,0);
      
    }
 
}


