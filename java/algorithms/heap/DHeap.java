package dheap;
import heap.Heap;

public class DHeap {

    private DHeap(){}

    public static int paiD(int indiceFilho, int d){
        int indicePai = (indiceFilho - 1) / d;
        return indicePai;
    }

    public static int filhoD(int indicePai, int d, int k){
        int indiceFilho = (indicePai * d) + k;
        return indiceFilho;
    }

    public static void Dheapify(int[] vetorOrdenar, int n, int d, int indicePai){
        int indiceMelhor = indicePai;

        for(int i=0;i<d;i++){
            int filho = DHeap.filhoD(indicePai, d, i + 1);
            if(filho < n && vetorOrdenar[filho] > vetorOrdenar[indiceMelhor]){
                indiceMelhor = filho;
            }
        }

        if(indiceMelhor != indicePai){
            Heap.swap(vetorOrdenar, indicePai, indiceMelhor);
            DHeap.Dheapify(vetorOrdenar, n, d, indiceMelhor);
        }
    }

    public static void buildDHeap(int[] vetorOrdenar, int n, int d){
        int indiceUltimoNo = n - 1;
        int indiceUltimoPai = paiD(indiceUltimoNo, d);
        for(int i=indiceUltimoPai;i>=0;i--){
            DHeap.Dheapify(vetorOrdenar, n, d, i);
        }
    }

    public static void DHeapSort(int[] vetorOrdenar, int n, int d){
        DHeap.buildDHeap(vetorOrdenar, n, d);

        for(int i=n-1;i>0;i--){
            Heap.swap(vetorOrdenar, 0, i);
            DHeap.Dheapify(vetorOrdenar, i, d, 0);
        }
    }
}