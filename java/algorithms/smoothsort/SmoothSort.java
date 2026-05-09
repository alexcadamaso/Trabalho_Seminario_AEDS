package smoothsort;
import dheapmax.DHeapMax;

public class SmoothSort {

    static final int[] NLEO = {
        1, 1, 3, 5, 9, 15, 25, 41, 67, 109,
        177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891,
        35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457,
        1664079, 2692537, 4356617, 7049155, 11405773, 18454929, 29860703,
        48315633, 78176337, 126491971, 204668309, 331160281, 535828591,
        866988873
    };

    public static void sift(int[] vetorDados, int ordemAtual, int indiceAtual) {
        int valorRaiz = vetorDados[indiceAtual];
        int indiceInicial = indiceAtual;

        while (ordemAtual > 1) {
            int indiceFilhoDireita  = indiceAtual - 1;
            int indiceFilhoEsquerda = indiceAtual - 1 - NLEO[ordemAtual - 2];

            if (valorRaiz >= vetorDados[indiceFilhoEsquerda] && valorRaiz >= vetorDados[indiceFilhoDireita]) {
                break;
            }

            if (vetorDados[indiceFilhoEsquerda] >= vetorDados[indiceFilhoDireita]) {
                dheapmax.DHeapMax.contadorTrocas++;
                vetorDados[indiceAtual] = vetorDados[indiceFilhoEsquerda];
                indiceAtual = indiceFilhoEsquerda;
                ordemAtual -= 1;
            } else {
                dheapmax.DHeapMax.contadorTrocas++;
                vetorDados[indiceAtual] = vetorDados[indiceFilhoDireita];
                indiceAtual = indiceFilhoDireita;
                ordemAtual -= 2;
            }
        }

        if (indiceAtual != indiceInicial) {
            DHeapMax.contadorTrocas++;
        }

        vetorDados[indiceAtual] = valorRaiz;
    }

    public static void trinkle(int[] vetorDados, int bitmap, int ordemAtual, int indiceAtual, boolean raizConfiavel) {
        int valorRaiz = vetorDados[indiceAtual];
        int indiceInicial = indiceAtual;

        while (bitmap != 1) {
            int indiceVizinhoEsquerda = indiceAtual - NLEO[ordemAtual];

            if (vetorDados[indiceVizinhoEsquerda] <= valorRaiz) {
                break;
            }

            if (!raizConfiavel && ordemAtual > 1) {
                int indiceFilhoDireita  = indiceAtual - 1;
                int indiceFilhoEsquerda = indiceAtual - 1 - NLEO[ordemAtual - 2];
                if (vetorDados[indiceFilhoDireita]  >= vetorDados[indiceVizinhoEsquerda] || vetorDados[indiceFilhoEsquerda] >= vetorDados[indiceVizinhoEsquerda]) {
                    break;
                }
            }

            vetorDados[indiceAtual] = vetorDados[indiceVizinhoEsquerda];
            dheapmax.DHeapMax.contadorTrocas++;
            indiceAtual = indiceVizinhoEsquerda;

            int zeros = Integer.numberOfTrailingZeros(bitmap & ~1);
            bitmap >>>= zeros;
            ordemAtual += zeros;
            raizConfiavel = false;
        }

        if (!raizConfiavel) {
            if (indiceAtual != indiceInicial) {
                DHeapMax.contadorTrocas++;
            }
            vetorDados[indiceAtual] = valorRaiz;
            sift(vetorDados, ordemAtual, indiceAtual);
        }
    }

    public static void smoothSort(int[] vetorDados) {
        if(vetorDados == null){
            throw new IllegalArgumentException("Erro: o vetor para ordenação não pode ser nulo");
        }

        int n = vetorDados.length;
        if (n <= 1){
            return;
        }

        int indiceAtual = 0;
        int bitmap = 1;
        int ordemAtual = 1;

        while (indiceAtual < n - 1) {
            if ((bitmap & 3) == 3) {
                sift(vetorDados, ordemAtual, indiceAtual);
                bitmap >>>= 2;
                ordemAtual += 2;
            } else {
                if (NLEO[ordemAtual - 1] >= (n - 1) - indiceAtual) {
                    trinkle(vetorDados, bitmap, ordemAtual, indiceAtual, false);
                } else {
                    sift(vetorDados, ordemAtual, indiceAtual);
                }

                if (ordemAtual == 1) {
                    bitmap <<= 1;
                    ordemAtual--;
                } else {
                    bitmap <<= (ordemAtual - 1);
                    ordemAtual = 1;
                }
            }
            bitmap |= 1;
            indiceAtual++;
        }

        trinkle(vetorDados, bitmap, ordemAtual, indiceAtual, false);

        while (ordemAtual != 1 || bitmap != 1) {
            if (ordemAtual <= 1) {
                int zeros = Integer.numberOfTrailingZeros(bitmap & ~1);
                bitmap >>>= zeros;
                ordemAtual += zeros;
            } else {
                bitmap <<= 2;
                bitmap ^= 7;
                ordemAtual -= 2;

                int indiceRaizEsquerda = indiceAtual - NLEO[ordemAtual] - 1;
                int indiceRaizDireita  = indiceAtual - 1;

                trinkle(vetorDados, bitmap >>> 1, ordemAtual + 1, indiceRaizEsquerda, true);
                trinkle(vetorDados, bitmap, ordemAtual, indiceRaizDireita, true);
            }
            indiceAtual--;
        }
    }

}

