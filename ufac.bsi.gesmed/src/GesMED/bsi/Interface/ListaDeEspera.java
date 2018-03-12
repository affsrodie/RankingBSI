/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GesMED.bsi.Interface;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Leoncio Carioca
 */
public class ListaDeEspera extends javax.swing.JFrame {
    
    private PainelPrincipal Pai;
    private String StatusMenu;
    /**
     * Creates new form AgendarConsulta
     */
    public ListaDeEspera() {
        initComponents();
        StatusMenu="AGENDAMENTO";
        
    }
    public ListaDeEspera(PainelPrincipal Principal) {
        Pai = Principal;
        initComponents();
        StatusMenu="AGENDAMENTO";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgFinanceiro = new javax.swing.ButtonGroup();
        bg = new javax.swing.JPanel();
        pCabecalho = new javax.swing.JPanel();
        imgTituloJanela = new javax.swing.JLabel();
        lbIMGListaEspera = new javax.swing.JLabel();
        btn_Close = new javax.swing.JLabel();
        btn_Maximizar = new javax.swing.JLabel();
        btn_Minimizar = new javax.swing.JLabel();
        pCentral = new javax.swing.JPanel();
        tfdNome = new javax.swing.JTextField();
        lbConvenio = new javax.swing.JLabel();
        jcbConvenio = new javax.swing.JComboBox<>();
        jcbStatus = new javax.swing.JComboBox<>();
        lbStatus = new javax.swing.JLabel();
        lbProcedimento = new javax.swing.JLabel();
        jcbProcedimento = new javax.swing.JComboBox<>();
        lbNome = new javax.swing.JLabel();
        jspListaEspera = new javax.swing.JScrollPane();
        tblListaEspera = new javax.swing.JTable();
        btn_Adicionar = new javax.swing.JButton();
        btn_Salvar = new javax.swing.JButton();
        btn_Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(0, 204, 204));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pCabecalho.setBackground(new java.awt.Color(0, 164, 137));

        imgTituloJanela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufac/bsi/images/Agenda_60px.png"))); // NOI18N
        imgTituloJanela.setText("jLabel2");

        lbIMGListaEspera.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lbIMGListaEspera.setForeground(new java.awt.Color(255, 255, 255));
        lbIMGListaEspera.setText("Lista de Espera");

