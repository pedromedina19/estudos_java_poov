//PEDRO MEDINA
package Classes;

public class Caminhao {
    private int CodCaminhao;
    private String Modelo;
    private float Potencia;
    
    public Caminhao() {
        this.CodCaminhao = 0;
        this.Modelo = "";
        this.Potencia = 0;
        
    }
    
    public Caminhao(int CodSapato, String Modelo, float Potencia) {
        this.CodCaminhao = CodSapato;
        this.Modelo = Modelo;
        this.Potencia = Potencia;
        
    }

    public int getCodCaminhao() {
        return CodCaminhao;
    }

    public void setCodCaminhao(int CodCaminhao) {
        this.CodCaminhao = CodCaminhao;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }
    
    public float getPotencia() {
        return Potencia;
    }

    public void setPotencia(float Potencia) {
        this.Potencia = Potencia;
    }

    @Override
    public String toString() {
        return "Caminhao{" + "CodCaminhao=" + CodCaminhao + ", Modelo=" + Modelo + ", Potencia=" + Potencia +'}';
    }

    
}
