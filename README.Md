# Trabalho de Algoritmos de Ordenação — Heap Sort e Variações

## Integrante
Alexandre Dâmaso

## Descrição

Este repositório contém as implementações e análises experimentais desenvolvidas para o trabalho da disciplina de Algoritmos e Estruturas de Dados, com foco em algoritmos de ordenação baseados em heaps.

Os algoritmos implementados foram:

- Heap Sort Máximo (Max Heap)
- Heap Sort Mínimo (Min Heap)
- Heap Sort d-ário
- Smoothsort

O objetivo do trabalho é comparar o comportamento dos algoritmos em diferentes cenários de entrada, avaliando:

- Tempo de execução
- Consumo de memória
- Escalabilidade
- Sensibilidade à ordenação prévia dos dados


# Estrutura do Projeto

```text
TRABALHO_SEMINÁRIO
│
├── data
│   ├── crescente
│   ├── decrescente
│   └── desordenada
│
├── docs
│   ├── artigo
│   ├── slides
│   └── informações
│
├── results
│   ├── tabelas
│   └── graficos
│
├── src
│   └── python
│       ├── benchmark.py
│       ├── heap_max.py
│       ├── heap_min.py
│       ├── heap_d.py
│       ├── smoothsort.py
│       └── utils.py
│
└── README.md
````

# Linguagem Utilizada

* Python 3


# Ambiente de Execução

* Linux Ubuntu
* VS Code


# Entradas Utilizadas

Foram utilizados arquivos contendo diferentes tipos de entrada:

* Crescente
* Decrescente
* Desordenada

E os seguintes tamanhos:

* 10²
* 10³
* 10⁴
* 10⁵
* 10⁶

Todas as execuções utilizaram os mesmos arquivos de entrada para garantir justiça experimental entre os algoritmos.

---

# Execução

Para executar os testes:

```bash
python3 benchmark.py
```


# Métricas Avaliadas

Durante os experimentos foram coletadas as seguintes métricas:

* Tempo de execução
* Consumo de memória
* Verificação de ordenação correta
* Impacto do tipo de entrada
* Escalabilidade dos algoritmos


# Observações

O benchmark foi desenvolvido para:

* Ler automaticamente os arquivos de entrada
* Executar todos os algoritmos
* Verificar se a saída está ordenada corretamente
* Coletar métricas experimentais


# Resultados Gerais

De forma geral:

* O Smoothsort apresentou excelente desempenho em entradas parcialmente ordenadas.
* O Heap Sort tradicional apresentou comportamento consistente em diferentes cenários.
* O Heap d-ário apresentou maior overhead computacional.
* O tipo de entrada influenciou diretamente o desempenho dos algoritmos.


# Repositório

Este repositório foi utilizado como suporte para:

* organização do código;
* versionamento;
* documentação;
* armazenamento dos resultados experimentais.


# Disciplina

Algoritmos e Estruturas de Dados - CEFET-MG
