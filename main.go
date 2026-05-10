package main

import (
	"bufio"
	"fmt"
	"os"
	"runtime"
	"strconv"
	"time"
)

func smoothSort(arr []int) {

	n := len(arr)

	// Primeira etapa
	for i := 1; i < n; i++ {

		j := i

		for j > 0 && arr[j] < arr[j-1] {

			arr[j], arr[j-1] = arr[j-1], arr[j]

			j--
		}
	}

	// Segunda etapa
	for i := n - 1; i >= 0; i-- {

		maxIdx := 0

		for j := 1; j <= i; j++ {

			if arr[j] > arr[maxIdx] {

				maxIdx = j
			}
		}

		arr[i], arr[maxIdx] = arr[maxIdx], arr[i]
	}
}

func readFile(filename string) ([]int, error) {

	file, err := os.Open(filename)

	if err != nil {

		return nil, err
	}

	defer file.Close()

	var arr []int

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {

		num, err := strconv.Atoi(scanner.Text())

		if err != nil {

			continue
		}

		arr = append(arr, num)
	}

	return arr, nil
}

func isSorted(arr []int) bool {

	for i := 0; i < len(arr)-1; i++ {

		if arr[i] > arr[i+1] {

			return false
		}
	}

	return true
}

func main() {

	files := []string{

	"entradas/crescente/ordenado100.dat",
	"entradas/crescente/ordenado1000.dat",
	"entradas/crescente/ordenado10000.dat",

	"entradas/decrescente/invertido100.dat",
	"entradas/decrescente/invertido1000.dat",
	"entradas/decrescente/invertido10000.dat",

	"entradas/desordenada/aleatorio100.dat",
	"entradas/desordenada/aleatorio1000.dat",
	"entradas/desordenada/aleatorio10000.dat",
}

	for _, file := range files {

		arr, err := readFile(file)

		if err != nil {

			fmt.Println("Erro ao ler arquivo:", err)

			continue
		}

		var memBefore runtime.MemStats
		var memAfter runtime.MemStats

		runtime.ReadMemStats(&memBefore)

		start := time.Now()

		smoothSort(arr)

		elapsed := time.Since(start)

		runtime.ReadMemStats(&memAfter)

		memUsed := memAfter.Alloc - memBefore.Alloc

		fmt.Println("Arquivo:", file)

		fmt.Println("Tempo:", elapsed)

		fmt.Println("Memória:", memUsed, "bytes")

		fmt.Println("Ordenado corretamente?", isSorted(arr))

		fmt.Println("-----------------------------")
	}
}