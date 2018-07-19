/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces;

import JFX.BSI.GesMed.Interfaces.Contas.LancarContaController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class TelaBoasVindasController implements Initializable {
    
    @FXML
    private StackPane rootStackPane;
    FXMLLoader LoginFXML = new FXMLLoader(LoginController.class.getResource("/JFX/BSI/GesMed/Interfaces/Login.fxml"));
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    
    
    public void openStage() {
        try {
        Stage stage = new Stage();
//        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.DECORATED);
        stage.initModality(Modality.NONE);
       
        System.err.println("Passou por aqui - Antes de Carregar os Dados");
        Parent root = LoginFXML.load();
         System.err.println("Passou por aqui - Depois de Carregar os Dados");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        CloseWindows();
//        currentStage.hide();

        } catch (IOException ex) {
            Logger.getLogger(MainAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void CloseWindows(){
        Stage currentStage = (Stage) rootStackPane.getScene().getWindow();
        currentStage.hide();
    }
    
}