        btn_Close.setBackground(new java.awt.Color(0, 164, 137));
        btn_Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufac/bsi/images/Close Window_36px.png"))); // NOI18N
        btn_Close.setOpaque(true);
        btn_Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_CloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_CloseMouseExited(evt);
            }
        });

        btn_Maximizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufac/bsi/images/Restore Window_36px.png"))); // NOI18N

        btn_Minimizar.setBackground(new java.awt.Color(0, 164, 137));
        btn_Minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufac/bsi/images/Minimize Window_36px.png"))); // NOI18N
        btn_Minimizar.setOpaque(true);
        btn_Minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_MinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_MinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_MinimizarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pCabecalhoLayout = new javax.swing.GroupLayout(pCabecalho);
        pCabecalho.setLayout(pCabecalhoLayout);
        pCabecalhoLayout.setHorizontalGroup(
            pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCabecalhoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(imgTituloJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbIMGListaEspera)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
                .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Minimizar)
                    .addGroup(pCabecalhoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btn_Maximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addComponent(btn_Close))
        );
        pCabecalhoLayout.setVerticalGroup(
            pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCabecalhoLayout.createSequentialGroup()
                .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCabecalhoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imgTituloJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbIMGListaEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_Minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Maximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        bg.add(pCabecalho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 110));

        pCentral.setBackground(new java.awt.Color(255, 255, 255));

        lbConvenio.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbConvenio.setText("Convênio:");

        jcbConvenio.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jcbConvenio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Particular" }));

        jcbStatus.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jcbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Agendado", "Confirmado", "Chegou", "Em Andamento", "Finalizando", "Cancelado", "Faltou", " ", " ", " " }));

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbStatus.setText("Status:");

        lbProcedimento.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbProcedimento.setText("Procedimento:");

        jcbProcedimento.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jcbProcedimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Consulta", "Retorno", " " }));

        lbNome.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbNome.setText("Nome:");

        tblListaEspera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ord", "Nome", "Procedimento", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspListaEspera.setViewportView(tblListaEspera);
        if (tblListaEspera.getColumnModel().getColumnCount() > 0) {
            tblListaEspera.getColumnModel().getColumn(0).setMinWidth(30);
            tblListaEspera.getColumnModel().getColumn(0).setMaxWidth(35);
            tblListaEspera.getColumnModel().getColumn(1).setMinWidth(300);
            tblListaEspera.getColumnModel().getColumn(1).setMaxWidth(400);
        }

        btn_Adicionar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Adicionar.setText("ADICIONAR");

        javax.swing.GroupLayout pCentralLayout = new javax.swing.GroupLayout(pCentral);
        pCentral.setLayout(pCentralLayout);
        pCentralLayout.setHorizontalGroup(
            pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCentralLayout.createSequentialGroup()
                .addGroup(pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCentralLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jspListaEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pCentralLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tfdNome, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(pCentralLayout.createSequentialGroup()
                .addGroup(pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCentralLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbConvenio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCentralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbProcedimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbProcedimento, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCentralLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCentralLayout.createSequentialGroup()
                                .addComponent(lbStatus)
                                .addGap(210, 210, 210))
                            .addComponent(jcbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCentralLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        pCentralLayout.setVerticalGroup(
            pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCentralLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdNome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbProcedimento)
                    .addComponent(jcbProcedimento)
                    .addComponent(lbStatus)
                    .addComponent(jcbStatus))
                .addGap(18, 18, 18)
                .addGroup(pCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbConvenio)
                    .addComponent(jcbConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jspListaEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );

        bg.add(pCentral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, 400));

        btn_Salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Salvar.setText("SALVAR");
        bg.add(btn_Salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 520, 80, 30));

        btn_Sair.setBackground(new java.awt.Color(255, 255, 255));
        btn_Sair.setText("SAIR");
        btn_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SairActionPerformed(evt);
            }
        });
        bg.add(btn_Sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 70, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CloseMouseClicked
        Pai.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_CloseMouseClicked

    private void btn_CloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CloseMouseEntered
        btn_Close.setBackground(new Color(215, 121, 121));
    }//GEN-LAST:event_btn_CloseMouseEntered

    private void btn_CloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CloseMouseExited
        btn_Close.setBackground(new Color(0,164,137));
    }//GEN-LAST:event_btn_CloseMouseExited

    private void btn_MinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_MinimizarMouseClicked
        setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btn_MinimizarMouseClicked

    private void btn_MinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_MinimizarMouseEntered
        btn_Minimizar.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_btn_MinimizarMouseEntered

    private void btn_MinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_MinimizarMouseExited
        btn_Minimizar.setBackground(new Color(0,164,137));
    }//GEN-LAST:event_btn_MinimizarMouseExited

    private void btn_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SairActionPerformed
        Pai.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_SairActionPerformed

    
    public void setColorClicked(JLabel lbButton){
        lbButton.setBackground(new Color(0,204,204));
    }
    
    public void setColorEntered(JLabel lbButton){
        lbButton.setBackground(new Color(200,240,240));
    }
    
    public void setColorExited(JLabel lbButton){
        lbButton.setBackground(new Color(0,204,153));
    }
    
    public void setColorPressed(JLabel lbButton){
        lbButton.setBackground(new Color(140,198,198));
    }
    
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
            java.util.logging.Logger.getLogger(ListaDeEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaDeEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaDeEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaDeEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaDeEspera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.ButtonGroup bgFinanceiro;
    private javax.swing.JButton btn_Adicionar;
    private javax.swing.JLabel btn_Close;
    private javax.swing.JLabel btn_Maximizar;
    private javax.swing.JLabel btn_Minimizar;
    private javax.swing.JButton btn_Sair;
    private javax.swing.JButton btn_Salvar;
    private javax.swing.JLabel imgTituloJanela;
    private javax.swing.JComboBox<String> jcbConvenio;
    private javax.swing.JComboBox<String> jcbProcedimento;
    private javax.swing.JComboBox<String> jcbStatus;
    private javax.swing.JScrollPane jspListaEspera;
    private javax.swing.JLabel lbConvenio;
    private javax.swing.JLabel lbIMGListaEspera;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbProcedimento;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JPanel pCabecalho;
    private javax.swing.JPanel pCentral;
    private javax.swing.JTable tblListaEspera;
    private javax.swing.JTextField tfdNome;
    // End of variables declaration//GEN-END:variables
}
