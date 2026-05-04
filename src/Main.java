public class Main {
    public static void main(String[] args){
        // gerar dados para ordenação
        int n = 3;
        float[] vetorDados = DataGenerator.gerarDados(n, DataGenerator.ALEATORIO);
        
        if(vetorDados != null){
            System.out.println("Dados gerados com sucesso.");

            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
            }
        } else {
            System.exit(1);
        }
    }
}

