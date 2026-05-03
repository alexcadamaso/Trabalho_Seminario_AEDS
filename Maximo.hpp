#ifndef MAXIMO_HPP
#define MAXIMO_HPP
#include <vector>

class Maximo {
  public:

  void Max_Heapify(std::vector<int> &valores, int tamanho, int i);
  void Build_Max_Heap(std::vector<int> &valores, int tamanho);
  void Heapsort(std::vector<int> &valores, int tamanho);


};



#endif