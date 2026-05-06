public class Main {
    public static void main(String[] args){
        // gerar dados para ordenação
        int n = 12;
        try{
            double[] vetorDados = DataGenerator.gerarDados(n, DataGenerator.INVERTIDO);
            for(int i=0;i<n;i++){
                if(i == 3){
                    vetorDados[i] = 2;
                }
                if(i == 10){
                    vetorDados[i] = 9;
                }
                System.out.println(vetorDados[i]);
            }

            Heap.heapSort(vetorDados, n);
            System.out.println("Dados ordenados com sucesso: ");
            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}

