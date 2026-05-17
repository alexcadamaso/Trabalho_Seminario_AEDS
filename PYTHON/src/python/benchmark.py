import os
import time
import tracemalloc
import csv
import psutil

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

            process = psutil.Process(os.getpid())

            mem_antes = process.memory_info().rss / 1024

            inicio = time.perf_counter()

            algoritmo(arr)

            fim = time.perf_counter()

            mem_depois = process.memory_info().rss / 1024

            memoria_usada = mem_depois

            ordenado = esta_ordenado(arr)

            writer.writerow([
                nome,
                tipo,
                fim - inicio,
                memoria_usada,
                ordenado
            ])

            print(
                f"{nome} | {tipo} | "
                f"Tempo: {fim - inicio:.6f}s | "
                f"Memória: {memoria_usada:.2f} KB | "
                f"Ordenado: {ordenado}"
            )