#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "include/geral.h"

#include "include/heap_max.h"
#include "include/heap_min.h"
#include "include/heap_d.h"
#include "include/smoothsort.h"

#include <sys/resource.h> // getrusage

long get_mem_usage() {
    struct rusage usage;
    getrusage(RUSAGE_SELF, &usage);
    // retorna em kilobytes (KB)
    return usage.ru_maxrss; 
}


void executar(int lista_original[], int n, FILE *fp) {
    // array temporário para as cópias
    int *lista_teste = (int*)malloc(n * sizeof(int));
    clock_t inicio, fim;
    double tempo;
    long mem_antes, mem_depois, mem_usada;

    printf("\n       RESULTADOS (N = %d)       \n\n", n);
    printf("%-20s | %-15s| %-15s\n", "Algoritmo", "Tempo (s)", "Memória (kb)");
    printf("-----------------------------------------------------------\n");


    fprintf(fp, "%-20s | %-15s| %-15s\n", "Algoritmo", "Tempo (s)", "Memória (kb)");


    //HeapSort Max
    
    mem_antes = get_mem_usage();
    copiar_lista(lista_teste, lista_original, n);

    
    inicio = clock();
    
    heapSortMax(lista_teste, n);
    mem_depois = get_mem_usage();
    
    fim = clock();
    mem_usada = mem_depois - mem_antes;

    tempo = (double)(fim - inicio) / CLOCKS_PER_SEC;
    printf("%-20s | %-15f | %-15ld\n", "HeapSort Max", tempo, mem_usada);
    fprintf(fp, "%-20s | %-15f| %-15ld\n", "HeapSort Max", tempo, mem_usada);



    //HeapSort Min
    
    mem_antes = get_mem_usage();
    copiar_lista(lista_teste, lista_original, n);
    
    inicio = clock();
    
    heapSortMin(lista_teste, n);
    
    fim = clock();
    mem_depois = get_mem_usage();
    
    tempo = (double)(fim - inicio) / CLOCKS_PER_SEC;
    mem_usada = mem_depois - mem_antes;

    printf("%-20s | %-15f | %-15ld\n", "HeapSort Min", tempo, mem_usada);
    fprintf(fp, "%-20s | %-15f| %-15ld\n", "HeapSort Min", tempo, mem_usada);




    //Heap D-ário (D=3)
    
    mem_antes = get_mem_usage();
    copiar_lista(lista_teste, lista_original, n);
    
    inicio = clock();
    
    heapSortD(lista_teste, n, 3); 
    
    fim = clock();
    mem_depois = get_mem_usage();
    
    tempo = (double)(fim - inicio) / CLOCKS_PER_SEC;
    mem_usada = mem_depois - mem_antes;

    printf("%-20s | %-15f | %-15ld\n", "Heap D-ario (D=3)", tempo, mem_usada);
    fprintf(fp, "%-20s | %-15f| %-15ld\n", "Heap D-ario (D=3)", tempo, mem_usada);



    
    //Smoothsort
    mem_antes = get_mem_usage();
    copiar_lista(lista_teste, lista_original, n);
    
    inicio = clock();
    
    smoothSort(lista_teste, n);
    
    fim = clock();
    mem_depois = get_mem_usage();
    
    tempo = (double)(fim - inicio) / CLOCKS_PER_SEC;
    mem_usada = mem_depois - mem_antes;

    printf("%-20s | %-15f | %-15ld\n", "Smoothsort", tempo, mem_usada);
    fprintf(fp, "%-20s | %-15f| %-15ld\n", "Smoothsort", tempo, mem_usada);

    printf("-----------------------------------------------------------\n");

    free(lista_teste);
}


int main() {

    int n; 

    FILE *fp = fopen("results.txt", "w");

    if (fp == NULL) {
        printf("Erro ao criar o arquivo de resultados!\n");
        return 1;
    }


    fprintf(fp, "Resultados\n\n");
    
    printf("\n\nNúmeros aleatórios\n");
    fprintf(fp, "Cenário: Números Aleatórios\n"); 
    

    int* listaA = ler_arquivo("../data/aleatorio.txt", &n);

    if (listaA == NULL) {
        printf("Falha ao carregar dados.\n");
        return 1;
    }

    executar(listaA, n, fp);
    free(listaA);



    printf("\n\nNúmeros invertidos\n");
    fprintf(fp, "\nCenário: Números Invertidos\n");

    int* listaI = ler_arquivo("../data/invertido.txt", &n);

    if (listaI == NULL) {
        printf("Falha ao carregar dados.\n");
        return 1;
    }

    executar(listaI, n, fp);
    free(listaI);


    printf("\n\nNúmeros ordenados\n");
    fprintf(fp, "\nCenário: Números Ordenados\n");


    int* listaO = ler_arquivo("../data/ordenado.txt", &n);

    if (listaO == NULL) {
        printf("Falha ao carregar dados.\n");
        return 1;
    }

    executar(listaO, n, fp);
    free(listaO);


    fclose(fp);


    return 0;
}

