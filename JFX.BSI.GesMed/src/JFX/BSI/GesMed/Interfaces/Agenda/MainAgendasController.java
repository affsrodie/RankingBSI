/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Atendente;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Repositorios.AgendamentoRepositorio;
import JFX.BSI.GesMed.Repositorios.PacienteRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
    

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class MainAgendasController implements Initializable {

    @FXML
    private BorderPane rootGesMed;
    @FXML
    private AnchorPane paneLateral, popUpMenu;
    @FXML
    private JFXButton btn_Menu, btn_ListaEspera, btnAtualizar;
    @FXML
    private JFXTreeTableView<AgendamentoFX> tblAgendamento;
    @FXML
    private JFXTextField tfdNome;
    @FXML
    private JFXDatePicker tfdDataAgendamento;
    
    @FXML
    private JFXComboBox jcbStatus;
    
    //SEGUIMENTO PARA XML LIST_TELEFONE
    
    @FXML
    private AnchorPane paneContacts;
    
    @FXML
    private AnchorPane paneListContatos;

    @FXML
    private JFXButton btn_AddPhone;

    @FXML
    private Label infoPhonePessoal;

    @FXML
    private Label infoPhoneRecado;

    @FXML
    private Label infoPhoneFixo;

    @FXML
    private Label infoPhoneTrabalho;
    
    
    private static JFXPopup staticJFXPopup;
    
    private ObservableList<String> listStatus = FXCollections.observableArrayList("","Agendado","Confirmado","Chegou","Em Andamento","Finalizado","Cancelado","Faltou");
    
    
    private List<Agendamento> listAgendamentos;
    private ObservableList<AgendamentoFX> agendamentosFX = FXCollections.observableArrayList();
    
    private Atendente atendente;
    private Paciente paciente;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CarregarTabelaAgendamento();
        inicializarComponentes();
    }
    
    public void inicializarComponentes(){
        jcbStatus.setItems(listStatus);
        jcbStatus.setValue("");
    }
    
    
    public void AtualizarTabelaPorData(){
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        listAgendamentos = agenRep.listarPorData(tfdDataAgendamento.getValue().toString());
        recarregarTabelaAgendamento();
    }
     public void AtualizarTabelaPorHoje(){
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        listAgendamentos = agenRep.listarPorDiaHoje();
       recarregarTabelaAgendamento();
    }
    
     public void AtualizarTabelaTodasDatas(){
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        listAgendamentos = agenRep.recuperarTodos();
        recarregarTabelaAgendamento();
    } 
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
        
    }
    
    @FXML
    private void btnAtualizar(MouseEvent event){
        
        if(tfdDataAgendamento.getValue()==null){
                AtualizarTabelaTodasDatas();
        }else if(tfdDataAgendamento.getValue()!=null){
                System.out.println("Data de Agendamento: "+ tfdDataAgendamento.getValue().toString());
                AtualizarTabelaPorData();
               
        }
    }
    
    @FXML
    private void openDialog(MouseEvent event) {
        popUpMenu.setVisible(true);
        JFXPopup fXPopup = new JFXPopup();
        fXPopup.setPopupContent(popUpMenu);
        fXPopup.show(paneLateral, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 5, 5);
        staticJFXPopup = fXPopup;
    }
    
    @FXML
    private void openListaEspera(MouseEvent event) throws IOException, Exception {
         openStage("/JFX/BSI/GesMed/Interfaces/Agenda/ListaEspera.fxml");
    }
    
    @FXML
    private void openAgendarConsulta(MouseEvent event) throws IOException, Exception {
         openStage("/JFX/BSI/GesMed/Interfaces/Agenda/AgendarConsulta.fxml");
    }
    
    
    @FXML
    private void hideStage(MouseEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void HideWindows(){
        Stage currentStage = (Stage) rootGesMed.getScene().getWindow(); 
        currentStage.hide();
    }
    

    private void openStage(String fxml) {
        try {
        Stage stage = new Stage();
        Stage currentStage = (Stage) rootGesMed.getScene().getWindow();
        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.DECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      
        } catch (IOException ex) {
            Logger.getLogger(MainAgendasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CarregarTabelaAgendamento(){
        
        JFXTreeTableColumn<AgendamentoFX,String> tblHorario = new JFXTreeTableColumn<>("HORA INÍCIO|HORA FIM");
        tblHorario.setMinWidth(200);
        tblHorario.setPrefWidth(200);
        tblHorario.setMaxWidth(300);
        tblHorario.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().Horario;
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
            listAgendamentos = agenRep.recuperarTodos();
        }
        
        AgendamentoFX agendaFX;
        agendamentosFX = FXCollections.observableArrayList();
        if(listAgendamentos!=null){
            for(Agendamento agenda : listAgendamentos){
                agendaFX = new AgendamentoFX(Integer.toString(agenda.getIDAgenda()), Integer.toString(agenda.getPaciente().getID()), 
                                                            agenda.getHoraInicio()+" | "+agenda.getHoraFim(), agenda.getPaciente().getNome(), 
                                                            agenda.getStatus(), agenda.getPaciente().getTelefone().getTelefone());
                
                agendamentosFX.add(agendaFX);
            }
        }
        
        
        final TreeItem<AgendamentoFX> root = new RecursiveTreeItem<AgendamentoFX>(agendamentosFX, RecursiveTreeObject::getChildren);
        tblAgendamento.refresh();
        tblAgendamento.getColumns().setAll(tblHorario, tblNome, tblStatus);
        tblAgendamento.setRoot(root);
        tblAgendamento.setShowRoot(false);
        
        jcbStatus.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
               tblAgendamento.setPredicate(new Predicate<TreeItem<AgendamentoFX>>() {
                   @Override
                   public boolean test(TreeItem<AgendamentoFX> paciente) {
                      Boolean flag = paciente.getValue().Status.getValue().contains((CharSequence) newValue);
                      return flag;
                   }
               }); 
            }
        });
        
        
    }
    
    public void recarregarTabelaAgendamento(){
        
        TreeTableColumn<AgendamentoFX,String> tblHorario = new TreeTableColumn<>("HORA INÍCIO|HORA FIM");
        tblHorario.setMinWidth(200);
        tblHorario.setPrefWidth(200);
        tblHorario.setMaxWidth(300);
        tblHorario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().Horario;
            }
        });
        
        TreeTableColumn<AgendamentoFX,String> tblNome = new TreeTableColumn<>("HORA INÍCIO|HORA FIM");
        tblNome.setMinWidth(200);
        tblNome.setPrefWidth(200);
        tblNome.setMaxWidth(300);
        tblNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AgendamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AgendamentoFX, String> param) {
                return param.getValue().getValue().nomePaciente;
            }
        });
        
  
        JFXTreeTableColumn<AgendamentoFX,String> tblStatus = new JFXTreeTableColumn<>("STATUS");
        tblStatus.setMinWidth(200);
        tblStatus.setPrefWidth(200);
        tblStatus.setMaxWidth(300);
       jcbStatus.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
               tblAgendamento.setPredicate(new Predicate<TreeItem<AgendamentoFX>>() {
                   @Override
                   public boolean test(TreeItem<AgendamentoFX> paciente) {
                      Boolean flag = paciente.getValue().Status.getValue().contains((CharSequence) newValue);
                      return flag;
                   }
               }); 
            }
        });
       
        AgendamentoFX agendaFX;
        agendamentosFX = FXCollections.observableArrayList();
        if(listAgendamentos!=null){
            for(Agendamento agenda : listAgendamentos){
                agendaFX = new AgendamentoFX(Integer.toString(agenda.getIDAgenda()), Integer.toString(agenda.getPaciente().getID()), 
                                                            agenda.getHoraInicio()+" | "+agenda.getHoraFim(), agenda.getPaciente().getNome(), 
                                                            agenda.getStatus(), agenda.getPaciente().getTelefone().getTelefone());
                
                agendamentosFX.add(agendaFX);
            }
        }
        
        
        
        final TreeItem<AgendamentoFX> root = new RecursiveTreeItem<AgendamentoFX>(agendamentosFX, RecursiveTreeObject::getChildren);
        
        tblAgendamento.getColumns().setAll(tblHorario, tblNome, tblStatus);
        tblAgendamento.setRoot(root);
        tblAgendamento.setShowRoot(false);
        

        
        
    }
    
    @FXML
    public void procurarPorNome(){
        tblAgendamento.setPredicate(new Predicate<TreeItem<AgendamentoFX>>() {
                   @Override
                   public boolean test(TreeItem<AgendamentoFX> paciente) {
                      Boolean flag = paciente.getValue().nomePaciente.getValue().contains(tfdNome.getText());
                      return flag;
                   }
               });
    }
    
    @FXML
    public void getAgendamentoSelectRow(MouseEvent event){
        TreeItem<AgendamentoFX> agenFX =  tblAgendamento.getSelectionModel().getSelectedItem();
        AgendamentoFX pacientefx = agenFX.getValue();
        PacienteRepositorio pRep = new PacienteRepositorio();
        
        Paciente paciente = pRep.recuperar(Integer.parseInt(pacientefx.IDPaciente.getValue()));
        this.paciente = paciente;
        openListTelefone();
    }
    
    public void openListTelefone(){
       
        FXMLLoader sceneMainPrincipal = new FXMLLoader(MainAgendasController.class.getResource("/JFX/BSI/GesMed/Interfaces/Agenda/ListContacts.fxml"));
        ListContactsController listContatosControl = new ListContactsController(this.paciente);
        
        sceneMainPrincipal.setController(listContatosControl);
        
        try {
            paneListContatos = sceneMainPrincipal.load();
        } catch (IOException ex) {
            Logger.getLogger(MainAgendasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        paneContacts.getChildren().clear();
        paneContacts.getChildren().add(paneListContatos);
        
        
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(paneContacts);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        listContatosControl.setInfoPhoneLabel();
        
    }
    
    class AgendamentoFX extends RecursiveTreeObject<AgendamentoFX> {
        
        StringProperty IDAgendamento;
        StringProperty IDPaciente;
        StringProperty Horario;
        StringProperty nomePaciente;
        StringProperty Status;
        StringProperty Telefone;

        
        public AgendamentoFX(String IDAgenda, String IDPaciente,String Horario, String nome, String Status, String Telefone){
            this.IDAgendamento = new SimpleStringProperty(IDAgenda);
            this.IDPaciente = new SimpleStringProperty(IDPaciente);
            this.Horario = new SimpleStringProperty(Horario);
            this.nomePaciente = new SimpleStringProperty(nome);
            this.Status = new SimpleStringProperty(Status);
            this.Telefone = new SimpleStringProperty(Telefone);
        }
    }
    
}
