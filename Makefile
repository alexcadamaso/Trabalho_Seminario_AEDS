JC = javac
JVM = java
FLAGS = -d bin
SRC = $(shell find java -name "*.java")
BIN = bin
MAIN = Main

build:
	mkdir -p $(BIN)
	$(JC) $(FLAGS) $(SRC)

# limpa, compila e executa tudo de uma vez
compilar: clear build run

compilar_fontes:
	mkdir -p $(BIN)
	$(JC) $(FLAGS) $(SRC)

run:
	$(JVM) -cp $(BIN) $(MAIN)

clear:
	rm -rf $(BIN)
	mkdir -p $(BIN)
	rm -f results/output.dat
	rm -f results/statistics.dat

.PHONY: compilar compilar_fontes run clear build