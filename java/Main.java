import data.DataGenerator;
import dheapmax.DHeapMax;
import dheapmin.DHeapMin;
import smoothsort.SmoothSort;

public class Main {
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
        int[] tamanhos = {100, 1000, 10000, 100000, 1000000}; 
        String[] tipos = {"aleatorio", "ordenado", "invertido"};
        String[] algoritmos = {"DHeapMax", "DHeapMin", "SmoothSort"};
        int[] aridades = {2}; 
        
        int repeticoes = 20;
        String caminhoOutput = "results/output.dat";
        String caminhoResumo = "results/statistics.dat";

        // Limpando os dados anteriores
        DataGenerator.limparArquivo(caminhoOutput);
        DataGenerator.limparArquivo(caminhoResumo);

        try {
            for (int n : tamanhos) {
                for (String tipoOrdenacao : tipos) {
                    String caminhoDados = "java/data/" + tipoOrdenacao + ".dat";
                    int[] original = DataGenerator.lerDadosArquivo(n, caminhoDados);
                    long memoriaFixaVetorKB = (n * 4) / 1024;

                    for (String algoritmo : algoritmos) {
                        boolean eHeapEspecial = algoritmo.contains("DHeap");
                        int[] dParaTestar;
                        if (eHeapEspecial) {
                            dParaTestar = aridades;
                        } else {
                            dParaTestar = new int[]{1};
                        }

                        for (int d : dParaTestar) {
                            System.out.println("Executando: " + algoritmo + " (d=" + d + ") | N=" + n + " | " + tipoOrdenacao);

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

                                
                                DataGenerator.gravarResultado(
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

                            DataGenerator.gravarMedias(
                                caminhoResumo, algoritmo, d, n, tipoOrdenacao, tempoMedio, memMediaDinamica, memoriaFixaVetorKB, trocasMedias
                            );
                        }
                    }
                }
            }
            System.out.println("\n>>> Todos os testes finalizados com sucesso! <<<");

        } catch (Exception e) {
            System.err.println("Erro crítico durante a bateria de testes:");
            e.printStackTrace();
        }
    }
    
}