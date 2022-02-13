/*
$ Nome: Pedro Lucas Medina Pereira
        Darla Garcia
$ Engenharia de computação - IFTM
$ POOV
 */
package trab1_ex3;
import java.util.Scanner;
import java.util.ArrayList;

public class Trab1_ex3 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<funcionario> listaFuncionarios = new ArrayList();
        funcionario f;

        int opc = 0;
        while (opc != 3) {
            System.out.print("\n MENU (Vetor dinâmico) ");
            System.out.print("\n 1- Inserir Funcionário ");
            System.out.print("\n 2- Mostrar Funcionarios ");
            System.out.print("\n 3- Sair \n ->");
            opc = entrada.nextInt();
            switch (opc) {
                case 1:
                    f = new funcionario();
                    System.out.print("\nDigite o Nome do funcionário: ");
                    f.nome = entrada.next();
                    System.out.print("\nDigite o CPF do funcionário: ");
                    f.CPF = entrada.next();
                    while (tamanhoString(f.CPF) < 11) {
                        System.out.print("\nCPF inválido");
                        System.out.print("\nDigite o CPF do funcionário novamente: ");
                        f.CPF = entrada.next();
                    }
                    System.out.print("\nDigite a Função do funcionário: ");
                    f.funcao = entrada.next();
                    System.out.print("\nDigite o Salário do funcionário: ");
                    f.salario = entrada.nextFloat();
                    while (salarioMaior(f.salario) == false) {
                        System.out.print("\nSalário inválido");
                        System.out.print("\nDigite o Salário do funcionário novamente: ");
                        f.salario = entrada.nextFloat();
                    }
                    f.salario = aumentoSalario(f.salario, f.funcao);

                    System.out.print("\n\n");
                    listaFuncionarios.add(f);

                    break;

                case 2:
                    for (int i = 0; i < listaFuncionarios.size(); i++) {
                        f = listaFuncionarios.get(i);
                        System.out.print("\nNome do [" + (i + 1) + "º] Funcionário -> " + f.nome);
                        System.out.print("\nCPF do [" + (i + 1) + "º] Funcionário -> " + f.CPF);
                        System.out.print("\nFunção do [" + (i + 1) + "º] Funcionário -> " + f.funcao);
                        System.out.print("\nSalário do [" + (i + 1) + "º] Funcionário -> " + f.salario);
                        System.out.print("\n\n");
                    }
                    break;

                case 3:
                    System.out.print("\nsaindo...");
                    entrada.close();
                    break;

                default:
                    System.out.print("Opção Inválida!");
                    break;
            }
        }

    }

    public static int tamanhoString(String tamanho) {
        int tam = tamanho.length();
        return tam;
    }

    public static boolean salarioMaior(float salario) {
        if (salario < (1100.00)) {
            return false;
        } else {
            return true;
        }
    }

    public static float aumentoSalario(float salario, String cargo) {
        if (("supervisor").equalsIgnoreCase(cargo)) {
            float salAumentado = (salario * 112) / 100;
            return salAumentado;
        } else {
            return salario;
        }
    }
}
