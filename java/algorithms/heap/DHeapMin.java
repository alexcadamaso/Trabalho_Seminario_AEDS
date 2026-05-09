package dheapmin;
import dheapmax.DHeapMax;

public class DHeapMin {
    private DHeapMin(){}

    public static void DheapifyMin(int[] vetorOrdenar, int n, int d, int indicePai){
        int indiceMelhor = indicePai;

        for(int i=0;i<d;i++){
            int filho = DHeapMax.filhoD(indicePai, d, i + 1);
            if(filho < n && vetorOrdenar[filho] < vetorOrdenar[indiceMelhor]){
                indiceMelhor = filho;
            }
        }

        if(indiceMelhor != indicePai){
            DHeapMax.swap(vetorOrdenar, indicePai, indiceMelhor);
            DHeapMin.DheapifyMin(vetorOrdenar, n, d, indiceMelhor);
        }
    }

    public static void buildDHeapMin(int[] vetorOrdenar, int n, int d){
        int indiceUltimoNo = n - 1;
        int indiceUltimoPai = DHeapMax.paiD(indiceUltimoNo, d);
        for(int i=indiceUltimoPai;i>=0;i--){
            DHeapMin.DheapifyMin(vetorOrdenar, n, d, i);
        }
    }

    public static void DHeapSortMin(int[] vetorOrdenar, int n, int d){
        DHeapMin.buildDHeapMin(vetorOrdenar, n, d);

        for(int i=n-1;i>0;i--){
            DHeapMax.swap(vetorOrdenar, 0, i);
            DHeapMin.DheapifyMin(vetorOrdenar, i, d, 0);
        }
    }
}