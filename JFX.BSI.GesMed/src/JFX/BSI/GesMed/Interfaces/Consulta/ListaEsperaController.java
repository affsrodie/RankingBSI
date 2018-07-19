/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;


import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Repositorios.AgendamentoRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.persistence.PersistenceException;
import org.hibernate.exception.DataException;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class ListaEsperaController implements Initializable {
    
    @FXML
    private BorderPane borderPaneRoot;

    @FXML
    private VBox PaneLateral;

    @FXML
    private AnchorPane paneLatOptions;

    @FXML
    private JFXDatePicker tfdDataAgendamento;

    @FXML
    private JFXComboBox jcbStatus;

    @FXML
    private JFXButton btnIniciarConsulta;



    @FXML
    private JFXTreeTableView<AgendamentoFX> tblListaEspera;

    @FXML
    private Label infoNomePaciente;

    @FXML
    private Label infoStatus;

    @FXML
    private Label infoOrdem;

    @FXML
    private Label infoHoraInicio;

    @FXML
    private Label infoHoraFim;
    
    private List<Agendamento> listAgendamentos;
    private ObservableList<AgendamentoFX> agendamentosFX = FXCollections.observableArrayList();
    
   
    private Agendamento agendaSelecRow;
  

 
    
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarregarTabelaAgendamento();
    }
    
    @FXML
    public void openConsultaPaciente(MouseEvent event){
        FXMLLoader openConsulta = new FXMLLoader(ListaEsperaController.class.getResource("/JFX/BSI/GesMed/Interfaces/Agenda/ConsultaPaciente.fxml"));
        ConsultaPacienteController consultaPacControl = new ConsultaPacienteController(getAgendamento());
        openConsulta.setController(consultaPacControl);
        openStage("/JFX/BSI/GesMed/Interfaces/Agenda/ConsultaPaciente.fxml", openConsulta);
    }
    
    public Agendamento getAgendamento (){
        Agendamento agenda=null;
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        if(tblListaEspera.getSelectionModel().getSelectedIndex()>=0){
        TreeItem<AgendamentoFX> agendaFX =  tblListaEspera.getSelectionModel().getSelectedItem();
        AgendamentoFX agendamento = agendaFX.getValue();
        agenda=agenRep.recuperar(Integer.parseInt(agendamento.IDAgendamento.getValue()));
        }
        return agenda;
    }
    
    
    private void openStage(String fxml, FXMLLoader fxmlPath) {
        try {
        Stage stage = new Stage();
        Stage currentStage = (Stage) borderPaneRoot.getScene().getWindow();
        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.DECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = fxmlPath.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      
        } catch (IOException ex) {
            Logger.getLogger(MainAgendasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void CarregarTabelaAgendamento(){
        
        JFXTreeTableColumn<AgendamentoFX,String> tblOrdem = new JFXTreeTableColumn<>("ORDEM");
        tblOrdem.setMinWidth(200);
        tblOrdem.setPrefWidth(200);
        tblOrdem.setMaxWidth(300);
        tblOrdem.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().Ordem;
            }
        });
        
        JFXTreeTableColumn<AgendamentoFX,String> tblNome = new JFXTreeTableColumn<>("NOME DO PACIENTE");
        tblNome.setMinWidth(200);
        tblNome.setPrefWidth(200);
        tblNome.setMaxWidth(300);
        tblNome.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().nomePaciente;
            }
        });
        
        JFXTreeTableColumn<AgendamentoFX,String> tblStatus = new JFXTreeTableColumn<>("STATUS");
        tblStatus.setMinWidth(200);
        tblStatus.setPrefWidth(200);
        tblStatus.setMaxWidth(300);
        tblStatus.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().Status;
            }
        });
        
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        
        if(listAgendamentos==null){
            try{
             listAgendamentos = agenRep.getListaEsperaHoje();   
            }catch(PersistenceException pex){
                Alert AlertErro = new Alert(Alert.AlertType.ERROR);
                AlertErro.setTitle("Lista de Espera");
                AlertErro.setHeaderText("Consulta de Registro de Atendimentos");
                AlertErro.setContentText("Problemas ao buscar os registro de agendamentos");
                AlertErro.showAndWait();
            }
            
        }
        
        AgendamentoFX agendaFX;
        agendamentosFX = FXCollections.observableArrayList();
        if(listAgendamentos!=null){
            for(Agendamento agenda : listAgendamentos){
                agendaFX = new AgendamentoFX();
                agendaFX.setIDAgendamento(Integer.toString(agenda.getIDAgenda()));
                agendaFX.setIDPaciente(Integer.toString(agenda.getPaciente().getID()));
                agendaFX.setOrdem(Integer.toString(listAgendamentos.indexOf(agenda)+1));
                agendaFX.setHoraInicio(agenda.getHoraInicio());
                agendaFX.setHoraFim(agenda.getHoraFim());
                agendaFX.setStatus(agenda.getStatus());
                agendaFX.setNomePaciente(agenda.getPaciente().getNome());
                agendaFX.setTelefone(agenda.getPaciente().getTelefone().getTelefone());
                agendaFX.setProcedimento(agenda.getProcedimento());
                agendaFX.setConvenio(agenda.getConvenio());
                agendamentosFX.add(agendaFX);
            }
        }
        
        
        final TreeItem<AgendamentoFX> root = new RecursiveTreeItem<AgendamentoFX>(agendamentosFX, RecursiveTreeObject::getChildren);
        tblListaEspera.refresh();
        tblListaEspera.getColumns().setAll(tblOrdem, tblNome, tblStatus);
        tblListaEspera.setRoot(root);
        tblListaEspera.setShowRoot(false);
        
    }
    
    public void atualizarTabelaAgendamento(){
        
        JFXTreeTableColumn<AgendamentoFX,String> tblOrdem = new JFXTreeTableColumn<>("ORDEM");
        tblOrdem.setMinWidth(200);
        tblOrdem.setPrefWidth(200);
        tblOrdem.setMaxWidth(300);
        tblOrdem.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().Ordem;
            }
        });
        
        JFXTreeTableColumn<AgendamentoFX,String> tblNome = new JFXTreeTableColumn<>("NOME DO PACIENTE");
        tblNome.setMinWidth(200);
        tblNome.setPrefWidth(200);
        tblNome.setMaxWidth(300);
        tblNome.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().nomePaciente;
            }
        });
        
        JFXTreeTableColumn<AgendamentoFX,String> tblStatus = new JFXTreeTableColumn<>("STATUS");
        tblStatus.setMinWidth(200);
        tblStatus.setPrefWidth(200);
        tblStatus.setMaxWidth(300);
        tblStatus.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().Status;
            }
        });
        
      
        AgendamentoFX agendaFX;
        agendamentosFX = FXCollections.observableArrayList();
        if(listAgendamentos!=null){
            for(Agendamento agenda : listAgendamentos){
                agendaFX = new AgendamentoFX();
                agendaFX.setIDAgendamento(Integer.toString(agenda.getIDAgenda()));
                agendaFX.setIDPaciente(Integer.toString(agenda.getPaciente().getID()));
                agendaFX.setOrdem(Integer.toString(listAgendamentos.indexOf(agenda)+1));
                agendaFX.setHoraInicio(agenda.getHoraInicio());
                agendaFX.setHoraFim(agenda.getHoraFim());
                agendaFX.setStatus(agenda.getStatus());
                agendaFX.setNomePaciente(agenda.getPaciente().getNome());
                agendaFX.setTelefone(agenda.getPaciente().getTelefone().getTelefone());
                agendaFX.setProcedimento(agenda.getProcedimento());
                agendaFX.setConvenio(agenda.getConvenio());
                
                agendamentosFX.add(agendaFX);
            }
        }
        
        
        final TreeItem<AgendamentoFX> root = new RecursiveTreeItem<AgendamentoFX>(agendamentosFX, RecursiveTreeObject::getChildren);
        tblListaEspera.refresh();
        tblListaEspera.getColumns().setAll(tblOrdem, tblNome, tblStatus);
        tblListaEspera.setRoot(root);
        tblListaEspera.setShowRoot(false);
        
    }
 
 
    @FXML
    public void setInfoAgendamento(MouseEvent event){
        enableButtons();
        if(tblListaEspera.getSelectionModel().getSelectedIndex()>=0){
        TreeItem<AgendamentoFX> agendaFX =  tblListaEspera.getSelectionModel().getSelectedItem();
        
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        AgendamentoFX agendamento = agendaFX.getValue();
        
        
        infoOrdem.setText(agendamento.getOrdem().getValue());
        infoHoraInicio.setText(agendamento.getHoraInicio().getValue());
        infoHoraFim.setText(agendamento.getHoraFim().getValue());
        infoNomePaciente.setText(agendamento.getNomePaciente().getValue());
        infoStatus.setText(agendaFX.getValue().getStatus().getValue()+" | "+agendaFX.getValue().getProcedimento().getValue()+" | "+agendaFX.getValue().getConvenio().getValue());  
        }
    }
    
    @FXML
    public void disableButtons(MouseEvent event){
        tblListaEspera.getSelectionModel().clearSelection();
        btnIniciarConsulta.setDisable(true);
    }
    
    @FXML
    public void enableButtons(){
        if(tblListaEspera.getSelectionModel().getSelectedIndex()<0){
            btnIniciarConsulta.setDisable(true);
        }else{
            btnIniciarConsulta.setDisable(false);
        }
    }

    class AgendamentoFX extends RecursiveTreeObject<AgendamentoFX> {
        
        private StringProperty IDAgendamento;
        private StringProperty IDPaciente;
        private StringProperty Ordem;
        private StringProperty HoraInicio;
        private StringProperty HoraFim;
        private StringProperty nomePaciente;
        private StringProperty Status;
        private StringProperty Telefone;
        private StringProperty Procedimento;
        private StringProperty Convenio;

        
        public AgendamentoFX(String IDAgenda, String IDPaciente, String Ordem, String Inicio, String Fim, String nome, String Status, String Telefone){
         
            this.IDAgendamento = new SimpleStringProperty(IDAgenda);
            this.IDPaciente = new SimpleStringProperty(IDPaciente);
            this.Ordem =  new SimpleStringProperty(Ordem);
            this.HoraInicio = new SimpleStringProperty(Inicio);
            this.HoraFim = new SimpleStringProperty(Fim);
            this.nomePaciente = new SimpleStringProperty(nome);
            this.Status = new SimpleStringProperty(Status);
            this.Telefone = new SimpleStringProperty(Telefone);
        }
        
        public AgendamentoFX(){
            
        }

        public StringProperty getIDAgendamento() {
            return IDAgendamento;
        }

        public void setIDAgendamento(String IDAgendamento) {
            this.IDAgendamento = new SimpleStringProperty(IDAgendamento);
        }

        public StringProperty getIDPaciente() {
            return IDPaciente;
        }

        public void setIDPaciente(String IDPaciente) {
            this.IDPaciente = new SimpleStringProperty(IDPaciente);
        }

        public StringProperty getOrdem() {
            return Ordem;
        }

        public void setOrdem(String Ordem) {
            this.Ordem = new SimpleStringProperty(Ordem);
        }

        public StringProperty getHoraInicio() {
            return HoraInicio;
        }

        public void setHoraInicio(String HoraInicio) {
            this.HoraInicio = new SimpleStringProperty(HoraInicio);
        }

        public StringProperty getHoraFim() {
            return HoraFim;
        }

        public void setHoraFim(String HoraFim) {
            this.HoraFim = new SimpleStringProperty(HoraFim);
        }

        public StringProperty getNomePaciente() {
            return nomePaciente;
        }

        public void setNomePaciente(String nomePaciente) {
            this.nomePaciente = new SimpleStringProperty(nomePaciente);
        }

        public StringProperty getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = new SimpleStringProperty(Status);
        }

        public StringProperty getTelefone() {
            return Telefone;
        }

        public void setTelefone(String Telefone) {
            this.Telefone = new SimpleStringProperty(Telefone);
        }

        public StringProperty getProcedimento() {
            return Procedimento;
        }

        public void setProcedimento(String Procedimento) {
            this.Procedimento = new SimpleStringProperty(Procedimento);
        }

        public StringProperty getConvenio() {
            return Convenio;
        }

        public void setConvenio(String Convenio) {
            this.Convenio = new SimpleStringProperty(Convenio);
        }
        
    }
    
    
}
