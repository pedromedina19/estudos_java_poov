//PEDRO MEDINA

package DAOs;

import Classes.Venda;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class VendaDAO {
     //Nossa classe de DAO do cliente possui apenas um atributo do tipo Conexão
    Connection conexao;
    
    public VendaDAO() throws SQLException, ClassNotFoundException{
        //INICIAMOS NOSSA CONEXÃO POR MEIO DE UM CONSTRUTOR
        this.conexao = Conexao.getConexao();
    }
    
    public int MaiorID(){
        PreparedStatement instrucao;
        ResultSet res;
        String codigo = "select MAX(CodVenda) from Venda";
        
        int maxID = 0;
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            
            while(res.next()){
                maxID = res.getInt("MAX(CodVenda)");
            }
        }catch(SQLException e){
            System.out.println("erro: "+e.toString());
        }
       
        return maxID;
    }
    
    public void inserir(Venda venda) throws SQLException{
        //Criando uma instrução preparada
        PreparedStatement instrucao;
        
        //Criando nosso pré código de inserção
        String codigo = "insert into Venda(CodVenda, FK_CPF, FK_CodCaminhao, Data, valor) values(?,?,?,?,?);";
        
        try{
            //dizendo que nossa instrução preparada será feita por meio da conexão e pelo pré código da string código
            instrucao = this.conexao.prepareStatement(codigo);
            
            //INSERINDO OS VALORES EM CADA UMA DAS INTERROGAÇÕES
            instrucao.setInt(1, venda.getCodVenda());
            instrucao.setString(2, venda.getFK_CPF());
            instrucao.setInt(3, venda.getFK_CodCaminhao());
            
            Date date1 = Date.valueOf(venda.getData());
            instrucao.setDate(4, date1);
            
            instrucao.setFloat(5, venda.getValor());
            //EXECUTANDO A INSTRUÇÃO PREPARADA
            instrucao.execute();
            
            //FECHANDO A INSTRUÇÃO PREPARADA
            instrucao.close();
            
            //MENSAGEM DE QUE O CARRO FOI ADICIONADO COM SUCESSO
            System.out.println("Venda adicionada com sucesso");
        }catch(SQLException e){
            //MENSAGEM CASO OCORRA UM ERRO
            System.out.println("Erro ao tentar criar uma Venda e adiciona-la: "+e.toString());
        }
    }
    
    
    public void update(Venda venda) throws SQLException{
        //Criando uma instrução preparada
        PreparedStatement instrucao;
        
        //Criando um pré condigo possuindo alguns valores como variáveis
        String codigo = "update Venda set FK_CPF=?, FK_CodCaminhao=?, Data=?, valor=? where CodVenda=?";
        
        try{
            //dizendo que nossa instrução preparada será feita por meio da conexão e pelo pré código da string código
            instrucao = this.conexao.prepareStatement(codigo);
            
            //INSERINDO OS VALORES EM CADA UMA DAS INTERROGAÇÕES
            
            instrucao.setString(1, venda.getFK_CPF());
            instrucao.setInt(2, venda.getFK_CodCaminhao());
            
            Date date1 = Date.valueOf(venda.getData());
            instrucao.setDate(3, date1);
            
            instrucao.setFloat(4, venda.getValor());
            instrucao.setInt(5, venda.getCodVenda());
            //EXECUTANDO A INSTRUÇÃO PREPARADA
            instrucao.execute();
            
            //FECHANDO A INSTRUÇÃO PREPARADA
            instrucao.close();
            
            //MENSAGEM DE QUE O CARRO FOI ADICIONADO COM SUCESSO
            System.out.println("Venda Atualizada com sucesso");
        }catch(SQLException e){
            //Mensagem de erro
            System.out.println("Erro ao tentar atualizar Venda: "+e.toString());
        }
    }
    
    public ArrayList<Venda> getAll(){
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Venda> ListaRetorno = new ArrayList();
        
        String codigo = "select * from Venda";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            while(res.next()){
                Venda venda = new Venda();
                venda.setCodVenda(res.getInt("CodVenda"));
                venda.setFK_CPF(res.getString("FK_CPF"));
                venda.setFK_CodCaminhao(res.getInt("FK_CodCaminhao"));
                LocalDate data = res.getDate("Data").toLocalDate();
                venda.setData(data);
                venda.setValor(res.getFloat("valor"));
                ListaRetorno.add(venda);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        return ListaRetorno;
    }
    
    public ArrayList<Venda> getOne_ID(int CodVenda) throws SQLException{
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Venda> ListaRetorno = new ArrayList();
        
        String codigo = "select * from Venda where CodVenda="+CodVenda+";";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            ListaRetorno = new ArrayList<>();
            while(res.next()){
                Venda venda = new Venda();
                venda.setCodVenda(res.getInt("CodVenda"));
                venda.setFK_CPF(res.getString("FK_CPF"));
                venda.setFK_CodCaminhao(res.getInt("FK_CodCaminhao"));
                LocalDate data = res.getDate("Data").toLocalDate();
                venda.setData(data);
                venda.setValor(res.getFloat("valor"));
                ListaRetorno.add(venda);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        System.out.println("Venda buscada com sucesso");
        return ListaRetorno;
    }
       
    public void delete(int CodVenda) throws SQLException{
        PreparedStatement instrucao;
        
        String codigo = "delete from Venda where CodVenda=?;";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            instrucao.setInt(1, CodVenda);
            
            instrucao.execute();
            instrucao.close();
            
            System.out.println("Venda deletada com sucesso");
        
        }catch(SQLException e){
            System.out.println("Erro ao tentar deletar: "+e.toString());
        }
    
    }
}
