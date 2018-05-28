/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Usuarios;

import JFX.BSI.GesMed.Entidades.Endereco;
import JFX.BSI.GesMed.Entidades.Especializacao;
import JFX.BSI.GesMed.Entidades.Medico;
import JFX.BSI.GesMed.Entidades.Telefone;
import JFX.BSI.GesMed.Repositorios.EspecialRepositorio;
import JFX.BSI.GesMed.Repositorios.MedicoRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class CadastrarMedicoController implements Initializable {

    @FXML
    private AnchorPane novaEspecialidade, paneNovaEspecialidade, returnCadastroMed;
    
    @FXML
    private AnchorPane paneDadosMedico;
    
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
    private JFXTextField tfdCRM;
    
    @FXML
    private JFXTextField tfdEspecialidade;

    @FXML
    private JFXTextArea textDescricao;

    @FXML
    private JFXButton btn_SalvarEspecialidade;

    @FXML
    private JFXButton btn_CancelarEspecial;

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
    
    
    private List<Especializacao> listEspecializacoes;
    private ObservableList<String> listEspecialidadesFX = FXCollections.observableArrayList();
    private ObservableList<String> listTipoTelefone = FXCollections.observableArrayList("Selecione","Celular","Fixo","Trabalho");
    private Medico medico;
    
    @FXML
    private JFXComboBox<String> cbxEspecialidade;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("URL:" +url);
        inicializeComponentes();
    }
    
    public void inicializeComponentes(){
        
       carregarEspecialidades();
       cbxEspecialidade.setItems(listEspecialidadesFX);
       cbxEspecialidade.setValue("Selecione");
        
        
        cbxTipoCelular.setItems(listTipoTelefone);
        cbxTipoCelular.setValue("Selecione");
    }
    
    public void carregarEspecialidades(){
        EspecialRepositorio espRep = new EspecialRepositorio();
        listEspecializacoes = espRep.recuperarTodas();
        listEspecialidadesFX.add("Selecione");
        for(Especializacao especial : listEspecializacoes){
            listEspecialidadesFX.add(especial.getTitulo());
        }
    }
    
    @FXML
    public Especializacao getEspecialidade(){
        String Especialidade = cbxEspecialidade.getSelectionModel().getSelectedItem();
        if(!cbxEspecialidade.getSelectionModel().equals("Selecione")){
        Especializacao especial = listEspecializacoes.get(cbxEspecialidade.getSelectionModel().getSelectedIndex()-1);
        return especial;
        }
        return null;
    }
         
    public boolean ValidarCampos(){
        
        boolean CamposValidos = true;
        
        ValidationFields validTextField = new ValidationFields();
        boolean CamposValidosTextFild = false;
        CamposValidosTextFild = validTextField.checkEmptyFields(
                tfdNome, tfdRG, tfdCPF, tfdTelefone, tfdEndereco, 
                tfdBairro, tfdCEP, tfdCRM);
        System.out.println("CamposValidos TextField: "+CamposValidosTextFild);
      
        boolean CamposValidosComboBox = false;
        ValidationFields validComboBox = new ValidationFields();
        CamposValidosComboBox = validComboBox.checkEmptyFields(cbxEspecialidade, cbxTipoCelular);
        System.out.println("CamposValidos CheckBox: "+CamposValidosComboBox);
        
        System.out.println("CamposValidos antes de verificar: "+CamposValidos);
        if(CamposValidosTextFild==false ||  CamposValidosComboBox==false ){
            CamposValidos = false; 
        }
        System.out.println("CamposValidos após de verificar: "+CamposValidos);
        return CamposValidos;
    }
    
    
    public void CadastrarMedico(ActionEvent event){
    
     if(ValidarCampos()==true&&ValidarSenha()==true){
        
         MedicoRepositorio mr = new MedicoRepositorio();
                
        Medico medico = new Medico();
        medico.setNome(tfdNome.getText());
        medico.setCPF(tfdCPF.getText());
        medico.setRG(tfdRG.getText());
        medico.setDataNasc(tfdDataNascimento.getValue().toString());

        Telefone t = new Telefone();
        if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Celular")){
            t.setCelular(tfdTelefone.getText());
        }else if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Fixo")){
            t.setFixo(tfdTelefone.getText());
        }else if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Trabalho")){
            t.setTrabalho(tfdTelefone.getText());
        }
        medico.setTelefone(t);
        
        Endereco end = new Endereco();
        end.setRua(tfdEndereco.getText());
        end.setNumero(tfdNumero.getText());
        end.setBairro(tfdBairro.getText());
        end.setCEP(tfdCEP.getText());
        medico.setEndereco(end);
        
        if(!cbxEspecialidade.getSelectionModel().equals("Selecione")){
        Especializacao especialidade = getEspecialidade();
        medico.setEspecializacao(especialidade);
        }
        
        medico.setCRM(tfdCRM.getText());
        medico.setSenha(passSenha.getText());
                
        try{
        medico.setID(mr.gerarID());
        mr.adicionar(medico);
        
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Cadastro");
            AlertErro.setHeaderText("Cadastro de Médico");
            AlertErro.setContentText("Médico foi cadastrado com sucesso");
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
        //DADOS DO MÉDICO
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
                
        //DADOS DO MÉDICO
        tfdCRM.setText("");
        cbxEspecialidade.setValue("Selecione");
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
    public void tfdCRMKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##########");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdCRM);
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
    
    @FXML
    private void openNovaEspecialidade(ActionEvent event){
        
    
        FXMLLoader cadastroEspecial = new FXMLLoader(CadastrarMedicoController.class.getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/NovaEspecialidade.fxml"));
        cadastroEspecial.setController(this);
        
        try{
            novaEspecialidade = cadastroEspecial.load();
            paneDadosMedico.getChildren().clear();
            paneDadosMedico.getChildren().add((Node) novaEspecialidade);
        
            FadeTransition ft = new FadeTransition(Duration.millis(1000));
            ft.setNode(novaEspecialidade);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
        }catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    private void openCadastrarMedico(){
        
        FXMLLoader cadastroEspecial = new FXMLLoader(CadastrarMedicoController.class.getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/returnDadosMedico.fxml"));
        cadastroEspecial.setController(this);
        
        try{
            returnCadastroMed = cadastroEspecial.load();
            paneDadosMedico.getChildren().clear();
            paneDadosMedico.getChildren().add((Node) returnCadastroMed);
            
           
            FadeTransition ft = new FadeTransition(Duration.millis(200));
            ft.setNode(returnCadastroMed);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
            
            listEspecialidadesFX = FXCollections.observableArrayList();
            carregarEspecialidades();
            cbxEspecialidade.setItems(listEspecialidadesFX);
            cbxEspecialidade.setValue("Selecione");
        }catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }
    
    public boolean ValidarCadastroEspecialidade(){
        ValidationFields validTexts = new ValidationFields();
        
        return validTexts.checkEmptyFields(tfdEspecialidade, textDescricao);
        }
    
    public void SalvarEspecialidade(MouseEvent event){
        System.out.println("Campos Validos? "+ ValidarCadastroEspecialidade());
        if(ValidarCadastroEspecialidade()){
        EspecialRepositorio espRep = new EspecialRepositorio();
        Especializacao especial = new Especializacao();
//        especial.setID(espRep.gerarID());
        especial.setTitulo(tfdEspecialidade.getText());
        especial.setDescricao(textDescricao.getText());
        espRep.adicionar(especial);
        espRep.encerrar();
        
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Cadastro");
            AlertErro.setHeaderText("Cadastro de Especialidade");
            AlertErro.setContentText("Nova Especialidade cadastrada com sucesso");
            AlertErro.showAndWait();
        
         openCadastrarMedico();
        }
        
    }
    
    
    
}
