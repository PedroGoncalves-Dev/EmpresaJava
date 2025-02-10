package com.inovaCode.services;


import com.inovaCode.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


public class Principal {
    public static void main(String[] args) {

        // Configuração de formatação para datas (dd/MM/yyyy)
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Configuração de formatação para números: separador de milhar com ponto e decimal com vírgula
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        // 3.1 – Inserir todos os funcionários em um Array
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9826.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 – Remover o funcionário "João" da lista
        funcionarios.removeIf(func -> func.getNome().equalsIgnoreCase("João"));

        // 3.3 – Imprimir todos os funcionários com informações formatadas
        System.out.println("Lista de Funcionários:");
        funcionarios.forEach(func -> {
            String dataFormatada = func.getDataNascimento().format(dateFormatter);
            String salarioFormatado = decimalFormat.format(func.getSalario());
            System.out.println("Nome: " + func.getNome() + 
                               " | Data de Nascimento: " + dataFormatada + 
                               " | Salário: " + salarioFormatado + 
                               " | Função: " + func.getFuncao());
        });

        // 3.4 – Aumentar o salário de cada funcionário em 10%
        funcionarios.forEach(func -> {
            BigDecimal novoSalario = func.getSalario().multiply(new BigDecimal("1.10"));
            novoSalario = novoSalario.setScale(2, RoundingMode.HALF_UP);
            func.setSalario(novoSalario);
        });
        System.out.println("\nApós aumento de 10% no salário:");
        funcionarios.forEach(func -> {
            String salarioFormatado = decimalFormat.format(func.getSalario());
            System.out.println("Nome: " + func.getNome() + " | Novo Salário: " + salarioFormatado);
        });

        // 3.5 – Agrupar os funcionários por função em um MAP (chave: função, valor: lista de funcionários)
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(func -> System.out.println("  - " + func.getNome()));
        });

        // 3.8 – Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\nFuncionários com aniversário nos meses 10 ou 12:");
        funcionarios.stream()
            .filter(func -> {
                int mes = func.getDataNascimento().getMonthValue();
                return mes == 10 || mes == 12;
            })
            .forEach(func -> System.out.println("Nome: " + func.getNome() + 
                                                 " | Data de Nascimento: " + func.getDataNascimento().format(dateFormatter)));

        // 3.9 – Imprimir o funcionário com a maior idade (mostrar nome e idade)
        Funcionario funcionarioMaisVelho = funcionarios.stream()
            .min(Comparator.comparing(Funcionario::getDataNascimento))
            .orElse(null);
        if (funcionarioMaisVelho != null) {
            int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("\nFuncionário com maior idade: " + funcionarioMaisVelho.getNome() + " (" + idade + " anos)");
        } else {
            System.out.println("\nNenhum funcionário para calcular a maior idade.");
        }

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionariosOrdenados.forEach(func -> System.out.println("Nome: " + func.getNome()));

        // 3.11 – Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + decimalFormat.format(totalSalarios));

        // 3.12 – Imprimir quantos salários mínimos cada funcionário ganha (salário mínimo = R$1212,00)
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        funcionarios.forEach(func -> {
            BigDecimal quantidade = func.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("Nome: " + func.getNome() + " | " + quantidade + " salários mínimos");
        });
    }
}
