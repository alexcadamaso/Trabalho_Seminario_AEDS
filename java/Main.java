import data.DataGenerator;
import heap.Heap;
import heapsort.HeapSort;
import dheap.DHeap;
import smoothsort.SmoothSort;

public class Main {
    public static void main(String[] args){
        // lendo os dados para ordenação
        int n = 10;
        int d = 3;
        String caminho_arquivo = "java/data/invertido.dat";
        int[] vetorDados = DataGenerator.lerDadosArquivo(n, caminho_arquivo);

        System.out.println("Antes:");
        for(int i = 0; i < n; i++) System.out.println(vetorDados[i]);

        SmoothSort.smoothSort(vetorDados);

        System.out.println("Depois:");
        for(int i = 0; i < n; i++) System.out.println(vetorDados[i]);
        /*try{
            int[] vetorDados = DataGenerator.lerDadosArquivo(n, caminho_arquivo);
            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
                if(i == 20){
                    break;
                }
            }

           // HeapSort.heapSort(vetorDados, n, Heap.MAX_HEAP);
           //DHeap.DHeapSort(vetorDados, n, d);
           //DHeap.buildDHeap(vetorDados, n, d);
           //System.out.println("Sou heap-d");
            //System.out.println("Dados ordenados com sucesso: ");
            /*for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
                if(i == 20){
                    break;
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }*/
    }
}

