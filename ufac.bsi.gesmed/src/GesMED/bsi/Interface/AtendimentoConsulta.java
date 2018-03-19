/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GesMED.bsi.Interface;

import GesMED.bsi.Entidades.Agendamento;
import GesMED.bsi.Entidades.Consulta;
import GesMED.bsi.Entidades.Exame;
import GesMED.bsi.Repositorios.AgendamentoRepositorio;
import GesMED.bsi.Repositorios.ConsultaRepositorio;
import GesMED.bsi.Repositorios.ExamesRepositorio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Leoncio Carioca
 */
public class AtendimentoConsulta extends javax.swing.JFrame {
    
    private ListaDeEspera Pai;
    private String StatusMenu;
    private Agendamento agenda;
    private boolean[] StatusOperacoes = new boolean[2]; //PRIMEIRA POSIÇÃO TRUE IDENTIFICA QUE OPERAÇÃO SALVAR FOI CONCLUIDA, SEGUNDA POSIÇÃO TRUE IDENTIFICA QUE OPERAÇÃO SOLICITAR EXAMES TAMBÉM FOI REALIZADA.
    private static Paragraph paragrafo;
    
    /**
     * Creates new form AgendarConsulta
     */
    public AtendimentoConsulta() {
        initComponents();
        StatusMenu="Anamnese";
        setColorClicked(btn_Consulta);
         DisableButton();
    }
    
    
    public AtendimentoConsulta(ListaDeEspera listEspera, Agendamento agendamento) {
        Pai = listEspera;
        initComponents();
        StatusMenu="Anamnese";
        
        agenda = agendamento;
        StatusOperacoes[1]=true;
        setColorClicked(btn_Consulta);
        setLabelPaciente();
        DisableButton();
    }
    
    public void setLabelPaciente() {
    	if(agenda!=null)
    	lbPaciente.setText(agenda.getPaciente().getNome());
    	
  
    }
    
    public Date setData() {
    	
        LocalDate data = LocalDate.now();
        Date date = new Date();
        return date;
    }
    
    public Date setHora() {
        LocalDate data = LocalDate.now();
        Date date = new Date();
        return date;
    }
    
