/*
Nome: Pedro Lucas Medina Pereira
Disciplina: POOV
Data: 29/09/2021
 */
package principal;
public class Cliente {
    private String cpf;
    private String nome;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
    
    public Cliente() {
        this.cpf = "sem cpf";
        this.nome = "sem nome";
    }

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

    @Override
    public String toString() {
        return "Cliente{" + "cpf=" + cpf + ", nome=" + nome + '}';
    }
}
