/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Entidades.Telefone;
import JFX.BSI.GesMed.Repositorios.AgendamentoRepositorio;
import JFX.BSI.GesMed.Repositorios.PacienteRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class ListContactsController implements Initializable {
    
    
    @FXML
    private AnchorPane paneLateral, popUpMenu, paneContactsEdit, paneContactsNew;

 

    @FXML
    private Label infoPhonePessoal;

    @FXML
    private Label infoPhoneRecado;

    @FXML
    private Label infoPhoneFixo;

    @FXML
    private Label infoPhoneTrabalho;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXComboBox<String> cbxTipoContato;

    @FXML
    private JFXTextField tfdTelefone;

    @FXML
    private JFXComboBox<String> cbxStatus;
    
    private ObservableList<String> listStatus = FXCollections.observableArrayList("Selecione","Agendado","Confirmado","Chegou","Em Andamento","Finalizado","Cancelado","Faltou");
    private ObservableList<String> listCelular = FXCollections.observableArrayList("Selecione","Celular","Telefone","Fixo","Trabalho");
    
    private MainAgendasController agenControl;
    
    private Paciente paciente;
    private List<Telefone> listTelefone;
    
    private Agendamento agenda;
    
    private AgendamentoRepositorio agenRep;
    private PacienteRepositorio pacRep;

    public ListContactsController() {
    
    }
    
    public ListContactsController(Paciente paciente) {
        this.paciente= paciente;
        
    }
    
    public ListContactsController(MainAgendasController Pai, Paciente paciente, Agendamento agenda) {
        agenControl = Pai;
        this.paciente= paciente;
        this.agenda = agenda;
        agenRep = new AgendamentoRepositorio();
        pacRep = new PacienteRepositorio();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cbxStatus.setItems(listStatus);
       cbxTipoContato.setItems(listCelular);
       cbxStatus.setValue(agenda.getStatus());
    }    
    
    public void setInfoPhoneLabel(){
        if(paciente!=null){
            
                if(paciente.getTelefone().getCelular()!=null){
                    infoPhonePessoal.setText(paciente.getTelefone().getCelular());
                }
              
                if(paciente.getTelefone().getTelefone()!=null){
                    infoPhoneRecado.setText(paciente.getTelefone().getTelefone());
                }
                
                if(paciente.getTelefone().getFixo()!=null){
                    infoPhoneFixo.setText(paciente.getTelefone().getFixo());
                }
                
                if(paciente.getTelefone().getTrabalho()!=null){
                    infoPhoneTrabalho.setText(paciente.getTelefone().getTrabalho());
                }
        }
    }
    
    @FXML
    public void Salvar(){
        if(ValidationFields.checkEmptyFields(tfdTelefone, cbxStatus,cbxStatus)){
           String Status = cbxStatus.getSelectionModel().getSelectedItem();
        String Telefone=cbxTipoContato.getSelectionModel().getSelectedItem();
        if(cbxTipoContato.getSelectionModel().getSelectedItem().equals("Celular")){
            paciente.getTelefone().setCelular(Telefone);
        }
        if(cbxTipoContato.getSelectionModel().getSelectedItem().equals("Telefone")){
            paciente.getTelefone().setCelular(Telefone);
        }
        if(cbxTipoContato.getSelectionModel().getSelectedItem().equals("Fixo")){
            paciente.getTelefone().setCelular(Telefone);
        }
        if(cbxTipoContato.getSelectionModel().getSelectedItem().equals("Trabalho")){
            paciente.getTelefone().setCelular(Telefone);
        }
        agenda.setStatus(Status);
        
        agenRep.atualizar(agenda);
        pacRep.atualizar(paciente);
        
        agenControl.CarregarTabelaAgendamento();
        agenControl.openListTelefone(); 
        }
        
    }
    
    
    @FXML
    public void tfdCelularKeyRelased(){
        JFX.BSI.GesMed.Interfaces.Paciente.TextFieldFormatter tff = new JFX.BSI.GesMed.Interfaces.Paciente.TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdTelefone);
        tff.formatter();
    }
    
    @FXML
    public void cancelarJanela(){
        agenControl.openListTelefone();
    }
    
}
