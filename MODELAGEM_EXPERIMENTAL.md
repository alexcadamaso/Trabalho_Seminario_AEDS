# Modelagem Experimental
Professor: Michel Pires da Silva

AEDS I - CEFET-MG


## Ambiente de Execução
Os algoritmos foram desenvolvidos em C, utilizando a leitura de arquivos para a entrada dos números a serem ordenados(dados). Os experimentos foram executados no mesmo ambiente computacional, garantindo que as condições de testes sejam igualitárias.


### Estrutura do projeto
O algoritmo foi organizado em arquivos para facilitar a modularização e manutenção do código, sendo eles:
```
.
├── include/
│   ├── geral.h          # funções utilizadas na main
│   ├── heap_d.h         # funções utilizadas na elaboração do heapSortD
│   ├── heap_max.h       # funções utilizadas na elaboração do heapSortMax
│   ├── heap_min.h       # funções utilizadas na elaboração do heapSortMin
│   └── smoothsort.h     # funções utilizadas na elaboração do smoothSort
├── src/                 # códigos das respectivas funções citadas nos arquivos .h
│   ├── geral.c
│   ├── heap_d.c
│   ├── heap_max.c
│   ├── heap_min.c
│   └── smoothsort.c
├── main.c               # lógica principal
├── aleatorio.txt        # arquivo de entrada de números aleatórios
├── invertido.txt        # arquivo de entrada de números invertidos
├── ordenado.txt         # arquivo de entrada de números ordenados
└── Makefile             # compilação
```

### Bibliotecas utilizadas no projeto
Para o desenvolvimento do projeto, foram utilizadas as bibliotecas: 
|        Biblioteca        |              Função              |
|--------------------------|----------------------------------|
|`<stdio.h>`               | Entrada e saída de dados no terminal e em arquivos|
|`<stdlib.h>`              | Funções utilitárias e gerenciamento de memória dinâmica|
|`<time>`                  | Medir o tempo de execução dos algoritmos|
|`<string.h>`              | Manipulação de cadeias de caracteres e cópia de dados


## Conjunto de dados 

Foram utilizados três arquivos distintos de entrada, cada um contendo **1.000.000** de números inteiros
- Arquivo aleatório (`aleatorio.txt`)
- Arquivo invertido (`invertido.txt`)
- Arquivo ordenado (`ordenado.txt`)

Assim, permitindo a observação do comportamento dos algoritmos em casos médios, piores casos e entradas favoráveis.


## Métrica avaliada
A métrica utilizada para comparação foi o:
- Tempo de execução (em segundos)
O tempo corresponde ao intervalo necessário para que cada algoritmo realizasse a ordenação do vetor.


## Resultados

Para a validação do experimento, cada teste foi executado três vezes para cada entrada. Ao final, para a análise dos resultados, foi calculado a média dos tempos obtidos, reduzindo possíveis variações externas.

### Teste com números aleatórios

O resultado médio obtido foi: 
| Algoritmo            | Tempo (s) |
|----------------------|-----------|
| HeapSort Max         | 0.506940  |
| HeapSort Min         | 0.486467  |
| Heap D-ario (D=3)    | 0.462677  |
| Smoothsort           | 0.342882  |


### Teste com números invertidos

O resultado médio obtido foi: 
| Algoritmo            | Tempo (s) |
|----------------------|-----------|
| HeapSort Max         | 0.354249  |
| HeapSort Min         | 0.355516  |
| Heap D-ario (D=3)    | 0.302125  |
| Smoothsort           | 0.348687  |


### Teste com números ordenados

O resultado médio obtido foi: 
| Algoritmo            | Tempo (s) |
|----------------------|-----------|
| HeapSort Max         | 0.342154  |
| HeapSort Min         | 0.352361  |
| Heap D-ario (D=3)    | 0.343390  |
| Smoothsort           | 0.042706  |



### Análise dos resultados
Os resultados experimentais demonstram que diferentes estruturas de Heap apresentam comportamentos distintos dependendo da distribuição dos dados de entrada: 
- __Entrada aleatória__: Nesse conjunto de dados, o algoritmo Smoothsort apresentou o melhor desempenho. Por outro lado, o Heap D-ário também apresentou resultados satisfatórios, superando as implementações tradicionais do HeapSort Max e HeapSort Min.
- __Entrada invertida__: Para entradas invertidas, o Heap D-ário apresentou o melhor desempenho. O Smoothsort apresentou um resultado semelhante ao HeapSort Max, mostrando que sua principal vantagem não ocorre, necessariamente, em entradas invertidas.
- __Entrada ordenada__: No conjunto de dados ordenados, o Smoothsort apresentou um desempenho extremamente superior aos demais, executando em aproximadamente **0,038s**. Isso ocorre pois esse método de ordenação possui um algoritmo adaptativo, conseguindo aproveitar entradas ordenadas ou parciamente ordenadas para reduzir significamente seu custo computacional.

