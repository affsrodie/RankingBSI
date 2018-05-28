/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Consulta;
import JFX.BSI.GesMed.Entidades.Paciente;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class ConsultaPacienteController implements Initializable {
    private ConsultaPacienteController consPacControl;
    private ConsultaMedicaController consMedControl;
    private SolicitarExameController solExamControl;
    private ReceitaMedicaController recMedControl;
    
    @FXML
    private StackPane rootStackPane, rootStackTansition;
    
    @FXML
    private AnchorPane paneCambioWindows;
    
    @FXML
    private AnchorPane ConsultaMedica, SolicitarExame, ReceitaMedica;
    
    @FXML
    private JFXButton btnConsultaMedica;

    @FXML
    private JFXButton btnReceitaMedica;

    @FXML
    private JFXButton btnGerarAtestado;

    @FXML
    private JFXButton btnSolicitarExame;
    
    @FXML
    private JFXButton btnSair;
    
    private Paciente paciente;
    private Agendamento agenda;
    private Consulta consulta;

    public ConsultaPacienteController() {
        
    }
    
    
    public ConsultaPacienteController(Agendamento agenda){
        this.agenda = agenda;
    }
    
    public ConsultaPacienteController(Agendamento agenda, Consulta consulta, ConsultaPacienteController control){
        this.agenda = agenda;
        this.consulta = consulta;
        this.consPacControl=control;
    }
    
    @FXML
    public void CloseWindows(){
        Stage currentStage = (Stage) rootStackPane.getScene().getWindow();
        currentStage.hide();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openConsultaMedica();
        btnSolicitarExame.setDisable(true);
    }
    
    @FXML
    private void openConsultaMedica(){
        if(consMedControl==null){
            String pathFXML = "/JFX/BSI/GesMed/Interfaces/Agenda/ConsultaMedica.fxml";

            FXMLLoader consultaMed = new FXMLLoader(ConsultaMedicaController.class.getResource(pathFXML));    

            consMedControl = new ConsultaMedicaController(this.agenda, this);

            consultaMed.setController(consMedControl);
            try {
                ConsultaMedica = consultaMed.load();
                setNode(ConsultaMedica);
            } catch (IOException ex) {
                Logger.getLogger(ConsultaPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        else{
            setNode(ConsultaMedica);
        }
    }
 
    
    
    @FXML
    private void openConsultaMedica(MouseEvent event){
        if(consMedControl==null){
            String pathFXML = "/JFX/BSI/GesMed/Interfaces/Agenda/ConsultaMedica.fxml";

            FXMLLoader consultaMed = new FXMLLoader(ConsultaMedicaController.class.getResource(pathFXML));    

            consMedControl = new ConsultaMedicaController(this.agenda,this);

            consultaMed.setController(consMedControl);
            try {
                ConsultaMedica = consultaMed.load();
                setNode(ConsultaMedica);
            } catch (IOException ex) {
                Logger.getLogger(ConsultaPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        else{
            setNode(ConsultaMedica);
        }
    }
    
    
    @FXML
    private void openSolicitarExame(MouseEvent event){
        if(solExamControl==null){
            String pathFXML = "/JFX/BSI/GesMed/Interfaces/Agenda/SolicitarExame.fxml";

            FXMLLoader consultaMed = new FXMLLoader(ConsultaMedicaController.class.getResource(pathFXML));    

            solExamControl = new SolicitarExameController(this.agenda, this.consulta);

            consultaMed.setController(solExamControl);
            try {
                SolicitarExame = consultaMed.load();
                setNode(SolicitarExame);
            } catch (IOException ex) {
                Logger.getLogger(ConsultaPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        else{
            setNode(SolicitarExame);
        }
    }
    
    

    
    public void EnnableButtonSolicitar(Agendamento agenda, Consulta consulta){
        this.agenda = agenda;
        this.consulta = consulta;
        
         
//            String pathFXML = "/JFX/BSI/GesMed/Interfaces/Agenda/ConsultaPaciente.fxml";
//
//            FXMLLoader consultaMed = new FXMLLoader(ConsultaMedicaController.class.getResource(pathFXML));    
//
//            
//            consultaMed.setController(consPacControl);
//            
//            try {
//                
//                rootStackPane=consultaMed.load();
//                rootStackPane.setPrefSize(1100, 650);
//                rootStackPane.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
//                
//            } catch (IOException ex) {
//                Logger.getLogger(ConsultaPacienteController.class.getName()).log(Level.SEVERE, null, ex);
//            }  
        btnSolicitarExame.setDisable(false);
    }
   
    @FXML
    private void openReceitaMedica(MouseEvent event){
        if(recMedControl==null){
            String pathFXML = "/JFX/BSI/GesMed/Interfaces/Agenda/ReceitaMedica.fxml";

            FXMLLoader consultaMed = new FXMLLoader(ReceitaMedicaController.class.getResource(pathFXML));    

            recMedControl = new ReceitaMedicaController(this.agenda);

            consultaMed.setController(recMedControl);
            try {
                ReceitaMedica = consultaMed.load();
                setNode(ReceitaMedica);
            } catch (IOException ex) {
                Logger.getLogger(ConsultaPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        else{
            setNode(ReceitaMedica);
        }
    }
 

    private void setNode(Node node) {
        
        paneCambioWindows.getChildren().clear();
        paneCambioWindows.getChildren().add((Node) node);
        paneCambioWindows.setPrefSize(900, 550);
        paneCambioWindows.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
       
        FadeTransition ft = new FadeTransition(Duration.millis(500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
    }
    
}
