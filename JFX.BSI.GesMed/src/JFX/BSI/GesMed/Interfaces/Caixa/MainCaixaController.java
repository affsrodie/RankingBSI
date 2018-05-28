/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Caixa;

import JFX.BSI.GesMed.Interfaces.Contas.MainContasController;
import JFX.BSI.GesMed.Interfaces.LoginController;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
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
public class MainCaixaController implements Initializable {

    @FXML
    private StackPane StackPaneRoot;
    
    @FXML
    private StackPane StackPaneRelatorio;
    
    @FXML
    private JFXButton btnJAN_IO;

    @FXML
    private JFXButton btnFEV_IO;

    @FXML
    private JFXButton btnMAR_IO;

    @FXML
    private JFXButton btnABR_IO;

    @FXML
    private JFXButton btnMAI_IO;

    @FXML
    private JFXButton btnJUN_IO;

    @FXML
    private JFXButton btnJUL_IO;

    @FXML
    private JFXButton btnAGO_IO;

    @FXML
    private JFXButton btnSET_IO;

    @FXML
    private JFXButton btnOUT_IO;

    @FXML
    private JFXButton btnNOV_IO;

    @FXML
    private JFXButton btnDEZ_IO;

    @FXML
    private DatePicker tfdDataIO;

    @FXML
    private JFXButton btnRelatorios;

    @FXML
    private JFXButton btn_NovaEntrada;

    @FXML
    private JFXButton btn_NovaSaida;

    @FXML
    private PieChart piePizzaES;

    @FXML
    private JFXButton btnJAN_I, btnFEV_I, btnMAR_I, btnABR_I, btnMAI_I, btnJUN_I, btnJUL_I, btnAGO_I, btnSET_I, btnOUT_I, btnNOV_I, btnDEZ_I;

    @FXML
    private JFXButton btnJAN_O, btnFEV_O, btnMAR_O, btnABR_O, btnMAI_O, btnJUN_O, btnJUL_O, btnAGO_O, btnSET_O, btnOUT_O, btnNOV_O, btnDEZ_O;
    
//    private ObservableList<PieChart.Data> detailsES = FXCollections.observableArrayList();
    
    private PieChart.Data pieEntrada;
    private PieChart.Data pieSaida;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    } 
    
    public void iniciarGraficoPizza(){
        pieEntrada = new PieChart.Data("Entrada", 75);
        pieSaida = new PieChart.Data("Saída", 25);
        piePizzaES.getData().addAll(pieEntrada, pieSaida);
    }


  
    @FXML
    public void openAdicionarEntrada(MouseEvent event){
      try {
          
        Stage currentStage = (Stage) StackPaneRoot.getScene().getWindow();
        Stage stage = new Stage();
        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader addContaFXML = new FXMLLoader(MainCaixaController.class.getResource("/JFX/BSI/GesMed/Interfaces/Caixa/AdicionarEntrada.fxml"));
        AdicionarEntradaController addEntradaControl = new AdicionarEntradaController(this);
        addContaFXML.setController(addEntradaControl);
        Parent root = addContaFXML.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainContasController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
  
    @FXML
    public void openAdicionarDespesa(MouseEvent event){
      try {
          
        Stage currentStage = (Stage) StackPaneRoot.getScene().getWindow();
        Stage stage = new Stage();
        stage.initOwner(currentStage);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        FXMLLoader addContaFXML = new FXMLLoader(MainCaixaController.class.getResource("/JFX/BSI/GesMed/Interfaces/Caixa/AdicionarDespesa.fxml"));
        AdicionarDespesaController addContaDesp = new AdicionarDespesaController(this);
        addContaFXML.setController(addContaDesp);
        Parent root = addContaFXML.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainContasController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @FXML
    public void openRelatorioCaixa() {
        
        try {
        
        FXMLLoader registrarFXML = new FXMLLoader(MainCaixaController.class.getResource("/JFX/BSI/GesMed/Interfaces/Caixa/RelatorioCaixa.fxml"));
        
        StackPaneRelatorio = registrarFXML.load();
        
        StackPaneRoot.getChildren().clear();
        StackPaneRoot.getChildren().add(StackPaneRelatorio);
        
        StackPaneRelatorio.setPrefWidth(1100);
        StackPaneRelatorio.setPrefHeight(600);
        StackPaneRelatorio.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(StackPaneRelatorio);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

 
    
    
}
