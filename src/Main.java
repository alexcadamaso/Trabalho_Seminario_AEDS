import data.DataGenerator;
import heap.Heap;

public class Main {
    public static void main(String[] args){
        // gerar dados para ordenação
        int n = 12;
        try{
            int[] vetorDados = DataGenerator.gerarDados(n, DataGenerator.ALEATORIO);
            //DataGenerator.gravarDadosArquivo(vetorDados, "src/data/invertido.dat");
            //System.out.println("Arquivo gravado");
            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
                if(i == 20){
                    break;
                }
            }

            Heap.heapSort(vetorDados, n, Heap.MIN_HEAP);
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

