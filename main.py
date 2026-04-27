import time
from utils import gerar_dados, esta_ordenado
from heap_max import heap_sort as heap_max
from heap_min import heap_sort as heap_min
from heap_d import heap_sort as heap_d
from smoothsort import smoothsort


def testar(nome, func, dados):
    arr = dados.copy()

    inicio = time.time()
    func(arr)
    fim = time.time()

    print(f"{nome}")
    print(f"Tempo: {fim - inicio:.6f}s")
    print(f"Ordenado: {esta_ordenado(arr)}")
    print("-"*30)


if __name__ == "__main__":

    dados = gerar_dados(10000, seed=42)

    testar("Heap Max", heap_max, dados)
    testar("Heap Min", heap_min, dados)
    testar("Heap D (d=3)", lambda arr: heap_d(arr, 3), dados)
    testar("Smoothsort", smoothsort, dados)
