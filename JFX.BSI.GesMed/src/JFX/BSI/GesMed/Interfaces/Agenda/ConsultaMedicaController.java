/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Consulta;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Repositorios.AgendamentoRepositorio;
import JFX.BSI.GesMed.Repositorios.ConsultaRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class ConsultaMedicaController implements Initializable {
    
    @FXML
    private AnchorPane paneRoot;
    
    @FXML
    private Label infoNomePaciente;
    
    @FXML
    private JFXTextField tfdNomeConsulta;

    @FXML
    private JFXTextArea textObservacoes;

    @FXML
    private JFXButton btnSalvarConsulta;

    @FXML
    private JFXButton btnLimpar;

    @FXML
    private JFXButton btnCancelar;
    
    private Paciente paciente;
    private Agendamento agenda;
    private Consulta consulta;
    private ConsultaPacienteController consPacControl;
    
    public ConsultaMedicaController() {
    }
    
    public ConsultaMedicaController(Agendamento agenda) {
        this.agenda = agenda;
    }
    
    public ConsultaMedicaController(Agendamento agenda, ConsultaPacienteController conPaciControl) {
        this.agenda = agenda;
        this.consPacControl= conPaciControl;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoNomePaciente.setText(agenda.getPaciente().getNome());
    }
    
    public boolean ValidarCampos(){
        ValidationFields validTextField = new ValidationFields();
        return validTextField.checkEmptyFields(tfdNomeConsulta,textObservacoes);
    }
    
    @FXML
    public void limparCampos(){
        tfdNomeConsulta.setText(null);
        textObservacoes.setText(null);
    }
    
    @FXML
    public void CloseWindows(){
        Stage currentStage = (Stage) paneRoot.getScene().getWindow();
        currentStage.hide();
    }
    
    @FXML
    public void salvarConsulta(MouseEvent event){
        if(ValidarCampos()){
        Consulta consulta = new Consulta();
        ConsultaRepositorio consRep = new ConsultaRepositorio();
        consulta.setNomeConsulta(tfdNomeConsulta.getText());
        consulta.setObservacoes(textObservacoes.getText());
        
        agenda.addConsulta(consulta);
        agenda.setStatus("Finalizado");
        this.consulta = consulta;
        
        this.consulta.setAgendamento(agenda);
        
        consRep.atualizar(this.consulta);
        consRep.encerrar();
        
        DisableComponentes();
        EnnbleSolicitarExame();
//        cRep.adicionar(consulta);
//        cRep.encerrar(); 
        }
    }
    
    public void DisableComponentes(){
        tfdNomeConsulta.setDisable(true);
        textObservacoes.setDisable(true);
        btnSalvarConsulta.setDisable(true);
        btnCancelar.setDisable(false);
        btnLimpar.setDisable(true);
    }
    
    public void EnnbleSolicitarExame(){
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        ConsultaRepositorio consRep = new ConsultaRepositorio();
        agenda = agenRep.recuperar(agenda.getIDAgenda());
        agenRep.encerrar();
        List<Consulta> listConsultas = consRep.recuperarTodos();
        for(Consulta consulta : listConsultas){
        if(this.consulta.getNomeConsulta().equals(consulta.getNomeConsulta())
                &&this.consulta.getObservacoes().equals(consulta.getObservacoes())){
            this.consulta = consulta;
            }
        }
        
        consPacControl.EnnableButtonSolicitar(agenda, consulta);
    }
    
}
