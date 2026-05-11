def ler_arquivo(caminho):
    with open(caminho,'r') as f:
        return [int(linha.strip()) for linha in f]
    
def esta_ordenado(arr):
    return all(arr[i] <= arr[i+1] for i in range(len(arr)-1))
