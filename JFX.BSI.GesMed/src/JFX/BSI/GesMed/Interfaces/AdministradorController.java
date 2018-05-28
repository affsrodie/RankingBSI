/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class AdministradorController implements Initializable {
    
    @FXML
    private StackPane rootStackPane;
    
    @FXML
    private JFXPasswordField tfdSenhaAdmin;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btn_Cancelar;
    
    private String SENHA_ADIM="@BSI.GESMED.2018";
    
    private LoginController Pai;
    
    public AdministradorController (){
        
    }
    
    public AdministradorController (LoginController Control){
        Pai = Control;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void Confirmar(MouseEvent event){
        ValidationFields validSenha = new ValidationFields();
        if(validSenha.checkEmptyFields(tfdSenhaAdmin)){
            if(tfdSenhaAdmin.getText().equals(SENHA_ADIM)){
             Pai.openRegistrarAtendente();
             CloseWindows();
            }else{
                Alert AlertErro = new Alert(Alert.AlertType.ERROR);
                AlertErro.setTitle("Senha do Administrador");
                AlertErro.setHeaderText("Autenticação do Administraor");
                AlertErro.setContentText("Senha do Administrador está incorreta!");
                AlertErro.showAndWait();  
            }
        }else{
                Alert AlertErro = new Alert(Alert.AlertType.ERROR);
                AlertErro.setTitle("Senha do Administrador");
                AlertErro.setHeaderText("Campos de Autenticação");
                AlertErro.setContentText("Entre com uma senha de Administrador");
                AlertErro.showAndWait();  
        }
    }
    
    @FXML
    public void CloseWindows(){
        Stage currentStage = (Stage) rootStackPane.getScene().getWindow();
        currentStage.hide();
    }
    
}
