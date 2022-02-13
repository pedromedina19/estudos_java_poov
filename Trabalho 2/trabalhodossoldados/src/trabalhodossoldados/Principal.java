/*
$ Nome: Pedro Lucas Medina Pereira
$ Engenharia de computação - IFTM
$ POOV
 */
package trabalhodossoldados;
 
import java.util.ArrayList;
import java.util.Scanner;
 
 
public class Principal {
 
  
    public static void main(String[] args) {
        
        //SCANNER
        Scanner scan= new Scanner(System.in);
        
        ArrayList <Guerreiros>ListadeGuerreiros=new ArrayList();
        
         int opc;
         String cpf;
         String nome;
         int idade;
         float altura;
         float peso;
         int i;
         int aux;
        //MENU DE OPCOES
        
        do
        {
            System.out.println("\n====================MENU DE OPCOES=======================");
            System.out.println("1)INSERIR OS DADOS");
            System.out.println("2)MOSTRAR OS DADOS");
            System.out.println("3)ALTERAR OS STATUS");
            System.out.println("4)BUSCAR PELO CPF");
            System.out.println("5)BUSCAR PELO NOME");
            System.out.println("6)VERIFICAR GUERREIROS ACIMA DE UMA CERTA IDADE");
            System.out.println("7)VERIFICAR GUERREIROS ABAIXO DE UMA CERTA IDADE");
            System.out.println("8)VERIFICAR GUERREIROS ACIMA DE UMA CERTA ALTURA");
            System.out.println("9)VERIFICAR GUERREIROS ABAIXO DE UMA CERTA ALTURA");
            System.out.println("10)REMOVER GUERREIROS A PARTIR DO CPF");
            System.out.println("11)EDITAR DADOS DE UM GUERREIRO,CPF COMO REFERENCIA");
            System.out.println("12)SAIR");
            System.out.println("===========================================================");
            System.out.println("DIGITE UMA OPCAO ACIMA: ");
            opc=scan.nextInt();
            //VALIDAR
            while(opc>15||opc<1)
            {
                System.out.println("DIGITE NOVAMENTE A OPCAO ESCOLHIDA: ");
                opc=scan.nextInt();
            }
            
            if(opc==1)
            {
                Guerreiros Soldadonovo=new Guerreiros();
               
                System.out.println("DIGITE O CPF DO SOLDADO: ");
                cpf=scan.next();
                while(cpf.length()!=11)
                {
                    System.out.println("\nCPF INVALIDO DIGITE NOVAMENTE: ");
                    cpf=scan.next();
                }
                
                Soldadonovo.setCPF(cpf);
               
                System.out.println("DIGITE O NOME DO SOLDADO: ");
                
                Soldadonovo.setNome(scan.next());
               
                System.out.println("DIGITE A IDADE DO SOLDADO: ");
                idade=scan.nextInt();
                //VALIDAR A IDADE
                while(idade<=18)
                {
                    System.out.println("\nIDADE INVALIDA,DIGITE NOVAMENTE: ");
                    idade=scan.nextInt();
                }
                Soldadonovo.setIdade(idade);
                
                System.out.println("DIGITE A ALTURA DO SOLDADO: ");
                altura=scan.nextFloat();
                //VALIDAR A ALATURA
                while(altura>2.10||altura<1.54)
                {
                    System.out.println("SOLDADO NAO POSSUI ALTURA ADEQUADA: ");
                    altura=scan.nextFloat();
                }
                Soldadonovo.setAltura(altura);
                
                System.out.println("DIGITE O PESO DO SOLDADO: ");
                peso=scan.nextFloat();
               
                Soldadonovo.setPeso(peso);
                
                System.out.println("DIGITE O BATALHAO");
                System.out.println("1)INFANTARIA");
                System.out.println("2)CAVALARIA");
                System.out.println("3)MONTANHA");
                System.out.println("4)CATINGA");
                System.out.println("5)SELVA");
                int bat;
                bat=scan.nextInt();
                while(bat>5||bat<1)
                {
                    System.out.println("\nDIGITE NOVAMENTE O BATALHAO: ");
                    bat=scan.nextInt();
                }
                Soldadonovo.setBatalhao(bat);
              
                System.out.println("DIGITE A CIDADE/ESTADO DE ORIGEM");
                Soldadonovo.setCidade_estado(scan.next());
                
                System.out.println("DIGITE 1 PARA VIVO E 0 PARA MORTO");
                bat=scan.nextInt();
                while(bat>1||bat<0)
                {
                    System.out.println("DIGITE NOVAMENTE: ");
                    bat=scan.nextInt();
                }
                Soldadonovo.setStatus(bat);
                
                
                ListadeGuerreiros.add(Soldadonovo);
                
                
                
                
            
            }
            
            if(opc==2)
            {
                //MOSTRAR TODOS
                System.out.println("\nTODOS OS SOLDADOS\n");
                for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    ListadeGuerreiros.get(i).mostraTodosDados();
                }
            }
            
            if(opc==3)
            {
                System.out.println("\nDIGITE O CPF A SER PROCURADO NO SISTEMA");
                cpf=scan.next();
                aux=0;
                for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getCPF().equals(cpf))
                    {
                       ListadeGuerreiros.get(i).alterarStatus();
                       aux=10;
                    }
                }
                if(aux!=10)
                {
                    System.out.println("\nCPF NAO ENCONTRADO NO SISTEMA");
                }
                
            }
            
            if(opc==4)
            {
                System.out.println("\nDIGITE O CPF A SER PROCURADO NO SISTEMA");
                cpf=scan.next();
                aux=0;
                for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getCPF().equals(cpf))
                    {
                         ListadeGuerreiros.get(i).mostraTodosDados();
                         aux=10;
                    }
                }
                if(aux!=10)
                {
                    System.out.println("\nCPF NAO ENCONTRADO NO SISTEMA");
                }
                
                
            }
            
            if(opc==5)
            {
                System.out.println("\nDIGITE O NOME A SER PROCURADO NO SISTEMA");
                nome=scan.next();
                aux=0;
                for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getNome().equals(nome))
                    {
                         ListadeGuerreiros.get(i).mostraTodosDados();
                         aux=10;
                    }
                }
                if(aux!=10)
                {
                    System.out.println("\nNOME NAO ENCONTRADO NO SISTEMA");
                }
            }
            
            
            if(opc==6)
            {
               
                System.out.println("\nDIGITE A IDADE: ");
                idade=scan.nextInt();
                for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getIdade()>idade)
                    {
                         ListadeGuerreiros.get(i).mostraTodosDados();
                    }
                }
                
                
                
                
            }
            
            if(opc==7)
            {
                System.out.println("\nDIGITE A IDADE: ");
                idade=scan.nextInt();
                for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getIdade()<idade)
                    {
                        ListadeGuerreiros.get(i).mostraTodosDados(); 
                    }
                }
            }
            
            
            if(opc==8)
            {
                System.out.println("\nDIGITE A ALTURA: ");
                altura=scan.nextFloat();
                 for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getAltura()>altura)
                    {
                         ListadeGuerreiros.get(i).mostraTodosDados();
                    }
                }
                
            }
            
            if(opc==9)
            {
                System.out.println("\nDIGITE A ALTURA: ");
                altura=scan.nextFloat();
                 for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getAltura()<altura)
                    {
                         ListadeGuerreiros.get(i).mostraTodosDados();
                    }
                }
                
            }
            
            if(opc==10)
            {
                System.out.println("\nDIGITE O CPF A SER PROCURADO NO SISTEMA,E RETIRAR O SOLDADO");
                cpf=scan.next();
                aux=0;
                for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getCPF().equals(cpf))
                    {
                        ListadeGuerreiros.remove(i);
                        aux=10;
                    }
                }
                if(aux!=10)
                {
                    System.out.println("\nCPF NAO ENCONTRADO NO SISTEMA");
                }
            }
            
            if(opc==11)
            {
                System.out.println("\nDIGITE O CPF A SER PROCURADO NO SISTEMA,E EDITAR O SOLDADO");
                cpf=scan.next();
                aux=0;
                for(i=0;i<ListadeGuerreiros.size();i++)
                {
                    if(ListadeGuerreiros.get(i).getCPF().equals(cpf))
                    {
                        ListadeGuerreiros.get(i).alteraDados();
                        aux=10;
                    }
                }
                if(aux!=10)
                {
                    System.out.println("\nCPF NAO ENCONTRADO NO SISTEMA");
                }
 
            }
            if(opc==12)
            {
                //LIMPAR A LISTA
                ListadeGuerreiros.clear();
            }
             
        }while(opc != 12);
          
    }
    
}