package heap
import "os"
import "fmt"

func swap(vetorDados []int, a int, b int){
	n := len(vetorDados)
	if a >= n || b >= n || a < 0 || b < 0 {
		fmt.Println("Erro ao realizar swap: indíce inválido")
		os.Exit(1);
	}

	aux := vetorDados[a]
	vetorDados[a] = vetorDados[b]
	vetorDados[b] = aux

}

func heapifyMax(vetorDados []int, n int, indicePai int){
	indiceMaior := indicePai
	indiceFilhoEsquerda := (2 * indicePai) + 1
	indiceFilhoDireita := (2 * indicePai) + 2

	if indiceFilhoEsquerda < n && vetorDados[indiceFilhoEsquerda] > vetorDados[indiceMaior] {
		indiceMaior = indiceFilhoEsquerda
	}

	if indiceFilhoDireita < n && vetorDados[indiceFilhoDireita] > vetorDados[indiceMaior] {
		indiceMaior = indiceFilhoDireita
	}

	if indiceMaior != indicePai {
		swap(vetorDados, indicePai, indiceMaior)
		heapifyMax(vetorDados, n, indiceMaior)
	}

}

func buildHeapMax(vetorDados []int, n int){
	indiceUltimoNo := n - 1
	indiceUltimoPai := (indiceUltimoNo - 1)/2

	for i:=indiceUltimoPai;i>=0;i-- {
		heapifyMax(vetorDados, n, i)
	}
}

func HeapSortMax(vetorDados []int, n int) []int{
	buildHeapMax(vetorDados, n)

	for i:=n-1;i>0;i--{
		swap(vetorDados, i, 0)
		heapifyMax(vetorDados, i, 0)
	}
	return vetorDados
}