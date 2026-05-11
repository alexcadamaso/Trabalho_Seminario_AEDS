<div align="center">
  <h1>Algoritmos de Ordenação baseados em Estruturas de Dados</h1>
  <h3>HeapSort (Binário e D-ário) e SmoothSort</h3>
</div>

---

## 📗 Introdução

Este trabalho foi proposto pelo professor Michel Pires da Silva como 
seminário da disciplina de Algoritmos e Estruturas de Dados I do curso 
de Engenharia de Computação — Centro Federal de Educação Tecnológica de 
Minas Gerais — Campus V.

A ordenação de dados é um problema fundamental da Computação, ela está presente em diversas aplicações computacionais, como sistemas de buscas, processamento de informações, banco de dados e otimização de algoritmos.

Métodos de ordenação possuem grande importância devido ao impacto direto que exercem no desempenho de sistemas computacionais, especialmente em aplicações que manipulam grandes volumes de dados. Portanto, compreender o funcionamento, comportamento e eficiência desses algoritmos é essencial.

O objetivo é realizar uma análise comparativa aprofundada de algoritmos 
de ordenação baseados na estrutura de dados Heap, implementados em 
múltiplas linguagens de programação, avaliando métricas de tempo de 
execução, consumo de memória e número de movimentações sob diferentes 
padrões de entrada, permitindo a comparação dos algoritmos e a análise do impacto causado pelo ambiente e pela linguagem utilizada na execução.

---

## 📋 Métodos Desenvolvidos

| Algoritmo | Descrição |
| :--- | :--- |
| **HeapSort Max** | Utiliza uma heap máxima para organizar os elementos em ordem crescente |
| **HeapSort Min** | Utiliza uma heap mínima como estrutura principal de ordenação |
| **Heap D-ário** | Generaliza a heap binária tradicional, permitindo múltiplos filhos por nó |
| **Smoothsort** | Algoritmo adaptativo baseado em heaps de Leonardo |

---

## 📊 Análise Assintótica

| Algoritmo | Melhor Caso | Caso Médio | Pior Caso | Espaço |
| :---: | :---: | :---: | :---: | :---: |
| HeapSort Max | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | $O(1)$ |
| HeapSort Min | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | $O(1)$ |
| Heap D-ário | $O(n \log_d n)$ | $O(n \log_d n)$ | $O(n \log_d n)$ | $O(1)$ |
| Smoothsort | $O(n)$ | $O(n \log n)$ | $O(n \log n)$ | $O(1)$ |

---

## 🧬 Lógica dos Algoritmos

### HeapSort

O HeapSort opera em duas fases principais. Na primeira, o vetor é 
transformado em um max-heap (ou min-heap) válido por meio do procedimento 
**Build Heap**, que percorre os nós internos de baixo para cima aplicando 
o **Heapify** em cada um. Na segunda fase, o maior elemento (na raiz) é 
trocado com o último elemento do vetor, o tamanho do heap é reduzido e o 
Heapify é reaplicado na raiz. Esse processo se repete até que o vetor 
esteja completamente ordenado.
```
ALGORITMO HEAPSORT_MAX(A):
Entrada: A = vetor de n elementos
Saída:   A ordenado em ordem crescente
n ← tamanho de A

// Fase 1: Construção do heap máximo
Para i de ⌊n/2⌋ - 1 até 0 faça:
HEAPIFY_MAX(A, n, i)

// Fase 2: Extração dos elementos
Para i de n - 1 até 1 faça:
Trocar A[0] com A[i]
HEAPIFY_MAX(A, i, 0)
Fim

PROCEDIMENTO HEAPIFY_MAX(A, n, i):
maior ← i
esquerda ← 2i + 1
direita  ← 2i + 2
Se esquerda < n e A[esquerda] > A[maior]:
maior ← esquerda
Se direita < n e A[direita] > A[maior]:
maior ← direita
Se maior ≠ i:
Trocar A[i] com A[maior]
HEAPIFY_MAX(A, n, maior)
Fim
```

### Heap D-ário

