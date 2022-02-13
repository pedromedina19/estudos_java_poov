/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PedroC;
import CONEXAO.Conexao;
/**
 *
 * @author PEDRO
 */
public class Chocolate_Pedido {
     private int id_chocolate_pedido;
   private int quantidade;
    private int fk_id_pedido;
    private String fk_id_chocolate;

    public Chocolate_Pedido(int id_chocolate_pedido, int quantidade, int fk_id_pedido, String fk_id_chocolate) {
        this.id_chocolate_pedido = id_chocolate_pedido;
        this.quantidade = quantidade;
        this.fk_id_pedido = fk_id_pedido;
        this.fk_id_chocolate = fk_id_chocolate;
    }
    public Chocolate_Pedido() {
        this.id_chocolate_pedido = 0;
        this.quantidade = 0;
        this.fk_id_pedido = 0;
        this.fk_id_chocolate = "";
    }

    public int getId_chocolate_pedido() {
        return id_chocolate_pedido;
    }

    public void setId_chocolate_pedido(int id_chocolate_pedido) {
        this.id_chocolate_pedido = id_chocolate_pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getFk_id_pedido() {
        return fk_id_pedido;
    }

    public void setFk_id_pedido(int fk_id_pedido) {
        this.fk_id_pedido = fk_id_pedido;
    }

    public String getFk_id_chocolate() {
        return fk_id_chocolate;
    }

    public void setFk_id_chocolate(String fk_id_chocolate) {
        this.fk_id_chocolate = fk_id_chocolate;
    }

    @Override
    public String toString() {
        return "Chocolate_Pedido{" + "id_chocolate_pedido=" + id_chocolate_pedido + ", quantidade=" + quantidade + ", fk_id_pedido=" + fk_id_pedido + ", fk_id_chocolate=" + fk_id_chocolate + '}';
    }
    
    
    
    
    
    
    

}
