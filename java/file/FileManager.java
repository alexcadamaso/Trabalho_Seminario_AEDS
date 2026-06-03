package file;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    // Definição de constantes
    public static final long SEED = 42L;
    public static final int ALEATORIO = 1;
    public static final int ORDENADO = 2;
    public static final int INVERTIDO = 3;

    private FileManager(){}

    // Função para gerar dados de acordo com a ordenação escolhida
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

    // Função para gravar os dados gerados em arquivos .dat
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

    // Função para ler os dados do arquivo .dat
    public static int[] lerDadosArquivo(int n, String caminho_arquivo){
        if(n <= 0 || n > 1000000){
            throw new IllegalArgumentException("Erro ao ler dados do arquivo: tamanho errado - " + n + " - de 1 até o limite máximo de 1.000.000");
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

    // Função para apagar dados do arquivo output.dat
    public static void limparArquivo(String caminho) {
        File arquivo = new File(caminho);

        if (arquivo.exists()) {
            try (FileWriter escrever = new FileWriter(arquivo)) {
            } catch (IOException e) {
                System.err.println("Erro ao tentar limpar o arquivo output.dat : " + e.getMessage());
            }
        }
    }

    // Função para gravar dados no arquivo output.dat
    public static void gravarResultado(int indice, String algoritmo, int d, int tamanho, String tipoOrdenacao, double tempoExecucao, long memoriaConsumida, long trocas) {
        String caminhoPasta = "resultsJava";
        String caminhoArquivo = caminhoPasta + "/output.dat";

        try {
            Files.createDirectories(Paths.get(caminhoPasta));
            
            File arquivo = new File(caminhoArquivo);
            boolean arquivoVazio = !arquivo.exists() || arquivo.length() == 0;

            try (FileWriter escrever = new FileWriter(arquivo, true); PrintWriter modoEscrever = new PrintWriter(escrever)) {

                if (arquivoVazio) {
                    modoEscrever.println("Indice;Algoritmo;Aridade_D;Tamanho;Tipo;Tempo_ms;Memoria_KB;Trocas");
                }

                modoEscrever.printf("%d;%s;%d;%d;%s;%f;%d;%d%n", 
                                    indice, algoritmo, d, tamanho, tipoOrdenacao, tempoExecucao, memoriaConsumida, trocas);
            }

        } catch (IOException e) {
            System.err.println("Erro ao gravar resultados: " + e.getMessage());
        }
    }

    // Função para gravar os dados médios no arquivo statistics.dat
    public static void gravarMedias(String caminhoResumo, String algoritmo, int d,int tamanho, String tipo, double tempoMedio, long memoriaMedia, long memoriaFixa, long trocasMedias) {
        try {
            Files.createDirectories(Paths.get("resultsJava"));
            File arquivo = new File(caminhoResumo);
            boolean novoArquivo = !arquivo.exists() || arquivo.length() == 0;

            try (FileWriter escrever = new FileWriter(arquivo, true);
                PrintWriter modoEscrever = new PrintWriter(escrever)) {
                
                if (novoArquivo) {
                    modoEscrever.println("Algoritmo;Aridade_D;Tamanho;Ordem;TempoMedio_ms;MemoriaMedia_KB;MemoriaFixa_KB;TrocasMedias");
                }

                modoEscrever.printf("%s;%d;%d;%s;%f;%d;%d;%d%n", 
                        algoritmo, d, tamanho, tipo, tempoMedio, memoriaMedia, memoriaFixa, trocasMedias);
            }
        } catch (IOException e) {
            System.err.println("Erro ao gravar resumo com as médias: " + e.getMessage());
        }
    }

}