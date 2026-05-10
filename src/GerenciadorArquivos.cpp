#include "GerenciadorArquivos.hpp"
#include "D_Heap.hpp"
#include "Smoothsort.hpp"
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
        throw runtime_error("Erro ao abrir arquivos.");
    }

    outLog << "Indice;Algoritmo;D;Tamanho;Tipo;Tempo_ms;Memoria_B\n";
    outResumo << "Algoritmo;D;Tamanho;Tipo;TempoMedio_ms;MemoriaMedia_B\n";
}

GerenciadorArquivos::~GerenciadorArquivos() {
    if (outLog.is_open()) outLog.close();
    if (outResumo.is_open()) outResumo.close();
}

void GerenciadorArquivos::rodar(vector<int> tamanhos, vector<string> tipos, vector<string> algoritmos, vector<int> valoresD, string pastaData) {
    for (int n : tamanhos) {
        for (const string& tipo : tipos) {
            string caminho = pastaData + tipo + ".dat";
            vector<int> original = lerArquivo(caminho, n);

            if (original.empty()) continue;

            for (const string& algo : algoritmos) {
                bool ehDHeap = (algo == "D-Heap-Max" || algo == "D-Heap-Min");
                vector<int> dParaTestar = ehDHeap ? valoresD : vector<int>{1};

                for (int d : dParaTestar) {
                    rodarBloco(original, tipo, algo, d);
                }
            }
        }
    }
}

void GerenciadorArquivos::rodarBloco(vector<int>& original, string tipo, string algo, int d) {
    bool isMax = (algo != "D-Heap-Min");
    double somaTempos = 0;
    long somaMemoria = 0;
    int n = original.size();

    for (int i = 0; i < repeticoes; i++) {
        vector<int> copia = original;
        double tempo = 0;
        long memoria = 0;

        executarUm(algo, copia, isMax, d, tempo, memoria);
        gravarLinha(i + 1, algo, d, n, tipo, tempo, memoria);

        somaTempos += tempo;
        somaMemoria += memoria;
    }

    gravarResumo(algo, d, n, tipo, somaTempos / repeticoes, somaMemoria / repeticoes);
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
        memoria = (long)(memoriaVetor + (profundidade * 32));
    } else {
        memoria = (long)memoriaVetor;
    }
}

vector<int> GerenciadorArquivos::lerArquivo(string caminho, int n) {
    vector<int> dados;
    dados.reserve(n);
    ifstream arq(caminho);

    if (!arq.is_open()) {
        cerr << "Erro: " << caminho << endl;
        return dados;
    }

    int num;
    while (dados.size() < (size_t)n && arq >> num) {
        dados.push_back(num);
    }
    return dados;
}

void GerenciadorArquivos::gravarLinha(int indice, string algo, int d, int tamanho, string tipo, double tempo, long memoria) {
    string valorD = (algo == "SmoothSort") ? "-" : to_string(d);
    outLog << indice << ";" << algo << ";" << valorD << ";" << tamanho << ";" << tipo << ";" << tempo << ";" << memoria << "\n";
}

void GerenciadorArquivos::gravarResumo(string algo, int d, int tamanho, string tipo, double tempoMedio, long memoriaMedia) {
    string valorD = (algo == "SmoothSort") ? "-" : to_string(d);
    outResumo << algo << ";" << valorD << ";" << tamanho << ";" << tipo << ";" << tempoMedio << ";" << memoriaMedia << "\n";
}