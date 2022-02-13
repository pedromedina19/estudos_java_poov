//PEDRO MEDINA

package Classes;

import java.time.LocalDate;

public class Venda {
    private int CodVenda;
    private String FK_CPF;
    private int FK_CodCaminhao;        
    private LocalDate Data;        
    private float valor;        
    
    public Venda() {
        this.CodVenda = 0;
        this.FK_CPF = "";
        this.FK_CodCaminhao = 0;
        this.Data = LocalDate.now();
        this.valor = 0;
    }
    
    public Venda(int CodVenda, String FK_CPF, int FK_CodCaminhao, LocalDate Data, float valor) {
        this.CodVenda = CodVenda;
        this.FK_CPF = FK_CPF;
        this.FK_CodCaminhao = FK_CodCaminhao;
        this.Data = Data;
        this.valor = valor;
    }

    public int getCodVenda() {
        return CodVenda;
    }

    public void setCodVenda(int CodVenda) {
        this.CodVenda = CodVenda;
    }

    public String getFK_CPF() {
        return FK_CPF;
    }

    public void setFK_CPF(String FK_CPF) {
        this.FK_CPF = FK_CPF;
    }

    public int getFK_CodCaminhao() {
        return FK_CodCaminhao;
    }

    public void setFK_CodCaminhao(int FK_CodCaminhao) {
        this.FK_CodCaminhao = FK_CodCaminhao;
    }

    public LocalDate getData() {
        return Data;
    }

    public void setData(LocalDate Data) {
        this.Data = Data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Venda{" + "CodVenda=" + CodVenda + ", FK_CPF=" + FK_CPF + ", FK_CodCaminhao=" + FK_CodCaminhao + ", Data=" + Data + ", valor=" + valor + '}';
    }

}
