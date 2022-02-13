/*
$ Nome: Pedro Lucas Medina Pereira
$ Engenharia de computação - IFTM
$ POOV
 */
package trabalhodossoldados;
 
import java.util.Scanner;
 
public class Guerreiros{
    // ATRIBUTOS DA CLASSE /////////////////////////////
    Scanner scan = new Scanner(System.in);
    private String CPF;
    private String Nome;
    private int Idade;
    private float Altura;
    private float Peso;
    private String Batalhao;
    private String Cidade_estado;
    private boolean Status;
    //////////////////////////////////////////////////
    
    // CONSTRUTOR /////////////////////////////////////
    public Guerreiros(){
        this.CPF = "12345678910";
        this.Nome = "soldadim";
        this.Idade = 18;
        this.Altura = (float)1.75;
        this.Peso = 70;
        this.Batalhao = "1";
        this.Cidade_estado = "Uberaba-MG";
        this.Status = true;    
    }
    /////////////////////////////////////////////////
    
    
    // INSERIR E MOSTRAR DADOS //////////////////////
    public void setCPF(String CPF){
        this.CPF = CPF;
    }
    public String getCPF(){
        return (this.CPF);
    }
 
    
    public void setNome(String Nome){
        this.Nome = Nome;
    }
    public String getNome(){
        return (this.Nome);
    }
    
    
    public void setIdade(int Idade){
        this.Idade = Idade;
    }
    public int getIdade(){
        return (this.Idade);
    }
    
    
    public void setAltura(float Altura){
        this.Altura = Altura;
    }
    public float getAltura(){
        return (this.Altura);
    }
    
    
    public void setPeso(float Peso){
        this.Peso = Peso;
    }
    public float getPeso(){
        return (this.Peso);
    }
    
    
    public void setBatalhao(int Batalhao){
        if(Batalhao == 1)
            this.Batalhao = "Infantaria";
        if(Batalhao == 2)
            this.Batalhao = "cavalaria";
        if(Batalhao == 3)
            this.Batalhao = "montanha";
        if(Batalhao == 4)
            this.Batalhao = "caatinga";
        if(Batalhao == 5)
            this.Batalhao = "selva";
    }
    public String getBatalhao(){
        return (this.Batalhao);
    }
    
    
    public void setCidade_estado(String Cidade_estado){
        this.Cidade_estado = Cidade_estado;
    }
    public String getCidade_estado(){
        return (this.Cidade_estado);
    }
    
    
    public void setStatus(int Status){
        if(Status == 1)
            this.Status = true;
        if(Status == 0)
            this.Status = false;
    }
    
    public boolean getStatus(){
        return (this.Status);
    }
    
    public String statusSoldado(){
        if(this.Status)
            return ("Soldado encontra-se vivo");
        if(!this.Status)
            return ("Soldado encontra-se morto");
        return null;
    }
    
    // MOSTRA TODOS OS DADOS DO SOLDADO
    public void mostraTodosDados(){
        System.out.print("\n ");
        System.out.print("\n Nome: "+this.getNome());
        System.out.print("\n CPF: "+this.getCPF());
        System.out.print("\n Idade: "+this.getIdade());
        System.out.print("\n Altura: "+this.getAltura());
        System.out.print("\n Peso: "+this.getPeso());
        System.out.print("\n Batalhão: "+this.getBatalhao());
        System.out.print("\n Cidade/Estado: "+this.getCidade_estado());
        System.out.print("\n Status: "+this.statusSoldado());
        System.out.println("\n");
    }
    /////////////////////////////////////////////////////////////
    
    // Alterar status
    public void alterarStatus(){
        if(!this.Status)
            System.out.println("\n Não é possível alterar o status do soldado, pois o soldado já está morto \n");
        if(this.Status){
            this.Status = false;
            System.out.println("O "+this.Nome+" morreu...");
        }
    }
    ///////////////////////////////////////////////////////////////
    
   public void alteraDados(){
        System.out.println("DIGITE O CPF DO SOLDADO: ");
        this.setCPF(scan.next());
               
        System.out.println("DIGITE O NOME DO SOLDADO: ");
        this.setNome(scan.next());
               
        System.out.println("DIGITE A IDADE DO SOLDADO: ");
        this.setIdade(scan.nextInt());
                
        System.out.println("DIGITE A ALTURA DO SOLDADO: ");
        this.setAltura(scan.nextFloat());
                
        System.out.println("DIGITE O PESO DO SOLDADO: ");
        this.setPeso(scan.nextFloat());
                
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
                this.setBatalhao(bat);
                
                System.out.println("DIGITE A CIDADE/ESTADO DE ORIGEM");
                this.setCidade_estado(scan.next());
                
                System.out.println("DIGITE 1 PARA VIVO E 0 PARA MORTO");
                bat=scan.nextInt();
                while(bat>1||bat<0)
                {
                    System.out.println("DIGITE NOVAMENTE: ");
                    bat=scan.nextInt();
                }
                this.setStatus(bat);
    }
}