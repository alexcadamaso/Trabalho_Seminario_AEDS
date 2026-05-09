package dheapmax;

public class DHeapMax {
    // Contador global de trocas realizadas durante a ordenação, compartilhado com DHeapMin e SmoothSort para centralizar a métrica.
    public static long contadorTrocas = 0;
    private DHeapMax(){}

    // Função para realizar troca no heap
    public static void swap(int[] vetor, int a, int b){
        if(a < 0 || a >= vetor.length || b < 0 || b >= vetor.length){
            throw new IllegalArgumentException("Indices inválidos para realizar swap");
        }

        int aux = vetor[a];
        vetor[a] = vetor[b];
        vetor[b] = aux;
        contadorTrocas++;
    }

    // Calcula o índice do pai de um nó em um d-heap
    public static int paiD(int indiceFilho, int d){
        int indicePai = (indiceFilho - 1) / d;
        return indicePai;
    }

    // Calcula o índice do k-ésimo filho de um nó em um d-heap.
    public static int filhoD(int indicePai, int d, int k){
        int indiceFilho = (indicePai * d) + k;
        return indiceFilho;
    }

    // Garante a propriedade de max-heap para que o maior valor seja o pai
    public static void DheapifyMax(int[] vetorOrdenar, int n, int d, int indicePai){
        if(d < 2) d = 2;
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

    // Constrói um max-heap a partir de um vetor desordenado
    public static void buildDHeapMax(int[] vetorOrdenar, int n, int d){
        int indiceUltimoNo = n - 1;
        int indiceUltimoPai = paiD(indiceUltimoNo, d);
        for(int i=indiceUltimoPai;i>=0;i--){
            DHeapMax.DheapifyMax(vetorOrdenar, n, d, i);
        }
    }

    // Ordena o vetor em ordem crescente usando o algoritmo D-Heap Max
    public static void DHeapSortMax(int[] vetorOrdenar, int n, int d){
        if(vetorOrdenar == null){
            throw new IllegalArgumentException("Erro: o vetor para ordenação não pode ser nulo");
        }
        if(n < 0 || n > vetorOrdenar.length){
            throw new IllegalArgumentException("Erro: o tamanho n está inválido : " + n);
        }
        if(d < 2){
            throw new IllegalArgumentException("Erro: o valor de d-Aridade deve ser maior que 2: " + d);
        }

        DHeapMax.buildDHeapMax(vetorOrdenar, n, d);

        for(int i=n-1;i>0;i--){
            DHeapMax.swap(vetorOrdenar, 0, i);
            DHeapMax.DheapifyMax(vetorOrdenar, i, d, 0);
        }
    }
}