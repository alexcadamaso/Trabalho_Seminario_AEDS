import random


def gerar_dados(n, seed=42):
    random.seed(seed)
    return [random.randint(0, 100000) for _ in range(n)]


def esta_ordenado(arr):
    return all(arr[i] <= arr[i+1] for i in range(len(arr)-1))
