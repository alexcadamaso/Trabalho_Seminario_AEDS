import file.FileManager;
import dheapmax.DHeapMax;
import dheapmin.DHeapMin;
import smoothsort.SmoothSort;

/**
 * Classe principal para execução da bateria de testes dos algoritmos.
 * 
 * Realiza testes automatizados para os algoritmos DHeapMax, DHeapMin e SmoothSort, variando tamanho do vetor, 
 * tipo de ordenação prévia e aridade d (para os D-Heaps). Os resultados são gravados em arquivos para análise posterior.
 * 
 * Metodologia:
 *   - Cada configuração é executada 20 vezes para reduzir variância
 *   - As médias de tempo, memória e trocas são gravadas separadamente
 *   - O vetor original é clonado antes de cada execução para garantir que todos os algoritmos recebem os mesmos dados
 */

public class Main {

    // Seleciona e executa o algoritmo de ordenação pelo nome.
    public static void selecionarExecutar(String nome, int[] vetor, int n, int d) {
        switch (nome) {
            case "DHeapMax":
                DHeapMax.DHeapSortMax(vetor, n, d);
                break;
            case "DHeapMin":
                 DHeapMin.DHeapSortMin(vetor, n, d); 
                break;
            case "SmoothSort":
                SmoothSort.smoothSort(vetor); 
                break;
        }
    }

    public static void main(String[] args) {
        // Configuração para os testes
        int[] tamanhos = {100, 1000, 10000, 100000, 1000000}; // tamanhos de entrada conforme pedido: 10², 10³, 10⁴, 10⁵, 10⁶
        String[] tipos = {"aleatorio", "ordenado", "invertido"};
        String[] algoritmos = {"DHeapMax", "DHeapMin", "SmoothSort"};
        int[] aridades = {2, 3, 4, 8}; 
        
        int repeticoes = 20;
        String caminhoOutput = "resultsJava/output.dat";
        String caminhoResumo = "resultsJava/statistics.dat";

        // Limpando os dados anteriores
        FileManager.limparArquivo(caminhoOutput);
        FileManager.limparArquivo(caminhoResumo);

        try {
            for (int n : tamanhos) {
                for (String tipoOrdenacao : tipos) {
                    String caminhoDados = "../data/" + tipoOrdenacao + ".dat";
                    int[] original = FileManager.lerDadosArquivo(n, caminhoDados);
                    long memoriaFixaVetorKB = (n * 4) / 1024; // cada int ocupa 4 bytes

                    for (String algoritmo : algoritmos) {
                        boolean eHeapEspecial = algoritmo.contains("DHeap");
                        int[] dParaTestar;
                        if (eHeapEspecial) {
                            dParaTestar = aridades;
                        } else {
                            dParaTestar = new int[]{1};
                        }

                        for (int d : dParaTestar) {

                            long totalTempo = 0;
                            long totalTrocas = 0;
                            long totalConsumoMemoria = 0;

                            for (int i = 0; i < repeticoes; i++) {
                                int[] copia = original.clone();
                                
                                DHeapMax.contadorTrocas = 0; 

                                Runtime runtime = Runtime.getRuntime();
                                runtime.gc(); 
                                long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();

                                long inicio = System.nanoTime();
                                
                                // Rodando o algoritmo
                                selecionarExecutar(algoritmo, copia, n, d);
                                
                                long fim = System.nanoTime();
                                long memoriaDepois = runtime.totalMemory() - runtime.freeMemory();

                                long tempoExecucao = (fim - inicio) / 1000000;
                                long trocasRealizadas = DHeapMax.contadorTrocas; 
                                long consumoMemoriaRodada = Math.max(0, (memoriaDepois - memoriaAntes) / 1024);

                                
                                FileManager.gravarResultado(
                                    (i + 1), algoritmo, d, n, tipoOrdenacao, tempoExecucao, consumoMemoriaRodada, trocasRealizadas
                                );

                                totalTempo += tempoExecucao;
                                totalTrocas += trocasRealizadas;
                                totalConsumoMemoria += consumoMemoriaRodada;
                            }

                            // Calculando as médias
                            long tempoMedio = totalTempo / repeticoes;
                            long trocasMedias = totalTrocas / repeticoes;
                            long memMediaDinamica = totalConsumoMemoria / repeticoes;

                            FileManager.gravarMedias(
                                caminhoResumo, algoritmo, d, n, tipoOrdenacao, tempoMedio, memMediaDinamica, memoriaFixaVetorKB, trocasMedias
                            );
                        }
                    }
                }
            }
            System.out.println(">>> Todos os testes foram finalizados com sucesso, confira em 'results'! <<<");

        } catch (IllegalArgumentException e) {
            System.err.println("Erro nos parâmetros de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro crítico durante a bateria de testes:");
            e.printStackTrace();
        }
    }
    
}