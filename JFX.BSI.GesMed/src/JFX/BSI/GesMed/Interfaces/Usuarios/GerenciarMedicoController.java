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
import JFX.BSI.GesMed.Exception.DataBaseConstraintException;
import JFX.BSI.GesMed.Repositorios.EspecialRepositorio;
import JFX.BSI.GesMed.Repositorios.MedicoRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
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
public class GerenciarMedicoController implements Initializable {
    
    @FXML
    private AnchorPane holderPaneEdit, novaEspecialidade, paneDadosMedico, returnDadosMed;
    
    @FXML
    private AnchorPane CadastroEdit;
    
    @FXML
    private JFXButton btn_Excluir;

    @FXML
    private JFXButton btn_Editar;
    
    @FXML
    private JFXButton btn_SalvarEspecialidade;

    @FXML
    private JFXButton btn_CancelarEspecial;
    
    @FXML
    private JFXButton btn_ExcluirMedico;

    @FXML
    private JFXTextField tfdFindNome;

    @FXML
    private JFXComboBox jcbTipoPesquisa;
    

    @FXML
    private JFXComboBox cbxTipoCelular;
    
    @FXML
    private JFXComboBox<String> cbxEspecialidade;
      
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
    private JFXTextField tfdCRM;
    
    @FXML
    private JFXTextField tfdEspecialidade;

    @FXML
    private JFXTextArea textDescricao;


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
    
    @FXML
    private JFXTreeTableView<MedicosFX> tblFindMedicos = new JFXTreeTableView<>();
    
    private List<Medico> listMedicos;
    
    ObservableList<MedicosFX> medicosfx = FXCollections.observableArrayList();
    ObservableList<String> listTipoPesquisa = FXCollections.observableArrayList("NOME","ID","CPF");
    private List<Especializacao> listEspecializacoes;
    private ObservableList<String> listEspecialidadesFX = FXCollections.observableArrayList();
    private ObservableList<String> listTipoTelefone = FXCollections.observableArrayList("Selecione","Celular","Fixo","Trabalho");
   
