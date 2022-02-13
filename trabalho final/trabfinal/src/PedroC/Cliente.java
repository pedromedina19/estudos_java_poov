
package PedroC;
import CONEXAO.Conexao;

public class Cliente {
    
    
    //ATRIBUTOS
    private String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String end_instagram;
    private String end_facebook;
    private String cartaoDeCredito;
    
    
    //CONSTRUTORES
    public Cliente() {
        this.cpf = "";
        this.nome = "";
        this.telefone = "";
        this.endereco = "";
        this.end_instagram ="";
        this.end_facebook ="";
        this.cartaoDeCredito = "";
    }

    public Cliente(String cpf, String nome, String telefone, String endereco, String end_instagram, String end_facebook, String cartaoDeCredito) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.end_instagram = end_instagram;
        this.end_facebook = end_facebook;
        this.cartaoDeCredito = cartaoDeCredito;
    }
    
    //SET E GETTERS
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEnd_instagram() {
        return end_instagram;
    }

    public void setEnd_instagram(String end_instagram) {
        this.end_instagram = end_instagram;
    }

    public String getEnd_facebook() {
        return end_facebook;
    }

    public void setEnd_facebook(String end_facebook) {
        this.end_facebook = end_facebook;
    }

    public String getCartaoDeCredito() {
        return cartaoDeCredito;
    }

    public void setCartaoDeCredito(String cartaoDeCredito) {
        this.cartaoDeCredito = cartaoDeCredito;
    }

    
        
    
    
    //TO STRING
    @Override
    public String toString() {
        return "Cliente{" + "cpf=" + cpf + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", end_instagram=" + end_instagram + ", end_facebook=" + end_facebook + ", cartaoDeCredito=" + cartaoDeCredito + '}';
    }

    
    
    
}
