# Métodos de ordenação: Heapsorts e Smoothsort
Professor: Michel Pires da Silva

AEDS I - CEFET-MG


## Introdução

A ordenação de dados é um problema fundamental da Computação, ela está presente em diversas aplicações computacionais, como sistemas de buscas, processamento de informações, banco de dados e otimização de algoritmos.

Métodos de ordenação possuem grande importância devido ao impacto direto que exercem no desempenho de sistemas computacionais, especialmente em aplicações que manipulam grandes volumes de dados. Portanto, compreender o funcionamento, comportamento e eficiência desses algoritmos é essencial.

Neste projeto foram implementados diferentes métodos de ordenação utilizando múltiplas linguagens de programação, permitindo a comparação dos algoritmos e a análise do impacto causado pelo ambiente e pela linguagem utilizada na execução.

## Métodos Desenvolvidos 

Os algoritmos implementados pertencem à fámilia dos métodos de ordenação baseados em estruturas Heap. Os algoritmos desenvolvidos foram:

  | Algoritmo         | Descrição |
  |-------------------|--------------------------|
  | HeapSort Max      | Utiliza uma heap máxima para organizar os elementos em ordem crescente.|
  | HeapSort Min      | Utiliza uma heap mínima como estrutura principal de ordenação.|
  | Heap D-ário       | Generaliza a heap binária tradicional, permitindo múltiplos filhos por nó.|
  | Smoothsort        | Algoritmo adaptativo baseado em heaps de Leonardo.|
  
  
### Análise Assíntótica dos métodos de ordenação 

| Algoritmo      | Melhor Caso | Caso Médio  | Pior Caso  | Espaço  |
|----------------|-------------|-------------|------------|---------|
| HeapSort Max   | O(n log n)  | O(n log n)  | O(n log n) | O(1)    |
| HeapSort Min   | O(n log n)  | O(n log n)  | O(n log n) | O(1)    |
| Heap D-ário    | O(n log n)  | O(n log n)  | O(n log n) | O(1)    |
| Smoothsort     | O(n)        | O(n log n)  | O(n log n) | O(1)    |


## Estrutura do projeto

Este repositório contém o desenvolvimento dos diferentes métodos de ordenação em múltiplas linguagens de programação. Esses algoritmos foram desenvolvido e separados em pastas, portanto cada pasta possui os métodos de ordenação desenvolvidos nas respectivas linguaguens.
```
.
├── C/           # Algoritmo desenvolvidos na linguagem C
├── CPP/         # Algoritmo desenvolvidos na linguagem C++
├── GO/          # Algoritmo desenvolvidos na linguagem GO
├── JAVA/        # Algoritmo desenvolvidos na linguagem JAVA
├── PYTHON/      # Algoritmo desenvolvidos na linguagem PYTHON
└── DATA/        # Pasta com os diferentes arquivos de entrada
```


#### Conjunto de dados:

Os experimentos utilizam três arquivos de entrada contendo
- 1.000.000 de números aleatórios (aleatorio.txt);
- 1.000.000 de números ordenados (ordenado.txt);
- 1.000.000 de números invertidos (invertido.txt);

Ademais, foram executados testes com diferentes tamanhos de entrada (com os mesmos dados dos arquivos citados). Os tamanhos de entrada foram: 10², 10³, 10⁴, 10⁵, 10⁶ elementos.



## Como Compilar e Executar

### Pré-requesitos
- GCC
- G++
- JDK
- Python
- Go

### Compilação e Execução em C, C++ e Java
**Compilação**
```bash
make            # compila
```

**Execução**
```bash
make run        # executa o programa
```

**Limpar arquivos compilados**
```bash
make clean      # remove os arquivos objetos
```

### Compilação e Execução em Go

```bash
go run main.go
```


- Gera um arquivo contendo os resultados obtidos (tempo e memória gastos)

- Gera `output.dat` (evolução resumida) e `historico.dat` (debug completo).
- Exibe pontos e parâmetros no console.

