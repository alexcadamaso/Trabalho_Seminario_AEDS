def heapify(arr, n, i, d):
    maior = i

    for k in range(1, d+1):
        filho = d*i + k
        if filho < n and arr[filho] > arr[maior]:
            maior = filho

    if maior != i:
        arr[i], arr[maior] = arr[maior], arr[i]
        heapify(arr, n, maior, d)


def heap_sort(arr, d=3):
    n = len(arr)

    for i in range(n//d, -1, -1):
        heapify(arr, n, i, d)

    for i in range(n-1, 0, -1):
        arr[0], arr[i] = arr[i], arr[0]
        heapify(arr, i, 0, d)
