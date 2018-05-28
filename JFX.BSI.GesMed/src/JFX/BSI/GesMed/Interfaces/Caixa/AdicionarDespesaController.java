/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Caixa;

import JFX.BSI.GesMed.Entidades.Conta;
import JFX.BSI.GesMed.Repositorios.ContaRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class AdicionarDespesaController implements Initializable {
    
    
     @FXML
    private StackPane StackRootPane;

    @FXML
    private Label infoNomePaciente;

    @FXML
    private JFXTextField tfdNomeSaida;

    @FXML
    private JFXTextField tfdValor;

    @FXML
    private DatePicker tfdDataLancamento;

    @FXML
    private JFXTextField tfdCNPJ;

    @FXML
    private JFXTextField tfdEndereco;

    @FXML
    private JFXTextField tfdReferencia;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnCancelar;
    
 
    
    private ObservableList<String> listCategorias = FXCollections.observableArrayList("Selecione","Contas Fixas","Salário", "Material de Limpeza", "Material de Escritório", "Material Clínico", "Aluguel de Imóvel", "Aluguel de Material", "Outras");
    
    private MainCaixaController Pai;
    
    public AdicionarDespesaController (){
        
    }
    
    public AdicionarDespesaController (MainCaixaController pai){
        Pai=pai;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfoWindows();
    }

    public void setInfoWindows(){
        
        LocalDate hoje = LocalDate.now();
        tfdDataLancamento.setValue(hoje);
      
        
    }
    
  
    
    @FXML
    public void SalvarConta(MouseEvent event){
        ContaRepositorio contaRep = new ContaRepositorio();
        Conta conta = new Conta();
        ValidationFields validarCampos = new ValidationFields();
        if(validarCampos.checkEmptyFields(tfdNomeSaida,tfdValor, tfdEndereco, tfdCNPJ, tfdReferencia)){
            conta.setNome(tfdNomeSaida.getText());
            conta.setCNPJ(tfdCNPJ.getText());
            conta.setEndereco(tfdEndereco.getText());
            conta.setValor(Double.parseDouble(tfdValor.getText()));
            conta.setReferencia(tfdReferencia.getText());
            LocalDate localDate = tfdDataLancamento.getValue();
            SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
            Date dataLanc=null;
            try {
                dataLanc = formatData.parse(localDate.toString());
            } catch (ParseException ex) {
                Logger.getLogger(AdicionarDespesaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            conta.setDataLancamento(dataLanc);
            conta.setTipo("Saida");
            contaRep.adicionar(conta);
            ExitAfterSave();
        }else{
            Alert AlertErro = new Alert(Alert.AlertType.ERROR);
            AlertErro.setTitle("Campos Inválidos");
            AlertErro.setHeaderText("Os Campo está Vazio");
            AlertErro.setContentText("Verifique se não há campos em brancos");
            AlertErro.showAndWait();
        }
    }
    
    public void ExitAfterSave(){
        Stage currentStage = (Stage) StackRootPane.getScene().getWindow();
        currentStage.hide();
    }
    
    @FXML
    public void CloseWindows(MouseEvent event){
        Stage currentStage = (Stage) StackRootPane.getScene().getWindow();
        currentStage.hide();
    }
    
    @FXML
    public void tfdValorKeyRelased(){
        
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#########");
        tff.setCaracteresValidos("0123456789.");
        tff.setTf(tfdValor);
        tff.formatter();
 
    }
    
}
