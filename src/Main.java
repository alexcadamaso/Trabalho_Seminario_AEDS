public class Main {
    public static void main(String[] args){
        // gerar dados para ordenação
        int n = 3;
        try{
            double[] vetorDados = DataGenerator.gerarDados(n, DataGenerator.INVERTIDO);
            for(int i=0;i<n;i++){
                System.out.println(vetorDados[i]);
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}