    public Date getData(String tfdData) {
    	
        LocalDate data = LocalDate.now();
        Date date = new Date();
        
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yy"); 
        try {
			date = formatoData.parse(tfdData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }
    
    public Date getHora(String Hora) {
    	
        LocalDate data = LocalDate.now();
        Date date = new Date();
        
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm"); 
        try {
			date = formatoHora.parse(Hora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }
    
    public void desativarCampos(){
        
    }
    
    
    public void solicitarExame(){
        Exame exame = new Exame();
        
        ExamesRepositorio exRep = new ExamesRepositorio();
        
        exame.setQueixaPrincipal(tfdTituloExame.getText());
        exame.setDataExame(getData(tfdDataExame.getText()));
        exame.setHoraExame(getHora(tfdHoraExame.getText()));
        exame.setObservacoes(textObsExame.getText());
        
        exRep.adicionar(exame);
        exRep.encerrar();
    }

    public void LimparCamposConsulta(){
        tfdTituloExame.setText("");
        tfdDataExame.setText("19/03/18");
        tfdHoraExame.setText("");
        textObsExame.setText("");
    }
    
    public void registrarConsulta(Consulta consulta){
        ConsultaRepositorio cRep = new ConsultaRepositorio();
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        
        agenda.setStatus("Finalizado");
        agenRep.atualizar(agenda);
        agenRep.encerrar();
        cRep.adicionar(consulta);
        cRep.encerrar();
    }
    
    public void SalvarConsulta(){
        
        Consulta consulta = new Consulta();
        boolean CamposValidos = true;
        
        
        if(!tfdTitulo.getText().isEmpty()){
            consulta.setNomeConsulta(tfdTitulo.getText());
        }else {
            JOptionPane.showMessageDialog(null, "Título da Consulta esta em branco!");
            CamposValidos = false;
        }
            
        
        if(!textObservacoes.getText().isEmpty()){
            consulta.setObservacoes(textObservacoes.getText());
        }else{
            JOptionPane.showMessageDialog(null, "Preencha as Observações da Consulta");
            CamposValidos = false;
        }
        
        consulta.setAgendamento(agenda);
        consulta.setData(setData());
        consulta.setHora(setHora());
            
        if(CamposValidos){
            registrarConsulta(consulta);
            
        }
        
        
    }
    
        public void GerarAtestadoPDF(){
          
            Document document = new Document();
            LocalDate dataHoje = LocalDate.now();
            String DataHoje = dataHoje.toString();
          
            String[] vetDataHoje = DataHoje.split("-");
            DataHoje = vetDataHoje[2]+"/"+vetDataHoje[1]+"/"+vetDataHoje[0];
            
           
          try {
              
              PdfWriter.getInstance(document, new FileOutputStream("/home/leoncio/Documents/GesMed/Atestados/Atestado - "+this.agenda.getPaciente().getNome()+".pdf"));
              document.open();
              Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
              Font bold2 = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
              Font fontConteudo = new Font(Font.FontFamily.TIMES_ROMAN, 15);
              // adicionando um parágrafo no documento
              paragrafo = new Paragraph("GESMED - Clinica Academicos da UFAC", bold2);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("ATESTADO MÉDICO", bold);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              
              document.add(paragrafo);
              paragrafo = new Paragraph("\n\n\nAtesto que o Sr.(a) "+this.agenda.getPaciente().getNome()+", CPF nº "+this.agenda.getPaciente().getCPF()+", esteve sobe os Cuidados Médicos no dia"+DataHoje+".", fontConteudo);
              document.add(paragrafo);
              String RodapeFinal = "\n" + "__________________________________________________________"+
                                         "\n" +  " Carimbo ou Assinatura do Médico"+"\n\n\n\n\n"+
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
                Desktop.getDesktop().open(new File("/home/leoncio/Documents/GesMed/Atestados/Atestado - "+this.agenda.getPaciente().getNome()+".pdf"));
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

        bgFinanceiro = new javax.swing.ButtonGroup();
        bg = new javax.swing.JPanel();
        pCabecalho = new javax.swing.JPanel();
        btn_Consulta = new javax.swing.JLabel();
        btn_Financeiro = new javax.swing.JLabel();
        imgTituloJanela = new javax.swing.JLabel();
        lbIMGAtendimento = new javax.swing.JLabel();
        btn_Close = new javax.swing.JLabel();
        btn_Maximizar = new javax.swing.JLabel();
        btn_Minimizar = new javax.swing.JLabel();
        pCentral = new javax.swing.JPanel();
        pConsulta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdTitulo = new javax.swing.JTextField();
        lbObsersavacoes = new javax.swing.JLabel();
        jScrollPaneObservacoes = new javax.swing.JScrollPane();
        textObservacoes = new javax.swing.JTextArea();
        lbTitlePaciente = new javax.swing.JLabel();
        lbTitulo = new javax.swing.JLabel();
        lbPaciente = new javax.swing.JLabel();
        lbObsersavacoes1 = new javax.swing.JLabel();
        tbtn_Solicitar = new javax.swing.JToggleButton();
        btn_GerarAtestado = new javax.swing.JButton();
        btn_GerarAtestado1 = new javax.swing.JButton();
        pSolocitarExame = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tfdDataExame = new datechooser.beans.DateChooserCombo();
        tfdHoraExame = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfdTituloExame = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textObsExame = new javax.swing.JTextArea();
        btn_SolicitarExame = new javax.swing.JButton();
        btn_Salvar = new javax.swing.JButton();
        btn_Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(0, 204, 204));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pCabecalho.setBackground(new java.awt.Color(0, 164, 137));

        btn_Consulta.setBackground(new java.awt.Color(0, 204, 153));
        btn_Consulta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_Consulta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_Consulta.setText("Consulta");
        btn_Consulta.setOpaque(true);
        btn_Consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ConsultaMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ConsultaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ConsultaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ConsultaMouseEntered(evt);
            }
        });

        btn_Financeiro.setBackground(new java.awt.Color(0, 204, 153));
        btn_Financeiro.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_Financeiro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_Financeiro.setText("Solicitar Exame");
        btn_Financeiro.setOpaque(true);
        btn_Financeiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_FinanceiroMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_FinanceiroMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_FinanceiroMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_FinanceiroMouseEntered(evt);
            }
        });

        imgTituloJanela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufac/bsi/images/Agenda_60px.png"))); // NOI18N

        lbIMGAtendimento.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lbIMGAtendimento.setForeground(new java.awt.Color(255, 255, 255));
        lbIMGAtendimento.setText("Consulta - Atendimento Clínico");

        btn_Close.setBackground(new java.awt.Color(0, 164, 137));
        btn_Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ufac/bsi/images/Close Window_36px.png"))); // NOI18N
        btn_Close.setOpaque(true);
        btn_Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CloseMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_CloseMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_CloseMouseEntered(evt);
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
                .addComponent(imgTituloJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCabecalhoLayout.createSequentialGroup()
                        .addComponent(lbIMGAtendimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                        .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Minimizar)
                            .addGroup(pCabecalhoLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btn_Maximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2)
                        .addComponent(btn_Close))
                    .addGroup(pCabecalhoLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(btn_Consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Financeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pCabecalhoLayout.setVerticalGroup(
            pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCabecalhoLayout.createSequentialGroup()
                .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCabecalhoLayout.createSequentialGroup()
                        .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Maximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pCabecalhoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pCabecalhoLayout.createSequentialGroup()
                                .addComponent(lbIMGAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pCabecalhoLayout.createSequentialGroup()
                                .addComponent(imgTituloJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(pCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Consulta, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btn_Financeiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );

        bg.add(pCabecalho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 110));

        pCentral.setBackground(new java.awt.Color(255, 255, 255));
        pCentral.setLayout(new java.awt.CardLayout());

        pConsulta.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 204, 204));
        jLabel1.setOpaque(true);

        lbObsersavacoes.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbObsersavacoes.setText("Observações:");

        textObservacoes.setColumns(20);
        textObservacoes.setRows(5);
        jScrollPaneObservacoes.setViewportView(textObservacoes);

        lbTitlePaciente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTitlePaciente.setText("Paciente:");

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTitulo.setText("Titulo:");

        lbPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPaciente.setText("PACIENTE DE TAL");

        lbObsersavacoes1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbObsersavacoes1.setText("Solicitar Exame:");

        tbtn_Solicitar.setText("NÃO");
        tbtn_Solicitar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tbtn_SolicitarItemStateChanged(evt);
            }
        });
        tbtn_Solicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtn_SolicitarActionPerformed(evt);
            }
        });

        btn_GerarAtestado.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        btn_GerarAtestado.setText("GERAR ATESTADO");
        btn_GerarAtestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GerarAtestadoActionPerformed(evt);
            }
        });

        btn_GerarAtestado1.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        btn_GerarAtestado1.setText("RECEITA MÉDICA");
        btn_GerarAtestado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GerarAtestado1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pConsultaLayout = new javax.swing.GroupLayout(pConsulta);
        pConsulta.setLayout(pConsultaLayout);
        pConsultaLayout.setHorizontalGroup(
            pConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConsultaLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(pConsultaLayout.createSequentialGroup()
                .addGroup(pConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pConsultaLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(pConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pConsultaLayout.createSequentialGroup()
                                .addComponent(lbTitulo)
                                .addGap(18, 18, 18)
                                .addComponent(tfdTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pConsultaLayout.createSequentialGroup()
                                .addComponent(lbObsersavacoes)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPaneObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pConsultaLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(lbTitlePaciente)
                        .addGap(31, 31, 31)
                        .addComponent(lbPaciente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pConsultaLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(lbObsersavacoes1)
                        .addGap(18, 18, 18)
                        .addComponent(tbtn_Solicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_GerarAtestado1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_GerarAtestado, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pConsultaLayout.setVerticalGroup(
            pConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConsultaLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(pConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitlePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbObsersavacoes)
                    .addComponent(jScrollPaneObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbObsersavacoes1)
                    .addComponent(tbtn_Solicitar)
                    .addComponent(btn_GerarAtestado)
                    .addComponent(btn_GerarAtestado1))
                .addGap(51, 51, 51))
        );

        pCentral.add(pConsulta, "Anamnese");

        pSolocitarExame.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setBackground(new java.awt.Color(0, 204, 204));
        jLabel13.setOpaque(true);

        tfdDataExame.setFieldFont(new java.awt.Font("Noto Sans", java.awt.Font.PLAIN, 15));

        try {
            tfdHoraExame.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfdHoraExame.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfdHoraExame.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 17)); // NOI18N
        jLabel2.setText("Data do Exame:");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 0, 17)); // NOI18N
        jLabel3.setText("Hora do Exame:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel4.setText("horas:min");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 0, 17)); // NOI18N
        jLabel5.setText("Titulo Exame:");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 0, 17)); // NOI18N
        jLabel6.setText("Observacões:");

        textObsExame.setColumns(20);
        textObsExame.setFont(new java.awt.Font("Noto Sans", 0, 16)); // NOI18N
        textObsExame.setRows(5);
        jScrollPane1.setViewportView(textObsExame);

        btn_SolicitarExame.setText("SOLICITAR EXAME");
        btn_SolicitarExame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SolicitarExameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pSolocitarExameLayout = new javax.swing.GroupLayout(pSolocitarExame);
        pSolocitarExame.setLayout(pSolocitarExameLayout);
        pSolocitarExameLayout.setHorizontalGroup(
            pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
            .addGroup(pSolocitarExameLayout.createSequentialGroup()
                .addGroup(pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSolocitarExameLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(18, 18, 18)
                        .addGroup(pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pSolocitarExameLayout.createSequentialGroup()
                                .addComponent(tfdHoraExame, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfdDataExame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdTituloExame, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pSolocitarExameLayout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(btn_SolicitarExame, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pSolocitarExameLayout.setVerticalGroup(
            pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSolocitarExameLayout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdTituloExame, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(47, 47, 47)
                .addGroup(pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdDataExame, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdHoraExame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33)
                .addGroup(pSolocitarExameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_SolicitarExame)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pCentral.add(pSolocitarExame, "Financeiro");

        bg.add(pCentral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 810, 440));

        btn_Salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Salvar.setText("SALVAR");
        btn_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarActionPerformed(evt);
            }
        });
        bg.add(btn_Salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 560, 90, 30));

        btn_Sair.setBackground(new java.awt.Color(255, 255, 255));
        btn_Sair.setText("SAIR");
        btn_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SairActionPerformed(evt);
            }
        });
        bg.add(btn_Sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 560, 70, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
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
        if(Pai==null){
            System.exit(0);
        }else{
        Pai.setVisible(true);
        this.dispose();  
        }
        
    }//GEN-LAST:event_btn_SairActionPerformed

    private void btn_ConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ConsultaMouseEntered
        if(!StatusMenu.equals("Anamnese"))
        setColorEntered(btn_Consulta);
    }//GEN-LAST:event_btn_ConsultaMouseEntered

    private void btn_ConsultaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ConsultaMouseExited
        if(!StatusMenu.equals("Anamnese"))
        setColorExited(btn_Consulta);
    }//GEN-LAST:event_btn_ConsultaMouseExited

    private void btn_ConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ConsultaMouseClicked
        setColorClicked(btn_Consulta);
        setColorExited(btn_Financeiro);
        StatusMenu = "Anamnese";
        CardLayout cl = (CardLayout) pCentral.getLayout();
        cl.show(pCentral, "Anamnese");
    }//GEN-LAST:event_btn_ConsultaMouseClicked

    private void btn_ConsultaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ConsultaMousePressed
        setColorPressed(btn_Consulta);
    }//GEN-LAST:event_btn_ConsultaMousePressed

    private void btn_FinanceiroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_FinanceiroMouseEntered
        if(!StatusMenu.equals("Financeiro")){
            setColorEntered(btn_Financeiro);
        }
    }//GEN-LAST:event_btn_FinanceiroMouseEntered

    private void btn_FinanceiroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_FinanceiroMouseExited
        if(!StatusMenu.equals("Financeiro"))
        setColorExited(btn_Financeiro);
    }//GEN-LAST:event_btn_FinanceiroMouseExited

    private void btn_FinanceiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_FinanceiroMouseClicked

        btn_Financeiro.setText("Solicitar Exames");
        setColorClicked(btn_Financeiro);
        setColorExited(btn_Consulta);
        StatusMenu = "Financeiro";
        CardLayout cl = (CardLayout) pCentral.getLayout();
        cl.show(pCentral, "Financeiro");

    }//GEN-LAST:event_btn_FinanceiroMouseClicked

    private void btn_FinanceiroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_FinanceiroMousePressed
        setColorPressed(btn_Financeiro);
    }//GEN-LAST:event_btn_FinanceiroMousePressed

    private void btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarActionPerformed
        SalvarConsulta();
        StatusOperacoes[0] = true;
        
        if(StatusOperacoes[0]==true && StatusOperacoes[1]==true){
            this.setVisible(false);
            Pai.setVisible(true);
        }
        
    }//GEN-LAST:event_btn_SalvarActionPerformed

    private void tbtn_SolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtn_SolicitarActionPerformed
        
        
    }//GEN-LAST:event_tbtn_SolicitarActionPerformed

    private void tbtn_SolicitarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tbtn_SolicitarItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
           tbtn_Solicitar.setText("SIM");
           StatusOperacoes[1]=false;
           EnableButton();
        }
        
        if(evt.getStateChange() == ItemEvent.DESELECTED){
           tbtn_Solicitar.setText("NÃO");
           StatusOperacoes[1]=true;
           DisableButton();
        }
    }//GEN-LAST:event_tbtn_SolicitarItemStateChanged

    private void btn_SolicitarExameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SolicitarExameActionPerformed
        solicitarExame();
        LimparCamposConsulta();
        JOptionPane.showMessageDialog(null, "Exame Solicato com Sucesso!");
        StatusOperacoes[1]=true;
    }//GEN-LAST:event_btn_SolicitarExameActionPerformed

    private void btn_GerarAtestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GerarAtestadoActionPerformed
        GerarAtestadoPDF();
    }//GEN-LAST:event_btn_GerarAtestadoActionPerformed

    private void btn_GerarAtestado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GerarAtestado1ActionPerformed
        ReceitaMedica receitaView = new ReceitaMedica();
        receitaView.setVisible(true);
    }//GEN-LAST:event_btn_GerarAtestado1ActionPerformed
    public void EnableButton(){
        btn_Financeiro.setText("Solicitar Exame");
        setColorExited(btn_Financeiro);
    }
    
    public void DisableButton(){
        btn_Financeiro.setText("");
        btn_Financeiro.setBackground(new Color(0,164,137));
        StatusOperacoes[1]=true;
    }
    
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
            java.util.logging.Logger.getLogger(AtendimentoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtendimentoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtendimentoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtendimentoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtendimentoConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.ButtonGroup bgFinanceiro;
    private javax.swing.JLabel btn_Close;
    private javax.swing.JLabel btn_Consulta;
    private javax.swing.JLabel btn_Financeiro;
    private javax.swing.JButton btn_GerarAtestado;
    private javax.swing.JButton btn_GerarAtestado1;
    private javax.swing.JLabel btn_Maximizar;
    private javax.swing.JLabel btn_Minimizar;
    private javax.swing.JButton btn_Sair;
    private javax.swing.JButton btn_Salvar;
    private javax.swing.JButton btn_SolicitarExame;
    private javax.swing.JLabel imgTituloJanela;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneObservacoes;
    private javax.swing.JLabel lbIMGAtendimento;
    private javax.swing.JLabel lbObsersavacoes;
    private javax.swing.JLabel lbObsersavacoes1;
    private javax.swing.JLabel lbPaciente;
    private javax.swing.JLabel lbTitlePaciente;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pCabecalho;
    private javax.swing.JPanel pCentral;
    private javax.swing.JPanel pConsulta;
    private javax.swing.JPanel pSolocitarExame;
    private javax.swing.JToggleButton tbtn_Solicitar;
    private javax.swing.JTextArea textObsExame;
    private javax.swing.JTextArea textObservacoes;
    private datechooser.beans.DateChooserCombo tfdDataExame;
    private javax.swing.JFormattedTextField tfdHoraExame;
    private javax.swing.JTextField tfdTitulo;
    private javax.swing.JTextField tfdTituloExame;
    // End of variables declaration//GEN-END:variables
}
