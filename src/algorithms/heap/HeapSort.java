package heapsort;
import heap.Heap;
import java.util.Comparator;

public class HeapSort {
    public static void heapSort(int[] vetorOrdenar, int n, Comparator<Integer> tipoHeap){
        Heap.buildHeap(vetorOrdenar, n,tipoHeap);

        for(int i=n - 1;i>0;i--){
            Heap.swap(vetorOrdenar, 0 , i);
            Heap.heapify(vetorOrdenar, i , 0, tipoHeap);
        }
    } 
}