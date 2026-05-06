public class Heap {
    private Heap(){}

    // função para realizar troca no heap
    public static void swap(double[] vetor, int a, int b){
        if(a < 0 || a >= vetor.length || b < 0 || b >= vetor.length){
            throw new IllegalArgumentException("Indices inválidos para realizar swap");
        }

        double aux = vetor[a];
        vetor[a] = vetor[b];
        vetor[b] = aux;
    }

    public static int pai(int i){
        int indicePai = (i - 1)/2;
        return indicePai;
    }

    public static int filho_esquerda(int i){
        int indiceFilhoEsquerda = (2 * i) + 1;
        return indiceFilhoEsquerda;
    }

    public static int filho_direita(int i){
        int indiceFilhoDireita = (2 * i) + 2;
        return indiceFilhoDireita;
    }

    public static void heapify(double[] vetorOrdenar, int n, int indicePai){
        int maior = indicePai;

        int indiceFilhoEsquerda = Heap.filho_esquerda(indicePai);
        int indiceFilhoDireita = Heap.filho_direita(indicePai);

        if(indiceFilhoEsquerda < n && vetorOrdenar[maior] < vetorOrdenar[indiceFilhoEsquerda]){
            maior = indiceFilhoEsquerda;
        }

        if(indiceFilhoDireita < n && vetorOrdenar[maior] < vetorOrdenar[indiceFilhoDireita]){
            maior = indiceFilhoDireita;
        }

        if (maior != indicePai){
            Heap.swap(vetorOrdenar, indicePai, maior);
            Heap.heapify(vetorOrdenar, n, maior);
        }
    }

    public static void buildHeap(double[] vetorOrdenar, int n){
        int ultimoNo = n-1;
        int indiceUltimoPai = Heap.pai(ultimoNo);

        for(int i=indiceUltimoPai;i>=0;i--){
            Heap.heapify(vetorOrdenar, n, i);
        }
    }

    public static void heapSort(double[] vetorOrdenar, int n){
        Heap.buildHeap(vetorOrdenar, n);

        for(int i=n - 1;i>0;i--){
            Heap.swap(vetorOrdenar, 0 , i);
            Heap.heapify(vetorOrdenar, i , 0);
        }
    } 
             
}