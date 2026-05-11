#ifndef D_HEAP_HPP
#define D_HEAP_HPP
#include <vector>

class Heap_d {
  private:
  bool isMax_Heap;
  int d;

  public:
  Heap_d(bool isMax_Heap,int d);
  void Heapify(std::vector<int> &valores3,int tamanho,int i);
  void Build_Heap(std::vector<int> &valores3, int tamanho);
  void Heapsort2(std::vector<int> & valores3,int tamanho);

  
};


#endif