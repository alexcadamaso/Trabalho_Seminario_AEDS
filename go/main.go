package main

import (
	"fmt"
	"os"
	"runtime"
	"time"
	"ordenacao_go/go/data_manipulation"
	"ordenacao_go/go/heap"
)

// estrutura para armazenar os resultados de desempenho de cada algoritmo
type Resultado struct {
	Nome    string
	Tempo   time.Duration
	Memoria uint64 // kb
}

// medirDesempenho executa a função passada e retorna métricas de tempo e memória
func medirDesempenho(nome string, algoritmo func()) Resultado {
	var m1, m2 runtime.MemStats
	
	// limpa o lixo da memória antes de medir para evitar falsos positivos
	runtime.GC()
	runtime.ReadMemStats(&m1)

	inicio := time.Now()
	algoritmo()
	duracao := time.Since(inicio)
	
	runtime.ReadMemStats(&m2)
	
	// diferença em KB
	memoriaUsada := (m2.Alloc - m1.Alloc) / 1024

	return Resultado{
		Nome:    nome,
		Tempo:   duracao,
		Memoria: memoriaUsada,
	}
}

// testar executa os algoritmos de ordenação e coleta os resultados
//  salva em um arquivo de saída
func testar(n int, d int, caminhoEntrada string, caminhoSaida string) {
	
	// carregar os dados
	dadosOriginais := data_manipulation.LerDadosArquivo(caminhoEntrada, n)
	
	if len(dadosOriginais) == 0 {
		fmt.Println("Erro: Nenhum dado carregado")
		return
	}

	// cria cópias independentes para não interferir nos algoritmos
	dadosMax := make([]int, len(dadosOriginais))
	dadosMin := make([]int, len(dadosOriginais))
	dadosDario := make([]int, len(dadosOriginais))
	
	copy(dadosMax, dadosOriginais)
	copy(dadosMin, dadosOriginais)
	copy(dadosDario, dadosOriginais)

	// executa e mede os algoritmos
	var resultados []Resultado

	resultados = append(resultados, medirDesempenho("HeapSort Max (Binário)", func() {
		heap.HeapSortMax(dadosMax, len(dadosMax))
	}))

	resultados = append(resultados, medirDesempenho("HeapSort Min (Binário)", func() {
		heap.HeapSortMin(dadosMin, len(dadosMin))
	}))

	resultados = append(resultados, medirDesempenho(fmt.Sprintf("HeapSort %d-ário", d), func() {
		heap.HeapSortDario(dadosDario, len(dadosDario), d)
	}))

	// salva e exibi resultados
	// Mudança aqui: os.OpenFile com O_APPEND para anexar ao fim do arquivo e O_CREATE para criá-lo se não existir
	arquivo, err := os.OpenFile(caminhoSaida, os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		fmt.Printf("Não foi possível abrir o arquivo de saída: %v\n", err)
		return
	}
	defer arquivo.Close()

	// imprime os resultados no console e salva no arquivo
	imprimir := func(s string) {
		fmt.Print(s)
		fmt.Fprint(arquivo, s)
	}

	imprimir(fmt.Sprintf("\nTeste com o arquivo: %s (n=%d)\n", caminhoEntrada, n))
	imprimir("---------------- RELATÓRIO DE DESEMPENHO ----------------\n")
	imprimir(fmt.Sprintf("%-25s | %-15s | %-15s\n", "Algoritmo", "Tempo", "Memória (Heap)"))
	imprimir("---------------------------------------------------------\n")

	for _, r := range resultados {
		linha := fmt.Sprintf("%-25s | %-15v | %d KB\n", r.Nome, r.Tempo, r.Memoria)
		imprimir(linha)
	}
}

func main() {
	// configurações do teste
	n := 100000             // tamanho da entrada
	d := 3                  
	arquivoInputA := "../data/aleatorio.dat"
	arquivoInputI := "../data/invertido.dat"
	arquivoInputO := "../data/ordenado.dat"
	arquivoOutput := "results.txt"

	// Opcional: remover o arquivo antigo no início da execução para não misturar com execuções de dias anteriores
	os.Remove(arquivoOutput)

	testar(n, d, arquivoInputA, arquivoOutput)
	testar(n, d, arquivoInputI, arquivoOutput)
	testar(n, d, arquivoInputO, arquivoOutput)
}