package dheapmin;
import dheapmax.DHeapMax;

public class DHeapMin {
    private DHeapMin(){}

    public static void DheapifyMin(int[] vetorOrdenar, int n, int d, int indicePai){
        if(d < 2) d = 2;
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
        if(vetorOrdenar == null){
            throw new IllegalArgumentException("Erro: o vetor para ordenação não pode ser nulo");
        }
        if(n < 0 || n > vetorOrdenar.length){
            throw new IllegalArgumentException("Erro: o tamanho n está inválido : " + n);
        }
        if(d < 2){
            throw new IllegalArgumentException("Erro: o valor de d-Aridade deve ser maior que 2: " + d);
        }
        
        DHeapMin.buildDHeapMin(vetorOrdenar, n, d);

        for(int i=n-1;i>0;i--){
            DHeapMax.swap(vetorOrdenar, 0, i);
            DHeapMin.DheapifyMin(vetorOrdenar, i, d, 0);
        }
    }
}