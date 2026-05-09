package data;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

// classe para gerar os dados de ordenação
public class DataGenerator {
    // definição de constantes
    public static final long SEED = 42L;
    public static final int ALEATORIO = 1;
    public static final int ORDENADO = 2;
    public static final int INVERTIDO = 3;

    private DataGenerator(){}

    // função para gerar dados de acordo com a ordenação escolhida
    public static int[] gerarDados(int n, int tipoOrganizacaoDados){
        if (n > 0){
            int[] vetorDados = new int[n];

            if (tipoOrganizacaoDados == ALEATORIO){
                Random gerador = new Random(SEED);
                for(int i=0;i<n;i++){
                    vetorDados[i] = gerador.nextInt(n) + 1;
                }
                return vetorDados;

            } else if (tipoOrganizacaoDados == ORDENADO){
                for(int i=0;i<n;i++){
                    vetorDados[i] = i + 1;
                }
                return vetorDados;

            } else if (tipoOrganizacaoDados == INVERTIDO){
                for(int i=0;i<n;i++){
                    vetorDados[i] = n - i;
                }
                return vetorDados;

            } else {
                throw new IllegalArgumentException("Erro ao gerar dados: tipo de organização inválido.");
            }

        } else {
            throw new IllegalArgumentException("Erro ao criar vetor de dados: tamanho inválido.");
        }
    }

    // classe para gravar os dados gerados em arquivos .dat
    public static void gravarDadosArquivo(int[] dados, String caminho_arquivo){
        try (BufferedWriter escrever = new BufferedWriter(new FileWriter(caminho_arquivo));) {
            for(int i=0;i<dados.length;i++){
                escrever.write(String.valueOf(dados[i]));
                escrever.newLine();
            }
        } catch (IOException e){
            throw new RuntimeException("Erro ao gravar dados no arquivo: " + caminho_arquivo, e);
        } 
    }

    // classe para ler os dados do arquivo .dat
    public static int[] lerDadosArquivo(int n, String caminho_arquivo){
        if(n < 0 || n > 1000000){
            throw new IllegalArgumentException("Erro ao ler dados do arquivo: tamanho errado - " + n + " - limite máximo de 1.000.000");
        }

        int[] vetorDados = new int[n];
        try (BufferedReader ler = new BufferedReader(new FileReader(caminho_arquivo))){
            for(int i=0;i<n;i++){
                String linha = ler.readLine();
                if(linha != null){
                    vetorDados[i] = Integer.parseInt(linha.trim());
                }
            }
            return vetorDados;
        } catch(IOException e){
            throw new RuntimeException("Erro ao ler dados do arquivo: " + caminho_arquivo, e);
        }
    }

    // função para apagar dados do arquivo output.dat
    public static void limparArquivo(String caminho) {
        File arquivo = new File(caminho);

        if (arquivo.exists()) {
            try (FileWriter escrever = new FileWriter(arquivo)) {
            } catch (IOException e) {
                System.err.println("Erro ao tentar limpar o arquivo output.dat : " + e.getMessage());
            }
        }
    }

}