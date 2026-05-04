#ifndef MINIMO_HPP
#define MINIMO_HPP
#include <vector>

class Minimo {
   public:

   void Min_Heapify(std::vector<int> valores, int tamanho, int i);
   void Build_Min_Heap(std::vector<int> valores, int tamanho);
   void Heapsort(std::vector<int> valores, int tamanho);


};


#endif