Generaliza o HeapSort binário permitindo que cada nó tenha até **d** 
filhos. Os filhos do nó `i` ocupam as posições `d*i+1` até `d*i+d`, e o 
pai do nó `i` está em `⌊(i-1)/d⌋`. Aumentar `d` reduz a altura da árvore 
(`log_d n`), diminuindo o número de trocas, porém aumenta as comparações 
por nível.
```
ALGORITMO HEAPSORT_D(A, d):
Entrada: A = vetor de n elementos, d = número de filhos por nó
Saída:   A ordenado
n ← tamanho de A

// Fase 1: Construção do heap d-ário
Para i de ⌊n/d⌋ até 0 faça:
HEAPIFY_D(A, n, i, d)

// Fase 2: Extração
Para i de n - 1 até 1 faça:
Trocar A[0] com A[i]
HEAPIFY_D(A, i, 0, d)
Fim

PROCEDIMENTO HEAPIFY_D(A, n, i, d):
maior ← i
Para k de 1 até d faça:
filho ← d*i + k
Se filho < n e A[filho] > A[maior]:
maior ← filho
Se maior ≠ i:
Trocar A[i] com A[maior]
HEAPIFY_D(A, n, maior, d)
Fim
```

### Smoothsort

O Smoothsort, proposto por Dijkstra (1982), é um algoritmo adaptativo 
que utiliza uma **floresta de heaps de Leonardo**. Os tamanhos possíveis 
das árvores seguem a sequência de Leonardo:

$$L(n) = \begin{cases} 1 & \text{se } n = 0 \\ 1 & \text{se } n = 1 \\ L(n-1) + L(n-2) + 1 & \text{se } n > 1 \end{cases}$$

Gerando: $\langle 1, 1, 3, 5, 9, 15, 25, 41, 67, \dots \rangle$

O algoritmo opera em duas fases:

- **Build:** insere elementos progressivamente na floresta, fundindo 
  árvores adjacentes quando possível e mantendo as raízes em ordem 
  crescente da esquerda para a direita via **trinkle**.
- **Extração:** remove a raiz mais à direita, expõe as sub-árvores 
  filhas e restaura a ordem via **trinkle** em cada uma.

A adaptatividade vem do fato de que, em dados já ordenados, o trinkle 
nunca precisa fazer trocas, resultando em complexidade $O(n)$.
```
ALGORITMO SMOOTHSORT(A):
Entrada: A = vetor de n elementos
Saída:   A ordenado
Inicialização:
Construir uma sequência de heaps de Leonardo

// Fase 1: Construção
Para cada elemento de A faça:
Inserir elemento na estrutura de heap
Manter propriedade de heap via SIFT
Ordenar raízes via TRINKLE

// Fase 2: Ordenação
Enquanto existirem heaps faça:
Remover o maior elemento (raiz mais à direita)
Expor sub-árvores filhas
Restaurar ordem via TRINKLE
Fim
```
---

## 📂 Organização do Repositório
```
Trabalho_Seminario_AEDS/
├── C/
│   ├── build/
│   ├── include/
│   │   ├── geral.h
│   │   ├── heap_d.h
│   │   ├── heap_max.h
│   │   ├── heap_min.h
│   │   └── smoothsort.h
│   ├── src/
│   │   ├── geral.c
│   │   ├── heap_d.c
│   │   ├── heap_max.c
│   │   ├── heap_min.c
│   │   └── smoothsort.c
│   ├── main.c
│   └── Makefile
├── C++/
│   ├── include/
│   │   ├── D_Heap.hpp
│   │   ├── GerenciadorArquivos.hpp
│   │   └── Smoothsort.hpp
│   ├── results/
│   │   ├── output.dat
│   │   └── statistics.dat
│   ├── src/
│   │   ├── D_Heap.cpp
│   │   ├── GerenciadorArquivos.cpp
│   │   ├── Main.cpp
│   │   └── Smoothsort.cpp
│   ├── .gitignore
│   └── Makefile
├── GO/
│   └── ...
├── JAVA/
│   ├── java/
│   │   ├── algorithms/
│   │   │   ├── heap/
│   │   │   │   ├── DHeapMax.java
│   │   │   │   └── DHeapMin.java
│   │   │   └── smoothsort/
│   │   │       └── SmoothSort.java
│   │   └── file/
│   │       └── FileManager.java
│   ├── resultsJava/
│   │   ├── output.dat
│   │   └── statistics.dat
│   ├── .gitignore
│   ├── Main.java
│   └── Makefile
├── PYTHON/
│   └── ...
└── data/                         # Arquivos de entrada compartilhados
    ├── aleatorio.dat
    ├── ordenado.dat
    └── invertido.dat

```

