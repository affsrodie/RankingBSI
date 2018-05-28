/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Usuarios;

import JFX.BSI.GesMed.Entidades.Atendente;
import JFX.BSI.GesMed.Entidades.Endereco;
import JFX.BSI.GesMed.Entidades.Telefone;
import JFX.BSI.GesMed.Repositorios.AtendenteRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;



/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class CadastrarAtendenteController implements Initializable {

   
    @FXML
    private AnchorPane paneCadastroAtendente;
    
    @FXML
    private JFXTextField tfdNome;

    @FXML
    private JFXTextField tfdRG;

    @FXML
    private JFXTextField tfdCPF;

    @FXML
    private JFXTextField tfdTelefone;

    @FXML
    private JFXComboBox cbxTipoCelular;

    @FXML
    private JFXDatePicker tfdDataNascimento;
    
    @FXML
    private JFXDatePicker tfdDataContratacao;

    @FXML
    private JFXTextField tfdEndereco;

    @FXML
    private JFXTextField tfdNumero;

    @FXML
    private JFXTextField tfdBairro;

    @FXML
    private JFXTextField tfdCEP;

    @FXML
    private JFXPasswordField passSenha;

    @FXML
    private JFXPasswordField passSenhaConfirma;

    @FXML
    private JFXButton btn_Adicionar;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXButton btn_Limpar;

    @FXML
    private JFXButton btn_Cancelar;
    
    private ObservableList<String> listTipoTelefone = FXCollections.observableArrayList("Selecione","Celular","Fixo","Trabalho");
    private Atendente atendente;

    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("URL:" +url);
        inicializeComponentes();
    }
    
    public void inicializeComponentes(){
        cbxTipoCelular.setItems(listTipoTelefone);
        cbxTipoCelular.setValue("Selecione");
    }
    
 

         
    public boolean ValidarCampos(){
        
        boolean CamposValidos = true;
        
        ValidationFields validTextField = new ValidationFields();
        boolean CamposValidosTextFild = false;
        CamposValidosTextFild = validTextField.checkEmptyFields(
                tfdNome, tfdRG, tfdCPF, tfdTelefone, tfdEndereco, 
                tfdBairro, tfdCEP);
        System.out.println("CamposValidos TextField: "+CamposValidosTextFild);
      
        boolean CamposValidosComboBox = false;
        ValidationFields validComboBox = new ValidationFields();
        CamposValidosComboBox = validComboBox.checkEmptyFields(cbxTipoCelular);
        System.out.println("CamposValidos CheckBox: "+CamposValidosComboBox);
        
        System.out.println("CamposValidos antes de verificar: "+CamposValidos);
        if(CamposValidosTextFild==false ||  CamposValidosComboBox==false ){
            CamposValidos = false; 
        }
        System.out.println("CamposValidos após de verificar: "+CamposValidos);
        return CamposValidos;
    }
    
    
    public void CadastrarAtendente(ActionEvent event){
    
     if(ValidarCampos()==true&&ValidarSenha()==true){
        
         AtendenteRepositorio mr = new AtendenteRepositorio();
                
        Atendente atendente = new Atendente();
        atendente.setNome(tfdNome.getText());
        atendente.setCPF(tfdCPF.getText());
        atendente.setRG(tfdRG.getText());
        atendente.setDataNasc(tfdDataNascimento.getValue().toString());

        Telefone t = new Telefone();
        if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Celular")){
            t.setCelular(tfdTelefone.getText());
        }else if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Fixo")){
            t.setFixo(tfdTelefone.getText());
        }else if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Trabalho")){
            t.setTrabalho(tfdTelefone.getText());
        }
        atendente.setTelefone(t);
        
        Endereco end = new Endereco();
        end.setRua(tfdEndereco.getText());
        end.setNumero(tfdNumero.getText());
        end.setBairro(tfdBairro.getText());
        end.setCEP(tfdCEP.getText());
        atendente.setEndereco(end);
        
        atendente.setDataAmissao(tfdDataContratacao.getValue().toString());
        atendente.setSenha(passSenha.getText());
                
        try{
        atendente.setID(mr.gerarID());
        mr.adicionar(atendente);
        
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Cadastro");
            AlertErro.setHeaderText("Cadastro de Atendente");
            AlertErro.setContentText("Atendente foi cadastrado com sucesso");
            AlertErro.showAndWait();
        
        limparCamposCadastro();
        
        }catch(Exception exc){
            exc.printStackTrace();
        }finally{
           mr.encerrar();  
        }
     }  
    }
    
    @FXML
    public void limparCampos(ActionEvent event){
        limparCamposCadastro();
    }
   

    public void limparCamposCadastro(){
        //DADOS DO ATENDENTE
        tfdNome.setText("");
        tfdCPF.setText("");
        tfdRG.setText("");
        tfdTelefone.setText("");
        cbxTipoCelular.setValue("Selecione");
        tfdDataNascimento.setValue(null);
        //ENDEREÇO
        tfdEndereco.setText("");
        tfdNumero.setText("");
        tfdBairro.setText("");
        tfdCEP.setText("");
                
        //DADOS DO ATENDENTE
        tfdDataContratacao.setValue(null);
        passSenha.setText(null);
        passSenhaConfirma.setText(null);
    }
    
 

    @FXML
    public void tfdCelularKeyRelased(){
        if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Celular")){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdTelefone);
        tff.formatter();  
        }else{
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdTelefone);
        tff.formatter();    
        }
        
    }
    
    @FXML
    public void tfdCPFKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdCPF);
        tff.formatter();
    }
    @FXML
    public void tfdCEPKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#####-###");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdCEP);
        tff.formatter();
    }
    
    
    @FXML
    public void tfdRGKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("################");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdRG);
        tff.formatter();
    }
    @FXML
    public void tfdNumeroKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("######");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdNumero);
        tff.formatter();
    }
    private boolean ValidarSenha(){
        
        boolean CamposValidos = false;
        if(passSenha.getText().trim().equals(passSenhaConfirma.getText().trim())){
            CamposValidos = true;
        }else if(passSenha.getText().trim().equals("")||passSenhaConfirma.getText().trim().equals("")){
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Senha");
            AlertErro.setHeaderText("Campos de senha Inválidos");
            AlertErro.setContentText("Os campos de senha estão vazios");
            AlertErro.showAndWait();
        }else{
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Senha");
            AlertErro.setHeaderText("Campos de senha Inválidos");
            AlertErro.setContentText("Os campos de senha não são iguais");
            AlertErro.showAndWait();
            passSenha.setText(null);
            passSenhaConfirma.setText(null);
            CamposValidos = false;
        }
        return CamposValidos;
    }  
    

    
}
