
package PedroC;

import java.time.LocalDateTime;
import CONEXAO.Conexao;

public class Pedidos {

    //ATRIBUTOS
    private int id_pedido;
    private LocalDateTime data;
    private LocalDateTime prazoParaEntrega;
    private String fk_cpf;
    
    
    //CONSTRUTORES
      public Pedidos() {
        this.id_pedido = 0;
        this.data = LocalDateTime.now();
        this.prazoParaEntrega = LocalDateTime.now();
        this.fk_cpf = "";
    }
    
    
    
    public Pedidos(int id_pedido, LocalDateTime data, LocalDateTime prazoParaEntrega, String fk_cpf) {
        this.id_pedido = id_pedido;
        this.data = data;
        this.prazoParaEntrega = prazoParaEntrega;
        this.fk_cpf = fk_cpf;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getPrazoParaEntrega() {
        return prazoParaEntrega;
    }

    public void setPrazoParaEntrega(LocalDateTime prazoParaEntrega) {
        this.prazoParaEntrega = prazoParaEntrega;
    }

    public String getFk_cpf() {
        return fk_cpf;
    }

    public void setFk_cpf(String fk_cpf) {
        this.fk_cpf = fk_cpf;
    }
    
    
       
   //TO STRING

    @Override
    public String toString() {
        return "Pedidos{" + "id_pedido=" + id_pedido + ", data=" + data + ", prazoParaEntrega=" + prazoParaEntrega + ", fk_cpf=" + fk_cpf + '}';
    }
    
    
    
    

    
    
    

    
}
