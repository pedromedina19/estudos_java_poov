/*
$ Nome: Pedro Lucas Medina Pereira
        Darla Garcia
$ Engenharia de computação - IFTM
$ POOV
 */
package darla_pedro_trabalho1_ex2;

import java.util.Scanner;
import java.util.ArrayList;

public class Darla_Pedro_Trabalho1_EX2 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        
        // Definindo o objeto
        conta_bancaria conta = null; 
        //Criando e inicializando a opção do menu
        int opc;
        opc=0;
        
        
        while(opc != 5)
        {
            System.out.println("\n\n");
            System.out.print("\n MENU ");
            System.out.print("\n 1- Inserir ");
            System.out.print("\n 2- Realizar saque ");
            System.out.print("\n 3- Realizar deposito ");
            System.out.print("\n 4- Acesso ao menu do vetor");
            System.out.print("\n 4- Sair \n ->");
            opc = entrada.nextInt();
            switch(opc)
            {
                case 1:
                    conta = new conta_bancaria(); 
                    System.out.print("\n Digite o número da conta......: ");
                    conta.numr = entrada.next();
                    System.out.print("\n Digite a senha................: ");
                    conta.senha = entrada.next();
                    System.out.print("\n Digite o proprietario da conta: ");
                    conta.proprietario = entrada.next();
                    System.out.print("\n Digite o saldo da conta.......: ");
                    conta.saldo = entrada.nextDouble();
                    System.out.print("\n Digite o limite do cheque.....: ");
                    conta.limite = entrada.nextDouble();
                break;
                case 2:
                    double saque;
                    saque =0;
                    System.out.print("\n\n Digite o valor a ser retirado: ");
                    saque = entrada.nextDouble();
                    conta.saldo = conta.saldo - saque;
                    System.out.print("\n O saldo atual da conta é: "+conta.saldo);
                break;
                case 3:
                    double deposito;
                    deposito =0;
                    System.out.print("\n\n Digite o valor a ser depositado: ");
                    deposito = entrada.nextDouble();
                    conta.saldo = conta.saldo + deposito;
                    System.out.print("\n O saldo atual da conta é: "+conta.saldo);
                break;
                case 4:
                    int op;
                    op=0;
                    while (op!=3)
                    {
                        System.out.println("\n\n");
                        System.out.print("\n MENU DO VETOR DE CONTAS ");
                        System.out.print("\n 1- Inserir ");
                        System.out.print("\n 2- Realizar saque ");
                        System.out.print("\n 3- Realizar transferencia ");
                        System.out.print("\n 4- Sair \n ->");
                        opc = entrada.nextInt();
                        switch(opc)
                        {
                            case 1:
                                conta_bancaria listaContas[];          //1- Declarar.
                                listaContas = new conta_bancaria[3];   //2- Definir quantas posições teremos
                                for(int i=0; i<3;i++)
                                {
                                    listaContas[i] = new conta_bancaria(); //3- Instanciar cada posição.
                                }       
                                for(int i=0; i<3;i++)
                                {
                                    System.out.print("\n --- ["+i+"] --- ");
                                    System.out.print("\n Digite o número da conta......: ");
                                    listaContas[i].numr = entrada.next();
                                    System.out.print("\n Digite a senha................: ");
                                    listaContas[i].senha = entrada.next();
                                    System.out.print("\n Digite o proprietario da conta: ");
                                    listaContas[i].proprietario = entrada.next();
                                    System.out.print("\n Digite o saldo da conta.......: ");
                                    listaContas[i].saldo = entrada.nextDouble();
                                    System.out.print("\n Digite o limite do cheque.....: ");
                                    listaContas[i].limite = entrada.nextDouble();
                                }
                            break;
                            case 2:
                                
                            break;
                            
                            
                            case 3:
                                
                                
                            break;
                        }
                       
                    }
                    
                break;   
            }
        }
        
        
    }
    
}
