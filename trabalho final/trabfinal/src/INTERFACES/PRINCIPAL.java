/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACES;
import CONEXAO.Conexao;
import PedroC.Pedidos;
import PedroC.Chocolate_Pedido;
import PedroC.Chocolate;
import PedroC.Cliente;
import DAOS.DAOCHOCOLATE_PEDIDO;
import DAOS.DAOCHOCO;
import DAOS.DAOCLIENTE;
import DAOS.DAOPEDIDO;
import java.awt.Desktop;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PEDRO
 */
public class PRINCIPAL extends javax.swing.JFrame {

    //LISTAS
    DefaultTableModel dtm_tabela;
    DefaultTableModel dtm_tabela2;
    
    DefaultTableModel dtm_tabelacara;
     DefaultTableModel dtm_tabelavalorporkg;
     
     DefaultTableModel dtm_tabelapedidos;
     DefaultTableModel dtm_tabelabd;
     
      DefaultTableModel dtm_tabelapedidosantigos;
    ArrayList<Cliente> LISTADECLIENTES = new ArrayList();
    ArrayList<Chocolate> LISTADECHOCOS = new ArrayList();
    DefaultTableModel dtm_tabelaCHOCO;

    ArrayList<Cliente> LISTADECLIENTESNOME = new ArrayList();
    
    ArrayList<Pedidos> LISTADEPEDIDOS=new ArrayList();
    
    
    ArrayList<Chocolate_Pedido> LISTADECHOCOSPEDIDOS=new ArrayList();
    

    public PRINCIPAL() throws SQLException, ClassNotFoundException {
        initComponents();
        
        //PEDIDO int id_pedido, LocalDateTime data, LocalDateTime prazoParaEntrega, String fk_cpf
        dtm_tabelapedidos=new DefaultTableModel(null,new String[]{"ID_PEDIDO","DATA","PRAZO PARA ENTREGA","FK_CPF"});
        dtm_tabelapedidosantigos=new DefaultTableModel(null,new String[]{"ID_PEDIDO","DATA","PRAZO PARA ENTREGA","FK_CPF"});
        dtm_tabelaCHOCO = new DefaultTableModel(null, new String[]{"ID_CHOCO", "PESO", "VALORPORKG", "TIPODOCHOCOLATE", "VALORDEVENDA", "TEMPERATURAIDEAL", "QTDEDELEITE"});
        dtm_tabelacara=new DefaultTableModel(null, new String[]{"ID_CHOCO", "PESO", "VALORPORKG", "TIPODOCHOCOLATE", "VALORDEVENDA", "TEMPERATURAIDEAL", "QTDEDELEITE"});
        dtm_tabelavalorporkg=new DefaultTableModel(null, new String[]{"ID_CHOCO", "PESO", "VALORPORKG", "TIPODOCHOCOLATE", "VALORDEVENDA", "TEMPERATURAIDEAL", "QTDEDELEITE"});
        //int id_bicicleta_pedido, int quantidade, int fk_id_pedido, String fk_id_bicicleta
        dtm_tabelabd=new DefaultTableModel(null,new String[]{"ID_CHOCO_PEDIDO","QTD","FK_PEDIDO","FK_CHOCO"});
        dtm_tabela = new DefaultTableModel(null, new String[]{"CPF", "NOME", "TELEFONE", "ENDEREÇO", "INSTAGRAM", "FACEBOOK", "CARTÃO DE CREDITO"});
        dtm_tabela2 = new DefaultTableModel(null, new String[]{"CPF", "NOME", "TELEFONE", "ENDEREÇO", "INSTAGRAM", "FACEBOOK", "CARTÃO DE CREDITO"});
        ComboMudanca.removeAllItems();
        ComboMudanca.addItem("PRINCIPAL");
        ComboMudanca.addItem("CLIENTE");
        ComboMudanca.addItem("CHOCOLATES");
        ComboMudanca.addItem("PEDIDOS");
        ComboMudanca.addItem("PEDIDOS DE CHOCOLATES"); 
        TDPEDIDOS();
        try {
            atualizarTabela();
           

        } catch (SQLException ex) {
            System.out.print("SQLException: " + ex.toString());
        } catch (ClassNotFoundException ex) {
            System.out.print("ClassNotFoundException: " + ex.toString());
        }
        ATUALIZARCHOCOSTABELA();
        TDCHOCOPEDIDOS();
        MAIN.setVisible(true);
        CHOCOS.setVisible(false);
        CHOCOSPEDIDOS.setVisible(false);
        CLIENTE.setVisible(false);
        PEDIDOS.setVisible(false);

    }
    
    
    private void TDPEDIDOS() throws SQLException, ClassNotFoundException
    {
        PutTabelaPedidos.setModel(dtm_tabelapedidos);
        dtm_tabelapedidos.setNumRows(0);
        DAOPEDIDO daonovo=new DAOPEDIDO();
        
        LISTADEPEDIDOS=daonovo.TODOSOSPEDIDOS();
         LISTADEPEDIDOS.forEach((p) -> {
            dtm_tabelapedidos.addRow(new Object[]{p.getId_pedido(),p.getData(),p.getPrazoParaEntrega(),p.getFk_cpf()});
        });
        
    }
    
    
    private void TDCHOCOPEDIDOS() throws SQLException, ClassNotFoundException
    {
        PUTTABELABK.setModel(dtm_tabelabd);
        dtm_tabelabd.setNumRows(0);
        DAOCHOCOLATE_PEDIDO daonovo=new DAOCHOCOLATE_PEDIDO();
        
        LISTADECHOCOSPEDIDOS=daonovo.TODOSOSPEDIDOSDECHOCO();
          LISTADECHOCOSPEDIDOS.forEach((p) -> {
            dtm_tabelabd.addRow(new Object[]{p.getId_chocolate_pedido(),p.getQuantidade(),p.getFk_id_pedido(),p.getFk_id_chocolate()});
        });
        
    }

    private void atualizarTabela() throws SQLException, ClassNotFoundException {
        PutTabelaCliente.setModel(dtm_tabela);
        dtm_tabela.setNumRows(0);
        DAOCLIENTE daonovo = new DAOCLIENTE();
        LISTADECLIENTES = daonovo.TODOSOSCLIENTES();
        QTDCLIENTES.setText("QTD:" + LISTADECLIENTES.size());
        LISTADECLIENTES.forEach((p) -> {
            dtm_tabela.addRow(new Object[]{p.getCpf(), p.getNome(), p.getTelefone(), p.getEndereco(), p.getEnd_instagram(), p.getEnd_facebook(), p.getCartaoDeCredito()});
        });
    }

    
    private void ATUALIZARCHOCOSTABELA() throws SQLException, ClassNotFoundException {
        PutTabelaChoco.setModel(dtm_tabelaCHOCO);
        dtm_tabelaCHOCO.setNumRows(0);
        DAOCHOCO daonovo = new DAOCHOCO();
        LISTADECHOCOS = daonovo.TODOSOSCHOCOS();
        QTDCHOCOS.setText("QTD:" + LISTADECHOCOS.size());
        LISTADECHOCOS.forEach((p) -> {
            dtm_tabelaCHOCO.addRow(new Object[]{p.getId_chocolate(), p.getPeso(), p.getValorPorKg(), p.getTipoDoChocolate(), p.getValorDeVenda(), p.getTemperaturaIdeal(), p.getQtdeDeLeite(), });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Mostragem = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TABELACLIENTES = new javax.swing.JScrollPane();
        PutTabelaCliente = new javax.swing.JTable();
        QTDCLIENTES = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TABELACHOCO = new javax.swing.JScrollPane();
        PutTabelaChoco = new javax.swing.JTable();
        QTDCHOCOS = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TABELAPEDIDOS = new javax.swing.JScrollPane();
        PutTabelaPedidos = new javax.swing.JTable();
        CIMA = new javax.swing.JButton();
        BAIXO = new javax.swing.JButton();
        TUDO = new javax.swing.JLayeredPane();
        ComboMudanca = new javax.swing.JComboBox<>();
        MAIN = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CLIENTE = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        INSERIRCLIENTE = new javax.swing.JPanel();
        CPFTEXTO = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PUTTELEFONE = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        PUTNOME = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PUTENDERECO = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        PUTFACEBOOK = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PUTINSTAGRAM = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        PUTCARTAO = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        INSERTCLIENTE = new javax.swing.JButton();
        PUTCPF = new javax.swing.JFormattedTextField();
        ATUALIZARCLIENTE = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        PUTTELEFONE1 = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        PUTNOME1 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PUTENDERECO1 = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        PUTFACEBOOK1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        PUTINSTAGRAM1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        PUTCARTAO1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        CPFTEXTO1 = new javax.swing.JLabel();
        PUTCPF1 = new javax.swing.JFormattedTextField();
        BUSCAR = new javax.swing.JButton();
        CLIENTEENCONTRADO = new javax.swing.JLabel();
        AtualizarCliente = new javax.swing.JButton();
        BUSCAREREMOVER = new javax.swing.JPanel();
        CPFTEXTO2 = new javax.swing.JLabel();
        PUTCPF2 = new javax.swing.JFormattedTextField();
        BUSCAR1 = new javax.swing.JButton();
        CLIENTEENCONTRADO1 = new javax.swing.JLabel();
        LIMPAR = new javax.swing.JButton();
        Remover = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        PUTNOMEBUSCA = new javax.swing.JTextField();
        BUSCAR2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TABELACLIENTESNOME = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        CHOCOS = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        PUTIDCHOCO = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        PUTPESO = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        PUTVALORPORKG = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        PUTVALORDEVENDA = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        LIMPARCHOCO = new javax.swing.JButton();
        INSERIRCHOCO = new javax.swing.JButton();
        PUTTIPODOCHOCOLATE = new javax.swing.JTextField();
        PUTTEMPERATURAIDEAL = new javax.swing.JTextField();
        PUTQTDEDELEITE = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        PUTIDCHOCO1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        PUTPESO1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        PUTVALORPORKG1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        PUTVALORDEVENDA1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        LIMPARCHOCO1 = new javax.swing.JButton();
        ATUALIZARCHOCO = new javax.swing.JButton();
        PUTTEMPERATURAIDEAL1 = new javax.swing.JTextField();
        PUTTIPODOCHOCOLATE1 = new javax.swing.JTextField();
        PUTQTDEDELEITE1 = new javax.swing.JTextField();
        BUSCARCHOCO = new javax.swing.JButton();
        BUSCARCHOCOLABEL = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        PUTIDCHOCO2 = new javax.swing.JTextField();
        REMOVERCHOCO = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TABELACARA = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        TABELACHOCOSVALORPORKG = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        MOSTRARINFORMACOES = new javax.swing.JButton();
        PEDIDOS = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        IDPEDIDO = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        PUTPRAZO = new javax.swing.JFormattedTextField();
        jLabel41 = new javax.swing.JLabel();
        PUTCPF3 = new javax.swing.JFormattedTextField();
        LIMPARPEDIDO = new javax.swing.JButton();
        INSERIRP = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        LIMPARPEDIDO1 = new javax.swing.JButton();
        UPTADEP = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        PUTID_PEDIDO = new javax.swing.JTextField();
        BUSCARPEDIDO = new javax.swing.JButton();
        IDPEDIDOENCONTRADO = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        PUTPRAZO1 = new javax.swing.JFormattedTextField();
        jLabel44 = new javax.swing.JLabel();
        PUTCPF4 = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        LIMPARPEDIDO2 = new javax.swing.JButton();
        REMOVERP = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        PUTID_PEDIDO1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TABELAPEDIDOSANTIGOS = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        PUTCPF5 = new javax.swing.JFormattedTextField();
        CHOCOSPEDIDOS = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        IDCHOCOPEDIDO = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        PUTQTD = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        PUTFKCHOCO = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        PUTIDPEDIDO = new javax.swing.JTextField();
        INSERIRBJ = new javax.swing.JButton();
        LIMPAR1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        PUTQTDU = new javax.swing.JTextField();
        ATUALIZARCHOCOPEDIDO = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        BUSCARUIDCHOCO = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        PUTIDPEDIDOU = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        PUTIDCHOCOU = new javax.swing.JTextField();
        BUSCARU = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        REMOVERQJ = new javax.swing.JButton();
        LIMPAR2 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        PUTID_CHOCO_PEDIDO = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        PUTTABELABK = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Salvartxt = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Mostragem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("CLIENTES");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        PutTabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TABELACLIENTES.setViewportView(PutTabelaCliente);

        QTDCLIENTES.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("CHOCOLATES");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        PutTabelaChoco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TABELACHOCO.setViewportView(PutTabelaChoco);
        PutTabelaChoco.getAccessibleContext().setAccessibleName("");

        QTDCHOCOS.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("PEDIDOS");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        PutTabelaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TABELAPEDIDOS.setViewportView(PutTabelaPedidos);

        CIMA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENS/up-arrow_icon-icons.com_70835.png"))); // NOI18N
        CIMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CIMAActionPerformed(evt);
            }
        });

