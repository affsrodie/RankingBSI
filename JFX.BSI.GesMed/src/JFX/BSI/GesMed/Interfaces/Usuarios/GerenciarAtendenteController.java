/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Usuarios;

import JFX.BSI.GesMed.Entidades.Atendente;
import JFX.BSI.GesMed.Entidades.Endereco;
import JFX.BSI.GesMed.Entidades.Telefone;
import JFX.BSI.GesMed.Exception.DataBaseConstraintException;
import JFX.BSI.GesMed.Repositorios.AtendenteRepositorio;
import JFX.BSI.GesMed.Repositorios.EspecialRepositorio;
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
public class GerenciarAtendenteController implements Initializable {
    
    @FXML
    private AnchorPane holderPaneEdit, novaEspecialidade, paneDadosMedico, returnDadosMed;
    
    @FXML
    private AnchorPane CadastroEdit;
    
    @FXML
    private JFXButton btn_Excluir;

    @FXML
    private JFXButton btn_Editar;
    

    @FXML
    private JFXButton btn_ExcluirAtendente;

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
    private JFXDatePicker tfdDataContratacao;

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
    private JFXTreeTableView<AtendenteFX> tblFindAtendente = new JFXTreeTableView<>();
    
    private List<Atendente> listAtendentes;
    
    ObservableList<AtendenteFX> atendentefx=  FXCollections.observableArrayList();
    ObservableList<String> listTipoPesquisa = FXCollections.observableArrayList("NOME","ID","CPF");
    
    private ObservableList<String> listTipoTelefone = FXCollections.observableArrayList("Selecione","Celular","Fixo","Trabalho");
   
