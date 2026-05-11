#ifndef GERENCIADOR_ARQUIVOS_HPP
#define GERENCIADOR_ARQUIVOS_HPP

#include <vector>
#include <string>
#include <fstream>

class GerenciadorArquivos {
public:
    GerenciadorArquivos(int repeticoes, std::string caminhoLog, std::string caminhoResumo);
    ~GerenciadorArquivos();
    void rodar(std::vector<int> tamanhos, std::vector<std::string> tipos, std::vector<std::string> algoritmos, std::vector<int> valoresD, std::string pastaData);

private:
    int repeticoes;
    std::ofstream outLog;
    std::ofstream outResumo;

    void executarUm(std::string algo, std::vector<int>& vetor, bool isMax, int d, double& tempo, long& memoria);
    void rodarBloco(std::vector<int>& original, std::string tipo, std::string algo, int d);
    std::vector<int> lerArquivo(std::string caminho, int n);
    void gravarLinha(int indice, std::string algo, int d, int tamanho, std::string tipo, double tempo, long memoria);
    void gravarResumo(std::string algo, int d, int tamanho, std::string tipo, double tempoMedio, long memoriaMedia);
};

#endif