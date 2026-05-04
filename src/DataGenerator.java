import java.util.Random;

// classe para gerar os dados de ordenação
public class DataGenerator {
    // definição de constantes
    public static final long SEED = 42L;
    public static final float LIMITE_MAXIMO = 1000000.0f;
    public static final float LIMITE_MINIMO = -1000000.0f;

    public static final int ALEATORIO = 1;
    public static final int ORDENADO = 2;
    public static final int INVERTIDO = 3;

    private DataGenerator(){}

    // função para gerar dados de acordo com a ordenação escolhida
    public static float[] gerarDados(int n, int tipo_organizacao_dados){
        if (n > 0){
            float[] vetorDados = new float[n];

            if (tipo_organizacao_dados == ALEATORIO){
                Random gerador = new Random(SEED);
                float amplitude = LIMITE_MAXIMO - LIMITE_MINIMO;
                for(int i=0;i<n;i++){
                    vetorDados[i] = LIMITE_MINIMO + (gerador.nextFloat() * amplitude);
                }
                return vetorDados;

            } else if (tipo_organizacao_dados == ORDENADO){
                for(int i=0;i<n;i++){
                    vetorDados[i] = (float) i;
                }
                return vetorDados;

            } else if (tipo_organizacao_dados == INVERTIDO){
                for(int i=0;i<n;i++){
                    vetorDados[i] = (float) n - i;
                }
                return vetorDados;

            } else {
                System.err.println("Erro ao gerar dados: tipo de organização inválido.");
                return null;
            }

        } else {
            System.err.println("Erro ao criar vetor de dados: tamanho inválido.");
            return null;
        }
    }
}