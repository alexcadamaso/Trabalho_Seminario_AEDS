#ifndef HEAP_HPP
#define HEAP_HPP
#include <vector>

class Max_Min {
  private:
  bool isMaxHeap;

  public:

  Max_Min(bool isMaxHeap);
  void Max_Heapify(std::vector<int> &valores, int tamanho, int i);
  void Build_Max_Heap(std::vector<int> &valores, int tamanho);
  void Heapsort(std::vector<int> &valores, int tamanho);


};



#endif