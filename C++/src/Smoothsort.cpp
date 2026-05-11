#include "Smoothsort.hpp"
#include <algorithm>

using namespace std;

static const int LEO[] = {
    1,1,3,5,9,15,25,41,67,109,177,287,465,753,1219,1973,
    3193,5167,8361,13529,21891,35421,57313,92735,150049,
    242785,392835,635621,1028457,1664079,2692537,4356617,
    7049155,11405773,18454929,29860703,48315633,78176337,
    126491971,204668309,331160281,535828591,866988873
};

void Smoothsort::corrigirHeap(vector<int>& valores, int raiz, int ordem) {
    while (ordem >= 2) {
        int filhoDir = raiz - 1;
        int filhoEsq = raiz - 1 - LEO[ordem - 2];
        int maior = raiz;

        if (valores[filhoEsq] > valores[maior]) maior = filhoEsq;
        if (valores[filhoDir] > valores[maior]) maior = filhoDir;
        if (maior == raiz) break;

        swap(valores[raiz], valores[maior]);

        if (maior == filhoEsq) { raiz = filhoEsq; ordem--; }
        else                   { raiz = filhoDir; ordem -= 2; }
    }
}

void Smoothsort::inserirNaFloresta(vector<int>& valores, int raiz, int mascara, int ordem) {
    while (mascara > 1) {
        int vizinho = raiz - LEO[ordem];

        if (valores[vizinho] <= valores[raiz]) break;

        if (ordem >= 2) {
            int filhoDir = raiz - 1;
            int filhoEsq = raiz - 1 - LEO[ordem - 2];
            if (valores[vizinho] <= valores[filhoDir] || valores[vizinho] <= valores[filhoEsq]) break;
        }

        swap(valores[raiz], valores[vizinho]);
        raiz = vizinho;

        mascara >>= 1;
        while (!(mascara & 1)) { mascara >>= 1; ordem++; }
        ordem++;
    }

    corrigirHeap(valores, raiz, ordem);
}

void Smoothsort::sort(vector<int>& valores) {
    int tamanho = valores.size();
    if (tamanho < 2) return;

    int mascara = 1;
    int ordem = 1;

    for (int i = 0; i < tamanho; i++) {
        if ((mascara & 3) == 3) {
            mascara = (mascara >> 2) | 1;
            ordem += 2;
        } else if (ordem == 1) {
            mascara = (mascara << 1) | 1;
            ordem = 0;
        } else {
            mascara = (mascara << (ordem - 1)) | 1;
            ordem = 1;
        }
        inserirNaFloresta(valores, i, mascara, ordem);
    }

    for (int i = tamanho - 1; i > 0; i--) {
        if (ordem <= 1) {
            mascara &= ~1;
            while (mascara && !(mascara & 1)) { mascara >>= 1; ordem++; }
            if (mascara) mascara &= ~1;
        } else {
            int filhoDir = i - 1;
            int filhoEsq = i - 1 - LEO[ordem - 2];
            mascara &= ~1;

            int novaMascara = (mascara << 1) | 1;
            inserirNaFloresta(valores, filhoEsq, novaMascara, ordem - 1);

            novaMascara = (novaMascara << 1) | 1;
            inserirNaFloresta(valores, filhoDir, novaMascara, ordem - 2);

            ordem -= 2;
            mascara = (mascara << 2) | 3;
        }
    }
}
