package main

import (
	"fmt"
	"ordenacao_go/go/data_manipulation"
	"ordenacao_go/go/heap"
	"os"
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

}
