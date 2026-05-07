import data.DataGenerator;
import heap.Heap;
import heapsort.HeapSort;
import dheap.DHeap;

public class Main {
    public static void main(String[] args){
        // lendo os dados para ordenação
        int n = 6;
        int d = 3;
        String caminho_arquivo = "src/data/ordenado.dat";
        try{
            int[] vetorDados = DataGenerator.lerDadosArquivo(n, caminho_arquivo);
            System.out.println("n = " + n);
            System.out.println("length = " + vetorDados.length);
            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
                if(i == 20){
                    break;
                }
            }

           // HeapSort.heapSort(vetorDados, n, Heap.MAX_HEAP);
           DHeap.DHeapSort(vetorDados, n, d);
           //DHeap.buildDHeap(vetorDados, n, d);
           System.out.println("Sou heap-d");
            System.out.println("Dados ordenados com sucesso: ");
            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
                if(i == 20){
                    break;
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}

