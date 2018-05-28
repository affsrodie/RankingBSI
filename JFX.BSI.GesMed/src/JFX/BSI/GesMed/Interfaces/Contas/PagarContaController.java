/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Contas;

import JFX.BSI.GesMed.Entidades.Conta;
import JFX.BSI.GesMed.Repositorios.ContaRepositorio;
import JFX.BSI.GesMed.Repositorios.ContaRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class PagarContaController implements Initializable {
    
    @FXML
    private StackPane StackRootPane;
    
    @FXML
    private JFXDatePicker tfdDataPagamento;

    @FXML
    private Label infoNomePaciente;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnPagarConta;

    @FXML
    private Label infoValor;

    @FXML
    private Label infoFavorecido;

    @FXML
    private Label infoDataVencimento;

    private Conta contaPaciente;
    
    private MainContasController Pai;
    
    public PagarContaController (){
        
    }
    
    public PagarContaController (MainContasController pai, Conta conta){
        this.contaPaciente = conta;
        this.Pai = pai;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfoContaPaciente();
    } 
    
    public void setInfoContaPaciente(){
        
        infoNomePaciente.setText(this.contaPaciente.getReferencia());
        
        infoValor.setText(Double.toString(this.contaPaciente.getValor()));
        infoFavorecido.setText(this.contaPaciente.getCPF());
        infoDataVencimento.setText(this.contaPaciente.getDataLancamento().toString());
        
//        LocalDate dataTemp= LocalDate.now();
//        if(contaPaciente.getDataLancamento()==null){
//            tfdDataPagamento.setValue(dataTemp);
//        }
        
        
    }
    @FXML
    public void PagarConta(MouseEvent event){
        
        if(tfdDataPagamento.getValue()!=null||tfdDataPagamento.getValue().toString().equals("")){
                      
            SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate dataTemp= tfdDataPagamento.getValue();
                Date DataPagamento = null;
                try {
                    DataPagamento = formatData.parse(dataTemp.toString());
                } catch (ParseException ex) {
                    Logger.getLogger(PagarContaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.contaPaciente.setDataPagamento(DataPagamento);
                this.contaPaciente.setStatus("Pago");
            
                ContaRepositorio contaRep = new ContaRepositorio();
                contaRep.atualizar(contaPaciente);
                
                Pai.NullContasPendentes();
                Pai.TableViewContas();
                CloseWindows();
                
        }else{
            Alert AlertErro = new Alert(Alert.AlertType.ERROR);
            AlertErro.setTitle("Campos Inválidos");
            AlertErro.setHeaderText("Campo está Vazio");
            AlertErro.setContentText("A Data de Pagamento está em branco");
            AlertErro.showAndWait();
        }
        
    }
  
    @FXML
    public void CancelLancarContas(MouseEvent event){
        CloseWindows();
    }
    
    public void CloseWindows(){
        Stage currentStage = (Stage) StackRootPane.getScene().getWindow();
        currentStage.hide();
   }
    
    
}
