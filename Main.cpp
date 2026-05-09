#include "Heap.hpp"
#include "D_Heap.hpp"
#include "Smoothsort.hpp"
#include <iostream>
#include <vector>
#include <chrono>
#include <fstream>
#include <string>
#include <iomanip>
#include <sys/resource.h>

using namespace std;
using namespace chrono;

long MemoriaGasta(){
    struct rusage uso;
    getrusage(RUSAGE_SELF, &uso);
    return uso.ru_maxrss;
}

vector<int> LerArquivo(string nome){
    vector<int> valores;
    ifstream arquivo(nome);
    int num;
    while(arquivo >> num){
        valores.push_back(num);
    }
    arquivo.close();
    return valores;
}

void RodarArquivo(ofstream& output, string caminho, string nome, Max_Min& heap_max, Max_Min& heap_min, Heap_d& heap3, Heap_d& heap4, Smoothsort& smooth){

    vector<int> original = LerArquivo(caminho);
    vector<int> copia = original;
    auto ini = high_resolution_clock::now();
    auto fim = high_resolution_clock::now();
    double tempo;

    tempo = 0;
    for(int i = 0; i < 3; i++){
        copia = original;
        ini = high_resolution_clock::now();
        heap_max.Heapsort(copia, copia.size());
        fim = high_resolution_clock::now();
        tempo += duration<double,milli>(fim - ini).count();
    }
    output << nome << ",HeapMax," << tempo / 3 << "," << MemoriaGasta() << "\n";

    tempo = 0;
    for(int i = 0; i < 3; i++){
        copia = original;
        ini = high_resolution_clock::now();
        heap_min.Heapsort(copia, copia.size());
        fim = high_resolution_clock::now();
        tempo += duration<double,milli>(fim - ini).count();
    }
    output << nome << ",HeapMin," << tempo / 3 << "," << MemoriaGasta() << "\n";

    tempo = 0;
    for(int i = 0; i < 3; i++){
        copia = original;
        ini = high_resolution_clock::now();
        heap3.Heapsort2(copia, copia.size());
        fim = high_resolution_clock::now();
        tempo += duration<double,milli>(fim - ini).count();
    }
    output << nome << ",DHeap3," << tempo / 3 << "," << MemoriaGasta() << "\n";

    tempo = 0;
    for(int i = 0; i < 3; i++){
        copia = original;
        ini = high_resolution_clock::now();
        heap4.Heapsort2(copia, copia.size());
        fim = high_resolution_clock::now();
        tempo += duration<double,milli>(fim - ini).count();
    }
    output << nome << ",DHeap4," << tempo / 3 << "," << MemoriaGasta() << "\n";

    tempo = 0;
    for(int i = 0; i < 3; i++){
        copia = original;
        ini = high_resolution_clock::now();
        smooth.sort(copia);
        fim = high_resolution_clock::now();
        tempo += duration<double,milli>(fim - ini).count();
    }
    output << nome << ",Smoothsort," << tempo / 3 << "," << MemoriaGasta() << "\n";


    output.flush();
}

int main(){

    ofstream output("output.dat");
    output << fixed << setprecision(6);
    output << "arquivo, algoritmo, tempo_ms, memoria_kb\n\n\n";

    Max_Min heap_max(true);
    Max_Min heap_min(false);
    Heap_d heap3(true,3);
    Heap_d heap4(true,4);
    Smoothsort smooth;

    RodarArquivo(output, "data/ordenado.dat",  "ordenado.dat", heap_max, heap_min, heap3,heap4, smooth);
    RodarArquivo(output, "data/invertido.dat", "invertido.dat",heap_max, heap_min, heap3,heap4, smooth);
    RodarArquivo(output, "data/aleatorio.dat", "aleatorio.dat",heap_max, heap_min, heap3,heap4, smooth);

    output.close();

    return 0;
}