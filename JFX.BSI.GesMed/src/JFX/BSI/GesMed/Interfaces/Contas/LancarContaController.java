/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Contas;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Conta;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Repositorios.AgendamentoRepositorio;
import JFX.BSI.GesMed.Repositorios.ContaRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class LancarContaController implements Initializable {
    
    @FXML
    private StackPane StackPaneRoot;
    
    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private Label infoNomePaciente, infoCPF;

    @FXML
    private AnchorPane paneDados;

    @FXML
    private JFXDatePicker tfdDataPagamento;

  
    @FXML
    private JFXToggleButton toggleJaPago;

    @FXML
    private JFXTextField tfdValor;


    @FXML
    private JFXButton btnCadastrar;
    

    @FXML
    private JFXTreeTableView<FavorecidosFX> tblFavorecidos;

    @FXML
    private JFXButton btnAdicionar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnCancelarFav;

    @FXML
    private JFXButton btnSalvarFav;

    private ObservableList<FavorecidosFX> listFavorecidosFX = FXCollections.observableArrayList();
    private ObservableList<String> cbxListFavorecidos = FXCollections.observableArrayList();
    private Paciente paciente;
    private Agendamento agenda;
    private MainContasController Pai;
    
    public LancarContaController(){
        
    }
    
    public LancarContaController(MainContasController pai, Paciente paciente, Agendamento agendamento){
        this.paciente = paciente;
        this.agenda = agendamento;
        Pai = pai;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AtivarDataPagamento();
        tfdDataPagamento.setDisable(true);
        if(paciente!=null){
            infoNomePaciente.setText(paciente.getNome());
            infoCPF.setText(paciente.getCPF());
        }
    }
      
    public void AtivarDataPagamento(){
     toggleJaPago.selectedProperty().addListener(new ChangeListener<Boolean>() {
         @Override
         public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
             if(toggleJaPago.isSelected()==true){
                 tfdDataPagamento.setDisable(false);
             }else{
                 tfdDataPagamento.setDisable(true);
             }
         }
     });
   }
    
  

    public boolean ValidarCampos(){
        ValidationFields validCampos = new ValidationFields();
        boolean CamposValidos = false;
        
        CamposValidos = validCampos.checkEmptyFields(tfdValor);
        
        if(tfdDataPagamento.getValue()==null&&toggleJaPago.isSelected()==true){
            CamposValidos = false;
            Alert AlertErro = new Alert(Alert.AlertType.ERROR);
            AlertErro.setTitle("Campos Inválidos");
            AlertErro.setHeaderText("Campo está Vazio");
            AlertErro.setContentText("A Data de Pagamento está em branco");
            AlertErro.showAndWait();
        }
        return CamposValidos;
    }
    
    @FXML
    public void LancarContaPaciente(MouseEvent event){
        Conta conta = new Conta();
        ContaRepositorio contaRep = new ContaRepositorio();
       
        if(ValidarCampos()){
            if(paciente!=null){
               conta.setNome(paciente.getNome());
            }
            conta.setNome(paciente.getNome());
            conta.setCPF(paciente.getCPF());
            conta.setReferencia("Consulta do dia "+agenda.getData());
            conta.setEndereco(paciente.getEndereco().getBairro()+" - "+paciente.getEndereco().getRua()+" - "+paciente.getEndereco().getCEP());
            conta.setValor(Double.parseDouble(tfdValor.getText()));
            LocalDate data = LocalDate.now();
            SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
            Date dataLanc = null;
            try {
                dataLanc = formatData.parse(data.toString());
            } catch (ParseException ex) {
                Logger.getLogger(LancarContaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            conta.setDataLancamento(dataLanc);
                        
            Date DataPagamento = null;
            if(toggleJaPago.isSelected()==true){
                LocalDate dataTemp= tfdDataPagamento.getValue();
                try {
                    DataPagamento = formatData.parse(dataTemp.toString());
                } catch (ParseException ex) {
                    Logger.getLogger(LancarContaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                conta.setDataPagamento(DataPagamento);
                conta.setStatus("Pago");
                conta.setTipo("Entrada");
            }else{
                conta.setStatus("Pendente");
            }
            
            contaRep.adicionar(conta);
            agenda.setStatus("Conta(Pendente)");
            AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
            agenRep.atualizar(agenda);
            Pai.NullAgendamentos();
            CloseWindows();
        }
        
    }
    
    
    @FXML
    public void CancelLancarContas(MouseEvent event){
        CloseWindows();
    }
    
    public void CloseWindows(){
        Stage currentStage = (Stage) StackPaneRoot.getScene().getWindow();
        currentStage.hide();
    }
    

    
    @FXML
    public void EnableButtons(MouseEvent event){
        if(tblFavorecidos.getSelectionModel().getSelectedIndex()>=0){
            btnEditar.setDisable(false);
            btnExcluir.setDisable(false);
        }else {
            btnEditar.setDisable(true);
            btnExcluir.setDisable(true);
        }
    }
    
    @FXML
    public void DisableButtons(MouseEvent event){
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);
        tblFavorecidos.getSelectionModel().clearSelection();
    }
  
    
    @FXML
    public void tfdValorKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("######");
        tff.setCaracteresValidos("0123456789.");
        tff.setTf(tfdValor);
        tff.formatter();
    }
    
    class FavorecidosFX extends RecursiveTreeObject<FavorecidosFX> {
       
       private StringProperty IDFavorecido;
       private StringProperty FavorecidoCurto;
       private StringProperty FavorecidoExtenso;

        public StringProperty getIDFavorecido() {
            return IDFavorecido;
        }

        public void setIDFavorecido(String IDFavorecido) {
            this.IDFavorecido =  new SimpleStringProperty(IDFavorecido);
        }

        public StringProperty getFavorecidoCurto() {
            return FavorecidoCurto;
        }

        public void setFavorecidoCurto(String FavorecidoCurto) {
            this.FavorecidoCurto =  new SimpleStringProperty(FavorecidoCurto);
        }

        public StringProperty getFavorecidoExtenso() {
            return FavorecidoExtenso;
        }

        public void setFavorecidoExtenso(String FavorecidoExtenso) {
            this.FavorecidoExtenso =  new SimpleStringProperty(FavorecidoExtenso);
        }
       
    }
}
