import data.DataGenerator;
import dheapmax.DHeapMax;
import dheapmin.DHeapMin;
import smoothsort.SmoothSort;

/*public class Main {
    public static void main(String[] args){
        // lendo os dados para ordenação
        int n = 1000000;
        int d = 2;
        String caminho_arquivo = "java/data/invertido.dat";
        
        try{
            int[] original = DataGenerator.lerDadosArquivo(n, caminho_arquivo);

            for (int i = 0; i < 5; i++) {
                int[] copia = original.clone();
                DHeapMax.DHeapSortMax(copia, n, d);
                //DHeapMin.DHeapSortMin(copia, n, d);
                //HeapSort.heapSort(original, n, Heap.MAX_HEAP);
                //SmoothSort.smoothSort(original);
            }

            
            int repeticoes = 20;
            long total = 0;

            for (int i = 0; i < repeticoes; i++) {
                int[] copia = original.clone();

                long inicio = System.nanoTime();
                DHeapMax.DHeapSortMax(copia, n, d);
                //DHeapMin.DHeapSortMin(copia, n, d);
                //HeapSort.heapSort(original, n, Heap.MAX_HEAP);
                //SmoothSort.smoothSort(original);
                long fim = System.nanoTime();

                total += (fim - inicio);
            }

            long media = total / repeticoes;
            System.out.println("Tempo médio: " + media + " ns");

            for(int i=0;i<n;i++){
                System.out.println(original[i]);
                if(i == 20){
                    break;
                }
            }

            
           //HeapSort.heapSort(vetorDados, n, Heap.MIN_HEAP);
           //DHeap.DHeapSort(vetorDados, n, d);
           System.out.println("Sou heap-d");
           //System.out.println("Sou smooth");
           //SmoothSort.smoothSort(vetorDados);
           

            System.out.println("Dados ordenados com sucesso: ");
            int[] teste = original.clone();
            //DHeapMin.DHeapSortMin(teste, n, d);
            DHeapMax.DHeapSortMax(teste, n, d);
            

            System.out.println("Dados ordenados (MIN HEAP):");
            for(int i=0;i<n;i++){
                System.out.println(teste[i]);
                if(i == 20){
                    break;
                }
            }
            
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}*/






public class Main {
    public static void main(String[] args) {
        int n = 1000000;
        int d = 2;
        String algoritmo = "D-Heap-Max";
        String tipoOrdenacao = "Invertido";
        String caminhoOutput = "results/output.dat";
        String caminhoResumo = "results/statistics.dat";

        long totalTempo = 0;
        long totalTrocas = 0;
        long totalConsumoMemoria = 0; 
        long memoriaFixaVetorKB = (n * 4) / 1024;

        DataGenerator.limparArquivo(caminhoOutput);
        DataGenerator.limparArquivo(caminhoResumo);

        try {
            int[] original = DataGenerator.lerDadosArquivo(n, "java/data/invertido.dat");

            int repeticoes = 20;
            for (int i = 0; i < repeticoes; i++) {
                int[] copia = original.clone();

                Runtime runtime = Runtime.getRuntime();
                runtime.gc(); 
                long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();

                DHeapMax.contadorTrocas = 0;

                long inicio = System.nanoTime();
                DHeapMax.DHeapSortMax(copia, n, d);
                long fim = System.nanoTime();
                
                long memoriaDepois = runtime.totalMemory() - runtime.freeMemory();
                long tempoExecucao = (fim - inicio) / 1000000;
                long trocasRealizadas = DHeapMax.contadorTrocas;
                long consumoMemoriaRodada = Math.max(0, (memoriaDepois - memoriaAntes) / 1024);

                
                DataGenerator.gravarResultado(
                    (i + 1), algoritmo, n, tipoOrdenacao, tempoExecucao, consumoMemoriaRodada, trocasRealizadas
                );

                
                totalTempo += tempoExecucao;
                totalTrocas += trocasRealizadas;
                totalConsumoMemoria += consumoMemoriaRodada; 
            }
            
           
            long tempoMedio = totalTempo / repeticoes;
            long trocasMedias = totalTrocas / repeticoes;
            long memMediaDinamica = totalConsumoMemoria / repeticoes; 

            
            DataGenerator.gravarMedias(
                caminhoResumo, algoritmo, n, tipoOrdenacao, tempoMedio, memMediaDinamica, memoriaFixaVetorKB, trocasMedias
            );

            System.out.println("Testes finalizados com sucesso!");
            System.out.println("Resumo gravado em: results/statistics.dat");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}

