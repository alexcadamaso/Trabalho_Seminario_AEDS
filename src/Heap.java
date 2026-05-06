import java.util.Comparator;

public class Heap {
    public static final Comparator<Integer> MAX_HEAP =  (a,b) -> Integer.compare(a,b);
    public static final Comparator<Integer> MIN_HEAP = (a,b) -> Integer.compare(b,a);

    private Heap(){}

    // função para realizar troca no heap
    public static void swap(int[] vetor, int a, int b){
        int aux = vetor[a];
        vetor[a] = vetor[b];
        vetor[b] = aux;
    }

    // encontrar o pai de um heap binário
    public static int pai(int i){
        int indicePai = (i - 1)/2;
        return indicePai;
    }

    // encontrar o nó da esquerda de um heap binário
    public static int filho_esquerda(int i){
        int indiceFilhoEsquerda = (2 * i) + 1;
        return indiceFilhoEsquerda;
    }

    // encontrar o nó da direita de um heap binário
    public static int filho_direita(int i){
        int indiceFilhoDireita = (2 * i) + 2;
        return indiceFilhoDireita;
    }


    public static void heapify(int[] vetorOrdenar, int n, int indicePai, Comparator<Integer> tipoHeap){
        int noSubir = indicePai;

        int indiceFilhoEsquerda = Heap.filho_esquerda(indicePai);
        int indiceFilhoDireita = Heap.filho_direita(indicePai);

        if(indiceFilhoEsquerda < n && tipoHeap.compare(vetorOrdenar[indiceFilhoEsquerda], vetorOrdenar[noSubir]) > 0){
            noSubir = indiceFilhoEsquerda;
        }

        if(indiceFilhoDireita < n && tipoHeap.compare(vetorOrdenar[indiceFilhoDireita], vetorOrdenar[noSubir]) > 0){
            noSubir = indiceFilhoDireita;
        }


        if (noSubir != indicePai){
            Heap.swap(vetorOrdenar, indicePai, noSubir);
            Heap.heapify(vetorOrdenar, n, noSubir, tipoHeap);
        }
    }

    public static void buildHeap(int[] vetorOrdenar, int n, Comparator<Integer> tipoHeap){
        int ultimoNo = n-1;
        int indiceUltimoPai = Heap.pai(ultimoNo);

        for(int i=indiceUltimoPai;i>=0;i--){
            Heap.heapify(vetorOrdenar, n, i, tipoHeap);
        }
    }

    public static void heapSort(int[] vetorOrdenar, int n, Comparator<Integer> tipoHeap){
        Heap.buildHeap(vetorOrdenar, n,tipoHeap);

        for(int i=n - 1;i>0;i--){
            Heap.swap(vetorOrdenar, 0 , i);
            Heap.heapify(vetorOrdenar, i , 0, tipoHeap);
        }
    } 
             
}