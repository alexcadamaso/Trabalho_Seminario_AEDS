def heapify(arr, n, i):
    menor = i
    esq = 2*i + 1
    dir = 2*i + 2

    if esq < n and arr[esq] < arr[menor]:
        menor = esq

    if dir < n and arr[dir] < arr[menor]:
        menor = dir

    if menor != i:
        arr[i], arr[menor] = arr[menor], arr[i]
        heapify(arr, n, menor)


def heap_sort(arr):
    n = len(arr)

    for i in range(n//2 - 1, -1, -1):
        heapify(arr, n, i)

    for i in range(n-1, 0, -1):
        arr[0], arr[i] = arr[i], arr[0]
        heapify(arr, i, 0)
