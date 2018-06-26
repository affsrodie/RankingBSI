/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Paciente;

import JFX.BSI.GesMed.Entidades.Endereco;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Entidades.PlanoSaude;
import JFX.BSI.GesMed.Entidades.Telefone;
import JFX.BSI.GesMed.Exception.DataBaseConstraintException;
import JFX.BSI.GesMed.Repositorios.PacienteRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.persistence.PersistenceException;


/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class HomePacientesController implements Initializable {
    
    private AnchorPane Cadastro, ViewPacientes, Prefil;
    
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
    
    
    @FXML
    private AnchorPane holderPaneEdit;
    
    @FXML
    private AnchorPane CadastroEdit;
    
    @FXML
    private JFXButton btn_Excluir;

    @FXML
    private JFXButton btn_Editar;

    @FXML
    private JFXTextField tfdFindNome;

    @FXML
    private JFXComboBox jcbTipoPesquisa;
    
    @FXML
    private JFXComboBox cbxTipoSanguineo;
    
    @FXML
    private JFXComboBox cbxTipoCelular;

    @FXML
    private JFXButton btn_Pesquisar;
    
    @FXML
    private JFXTextField tfdNome;

    @FXML
    private JFXTextField tfdRG;

    @FXML
    private JFXTextField tfdCPF;

    @FXML
    private JFXTextField tfdTelefone;

    @FXML
    private JFXDatePicker tfdDataNascimento;

    @FXML
    private JFXTextField tfdEndereco;

    @FXML
    private JFXTextField tfdNumero;

    @FXML
    private JFXTextField tfdBairro;

    @FXML
    private JFXTextField tfdCEP;

    @FXML
    private JFXTextField tfdPlanoSaude;

    @FXML
    private JFXTextArea textPSDescricao;
    
    @FXML
    private JFXTextArea textObservacoes;
   
    @FXML
    private JFXButton btn_Atualizar;

    @FXML
    private JFXButton btn_ExcluirPaciente;

    @FXML
    private JFXButton btn_Cancelar;
    
    @FXML
    private List<Paciente> listPacientes;
    
   

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createPages();
        setNode(ViewPacientes);
    }

        //MÉTODOS PARA PESQUISA DE PACIENTES PARA EDITAR OU EXCLUIR
    

    //MÉTODOS PARA ALTERNAÇÃO DE JANELAS COMO EDITAR, CADASTRAR E PERFIL
    
    
    private void createPages() {
        try {
           
            Prefil = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Paciente/VisualizarPacientes.fxml"));
            Cadastro = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Paciente/CadastrarPaciente.fxml"));
            ViewPacientes = FXMLLoader.load(getClass().getResource("/JFX/BSI/GesMed/Interfaces/Paciente/GerenciarPacientes.fxml"));

            //set up default node on page load
            
        } catch (IOException ex) {
            Logger.getLogger(HomePacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void setNode(Node node) {
        paneRootPreview.getChildren().clear();
        paneRootPreview.getChildren().add((Node) node);
        paneRootPreview.setPrefSize(1100, 550);
        paneRootPreview.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        
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
        setNode(Prefil);
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
    
     
    
    @FXML
    public void CloseWindows(ActionEvent event){
        Stage currentStage = (Stage) holderPane.getScene().getWindow();
        currentStage.hide();
    }
   
 
  
}
