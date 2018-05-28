/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Contas;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Conta;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Repositorios.AgendamentoRepositorio;
import JFX.BSI.GesMed.Repositorios.ContaRepositorio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class MainContasController implements Initializable {
    
    @FXML
    private StackPane StackPaneRoot;
    
    @FXML
    private AnchorPane sideAnchor;

    @FXML
    private JFXButton btnConsultaMedica;

    @FXML
    private JFXButton btnContasPagas;

    @FXML
    private JFXButton btnGerenciarDespesas;

    @FXML
    private JFXButton btnSolicitarExame;

    @FXML
    private AnchorPane VisualizarContas;

    @FXML
    private JFXTreeTableView<ContasFX> tblContasViewContas;

    @FXML
    private JFXButton btnLancarConta;

    @FXML
    private JFXButton btnEditarVC;

    @FXML
    private JFXButton btnExcluirVC;

    @FXML
    private JFXTextField tfdPacienteView;

    @FXML
    private AnchorPane ContasPendentes;

    @FXML
    private JFXTreeTableView<ContasFX> tblContasPendentes;

    @FXML
    private JFXButton btnPagarConta;

    @FXML
    private JFXButton btnEditarCP;

    @FXML
    private JFXButton btnExcluirCP;

    @FXML
    private JFXTextField tfdPacienteCP;

    @FXML
    private AnchorPane ContasPagas;

    @FXML
    private JFXTreeTableView<ContasFX> tblContasPagas;

    @FXML
    private JFXButton btnEditarCPg;
    
    @FXML
    private JFXButton btnGerarRecibo;

    @FXML
    private JFXButton btnExcluirCPg;

    @FXML
    private JFXTextField tfdPacienteCPg;

    @FXML
    private AnchorPane GerenciarDespesas;



    @FXML
    private JFXButton btnAdicionar;

    @FXML
    private JFXButton btnEditarDespesa;

    @FXML
    private JFXButton btnExcluirDespesa;

    @FXML
    private JFXTextField tfdTituloDespesa;
    
    private ObservableList<ContasFX> listContasPacientesFX = FXCollections.observableArrayList();
    private ObservableList<ContasFX> listContasPendentesFX = FXCollections.observableArrayList();
    private ObservableList<ContasFX> listContasPagasFX = FXCollections.observableArrayList();
    
    private List<Agendamento> listAgendamentos;
    private List<Conta> listContasPendentes;
    private List<Conta> listContasPagas;
  
    private static Paragraph paragrafo;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableViewContas();
    }
    
    @FXML
    public void openLancarConta(MouseEvent event){
        
        TreeItem<ContasFX> contaTFX =  tblContasViewContas.getSelectionModel().getSelectedItem();
        ContasFX contafx = contaTFX.getValue();
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        Agendamento agenda = agenRep.recuperar(Integer.parseInt(contafx.getIDConta().getValue()));
        Paciente paciente = agenda.getPaciente();
        
        if(paciente!=null&&agenda!=null){
            try {
        Stage currentStage = (Stage) StackPaneRoot.getScene().getWindow();
        Stage stage = new Stage();
        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.DECORATED);
        stage.initModality(Modality.NONE);
        FXMLLoader lancarContaFXML = new FXMLLoader(LancarContaController.class.getResource("/JFX/BSI/GesMed/Interfaces/Contas/LancarConta.fxml"));
        
        LancarContaController contaControl = new LancarContaController(this,paciente,agenda);
        lancarContaFXML.setController(contaControl);
        Parent root = lancarContaFXML.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainContasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }

    
    public void NullAgendamentos(){
        listAgendamentos = null;
        listContasPendentes = null;
        TableViewContas();
        TableContasPendentes();
    }
    
  
    
    public void TableViewContas(){
        
        JFXTreeTableColumn<ContasFX,String> tblPaciente = new JFXTreeTableColumn<>("NOME DO PACIENTE");
        tblPaciente.setMinWidth(300);
        tblPaciente.setPrefWidth(400);
        tblPaciente.setMaxWidth(300);
        tblPaciente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getNomePaciente();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblStatus = new JFXTreeTableColumn<>("STATUS");
        tblStatus.setMinWidth(100);
        tblStatus.setPrefWidth(150);
        tblStatus.setMaxWidth(800);
        tblStatus.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getStatus();
            } 
        });
        
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        
        if(listAgendamentos==null){
            listAgendamentos = agenRep.getContasPacientes();
        }
        ContasFX cPacFX;
        if(listAgendamentos!=null){
            cPacFX = new ContasFX();
            listContasPacientesFX = FXCollections.observableArrayList();
            
            for(Agendamento agenda : listAgendamentos){
                cPacFX.setNomePaciente(agenda.getPaciente().getNome());
                cPacFX.setStatus(agenda.getStatus());
                cPacFX.setIDPaciente(Integer.toString(agenda.getPaciente().getID()));
                cPacFX.setCPF(agenda.getPaciente().getCPF());
                cPacFX.setIDConta(Integer.toString(agenda.getIDAgenda()));
                listContasPacientesFX.add(cPacFX);
            }
            
        }
        
         tfdPacienteView.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               tblContasViewContas.setPredicate(new Predicate<TreeItem<ContasFX>>() {
                   @Override
                   public boolean test(TreeItem<ContasFX> paciente) {
                      Boolean flag = paciente.getValue().getNomePaciente().getValue().contains(newValue);
                      return flag;
                   }
               });
            }
        });
        
        final TreeItem<ContasFX> root = new RecursiveTreeItem<ContasFX>(listContasPacientesFX, RecursiveTreeObject::getChildren);
        tblContasViewContas.refresh();
        tblContasViewContas.getColumns().setAll(tblPaciente, tblStatus);
        tblContasViewContas.setRoot(root);
        tblContasViewContas.setShowRoot(false);
        
        }
    
    
    @FXML
    public void openPagarConta(MouseEvent event){
        
        TreeItem<ContasFX> contaTFX =  tblContasPendentes.getSelectionModel().getSelectedItem();
        ContasFX contafx = contaTFX.getValue();
        ContaRepositorio contaRep = new ContaRepositorio();
        Conta contaPac = contaRep.recuperar(Integer.parseInt(contafx.getIDConta().getValue()));
        
        
        if(contaPac!=null){
        try {
        Stage currentStage = (Stage) StackPaneRoot.getScene().getWindow();
        Stage stage = new Stage();
        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.NONE);
        FXMLLoader lancarContaFXML = new FXMLLoader(LancarContaController.class.getResource("/JFX/BSI/GesMed/Interfaces/Contas/PagarConta.fxml"));
        
        PagarContaController contaControl = new PagarContaController(this,contaPac);
        lancarContaFXML.setController(contaControl);
        Parent root = lancarContaFXML.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainContasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public void NullContasPendentes(){
        listContasPendentes = null;
        listContasPagas = null;
        TableContasPendentes();
        TableContasPagas();
    }
    
    public void TableContasPendentes(){
        
        JFXTreeTableColumn<ContasFX,String> tblPaciente = new JFXTreeTableColumn<>("NOME DO PACIENTE");
        tblPaciente.setMinWidth(300);
        tblPaciente.setPrefWidth(350);
        tblPaciente.setMaxWidth(2000);
        tblPaciente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getNomePaciente();
            } 
        });
        
     
        JFXTreeTableColumn<ContasFX,String> tblValor = new JFXTreeTableColumn<>("VALOR");
        tblValor.setMinWidth(100);
        tblValor.setPrefWidth(125);
        tblValor.setMaxWidth(500);
        tblValor.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getValor();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblDataVencimento = new JFXTreeTableColumn<>("DATA VENCIMENTO");
        tblDataVencimento.setMinWidth(100);
        tblDataVencimento.setPrefWidth(160);
        tblDataVencimento.setMaxWidth(800);
        tblDataVencimento.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getDataPagamento();
            } 
        });
        
        ContaRepositorio contRep = new ContaRepositorio();
        
        if(listContasPendentes==null){
            listContasPendentes = contRep.recuperarTodasContasPendentes();
        }
        listContasPendentesFX = FXCollections.observableArrayList();
        ContasFX cPacFX;
        if(listContasPendentes!=null){
            cPacFX = new ContasFX();
            for(Conta cPac : listContasPendentes){
                if(cPac.getCPF()!=null){
                cPacFX.setIDConta(Integer.toString(cPac.getIDConta()));
                cPacFX.setNomePaciente(cPac.getNome());
                cPacFX.setCPF(cPac.getCPF());
                cPacFX.setValor(Double.toString(cPac.getValor()));
                cPacFX.setDataPagamento(cPac.getDataLancamento().toString());
                cPacFX.setStatus(cPac.getStatus());
                listContasPendentesFX.add(cPacFX);  
                }
                
            }
        }
        
        tfdPacienteCP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               tblContasPendentes.setPredicate(new Predicate<TreeItem<ContasFX>>() {
                   @Override
                   public boolean test(TreeItem<ContasFX> paciente) {
                      Boolean flag = paciente.getValue().getNomePaciente().getValue().contains(newValue);
                      return flag;
                   }
               });
            }
        });
        
        final TreeItem<ContasFX> root = new RecursiveTreeItem<ContasFX>(listContasPendentesFX, RecursiveTreeObject::getChildren);
        tblContasPendentes.refresh();
        tblContasPendentes.getColumns().setAll(tblPaciente, tblValor, tblDataVencimento);
        tblContasPendentes.setRoot(root);
        tblContasPendentes.setShowRoot(false); 
    }
    
    public void TableContasPagas(){
        
        JFXTreeTableColumn<ContasFX,String> tblPaciente = new JFXTreeTableColumn<>("NOME DO PACIENTE");
        tblPaciente.setMinWidth(300);
        tblPaciente.setPrefWidth(350);
        tblPaciente.setMaxWidth(2000);
        tblPaciente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getNomePaciente();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblValor = new JFXTreeTableColumn<>("VALOR");
        tblValor.setMinWidth(100);
        tblValor.setPrefWidth(125);
        tblValor.setMaxWidth(500);
        tblValor.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getValor();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblDataPagamento = new JFXTreeTableColumn<>("DATA DE PAGAMENTO");
        tblDataPagamento.setMinWidth(100);
        tblDataPagamento.setPrefWidth(160);
        tblDataPagamento.setMaxWidth(800);
        tblDataPagamento.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getDataPagamento();
            } 
        });
        
        ContaRepositorio contRep = new ContaRepositorio();
        
        if(listContasPagas==null){
            listContasPagas = contRep.recuperarTodasContasPagas();
        }
        listContasPacientesFX.clear();
        listContasPagasFX = FXCollections.observableArrayList();
        ContasFX cPacFX;
        if(listContasPagas!=null){
            cPacFX = new ContasFX();
            for(Conta cPac : listContasPagas){
                cPacFX.setIDConta(Integer.toString(cPac.getIDConta()));
                cPacFX.setNomePaciente(cPac.getNome());
                cPacFX.setCPF(cPac.getCPF());
                cPacFX.setValor(Double.toString(cPac.getValor()));
                cPacFX.setDataPagamento(cPac.getDataLancamento().toString());
                cPacFX.setStatus(cPac.getStatus());
                listContasPagasFX.add(cPacFX);
            }
        }
        
        tfdPacienteCPg.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               tblContasPagas.setPredicate(new Predicate<TreeItem<ContasFX>>() {
                   @Override
                   public boolean test(TreeItem<ContasFX> paciente) {
                      Boolean flag = paciente.getValue().getNomePaciente().getValue().contains(newValue);
                      return flag;
                   }
               });
            }
        });
        
        final TreeItem<ContasFX> root = new RecursiveTreeItem<ContasFX>(listContasPagasFX, RecursiveTreeObject::getChildren);
        tblContasPagas.refresh();
        tblContasPagas.getColumns().setAll(tblPaciente, tblValor, tblDataPagamento);
        tblContasPagas.setRoot(root);
        tblContasPagas.setShowRoot(false); 
    }
    
    @FXML
    public void GerarRecibo(MouseEvent event){
        
        TreeItem<ContasFX> contaTFX =  tblContasPagas.getSelectionModel().getSelectedItem();
        ContasFX contafx = contaTFX.getValue();
        ContaRepositorio contaRep = new ContaRepositorio();
        Conta contaPaciente = contaRep.recuperar(Integer.parseInt(contafx.getIDConta().getValue()));
        
        
        Document document = new Document();
            LocalDate dataHoje = LocalDate.now();
            String DataHoje = dataHoje.toString();
          
            String[] vetDataHoje = DataHoje.split("-");
            DataHoje = vetDataHoje[2]+"/"+vetDataHoje[1]+"/"+vetDataHoje[0];
            
            String DataPago = contaPaciente.getDataLancamento().toString();
            
            String[] vetDataPago = DataPago.split("-");
            DataPago = vetDataPago[2]+"/"+vetDataPago[1]+"/"+vetDataPago[0];
            
            String DataPagoCaminho = vetDataPago[2]+"-"+vetDataPago[1]+"-"+vetDataPago[0];
          try {
              
              FileOutputStream pdfRecibo = new FileOutputStream(".\\Recibo - "+contaPaciente.getReferencia()+" "+DataPagoCaminho+".pdf");
              
              PdfWriter.getInstance(document, pdfRecibo);
              document.open();
              Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
              Font bold2 = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
              Font fontConteudo = new Font(Font.FontFamily.TIMES_ROMAN, 15);
              // adicionando um parágrafo no documento
              paragrafo = new Paragraph("GESMED - Clinica Academicos da UFAC", bold2);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("Recibo de Pagamento para: "+ contaPaciente.getReferencia(), bold);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              
              document.add(paragrafo);
              paragrafo = new Paragraph("\n\n\nO Paciente "+contaPaciente.getReferencia()+", CPF nº "+contaPaciente.getCPF()+", pagou a quantia de R$ "+ contaPaciente.getValor() + " Reais no dia "+DataPago+" Referente a uma Consulta Realizada.", fontConteudo);
              document.add(paragrafo);
              String RodapeFinal = "\n" + "__________________________________________________________"+
                                         "\n" +  " Assinatura da Secretária"+"\n\n\n\n\n"+
                                        "Clínica Universitaria - Avenida Ceara nº 345 \n" +
                                            "Rio Branco - Acre";
                      
              paragrafo = new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+RodapeFinal);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
        
          }catch(DocumentException de) {
              System.err.println(de.getMessage());
          }
          catch(IOException ioe) {
              System.err.println(ioe.getMessage());
          }finally{
               document.close();
               
          }
          
        try {
             Desktop.getDesktop().open(new File(".\\Recibo - "+contaPaciente.getReferencia()+" "+DataPagoCaminho+".pdf"));
        } catch (IOException ex) {
             Logger.getLogger(MainContasController.class.getName()).log(Level.SEVERE, null, ex);
        }   
          
    }
    
   
    @FXML
    public void openViewContas(MouseEvent event){
        TableViewContas();
        DisableButtonsVC();
        VisualizarContas.setVisible(true);
        ContasPendentes.setVisible(false);
        ContasPagas.setVisible(false);
        GerenciarDespesas.setVisible(false);
    }
    
    @FXML
    public void openContasPendentes(MouseEvent event){
        TableContasPendentes();
        DisableButtonsCP();
        VisualizarContas.setVisible(false);
        ContasPendentes.setVisible(true);
        ContasPagas.setVisible(false);
    
    }
    
    @FXML
    public void openContasPagas(MouseEvent event){
        TableContasPagas();
        DisableButtonsCPG();
        VisualizarContas.setVisible(false);
        ContasPendentes.setVisible(false);
        ContasPagas.setVisible(true);
       
    }
    
    @FXML
    public void openGerenciarDespesas(MouseEvent event){
        VisualizarContas.setVisible(false);
        ContasPendentes.setVisible(false);
        ContasPagas.setVisible(false);
    }
    
    @FXML
    public void EnableButtonsVC(){
        if(tblContasViewContas.getSelectionModel().getSelectedIndex()>=0){
            btnLancarConta.setDisable(false);
            btnEditarVC.setDisable(false);
            btnExcluirVC.setDisable(false);
        }else{
            btnLancarConta.setDisable(true);
            btnEditarVC.setDisable(true);
            btnExcluirVC.setDisable(true);
        }
    }
    
    @FXML
    public void DisableButtonsVC(){
        btnLancarConta.setDisable(true);
        btnEditarVC.setDisable(true);
        btnExcluirVC.setDisable(true);
        tblContasViewContas.getSelectionModel().clearSelection();
    }
    
    @FXML
    public void EnableButtonsCP(){
        if(tblContasPendentes.getSelectionModel().getSelectedIndex()>=0){
            btnPagarConta.setDisable(false);
            btnEditarCP.setDisable(false);
            btnExcluirCP.setDisable(false);
        }else{
            btnLancarConta.setDisable(true);
            btnEditarCP.setDisable(true);
            btnExcluirCP.setDisable(true);
        }
    }
    
    @FXML
    public void DisableButtonsCP(){
        btnPagarConta.setDisable(true);
        btnEditarCP.setDisable(true);
        btnExcluirCP.setDisable(true);
        tblContasPendentes.getSelectionModel().clearSelection();
    }
    
    @FXML
    public void EnableButtonsCPg(){
        if(tblContasPagas.getSelectionModel().getSelectedIndex()>=0){
            btnGerarRecibo.setDisable(false);
            btnEditarCPg.setDisable(false);
            btnExcluirCPg.setDisable(false);
        }else{
            btnGerarRecibo.setDisable(true);
            btnEditarCPg.setDisable(true);
            btnExcluirCPg.setDisable(true);
        }
    }
    
    @FXML
    public void DisableButtonsCPG(){
        btnGerarRecibo.setDisable(true);
        btnEditarCPg.setDisable(true);
        btnExcluirCPg.setDisable(true);
        tblContasPagas.getSelectionModel().clearSelection();
    }
    
 
    class ContasFX extends RecursiveTreeObject<ContasFX> {
       
       private StringProperty IDConta;
       private StringProperty Valor;
       private StringProperty DataPagamento;
       private StringProperty Status;
       
       private StringProperty IDPaciente;
       private StringProperty NomePaciente;
       private StringProperty CPF;
       


        public StringProperty getIDConta() {
            return IDConta;
        }

        public void setIDConta(String IDConta) {
            this.IDConta = new SimpleStringProperty(IDConta);
        }

        public StringProperty getValor() {
            return Valor;
        }

        public void setValor(String Valor) {
            this.Valor = new SimpleStringProperty(Valor);
        }

        public StringProperty getDataPagamento() {
            return DataPagamento;
        }

        public void setDataPagamento(String DataPagamento) {
            this.DataPagamento = new SimpleStringProperty(DataPagamento);
        }

        public StringProperty getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = new SimpleStringProperty(Status);
        }

        public StringProperty getIDPaciente() {
            return IDPaciente;
        }

        public void setIDPaciente(String IDPaciente) {
            this.IDPaciente = new SimpleStringProperty(IDPaciente);
        }

        
        
        public StringProperty getNomePaciente() {
            return NomePaciente;
        }

        public void setNomePaciente(String NomePaciente) {
            this.NomePaciente = new SimpleStringProperty(NomePaciente);
        }

        public StringProperty getCPF() {
            return CPF;
        }

        public void setCPF(String CPF) {
            this.CPF = new SimpleStringProperty(CPF);
        }

       
    }
    
     
 }
