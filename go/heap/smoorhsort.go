package heap

func Smoothsort(arr []int) {

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
