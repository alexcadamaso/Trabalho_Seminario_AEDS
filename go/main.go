package main

import (
	"fmt"
	"ordenacao_go/go/data_manipulation"
	"ordenacao_go/go/heap"
	"os"
	"time" // medição de tempo
)

func main() {
	n := 6
	if n <= 0 || n > 1000000 {
		fmt.Println("Número de entrada inválido, deve estar entre 1 e 1000000")
		os.Exit(1)
	}

	caminho := "./data/invertido.dat"
	dados := data_manipulation.LerDadosArquivo(caminho, n)
	fmt.Println("Dados: ", dados)

	resultado := heap.HeapSortMax(dados, len(dados))
	fmt.Println("Ordenado: ", resultado)



	caminhoD := "../data/aleatorio.dat"
	dadosD := data_manipulation.LerDadosArquivo(caminhoD, n)

	// criando uma cópia para o Heap D-ario
	dadosDario := make([]int, len(dadosD))
	copy(dadosDario, dadosD)
	
	fmt.Println("Dados Iniciais: ", dadosD)

	
	// teste do heap D-ário (D=3)
	d := 3
	comeco := time.Now()
	resultadoD := heap.HeapSortDario(dadosDario, len(dadosDario), d)
	duracao := time.Since(comeco)

	fmt.Printf("Ordenado (D=%d): %v\n", d, resultadoD)
	fmt.Printf("Tempo de execução: %v\n", duracao)

}
