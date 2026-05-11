package heap

// heapSortDario implementa a ordenação usando um heap com D filhos
func HeapSortDario(dados []int, n int, d int) []int {
	if n < 2 {
		return dados
	}

	// contrução da heap
	for i := (n - 2) / d; i >= 0; i-- {
		siftDownDario(dados, i, n, d)
	}

	// ordenação final
	for i := n - 1; i > 0; i-- {
		dados[0], dados[i] = dados[i], dados[0]
		siftDownDario(dados, 0, i, d)
	}

	return dados
}

// função que mantém a propriedade do heap para um nó específico
func siftDownDario(dados []int, raiz, fim, d int) {
	
	// o loop continua enquanto a raiz tiver pelo menos um filho
	for {

		// calcula o índice do filho mais à esquerda
		filhoEsquerda := d*raiz + 1
		if filhoEsquerda >= fim {
			break
		}

		maiorFilho := filhoEsquerda
		// encontra o maior entre os D filhos
		for i := 2; i <= d; i++ {
			proximoFilho := d*raiz + i
			if proximoFilho < fim && dados[proximoFilho] > dados[maiorFilho] {
				maiorFilho = proximoFilho
			}
		}

		// compara o maior filho com a raiz
		if dados[maiorFilho] > dados[raiz] {
			dados[raiz], dados[maiorFilho] = dados[maiorFilho], dados[raiz]
			// se a raiz foi trocada, continua descendo para garantir a propriedade do heap
			raiz = maiorFilho
		} else {
			break
		}
	}
}
