/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces;

import JFX.BSI.GesMed.Entidades.Atendente;
import JFX.BSI.GesMed.Entidades.Medico;
import JFX.BSI.GesMed.Repositorios.AtendenteRepositorio;
import JFX.BSI.GesMed.Repositorios.MedicoRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class LoginController implements Initializable {
    
    //SESSÃO PARA LOGIN
    @FXML
    private StackPane stackLogin;
    @FXML
    private StackPane rootLogin;
    
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
    private JFXButton btnRegistrar;

    @FXML
    private ImageView imgProgress;
    
    @FXML
    private JFXComboBox cbxTipoUser;
    private ObservableList<String> listTipoUser = FXCollections.observableArrayList("MÉDICO", "ATENDENTE");
    
    private Medico medicoLogin;
    private Atendente atendenteLogin;
    
    private int QUANT_ATEND=-1;
    private int QUANT_MED=-1;
    
    //SESSÃO PARA TELA PRINCIPAL
    
    
    @FXML
    private StackPane rootPrincipal, rootRegistrar;
    
    @FXML
    private AnchorPane rootGesMed, popUpMenu;
    
    @FXML
    private FontAwesomeIconView iconUserLogin;
    
    @FXML
    private FontAwesomeIconView iconTipoLogin;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializeLogin();
        CheckQuantidadeUsuarios();
    } 
    
    public void inicializeLogin(){
       
        cbxTipoUser.setItems(listTipoUser);
        cbxTipoUser.setValue("ATENDENTE");
        cbxTipoUser.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               
                if(cbxTipoUser.getSelectionModel().getSelectedItem().equals("MÉDICO")){
                  iconTipoLogin.setGlyphName("USER_MD");
                }else if(cbxTipoUser.getSelectionModel().getSelectedItem().equals("ATENDENTE")){
                  iconTipoLogin.setGlyphName("USER");
                }
                
            }
        });
        
        RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("O Campo Login é necessário");
        fieldValidator.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        tfdLoginUser.getValidators().add(fieldValidator);
        tfdLoginUser.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                tfdLoginUser.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("E necessário da senha para entrar");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        tfdSenhaUser.getValidators().add(fieldValidator2);
        tfdSenhaUser.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                tfdSenhaUser.validate();
            }
        });  
    }
   
    @FXML
    public void ValidaLogin(ActionEvent event){
        if(cbxTipoUser.getSelectionModel().getSelectedItem().equals("MÉDICO")&&!tfdLoginUser.getText().trim().equals("")){
            
                    if(validaMedico()){  
                        completeLoginMedico();

                    }else{
                        imgProgress.setVisible(false); 
                    } 
                
        }else if(cbxTipoUser.getSelectionModel().getSelectedItem().equals("ATENDENTE")&&!tfdLoginUser.getText().trim().equals("")){
           
                    if(validaAtendente()){
                     completeLoginAtendente();
                    }else{
                     imgProgress.setVisible(false); 
                    } 
                
            }else{
                imgProgress.setVisible(false); 
                Alert AlertErro = new Alert(Alert.AlertType.ERROR);
                AlertErro.setTitle("Confirmação de Login");
                AlertErro.setHeaderText("Autenticação de Login");
                AlertErro.setContentText("Os campo de Login está em branco");
                AlertErro.showAndWait();  
            }
    }
    
    public boolean validaMedico(){
        boolean Valido = false;
        MedicoRepositorio medRep = new MedicoRepositorio();
       
        
        medicoLogin = medRep.recuperarMedicoCPF(tfdLoginUser.getText().trim());
        
        if(medicoLogin!=null){
           if(this.medicoLogin.getSenha()
                   .equals(tfdSenhaUser.getText())){
               Valido = true;
           }else{
                Valido = false;
                Alert AlertErro = new Alert(Alert.AlertType.ERROR);
                AlertErro.setTitle("Confirmação de Login");
                AlertErro.setHeaderText("Médico: Autenticação de Login");
                AlertErro.setContentText("Senha incorreta para este usuário");
                AlertErro.showAndWait();
           }
           
        }else{
                imgProgress.setVisible(false);
                Valido = false;
                Alert AlertErro = new Alert(Alert.AlertType.ERROR);
                AlertErro.setTitle("Confirmação de Login");
                AlertErro.setHeaderText("Médico: Autenticação de Login");
                AlertErro.setContentText("Não foi encontrado nenhum Usuário com este CPF");
                AlertErro.showAndWait();
        }
        return Valido;
    }
    
 public boolean validaAtendente(){
        boolean Valido = false;
        AtendenteRepositorio atenRep = new AtendenteRepositorio();
        
        atendenteLogin = atenRep.recuperarAtendenteCPF(tfdLoginUser.getText().trim());
        if(atendenteLogin!=null){
                if(this.atendenteLogin.getSenha().equals(tfdSenhaUser.getText())){
                    Valido = true;
                }else{
                     Valido = false;
                     Alert AlertErro = new Alert(Alert.AlertType.ERROR);
                     AlertErro.setTitle("Confirmação de Login");
                     AlertErro.setHeaderText("Atendente: Falha na Autenticação de Login");
                     AlertErro.setContentText("Senha incorreta para este usuário");
                     AlertErro.showAndWait();
                }
        }else{
                imgProgress.setVisible(false);
                Valido = false;
                Alert AlertErro = new Alert(Alert.AlertType.ERROR);
                AlertErro.setTitle("Confirmação de Login");
                AlertErro.setHeaderText("Atendente: Falha na Autenticação de Login");
                AlertErro.setContentText("Não foi encontrado nenhum Usuário com este CPF");
                AlertErro.showAndWait();
        }
        return Valido;
    }
 
    
    
    public void completeLoginAtendente() {
        
        try {
        paneVBox.getChildren().clear();
        paneVBox.setDisable(true);
        FXMLLoader sceneMainPrincipal = new FXMLLoader(MainAtendenteController.class.getResource("/JFX/BSI/GesMed/Interfaces/MainAtendente.fxml"));
        MainAtendenteController mainPrincipal = new MainAtendenteController(this.atendenteLogin);
        sceneMainPrincipal.setController(mainPrincipal);
        rootPrincipal = sceneMainPrincipal.load();
        
        paneLogin.getChildren().clear();
        paneLogin.getChildren().add(rootPrincipal);
        
        paneLogin.setPrefWidth(1100);
        paneLogin.setPrefHeight(600);
        paneLogin.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(rootPrincipal);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        mainPrincipal.setRipples();
        mainPrincipal.loadUserAtendente();
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void completeLoginMedico() {
        
        try {
        paneVBox.getChildren().clear();
        paneVBox.setDisable(true);
        FXMLLoader sceneMainPrincipal = new FXMLLoader(MainMedicoController.class.getResource("/JFX/BSI/GesMed/Interfaces/MainMedico.fxml"));
        MainMedicoController mainPrincipal = new MainMedicoController(this.medicoLogin);
        sceneMainPrincipal.setController(mainPrincipal);
        rootPrincipal = sceneMainPrincipal.load();
        
        paneLogin.getChildren().clear();
        paneLogin.getChildren().add(rootPrincipal);
        
        paneLogin.setPrefWidth(1100);
        paneLogin.setPrefHeight(600);
        paneLogin.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(rootPrincipal);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        mainPrincipal.setRipples();
        mainPrincipal.loadUserMedico();
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

        
    public void openRegistrarAtendente() {
        
        try {
        
        FXMLLoader registrarFXML = new FXMLLoader(LoginController.class.getResource("/JFX/BSI/GesMed/Interfaces/RegistrarAtendente.fxml"));
        RegistrarAtendenteController registrarAtenControl = new RegistrarAtendenteController(this);
        registrarFXML.setController(registrarAtenControl);
        rootRegistrar = registrarFXML.load();
        
        paneLogin.getChildren().clear();
        paneLogin.getChildren().add(rootRegistrar);
        
        paneLogin.setPrefWidth(1100);
        paneLogin.setPrefHeight(600);
        paneLogin.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(rootRegistrar);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void returnLogin(Atendente atendente) {
        Atendente atendTemp = atendente; 
        try {
        
        FXMLLoader registrarFXML = new FXMLLoader(LoginController.class.getResource("/JFX/BSI/GesMed/Interfaces/Login.fxml"));
        LoginController loginController = this;
        rootRegistrar = registrarFXML.load();
        
        paneLogin.getChildren().clear();
        paneLogin.getChildren().add(rootRegistrar);
        
        paneLogin.setPrefWidth(1100);
        paneLogin.setPrefHeight(600);
        paneLogin.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(rootRegistrar);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        atendenteLogin=atendTemp;
        tfdLoginUser.setText(atendenteLogin.getCPF());
    }
    
    public void cancelarRegistro() {
        
        try {
        
        FXMLLoader registrarFXML = new FXMLLoader(LoginController.class.getResource("/JFX/BSI/GesMed/Interfaces/Login.fxml"));
        
        
        rootRegistrar = registrarFXML.load();
        
        paneLogin.getChildren().clear();
        paneLogin.getChildren().add(rootRegistrar);
        
        paneLogin.setPrefWidth(1100);
        paneLogin.setPrefHeight(600);
        paneLogin.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(rootRegistrar);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void inicializeTelaLogin() {
        
        try {
        FXMLLoader sceneLogin = new FXMLLoader(MainAtendenteController.class.getResource("/JFX/BSI/GesMed/Interfaces/Login.fxml"));
        
        paneLogin = sceneLogin.load();
        
        rootPrincipal.getChildren().clear();
        rootPrincipal.getChildren().add(paneLogin);
        
        inicializeLogin();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 
    @FXML
    private void openAdministrador() {
        try {
        Stage currentStage = (Stage) stackLogin.getScene().getWindow();
        Stage stage = new Stage();
        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader adminSenhaFXML = new FXMLLoader(LoginController.class.getResource("/JFX/BSI/GesMed/Interfaces/Administrador.fxml"));
        AdministradorController adminControl = new AdministradorController(this);
        adminSenhaFXML.setController(adminControl);
        
        Parent root = adminSenhaFXML.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        } catch (IOException ex) {
            Logger.getLogger(MainAtendenteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException npe){
        Stage stage = new Stage();
        
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        
        FXMLLoader adminSenhaFXML = new FXMLLoader(LoginController.class.getResource("/JFX/BSI/GesMed/Interfaces/Administrador.fxml"));
        AdministradorController adminControl = new AdministradorController(this);
        adminSenhaFXML.setController(adminControl);
        
        Parent root = null;
            try {
                root = adminSenhaFXML.load();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        }
    }
    
    public void CheckQuantidadeUsuarios(){
        AtendenteRepositorio atendRep = new AtendenteRepositorio();
        MedicoRepositorio medRep = new MedicoRepositorio();
        QUANT_ATEND = atendRep.getQuantAtendentes();
        QUANT_MED = medRep.getQuantMedicos();
        int SOMA = 0;
        if(QUANT_ATEND!=-1&&QUANT_MED!=-1){
            SOMA = QUANT_ATEND+QUANT_MED;
        }

    }
    
    @FXML
    public void tfdCPFKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdLoginUser);
        tff.formatter();
    }
    
}
