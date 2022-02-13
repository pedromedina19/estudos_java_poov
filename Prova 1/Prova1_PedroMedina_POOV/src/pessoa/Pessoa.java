package pessoa;

public abstract class Pessoa {

    private String cpf;
    private String nome;
    private int idade;
    private String endereco;
    private String telefone;
    private static int quantidade;

    public Pessoa(String cpf, String nome, int idade, String endereco, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
        Pessoa.quantidade++;
    }

    public Pessoa() {
        this.cpf = "sem cpf";
        this.nome = "sem nome";
        this.idade = 0;
        this.endereco = "sem endereço";
        this.telefone = "sem telefone";
        Pessoa.quantidade++;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public static int getQuantidade() {
        return quantidade;
    }

    public static void setQuantidade(int quantidade) {
        Pessoa.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "\n CPF: " + cpf + "\n Nome: " + nome + "\n Idade: " + idade + "\n Endereço: " + endereco + "\n Telefone: " + telefone + "\n";
    }

    public String andarPeloCampus() {
        return ("\n Dando uma voltinha feliz pelo campus. \n");
    }
}