    private Atendente atendente;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColumnTableFindAtendente();
        inicializeComponentes();
        
    }

        //MÉTODOS PARA PESQUISA DE PACIENTES PARA EDITAR OU EXCLUIR
    
    @FXML
    public void ColumnTableFindAtendente(){
        
        JFXTreeTableColumn<AtendenteFX,String> tblID = new JFXTreeTableColumn<>("ID");
        tblID.setMinWidth(95);
        tblID.setPrefWidth(95);
        tblID.setMaxWidth(100);
        tblID.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AtendenteFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AtendenteFX, String> param) {
                return param.getValue().getValue().ID;
            }
        });
        
        JFXTreeTableColumn<AtendenteFX,String> tblNomeAtendente = new JFXTreeTableColumn<>("Nome do Atendente");
        tblNomeAtendente.setMinWidth(285);
        tblNomeAtendente.setPrefWidth(300);
        tblNomeAtendente.setMaxWidth(1000);
        tblNomeAtendente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AtendenteFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AtendenteFX, String> param) {
                return param.getValue().getValue().nomeAtendente;
            }
        });
         
        JFXTreeTableColumn<AtendenteFX,String> tblCPF = new JFXTreeTableColumn<>("CPF");
        tblCPF.setMinWidth(200);
        tblCPF.setPrefWidth(240);
        tblCPF.setMaxWidth(350);
        tblCPF.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AtendenteFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AtendenteFX, String> param) {
                return param.getValue().getValue().CPF;
            }
        });
        
        JFXTreeTableColumn<AtendenteFX,String> tblTelefone = new JFXTreeTableColumn<>("Telefone");
        tblTelefone.setMinWidth(150);
        tblTelefone.setPrefWidth(150);
        tblTelefone.setMaxWidth(350);
        tblTelefone.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<AtendenteFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<AtendenteFX, String> param) {
                return param.getValue().getValue().Celular;
            }
        });
        
        AtendenteRepositorio medRep = new AtendenteRepositorio();
        
        if(listAtendentes==null||listAtendentes.size()==0){
        listAtendentes = medRep.recuperarTodos();
        medRep.encerrar();
        }
        
        AtendenteFX medFX;
        atendentefx = FXCollections.observableArrayList();
        if(listAtendentes!=null){
            for( Atendente atendente : listAtendentes){
                medFX = new AtendenteFX(Integer.toString(atendente.getID()), atendente.getNome(), atendente.getCPF(), atendente.getDataAmissao(), atendente.getTelefone().getTelefone());
                atendentefx.add(medFX);
            }    
        }
        
        
        final TreeItem<AtendenteFX> root = new RecursiveTreeItem<AtendenteFX>(atendentefx, RecursiveTreeObject::getChildren);
        tblFindAtendente.getColumns().setAll(tblID, tblNomeAtendente, tblCPF, tblTelefone);
        tblFindAtendente.setRoot(root);
        tblFindAtendente.setShowRoot(false);
       
    //PESQUISA DE MEDICOS USANDO O ID, NOME, CPF PASSADO NO TEXTFIELD

   
        
      tfdFindNome.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               
            if(jcbTipoPesquisa.getValue().equals("NOME")){
                 tblFindAtendente.setPredicate(new Predicate<TreeItem<AtendenteFX>>() {
                   @Override
                   public boolean test(TreeItem<AtendenteFX> medico) {
                      Boolean flag = medico.getValue().nomeAtendente.getValue().contains(newValue);
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
              AtendenteRepositorio atenRep = new AtendenteRepositorio();
                if(!tfdFindNome.getText().trim().equals("")){
                    atendentefx = null;
                    listAtendentes = new ArrayList<Atendente>();
                    atendente = atenRep.recuperar(Integer.parseInt(tfdFindNome.getText().trim()));
                    listAtendentes.add(atendente);
                    ColumnTableFindAtendente();
                }else{
                    listAtendentes = null;
                    listAtendentes = atenRep.recuperarTodos();
                    ColumnTableFindAtendente();
                }
            }else if(jcbTipoPesquisa.getValue().toString().equals("CPF")){
                    AtendenteRepositorio atenRep = new AtendenteRepositorio();
                    if(!tfdFindNome.getText().trim().equals("")){
                    listAtendentes = null;
                    atendentefx = null;
                    listAtendentes = new ArrayList<Atendente>();
//                    listMedicos = pRep.recuperarCPF(tfdFindNome.getText().trim());
                    ColumnTableFindAtendente();  
                    }else{
                    atendentefx = null;    
                    listAtendentes = atenRep.recuperarTodos();
                    ColumnTableFindAtendente();
                    }
             }
    }
    
    @FXML
    public void selectAtendenteEdit(MouseEvent event){
        TreeItem<AtendenteFX> pFX =  tblFindAtendente.getSelectionModel().getSelectedItem();
        AtendenteFX atendenteFX = pFX.getValue();
        
        AtendenteRepositorio atenRep = new AtendenteRepositorio();
        Atendente atendente = atenRep.recuperar(Integer.parseInt(atendenteFX.ID.getValue()));
        this.atendente = atendente;
        openEditCadastro(atendente, 0);
       
    }
    
    @FXML
    public void selectAtendenteExcluir(MouseEvent event){
        TreeItem<AtendenteFX> pFX =  tblFindAtendente.getSelectionModel().getSelectedItem();
        AtendenteFX medicofx = pFX.getValue();
        
        AtendenteRepositorio atenRep = new AtendenteRepositorio();
        Atendente atendente = atenRep.recuperar(Integer.parseInt(medicofx.ID.getValue()));
        this.atendente=atendente;
        openEditCadastro(atendente, 1);
    }
    

    
    private void openEditAtendente(){
        
        FXMLLoader managerPac = new FXMLLoader(GerenciarMedicoController.class.getResource("/JFX/BSI/GesMed/Interfaces/Usuarios/openEditAtendente.fxml"));
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
            
            ColumnTableFindAtendente();
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
        
    void openEditCadastro(Atendente atendente, int Operacao) {
        openEditAtendente();
        inicializeComponentesEdit();
        disableCamposCadastro(Operacao);
        setDadosCamposEdit(atendente);
    }
    
    public void inicializeComponentes(){
        jcbTipoPesquisa.setItems(listTipoPesquisa);
        jcbTipoPesquisa.setValue("NOME");
    }
    
    public void inicializeComponentesEdit(){
        cbxTipoCelular.setItems(listTipoTelefone);
        cbxTipoCelular.setValue("Selecione");
    }
    
 
    @FXML
    public void EnnableButtonsMouse(MouseEvent event){
        if(tblFindAtendente.getSelectionModel().getSelectedIndex()>=0){
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
            tblFindAtendente.getSelectionModel().clearSelection();
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
        CamposValidosComboBox = validComboBox.checkEmptyFields(cbxEspecialidade, cbxTipoCelular);
        System.out.println("CamposValidos CheckBox: "+CamposValidosComboBox);
        
        System.out.println("CamposValidos antes de verificar: "+CamposValidos);
        if(CamposValidosTextFild==false || CamposValidosComboBox==false ){
            CamposValidos = false; 
        }
        System.out.println("CamposValidos após de verificar: "+CamposValidos);
        return CamposValidos;
    }
    
    

    public void setDadosCamposEdit(Atendente atendente){
        
        AtendenteRepositorio medRep = new AtendenteRepositorio();
        this.atendente = atendente;
        if(this.atendente!=null){
         tfdNome.setText(this.atendente.getNome());
         tfdRG.setText(this.atendente.getRG());
         tfdCPF.setText(this.atendente.getCPF());
         tfdTelefone.setText(this.atendente.getTelefone().getTelefone());
         tfdEndereco.setText(this.atendente.getEndereco().getRua());
         tfdBairro.setText(this.atendente.getEndereco().getBairro());
         tfdNumero.setText(this.atendente.getEndereco().getNumero());
         tfdCEP.setText(this.atendente.getEndereco().getCEP());
//         tfdDataNascimento.setValue(LocalDate.parse(this.medico.getDataNasc()));
         
        }
    }
 
    
    @FXML    
    public void atualizarMedico(ActionEvent event){
      
    if(ValidarCampos()==true){
        
        AtendenteRepositorio atenRep = new AtendenteRepositorio();
        
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
        
        if(tfdDataContratacao.getValue()!=null){
            atendente.setDataAmissao(tfdDataContratacao.getValue().toString());
        }
            
        try{
        atenRep.atualizar(atendente);
        
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Atualização");
            AlertErro.setHeaderText("Atualização de Atendente");
            AlertErro.setContentText("O Atendente foi atualizado com sucesso");
            AlertErro.showAndWait();
            openGerenciarMedico();
            ColumnTableFindAtendente();
        }catch(Exception exc){
            exc.printStackTrace();
        }finally{
         atenRep.encerrar();
        
         
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
        //DADOS DO ATENDENTE
        tfdDataContratacao.setValue(null);
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
                        tfdDataNascimento.setDisable(true);
                        tfdTelefone.setDisable(false);
                        cbxTipoCelular.setDisable(false);
                        //ENDEREÇO
                        tfdEndereco.setDisable(false);
                        tfdNumero.setDisable(false);
                        tfdBairro.setDisable(false);
                        tfdCEP.setDisable(false);
                        //DADOS DO MEDICO
                        
                        tfdDataContratacao.setDisable(true);
                        passSenha.setDisable(false);
                        passSenhaConfirma.setDisable(false);
                        btn_Limpar.setDisable(true);
                        btn_ExcluirAtendente.setDisable(true);
                        
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
                        //DADOS DO ATENDENTE
                       
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
        alert.setHeaderText("Confirmação de exclusão do Atendente");
        alert.setContentText("Tem certeza que deseja excluir este Atendente");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if( result.get() == ButtonType.OK ){
        AtendenteRepositorio atenRep = new AtendenteRepositorio();
        Atendente atendenteDel = atenRep.recuperar(this.atendente.getID());
        try{
            atenRep.remover(atendenteDel);
            
            Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
            AlertErro.setTitle("Confirmação de Exclusão");
            AlertErro.setHeaderText("Exclusão de Atendente");
            AlertErro.setContentText("O Atendente foi removido com sucesso");
            AlertErro.showAndWait();
            
            openGerenciarMedico();
            ColumnTableFindAtendente();
        }catch(DataBaseConstraintException dtce){
            Alert AlertErro = new Alert(Alert.AlertType.ERROR);
            AlertErro.setTitle("Erro de Exclusão");
            AlertErro.setHeaderText("Erro ao Excluir Atendente");
            AlertErro.setContentText("Não é possível excluir ou atualizar uma linha pai: uma restrição de chave estrangeira "+"\nErro: "+dtce);
            AlertErro.showAndWait();
        }catch(PersistenceException  ex){
            System.out.println("Erro de Persistencia: "+ex);
            Alert alertErro2 = new Alert(Alert.AlertType.ERROR);
            alertErro2.setTitle("Erro de Exclusão");
            alertErro2.setHeaderText("Erro ao Excluir Atendente");
            alertErro2.setContentText("Não é possível excluir ou atualizar uma linha pai: uma restrição de chave estrangeira "+"\nErro: "+ex);
            TextArea area = new TextArea(ex.toString());
            alertErro2.getDialogPane().setExpandableContent(area);
            alertErro2.showAndWait();
        }finally{
            atenRep.encerrar();
            
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
    
           
    
    class AtendenteFX extends RecursiveTreeObject<AtendenteFX> {
        
        StringProperty ID;
        StringProperty nomeAtendente;
        StringProperty CPF;
        StringProperty DataContratacao;
        StringProperty Celular;
        
        public AtendenteFX(String ID, String nome, String CPF, String Data, String Celular){
            this.ID = new SimpleStringProperty(ID);
            this.nomeAtendente = new SimpleStringProperty(nome);
            this.CPF = new SimpleStringProperty(CPF);
            this.DataContratacao = new SimpleStringProperty(Data);
            this.Celular = new SimpleStringProperty(Celular);
        }
    }
        
    
}
