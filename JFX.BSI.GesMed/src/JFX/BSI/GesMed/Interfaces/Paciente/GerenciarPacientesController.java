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
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.persistence.PersistenceException;


/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class GerenciarPacientesController implements Initializable {
    
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
    private JFXTreeTableView<PacientesFX> tblFindPacientes = new JFXTreeTableView<>();
    
    private List<Paciente> listPacientes;
    
    ObservableList<PacientesFX> pacientesfx = FXCollections.observableArrayList();
    ObservableList<String> listTipoPesquisa = FXCollections.observableArrayList("NOME","ID","CPF");
    
    ObservableList<String> listTiposSangue = FXCollections.observableArrayList("Selecione","A+","A-","B+","B-","AB+","AB-","O+","O-");
    ObservableList<String> listTipoTelefone = FXCollections.observableArrayList("Selecione","Celular","Fixo","Trabalho");
    private Paciente paciente;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColumnTableFindPacientes();
        inicializeComponentes();
    }

        //MÉTODOS PARA PESQUISA DE PACIENTES PARA EDITAR OU EXCLUIR
    
    @FXML
    public void ColumnTableFindPacientes(){
        
        JFXTreeTableColumn<PacientesFX,String> tblID = new JFXTreeTableColumn<>("ID");
        tblID.setMinWidth(95);
        tblID.setPrefWidth(95);
        tblID.setMaxWidth(100);
        tblID.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<PacientesFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<PacientesFX, String> param) {
                return param.getValue().getValue().ID;
            }
        });
        
        JFXTreeTableColumn<PacientesFX,String> tblNomePaciente = new JFXTreeTableColumn<>("Nome do Paciente");
        tblNomePaciente.setMinWidth(285);
        tblNomePaciente.setPrefWidth(300);
        tblNomePaciente.setMaxWidth(1000);
        tblNomePaciente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<PacientesFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<PacientesFX, String> param) {
                return param.getValue().getValue().nomePaciente;
            }
        });
         
        JFXTreeTableColumn<PacientesFX,String> tblCPF = new JFXTreeTableColumn<>("CPF");
        tblCPF.setMinWidth(200);
        tblCPF.setPrefWidth(240);
        tblCPF.setMaxWidth(350);
        tblCPF.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<PacientesFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<PacientesFX, String> param) {
                return param.getValue().getValue().CPF;
            }
        });
        
        JFXTreeTableColumn<PacientesFX,String> tblTelefone = new JFXTreeTableColumn<>("Telefone");
        tblTelefone.setMinWidth(150);
        tblTelefone.setPrefWidth(150);
        tblTelefone.setMaxWidth(350);
        tblTelefone.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<PacientesFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<PacientesFX, String> param) {
                return param.getValue().getValue().Celular;
            }
        });
        
              PacienteRepositorio pacRep = new PacienteRepositorio();
        
        if(listPacientes==null||listPacientes.size()==0){
        listPacientes = pacRep.recuperarTodos();
        pacRep.encerrar();
        }
        
        PacientesFX pacFX;
        pacientesfx = FXCollections.observableArrayList();
        if(listPacientes!=null){
            for( Paciente paciente : listPacientes){
                pacFX = new PacientesFX(Integer.toString(paciente.getID()), paciente.getNome(), paciente.getCPF(), paciente.getTelefone().getTelefone());
                pacientesfx.add(pacFX);
            }    
        }
        
        
        final TreeItem<PacientesFX> root = new RecursiveTreeItem<PacientesFX>(pacientesfx, RecursiveTreeObject::getChildren);
        tblFindPacientes.getColumns().setAll(tblID, tblNomePaciente, tblCPF, tblTelefone);
        tblFindPacientes.setRoot(root);
        tblFindPacientes.setShowRoot(false);
       
    //PESQUISA DE PACIENTES USANDO O ID, NOME, CPF PASSADO NO TEXTFIELD

   
        
      tfdFindNome.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               
            if(jcbTipoPesquisa.getValue().equals("NOME")){
                 tblFindPacientes.setPredicate(new Predicate<TreeItem<PacientesFX>>() {
                   @Override
                   public boolean test(TreeItem<PacientesFX> paciente) {
                      Boolean flag = paciente.getValue().nomePaciente.getValue().contains(newValue);
                      return flag;
                   }
               });  
            }
              
            }
        });
    }
    @FXML
    public void pesquisarPaciente(MouseEvent event){
        System.out.println("OPEN PESQUISA PACIENTE");
          if(jcbTipoPesquisa.getValue().toString().equals("ID")){
              PacienteRepositorio pRep = new PacienteRepositorio();
                if(!tfdFindNome.getText().trim().equals("")){
                    pacientesfx = null;
                    listPacientes = new ArrayList<Paciente>();
                    paciente = pRep.recuperar(Integer.parseInt(tfdFindNome.getText().trim()));
                    listPacientes.add(paciente);
                    ColumnTableFindPacientes();
                }else{
                    listPacientes = null;
                    listPacientes = pRep.recuperarTodos();
                    ColumnTableFindPacientes();
                }
            }else if(jcbTipoPesquisa.getValue().toString().equals("CPF")){
                    PacienteRepositorio pRep = new PacienteRepositorio();
                    if(!tfdFindNome.getText().trim().equals("")){
                    listPacientes = null;
                    pacientesfx = null;
                    listPacientes = new ArrayList<Paciente>();
                    listPacientes = pRep.recuperarPacienteCPF(tfdFindNome.getText().trim());
                    ColumnTableFindPacientes();  
                    }else{
                    pacientesfx = null;    
                    listPacientes = pRep.recuperarTodos();
                    ColumnTableFindPacientes();
                    }
             }
    }
    
    @FXML
    public void selectPacienteEdit(MouseEvent event){
        TreeItem<PacientesFX> pFX =  tblFindPacientes.getSelectionModel().getSelectedItem();
        PacientesFX pacientefx = pFX.getValue();
        
        PacienteRepositorio pacRep = new PacienteRepositorio();
        Paciente paciente = pacRep.recuperar(Integer.parseInt(pacientefx.ID.getValue()));
        openEditCadastro(paciente, 0);
       
    }
    
    @FXML
    public void selectPacienteExcluir(MouseEvent event){
        TreeItem<PacientesFX> pFX =  tblFindPacientes.getSelectionModel().getSelectedItem();
        PacientesFX pacientefx = pFX.getValue();
        
        PacienteRepositorio pacRep = new PacienteRepositorio();
        Paciente paciente = pacRep.recuperar(Integer.parseInt(pacientefx.ID.getValue()));
        this.paciente=paciente;
        openEditCadastro(paciente, 1);
    }
    

    
    private void openEditPaciente(){
        
        FXMLLoader managerPac = new FXMLLoader(GerenciarPacientesController.class.getResource("/JFX/BSI/GesMed/Interfaces/Paciente/openPaciente.fxml"));
        managerPac.setController(this);
        
        try{
            CadastroEdit = managerPac.load();
            holderPaneEdit.getChildren().clear();
            holderPaneEdit.getChildren().add((Node) CadastroEdit);
        
            FadeTransition ft = new FadeTransition(Duration.millis(1000));
            ft.setNode(CadastroEdit);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
        }catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }
    
    private void openGerenciarPaciente(){
        
        FXMLLoader managerPac = new FXMLLoader(GerenciarPacientesController.class.getResource("/JFX/BSI/GesMed/Interfaces/Paciente/GerenciarPacientes.fxml"));
        
        try{
            CadastroEdit = managerPac.load();
            holderPaneEdit.getChildren().clear();
            holderPaneEdit.getChildren().add((Node) CadastroEdit);
            
            ColumnTableFindPacientes();
            FadeTransition ft = new FadeTransition(Duration.millis(1000));
            ft.setNode(CadastroEdit);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
        }catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }
        
    void openEditCadastro(Paciente paciente, int Operacao) {
        openEditPaciente();
        inicializeComponentesEdit();
        disableCamposCadastro(Operacao);
        PacienteSelectRow(paciente);
    }
    
    public void inicializeComponentes(){
        jcbTipoPesquisa.setItems(listTipoPesquisa);
        jcbTipoPesquisa.setValue("NOME");
    }
    
    public void inicializeComponentesEdit(){
        cbxTipoSanguineo.setItems(listTiposSangue);
        cbxTipoSanguineo.setValue("Selecione");
        cbxTipoCelular.setItems(listTipoTelefone);
        cbxTipoCelular.setValue("Selecione");
    }
 
    
    @FXML
    public void EnnableButtons(MouseEvent event){
        if(tblFindPacientes.getSelectionModel().getSelectedIndex()>=0){
            btn_Editar.setDisable(false);
            btn_Excluir.setDisable(false);
        }else{
            btn_Editar.setDisable(true);
            btn_Excluir.setDisable(true);
        }
    }
    
    @FXML
    public void DissableButtons(MouseEvent event){
            btn_Editar.setDisable(true);
            btn_Excluir.setDisable(true);
            tblFindPacientes.getSelectionModel().clearSelection();
    }
    
    
    
    //MÉTODOS PARA EDIÇÃO E EXCLUSÃO DE PACIENTES REFERENTE AO XML OPENPACIENTE.XML
    
            
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
    
    

    public void PacienteSelectRow(Paciente paciente){
        
        PacienteRepositorio pRep = new PacienteRepositorio();
        this.paciente = paciente;
        if(this.paciente!=null){
         //   , , , 
         //    tfdNumeroEdit, tfdCEPEdit,  tfdPlanoSaudeEdit;
             tfdNome.setText(
                 this.paciente.getNome()
         );
         tfdRG.setText(this.paciente.getRG());
         tfdCPF.setText(this.paciente.getCPF());
         tfdTelefone.setText(this.paciente.getTelefone().getTelefone());
         tfdEndereco.setText(this.paciente.getEndereco().getRua());
         tfdBairro.setText(this.paciente.getEndereco().getBairro());
         tfdNumero.setText(this.paciente.getEndereco().getNumero());
         tfdCEP.setText(this.paciente.getEndereco().getCEP());
         tfdPlanoSaude.setText(this.paciente.getPlanoSaude().getTitulo());
         textObservacoes.setText(this.paciente.getObservacao());
         textPSDescricao.setText(this.paciente.getPlanoSaude().getDescricao());
         cbxTipoSanguineo.setValue(this.paciente.getTipoSangue());
         tfdDataNascimento.setValue(LocalDate.parse(this.paciente.getDataNasc()));
         
        }
    }
 
    
    @FXML    
    public void atualizarPaciente(ActionEvent event){
      
     if(ValidarCampos()==true){
        
        PacienteRepositorio pr = new PacienteRepositorio();
        
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
        
        this.paciente.getPlanoSaude().setTitulo(tfdPlanoSaude.getText());
        this.paciente.getPlanoSaude().setDescricao(textPSDescricao.getText());
                
        try{
        pr.atualizar(paciente);
        
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Atualização");
            AlertErro.setHeaderText("Atualização de Paciente");
            AlertErro.setContentText("O Paciente foi atualizado com sucesso");
            AlertErro.showAndWait();
            openGerenciarPaciente();
            ColumnTableFindPacientes();
        }catch(Exception exc){
            exc.printStackTrace();
        }finally{
         pr.encerrar();
         HomePacienteController homeP = new HomePacienteController();
         
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
    
    public void disableCamposCadastro(int OPERACAO){
         
         final int EDICAO = 0;
         final int EXCLUIR = 1;
         
        switch(OPERACAO)
        {
            case EDICAO: System.out.println("EDICAO");
                        tfdNome.setDisable(false);
                        tfdCPF.setDisable(true);
                        tfdRG.setDisable(false);
                        tfdDataNascimento.setDisable(false);
                        cbxTipoSanguineo.setDisable(false);
                        textObservacoes.setDisable(false);
                        tfdTelefone.setDisable(false);
                        cbxTipoCelular.setDisable(false);
                        //ENDEREÇO
                        tfdEndereco.setDisable(false);
                        tfdNumero.setDisable(false);
                        tfdBairro.setDisable(false);
                        tfdCEP.setDisable(false);
                        //PLANO DE SAÚDE
                        tfdPlanoSaude.setDisable(false);
                        textPSDescricao.setDisable(false);
                        btn_ExcluirPaciente.setDisable(true);
                break;
                
            case EXCLUIR: System.out.println("EXCLUSÃO");
                        tfdNome.setDisable(true);
                        tfdCPF.setDisable(true);
                        tfdRG.setDisable(true);
                        tfdDataNascimento.setDisable(true);
                        cbxTipoSanguineo.setDisable(true);
                        textObservacoes.setDisable(true);
                        tfdTelefone.setDisable(true);
                        cbxTipoCelular.setDisable(true);
                        //ENDEREÇO
                        tfdEndereco.setDisable(true);
                        tfdNumero.setDisable(true);
                        tfdBairro.setDisable(true);
                        tfdCEP.setDisable(true);
                        //PLANO DE SAÚDE
                        tfdPlanoSaude.setDisable(true);
                        textPSDescricao.setDisable(true);
                        btn_Atualizar.setDisable(true);
                        btn_ExcluirPaciente.setDisable(false);
                break;
        }
    }
    
   
    
    @FXML
    public void excluirPaciente(ActionEvent event){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText("Confirmação de exclusão do Paciente");
        alert.setContentText("Tem certeza que deseja excluir este Paciente");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if( result.get() == ButtonType.OK ){
        PacienteRepositorio pRep = new PacienteRepositorio();
        Paciente pacienteDel = pRep.recuperar(this.paciente.getID());
        try{
            pRep.remover(pacienteDel);
            
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Exclusão");
            AlertErro.setHeaderText("Exclusão do Paciente");
            AlertErro.setContentText("O Paciente foi removido com sucesso");
            AlertErro.showAndWait();
            
            openGerenciarPaciente();
            ColumnTableFindPacientes();
        }catch(DataBaseConstraintException dtce){
            Alert AlertErro = new Alert(Alert.AlertType.ERROR);
            AlertErro.setTitle("Erro de Exclusão");
            AlertErro.setHeaderText("Erro ao Excluir Paciente");
            AlertErro.setContentText("Não é possível excluir ou atualizar uma linha pai: uma restrição de chave estrangeira "+"\nErro: "+dtce);
            AlertErro.showAndWait();
        }catch(PersistenceException  ex){
            System.out.println("Erro de Persistencia: "+ex);
            Alert alertErro2 = new Alert(Alert.AlertType.ERROR);
            alertErro2.setTitle("Erro de Exclusão");
            alertErro2.setHeaderText("Erro ao Excluir Paciente");
            alertErro2.setContentText("Não é possível excluir ou atualizar uma linha pai: uma restrição de chave estrangeira "+"\nErro: "+ex);
            TextArea area = new TextArea(ex.toString());
            alertErro2.getDialogPane().setExpandableContent(area);
            alertErro2.showAndWait();
        }finally{
            pRep.encerrar();
            
        }
        
        }else if(result.get() == ButtonType.CANCEL){
            System.out.println("OPERAÇÃO CANCELADA");
        }
        
    }
    
    @FXML
    public void buttonCancelar(MouseEvent event){
        openGerenciarPaciente();
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
    
    
    
    
    class PacientesFX extends RecursiveTreeObject<PacientesFX> {
        
        StringProperty ID;
        StringProperty nomePaciente;
        StringProperty CPF;
        StringProperty Celular;
        
        public PacientesFX(String ID, String nome, String CPF, String Celular){
            this.ID = new SimpleStringProperty(ID);
            this.nomePaciente = new SimpleStringProperty(nome);
            this.CPF = new SimpleStringProperty(CPF);
            this.Celular = new SimpleStringProperty(Celular);
        }
    }
        
    
}
