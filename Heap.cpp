#include "Heap.hpp"
#include <iostream>
#include <algorithm>

using namespace std;

Max_Min::Max_Min(bool isMaxHeap){
    this->isMaxHeap = isMaxHeap;
}

void Max_Min::Max_Heapify(vector<int> &valores, int tamanho,int i){

    int esquerda = 2 * i + 1;
    int direita = 2 * i + 2;
    int melhor = i;

    if(isMaxHeap){
       if(esquerda < tamanho && valores[esquerda] > valores[melhor]){
         melhor = esquerda;
      }   else  {
         melhor = i;
      }
       if(direita < tamanho && valores[direita] > valores[melhor]){
         melhor = direita;
      }
   } else{
        if(esquerda < tamanho && valores[esquerda] < valores[melhor]){
         melhor = esquerda;
      }  else  {
         melhor = i;
      }
       if(direita < tamanho && valores[direita] < valores[melhor]){
         melhor = direita;
      }
   }

    if(melhor != i){
        swap(valores[melhor],valores[i]);
        Max_Heapify(valores,tamanho,melhor);
    }

}
 void Max_Min::Build_Max_Heap(vector<int> &valores, int tamanho){
    for(int i = tamanho/2 - 1; i >= 0; i--){
        Max_Heapify(valores,tamanho,i);
    }
 }
 void Max_Min::Heapsort(vector<int> &valores,int tamanho){

    Build_Max_Heap(valores,tamanho);

    while(tamanho > 1){
    
       int ultimo = tamanho - 1;
      
       swap(valores[0],valores[ultimo]); 
       tamanho--;
       Max_Heapify(valores,tamanho,0);
      
    }
 
 }


