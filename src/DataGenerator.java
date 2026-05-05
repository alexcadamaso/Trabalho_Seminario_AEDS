import java.util.Random;

// classe para gerar os dados de ordenação
public class DataGenerator {
    // definição de constantes
    public static final long SEED = 42L;
    public static final double LIMITE_MAXIMO = 1000000.0;
    public static final double LIMITE_MINIMO = -1000000.0;

    public static final int ALEATORIO = 1;
    public static final int ORDENADO = 2;
    public static final int INVERTIDO = 3;

    private DataGenerator(){}

    // função para gerar dados de acordo com a ordenação escolhida
    public static double[] gerarDados(int n, int tipoOrganizacaoDados){
        if (n > 0){
            double[] vetorDados = new double[n];

            if (tipoOrganizacaoDados == ALEATORIO){
                Random gerador = new Random(SEED);
                double amplitude = LIMITE_MAXIMO - LIMITE_MINIMO;
                for(int i=0;i<n;i++){
                    vetorDados[i] = LIMITE_MINIMO + (gerador.nextDouble() * amplitude);
                }
                return vetorDados;

            } else if (tipoOrganizacaoDados == ORDENADO){
                for(int i=0;i<n;i++){
                    vetorDados[i] = i;
                }
                return vetorDados;

            } else if (tipoOrganizacaoDados == INVERTIDO){
                for(int i=0;i<n;i++){
                    vetorDados[i] = n - i - 1;
                }
                return vetorDados;

            } else {
                throw new IllegalArgumentException("Erro ao gerar dados: tipo de organização inválido.");
            }

        } else {
            throw new IllegalArgumentException("Erro ao criar vetor de dados: tamanho inválido.");
        }
    }
}