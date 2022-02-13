/*
Nome: Pedro Lucas Medina Pereira
Disciplina: POOV
Data: 29/09/2021
 */
package principal;

import pessoa.*;
import animal.*;
import rh.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ArrayList<Pessoa> listaPessoas = new ArrayList();
        ArrayList<Animal> listaAnimais = new ArrayList();
        Scanner entrada = new Scanner(System.in);

        int opc1, opc2, opc3;
        opc1 = opc2 = opc3 = 0;
        int i = 0;
        int j = 0;
        double somarSalarios = 0;
        double mediaSalarios = 0;
        double maiorSalario = 0;
        double salario = 0;
        int flag = 0;
        int menorIdade = 0;
        int idade = 0;
        int pos = 0;
        String id = "";
        String cpf = "";
        String tel = "";

        while (opc1 != 15) {
            System.out.print("\n-----MENU----- ");
            System.out.print("\n________________");
            System.out.print("\n 1- Inserir (pessoa ou animal)");
            System.out.print("\n 2- Inserir (pessoa ou animal) em uma posição específica");
            System.out.print("\n 3- Mostrar um elemento (pelo CPF ou ID)");
            System.out.print("\n 4- Mostrar todos elementos");
            System.out.print("\n 5- Remover elemento (pelo CPF ou ID)");
            System.out.print("\n 6- Limpar os Arrays");
            System.out.print("\n 7- Verificar se o ArrayList esta vazio");
            System.out.print("\n 8- Mostrar quantas pessoas estão no campus");
            System.out.print("\n 9- Mostrar funcionário (professor ou administrativo) com maior salario");
            System.out.print("\n 10- Mostrar menor idade entre os animais");
            System.out.print("\n 11- Mostrar média salarial dos administrativos");
            System.out.print("\n 12- Mostrar dados dos administrativos por setor");
            System.out.print("\n 13- Mostrar idade média (RH)");
            System.out.print("\n 14- Método andar pelo campus");
            System.out.print("\n 15- Sair");
            System.out.print("\n Digite a opção desejada: ");
            opc1 = entrada.nextInt();
            while (opc1 < 1 || opc1 > 15) {
                System.out.print("\n ENTRADA INVÁLIDA. Digite novamente: ");
                opc1 = entrada.nextInt();
            }
            //OPCOES DO MENU PRINCIPAL
            switch (opc1) {
                case 1:
                case 2:
                    System.out.print("\n SUBMENU 1 - Inserção");
                    System.out.print("\n______________________");
                    System.out.print("\n 1- Pessoa");
                    System.out.print("\n 2- Animal");
                    System.out.print("\n Digite a opção desejada: ");
                    opc2 = entrada.nextInt();
                    while (opc2 != 1 && opc2 != 2) {
                        System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                        opc2 = entrada.nextInt();
                    }
                    if (opc2 == 1) {
                        System.out.print("\n SUBMENU 2 - Inserção");
                        System.out.print("\n______________________");
                        System.out.print("\n 1- Professor");
                        System.out.print("\n 2- Aluno");
                        System.out.print("\n 3- Administrativo");
                        System.out.print("\n Digite a opção desejada: ");
                        opc3 = entrada.nextInt();
                        while (opc3 < 1 || opc3 > 3) {
                            System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                            opc3 = entrada.nextInt();
                        }
                        if (opc1 == 2) {
                            System.out.print("\n Digite a posição desejada (0 a " + listaPessoas.size() + "): ");
                            pos = entrada.nextInt();
                            while (pos < 0 || pos > listaPessoas.size()) {
                                System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                                pos = entrada.nextInt();
                            }
                        } else {
                            pos = listaPessoas.size();
                        }
                        System.out.print("\n CPF: ");
                        cpf = entrada.next();
                        if (verificaCPF(cpf, listaPessoas)) {
                            System.out.print(" Telefone: ");
                            tel = entrada.next();
                            if (verificaTel(tel)) {
                                if (opc3 == 1) {
                                    Professor professor = new Professor();
                                    professor.setCpf(cpf);
                                    professor.setTelefone(tel);
                                    System.out.print(" Nome: ");
                                    professor.setNome(entrada.next());
                                    System.out.print(" Idade: ");
                                    professor.setIdade(entrada.nextInt());
                                    System.out.print(" Endereço: ");
                                    professor.setEndereco(entrada.next());
                                    System.out.print(" Matrícula: ");
                                    professor.setMatricula(entrada.next());
                                    System.out.print(" Salário: R$ ");
                                    professor.setSalario(entrada.nextDouble());
                                    listaPessoas.add(pos, professor);
                                }
                                if (opc3 == 2) {
                                    Aluno aluno = new Aluno();
                                    aluno.setCpf(cpf);
                                    aluno.setTelefone(tel);
                                    System.out.print(" Nome: ");
                                    aluno.setNome(entrada.next());
                                    System.out.print(" Idade: ");
                                    aluno.setIdade(entrada.nextInt());
                                    System.out.print(" Endereço: ");
                                    aluno.setEndereco(entrada.next());
                                    System.out.print(" RA: ");
                                    aluno.setRA(entrada.next());
                                    System.out.print(" Curso: ");
                                    aluno.setCurso(entrada.next());
                                    System.out.print(" Período: ");
                                    aluno.setPeriodo(entrada.nextInt());
                                    listaPessoas.add(pos, aluno);
                                }
                                if (opc3 == 3) {
                                    Administrativo adm = new Administrativo();
                                    adm.setCpf(cpf);
                                    adm.setTelefone(tel);
                                    System.out.print(" Nome: ");
                                    adm.setNome(entrada.next());
                                    System.out.print(" Idade: ");
                                    adm.setIdade(entrada.nextInt());
                                    System.out.print(" Endereço: ");
                                    adm.setEndereco(entrada.next());
                                    System.out.print(" Matrícula: ");
                                    adm.setMatricula(entrada.next());
                                    System.out.print(" Salário: R$ ");
                                    adm.setSalario(entrada.nextDouble());
                                    System.out.print(" Setor: ");
                                    adm.setSetor(entrada.next());
                                    listaPessoas.add(pos, adm);
                                }
                            } else {
                                System.out.print("\n Telefone inválido.\n");
                            }
                        } else {
                            System.out.print("\n CPF inválido.\n");
                        }
                    }
                    if (opc2 == 2) {
                        System.out.print("\n SUBMENU 2 - Inserção");
                        System.out.print("\n______________________");
                        System.out.print("\n 1- Equino");
                        System.out.print("\n 2- Suíno");
                        System.out.print("\n Digite a opção desejada: ");
                        opc3 = entrada.nextInt();
                        while (opc3 != 1 && opc3 != 2) {
                            System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                            opc3 = entrada.nextInt();
                        }
                        if (opc1 == 2) {
                            System.out.print("\n Digite a posição desejada (0 a " + listaAnimais.size() + "): ");
                            pos = entrada.nextInt();
                            while (pos < 0 || pos > listaAnimais.size()) {
                                System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                                pos = entrada.nextInt();
                            }
                        } else {
                            pos = listaAnimais.size();
                        }
                        System.out.print("\n Identificador: ");
                        id = entrada.next();
                        if (verificaID(id, listaAnimais)) {
                            if (opc3 == 1) {
                                Equino equino = new Equino();
                                equino.setIdentificador(id);
                                System.out.print(" Espécie: ");
                                equino.setEspecie(entrada.next());
                                System.out.print(" Idade: ");
                                equino.setIdade(entrada.nextInt());
                                System.out.print(" Velocidade de Marcha: ");
                                equino.setVelocidadeDeMarcha(entrada.nextFloat());
                                listaAnimais.add(pos, equino);
                            }
                            if (opc3 == 2) {
                                Suino suino = new Suino();
                                suino.setIdentificador(id);
                                System.out.print(" Espécie: ");
                                suino.setEspecie(entrada.next());
                                System.out.print(" Idade: ");
                                suino.setIdade(entrada.nextInt());
                                System.out.print(" Peso de Carne Útil: ");
                                suino.setPesoDeCarneUtil(entrada.nextFloat());
                                listaAnimais.add(pos, suino);
                            }
                        } else {
                            System.out.print("\n ID inválido.\n");
                        }
                    }
                    break;
                case 3:
                    flag = 0;
                    System.out.print("\n SUBMENU - MOSTRAR");
                    System.out.print("\n___________________");
                    System.out.print("\n 1- Pessoas");
                    System.out.print("\n 2- Animais");
                    System.out.print("\n Digite a opção desejada: ");
                    opc2 = entrada.nextInt();
                    while (opc2 != 1 && opc2 != 2) {
                        System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                        opc2 = entrada.nextInt();
                    }
                    if (opc2 == 1) {
                        System.out.print("\n Informe o CPF para buscar: ");
                        cpf = entrada.next();
                        for (i = 0; i < listaPessoas.size(); i++) {
                            if (listaPessoas.get(i).getCpf().equals(cpf)) {
                                flag = 1;
                                System.out.print(listaPessoas.get(i).toString());
                            }
                        }
                        if (flag == 0) {
                            System.out.print("\n Não há nenhuma pessoa com esse CPF.\n");
                        }
                        flag = 0;
                    }
                    if (opc2 == 2) {
                        System.out.print("\n Informe o ID para buscar: ");
                        id = entrada.next();
                        for (i = 0; i < listaAnimais.size(); i++) {
                            if (listaAnimais.get(i).getIdentificador().equals(id)) {
                                flag = 1;
                                System.out.print(listaAnimais.get(i).toString());
                            }
                        }
                        if (flag == 0) {
                            System.out.print("\n Não há nenhum animal com esse ID.\n");
                        }
                        flag = 0;
                    }
                    break;
                case 4:
                    System.out.print("\n SUBMENU - MOSTRAR");
                    System.out.print("\n___________________");
                    System.out.print("\n 1- Pessoas");
                    System.out.print("\n 2- Animais");
                    System.out.print("\n 3- Pessoas e Animais");
                    System.out.print("\n Digite a opção desejada: ");
                    opc2 = entrada.nextInt();
                    while (opc2 != 1 && opc2 != 2 && opc2 != 3) {
                        System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                        opc2 = entrada.nextInt();
                    }
                    if (opc2 == 1) {
                        for (i = 0; i < listaPessoas.size(); i++) {
                            System.out.print(listaPessoas.get(i));
                        }
                    }
                    if (opc2 == 2) {
                        for (i = 0; i < listaAnimais.size(); i++) {
                            System.out.print(listaAnimais.get(i));
                        }
                    }
                    if (opc2 == 3) {
                        for (i = 0; i < listaPessoas.size(); i++) {
                            System.out.print(listaPessoas.get(i));
                        }
                        for (i = 0; i < listaAnimais.size(); i++) {
                            System.out.print(listaAnimais.get(i));
                        }
                    }
                    if (i == 0) {
                        System.out.print("\n Lista vazia.\n");
                    }
                    break;
                case 5:
                    flag = 0;
                    System.out.print("\n SUBMENU - REMOVER");
                    System.out.print("\n___________________");
                    System.out.print("\n 1- Pessoas");
                    System.out.print("\n 2- Animais");
                    System.out.print("\n Digite a opção desejada: ");
                    opc2 = entrada.nextInt();
                    while (opc2 != 1 && opc2 != 2) {
                        System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                        opc2 = entrada.nextInt();
                    }
                    if (opc2 == 1) {
                        System.out.print("\n Informe o CPF para buscar: ");
                        cpf = entrada.next();
                        Pessoa p;
                        p = null;
                        for (i = 0; i < listaPessoas.size(); i++) {
                            if (listaPessoas.get(i).getCpf().equals(cpf)) {
                                flag = 1;
                                p = listaPessoas.get(i);
                            }
                        }
                        if (flag == 1) {
                            listaPessoas.remove(p);
                            System.out.print("\n Remoção feita com sucesso.\n");
                        } else {
                            System.out.print("\n Não há nenhuma pessoa com esse CPF.\n");
                        }
                        flag = 0;
                    }
                    if (opc2 == 2) {
                        System.out.print("\n Informe o ID para buscar: ");
                        id = entrada.next();
                        Animal a;
                        a = null;
                        for (i = 0; i < listaAnimais.size(); i++) {
                            if (listaAnimais.get(i).getIdentificador().equals(id)) {
                                flag = 1;
                                a = listaAnimais.get(i);
                            }
                        }
                        if (flag == 1) {
                            listaAnimais.remove(a);
                            System.out.print("\n Remoção feita com sucesso.\n");
                        } else {
                            System.out.print("\n Não há nenhum animal com esse ID.\n");
                        }
                        flag = 0;
                    }
                    break;
                case 6:
                    listaPessoas.clear();
                    listaAnimais.clear();
                    Pessoa.setQuantidade(0);
                    System.out.print("\n Os Arrays foram limpos.\n ");
                    break;
                case 7:
                    if (listaPessoas.isEmpty()) {
                        System.out.print("\n O ArrayList de pessoas está vazio.");
                    } else {
                        System.out.print("\n O ArrayList de pessoas não está vazio.");
                    }
                    if (listaAnimais.isEmpty()) {
                        System.out.print("\n O ArrayList de animais está vazio.\n");
                    } else {
                        System.out.print("\n O ArrayList de animais não está vazio.\n");
                    }
                    break;
                case 8:
                    System.out.print("\n Quantidade de pessoas no campus: " + Pessoa.getQuantidade() + "\n");
                    break;
                case 9:
                    salario = maiorSalario = 0;
                    flag = 0;
                    for (i = 0; i < listaPessoas.size(); i++) {
                        if (listaPessoas.get(i) instanceof Administrativo) {
                            Administrativo a = (Administrativo) listaPessoas.get(i);
                            if (flag == 0) {
                                maiorSalario = a.getSalario();
                                flag = 1;
                            }
                            salario = a.getSalario();
                        }
                        if (listaPessoas.get(i) instanceof Professor) {
                            Professor p = (Professor) listaPessoas.get(i);
                            if (flag == 0) {
                                maiorSalario = p.getSalario();
                                flag = 1;
                            }
                            salario = p.getSalario();
                        }
                        if (salario > maiorSalario) {
                            maiorSalario = salario;
                        }
                    }
                    if (i == 0) {
                        System.out.print("\n Não temos funcionários cadastrados.\n");
                    } else {
                        System.out.print("\n Funcionário(s) com maior salário (R$ " + maiorSalario + "):\n");
                        for (i = 0; i < listaPessoas.size(); i++) {
                            if (listaPessoas.get(i) instanceof Administrativo) {
                                Administrativo a = (Administrativo) listaPessoas.get(i);
                                if (a.getSalario() == maiorSalario) {
                                    System.out.print(listaPessoas.get(i));
                                }
                            }
                            if (listaPessoas.get(i) instanceof Professor) {
                                Professor p = (Professor) listaPessoas.get(i);
                                if (p.getSalario() == maiorSalario) {
                                    System.out.print(listaPessoas.get(i));
                                }
                            }
                        }
                    }
                    break;
                case 10:
                    idade = menorIdade = flag = 0;
                    for (i = 0; i < listaAnimais.size(); i++) {
                        if (listaAnimais.get(i) instanceof Equino) {
                            Equino e = (Equino) listaAnimais.get(i);
                            if (flag == 0) {
                                menorIdade = e.getIdade();
                                flag = 1;
                            }
                            idade = e.getIdade();
                            if (idade < menorIdade) {
                                menorIdade = idade;
                            }
                        }
                    }
                    if (flag == 0) {
                        System.out.print("\n Nenhum equino cadastrado.");
                    } else {
                        System.out.print("\n Menor idade entre equinos: " + menorIdade);
                    }
                    idade = menorIdade = flag = 0;
                    for (j = 0; j < listaAnimais.size(); j++) {
                        if (listaAnimais.get(j) instanceof Suino) {
                            Suino s = (Suino) listaAnimais.get(j);
                            if (flag == 0) {
                                menorIdade = s.getIdade();
                                flag = 1;
                            }
                            idade = s.getIdade();
                            if (idade < menorIdade) {
                                menorIdade = idade;
                            }
                        }
                    }
                    if (flag == 0) {
                        System.out.print("\n Nenhum suino cadastrado.");
                    } else {
                        System.out.print("\n Menor idade entre suinos: " + menorIdade);
                    }
                    idade = menorIdade = flag = 0;
                    for (i = 0; i < listaAnimais.size(); i++) {
                        Animal a = (Animal) listaAnimais.get(i);
                        if (flag == 0) {
                            menorIdade = a.getIdade();
                            flag = 1;
                        }
                        idade = a.getIdade();
                        if (idade < menorIdade) {
                            menorIdade = idade;
                        }
                    }
                    if (i == 0) {
                        System.out.print("\n Nenhum animal cadastrado.\n");
                    } else {
                        System.out.print("\n Menor idade entre animais (geral): " + menorIdade + "\n");
                    }
                    break;
                case 11:
                    flag = 0;
                    for (i = 0; i < listaPessoas.size(); i++) {
                        if (listaPessoas.get(i) instanceof Administrativo) {
                            Administrativo a = (Administrativo) listaPessoas.get(i);
                            somarSalarios = somarSalarios + (a.getSalario());
                            flag = 1;
                        }
                    }
                    mediaSalarios = somarSalarios / i;
                    if (flag == 0) {
                        System.out.print("\n Nenhum administrativo cadastrado.\n");
                    } else {
                        System.out.print("\n Média salarial dos administrativos: R$ " + mediaSalarios + "\n");
                    }
                    break;
                case 12:
                    flag = 0;
                    for (i = 0; i < listaPessoas.size(); i++) {
                        if (listaPessoas.get(i) instanceof Administrativo) {
                            flag = 1;
                            Administrativo aux = (Administrativo) listaPessoas.get(i);
                            if (aux.visitado == 0) {
                                System.out.print("\n Setor: " + aux.getSetor() + "\n");
                            }
                            for (j = 0; j < listaPessoas.size(); j++) {
                                if (listaPessoas.get(j) instanceof Administrativo) {
                                    Administrativo adm = (Administrativo) listaPessoas.get(j);
                                    if (adm.getSetor().equals(aux.getSetor()) && adm.visitado == 0) {
                                        System.out.print(listaPessoas.get(j));
                                        adm.visitado = 1;
                                    }
                                }
                            }
                        }
                    }
                    for (i = 0; i < listaPessoas.size(); i++) {
                        if (listaPessoas.get(i) instanceof Administrativo) {
                            Administrativo aux2 = (Administrativo) listaPessoas.get(i);
                            aux2.visitado = 0;
                        }
                    }
                    if (flag == 0) {
                        System.out.print("\n Nenhum administrativo cadastrado.\n");
                    }
                    break;
                case 13:
                    if (listaPessoas.isEmpty()) {
                        System.out.print("\n Lista de pessoas vazia.\n");
                    } else {
                        System.out.print("\n Média das idades das pessoas: " + RH.calcularIdadeMedia(listaPessoas) + "\n");
                    }
                    break;
                case 14:
                    System.out.print("\n SUBMENU - ANDAR PELO CAMPUS");
                    System.out.print("\n_____________________________");
                    System.out.print("\n 1- Professor");
                    System.out.print("\n 2- Aluno");
                    System.out.print("\n 3- Administrativo");
                    System.out.print("\n Digite a opção desejada: ");
                    opc2 = entrada.nextInt();
                    while (opc2 < 1 || opc2 > 3) {
                        System.out.print("\n ENTRADA INVÁLIDA. Digite novamente a opção desejada: ");
                        opc2 = entrada.nextInt();
                    }
                    if (opc2 == 1) {
                        Professor p = new Professor();
                        System.out.print(p.andarPeloCampus());
                    }
                    if (opc2 == 2) {
                        Aluno al = new Aluno();
                        System.out.print(al.andarPeloCampus());
                    }
                    if (opc2 == 3) {
                        Administrativo ad = new Administrativo();
                        System.out.print(ad.andarPeloCampus());
                    }
                    break;

            }

        }
    }

    public static boolean verificaID(String id, ArrayList<Animal> listaAnimais) {
        boolean verifica = true;
        int tamanho = id.length();
        int i = 0;
        int soma = 0;
        int num = 0;
        int flag = 0;
        while (i < tamanho) {
            if ((id.charAt(i)) < 48 || (id.charAt(i)) > 57) {
                verifica = false;
            } else {
                num = (id.charAt(i) - 48);
                soma = soma + num;
            }
            i++;
        }
        if (soma != 10) {
            verifica = false;
        }
        for (i = 0; i < listaAnimais.size(); i++) {
            if (listaAnimais.get(i).getIdentificador().equals(id)) {
                flag = 1;
            }
        }
        if (flag == 1) {
            verifica = false;
        }
        return (verifica);
    }

    public static boolean verificaTel(String tel) {
        boolean verifica = false;
        if (tel.length() == 18) {
            if (tel.charAt(0) == 43 && tel.charAt(3) == 40 && tel.charAt(6) == 41 && tel.charAt(8) == 45 && tel.charAt(13) == 45) {
                if ((tel.charAt(1) >= 48) && (tel.charAt(1) <= 57) && (tel.charAt(2) >= 48) && (tel.charAt(2) <= 57)) {
                    if ((tel.charAt(4) >= 48) && (tel.charAt(4) <= 57) && (tel.charAt(5) >= 48) && (tel.charAt(5) <= 57)) {
                        if ((tel.charAt(7) >= 48) && (tel.charAt(7) <= 57)) {
                            if ((tel.charAt(9) >= 48) && (tel.charAt(9) <= 57) && (tel.charAt(10) >= 48) && (tel.charAt(10) <= 57)) {
                                if ((tel.charAt(11) >= 48) && (tel.charAt(11) <= 57) && (tel.charAt(12) >= 48) && (tel.charAt(12) <= 57)) {
                                    if ((tel.charAt(14) >= 48) && (tel.charAt(14) <= 57) && (tel.charAt(15) >= 48) && (tel.charAt(15) <= 57)) {
                                        if ((tel.charAt(16) >= 48) && (tel.charAt(16) <= 57) && (tel.charAt(17) >= 48) && (tel.charAt(17) <= 57)) {
                                            verifica = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return (verifica);
    }

    public static boolean verificaCPF(String cpf, ArrayList<Pessoa> listaPessoas) {
        boolean verifica = false;
        int flag = 0;
        if (cpf.length() == 5) {
            if ((cpf.charAt(0) >= 48) && (cpf.charAt(0) <= 57)) {//número
                if (((cpf.charAt(1) >= 97) && (cpf.charAt(1) <= 122)) || ((cpf.charAt(1) >= 65) && (cpf.charAt(1) <= 90))) {//letra
                    if ((cpf.charAt(2) >= 48) && (cpf.charAt(2) <= 57)) {//impar
                        if ((cpf.charAt(2) % 2) == 1) {
                            if (((cpf.charAt(3) >= 97) && (cpf.charAt(3) <= 122)) || ((cpf.charAt(3) >= 65) && (cpf.charAt(3) <= 90))) {//letra
                                if ((cpf.charAt(4) >= 48) && (cpf.charAt(4) <= 57)) {//par
                                    if ((cpf.charAt(4) % 2) == 0) {
                                        for (int i = 0; i < listaPessoas.size(); i++) {
                                            if (listaPessoas.get(i).getCpf().equals(cpf)) {
                                                flag = 1;
                                            }
                                        }
                                        if (flag == 0) {
                                            verifica = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return (verifica);
    }

}
