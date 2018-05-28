/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Paciente;

import JFX.BSI.GesMed.Entidades.Paciente;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class HomePacienteController implements Initializable {
        
    private AnchorPane Cadastro, ViewPacientes, Home;
    
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane paneRootPreview;

 
    
    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnPacientes;

    @FXML
    private JFXButton btnCadastrar;

    @FXML
    private AnchorPane holderPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createPages();
    }    
    
    private void createPages() {
        try {
            Home = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Paciente/VisualizarPacientes.fxml"));
            Cadastro = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Paciente/CadastrarPaciente.fxml"));
            ViewPacientes = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Paciente/GerenciarPacientes.fxml"));

            //set up default node on page load
            setNode(Home);
        } catch (IOException ex) {
            Logger.getLogger(HomePacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        holderPane.setPrefSize(1100, 550);
        holderPane.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
    }
    
    @FXML
    private void openHome(ActionEvent event) {
        setNode(Home);
    }
    
    @FXML
    private void openCadastro(ActionEvent event) {
        setNode(Cadastro);
    }
    
    
    @FXML
    private void openViewPacientes(ActionEvent event) {
        setNode(ViewPacientes);
    }
    
    
    
    void openViewPacientes() {
        setNode(ViewPacientes);
    }
    
    
}
