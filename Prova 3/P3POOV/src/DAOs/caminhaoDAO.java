//PEDRO MEDINA

package DAOs;

import Classes.Caminhao;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class caminhaoDAO {
     //Nossa classe de DAO do cliente possui apenas um atributo do tipo Conexão
    Connection conexao;
    
    public caminhaoDAO() throws SQLException, ClassNotFoundException{
        //INICIAMOS NOSSA CONEXÃO POR MEIO DE UM CONSTRUTOR
        this.conexao = Conexao.getConexao();
    }
    
    public int MaiorID(){
        PreparedStatement instrucao;
        ResultSet res;
        String codigo = "select MAX(CodCaminhao) from Caminhao";
        
        int maxID = 0;
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            
            while(res.next()){
                maxID = res.getInt("MAX(CodCaminhao)");
            }
        }catch(SQLException e){
            System.out.println("erro: "+e.toString());
        }
       
        return maxID;
    }
    
    public void inserir(Caminhao caminhao) throws SQLException{
        //Criando uma instrução preparada
        PreparedStatement instrucao;
        
        //Criando nosso pré código de inserção
        String codigo = "insert into caminhao(CodCaminhao, Modelo, Potencia) values(?,?,?);";
        
        try{
            //dizendo que nossa instrução preparada será feita por meio da conexão e pelo pré código da string código
            instrucao = this.conexao.prepareStatement(codigo);
            
            //INSERINDO OS VALORES EM CADA UMA DAS INTERROGAÇÕES
            instrucao.setInt(1, caminhao.getCodCaminhao());
            instrucao.setString(2, caminhao.getModelo());
            instrucao.setFloat(3, caminhao.getPotencia());
            //EXECUTANDO A INSTRUÇÃO PREPARADA
            instrucao.execute();
            
            //FECHANDO A INSTRUÇÃO PREPARADA
            instrucao.close();
            
            //MENSAGEM DE QUE O CARRO FOI ADICIONADO COM SUCESSO
            System.out.println("Caminhao adicionado com sucesso");
        }catch(SQLException e){
            //MENSAGEM CASO OCORRA UM ERRO
            System.out.println("Erro ao tentar criar um Cliente e adiciona-lo: "+e.toString());
        }
    }
    
    
    public void update(Caminhao caminhao) throws SQLException{
        //Criando uma instrução preparada
        PreparedStatement instrucao;
        
        //Criando um pré condigo possuindo alguns valores como variáveis
        String codigo = "update Caminhao set  Modelo=?, Potencia=? where CodCaminhao=?";
        
        try{
            //dizendo que nossa instrução preparada será feita por meio da conexão e pelo pré código da string código
            instrucao = this.conexao.prepareStatement(codigo);
            
            //INSERINDO OS VALORES EM CADA UMA DAS INTERROGAÇÕES
            
            instrucao.setString(1, caminhao.getModelo());
            instrucao.setFloat(2, caminhao.getPotencia());
            
            instrucao.setInt(3, caminhao.getCodCaminhao());
            //EXECUTANDO A INSTRUÇÃO PREPARADA
            instrucao.execute();
            
            //FECHANDO A INSTRUÇÃO PREPARADA
            instrucao.close();
            
            //MENSAGEM DE QUE O CARRO FOI ADICIONADO COM SUCESSO
            System.out.println("Cliente adicionado com sucesso");
        }catch(SQLException e){
            //Mensagem de erro
            System.out.println("Erro ao tentar atualizar Caminhao: "+e.toString());
        }
    }
    
    public ArrayList<Caminhao> getAll(){
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Caminhao> ListaRetorno = new ArrayList();
        
        String codigo = "select * from Caminhao";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            while(res.next()){
                Caminhao caminhao = new Caminhao();
                caminhao.setCodCaminhao(res.getInt("CodCaminhao"));
                caminhao.setModelo(res.getString("Modelo"));
                caminhao.setPotencia(res.getFloat("Potencia"));
                ListaRetorno.add(caminhao);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        return ListaRetorno;
    }
    
    public ArrayList<Caminhao> getOne_ID(int CodCaminhao) throws SQLException{
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Caminhao> ListaRetorno = new ArrayList();
        
        String codigo = "select * from Caminhao where CodCaminhao="+CodCaminhao+";";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            ListaRetorno = new ArrayList<>();
            while(res.next()){
                Caminhao caminhao = new Caminhao();
                caminhao.setCodCaminhao(res.getInt("CodCaminhao"));
                caminhao.setModelo(res.getString("Modelo"));
                caminhao.setPotencia(res.getFloat("Potencia"));                
                ListaRetorno.add(caminhao);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        System.out.println("Caminhao buscado com sucesso");
        return ListaRetorno;
    }
    
    public ArrayList<Caminhao> getOne_Potencia(Float Potencia) throws SQLException{
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Caminhao> ListaRetorno = new ArrayList();
        
        String codigo = "select * from Caminhao where Potencia='"+Potencia+"';";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            ListaRetorno = new ArrayList<>();
            while(res.next()){
                Caminhao caminhao = new Caminhao();
                caminhao.setCodCaminhao(res.getInt("CodCaminhao"));
                caminhao.setModelo(res.getString("Modelo"));
                caminhao.setPotencia(res.getFloat("Potencia"));                
                ListaRetorno.add(caminhao);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        System.out.println("Caminhao buscado com sucesso");
        return ListaRetorno;
    }
    
    public void delete(int CodCaminhao) throws SQLException{
        PreparedStatement instrucao;
        
        String codigo = "delete from Caminhao where CodCaminhao=?;";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            instrucao.setInt(1, CodCaminhao);
            
            instrucao.execute();
            instrucao.close();
            
            System.out.println("Caminhao deletado com sucesso");
        
        }catch(SQLException e){
            System.out.println("Erro ao tentar deletar: "+e.toString());
        }
    
    }
}