## Análise assintótica 
A análise assintótica tem como objeto avaliar o comportamento dos algoritmos à medida que o tamanho da entrada cresce. Assim permitindo a compreensão teórica do desempenho dos métodos de ordenação implementados e a comparação desses resultados com os dados obtidos experimentalmente.

### HeapSort Max
  | Operação              | Complexidade          | Explicação |
  |-----------------------|-----------------------| -----------|
  | Construção da heap    | $O(n)$                | A heap máxima é construída percorrendo os elementos do vetor e ajustando a estrutura heap.|
  | Remoção dos elementos | $$O(n \cdot \log n)$$ | Cada remoção exige uma reorganização da heap com custo $$O(log n)$$, repetida para todos os $$(n)$$ elementos. |

Portanto, o custo computacional é expressado por:
$$O(n \cdot \log n)$$.

### HeapSort Min
  | Operação              | Complexidade          | Explicação |
  |-----------------------|-----------------------| -----------|
  | Construção da heap    | $O(n)$                | A heap mínima é construída reorganizando os elementos do vetor.|
  | Remoção dos elementos | $$O(n \cdot \log n)$$ | Cada remoção do menor elemento exige a reorganização da heap com custo logarítmico. |

Portanto, o custo computacional é expressado por:
$$O(n \cdot \log n)$$.


### Heap D-ário (D = 3)
  | Operação              | Complexidade          | Explicação |
  |-----------------------|-----------------------| -----------|
  | Construção da heap    | $O(n)$                | A estrutura heap é construída reorganizando os elementos do vetor.|
  | Ajuste da heap        | $$O(\log₃ n)$$        | A altura reduzida da heap diminui a quantidade de níveis percorridos durante as operações de reorganização. |
  | Remoção dos elementos | $$O(n \cdot \log n)$$ | As operações de remoção continuam sendo realizadas para todos os elementos do vetor |

Portanto, o custo computacional é expressado por:
$$O(n \cdot \log n)$$.


### Smoothsort
  | Operação                          | Complexidade          | Explicação |
  |-----------------------------------|-----------------------| -----------|
  | Construção das heaps de Leonardo  | $O(n)$                | O algoritmo constrói heap especiais chamadas de heaps de Leonardo. |
  | Reorganização dos elementos       | $$O(n \cdot \log n)$$ | Em casos gerais, as operações de ajuste das heaps possuem comportamento semelhante ao HeapSort tradicional. |


Ademais, o custo computacional é expressado por:
  | Caso             | Complexidade          | Explicação |
  |------------------|-----------------------| -----------|
  | Melhor caso      | $O(n)$                | Quando os dados já estão ordenados, o algoritmo aproveita a organização prévia e reduz significamente o número de operações. |
  | Melhor caso      | $O(n \cdot \log n)$   | Em entradas desorganizadas, o comportamento aproxima-se do HeapSort tradicional. |


## Conclusão
Ante o exposto, a modelagem experimental permitiu comparar de forma prática os algoritmos de ordenação implementados no projeto, utilizando grandes quantidades de dados em diferentes cenários.
Assim, mostra-se que:
- Smoothsort é altamente eficiente para dados previamente ordenados.
- O Heap D-ário apresenta um ótimo desempenho geral.
- A distribuição dos dados influência diretamente na eficiência dos algoritmos.
- Algoritmos com a mesma complexidade assintótica podem apresentar diferentes desempenhos na prática.
Portanto, os experimentos confirmam a importância da análise experimental na avaliação de algoritmos de ordenação, complementando estudos teóricos sobre complexidade computacional.

## Como Compilar e Executar

### Compilação
```bash
make            # compila e gera o executável
```

### Execução
```bash
make run        # executa o programa utilizando o imput.dat 
```

### Limpar arquivos compilados
```bash
make clean      # remove os arquivos objetos e o executável
```

- Exibe resultados no terminal.
