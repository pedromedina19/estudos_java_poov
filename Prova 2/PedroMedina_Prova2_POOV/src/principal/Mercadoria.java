/*
Nome: Pedro Lucas Medina Pereira
Disciplina: POOV
Data: 29/09/2021
 */
package principal;
import java.time.LocalDate;
public class Mercadoria {
    
    private String codigo;
    private String nome;
    private String descricao;
    private LocalDate dataCompra;
    private LocalDate dataValidade;
    private float valor;

    public Mercadoria(String codigo, String nome, String descricao, LocalDate dataCompra, LocalDate dataValidade, float valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCompra = dataCompra;
        this.dataValidade = dataValidade;
        this.valor = valor;
    }
    
    public Mercadoria() {
        this.codigo = "sem codigo";
        this.nome = "sem nome";
        this.descricao = "sem descricao";
        this.valor = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return "Mercadoria{" + "codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", dataCompra=" + dataCompra + ", dataValidade=" + dataValidade + ", valor=" + valor + '}';
    }
}
