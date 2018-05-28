/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Usuarios;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class HomeUsuariosController implements Initializable {
        
    private AnchorPane CadastroMedico, CadastroAtendente, GerenciarMedico, GerenciarAtendente, Home;
    
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane sideAnchor;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnGerenciarMed, btnCadastrarMedico, btnGerenciarAtendente, btnCadastrarAtendente;

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
            Home = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/InfoUsers.fxml"));
            CadastroMedico = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/CadastrarMedico.fxml"));
            GerenciarMedico = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/GerenciarMedico.fxml"));
            CadastroAtendente = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/CadastrarAtendente.fxml"));
            GerenciarAtendente = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/GerenciarAtendente.fxml"));
            //set up default node on page load
            setNode(Home);
        } catch (IOException ex) {
            Logger.getLogger(HomeUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

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
    private void openCadastroMedico(ActionEvent event) {
        setNode(CadastroMedico);
    }
    
    
    @FXML
    private void openGerenciarMedico(ActionEvent event) {
        setNode(GerenciarMedico);
    }
    
    @FXML
    private void openCadastrarAtendente(ActionEvent event) {
        setNode(CadastroAtendente);
    }
    
    @FXML
    private void openGerenciarAtendente(ActionEvent event) {
        setNode(GerenciarAtendente);
    }
    
    
}
