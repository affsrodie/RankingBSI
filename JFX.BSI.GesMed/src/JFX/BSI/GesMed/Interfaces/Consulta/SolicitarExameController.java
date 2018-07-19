/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Consulta;
import JFX.BSI.GesMed.Entidades.Exame;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Repositorios.AgendamentoRepositorio;
import JFX.BSI.GesMed.Repositorios.ConsultaRepositorio;
import JFX.BSI.GesMed.Repositorios.ExamesRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class SolicitarExameController implements Initializable {
    
    @FXML
    private Label infoNomePaciente;

    @FXML
    private JFXTextField tfdNomeExame;

    @FXML
    private JFXTextArea textObservacoes;

    @FXML
    private JFXDatePicker tfdDataExame;

    @FXML
    private JFXTimePicker tfdHoraExame;

    @FXML
    private JFXButton btnSolicitarExame;

    @FXML
    private JFXButton btnCancelar;
    
    
    private Paciente paciente;
    private Agendamento agenda;
    private Consulta consulta;

    public SolicitarExameController() {
    }
    
    public SolicitarExameController(Agendamento agenda) {
    this.agenda = agenda;
    }
    
    public SolicitarExameController(Agendamento agenda, Consulta consulta) {
    this.agenda = agenda;
    this.consulta = consulta;
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoNomePaciente.setText(agenda.getPaciente().getNome());
    }    
    
    
    @FXML
    public void SolicitarExame(MouseEvent event){
        ValidationFields validCampos = new ValidationFields();
        Exame ex = new Exame();
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        ExamesRepositorio exRep = new ExamesRepositorio();
        boolean CamposValidos = validCampos.checkEmptyFields(tfdNomeExame,textObservacoes);
        if(tfdDataExame.getValue()==null || tfdHoraExame.getValue()==null){
            CamposValidos = false;
        }
        if(CamposValidos){
//        ex.setConsulta(consulta);
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoTime = new SimpleDateFormat("hh:mm");
        
        ex.setIDExame(exRep.gerarID());
        ex.setQueixaPrincipal(tfdNomeExame.getText());
        ex.setObservacoes(textObservacoes.getText());
        try {
            ex.setDataExame(formatoData.parse(tfdDataExame.getValue().toString()));
            ex.setHoraExame(formatoTime.parse(tfdHoraExame.getValue().toString()));
        } catch (ParseException ex1) {
            Logger.getLogger(SolicitarExameController.class.getName()).log(Level.SEVERE, null, ex1);
        }
        
        LimparCampos();
         
        consulta.addExame(ex);
        ex.setConsulta(consulta);
 
        exRep.adicionar(ex);
        
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Solicitação de Exame");
            AlertErro.setHeaderText("Solicitação de Exame");
            AlertErro.setContentText("Exame foi solicitado com sucesso");
            AlertErro.showAndWait();
        
        }
        
    }
    
    public void LimparCampos(){
        
        tfdNomeExame.setText(null);
        textObservacoes.setText(null);
        tfdDataExame.setValue(null);
        tfdHoraExame.setValue(null);
        
    }
    
}
