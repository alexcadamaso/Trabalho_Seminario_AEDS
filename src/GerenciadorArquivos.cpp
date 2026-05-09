#include "GerenciadorArquivos.hpp"
#include <iostream>
#include <chrono>
#include <algorithm>
#include <cmath>

using namespace std;
using namespace chrono;

GerenciadorArquivos::GerenciadorArquivos(int repeticoes, string caminhoLog, string caminhoResumo) {
    this->repeticoes = repeticoes;
    outLog.open(caminhoLog);
    outResumo.open(caminhoResumo);

    if (!outLog.is_open() || !outResumo.is_open()) {
        throw runtime_error("Erro ao criar arquivos. Verifique a pasta results/");
    }

    outLog    << "# output: dados brutos de cada execucao, cada algoritmo e rodado " << repeticoes << " vezes\n";
    outLog    << "Indice;Algoritmo;D;Tipo;Tempo_ms;Memoria_B\n";

    outResumo << "# statistics: media dos " << repeticoes << " resultados do output agrupados por algoritmo e tipo\n";
    outResumo << "Algoritmo;D;Tipo;TempoMedio_ms;MemoriaMedia_B\n";
}

GerenciadorArquivos::~GerenciadorArquivos() {
    outLog.close();
    outResumo.close();
}

void GerenciadorArquivos::rodar(vector<string> tipos, vector<string> algoritmos, vector<int> valoresD, string pastaData) {
    for (int i = 0; i < (int)tipos.size(); i++) {
        string caminho = pastaData + tipos[i] + ".dat";
        vector<int> original = lerArquivo(caminho);

        for (int k = 0; k < (int)algoritmos.size(); k++) {
            bool ehDHeap = (algoritmos[k] == "D-Heap-Max" || algoritmos[k] == "D-Heap-Min");
            vector<int> dParaTestar = ehDHeap ? valoresD : vector<int>{1};

            for (int l = 0; l < (int)dParaTestar.size(); l++) {
                rodarBloco(original, tipos[i], algoritmos[k], dParaTestar[l]);
            }
        }
    }
}

void GerenciadorArquivos::rodarBloco(vector<int>& original, string tipo, string algo, int d) {
    bool isMax = (algo != "D-Heap-Min");
    double somaTempos = 0;
    long somaMemoria = 0;

    for (int i = 0; i < repeticoes; i++) {
        vector<int> copia = original;
        double tempo = 0;
        long memoria = 0;

        executarUm(algo, copia, isMax, d, tempo, memoria);
        gravarLinha(i + 1, algo, d, tipo, tempo, memoria);

        somaTempos += tempo;
        somaMemoria += memoria;
    }

    gravarResumo(algo, d, tipo, somaTempos / repeticoes, somaMemoria / repeticoes);
}

void GerenciadorArquivos::executarUm(string algo, vector<int>& vetor, bool isMax, int d, double& tempo, long& memoria) {
    auto ini = high_resolution_clock::now();

    if (algo == "D-Heap-Max" || algo == "D-Heap-Min") {
        Heap_d heap(isMax, d);
        heap.Heapsort2(vetor, vetor.size());
    } else if (algo == "SmoothSort") {
        Smoothsort smooth;
        smooth.sort(vetor);
    }

    auto fim = high_resolution_clock::now();
    tempo = duration<double, milli>(fim - ini).count();

    size_t memoriaVetor = vetor.size() * sizeof(int);

    if (algo == "D-Heap-Max" || algo == "D-Heap-Min") {
        size_t profundidade = (size_t)(log(vetor.size()) / log(d)) + 1;
        size_t stack = profundidade * 32;
        memoria = (long)(memoriaVetor + stack);
    } else {
        memoria = (long)(memoriaVetor);
    }
}

vector<int> GerenciadorArquivos::lerArquivo(string caminho) {
    vector<int> dados;
    ifstream arq(caminho);

    if (!arq.is_open()) {
        cerr << "Erro ao abrir: " << caminho << "\n";
        return dados;
    }

    int num;
    while (arq >> num) {
        dados.push_back(num);
    }

    return dados;
}

void GerenciadorArquivos::gravarLinha(int indice, string algo, int d, string tipo, double tempo, long memoria) {
    string valorD = (algo == "SmoothSort") ? "-" : to_string(d);
    outLog << indice << ";" << algo << ";" << valorD << ";" << tipo << ";" << tempo << ";" << memoria << "\n";
}

void GerenciadorArquivos::gravarResumo(string algo, int d, string tipo, double tempoMedio, long memoriaMedia) {
    string valorD = (algo == "SmoothSort") ? "-" : to_string(d);
    outResumo << algo << ";" << valorD << ";" << tipo << ";" << tempoMedio << ";" << memoriaMedia << "\n";
}