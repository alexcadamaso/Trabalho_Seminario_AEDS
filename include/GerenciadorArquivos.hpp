#pragma once

#include "D_Heap.hpp"
#include "Smoothsort.hpp"
#include <string>
#include <vector>
#include <fstream>

using namespace std;

class GerenciadorArquivos {

    public:

    GerenciadorArquivos(int repeticoes,string caminhoLog,string caminhoResumo);
    ~GerenciadorArquivos();
    void rodar(vector<string> tipos,vector<string> algoritmos,vector<int> valoresD,string pastaData);

    private:

    int repeticoes;

    ofstream outLog;
    ofstream outResumo;

    void executarUm(string algo,vector<int>& vetor,bool isMax,int d,double& tempo,long& memoria);
    void rodarBloco(vector<int>& original,string tipo,string algo,int d);
    vector<int> lerArquivo(string caminho);
    void gravarLinha(int indice,string algo,int d,string tipo,double tempo,long memoria);
    void gravarResumo(string algo,int d,string tipo,double tempoMedio, long memoriaMedia);


};