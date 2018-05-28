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
import JFX.BSI.GesMed.Repositorios.PacienteRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class CadastrarPacienteController implements Initializable {

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
    private JFXComboBox cbxTipoSanguineo;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXButton btn_Limpar;

    @FXML
    private JFXButton btn_Cancelar;
    
   
    ObservableList<String> listTiposSangue = FXCollections.observableArrayList("Selecione","A+","A-","B+","B-","AB+","AB-","O+","O-");
    ObservableList<String> listTipoTelefone = FXCollections.observableArrayList("Selecione","Celular","Fixo","Trabalho");
    private Paciente paciente;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("URL:" +url);
        inicializeComponentes();
    }
    
    public void inicializeComponentes(){
        
        cbxTipoSanguineo.setItems(listTiposSangue);
        cbxTipoSanguineo.setValue("Selecione");
        cbxTipoCelular.setItems(listTipoTelefone);
        cbxTipoCelular.setValue("Selecione");
    }
    
         
    public boolean ValidarCampos(){
        
        boolean CamposValidos = true;
        
        ValidationFields validTextField = new ValidationFields();
        boolean CamposValidosTextFild = false;
        CamposValidosTextFild = validTextField.checkEmptyFields(
                tfdNome, tfdRG, tfdCPF, tfdTelefone, tfdEndereco, 
                tfdBairro, tfdCEP,  tfdPlanoSaude);
        System.out.println("CamposValidos TextField: "+CamposValidosTextFild);
        
        boolean CamposValidosTextArea = false;
        ValidationFields validTextArea = new ValidationFields();
        CamposValidosTextArea = validTextArea.checkEmptyFields(textPSDescricao);
        System.out.println("CamposValidos TextArea: "+CamposValidosTextArea);
        
        boolean CamposValidosComboBox = false;
        ValidationFields validComboBox = new ValidationFields();
        CamposValidosComboBox = validComboBox.checkEmptyFields(cbxTipoSanguineo, cbxTipoCelular);
        System.out.println("CamposValidos CheckBox: "+CamposValidosComboBox);
        
        System.out.println("CamposValidos antes de verificar: "+CamposValidos);
        if(CamposValidosTextFild==false || CamposValidosTextArea==false || CamposValidosComboBox==false ){
            CamposValidos = false; 
        }
        System.out.println("CamposValidos após de verificar: "+CamposValidos);
        return CamposValidos;
    }
    
    
    public void CadastrarPaciente(ActionEvent event){
    
     if(ValidarCampos()==true){
        
        PacienteRepositorio pr = new PacienteRepositorio();
                
        Paciente paciente = new Paciente();
        paciente.setNome(tfdNome.getText());
        paciente.setCPF(tfdCPF.getText());
        paciente.setRG(tfdRG.getText());
        paciente.setDataNasc(tfdDataNascimento.getValue().toString());
        paciente.setTipoSangue(cbxTipoSanguineo.getSelectionModel().getSelectedItem().toString());
        paciente.setObservacao(textObservacoes.getText());
        
        Telefone t = new Telefone();
        if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Celular")){
            t.setCelular(tfdTelefone.getText());
        }else if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Fixo")){
            t.setFixo(tfdTelefone.getText());
        }else if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Trabalho")){
            t.setTrabalho(tfdTelefone.getText());
        }
        paciente.setTelefone(t);
        
        Endereco end = new Endereco();
        end.setRua(tfdEndereco.getText());
        end.setNumero(tfdNumero.getText());
        end.setBairro(tfdBairro.getText());
        end.setCEP(tfdCEP.getText());
        paciente.setEndereco(end);
        
        PlanoSaude ps = new PlanoSaude();
        ps.setTitulo(tfdPlanoSaude.getText());
        ps.setDescricao(textPSDescricao.getText());
        paciente.setPlanoSaude(ps);
        
        try{
        paciente.setID(pr.gerarID());
        pr.adicionar(paciente);
        
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Cadastro");
            AlertErro.setHeaderText("Cadastro de Paciente");
            AlertErro.setContentText("O Paciente foi cadastrado com sucesso");
            AlertErro.showAndWait();
        
        limparCamposCadastro();
        
        }catch(Exception exc){
            exc.printStackTrace();
           
        }finally{
           pr.encerrar();  
        }
     }
        
    }

   

    public void limparCamposCadastro(){
        //DADOS DO PACIENTE
        tfdNome.setText("");
        tfdCPF.setText("");
        tfdRG.setText("");
        tfdDataNascimento.setPromptText(" ");
        cbxTipoSanguineo.setValue("Selecione");
        textObservacoes.setText("");
        tfdTelefone.setText("");
        cbxTipoCelular.setValue("Selecione");
        //ENDEREÇO
        tfdEndereco.setText("");
        tfdNumero.setText("");
        tfdBairro.setText("");
        tfdCEP.setText("");
        //PLANO DE SAÚDE
        tfdPlanoSaude.setText("");
        textPSDescricao.setText("");
    }
    
 

    @FXML
    public void tfdCelularKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdTelefone);
        tff.formatter();
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
    
    
    
}
