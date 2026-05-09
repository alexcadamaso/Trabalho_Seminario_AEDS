import data.DataGenerator;
import heap.Heap;
import heapsort.HeapSort;
import dheap.DHeap;
import dheapmin.DHeapMin;
import smoothsort.SmoothSort;

public class Main {
    public static void main(String[] args){
        // lendo os dados para ordenação
        int n = 1000000;
        int d = 2;
        String caminho_arquivo = "java/data/ordenado.dat";
        
        try{
            int[] original = DataGenerator.lerDadosArquivo(n, caminho_arquivo);

            for (int i = 0; i < 5; i++) {
                int[] copia = original.clone();
                //DHeap.DHeapSort(copia, n, d);
                DHeapMin.DHeapSortMin(copia, n, d);
                //HeapSort.heapSort(original, n, Heap.MIN_HEAP);
                //SmoothSort.smoothSort(original);
            }

            
            int repeticoes = 20;
            long total = 0;

            for (int i = 0; i < repeticoes; i++) {
                int[] copia = original.clone();

                long inicio = System.nanoTime();
                //DHeap.DHeapSort(copia, n, d);
                DHeapMin.DHeapSortMin(copia, n, d);
                //HeapSort.heapSort(original, n, Heap.MIN_HEAP);
                //SmoothSort.smoothSort(original);
                long fim = System.nanoTime();

                total += (fim - inicio);
            }

            long media = total / repeticoes;
            System.out.println("Tempo médio: " + media + " ns");

            /*for(int i=0;i<n;i++){
                System.out.println(original[i]);
                if(i == 20){
                    break;
                }
            }

            
           //HeapSort.heapSort(vetorDados, n, Heap.MIN_HEAP);
           //DHeap.DHeapSort(vetorDados, n, d);
           System.out.println("Sou heap-d");
           //System.out.println("Sou smooth");
           //SmoothSort.smoothSort(vetorDados);
           

            System.out.println("Dados ordenados com sucesso: ");
            int[] teste = original.clone();
            //DHeapMin.DHeapSortMin(teste, n, d);
            HeapSort.heapSort(original, n, Heap.MIN_HEAP);

            System.out.println("Dados ordenados (MIN HEAP):");
            for(int i=0;i<n;i++){
                System.out.println(teste[i]);
                if(i == 20){
                    break;
                }
            }*/
            
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}