---

## 🗃️ Conjunto de Dados

Os experimentos utilizam três arquivos de entrada:
- **aleatorio.dat** — 1.000.000 de números aleatórios (seed fixa = 42)
- **ordenado.dat** — 1.000.000 de números em ordem crescente
- **invertido.dat** — 1.000.000 de números em ordem decrescente

Os testes foram executados com tamanhos de entrada: 
$10^2$, $10^3$, $10^4$, $10^5$ e $10^6$ elementos.

---

## ⚙️ Compilação e Execução

### ✅ Pré-requisitos

> [!NOTE]
> Para garantir o funcionamento correto dos comandos do **Makefile**, 
> é recomendado o uso de uma distribuição Linux ou do WSL 
> (Windows Subsystem for Linux).

**Instalar dependências:**
```bash
sudo apt update
sudo apt install build-essential default-jdk golang-go python3
```

Clone o repositório :
```bash
git clone https://github.com/Trabalho_Seminario_AEDS.git
cd Trabalho_Seminario_AEDS
```

### 🛠️ Java
Comandos disponíveis:
```bash
cd java
make compilar   # limpa + compila + executa (tudo de uma vez)
make build      # só compila
make run        # só executa
make clear      # limpa bin e results
```

### 🛠️ C
Comandos disponíveis:
```bash
cd C
make            # compila
make run        # executa
make clean      # remove arquivos compilados
```

### 🛠️ C++

```bash
cd C++
make            # compila
make run        # executa
make clean      # remove arquivos compilados
```

### 🛠️ Go
```bash
cd GO
go run main.go
```

### 🛠️ Python
```bash
cd PYTHON
python3 benchmark.py
```

---

## 📊 Resultados Experimentais

Este repositório contém a implementação e análise comparativa de algoritmos de ordenação e estruturas de dados em **C, C++, Java, Python e Go**. O foco da pesquisa foi avaliar o comportamento do **Smoothsort** em comparação ao **D-Heap Max**.

---

## 📈 Resultados Experimentais ($N = 10^6$)

A tabela abaixo apresenta os tempos médios de execução no cenário de **Entradas Aleatórias**, evidenciando a performance de cada ambiente.

| Linguagem | Algoritmo Líder | Tempo Médio | Observação |
| :--- | :--- | :--- | :--- |
| **C** | Smoothsort | **0.042s** | Alta eficiência com manipulação direta de memória. |
| **Java** | D-Heap-Max ($d=4$) | **0.263s** | Otimização JIT superou o binário nativo do C++. |
| **C++** | D-Heap-Max ($d=4$) | **0.633s** | Desempenho sólido, mas sensível ao hardware utilizado. |
| **Go** | *Preencher aqui* | *Preencher* | Compilação estática e gerenciamento de memória eficiente. |
| **Python** | Timsort (Nativo) | **~0.28s** | Desempenho atrelado a implementações internas em C. |

---

## 🔍 Análise Crítica dos Resultados

### 1. Superioridade Adaptativa do Smoothsort
Os testes confirmaram a propriedade teórica de **melhor caso $O(n)$**. Em entradas já ordenadas, o Smoothsort apresentou um ganho de performance massivo:
* **Java:** Fator de aceleração (**Speedup**) de **24,2x** sobre o D-Heap.
* **C++:** Conclusão em apenas **56ms**, contra 525ms das outras estruturas.
* **C:** Desempenho amplamente superior, atingindo a marca de **0.042s**.

### 2. O Paradoxo Java vs. C++
Observou-se que o Java (263ms) superou o C++ (633ms) em entradas aleatórias. Esta disparidade é atribuída a dois fatores:
* **Otimização JIT:** O compilador *Just-In-Time* da JVM otimizou trechos críticos de código durante a execução.
* **Variabilidade de Hardware:** Como os testes foram conduzidos em máquinas distintas, os resultados refletem tanto a eficiência do software quanto a potência do hardware local.

