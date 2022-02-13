/*
Nome: Pedro Lucas Medina Pereira
Disciplina: POOV
Data: 29/09/2021
 */
package principal;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Servidor {

    public static void main(String[] args) {
        
        Tela tela = new Tela();
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);

        Socket socket;
        int portaServidor = 8000;  
        
        ArrayList <Mercadoria> listaM = new ArrayList();
        Cliente c = new Cliente();
        
        String nomeCliente, nomeMerc, cpf , codigo, descricao, dataCompra, dataValidade;
        float valor;
        LocalDate auxCompra , auxValidade;
        try{           
            ServerSocket serverSocket = new ServerSocket(portaServidor);
            System.out.println("\n Servidor ativo.");
            while(true) 
            {
                socket = serverSocket.accept();
                ObjectOutputStream envia = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream recebe = new ObjectInputStream(socket.getInputStream());
               
                nomeCliente = recebe.readUTF();
                nomeMerc = recebe.readUTF();
                codigo = recebe.readUTF();
                cpf = recebe.readUTF();
                descricao = recebe.readUTF();
                valor = recebe.readFloat();
                dataCompra = recebe.readUTF();
                dataValidade = recebe.readUTF();
                //transformando em LocalDate 
                // padrão dos Estados Unidos exemplo: 2002-Jan-31 
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
                formatter = formatter.withLocale( Locale.US ); 
                auxCompra = LocalDate.parse(dataCompra,formatter);
                auxValidade = LocalDate.parse(dataValidade,formatter);
                
                listaM.add(new Mercadoria(codigo,nomeMerc,descricao,auxCompra,auxValidade,valor));
                c.setCpf(cpf);
                c.setNome(nomeCliente);
                
                System.out.println("\n\n Cliente: "+nomeCliente);
                System.out.println("\n\n Mercadoria: "+nomeMerc);
                System.out.println("\n\n Descrição: "+descricao);
                System.out.println("\n\n Data da compra: "+auxCompra);
                System.out.println("\n\n Data de validade: "+auxValidade);
                System.out.println("\n\n Preço: "+valor);
                System.out.println("\n\n _______________________ ");
                Map<Cliente, ArrayList<Mercadoria>> mapa = new HashMap<>();
                mapa.put(tela.cliente, tela.listaMerc);
                envia.flush();
            }
            
        } catch (IOException e) {
            System.out.println("Erro:" + e.getMessage());       
        }
    }  
}