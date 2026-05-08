import data.DataGenerator;
import heap.Heap;
import heapsort.HeapSort;
import dheap.DHeap;
import smoothsort.SmoothSort;

public class Main {
    public static void main(String[] args){
        // lendo os dados para ordenação
        int n = 9;
        int d = 3;
        String caminho_arquivo = "java/data/aleatorio.dat";
        try{
            int[] vetorDados = DataGenerator.lerDadosArquivo(n, caminho_arquivo);
            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
                if(i == 20){
                    break;
                }
            }
            System.out.println("Leonarod: ");
            int[] sequencia = SmoothSort.numerosLeonardo(n);
            for(int i=0;i<sequencia.length;i++){
                System.out.println(sequencia[i]);
            }
            int[] arvores = SmoothSort.arvoresFloresta(sequencia, n);
            System.out.println("Tamanho das árvores: ");
            
            for(int i=0;i<arvores.length;i++){
                System.out.println(arvores[i]);
            }
            System.out.println("Testando o sift");
            int k = SmoothSort.obterIndiceK(sequencia, n);
            SmoothSort.sift(sequencia, vetorDados, k, n-1);
            for(int i=0;i<vetorDados.length;i++){
                System.out.println(vetorDados[i]);
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
            }*/
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}

