/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces;

import JFX.BSI.GesMed.Entidades.Medico;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class MainMedicoController implements Initializable {
    
    //SESSÃO PARA LOGIN
    @FXML
    private StackPane paneLogin;
    
    @FXML
    private VBox paneVBox;
    
    @FXML
    private VBox telaPrincipal;

    @FXML
    private JFXTextField tfdLoginUser;

    @FXML
    private JFXPasswordField tfdSenhaUser;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private ImageView imgProgress;
    

    
    //SESSÃO PARA TELA PRINCIPAL
    
    
    @FXML
    private StackPane rootPrincipal;
    
    @FXML
    private AnchorPane rootGesMed, popUpMenu;
    
    @FXML
    private FontAwesomeIconView iconUserLogin;
    
    @FXML
    private FontAwesomeIconView iconTipoLogin;
    
    @FXML
    private Label infoUsuario;
    
    @FXML
    private HBox groupOptions;

    @FXML
    private Group groupAgendamento;
    
    @FXML
    private Group groupPerfil;

    
    @FXML
    private JFXButton btn_Menu;
    
    JFXPopup fXPopup;
    
    private static JFXPopup staticJFXPopup;
    
    private Medico medicoUser;
 
    public MainMedicoController(){
        
    }
    
    public MainMedicoController(Medico medico){
        medicoUser = medico;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    } 
    
    public void loadUserMedico(){
        infoUsuario.setText(this.medicoUser.getNome());
        iconUserLogin.setGlyphName("USER_MD");
    }
    
  
    public void setRipples() {
        JFXRippler rippler1 = new JFXRippler(groupPerfil);
        JFXRippler rippler2 = new JFXRippler(groupAgendamento);
        rippler1.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler2.setMaskType(JFXRippler.RipplerMask.RECT);


        rippler1.setRipplerFill(Paint.valueOf("#1564C0"));
        rippler2.setRipplerFill(Paint.valueOf("#00AACF"));

        groupOptions.getChildren().addAll(rippler1, rippler2);
    }
    
    @FXML
    private void openDialog(MouseEvent event) {
        popUpMenu.setDisable(false);
        popUpMenu.setVisible(true);
        fXPopup = new JFXPopup();
        fXPopup.setPopupContent(popUpMenu);
        fXPopup.show(rootGesMed, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 10, 10);
        staticJFXPopup = fXPopup;
    }
    

    
    @FXML
    private void openListaEspera(MouseEvent event) throws IOException, Exception {
         openStage("/JFX/BSI/GesMed/Interfaces/Consulta/ListaEspera.fxml");
    }
    
    @FXML
    private void openPerfil(MouseEvent event) throws IOException, Exception {
//         openStage("/JFX/BSI/GesMed/Interfaces/Paciente/HomePaciente.fxml");
    }
   
        
    @FXML
    private void hideStage(MouseEvent event) {
         Stage currentStage = (Stage) rootPrincipal.getScene().getWindow();
         currentStage.hide();
    }
    

    private void openStage(String fxml) {
        try {
        Stage currentStage = (Stage) rootPrincipal.getScene().getWindow();
        Stage stage = new Stage();
        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.DECORATED);
        stage.initModality(Modality.NONE);
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        currentStage.hide();

        } catch (IOException ex) {
            Logger.getLogger(MainAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void EncerrarSessao(MouseEvent event) {
        
        try {
        rootPrincipal.getChildren().clear();
        FXMLLoader sceneMainPrincipal = new FXMLLoader(MainAtendenteController.class.getResource("/JFX/BSI/GesMed/Interfaces/Login.fxml"));
        
        paneLogin = sceneMainPrincipal.load();
        
        rootPrincipal.getChildren().add(paneLogin);
        
        rootPrincipal.setPrefWidth(1100);
        rootPrincipal.setPrefHeight(600);
        rootPrincipal.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(paneLogin);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        popUpMenu.setVisible(false);
        fXPopup.hide();
    }
    
}
