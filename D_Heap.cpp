#include "D_Heap.hpp"
#include <algorithm>

using namespace std;

Heap_d::Heap_d(bool isMax_Heap, int d){
    this->isMax_Heap = isMax_Heap;
    this->d = d;
}

void Heap_d::Heapify(vector<int> &valores3,int tamanho,int i){
    int filho, melhor = i;

    for(int k = 1; k <= d; k++){

      filho = (d * i) + k;

      if(filho < tamanho && valores3[filho] > valores3[melhor]){
         melhor = filho;
      } 
      
    }

    if(melhor != i){
         swap(valores3[melhor],valores3[i]);
         Heapify(valores3,tamanho,melhor);
    }

    
}

void Heap_d::Build_Heap(vector<int> &valores3, int tamanho){
    for(int i = (tamanho - 2)/d; i >= 0; i--){
        Heapify(valores3,tamanho,i);
    }

}

void Heap_d::Heapsort2(vector<int> & valorse3,int tamanho){
    Build_Heap(valores3,tamanho);

    while(tamanho > 1){
     int ultimo = tamanho - 1;
     swap(valores3[0],valores3[ultimo]);   
     tamanho--;
     Heapify(valores3,tamanho,0);
    }


}