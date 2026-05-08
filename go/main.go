package main
import "fmt"
import "ordenacao_go/go/heap"

func main() {
    n := 7
    dados := []int{38, 27, 43, 3, 9, 82, 10}
    fmt.Println("Dados: ", dados)

    resultado := heap.HeapSortMax(dados, n)
    fmt.Println("Ordenado: ", resultado)
}