### 3. Implementação em Python e Viés do Timsort
A análise em Python revelou que o uso do `sort()` nativo (Timsort) não representa uma comparação fiel ao Smoothsort acadêmico, visto que o Timsort é um algoritmo híbrido altamente otimizado em C, funcionando como um *benchmark* do ambiente e não do algoritmo estudado.

### 4. Eficiência de Memória
Todos os algoritmos validaram a complexidade espacial **$O(n)$**.
* **Java:** Registrou **0 KB** de memória dinâmica para $N \geq 10^3$, confirmando a natureza *in-place*.
* **C++:** Manteve o padrão, com variações mínimas de apenas $d \times 4$ bytes entre as versões de D-Heap.

### 5. Python e a Eficiência Nativa
Os resultados de Python revelam um contraste massivo entre os algoritmos implementados manualmente e as funções nativas da linguagem:
* **Domínio do Timsort:** O Smoothsort em Python (via `sort()`) foi cerca de **135 vezes mais rápido** que o D-Heap manual para $N=10^6$. 
* **Custo da Interpretação:** Enquanto o D-Heap manual levou quase **40 segundos** para processar um milhão de elementos, a função nativa resolveu em menos de **0,3 segundos**. Isso reforça sua análise crítica de que bibliotecas padrão otimizadas em C superam drasticamente códigos de alto nível em Python para processamento intenso de dados.

---

## 🛠️ Metodologia e Limitações
* **Amostragem:** Java (20 repetições), C++ (5 repetições), Python (7 repetições) e C e Go (3 repetições).
* **Hardware:** Os experimentos foram realizados em diferentes máquinas, o que impede uma comparação direta de "qual linguagem é melhor", mas permite analisar como cada algoritmo escala dentro de seu próprio ecossistema.

---

## 👩🏽‍💻 Ambientes de Desenvolvimento

| Linguagem | Hardware | SO | Compilador |
| :--- | :--- | :--- | :--- |
| C e Go | Intel Core i5-12450H, 8GB RAM | Ubuntu 24.04 (WSL) | gcc 13.3.0 / go1.22.2 |
| C++ | Intel Core i5-7200U, 8GB RAM | Ubuntu 24.04 (WSL) | g++ 13.3.0 (C++17) |
| Java | Intel Core i7-8550U, 8GB RAM | Ubuntu 20.04 (WSL) | javac 17.0.15 / OpenJDK 17 |
| Python | Intel Core i5-11400H, 8GB RAM | Ubuntu 24.04 | Python 3.12.3 |

---

## 🔗 Referências

- CORMEN, T. H. et al. **Algoritmos: Teoria e Prática**. 3. ed. Rio de Janeiro: Elsevier, 2012.
- DIJKSTRA, E. W. **Smoothsort, an alternative for sorting in situ**. Science of Computer Programming, v. 1, n. 3, p. 223–233, 1982.
- JOHNSON, D. B. **Priority queues with update and finding minimum spanning trees**. Information Processing Letters, v. 4, n. 3, p. 53–57, 1975.
- TARJAN, R. E. **Data Structures and Network Algorithms**. SIAM, 1983.

---

## 🫱🏽‍🫲🏽 Equipe

| Nome | Linguagem |
| :--- | :--- |
| Alexandre Dâmaso | Python e Go |
| Julya Pires | C++ e Go |
| Mariana Kaori Yano | C e Go |
| Rita Mariê Amaral Siqueira | Java e Go |

Agradecemos ao professor Michel Pires da Silva pela orientação e 
disponibilidade ao longo do desenvolvimento do trabalho.

---

## 📧 Contato

**Alexandre Dâmaso**
- Email: alexcadamaso@gmail.com

**Julya Pires**
- Email: julyapires500@gmail.com

**Mariana Kaori Yano**
- Email: marisxzh@gmail.com

**Rita Mariê Amaral Siqueira**
- Email: ritamariecajuru@gmail.com










