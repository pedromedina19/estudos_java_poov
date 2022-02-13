package PedroC;
import CONEXAO.Conexao;
public class Chocolate {

    private String id_chocolate;
    private double peso;
    private double valorPorKg;
    private String tipoDoChocolate;
    private double valorDeVenda;
    private int temperaturaIdeal;
    private double qtdeDeLeite;

    public Chocolate(String id_chocolate, double peso, double valorPorKg,  String tipoDoChocolate, double valorDeVenda, int temperaturaIdeal, double qtdeDeLeite) {
        this.id_chocolate = id_chocolate;
        this.peso = peso;
        this.valorPorKg = valorPorKg;
        this.tipoDoChocolate = tipoDoChocolate;
        this.valorDeVenda = valorDeVenda;
        this.temperaturaIdeal = temperaturaIdeal;
        this.qtdeDeLeite = qtdeDeLeite;
    }

    public Chocolate() {
        this.id_chocolate = "";
        this.peso = -1;
        this.valorPorKg = -1;
        this.tipoDoChocolate = "";
        this.valorDeVenda = -1;
        this.temperaturaIdeal = 0;
        this.qtdeDeLeite = -1;
    }

    public String getId_chocolate() {
        return id_chocolate;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorPorKg() {
        return valorPorKg;
    }

    public String getTipoDoChocolate() {
        return tipoDoChocolate;
    }

    public double getValorDeVenda() {
        return valorDeVenda;
    }

    public int getTemperaturaIdeal() {
        return temperaturaIdeal;
    }

   

    public void setId_chocolate(String id_chocolate) {
        this.id_chocolate = id_chocolate;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setValorPorKg(double valorPorKg) {
        this.valorPorKg = valorPorKg;
    }

    public void setTipoDoChocolate(String tipoDoChocolate) {
        this.tipoDoChocolate = tipoDoChocolate;
    }

    public void setValorDeVenda(double valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public void setTemperaturaIdeal(int temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    public double getQtdeDeLeite() {
        return qtdeDeLeite;
    }

    public void setQtdeDeLeite(double qtdeDeLeite) {
        this.qtdeDeLeite = qtdeDeLeite;
    }

   
    @Override
    public String toString() {
        return "Chocolate{" + "id_chocolate=" + id_chocolate + ", peso=" + peso + ", valorPorKg=" + valorPorKg + ", tipoDoChocolate=" + tipoDoChocolate + ", valorDeVenda=" + valorDeVenda +", qtdeDeLeite=" + qtdeDeLeite + '}';
    }

}
