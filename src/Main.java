import data.DataGenerator;
import heap.Heap;
import heapsort.HeapSort;

public class Main {
    public static void main(String[] args){
        // lendo os dados para ordenação
        int n = 13;
        String caminho_arquivo = "src/data/ordenado.dat";
        try{
            int[] vetorDados = DataGenerator.lerDadosArquivo(n, caminho_arquivo);
            
            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
                if(i == 20){
                    break;
                }
            }

            HeapSort.heapSort(vetorDados, n, Heap.MAX_HEAP);
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

