/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Paciente;

import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Repositorios.PacienteRepositorio;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class VisualizarPacientesController implements Initializable {

    
    @FXML
    private JFXTreeTableView<PacientesFX> tblPacientesPreview;

    @FXML
    private JFXTextField tfdFindPacienteTree;

    @FXML
    private AnchorPane tabPacientes;

    @FXML
    private Label infoCPF;

    @FXML
    private Label infoRG;

    @FXML
    private Label infoTelefone;

    @FXML
    private Label infoObservacoes;

    @FXML
    private Label infoTipoSanguineo;

    @FXML
    private Label infoPlanoSaude;

    @FXML
    private Label infoDataNascimento;

    @FXML
    private Label infoEndereco;

    @FXML
    private Label infoNomePaciente;

    ObservableList<PacientesFX> pacientesfx = FXCollections.observableArrayList();
    
    private List<Paciente> listPacientes;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ColumnTablePacientes();
    }
    
        @FXML
    public void ColumnTablePacientes(){
        
        JFXTreeTableColumn<PacientesFX,String> tblID = new JFXTreeTableColumn<>("ID");
        tblID.setMinWidth(35);
        tblID.setPrefWidth(60);
        tblID.setMaxWidth(40);
        tblID.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<PacientesFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<PacientesFX, String> param) {
                return param.getValue().getValue().ID;
            }
        });
        
        JFXTreeTableColumn<PacientesFX,String> tblNomePaciente = new JFXTreeTableColumn<>("Nome do Paciente");
        tblNomePaciente.setMinWidth(255);
        tblNomePaciente.setPrefWidth(300);
        tblNomePaciente.setMaxWidth(500);
        tblNomePaciente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<PacientesFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<PacientesFX, String> param) {
                return param.getValue().getValue().nomePaciente;
            }
        });
         
        JFXTreeTableColumn<PacientesFX,String> tblCPF = new JFXTreeTableColumn<>("CPF");
        tblCPF.setMinWidth(130);
        tblCPF.setPrefWidth(140);
        tblCPF.setMaxWidth(200);
        tblCPF.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<PacientesFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<PacientesFX, String> param) {
                return param.getValue().getValue().CPF;
            }
        });
        
        
        PacienteRepositorio pacRep = new PacienteRepositorio();
        
        if(listPacientes==null||listPacientes.size()==0){
        listPacientes = pacRep.recuperarTodos();
        pacRep.encerrar();
        }
        
        PacientesFX pacFX;
        
        if(listPacientes!=null){
            for( Paciente paciente : listPacientes){
                pacFX = new PacientesFX(Integer.toString(paciente.getID()), paciente.getNome(), paciente.getCPF(), paciente.getTelefone().getTelefone());
                pacientesfx.add(pacFX);
            }    
        }
        
        final TreeItem<PacientesFX> root = new RecursiveTreeItem<PacientesFX>(pacientesfx, RecursiveTreeObject::getChildren);
        tblPacientesPreview.getColumns().setAll(tblID, tblNomePaciente, tblCPF);
        tblPacientesPreview.setRoot(root);
        tblPacientesPreview.setShowRoot(false);
       
    //PESQUISA DE PACIENTES USANDO O NOME PASSADO NO TEXTFIELD
    
        tfdFindPacienteTree.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               tblPacientesPreview.setPredicate(new Predicate<TreeItem<PacientesFX>>() {
                   @Override
                   public boolean test(TreeItem<PacientesFX> paciente) {
                      Boolean flag = paciente.getValue().nomePaciente.getValue().contains(newValue);
                      return flag;
                   }
               });
            }
        });
        
    }
    
    @FXML
    public void selectRowTable(MouseEvent event){
        TreeItem<PacientesFX> pFX =  tblPacientesPreview.getSelectionModel().getSelectedItem();
        PacientesFX paciente = pFX.getValue();
        infoPacienteSelectRow(paciente.ID.getValue());
    }
    
    //MÉTODO PARA LEVAR INFORMAÇÕES DO PACIENTE AO PAINEL DE INFORMAÇÕES NA GUIA DE PACIENTES
    
    public void infoPacienteSelectRow(String ID){
        PacienteRepositorio pRep = new PacienteRepositorio();
        Paciente p = pRep.recuperar(Integer.parseInt(ID));
        if(p!=null){
            infoNomePaciente.setText(p.getNome()); 
            infoRG.setText(p.getRG()); 
            infoCPF.setText(p.getCPF()); 
            infoDataNascimento.setText(p.getDataNasc()); 
            infoTelefone.setText(p.getTelefone().getTelefone()); 
            infoObservacoes.setText(p.getObservacao()); 
            infoTipoSanguineo.setText(p.getTipoSangue()); 
            infoEndereco.setText(p.getEndereco().getRua()+", Nº " + p.getEndereco().getNumero() + " \n "+ p.getEndereco().getBairro() +" CEP: "+p.getEndereco().getCEP()); 
            infoPlanoSaude.setText(p.getPlanoSaude().getTitulo()+" \n"+ p.getPlanoSaude().getDescricao());
        }
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
