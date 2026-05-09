#include "GerenciadorArquivos.hpp"
#include <iostream>
using namespace std;

int main() {
    vector<string> tipos = {"ordenado", "invertido", "aleatorio"};
    vector<string> algoritmos = {"D-Heap-Max", "D-Heap-Min", "SmoothSort"};
    vector<int> valoresD = {2, 3, 4, 8};
    int repeticoes = 5;
    string pastaData = "data/";

    try {
        GerenciadorArquivos gerenciador(repeticoes, "results/output.dat", "results/statistics.dat");
        gerenciador.rodar(tipos, algoritmos, valoresD, pastaData);
        cout << "\n>>> Todos os testes finalizados com sucesso! <<<\n";
    } catch (const exception& e) {
        cerr << "Erro critico: " << e.what() << "\n";
        return 1;
    }

    return 0;
}