    private Medico medico;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColumnTableFindMedicos();
        inicializeComponentes();
        
    }

        //MÉTODOS PARA PESQUISA DE PACIENTES PARA EDITAR OU EXCLUIR
    
    @FXML
    public void ColumnTableFindMedicos(){
        
        JFXTreeTableColumn<MedicosFX,String> tblID = new JFXTreeTableColumn<>("ID");
        tblID.setMinWidth(95);
        tblID.setPrefWidth(95);
        tblID.setMaxWidth(100);
        tblID.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<MedicosFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<MedicosFX, String> param) {
                return param.getValue().getValue().ID;
            }
        });
        
        JFXTreeTableColumn<MedicosFX,String> tblNomeMedico = new JFXTreeTableColumn<>("Nome do Médico");
        tblNomeMedico.setMinWidth(285);
        tblNomeMedico.setPrefWidth(300);
        tblNomeMedico.setMaxWidth(1000);
        tblNomeMedico.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<MedicosFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<MedicosFX, String> param) {
                return param.getValue().getValue().nomeMedico;
            }
        });
         
        JFXTreeTableColumn<MedicosFX,String> tblCPF = new JFXTreeTableColumn<>("CPF");
        tblCPF.setMinWidth(200);
        tblCPF.setPrefWidth(240);
        tblCPF.setMaxWidth(350);
        tblCPF.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<MedicosFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<MedicosFX, String> param) {
                return param.getValue().getValue().CPF;
            }
        });
        
        JFXTreeTableColumn<MedicosFX,String> tblTelefone = new JFXTreeTableColumn<>("Telefone");
        tblTelefone.setMinWidth(150);
        tblTelefone.setPrefWidth(150);
        tblTelefone.setMaxWidth(350);
        tblTelefone.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<MedicosFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<MedicosFX, String> param) {
                return param.getValue().getValue().Celular;
            }
        });
        
        MedicoRepositorio medRep = new MedicoRepositorio();
        
        if(listMedicos==null||listMedicos.size()==0){
        listMedicos = medRep.recuperarTodos();
        medRep.encerrar();
        }
        
        MedicosFX medFX;
        medicosfx = FXCollections.observableArrayList();
        if(listMedicos!=null){
            for( Medico medico : listMedicos){
                medFX = new MedicosFX(Integer.toString(medico.getID()), medico.getNome(), medico.getCPF(),medico.getCRM(), medico.getTelefone().getTelefone());
                medicosfx.add(medFX);
            }    
        }
        
        
        final TreeItem<MedicosFX> root = new RecursiveTreeItem<MedicosFX>(medicosfx, RecursiveTreeObject::getChildren);
        tblFindMedicos.getColumns().setAll(tblID, tblNomeMedico, tblCPF, tblTelefone);
        tblFindMedicos.setRoot(root);
        tblFindMedicos.setShowRoot(false);
       
    //PESQUISA DE MEDICOS USANDO O ID, NOME, CPF PASSADO NO TEXTFIELD

   
        
      tfdFindNome.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               
            if(jcbTipoPesquisa.getValue().equals("NOME")){
                 tblFindMedicos.setPredicate(new Predicate<TreeItem<MedicosFX>>() {
                   @Override
                   public boolean test(TreeItem<MedicosFX> medico) {
                      Boolean flag = medico.getValue().nomeMedico.getValue().contains(newValue);
                      return flag;
                   }
               });  
            }
              
            }
        });
    }
    @FXML
    public void pesquisarMedico(MouseEvent event){
        
          if(jcbTipoPesquisa.getValue().toString().equals("ID")){
              MedicoRepositorio pRep = new MedicoRepositorio();
                if(!tfdFindNome.getText().trim().equals("")){
                    medicosfx = null;
                    listMedicos = new ArrayList<Medico>();
                    medico = pRep.recuperar(Integer.parseInt(tfdFindNome.getText().trim()));
                    listMedicos.add(medico);
                    ColumnTableFindMedicos();
                }else{
                    listMedicos = null;
                    listMedicos = pRep.recuperarTodos();
                    ColumnTableFindMedicos();
                }
            }else if(jcbTipoPesquisa.getValue().toString().equals("CPF")){
                    MedicoRepositorio pRep = new MedicoRepositorio();
                    if(!tfdFindNome.getText().trim().equals("")){
                    listMedicos = null;
                    medicosfx = null;
                    listMedicos = new ArrayList<Medico>();
//                    listMedicos = pRep.recuperarCPF(tfdFindNome.getText().trim());
                    ColumnTableFindMedicos();  
                    }else{
                    medicosfx = null;    
                    listMedicos = pRep.recuperarTodos();
                    ColumnTableFindMedicos();
                    }
             }
    }
    
    @FXML
    public void selectMedicoEdit(MouseEvent event){
        TreeItem<MedicosFX> pFX =  tblFindMedicos.getSelectionModel().getSelectedItem();
        MedicosFX medicofx = pFX.getValue();
        
        MedicoRepositorio medRep = new MedicoRepositorio();
        Medico medico = medRep.recuperar(Integer.parseInt(medicofx.ID.getValue()));
        this.medico = medico;
        openEditCadastro(medico, 0);
       
    }
    
    @FXML
    public void selectMedicoExcluir(MouseEvent event){
        TreeItem<MedicosFX> pFX =  tblFindMedicos.getSelectionModel().getSelectedItem();
        MedicosFX medicofx = pFX.getValue();
        
        MedicoRepositorio pacRep = new MedicoRepositorio();
        Medico medico = pacRep.recuperar(Integer.parseInt(medicofx.ID.getValue()));
        this.medico=medico;
        openEditCadastro(medico, 1);
    }
    

    
    private void openEditMedico(){
        
        FXMLLoader managerPac = new FXMLLoader(GerenciarMedicoController.class.getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/openEditMedico.fxml"));
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
    
    @FXML
        public void openGerenciarMed(MouseEvent event){
        openGerenciarMedico();
    }
    
    public void openGerenciarMedico(){
        
        FXMLLoader managerPac = new FXMLLoader(GerenciarMedicoController.class.getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/GerenciarMedico.fxml"));
        
        try{
            CadastroEdit = managerPac.load();
            holderPaneEdit.getChildren().clear();
            holderPaneEdit.getChildren().add((Node) CadastroEdit);
            
            ColumnTableFindMedicos();
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
        
    void openEditCadastro(Medico medico, int Operacao) {
        openEditMedico();
        carregarEspecialidades();
        inicializeComponentesEdit();
        disableCamposCadastro(Operacao);
        setDadosCamposEdit(medico);
    }
    
    public void inicializeComponentes(){
        jcbTipoPesquisa.setItems(listTipoPesquisa);
        jcbTipoPesquisa.setValue("NOME");
    }
    
    public void inicializeComponentesEdit(){
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
        System.out.println("Especialização:" + Especialidade);
        if(!cbxEspecialidade.getSelectionModel().equals("Selecione")){
        Especializacao especial = listEspecializacoes.get(cbxEspecialidade.getSelectionModel().getSelectedIndex()-1);
        System.out.println("Especilidade por ArrayList: "+ especial.getTitulo() + " \n Descrição: "+especial.getDescricao());
        return especial;
        }
        return null;
    }
 
    
    @FXML
    public void EnnableButtonsMouse(MouseEvent event){
        if(tblFindMedicos.getSelectionModel().getSelectedIndex()>=0){
            btn_Editar.setDisable(false);
            btn_Excluir.setDisable(false);
        }else{
            btn_Editar.setDisable(false);
            btn_Excluir.setDisable(false);
        }
    }
    
    @FXML
    public void DissableButtons(MouseEvent event){
            btn_Editar.setDisable(true);
            btn_Excluir.setDisable(true);
            tblFindMedicos.getSelectionModel().clearSelection();
    }
    
    
    
    //MÉTODOS PARA EDIÇÃO E EXCLUSÃO DE PACIENTES REFERENTE AO XML OPENPACIENTE.XML
    
            
    public boolean ValidarCampos(){
        
        boolean CamposValidos = true;
        
        ValidationFields validTextField = new ValidationFields();
        boolean CamposValidosTextFild = false;
        CamposValidosTextFild = validTextField.checkEmptyFields(
                tfdNome, tfdRG, tfdCPF, tfdTelefone, tfdEndereco, 
                tfdBairro, tfdCEP);
        System.out.println("CamposValidos TextField: "+CamposValidosTextFild);
        
        boolean CamposValidosTextArea = false;
        ValidationFields validTextArea = new ValidationFields();
        CamposValidosTextArea = validTextArea.checkEmptyFields(textDescricao);
        System.out.println("CamposValidos TextArea: "+CamposValidosTextArea);
        
        boolean CamposValidosComboBox = false;
        ValidationFields validComboBox = new ValidationFields();
        CamposValidosComboBox = validComboBox.checkEmptyFields(cbxEspecialidade, cbxTipoCelular);
        System.out.println("CamposValidos CheckBox: "+CamposValidosComboBox);
        
        System.out.println("CamposValidos antes de verificar: "+CamposValidos);
        if(CamposValidosTextFild==false || CamposValidosTextArea==false || CamposValidosComboBox==false ){
            CamposValidos = false; 
        }
        System.out.println("CamposValidos após de verificar: "+CamposValidos);
        return CamposValidos;
    }
    
    

    public void setDadosCamposEdit(Medico medico){
        
        MedicoRepositorio medRep = new MedicoRepositorio();
        this.medico = medico;
        if(this.medico!=null){
         tfdNome.setText(this.medico.getNome());
         tfdRG.setText(this.medico.getRG());
         tfdCPF.setText(this.medico.getCPF());
         tfdTelefone.setText(this.medico.getTelefone().getTelefone());
         tfdEndereco.setText(this.medico.getEndereco().getRua());
         tfdBairro.setText(this.medico.getEndereco().getBairro());
         tfdNumero.setText(this.medico.getEndereco().getNumero());
         tfdCEP.setText(this.medico.getEndereco().getCEP());
         tfdCRM.setText(this.medico.getCRM());
         cbxEspecialidade.setValue(this.medico.getEspecializacao().getTitulo());
         tfdDataNascimento.setValue(LocalDate.parse(this.medico.getDataNasc()));
         tfdTelefone.setText(this.medico.getTelefone().getCelular());
         cbxTipoCelular.setValue("Celular");
        }
    }
 
    
    @FXML    
    public void atualizarMedico(ActionEvent event){
      
    if(ValidarCampos()==true){
        
        MedicoRepositorio pr = new MedicoRepositorio();
        
        medico.setNome(tfdNome.getText());
        medico.setCPF(tfdCPF.getText());
        medico.setRG(tfdRG.getText());
        medico.setDataNasc(tfdDataNascimento.getValue().toString());
        medico.setCRM(tfdCRM.getText());
        medico.setEspecializacao(getEspecialidade());
        
        Telefone t = new Telefone();
        if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Celular")){
            t.setCelular(tfdTelefone.getText());
        }else if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Fixo")){
            t.setFixo(tfdTelefone.getText());
        }else if(cbxTipoCelular.getSelectionModel().getSelectedItem().equals("Trabalho")){
            t.setTrabalho(tfdTelefone.getText());
        }
        
// 
        medico.setTelefone(t);
        
        Endereco end = new Endereco();
        end.setRua(tfdEndereco.getText());
        end.setNumero(tfdNumero.getText());
        end.setBairro(tfdBairro.getText());
        end.setCEP(tfdCEP.getText());
        medico.setEndereco(end);
        
              
        try{
        pr.atualizar(medico);
        
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Atualização");
            AlertErro.setHeaderText("Atualização de Médico");
            AlertErro.setContentText("O Médico foi atualizado com sucesso");
            AlertErro.showAndWait();
            openGerenciarMedico();
            ColumnTableFindMedicos();
        }catch(Exception exc){
            exc.printStackTrace();
        }finally{
         pr.encerrar();
        
         
        }
      }  
    }
           

    public void limparCamposCadastro(){
        //DADOS DO MÉDICO
        tfdNome.setText("");
        tfdCPF.setText("");
        tfdRG.setText("");
        tfdDataNascimento.setPromptText(" ");
        cbxEspecialidade.setValue("Selecione");
        tfdTelefone.setText("");
        cbxTipoCelular.setValue("Selecione");
        //ENDEREÇO
        tfdEndereco.setText("");
        tfdNumero.setText("");
        tfdBairro.setText("");
        tfdCEP.setText("");
        //DADOS DO MÉDICO
        tfdCRM.setText("");
        passSenha.setText("");
        passSenhaConfirma.setText("");
        
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
                        tfdTelefone.setDisable(false);
                        cbxTipoCelular.setDisable(false);
                        //ENDEREÇO
                        tfdEndereco.setDisable(false);
                        tfdNumero.setDisable(false);
                        tfdBairro.setDisable(false);
                        tfdCEP.setDisable(false);
                        //DADOS DO MEDICO
                        cbxEspecialidade.setDisable(false);
                        tfdCRM.setDisable(false);
                        passSenha.setDisable(false);
                        passSenhaConfirma.setDisable(false);
                        tfdDataNascimento.setDisable(true);
                        btn_Limpar.setDisable(true);
                        btn_ExcluirMedico.setDisable(true);
                        
                break;
                
            case EXCLUIR: System.out.println("EXCLUSÃO");
                        tfdNome.setDisable(true);
                        tfdCPF.setDisable(true);
                        tfdRG.setDisable(true);
                        tfdDataNascimento.setDisable(true);
                        tfdTelefone.setDisable(true);
                        cbxTipoCelular.setDisable(true);
                        
                        //ENDEREÇO
                        tfdEndereco.setDisable(true);
                        tfdNumero.setDisable(true);
                        tfdBairro.setDisable(true);
                        tfdCEP.setDisable(true);
                        //DADOS DO MEDICO
                        tfdCRM.setDisable(true);
                        cbxEspecialidade.setDisable(true);
                        passSenha.setDisable(true);
                        passSenhaConfirma.setDisable(true);
                        btn_Salvar.setDisable(true);
                        
                break;
        }
    }
    
   
    
    @FXML
    public void excluirMedico(ActionEvent event){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText("Confirmação de exclusão do Médico");
        alert.setContentText("Tem certeza que deseja excluir este Médico");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if( result.get() == ButtonType.OK ){
        MedicoRepositorio medRep = new MedicoRepositorio();
        Medico medicoDel = medRep.recuperar(this.medico.getID());
        try{
            medRep.remover(medicoDel);
            
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Exclusão");
            AlertErro.setHeaderText("Exclusão do Médico");
            AlertErro.setContentText("O Médico foi removido com sucesso");
            AlertErro.showAndWait();
            
            openGerenciarMedico();
            ColumnTableFindMedicos();
        }catch(DataBaseConstraintException dtce){
            Alert AlertErro = new Alert(Alert.AlertType.ERROR);
            AlertErro.setTitle("Erro de Exclusão");
            AlertErro.setHeaderText("Erro ao Excluir Médico");
            AlertErro.setContentText("Não é possível excluir ou atualizar uma linha pai: uma restrição de chave estrangeira "+"\nErro: "+dtce);
            AlertErro.showAndWait();
        }catch(PersistenceException  ex){
            System.out.println("Erro de Persistencia: "+ex);
            Alert alertErro2 = new Alert(Alert.AlertType.ERROR);
            alertErro2.setTitle("Erro de Exclusão");
            alertErro2.setHeaderText("Erro ao Excluir Médico");
            alertErro2.setContentText("Não é possível excluir ou atualizar uma linha pai: uma restrição de chave estrangeira "+"\nErro: "+ex);
            TextArea area = new TextArea(ex.toString());
            alertErro2.getDialogPane().setExpandableContent(area);
            alertErro2.showAndWait();
        }finally{
            medRep.encerrar();
            
        }
        
        }else if(result.get() == ButtonType.CANCEL){
            System.out.println("OPERAÇÃO CANCELADA");
        }
        
    }
    
    @FXML
    public void buttonCancelar(MouseEvent event){
        openGerenciarMedico();
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
    
    @FXML
    public void tfdCRMKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##########");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdCRM);
        tff.formatter();
    }
    
        
    @FXML
    private void openDadosMedico(){
        
        FXMLLoader cadastroEspecial = new FXMLLoader(CadastrarMedicoController.class.getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/returnDadosMedico.fxml"));
        cadastroEspecial.setController(this);
        
        try{
            returnDadosMed = cadastroEspecial.load();
            paneDadosMedico.getChildren().clear();
            paneDadosMedico.getChildren().add((Node) returnDadosMed);
            
           
            FadeTransition ft = new FadeTransition(Duration.millis(200));
            ft.setNode(returnDadosMed);
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
        
         openGerenciarMedico();
        }
        
    }
    
    
    class MedicosFX extends RecursiveTreeObject<MedicosFX> {
        
        StringProperty ID;
        StringProperty nomeMedico;
        StringProperty CPF;
        StringProperty CRM;
        StringProperty Celular;
        
        public MedicosFX(String ID, String nome, String CPF, String CRM, String Celular){
            this.ID = new SimpleStringProperty(ID);
            this.nomeMedico = new SimpleStringProperty(nome);
            this.CPF = new SimpleStringProperty(CPF);
            this.CRM = new SimpleStringProperty(CRM);
            this.Celular = new SimpleStringProperty(Celular);
        }
    }
        
    
}
