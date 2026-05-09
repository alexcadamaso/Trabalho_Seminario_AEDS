package dheapmax;

public class DHeapMax {
    public static long contadorTrocas = 0;
    private DHeapMax(){}

    // função para realizar troca no heap
    public static void swap(int[] vetor, int a, int b){
        if(a < 0 || a >= vetor.length || b < 0 || b >= vetor.length){
            throw new IllegalArgumentException("Indices inválidos para realizar swap");
        }

        int aux = vetor[a];
        vetor[a] = vetor[b];
        vetor[b] = aux;
        contadorTrocas++;
    }

    public static int paiD(int indiceFilho, int d){
        int indicePai = (indiceFilho - 1) / d;
        return indicePai;
    }

    public static int filhoD(int indicePai, int d, int k){
        int indiceFilho = (indicePai * d) + k;
        return indiceFilho;
    }

    public static void DheapifyMax(int[] vetorOrdenar, int n, int d, int indicePai){
        int indiceMelhor = indicePai;

        for(int i=0;i<d;i++){
            int filho = DHeapMax.filhoD(indicePai, d, i + 1);
            if(filho < n && vetorOrdenar[filho] > vetorOrdenar[indiceMelhor]){
                indiceMelhor = filho;
            }
        }

        if(indiceMelhor != indicePai){
            DHeapMax.swap(vetorOrdenar, indicePai, indiceMelhor);
            DHeapMax.DheapifyMax(vetorOrdenar, n, d, indiceMelhor);
        }
    }

    public static void buildDHeapMax(int[] vetorOrdenar, int n, int d){
        int indiceUltimoNo = n - 1;
        int indiceUltimoPai = paiD(indiceUltimoNo, d);
        for(int i=indiceUltimoPai;i>=0;i--){
            DHeapMax.DheapifyMax(vetorOrdenar, n, d, i);
        }
    }

    public static void DHeapSortMax(int[] vetorOrdenar, int n, int d){
        DHeapMax.buildDHeapMax(vetorOrdenar, n, d);

        for(int i=n-1;i>0;i--){
            DHeapMax.swap(vetorOrdenar, 0, i);
            DHeapMax.DheapifyMax(vetorOrdenar, i, d, 0);
        }
    }
}