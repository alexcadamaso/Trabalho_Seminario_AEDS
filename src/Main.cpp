#include "GerenciadorArquivos.hpp"
#include <iostream>
#include <vector>

using namespace std;

int main() {
    vector<int> tamanhos = {100, 1000, 10000, 100000, 1000000};
    vector<string> tipos = {"ordenado", "invertido", "aleatorio"};
    vector<string> algoritmos = {"D-Heap-Max", "D-Heap-Min", "SmoothSort"};
    vector<int> valoresD = {2, 3, 4, 8};
    int repeticoes = 20;
    string pastaData = "data/";

    try {
        GerenciadorArquivos gerenciador(repeticoes, "results/output.dat", "results/statistics.dat");
        gerenciador.rodar(tamanhos, tipos, algoritmos, valoresD, pastaData);
    } catch (const exception& e) {
        cerr << "Erro critico: " << e.what() << endl;
        return 1;
    }

    return 0;
}