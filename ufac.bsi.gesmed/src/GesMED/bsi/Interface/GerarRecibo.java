/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GesMED.bsi.Interface;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GerarRecibo extends javax.swing.JDialog {
     
        private static Paragraph paragrafo;
        private GesMED.bsi.Entidades.ContaPaciente contaPaciente;
    /**
     * Creates new form GerarRecibo
     */
    public GerarRecibo() {
        initComponents();
    }
    
    public GerarRecibo(GesMED.bsi.Entidades.ContaPaciente contaPaciente) {
        this.contaPaciente = contaPaciente;
        initComponents();
        setTextAreaInformacoes();
    }
    
    public void setTextAreaInformacoes(){
        
        String TextInicial = " \n" +
"      	       GESMED - Clinica Universitaria\n" +
"	 Recibo para "+this.contaPaciente.getPaciente().getNome()+ "\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"     O Paciente "+contaPaciente.getPaciente().getNome() +" pagou a quantia \n" +
"     no valor de R$ "+ this.contaPaciente.getValor()+ " Reais no dia "+contaPaciente.getDataPago()+".\n" +
"\n" +
"\n" +
"\n" +
"\n" + "                       ---------------------------------"+
"\n" +  "                            Assinatura"+"\n\n"+
"  	  Clínica Universitaria - Avenida Ceara nº 345 \n" +
"		  Rio Branco - Acre";
        
        jtextInformacoes.setText(TextInicial);
    }

    public void GerarReciboPDF(){
          
            Document document = new Document();
            LocalDate dataHoje = LocalDate.now();
            String DataHoje = dataHoje.toString();
          
            String[] vetDataHoje = DataHoje.split("-");
            DataHoje = vetDataHoje[2]+"/"+vetDataHoje[1]+"/"+vetDataHoje[0];
            
            String DataPago = this.contaPaciente.getDataPago().toString();
            
            String[] vetDataPago = DataPago.split("-");
            DataPago = vetDataPago[2]+"/"+vetDataPago[1]+"/"+vetDataPago[0];
            
            String DataPagoCaminho = vetDataPago[2]+"-"+vetDataPago[1]+"-"+vetDataPago[0];
          try {
              
              PdfWriter.getInstance(document, new FileOutputStream("/home/leoncio/Documents/GesMed/Recibos/Recibo - "+this.contaPaciente.getPaciente().getNome()+" "+DataPagoCaminho+".pdf"));
              document.open();
              Font bold = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);
              Font bold2 = new Font(FontFamily.TIMES_ROMAN, 22, Font.BOLD);
              Font fontConteudo = new Font(FontFamily.TIMES_ROMAN, 15);
              // adicionando um parágrafo no documento
              paragrafo = new Paragraph("GESMED - Clinica Academicos da UFAC", bold2);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("Recibo de Pagamento para: "+ this.contaPaciente.getPaciente().getNome(), bold);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              
              document.add(paragrafo);
              paragrafo = new Paragraph("\n\n\nO Paciente "+this.contaPaciente.getPaciente().getNome()+", CPF nº "+this.contaPaciente.getPaciente().getCPF()+", pagou a quantia de R$ "+ this.contaPaciente.getValor() + " Reais no dia "+DataPago+" Referente a uma Consulta Realizada.", fontConteudo);
              document.add(paragrafo);
              String RodapeFinal = "\n" + "__________________________________________________________"+
                                         "\n" +  " Assinatura da Secretária"+"\n\n\n\n\n"+
                                        "Clínica Universitaria - Avenida Ceara nº 345 \n" +
                                            "Rio Branco - Acre";
                      
              paragrafo = new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+RodapeFinal);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
  
          }
          catch(DocumentException de) {
              System.err.println(de.getMessage());
          }
          catch(IOException ioe) {
              System.err.println(ioe.getMessage());
          }finally{
               document.close();
               
          }
          
            try {
                Desktop.getDesktop().open(new File("/home/leoncio/Documents/GesMed/Recibos/Recibo - "+this.contaPaciente.getPaciente().getNome()+" "+DataPagoCaminho+".pdf"));
            } catch (IOException ex) {
                Logger.getLogger(GerarRecibo.class.getName()).log(Level.SEVERE, null, ex);
            }
          
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtextInformacoes = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_GerarRecibo = new javax.swing.JButton();

        setTitle("Gerar Recibo ");
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);

        jPanel1.setBackground(new java.awt.Color(215, 215, 246));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtextInformacoes.setColumns(20);
        jtextInformacoes.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jtextInformacoes.setRows(5);
        jtextInformacoes.setText("\n      \t       GESMED - Clinica Universitaria\n\tRecibo para NEURIS GRANGEIRO CARIOCA\n\n\n\n\n     O Paciente NEURIS GRANGEIRO CARIOCA pagou a quantia \n     de 200 Reais no dia 24/03/2018.\n\n\n\n\n\n\tClínica Universitaria - Avenida Ceara nº 345 \n\t\tRio Branco - Acre");
        jScrollPane1.setViewportView(jtextInformacoes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 500, 390));

        jPanel2.setBackground(new java.awt.Color(46, 197, 236));

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Gerar Recibo de Pagamento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jLabel1)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 130));

        btn_GerarRecibo.setText("GERAR RECIBO");
        btn_GerarRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GerarReciboActionPerformed(evt);
            }
        });
        jPanel1.add(btn_GerarRecibo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_GerarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GerarReciboActionPerformed
        GerarReciboPDF();
        this.dispose();
    }//GEN-LAST:event_btn_GerarReciboActionPerformed

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
            java.util.logging.Logger.getLogger(GerarRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerarRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerarRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerarRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerarRecibo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_GerarRecibo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtextInformacoes;
    // End of variables declaration//GEN-END:variables
}
