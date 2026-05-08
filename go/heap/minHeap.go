<<<<<<< HEAD
package heap
=======
package heap

func MinHeapify(valores []int,tamanho int,i int){
	
    esquerda := 2 * i + 1
    direita := 2 * i + 2
    melhor := i

	if esquerda < tamanho && valores[esquerda] < valores[melhor] {
        melhor = esquerda
    }
    if direita < tamanho && valores[direita] < valores[melhor] {
        melhor = direita
    }
    if melhor != i {
        valores[melhor],valores[i] = valores[i],valores[melhor]
        MinHeapify(valores,tamanho,melhor)
    }

}
func BuildMinHeap(valores []int,tamanho int){
    for i := tamanho/2 - 1; i >= 0; i-- {
        MinHeapify(valores,tamanho,i)
    }
}

func Heapsort(valores []int,tamanho int){

    BuildMinHeap(valores,tamanho)

    for tamanho > 1 {
    
       ultimo := tamanho - 1
      
       valores[0],valores[ultimo] = valores[ultimo],valores[0]
       tamanho--
       MinHeapify(valores,tamanho,0)
      
    }
 
}
>>>>>>> aed509d (implementa Heapsort(min) em Go)
