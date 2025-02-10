# Teste Técnico Java - Gerenciamento de Funcionários

Este projeto é uma solução para um teste técnico que envolve a manipulação e gerenciamento de informações de funcionários de uma indústria. Nele, são implementadas as classes e funcionalidades descritas nos requisitos.

## Descrição

A aplicação é composta por:

- **Pessoa:** Classe base com atributos nome (String) e data de nascimento (LocalDate).
- **Funcionário:** Estende Pessoa, adicionando os atributos salário (BigDecimal) e função (String).
- **Principal:** Classe que  executa as seguintes ações:
  - Inserir os funcionários conforme a tabela de dados informada.
  - Remover o funcionário “João” da lista.
  - Imprimir os funcionários com data formatada no padrão dd/MM/yyyy e salário formatado com separador de milhar (.) e decimal (,).
  - Atualizar o salário de todos os funcionários com um aumento de 10%.
  - Agrupar os funcionários por função e exibir cada grupo.
  - Exibir os funcionários que fazem aniversário nos meses 10 e 12.
  - Exibir o funcionário com a maior idade (nome e idade).
  - Imprimir a lista de funcionários em ordem alfabética.
  - Calcular e imprimir o total dos salários.
  - Exibir quantos salários mínimos (R$1212,00) cada funcionário recebe.

## Pré-requisitos

- **Java JDK:** Certifique-se de ter o JDK instalado (versão 8 ou superior).  
  Verifique com:  
  `javac -version`

- **Visual Studio Code:** Utilizando o VS Code, recomendamos a instalação do _Java Extension Pack_ para suporte completo à linguagem.



## Como Rodar a Aplicação

### Usando o Visual Studio Code

1. **Abrir o Projeto:**
   - Abra o VS Code e vá em **Arquivo > Abrir Pasta** para selecionar a pasta do projeto.

2. **Executar Pelo Code Lens:**
   - Abra o arquivo `Principal.java` que esta em JOBCONNECT/src/com/inovaCode/services e aperte F5 para rodar no terminal do vsCode.



## Considerações

- **Formatação:**  
  As datas são formatadas no padrão `dd/MM/yyyy` e os valores numéricos são exibidos com o separador de milhar como ponto e o decimal como vírgula, conforme exigido.


