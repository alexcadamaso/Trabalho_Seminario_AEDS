package smoothsort;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SmoothSort {
    private SmoothSort(){}

    public static int[] numerosLeonardo(int n){
        if(n <= 0) throw new IllegalArgumentException("O valor de n deve ser maior que 0");

        int[] sequencia = new int[45];
        sequencia[0] = 1;

        if(n == 1) return sequencia;
        sequencia[1] = 1;

        int tamanhoFinal = 2;
        for(int i=2;i<n;i++){
            sequencia[i] = (sequencia[i-1] + sequencia[i-2] + 1);
            tamanhoFinal ++;
            if(sequencia[i] >= n){
                break;
            }
        }

        return Arrays.copyOf(sequencia, tamanhoFinal);
    }

    public static int[] arvoresFloresta(int[] numerosLeonardo, int n){
        List<Integer> arvores = new ArrayList<>();
        int sobra = n;

        for(int i=numerosLeonardo.length - 1;i>=0;i--){
            while(sobra >= numerosLeonardo[i]){
                arvores.add(numerosLeonardo[i]);
                sobra-=numerosLeonardo[i];
            }
        }

        int[] resultado = new int[arvores.size()];
        for(int i=0;i<resultado.length;i++){
            resultado[i] = arvores.get(i);
        }

        return resultado;
    }
}