/*
$ Nome: Pedro Lucas Medina Pereira
        Darla Garcia
$ Engenharia de computação - IFTM
$ POOV
 */
package darla_pedro_trabalho1_ex1;


import java.util.Scanner;
        

public class Darla_Pedro_Trabalho1_EX1 {

    public static void main(String[] args) {
        
        Pessoa aluno; 
        Scanner entrada = new Scanner (System.in);
        aluno = new Pessoa(); 
        int opc;
        opc=0;
        do{
            opc=0;
       
            
            System.out.print("\n\n MENU");
            System.out.print("\n1- Digitar dados ");
            System.out.print("\n2- Mostrar dados ");
            System.out.print("\n3- Sair");
            System.out.print("\n Digite a opção-> ");
            opc = entrada.nextInt();
            
            switch (opc)
            {
                case 1:
                    System.out.print("\n Digite o nome: ");
                    aluno.nome = entrada.next();
                    System.out.print("\n Digite a idade: ");
                    aluno.idade = entrada.nextInt();
                    System.out.print("\n Digite a ra: ");
                    aluno.ra = entrada.next();
                    System.out.print("\n Digite a area: ");
                    aluno.area = entrada.next();
                    System.out.print("\n Digite a curso: ");
                    aluno.curso = entrada.next();
                break;
                
                case 2:
                    System.out.print("\n\n\n");
                    System.out.print("\n O nome do aluno é "+aluno.nome);
                    System.out.print("\n A idade do aluno é "+aluno.idade);
                    System.out.print("\n O ra do aluno é "+aluno.ra);
                    System.out.print("\n A area do aluno é "+aluno.area);
                    System.out.print("\n O curso do aluno é "+aluno.curso);
            }
        }while (opc!=3);
        
        
    }
    
}