        BAIXO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENS/down-arrow_icon-icons.com_71215.png"))); // NOI18N
        BAIXO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAIXOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MostragemLayout = new javax.swing.GroupLayout(Mostragem);
        Mostragem.setLayout(MostragemLayout);
        MostragemLayout.setHorizontalGroup(
            MostragemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MostragemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
            .addGroup(MostragemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MostragemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MostragemLayout.createSequentialGroup()
                        .addComponent(QTDCHOCOS, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(156, 156, 156))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MostragemLayout.createSequentialGroup()
                        .addGroup(MostragemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TABELAPEDIDOS, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TABELACLIENTES, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MostragemLayout.createSequentialGroup()
                                .addComponent(QTDCLIENTES, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CIMA, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BAIXO, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TABELACHOCO, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        MostragemLayout.setVerticalGroup(
            MostragemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostragemLayout.createSequentialGroup()
                .addGroup(MostragemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MostragemLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(MostragemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(QTDCLIENTES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE))
                    .addGroup(MostragemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(MostragemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CIMA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BAIXO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(TABELACLIENTES, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MostragemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(QTDCHOCOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TABELACHOCO, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TABELAPEDIDOS, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TUDO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        ComboMudanca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboMudancaItemStateChanged(evt);
            }
        });
        ComboMudanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboMudancaActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENS/CHOCOLATE.jpg")));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("FÁBRICA DE CHOCOLATES");
        jLabel5.setToolTipText("");

        javax.swing.GroupLayout MAINLayout = new javax.swing.GroupLayout(MAIN);
        MAIN.setLayout(MAINLayout);
        MAINLayout.setHorizontalGroup(
            MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MAINLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MAINLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(135, 135, 135))
        );
        MAINLayout.setVerticalGroup(
            MAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MAINLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );

        CPFTEXTO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CPFTEXTO.setText("  CPF");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("TELEFONE");

        try {
            PUTTELEFONE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PUTTELEFONE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PUTTELEFONE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUTTELEFONEActionPerformed(evt);
            }
        });
        PUTTELEFONE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PUTTELEFONEKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("NOME");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("ENDEREÇO");

        PUTENDERECO.setColumns(20);
        PUTENDERECO.setRows(5);
        jScrollPane1.setViewportView(PUTENDERECO);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("FACEBOOK");

        PUTFACEBOOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUTFACEBOOKActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("INSTAGRAM");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("CARTÃO DE CREDITO");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jButton1.setText("LIMPAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        INSERTCLIENTE.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        INSERTCLIENTE.setText("INSERIR");
        INSERTCLIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INSERTCLIENTEActionPerformed(evt);
            }
        });

        try {
            PUTCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PUTCPF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PUTCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PUTCPFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout INSERIRCLIENTELayout = new javax.swing.GroupLayout(INSERIRCLIENTE);
        INSERIRCLIENTE.setLayout(INSERIRCLIENTELayout);
        INSERIRCLIENTELayout.setHorizontalGroup(
            INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, INSERIRCLIENTELayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PUTTELEFONE))
                            .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PUTNOME))
                            .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PUTFACEBOOK))
                            .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PUTINSTAGRAM))
                            .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PUTCARTAO))
                            .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                                        .addComponent(CPFTEXTO, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(PUTCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(INSERTCLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        INSERIRCLIENTELayout.setVerticalGroup(
            INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CPFTEXTO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(INSERIRCLIENTELayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(PUTCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(PUTTELEFONE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTNOME, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTFACEBOOK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTINSTAGRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTCARTAO, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(INSERIRCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(INSERTCLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("INSERIR", INSERIRCLIENTE);

        ATUALIZARCLIENTE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("TELEFONE");
        ATUALIZARCLIENTE.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 31));

        try {
            PUTTELEFONE1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PUTTELEFONE1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PUTTELEFONE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUTTELEFONE1ActionPerformed(evt);
            }
        });
        ATUALIZARCLIENTE.add(PUTTELEFONE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 435, 39));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("NOME");
        ATUALIZARCLIENTE.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 73, 66, 26));
        ATUALIZARCLIENTE.add(PUTNOME1, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 68, 467, 42));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("ENDEREÇO");
        ATUALIZARCLIENTE.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 139, -1, -1));

        PUTENDERECO1.setColumns(20);
        PUTENDERECO1.setRows(5);
        jScrollPane2.setViewportView(PUTENDERECO1);

        ATUALIZARCLIENTE.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 128, 427, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("FACEBOOK");
        ATUALIZARCLIENTE.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 238, 106, 30));

        PUTFACEBOOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUTFACEBOOK1ActionPerformed(evt);
            }
        });
        ATUALIZARCLIENTE.add(PUTFACEBOOK1, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 235, 427, 43));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("INSTAGRAM");
        ATUALIZARCLIENTE.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 289, -1, 32));
        ATUALIZARCLIENTE.add(PUTINSTAGRAM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 289, 416, 36));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("CARTÃO DE CREDITO");
        ATUALIZARCLIENTE.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 332, -1, 37));
        ATUALIZARCLIENTE.add(PUTCARTAO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 333, 347, 41));

        jLabel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        ATUALIZARCLIENTE.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 585, 231));

        CPFTEXTO1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CPFTEXTO1.setText("BUSCAR CPF");
        ATUALIZARCLIENTE.add(CPFTEXTO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        PUTCPF1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        try {
            PUTCPF1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PUTCPF1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ATUALIZARCLIENTE.add(PUTCPF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 400, 30));

        BUSCAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BUSCAR.setText("BUSCAR");
        BUSCAR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        BUSCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCARActionPerformed(evt);
            }
        });
        ATUALIZARCLIENTE.add(BUSCAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, 90, 60));

        CLIENTEENCONTRADO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CLIENTEENCONTRADO.setText("CLIENTE:");
        CLIENTEENCONTRADO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        ATUALIZARCLIENTE.add(CLIENTEENCONTRADO, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 400, 50));

        AtualizarCliente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AtualizarCliente.setText("ATUALIZAR CLIENTE");
        AtualizarCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        AtualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizarClienteActionPerformed(evt);
            }
        });
        ATUALIZARCLIENTE.add(AtualizarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, 270, 80));

        jTabbedPane1.addTab("ATUALIZAR", ATUALIZARCLIENTE);

        CPFTEXTO2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CPFTEXTO2.setText("BUSCAR CPF");

        PUTCPF2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        try {
            PUTCPF2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PUTCPF2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BUSCAR1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BUSCAR1.setText("BUSCAR");
        BUSCAR1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        BUSCAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCAR1ActionPerformed(evt);
            }
        });

        CLIENTEENCONTRADO1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CLIENTEENCONTRADO1.setText("CLIENTE:");
        CLIENTEENCONTRADO1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        LIMPAR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LIMPAR.setText("LIMPAR");
        LIMPAR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        LIMPAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPARActionPerformed(evt);
            }
        });

        Remover.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Remover.setText("REMOVER CLIENTE");
        Remover.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoverActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("BUSCAR POR NOME");

        BUSCAR2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BUSCAR2.setText("BUSCAR");
        BUSCAR2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        BUSCAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCAR2ActionPerformed(evt);
            }
        });

        TABELACLIENTESNOME.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(TABELACLIENTESNOME);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setText("CLIENTES");
        jLabel20.setToolTipText("");

        javax.swing.GroupLayout BUSCAREREMOVERLayout = new javax.swing.GroupLayout(BUSCAREREMOVER);
        BUSCAREREMOVER.setLayout(BUSCAREREMOVERLayout);
        BUSCAREREMOVERLayout.setHorizontalGroup(
            BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BUSCAREREMOVERLayout.createSequentialGroup()
                .addGroup(BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(BUSCAREREMOVERLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(BUSCAREREMOVERLayout.createSequentialGroup()
                                    .addComponent(LIMPAR, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Remover, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(BUSCAREREMOVERLayout.createSequentialGroup()
                                    .addComponent(CLIENTEENCONTRADO1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BUSCAR1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BUSCAREREMOVERLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BUSCAREREMOVERLayout.createSequentialGroup()
                                        .addComponent(CPFTEXTO2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PUTCPF2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(BUSCAREREMOVERLayout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(PUTNOMEBUSCA, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(BUSCAR2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(BUSCAREREMOVERLayout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BUSCAREREMOVERLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BUSCAREREMOVERLayout.setVerticalGroup(
            BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BUSCAREREMOVERLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PUTCPF2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CPFTEXTO2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BUSCAR1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLIENTEENCONTRADO1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LIMPAR, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(Remover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(BUSCAREREMOVERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(PUTNOMEBUSCA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BUSCAR2, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("BUSCAR/REMOVER", BUSCAREREMOVER);

        javax.swing.GroupLayout CLIENTELayout = new javax.swing.GroupLayout(CLIENTE);
        CLIENTE.setLayout(CLIENTELayout);
        CLIENTELayout.setHorizontalGroup(
            CLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        CLIENTELayout.setVerticalGroup(
            CLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CLIENTELayout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("IDCHOCO");

        PUTIDCHOCO.setToolTipText("");
        PUTIDCHOCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUTIDCHOCOActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("PESO");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("VALOR POR KG");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("TIPO DO CHOCOLATE");

        PUTVALORDEVENDA.setToolTipText("");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("VALOR DE VENDA");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("TEMPERATURA IDEAL");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("QTD DE LEITE");

        LIMPARCHOCO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LIMPARCHOCO.setText("LIMPAR");
        LIMPARCHOCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPARCHOCOActionPerformed(evt);
            }
        });

        INSERIRCHOCO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        INSERIRCHOCO.setText("INSERIR CHOCO");
        INSERIRCHOCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INSERIRCHOCOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addGap(18, 18, 18)
                            .addComponent(PUTTIPODOCHOCOLATE, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(PUTIDCHOCO, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                                .addComponent(PUTPESO)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(LIMPARCHOCO, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                            .addComponent(INSERIRCHOCO, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(18, 18, 18)
                            .addComponent(PUTQTDEDELEITE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addGap(18, 18, 18)
                            .addComponent(PUTVALORPORKG, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(PUTVALORDEVENDA, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PUTTEMPERATURAIDEAL, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(388, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(PUTIDCHOCO, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTPESO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PUTVALORPORKG, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTTIPODOCHOCOLATE, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PUTVALORDEVENDA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTTEMPERATURAIDEAL, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTQTDEDELEITE, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(INSERIRCHOCO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LIMPARCHOCO, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        jTabbedPane2.addTab("INSERIR", jPanel1);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("IDCHOCO");

        PUTIDCHOCO1.setToolTipText("");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("PESO");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("VALOR POR KG");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setText("TIPO DO CHOCOLATE");

        PUTVALORDEVENDA1.setToolTipText("");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("VALOR DE VENDA");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setText("TEMPERATURA IDEAL");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setText("QTD DE LEITE");

        LIMPARCHOCO1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LIMPARCHOCO1.setText("LIMPAR");
        LIMPARCHOCO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPARCHOCO1ActionPerformed(evt);
            }
        });

        ATUALIZARCHOCO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ATUALIZARCHOCO.setText("ATUALIZAR CHOCO");
        ATUALIZARCHOCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATUALIZARCHOCOActionPerformed(evt);
            }
        });

        BUSCARCHOCO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        BUSCARCHOCO.setText("BUSCAR CHOCO");
        BUSCARCHOCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCARCHOCOActionPerformed(evt);
            }
        });

        BUSCARCHOCOLABEL.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        BUSCARCHOCOLABEL.setText("CHOCO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BUSCARCHOCOLABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(BUSCARCHOCO, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PUTQTDEDELEITE1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(9, 9, 9)
                                .addComponent(PUTIDCHOCO1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(LIMPARCHOCO1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ATUALIZARCHOCO, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel33))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(PUTTIPODOCHOCOLATE1)
                                    .addComponent(PUTVALORDEVENDA1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PUTTEMPERATURAIDEAL1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PUTVALORPORKG1)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(PUTPESO1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(407, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(PUTIDCHOCO1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BUSCARCHOCO, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(BUSCARCHOCOLABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(PUTPESO1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTVALORPORKG1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(PUTTIPODOCHOCOLATE1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTVALORDEVENDA1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PUTTEMPERATURAIDEAL1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PUTQTDEDELEITE1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LIMPARCHOCO1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(ATUALIZARCHOCO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(61, 61, 61))
        );

        jTabbedPane2.addTab("ATUALIZAR", jPanel2);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setText("IDCHOCO");

        PUTIDCHOCO2.setToolTipText("");

        REMOVERCHOCO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        REMOVERCHOCO.setText("REMOVER CHOCO");
        REMOVERCHOCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REMOVERCHOCOActionPerformed(evt);
            }
        });

        TABELACARA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(TABELACARA);

        TABELACHOCOSVALORPORKG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(TABELACHOCOSVALORPORKG);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel38.setText("CHOCOLATES MAIS CAROS");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel39.setText("CHOCOLATES COM MAIS LEITE");

        MOSTRARINFORMACOES.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        MOSTRARINFORMACOES.setText("MOSTRAR INFORMAÇOES");
        MOSTRARINFORMACOES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOSTRARINFORMACOESActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(146, 146, 146))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MOSTRARINFORMACOES, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(REMOVERCHOCO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(PUTIDCHOCO2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane5)
                                    .addComponent(jScrollPane4))
                                .addGap(0, 398, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(PUTIDCHOCO2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(REMOVERCHOCO, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(MOSTRARINFORMACOES, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("BUSCAR/REMOVER", jPanel3);

        javax.swing.GroupLayout CHOCOSLayout = new javax.swing.GroupLayout(CHOCOS);
        CHOCOS.setLayout(CHOCOSLayout);
        CHOCOSLayout.setHorizontalGroup(
            CHOCOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        CHOCOSLayout.setVerticalGroup(
            CHOCOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CHOCOSLayout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        IDPEDIDO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel40.setText("PRAZO PARA A ENTREGA");

        try {
            PUTPRAZO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-## ##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PUTPRAZO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PUTPRAZOKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PUTPRAZOKeyTyped(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setText("CPF DO CLIENTE");

        try {
            PUTCPF3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PUTCPF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUTCPF3ActionPerformed(evt);
            }
        });
        PUTCPF3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PUTCPF3KeyTyped(evt);
            }
        });

        LIMPARPEDIDO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LIMPARPEDIDO.setText("LIMPAR");
        LIMPARPEDIDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPARPEDIDOActionPerformed(evt);
            }
        });

        INSERIRP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        INSERIRP.setText("INSERIR PEDIDO");
        INSERIRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INSERIRPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PUTPRAZO))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PUTCPF3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(IDPEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(LIMPARPEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(INSERIRP, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(IDPEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(PUTPRAZO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTCPF3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(175, 175, 175)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LIMPARPEDIDO, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(INSERIRP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54))
        );

        jTabbedPane3.addTab("INSERIR PEDIDOS", jPanel4);

        LIMPARPEDIDO1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LIMPARPEDIDO1.setText("LIMPAR");
        LIMPARPEDIDO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPARPEDIDO1ActionPerformed(evt);
            }
        });

        UPTADEP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        UPTADEP.setText("UPTADE PEDIDO");
        UPTADEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPTADEPActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setText("BUSCAR ID_PEDIDO");

        BUSCARPEDIDO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BUSCARPEDIDO.setText("BUSCAR");
        BUSCARPEDIDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCARPEDIDOActionPerformed(evt);
            }
        });

        IDPEDIDOENCONTRADO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setText("PRAZO PARA A ENTREGA");

        try {
            PUTPRAZO1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-## ##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel44.setText("CPF DO CLIENTE");

        try {
            PUTCPF4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(LIMPARPEDIDO1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(UPTADEP, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PUTPRAZO1)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(IDPEDIDOENCONTRADO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PUTCPF4)))
                .addGap(43, 43, 43))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PUTID_PEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BUSCARPEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTPRAZO1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTCPF4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(IDPEDIDOENCONTRADO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PUTID_PEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(BUSCARPEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LIMPARPEDIDO1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(UPTADEP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54))
        );

        jTabbedPane3.addTab("UPTADE ", jPanel5);

        LIMPARPEDIDO2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LIMPARPEDIDO2.setText("LIMPAR");
        LIMPARPEDIDO2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPARPEDIDO2ActionPerformed(evt);
            }
        });

        REMOVERP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        REMOVERP.setText("REMOVER PEDIDO");
        REMOVERP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REMOVERPActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel45.setText("BUSCAR ID_PEDIDO");

        TABELAPEDIDOSANTIGOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(TABELAPEDIDOSANTIGOS);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel47.setText("PEDIDO ANTIGO");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel46.setText("BUSCAR PEDIDO ANTIGO");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        try {
            PUTCPF5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PUTCPF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUTCPF5ActionPerformed(evt);
            }
        });
        PUTCPF5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PUTCPF5KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(33, 33, 33)
                                .addComponent(PUTCPF5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PUTID_PEDIDO1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(LIMPARPEDIDO2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(REMOVERP, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel47)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PUTID_PEDIDO1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LIMPARPEDIDO2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(REMOVERP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTCPF5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("BUSCAR E REMOVER", jPanel6);

        javax.swing.GroupLayout PEDIDOSLayout = new javax.swing.GroupLayout(PEDIDOS);
        PEDIDOS.setLayout(PEDIDOSLayout);
        PEDIDOSLayout.setHorizontalGroup(
            PEDIDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PEDIDOSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        PEDIDOSLayout.setVerticalGroup(
            PEDIDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PEDIDOSLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        IDCHOCOPEDIDO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel48.setText("QUANTIDADE");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel49.setText("ID CHOCO");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel50.setText("ID PEDIDO");

        INSERIRBJ.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        INSERIRBJ.setText("INSERIR");
        INSERIRBJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INSERIRBJActionPerformed(evt);
            }
        });

        LIMPAR1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LIMPAR1.setText("LIMPAR");
        LIMPAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPAR1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(IDCHOCOPEDIDO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PUTQTD, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PUTIDPEDIDO)
                            .addComponent(PUTFKCHOCO))))
                .addContainerGap(116, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LIMPAR1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(INSERIRBJ, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IDCHOCOPEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTQTD, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(PUTFKCHOCO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTIDPEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(INSERIRBJ, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(LIMPAR1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("INSERIR", jPanel7);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel53.setText("QUANTIDADE");

        ATUALIZARCHOCOPEDIDO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ATUALIZARCHOCOPEDIDO.setText("ATUALIZAR CHOCO PEDIDO");
        ATUALIZARCHOCOPEDIDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATUALIZARCHOCOPEDIDOActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel54.setText("BUSCAR ID CHOCO PEDIDO");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel55.setText("ID PEDIDO");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel56.setText("ID CHOCO");

        BUSCARU.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BUSCARU.setText("BUSCAR");
        BUSCARU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCARUActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setText("LIMPAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PUTIDPEDIDOU, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PUTQTDU, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PUTIDCHOCOU, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(BUSCARU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel54)
                        .addGap(39, 39, 39)
                        .addComponent(BUSCARUIDCHOCO))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ATUALIZARCHOCOPEDIDO)))
                .addGap(23, 23, 23))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(BUSCARUIDCHOCO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PUTIDCHOCOU)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(BUSCARU, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTIDPEDIDOU, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUTQTDU, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(ATUALIZARCHOCOPEDIDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jTabbedPane4.addTab("UPTADE", jPanel8);

        REMOVERQJ.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        REMOVERQJ.setText("REMOVER CHOCO PEDIDO");
        REMOVERQJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REMOVERQJActionPerformed(evt);
            }
        });

        LIMPAR2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LIMPAR2.setText("LIMPAR");
        LIMPAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPAR2ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel52.setText("BUSCAR PELO ID_CHOCO_PEDIDO");

        PUTID_CHOCO_PEDIDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUTID_CHOCO_PEDIDOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(LIMPAR2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(REMOVERQJ))
                        .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(PUTID_CHOCO_PEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PUTID_CHOCO_PEDIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(REMOVERQJ, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LIMPAR2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        jTabbedPane4.addTab("REMOVER", jPanel9);

        PUTTABELABK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(PUTTABELABK);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel51.setText("CHOCOS PEDIDOS");
        jLabel51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        javax.swing.GroupLayout CHOCOSPEDIDOSLayout = new javax.swing.GroupLayout(CHOCOSPEDIDOS);
        CHOCOSPEDIDOS.setLayout(CHOCOSPEDIDOSLayout);
        CHOCOSPEDIDOSLayout.setHorizontalGroup(
            CHOCOSPEDIDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CHOCOSPEDIDOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CHOCOSPEDIDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addComponent(jTabbedPane4))
                .addContainerGap())
            .addGroup(CHOCOSPEDIDOSLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CHOCOSPEDIDOSLayout.setVerticalGroup(
            CHOCOSPEDIDOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CHOCOSPEDIDOSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TUDO.setLayer(ComboMudanca, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TUDO.setLayer(MAIN, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TUDO.setLayer(CLIENTE, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TUDO.setLayer(CHOCOS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TUDO.setLayer(PEDIDOS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TUDO.setLayer(CHOCOSPEDIDOS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TUDOLayout = new javax.swing.GroupLayout(TUDO);
        TUDO.setLayout(TUDOLayout);
        TUDOLayout.setHorizontalGroup(
            TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TUDOLayout.createSequentialGroup()
                .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TUDOLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(ComboMudanca, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TUDOLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CHOCOSPEDIDOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TUDOLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(PEDIDOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(15, 15, 15)))
            .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TUDOLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(CHOCOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(15, 15, 15)))
            .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TUDOLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(CLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(15, 15, 15)))
            .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TUDOLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(MAIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(15, 15, 15)))
        );
        TUDOLayout.setVerticalGroup(
            TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TUDOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboMudanca, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CHOCOSPEDIDOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TUDOLayout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(PEDIDOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE)))
            .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TUDOLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(CHOCOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(48, 48, 48)))
            .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TUDOLayout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(CLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(36, 36, 36)))
            .addGroup(TUDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TUDOLayout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(MAIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(36, 36, 36)))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENS/ICONEARQUIVO.png"))); // NOI18N
        jMenu1.setText("Arquivo");

        Salvartxt.setSelected(true);
        Salvartxt.setText("Salvar em Txt");
        Salvartxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENS/Txt.png"))); // NOI18N
        Salvartxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvartxtActionPerformed(evt);
            }
        });
        jMenu1.add(Salvartxt);

        jMenuBar1.add(jMenu1);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENS/SOBRE.png"))); // NOI18N
        jMenu3.setText("Sobre");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Sobre");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENS/SAIR.png"))); // NOI18N
        jMenu2.setText("Sair");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("SAIR");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(TUDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Mostragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Mostragem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TUDO, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalvartxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvartxtActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("SALVAR TXT");

     
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int retorno = fileChooser.showOpenDialog(this);
        String Caminhao = null;
        
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File Arquivo = fileChooser.getSelectedFile();
            Caminhao = Arquivo.getPath();
        }

        if (Caminhao.equals("") == true) {
            JOptionPane.showMessageDialog(null, "NÃO INSERIU O DIRETORIO.");
        } else {

            DAOCLIENTE daocliente = null;
            try {
                daocliente = new DAOCLIENTE();

            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            DAOPEDIDO daopedido = null;
            try {
                daopedido = new DAOPEDIDO();

            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            DAOCHOCO daochoco = null;
            try {
                daochoco = new DAOCHOCO();

            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

           
            DAOCHOCOLATE_PEDIDO daobd;
            daobd = null;
            try {
                daobd= new DAOCHOCOLATE_PEDIDO();

            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }


            LISTADECLIENTES.clear();

            LISTADECLIENTES = daocliente.TODOSOSCLIENTES();

            LISTADEPEDIDOS.clear();

            LISTADEPEDIDOS = daopedido.TODOSOSPEDIDOS();

            LISTADECHOCOSPEDIDOS.clear();
            LISTADECHOCOSPEDIDOS = daobd.TODOSOSPEDIDOSDECHOCO();

            LISTADECHOCOS.clear();
            LISTADECHOCOS= daochoco.TODOSOSCHOCOS();

            int i;
            String ca=Caminhao + "\\EXPORTACAOTXT.txt";
            try {
                String caminho = Caminhao + "\\EXPORTACAOTXT.txt";
                FileWriter arq = new FileWriter(caminho);
                PrintWriter gravarArq = new PrintWriter(arq);
                gravarArq.printf("\n\t\t\tCLIENTES");

                for (i = 0; i < LISTADECLIENTES.size(); i++) {
                    gravarArq.printf("\n" + LISTADECLIENTES.get(i).toString());
                }
                gravarArq.printf("\n\n\n\t\t\tCHOCOLATES");
                for (i = 0; i < LISTADECHOCOS.size(); i++) {
                    gravarArq.printf("\n" + LISTADECHOCOS.get(i).toString());
                }
                gravarArq.printf("\n\n\n\t\t\tPEDIDOS");
                for (i = 0; i < LISTADEPEDIDOS.size(); i++) {
                    gravarArq.printf("\n" + LISTADEPEDIDOS.get(i).toString());
                }
                gravarArq.printf("\n\n\n\t\t\tCHOCOLATES PEDIDOS");
                for (i = 0; i < LISTADECHOCOSPEDIDOS.size(); i++) {
                    gravarArq.printf("\n" + LISTADECHOCOSPEDIDOS.get(i).toString());
                }

                arq.close();

            } catch (IOException ex) {
                Logger.getLogger(PRINCIPAL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(ca));
        } catch (IOException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        }

    }//GEN-LAST:event_SalvartxtActionPerformed

    private void ComboMudancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboMudancaActionPerformed


    }//GEN-LAST:event_ComboMudancaActionPerformed

    private void ComboMudancaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboMudancaItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {

            /*
           ComboMudanca.addItem("PRINCIPAL");
           ComboMudanca.addItem("CLIENTE");
           ComboMudanca.addItem("CHOCOLATES");
           ComboMudanca.addItem("PEDIDOS");
           ComboMudanca.addItem("PEDIDOS DE CHOCOLATES");
          
          
             */
            String opcao = ComboMudanca.getSelectedItem().toString();
            if (opcao.equals("PRINCIPAL") == true) {
                MAIN.setVisible(true);
                CHOCOS.setVisible(false);
                CHOCOSPEDIDOS.setVisible(false);
                CLIENTE.setVisible(false);
                PEDIDOS.setVisible(false);
            }

            if (opcao.equals("CLIENTE") == true) {
                MAIN.setVisible(false);
                CHOCOS.setVisible(false);
                CHOCOSPEDIDOS.setVisible(false);
                CLIENTE.setVisible(true);
                PEDIDOS.setVisible(false);
            }

            if (opcao.equals("CHOCOLATES") == true) {
                MAIN.setVisible(false);
                CHOCOS.setVisible(true);
                CHOCOSPEDIDOS.setVisible(false);
                CLIENTE.setVisible(false);
                PEDIDOS.setVisible(false);
            }

            if (opcao.equals("PEDIDOS") == true) {
                MAIN.setVisible(false);
                CHOCOS.setVisible(false);
                CHOCOSPEDIDOS.setVisible(false);
                CLIENTE.setVisible(false);
                PEDIDOS.setVisible(true);
            }

            if (opcao.equals("PEDIDOS DE CHOCOLATES") == true) {
                MAIN.setVisible(false);
                CHOCOS.setVisible(false);
                CHOCOSPEDIDOS.setVisible(true);
                CLIENTE.setVisible(false);
                PEDIDOS.setVisible(false);
            }

        }


    }//GEN-LAST:event_ComboMudancaItemStateChanged

    private void PUTTELEFONEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUTTELEFONEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTTELEFONEActionPerformed

    private void PUTTELEFONEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PUTTELEFONEKeyTyped
        String chars = "0987654321";

        if (!chars.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
    }//GEN-LAST:event_PUTTELEFONEKeyTyped

    private void PUTFACEBOOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUTFACEBOOKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTFACEBOOKActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //LIMPAR DEPOIS DE USAR
        PUTCARTAO.setText("");
        PUTNOME.setText("");
        PUTCPF.setText("");
        PUTFACEBOOK.setText("");
        PUTTELEFONE.setText("");
        PUTINSTAGRAM.setText("");
        PUTENDERECO.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void INSERTCLIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INSERTCLIENTEActionPerformed

        boolean val = false;

        if (PUTCARTAO.getText().equals("") == true) {
            val = true;
        }

        if (PUTNOME.getText().equals("") == true) {
            val = true;
        }

        if (PUTCPF.getText().equals("") == true) {
            val = true;
        }

        if (PUTFACEBOOK.getText().equals("") == true) {
            val = true;
        }

        if (PUTTELEFONE.getText().equals("") == true) {
            val = true;
        }

        if (PUTINSTAGRAM.getText().equals("") == true) {
            val = true;
        }

        if (PUTENDERECO.getText().equals("") == true) {
            val = true;
        }

        if (val == true) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSIVEL ADICIONAR O CLIENTE\nTENTE NOVAMENTE!");

        } else {
            //CRIAR UM NOVO CLIENTE
            Cliente novocliente = new Cliente(PUTCPF.getText(), PUTNOME.getText(), PUTTELEFONE.getText(), PUTENDERECO.getText(), PUTINSTAGRAM.getText(), PUTFACEBOOK.getText(), PUTCARTAO.getText());

            try {
                DAOCLIENTE dao = new DAOCLIENTE();

                dao.INSERIRCLIENTEBANCO(novocliente);
                JOptionPane.showMessageDialog(null, "CLIENTE ADICIONADO!");

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        try {
            atualizarTabela();

        } catch (SQLException ex) {
            System.out.print("SQLException: " + ex.toString());
        } catch (ClassNotFoundException ex) {
            System.out.print("ClassNotFoundException: " + ex.toString());
        }

        //LIMPAR DEPOIS DE USAR
        PUTCARTAO.setText("");
        PUTNOME.setText("");
        PUTCPF.setText("");
        PUTFACEBOOK.setText("");
        PUTTELEFONE.setText("");
        PUTINSTAGRAM.setText("");
        PUTENDERECO.setText("");
    }//GEN-LAST:event_INSERTCLIENTEActionPerformed

    private void PUTCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PUTCPFKeyTyped
        String chars = "0987654321";

        if (!chars.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
    }//GEN-LAST:event_PUTCPFKeyTyped

    private void PUTTELEFONE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUTTELEFONE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTTELEFONE1ActionPerformed

    private void PUTFACEBOOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUTFACEBOOK1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTFACEBOOK1ActionPerformed

    private void BUSCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCARActionPerformed
        if (PUTCPF1.getText().equals("") == true) {
            JOptionPane.showMessageDialog(null, "ERRO,CPF NÃO DIGITADO!!");

        } else {
            Cliente encontrar = new Cliente();
            DAOCLIENTE dao = null;
            try {
                dao = new DAOCLIENTE();
            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            encontrar = dao.RETORNAR(PUTCPF1.getText());

            if (encontrar == null) {
                CLIENTEENCONTRADO.setText("CLIENTE:NÃO ENCONTRADO");
            } else {
                CLIENTEENCONTRADO.setText("CLIENTE:ENCONTRADO CPF " + PUTCPF1.getText());
                PUTCARTAO1.setText("" + encontrar.getCartaoDeCredito());
                PUTENDERECO1.setText("" + encontrar.getEndereco());
                PUTFACEBOOK1.setText("" + encontrar.getEnd_facebook());
                PUTNOME1.setText("" + encontrar.getNome());
                PUTTELEFONE1.setText("" + encontrar.getTelefone());
                PUTINSTAGRAM1.setText("" + encontrar.getEnd_instagram());

            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BUSCARActionPerformed

    private void AtualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizarClienteActionPerformed

        if (PUTCPF1.getText().equals("") == true || CLIENTEENCONTRADO.getText().equals("CLIENTE:NÃO ENCONTRADO") == true || CLIENTEENCONTRADO.getText().equals("CLIENTE:") == true) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSIVEL ATUALIZAR O CLIENTE\nTENTE NOVAMENTE!");
        } else {
            //CRIAR UM NOVO CLIENTE
            Cliente novocliente = new Cliente(PUTCPF1.getText(), PUTNOME1.getText(), PUTTELEFONE1.getText(), PUTENDERECO1.getText(), PUTINSTAGRAM1.getText(), PUTFACEBOOK1.getText(), PUTCARTAO1.getText());

            try {
                DAOCLIENTE dao = new DAOCLIENTE();

                dao.UPTADE(novocliente);
                JOptionPane.showMessageDialog(null, "CLIENTE ATUALIZADO!");

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        try {
            atualizarTabela();

        } catch (SQLException ex) {
            System.out.print("SQLException: " + ex.toString());
        } catch (ClassNotFoundException ex) {
            System.out.print("ClassNotFoundException: " + ex.toString());
        }

        //LIMPAR DEPOIS DE USAR
        PUTCARTAO1.setText("");
        PUTNOME1.setText("");
        PUTCPF1.setText("");
        PUTFACEBOOK1.setText("");
        PUTTELEFONE1.setText("");
        PUTINSTAGRAM1.setText("");
        PUTENDERECO1.setText("");
    }//GEN-LAST:event_AtualizarClienteActionPerformed

    private void BUSCAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCAR1ActionPerformed
        if (PUTCPF2.getText().equals("") == true) {
            JOptionPane.showMessageDialog(null, "ERRO,CPF NÃO DIGITADO!!");

        } else {
            Cliente encontrar = new Cliente();
            DAOCLIENTE dao = null;
            try {
                dao = new DAOCLIENTE();
            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            encontrar = dao.RETORNAR(PUTCPF2.getText());

            if (encontrar == null) {
                CLIENTEENCONTRADO1.setText("CLIENTE:NÃO ENCONTRADO");
            } else {
                CLIENTEENCONTRADO1.setText("CLIENTE:ENCONTRADO CPF " + PUTCPF2.getText());

            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BUSCAR1ActionPerformed

    private void LIMPARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPARActionPerformed

        PUTCPF2.setText("");
        CLIENTEENCONTRADO1.setText("CLIENTE:");

    }//GEN-LAST:event_LIMPARActionPerformed

    private void RemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoverActionPerformed

        if (CLIENTEENCONTRADO1.getText().equals("CLIENTE:") == true || PUTCPF2.getText().equals("") == true || CLIENTEENCONTRADO1.getText().equals("CLIENTE:NÃO ENCONTRADO") == true) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSIVEL REMOVER O CLIENTE\nTENTE NOVAMENTE!");
        } else {
            //CRIAR UM NOVO CLIENTE

            try {
                DAOCLIENTE dao = new DAOCLIENTE();

                dao.REMOVER(PUTCPF2.getText());
                JOptionPane.showMessageDialog(null, "CLIENTE REMOVIDO!");

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            atualizarTabela();

        } catch (SQLException ex) {
            System.out.print("SQLException: " + ex.toString());
        } catch (ClassNotFoundException ex) {
            System.out.print("ClassNotFoundException: " + ex.toString());
        }

        PUTCPF2.setText("");
        CLIENTEENCONTRADO1.setText("CLIENTE:");

    }//GEN-LAST:event_RemoverActionPerformed

    private void BUSCAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCAR2ActionPerformed

        TABELACLIENTESNOME.setModel(dtm_tabela2);
        dtm_tabela2.setNumRows(0);
        DAOCLIENTE daonovo = null;
        try {
            daonovo = new DAOCLIENTE();
        } catch (SQLException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        LISTADECLIENTESNOME = daonovo.MESMONOME(PUTNOMEBUSCA.getText());
        LISTADECLIENTESNOME.forEach((p) -> {
            dtm_tabela2.addRow(new Object[]{p.getCpf(), p.getNome(), p.getTelefone(), p.getEndereco(), p.getEnd_instagram(), p.getEnd_facebook(), p.getCartaoDeCredito()});
        });
    }//GEN-LAST:event_BUSCAR2ActionPerformed

    private void CIMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CIMAActionPerformed
        PutTabelaCliente.setModel(dtm_tabela);
        dtm_tabela.setNumRows(0);
        DAOCLIENTE daonovo = null;
        try {
            daonovo = new DAOCLIENTE();

        } catch (SQLException ex) {
            Logger.getLogger(PRINCIPAL.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PRINCIPAL.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        LISTADECLIENTES = daonovo.NOMECIMA();
        LISTADECLIENTES.forEach((p) -> {
            dtm_tabela.addRow(new Object[]{p.getCpf(), p.getNome(), p.getTelefone(), p.getEndereco(), p.getEnd_instagram(), p.getEnd_facebook(), p.getCartaoDeCredito()});
        });
    }//GEN-LAST:event_CIMAActionPerformed

    private void BAIXOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAIXOActionPerformed
        PutTabelaCliente.setModel(dtm_tabela);
        dtm_tabela.setNumRows(0);
        DAOCLIENTE daonovo = null;
        try {
            daonovo = new DAOCLIENTE();

        } catch (SQLException ex) {
            Logger.getLogger(PRINCIPAL.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PRINCIPAL.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        LISTADECLIENTES = daonovo.NOMEBAIXO();
        LISTADECLIENTES.forEach((p) -> {
            dtm_tabela.addRow(new Object[]{p.getCpf(), p.getNome(), p.getTelefone(), p.getEndereco(), p.getEnd_instagram(), p.getEnd_facebook(), p.getCartaoDeCredito()});
        });
    }//GEN-LAST:event_BAIXOActionPerformed

    private void LIMPARCHOCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPARCHOCOActionPerformed

        PUTIDCHOCO.setText("");
        PUTPESO.setText("");
        PUTVALORPORKG.setText("");
        PUTTIPODOCHOCOLATE.setText("");
        PUTVALORDEVENDA.setText("");
        PUTTEMPERATURAIDEAL.setText("");
        PUTQTDEDELEITE.setText("");
    }//GEN-LAST:event_LIMPARCHOCOActionPerformed

    private void INSERIRCHOCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INSERIRCHOCOActionPerformed

        System.out.println(""+ Double.parseDouble(PUTPESO.getText()));
        Chocolate novochoco = new Chocolate(PUTIDCHOCO.getText(), Double.parseDouble(PUTPESO.getText()), Double.parseDouble(PUTVALORPORKG.getText()),PUTTIPODOCHOCOLATE.getText(), Double.parseDouble(PUTVALORDEVENDA.getText()), Integer.parseInt(PUTTEMPERATURAIDEAL.getText()), Double.parseDouble(PUTQTDEDELEITE.getText()));
         System.out.println(""+ Double.parseDouble(PUTVALORPORKG.getText()));
        System.out.println(""+novochoco.toString());
        try {
            DAOCHOCO novodao = new DAOCHOCO();

            novodao.INSERIRCHOCO(novochoco);
            ATUALIZARCHOCOSTABELA();
        } catch (SQLException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        PUTIDCHOCO.setText("");
        PUTPESO.setText("");
        PUTVALORPORKG.setText("");
        PUTTIPODOCHOCOLATE.setText("");
        PUTVALORDEVENDA.setText("");
        PUTTEMPERATURAIDEAL.setText("");
        PUTQTDEDELEITE.setText("");

    }//GEN-LAST:event_INSERIRCHOCOActionPerformed

    private void LIMPARCHOCO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPARCHOCO1ActionPerformed

        PUTPESO1.setText("");
        PUTVALORPORKG1.setText("");
        PUTTIPODOCHOCOLATE1.setText("");
        PUTVALORDEVENDA1.setText("");
        PUTTEMPERATURAIDEAL1.setText("");
        PUTQTDEDELEITE.setText("");
    }//GEN-LAST:event_LIMPARCHOCO1ActionPerformed

    private void ATUALIZARCHOCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATUALIZARCHOCOActionPerformed
         if (PUTIDCHOCO1.getText().equals("") == true ||  BUSCARCHOCOLABEL.getText().equals("CHOCOLATE:NÃO ENCONTRADO") == true ||BUSCARCHOCOLABEL.getText().equals("CHOCOLATE:")  == true) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSIVEL ATUALIZAR O CHOCOLATE\nTENTE NOVAMENTE!");
        } else {
                         
            Chocolate novochoco = new Chocolate(PUTIDCHOCO.getText(), Double.parseDouble(PUTPESO.getText()), Double.parseDouble(PUTVALORPORKG.getText()),PUTTIPODOCHOCOLATE.getText(), Double.parseDouble(PUTVALORDEVENDA.getText()), Integer.parseInt(PUTTEMPERATURAIDEAL.getText()), Double.parseDouble(PUTQTDEDELEITE.getText()));

            try {
                DAOCHOCO dao = new DAOCHOCO();

                dao.UPTADE(novochoco);
                JOptionPane.showMessageDialog(null, "CHOCOLATE ATUALIZADO!");
                ATUALIZARCHOCOSTABELA();

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        
  
        PUTPESO1.setText("");
        PUTVALORPORKG1.setText("");
        PUTTIPODOCHOCOLATE1.setText("");
        PUTVALORDEVENDA1.setText("");
        PUTTEMPERATURAIDEAL1.setText("");
        PUTQTDEDELEITE.setText("");
 
    }//GEN-LAST:event_ATUALIZARCHOCOActionPerformed

    private void BUSCARCHOCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCARCHOCOActionPerformed
        if (PUTIDCHOCO1.getText().equals("") == true) {
            JOptionPane.showMessageDialog(null, "ERRO,ID CHOCO NÃO DIGITADO!!");

        } else {
            Chocolate encontrar = new Chocolate();
            DAOCHOCO dao = null;
            try {
                dao = new DAOCHOCO();
            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            encontrar = dao.RETORNAR(PUTIDCHOCO1.getText());

            if (encontrar == null) {
                BUSCARCHOCOLABEL.setText("CHOCOLATE:NÃO ENCONTRADO");
            } else {
                BUSCARCHOCOLABEL.setText("CHOCOLATE:ENCONTRADO  " + PUTIDCHOCO1.getText());

                PUTPESO1.setText(""+encontrar.getPeso());
                PUTVALORPORKG1.setText(""+encontrar.getValorPorKg());
                PUTTIPODOCHOCOLATE1.setText(""+encontrar.getTipoDoChocolate());
                PUTVALORDEVENDA1.setText(""+encontrar.getValorDeVenda());
                PUTTEMPERATURAIDEAL1.setText(""+encontrar.getTemperaturaIdeal());
                PUTQTDEDELEITE1.setText(""+encontrar.getQtdeDeLeite());

            }

        }
        // TODO add your handling code here:       


    }//GEN-LAST:event_BUSCARCHOCOActionPerformed

    private void REMOVERCHOCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMOVERCHOCOActionPerformed

            try {
                DAOCHOCO dao = new DAOCHOCO();

                dao.REMOVER(PUTIDCHOCO2.getText());
                JOptionPane.showMessageDialog(null, "CHOCOLATE REMOVIDO!");
                ATUALIZARCHOCOSTABELA();

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }     
    }//GEN-LAST:event_REMOVERCHOCOActionPerformed

    private void MOSTRARINFORMACOESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOSTRARINFORMACOESActionPerformed

           ArrayList<Chocolate> LISTADECHOCOS1 = new ArrayList();
        TABELACARA.setModel(dtm_tabelacara);
        dtm_tabelacara.setNumRows(0);
        DAOCHOCO daonovo = null;
        try {
            daonovo = new DAOCHOCO();
        } catch (SQLException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        LISTADECHOCOS1 = daonovo.CHOCOSCAROS();
     
        LISTADECHOCOS1.forEach((p) -> {
            dtm_tabelacara.addRow(new Object[]{p.getId_chocolate(), p.getPeso(), p.getValorPorKg(), p.getTipoDoChocolate(), p.getValorDeVenda(), p.getTemperaturaIdeal(), p.getQtdeDeLeite()});
        });     
       
        ArrayList<Chocolate> LISTADECHOCOS2 = new ArrayList();
        
        TABELACHOCOSVALORPORKG.setModel(dtm_tabelavalorporkg);
        dtm_tabelavalorporkg.setNumRows(0);
       
        LISTADECHOCOS2 = daonovo.CHOCOSVALORPORKG();
     
        LISTADECHOCOS2.forEach((p) -> {
            dtm_tabelavalorporkg.addRow(new Object[]{p.getId_chocolate(), p.getPeso(), p.getValorPorKg(), p.getTipoDoChocolate(), p.getValorDeVenda(), p.getTemperaturaIdeal(), p.getQtdeDeLeite()});
        });     
    }//GEN-LAST:event_MOSTRARINFORMACOESActionPerformed

    private void PUTPRAZOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PUTPRAZOKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTPRAZOKeyReleased

    private void PUTPRAZOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PUTPRAZOKeyTyped
        String chars = "0987654321";

        if (!chars.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
    }//GEN-LAST:event_PUTPRAZOKeyTyped

    private void PUTCPF3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PUTCPF3KeyTyped
        String chars = "0987654321";

        if (!chars.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
    }//GEN-LAST:event_PUTCPF3KeyTyped

    private void LIMPARPEDIDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPARPEDIDOActionPerformed

        PUTCPF3.setText("");
        PUTPRAZO.setText("");

    }//GEN-LAST:event_LIMPARPEDIDOActionPerformed

    private void INSERIRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INSERIRPActionPerformed

        boolean val=false;

        if(PUTCPF3.getText().equals("")==true)
        {
            val=true;
        }

        if(PUTPRAZO.getText().equals("")==true)
        {
            val=true;
        }

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime PRAZO = LocalDateTime.parse(PUTPRAZO.getText(), FORMATTER);

        System.out.println("Parsed Date : " +PRAZO);

        if(val==true)
        {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL ADICIONAR O PEDIDO\nTENTE NOVAMENTE!");
          
        }
        else
        {
            //CRIAR UM NOVO CLIENTE
            Pedidos novoP=new Pedidos(0,LocalDateTime.now(),PRAZO,PUTCPF3.getText());

            try {
                DAOPEDIDO dao = new DAOPEDIDO();
                System.out.println(""+novoP.toString());
                dao.INSERIRPEDIDOBANCO(novoP);
                IDPEDIDO.setText("ID PEDIDO: "+(dao.MAIORIDPEDIDO()+1));
                JOptionPane.showMessageDialog(null,"PEDIDO ADICIONADO!");
                TDPEDIDOS();
            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        PUTCPF3.setText("");
        PUTPRAZO.setText("");

    }//GEN-LAST:event_INSERIRPActionPerformed

    private void LIMPARPEDIDO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPARPEDIDO1ActionPerformed

        PUTCPF4.setText("");
        PUTPRAZO1.setText("");

    }//GEN-LAST:event_LIMPARPEDIDO1ActionPerformed

    private void UPTADEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPTADEPActionPerformed

        if(PUTID_PEDIDO.getText().equals("")==true||IDPEDIDOENCONTRADO.getText().equals("PEDIDO:NÃO ENCONTRADO")==true||IDPEDIDOENCONTRADO.getText().equals("PEDIDO:")==true)
        {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL ATUALIZAR O PEDIDO\nTENTE NOVAMENTE!");
        }
        else
        {

            DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalDateTime PRAZO = LocalDateTime.parse(PUTPRAZO1.getText(), FORMATTER);

            System.out.println("Parsed Date : " +PRAZO);

            //CRIAR UM NOVO CLIENTE
            Pedidos novoP=new Pedidos(Integer.parseInt(PUTID_PEDIDO.getText()),LocalDateTime.now(),PRAZO,PUTCPF4.getText());

            try {
                DAOPEDIDO dao = new DAOPEDIDO();

                dao.UPTADE(novoP);
                JOptionPane.showMessageDialog(null,"PEDIDO ATUALIZADO!");
                TDPEDIDOS();

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        PUTCPF4.setText("");
        PUTPRAZO1.setText("");

    }//GEN-LAST:event_UPTADEPActionPerformed

    private void BUSCARPEDIDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCARPEDIDOActionPerformed
        if(PUTID_PEDIDO.getText().equals("")==true)
        {
            JOptionPane.showMessageDialog(null, "ERRO,ID NÃO DIGITADO!!");

        }
        else
        {
            Pedidos encontrar=new Pedidos();
            DAOPEDIDO dao = null;
            try {
                dao = new DAOPEDIDO();
            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            encontrar=dao.RETORNARPEDIDO(Integer.parseInt(PUTID_PEDIDO.getText()));

            if(encontrar==null)
            {
                IDPEDIDOENCONTRADO.setText("ID_PEDIDO:NÃO ENCONTRADO");
            }
            else
            {
                IDPEDIDOENCONTRADO.setText("ID_PEDIDO:ENCONTRADO:"+PUTID_PEDIDO.getText());
                PUTCPF4.setText(""+encontrar.getFk_cpf());
                PUTPRAZO1.setText(""+encontrar.getPrazoParaEntrega());

                //String substituir=encontrar.getPrazoParaEntrega();

                String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
                String formatDateTime = encontrar.getPrazoParaEntrega().format(formatter);
                PUTPRAZO1.setText(""+formatDateTime);
                System.out.println(""+encontrar.toString());

            }

        }

    }//GEN-LAST:event_BUSCARPEDIDOActionPerformed

    private void LIMPARPEDIDO2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPARPEDIDO2ActionPerformed

        PUTID_PEDIDO1.setText("");

    }//GEN-LAST:event_LIMPARPEDIDO2ActionPerformed

    private void REMOVERPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMOVERPActionPerformed

        if(PUTID_PEDIDO1.getText().equals("")==true)
        {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL REMOVER O PEDIDO\nTENTE NOVAMENTE!");
        }
        else
        {
            //CRIAR UM NOVO CLIENTE

            try {
                DAOPEDIDO dao = new DAOPEDIDO();

                dao.REMOVERPEDIDO(Integer.parseInt(PUTID_PEDIDO1.getText()));
                JOptionPane.showMessageDialog(null,"PEDIDO REMOVIDO!");
                TDPEDIDOS();

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_REMOVERPActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      TABELAPEDIDOSANTIGOS.setModel(dtm_tabelapedidosantigos);
        dtm_tabelapedidosantigos.setNumRows(0);
        DAOPEDIDO daonovo = null;
        try {
            daonovo = new DAOPEDIDO();
        } catch (SQLException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LISTADEPEDIDOS=daonovo.TODOSOSPEDIDOSANTIGOSDESTECLIENTE(PUTCPF5.getText());
         LISTADEPEDIDOS.forEach((p) -> {
            dtm_tabelapedidosantigos.addRow(new Object[]{p.getId_pedido(),p.getData(),p.getPrazoParaEntrega(),p.getFk_cpf()});
        });
    }//GEN-LAST:event_jButton2ActionPerformed

    private void PUTCPF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUTCPF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTCPF3ActionPerformed

    private void PUTCPF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUTCPF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTCPF5ActionPerformed

    private void PUTCPF5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PUTCPF5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTCPF5KeyTyped

    private void INSERIRBJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INSERIRBJActionPerformed

        boolean val=false;

        if(PUTQTD.getText().equals("")==true)
        {
            val=true;
        }

        if(PUTIDPEDIDO.getText().equals("")==true)
        {
            val=true;
        }

        if(PUTFKCHOCO.getText().equals("")==true)
        {
            val=true;
        }

        if(val==true)
        {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL ADICIONAR O PEDIDO DO CHOCOLATE\nTENTE NOVAMENTE!");
        }
        else
        {
            //CRIAR UM NOVO CLIENTE
            Chocolate_Pedido novoBD=new Chocolate_Pedido(0,Integer.parseInt(PUTQTD.getText()),Integer.parseInt(PUTIDPEDIDO.getText()),PUTFKCHOCO.getText());

            try {
                DAOCHOCOLATE_PEDIDO dao = new DAOCHOCOLATE_PEDIDO();

                dao.INSERIRCHOCOPEDIDO(novoBD);
                IDCHOCOPEDIDO.setText("ID CHOCO PEDIDO:"+(dao.MAIORIDCHOCOPEDIDO()+1));
                TDCHOCOPEDIDOS();
                JOptionPane.showMessageDialog(null,"CHOCO PEDIDO ADICIONADO!");

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        PUTQTD.setText("");
        PUTIDPEDIDO.setText("");
        PUTFKCHOCO.setText("");
     

    }//GEN-LAST:event_INSERIRBJActionPerformed

    private void LIMPAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPAR1ActionPerformed
        PUTQTD.setText("");
        PUTIDPEDIDO.setText("");
        PUTFKCHOCO.setText("");

    }//GEN-LAST:event_LIMPAR1ActionPerformed

    private void REMOVERQJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMOVERQJActionPerformed

        if(PUTID_CHOCO_PEDIDO.getText().equals("")==true)
        {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL REMOVER O CHOCO_PEDIDO\nTENTE NOVAMENTE!");
        }
        else
        {
            //CRIAR UM NOVO CLIENTE

            try {
                DAOCHOCOLATE_PEDIDO dao = new DAOCHOCOLATE_PEDIDO();

                dao.REMOVERCHOCO_PEDIDO(Integer.parseInt(PUTID_CHOCO_PEDIDO.getText()));
                JOptionPane.showMessageDialog(null,"CHOCO_PEDIDO REMOVIDO!");
                TDCHOCOPEDIDOS();

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_REMOVERQJActionPerformed

    private void LIMPAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPAR2ActionPerformed

        PUTID_CHOCO_PEDIDO.setText("");
    }//GEN-LAST:event_LIMPAR2ActionPerformed

    private void PUTID_CHOCO_PEDIDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUTID_CHOCO_PEDIDOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTID_CHOCO_PEDIDOActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      PUTQTDU.setText("");
      PUTIDPEDIDOU.setText("");
      PUTIDCHOCOU.setText("");
      BUSCARUIDCHOCO.setText("");
      
      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BUSCARUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCARUActionPerformed
        if(BUSCARUIDCHOCO.getText().equals("")==true)
        {
            JOptionPane.showMessageDialog(null, "ERRO,ID NÃO DIGITADO!!");

        }
        else
        {
            Chocolate_Pedido encontrar=new Chocolate_Pedido();
            DAOCHOCOLATE_PEDIDO dao = null;
            try {
                dao = new DAOCHOCOLATE_PEDIDO();
            } catch (SQLException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            encontrar=dao.RETORNAR(Integer.parseInt(BUSCARUIDCHOCO.getText()));

            if(encontrar==null)
            {
                     JOptionPane.showMessageDialog(null, "ERRO,ID NÃO ENCONTRADO!");
            }
            else
            {
                PUTQTDU.setText(""+encontrar.getQuantidade());
                PUTIDPEDIDOU.setText(""+encontrar.getFk_id_pedido());
                PUTIDCHOCOU.setText(""+encontrar.getFk_id_chocolate());
            }

        }

        
    }//GEN-LAST:event_BUSCARUActionPerformed

    private void ATUALIZARCHOCOPEDIDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATUALIZARCHOCOPEDIDOActionPerformed
    if(BUSCARUIDCHOCO.getText().equals("")==true ) 
    {
        JOptionPane.showMessageDialog(null, "ERRO,NÃO FOI POSSIVEL ATUALIZAR!!");

    }
    else
    {
          Chocolate_Pedido novoP=new  Chocolate_Pedido (Integer.parseInt(BUSCARUIDCHOCO.getText()),Integer.parseInt( PUTQTDU.getText()),Integer.parseInt( PUTIDPEDIDOU.getText()),PUTIDCHOCOU.getText());

            try {
                DAOCHOCOLATE_PEDIDO dao = new DAOCHOCOLATE_PEDIDO();

                dao.UPTADE(novoP);
                JOptionPane.showMessageDialog(null,"CHOCO_PEDIDO ATUALIZADO!");
                TDCHOCOPEDIDOS();

            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
            }

    
    
    }
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_ATUALIZARCHOCOPEDIDOActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
         setVisible(false);
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
    JOptionPane.showMessageDialog(null,"TRABALHO FINAL PEDRO MEDINA!");
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void PUTIDCHOCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUTIDCHOCOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUTIDCHOCOActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PRINCIPAL().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ATUALIZARCHOCO;
    private javax.swing.JButton ATUALIZARCHOCOPEDIDO;
    private javax.swing.JPanel ATUALIZARCLIENTE;
    private javax.swing.JButton AtualizarCliente;
    private javax.swing.JButton BAIXO;
    private javax.swing.JButton BUSCAR;
    private javax.swing.JButton BUSCAR1;
    private javax.swing.JButton BUSCAR2;
    private javax.swing.JButton BUSCARCHOCO;
    private javax.swing.JLabel BUSCARCHOCOLABEL;
    private javax.swing.JPanel BUSCAREREMOVER;
    private javax.swing.JButton BUSCARPEDIDO;
    private javax.swing.JButton BUSCARU;
    private javax.swing.JTextField BUSCARUIDCHOCO;
    private javax.swing.JPanel CHOCOS;
    private javax.swing.JPanel CHOCOSPEDIDOS;
    private javax.swing.JButton CIMA;
    private javax.swing.JPanel CLIENTE;
    private javax.swing.JLabel CLIENTEENCONTRADO;
    private javax.swing.JLabel CLIENTEENCONTRADO1;
    private javax.swing.JLabel CPFTEXTO;
    private javax.swing.JLabel CPFTEXTO1;
    private javax.swing.JLabel CPFTEXTO2;
    private javax.swing.JComboBox<String> ComboMudanca;
    private javax.swing.JLabel IDCHOCOPEDIDO;
    private javax.swing.JLabel IDPEDIDO;
    private javax.swing.JLabel IDPEDIDOENCONTRADO;
    private javax.swing.JButton INSERIRBJ;
    private javax.swing.JButton INSERIRCHOCO;
    private javax.swing.JPanel INSERIRCLIENTE;
    private javax.swing.JButton INSERIRP;
    private javax.swing.JButton INSERTCLIENTE;
    private javax.swing.JButton LIMPAR;
    private javax.swing.JButton LIMPAR1;
    private javax.swing.JButton LIMPAR2;
    private javax.swing.JButton LIMPARCHOCO;
    private javax.swing.JButton LIMPARCHOCO1;
    private javax.swing.JButton LIMPARPEDIDO;
    private javax.swing.JButton LIMPARPEDIDO1;
    private javax.swing.JButton LIMPARPEDIDO2;
    private javax.swing.JPanel MAIN;
    private javax.swing.JButton MOSTRARINFORMACOES;
    private javax.swing.JPanel Mostragem;
    private javax.swing.JPanel PEDIDOS;
    private javax.swing.JTextField PUTCARTAO;
    private javax.swing.JTextField PUTCARTAO1;
    private javax.swing.JFormattedTextField PUTCPF;
    private javax.swing.JFormattedTextField PUTCPF1;
    private javax.swing.JFormattedTextField PUTCPF2;
    private javax.swing.JFormattedTextField PUTCPF3;
    private javax.swing.JFormattedTextField PUTCPF4;
    private javax.swing.JFormattedTextField PUTCPF5;
    private javax.swing.JTextArea PUTENDERECO;
    private javax.swing.JTextArea PUTENDERECO1;
    private javax.swing.JTextField PUTFACEBOOK;
    private javax.swing.JTextField PUTFACEBOOK1;
    private javax.swing.JTextField PUTFKCHOCO;
    private javax.swing.JTextField PUTIDCHOCO;
    private javax.swing.JTextField PUTIDCHOCO1;
    private javax.swing.JTextField PUTIDCHOCO2;
    private javax.swing.JTextField PUTIDCHOCOU;
    private javax.swing.JTextField PUTIDPEDIDO;
    private javax.swing.JTextField PUTIDPEDIDOU;
    private javax.swing.JTextField PUTID_CHOCO_PEDIDO;
    private javax.swing.JTextField PUTID_PEDIDO;
    private javax.swing.JTextField PUTID_PEDIDO1;
    private javax.swing.JTextField PUTINSTAGRAM;
    private javax.swing.JTextField PUTINSTAGRAM1;
    private javax.swing.JFormattedTextField PUTNOME;
    private javax.swing.JFormattedTextField PUTNOME1;
    private javax.swing.JTextField PUTNOMEBUSCA;
    private javax.swing.JTextField PUTPESO;
    private javax.swing.JTextField PUTPESO1;
    private javax.swing.JFormattedTextField PUTPRAZO;
    private javax.swing.JFormattedTextField PUTPRAZO1;
    private javax.swing.JTextField PUTQTD;
    private javax.swing.JTextField PUTQTDEDELEITE;
    private javax.swing.JTextField PUTQTDEDELEITE1;
    private javax.swing.JTextField PUTQTDU;
    private javax.swing.JTable PUTTABELABK;
    private javax.swing.JFormattedTextField PUTTELEFONE;
    private javax.swing.JFormattedTextField PUTTELEFONE1;
    private javax.swing.JTextField PUTTEMPERATURAIDEAL;
    private javax.swing.JTextField PUTTEMPERATURAIDEAL1;
    private javax.swing.JTextField PUTTIPODOCHOCOLATE;
    private javax.swing.JTextField PUTTIPODOCHOCOLATE1;
    private javax.swing.JTextField PUTVALORDEVENDA;
    private javax.swing.JTextField PUTVALORDEVENDA1;
    private javax.swing.JTextField PUTVALORPORKG;
    private javax.swing.JTextField PUTVALORPORKG1;
    private javax.swing.JTable PutTabelaChoco;
    private javax.swing.JTable PutTabelaCliente;
    private javax.swing.JTable PutTabelaPedidos;
    private javax.swing.JLabel QTDCHOCOS;
    private javax.swing.JLabel QTDCLIENTES;
    private javax.swing.JButton REMOVERCHOCO;
    private javax.swing.JButton REMOVERP;
    private javax.swing.JButton REMOVERQJ;
    private javax.swing.JButton Remover;
    private javax.swing.JRadioButtonMenuItem Salvartxt;
    private javax.swing.JTable TABELACARA;
    private javax.swing.JScrollPane TABELACHOCO;
    private javax.swing.JTable TABELACHOCOSVALORPORKG;
    private javax.swing.JScrollPane TABELACLIENTES;
    private javax.swing.JTable TABELACLIENTESNOME;
    private javax.swing.JScrollPane TABELAPEDIDOS;
    private javax.swing.JTable TABELAPEDIDOSANTIGOS;
    private javax.swing.JLayeredPane TUDO;
    private javax.swing.JButton UPTADEP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    // End of variables declaration//GEN-END:variables
}
