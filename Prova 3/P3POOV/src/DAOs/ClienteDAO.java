//PEDRO MEDINA

package DAOs;

import Classes.Cliente;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    
     //Nossa classe de DAO do cliente possui apenas um atributo do tipo Conexão
    Connection conexao;
    
    public ClienteDAO() throws SQLException, ClassNotFoundException{
        //INICIAMOS NOSSA CONEXÃO POR MEIO DE UM CONSTRUTOR
        this.conexao = Conexao.getConexao();
    }
    
    public void inserir(Cliente cliente) throws SQLException{
        //Criando uma instrução preparada
        PreparedStatement instrucao;
        
        //Criando nosso pré código de inserção
        String codigo = "insert into Cliente(cpf, nome, endereco, email, telefone) values(?,?,?,?,?);";
        
        try{
            //dizendo que nossa instrução preparada será feita por meio da conexão e pelo pré código da string código
            instrucao = this.conexao.prepareStatement(codigo);
            
            //INSERINDO OS VALORES EM CADA UMA DAS INTERROGAÇÕES
            instrucao.setString(1, cliente.getCpf());
            instrucao.setString(2, cliente.getNome());
            instrucao.setString(3, cliente.getEndereco());
            instrucao.setString(4, cliente.getEmail());
            instrucao.setString(5, cliente.getTelefone());
            //EXECUTANDO A INSTRUÇÃO PREPARADA
            instrucao.execute();
            
            //FECHANDO A INSTRUÇÃO PREPARADA
            instrucao.close();
            
            //MENSAGEM DE QUE O CARRO FOI ADICIONADO COM SUCESSO
            System.out.println("Cliente adicionado com sucesso");
        }catch(SQLException e){
            //MENSAGEM CASO OCORRA UM ERRO
            System.out.println("Erro ao tentar criar um Cliente e adiciona-lo: "+e.toString());
        }
    }
    
    
    public void update(Cliente cliente) throws SQLException{
        //Criando uma instrução preparada
        PreparedStatement instrucao;
        
        //Criando um pré condigo possuindo alguns valores como variáveis
        String codigo = "update Cliente set nome=?, endereco=?, email=?, telefone=? where cpf=?";
        
        try{
            //informando que nossa instrução preparada será feita por meio da conexão pelo pré código escrito
            instrucao = this.conexao.prepareStatement(codigo);
            
            //Informando qual o valor de cada uma das interrogações
            instrucao.setString(1, cliente.getNome());
            instrucao.setString(2, cliente.getEndereco());
            instrucao.setString(3, cliente.getEmail());
            instrucao.setString(4, cliente.getTelefone());
            instrucao.setString(5, cliente.getCpf());
           
            //Executando a instrução
            instrucao.execute();
            
            //Fechando a instrução
            instrucao.close();
            
            //Mensagem de sucesso
            System.out.println("Cliente atualizado com sucesso");
        }catch(SQLException e){
            //Mensagem de erro
            System.out.println("Erro ao tentar atualizar Cliente: "+e.toString());
        }
    }
    
    public ArrayList<Cliente> getAll(){
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Cliente> ListaRetorno = new ArrayList();
        
        String codigo = "select * from Cliente";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            while(res.next()){
                Cliente cliente = new Cliente();
                cliente.setCpf(res.getString("cpf"));
                cliente.setNome(res.getString("nome"));
                cliente.setEndereco(res.getString("endereco"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));
                ListaRetorno.add(cliente);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        return ListaRetorno;
    }
    
    public ArrayList<Cliente> getOne_CPF(String cpf) throws SQLException{
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Cliente> ListaRetorno = new ArrayList();
        
        String codigo = "select * from Cliente where cpf='"+cpf+"';";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            ListaRetorno = new ArrayList<>();
            while(res.next()){
                Cliente cliente = new Cliente();
                cliente.setCpf(res.getString("cpf"));
                cliente.setNome(res.getString("nome"));
                cliente.setEndereco(res.getString("endereco"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));
                
                ListaRetorno.add(cliente);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        System.out.println("Cliente buscado com sucesso");
        return ListaRetorno;
    }
    
    public ArrayList<Cliente> getOne_nome(String nome) throws SQLException{
        PreparedStatement instrucao;
        ResultSet res;
        ArrayList <Cliente> ListaRetorno = new ArrayList();
        
        String codigo = "select * from Cliente where nome='"+nome+"';";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            res = instrucao.executeQuery();
            ListaRetorno = new ArrayList<>();
            while(res.next()){
                Cliente cliente = new Cliente();
                cliente.setCpf(res.getString("cpf"));
                cliente.setNome(res.getString("nome"));
                cliente.setEndereco(res.getString("endereco"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));
                
                ListaRetorno.add(cliente);
            }
            
            res.close();
            instrucao.close();
        }catch(SQLException e){
            System.out.println("Erro ao tentar recuperar a lista "+e.toString());
        }
        System.out.println("Cliente buscado com sucesso");
        return ListaRetorno;
    }
    
    public void delete(String cpf) throws SQLException{
        PreparedStatement instrucao;
        
        String codigo = "delete from Cliente where cpf=?;";
        
        try{
            instrucao = this.conexao.prepareStatement(codigo);
            instrucao.setString(1, cpf);
            
            instrucao.execute();
            instrucao.close();
            
            System.out.println("Cliente deletado com sucesso");
        
        }catch(SQLException e){
            System.out.println("Erro ao tentar deletar: "+e.toString());
        }
    
    }
    
}
