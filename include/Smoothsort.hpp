#ifndef SMOOTHSORT_HPP
#define SMOOTHSORT_HPP
#include <vector>

class Smoothsort {

private:
    void corrigirHeap(std::vector<int>& valores, int raiz, int ordem);
    void inserirNaFloresta(std::vector<int>& valores, int raiz, int mascara, int ordem);

public:
    void sort(std::vector<int>& valores);

};

#endif