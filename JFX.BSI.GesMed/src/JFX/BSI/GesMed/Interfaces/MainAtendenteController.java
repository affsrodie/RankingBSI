/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces;

import JFX.BSI.GesMed.Entidades.Atendente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class MainAtendenteController implements Initializable {
    
    //SESSÃO PARA TELA PRINCIPAL
    
    
    @FXML
    private StackPane rootPrincipal;
    
    @FXML
    private StackPane paneLogin;
    
    @FXML
    private AnchorPane rootGesMed, popUpMenu;
    
    @FXML
    private FontAwesomeIconView iconUserLogin;

    @FXML
    private Label infoUsuario;
    
    @FXML
    private HBox groupOptions;

    @FXML
    private Group groupPacientes;

    @FXML
    private Group groupAgenda;

    @FXML
    private Group groupContas;

    @FXML
    private Group groupCaixa;
    
    @FXML
    private JFXButton btn_Menu;
    
    @FXML
    private JFXButton btnLogoff;
    
    JFXPopup fXPopup;
    
    private static JFXPopup staticJFXPopup;
    
    private Atendente atendenteLogin;
    

    
    public MainAtendenteController(){
        
    }
    
    public MainAtendenteController(Atendente atendente){
        this.atendenteLogin=atendente;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    } 
    

    public void loadUserAtendente(){
        infoUsuario.setText(atendenteLogin.getNome());
        iconUserLogin.setGlyphName("USER");
    }
  
    public void setRipples() {
        JFXRippler rippler1 = new JFXRippler(groupPacientes);
        JFXRippler rippler2 = new JFXRippler(groupAgenda);
        JFXRippler rippler3 = new JFXRippler(groupContas);
        JFXRippler rippler4 = new JFXRippler(groupCaixa);
        rippler1.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler2.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler3.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler4.setMaskType(JFXRippler.RipplerMask.RECT);

        rippler1.setRipplerFill(Paint.valueOf("#1564C0"));
        rippler2.setRipplerFill(Paint.valueOf("#00AACF"));
        rippler3.setRipplerFill(Paint.valueOf("#00B3A0"));
        rippler4.setRipplerFill(Paint.valueOf("#F87951"));

        groupOptions.getChildren().addAll(rippler1, rippler2, rippler3, rippler4);
    }
    
    @FXML
    private void openDialog(MouseEvent event) {
        popUpMenu.setDisable(false);
        popUpMenu.setVisible(true);
        fXPopup = new JFXPopup();
        fXPopup.setPopupContent(popUpMenu);
        fXPopup.show(rootGesMed, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 10, 45);
        staticJFXPopup = fXPopup;
    }
    

    
    @FXML
    private void openAgenda(MouseEvent event) throws IOException, Exception {
         openStage("/JFX/BSI/GesMed/Interfaces/Agenda/MainAgendas.fxml");
    }
    
    @FXML
    private void openPacientes(MouseEvent event) throws IOException, Exception {
         openStage("/JFX/BSI/GesMed/Interfaces/Paciente/HomePaciente.fxml");
    }
    
    
    @FXML
    private void openUsuarios(MouseEvent event) throws IOException, Exception {
         openStage("/JFX/BSI/GesMed/Interfaces/Usuarios/HomeUsuarios.fxml");
    }
    
    @FXML
    private void openContas(MouseEvent event) throws IOException, Exception {
         openStage("/JFX/BSI/GesMed/Interfaces/Contas/MainContas.fxml");
    }
    
       
    @FXML
    private void openCaixa(MouseEvent event) throws IOException, Exception {
         openStage("/JFX/BSI/GesMed/Interfaces/Caixa/MainCaixa.fxml");
    }
        
    @FXML
    private void hideStage(MouseEvent event) {
        Platform.exit();
    }
    

    private void openStage(String fxml) {
        try {
        Stage currentStage = (Stage) rootPrincipal.getScene().getWindow();
        Stage stage = new Stage();
//        stage.initOwner(currentStage);
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
