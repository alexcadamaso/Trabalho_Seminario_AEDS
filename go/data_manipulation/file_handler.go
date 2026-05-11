package data_manipulation
import "os"
import "fmt"
import "bufio"
import "strings"
import "strconv"

func LerDadosArquivo(caminho string, n int) []int{
	arquivo, erro := os.Open(caminho)

	if(erro != nil){
		fmt.Println("Erro ao ler arquivo dos dados para ordenação: ", erro)
		os.Exit(1)
	}
	defer arquivo.Close()

	if n > 1000000 || n <= 0{
		fmt.Println("Erro: o tamanho da entrada deve estar entre 1 e 1.000.000")
		os.Exit(1)
	}

	dados := make([]int, 0, n)
	scanner := bufio.NewScanner(arquivo)

	for scanner.Scan(){
		linha := strings.TrimSpace(scanner.Text())
		if linha == ""{
			continue
		} else {
			numero, erro := strconv.Atoi(linha)
			if erro == nil{
				dados = append(dados, numero)
			}
		}

		if len(dados) >= n {
			break
		}
	}

	if scanner.Err() != nil {
		fmt.Println("Aviso: a leitura parou antes do fim devido a um erro no arquivo.")
	}

	return dados
}