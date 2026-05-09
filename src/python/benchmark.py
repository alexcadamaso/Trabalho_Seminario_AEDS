import os
import time
import tracemalloc
import csv

from utils import ler_arquivo, esta_ordenado

from heap_max import heap_sort as heap_max
from heap_min import heap_sort as heap_min
from heap_d import heap_sort as heap_d
from smoothsort import smoothsort


algoritmos = {
    "HeapMax": heap_max,
    "HeapMin": heap_min,
    "HeapD": lambda arr: heap_d(arr, 3),
    "Smoothsort": smoothsort
}


arquivos = {
    "crescente": "../../data/crescente/ordenado.dat",
    "decrescente": "../../data/decrescente/invertido.dat",
    "desordenada": "../../data/desordenada/aleatorio.dat"
}


os.makedirs("../../results", exist_ok=True)


with open("../../results/resultados.csv", "w", newline="") as csvfile:

    writer = csv.writer(csvfile)

    writer.writerow([
        "algoritmo",
        "tipo_entrada",
        "tempo",
        "memoria_kb",
        "ordenado"
    ])

    for tipo, caminho in arquivos.items():

        dados = ler_arquivo(caminho)

        for nome, algoritmo in algoritmos.items():

            arr = dados.copy()

            tracemalloc.start()

            inicio = time.perf_counter()

            algoritmo(arr)

            fim = time.perf_counter()

            memoria_atual, memoria_pico = tracemalloc.get_traced_memory()

            tracemalloc.stop()

            ordenado = esta_ordenado(arr)

            writer.writerow([
                nome,
                tipo,
                fim - inicio,
                memoria_pico / 1024,
                ordenado
            ])

            print(
                f"{nome} | {tipo} | "
                f"Tempo: {fim - inicio:.6f}s | "
                f"Memória: {memoria_pico / 1024:.2f} KB | "
                f"Ordenado: {ordenado}"
            )