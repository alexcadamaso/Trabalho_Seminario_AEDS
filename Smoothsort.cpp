#include "Smoothsort.hpp"
#include <algorithm>

using namespace std;

void Smoothsort::organizar(vector<int>& valores, int raiz,int atual, int anterior){

    while(atual > 1){

        int direita = raiz - 1;
        int esquerda = raiz - 1 - anterior;

        if(valores[raiz] >= valores[esquerda] &&
           valores[raiz] >= valores[direita]){
            break;
        }

        if(valores[esquerda] >= valores[direita]){

            swap(valores[raiz], valores[esquerda]);

            raiz = esquerda;

            int aux = atual - anterior - 1;

            atual = anterior;
            anterior = aux;

        }else{

            swap(valores[raiz], valores[direita]);

            raiz = direita;

            atual = atual - 1;
            anterior = anterior - 1;
        }
    }
}

void Smoothsort::reorganizar(vector<int>& valores, int raiz,int mascara, int atual, int anterior){

    while(mascara > 1){

        int esquerda = raiz - atual;

        if(valores[esquerda] <= valores[raiz]){
            break;
        }

        if(atual > 1){

            int direita = raiz - 1;
            int filhoEsq = raiz - 1 - anterior;

            if(valores[esquerda] <= valores[filhoEsq] ||
               valores[esquerda] <= valores[direita]){
                break;
            }
        }

        swap(valores[raiz], valores[esquerda]);

        raiz = esquerda;

        while(!(mascara & 1)){

            mascara >>= 1;

            int aux = atual + anterior + 1;

            anterior = atual;
            atual = aux;
        }

        mascara >>= 1;
    }

    organizar(valores, raiz, atual, anterior);
}

