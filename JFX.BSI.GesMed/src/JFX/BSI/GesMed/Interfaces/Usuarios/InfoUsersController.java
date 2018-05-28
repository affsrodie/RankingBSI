/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Usuarios;

import JFX.BSI.GesMed.Entidades.Atendente;
import JFX.BSI.GesMed.Entidades.Medico;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Repositorios.AtendenteRepositorio;
import JFX.BSI.GesMed.Repositorios.MedicoRepositorio;
import JFX.BSI.GesMed.Repositorios.PacienteRepositorio;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class InfoUsersController implements Initializable {
    @FXML
    private AnchorPane paneInfoMedico;
    
    @FXML
    private AnchorPane paneInfoAtendente;
    
    @FXML
    private HBox groupOptions;

    @FXML
    private Group groupMedico;

    @FXML
    private Label infoCountAtendentes;

    @FXML
    private Group groupAtendente;

    @FXML
    private Label infoCountMedicos;
    
    @FXML
    private Label infoNomeUserMed, infoNomeAtendente;
    
    @FXML
    private Label infoCPFMed, infoCPFAtendente;

    @FXML
    private Label infoRGMed, infoRGAtendente;

    @FXML
    private Label infoBairroMed, infoBairroAtendente;
    
    @FXML
    private Label infoRuaMed, infoRuaAtendente;
    
    @FXML
    private Label infoTelefoneMed, infoTelefoneAtendente;

    @FXML
    private Label infoEspecialidade;
    
    @FXML
    private Label infoDataContratacao;

    @FXML
    private JFXTextField tfdFindUsuarios;

    @FXML
    private JFXComboBox cbxTipoUsers;
    
    ObservableList<String> tipoUser = FXCollections.observableArrayList("SELECIONE","MÉDICO","ATENDENTE");
    private List<Medico> listMedicos;
    private List<Atendente> listAtendentes;
    private List<String> nomesMedicos;
    private List<String> nomesAtendentes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxTipoUsers.setItems(tipoUser);
        cbxTipoUsers.setValue("SELECIONE");
        paneInfoMedico.setVisible(false);
        paneInfoMedico.setVisible(false);
        setRipples();
        atualizarListMedicos();
        atualizarListAtendentes();
        AlterarTipoUserInfo();
        limparInfoUser();
    }
    
    public void limparInfoUser(){
        infoNomeUserMed.setText("");
        infoCPFMed.setText("");
        infoCPFAtendente.setText("");
        infoRGMed.setText("");
        infoRGAtendente.setText("");
        infoDataContratacao.setText("");
        infoEspecialidade.setText("");
        infoBairroAtendente.setText("");
        infoBairroMed.setText("");
        infoRuaMed.setText("");
        infoRuaAtendente.setText("");
        infoTelefoneMed.setText("");
        infoTelefoneAtendente.setText("");
    }
    
    public void AlterarTipoUserInfo(){
        cbxTipoUsers.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String[] NomesUserPeview=null;
                limparInfoUser();
                if(cbxTipoUsers.getSelectionModel().getSelectedItem().equals("MÉDICO")){
                  paneInfoMedico.setVisible(true); 
                  paneInfoAtendente.setVisible(false);
                String listNomesMed = nomesMedicos.toString();
                listNomesMed = listNomesMed.substring(1,listNomesMed.length()-1);
                String[] NomesMedPeview = listNomesMed.split(",");
                NomesUserPeview = NomesMedPeview;  
                }else if(cbxTipoUsers.getSelectionModel().getSelectedItem().equals("ATENDENTE")){
                  paneInfoAtendente.setVisible(true);
                  paneInfoMedico.setVisible(false);
                String listNomesAtend = nomesAtendentes.toString();
                listNomesAtend = listNomesAtend.substring(1,listNomesAtend.length()-1);
                String[] NomesAtendentesPreview = listNomesAtend.split(",");
                NomesUserPeview = NomesAtendentesPreview;
                }
                if(cbxTipoUsers.getSelectionModel().getSelectedIndex()!=0)
                TextFields.bindAutoCompletion(tfdFindUsuarios,NomesUserPeview);
            }
        });
        
        tfdFindUsuarios.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
        @Override
        public void handle(KeyEvent ke)
        {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                 if(cbxTipoUsers.getSelectionModel().getSelectedItem().equals("MÉDICO")){
                 int POSIC = nomesMedicos.indexOf(tfdFindUsuarios.getText().trim());
                 Medico med = null;
                 if(POSIC>=0){
                     med = listMedicos.get(POSIC);
                 }
                 if(med!=null){
                   setInfoMedico(med);  
                 }
                 
                 }else if(cbxTipoUsers.getSelectionModel().getSelectedItem().equals("ATENDENTE")){
                 int POSIC = nomesAtendentes.indexOf(tfdFindUsuarios.getText().trim());
                 Atendente atendente = null;
                 if(POSIC>=0){
                     atendente = listAtendentes.get(POSIC);
                     System.out.println("NOME"+atendente.getNome());
                 }
                 if(atendente!=null){
                    setInfoAtendente(atendente);  
                 } 
                }
            }
        }
        });
    }

     private void setRipples() {
        JFXRippler rippler1 = new JFXRippler(groupAtendente);
        JFXRippler rippler2 = new JFXRippler(groupMedico);
       
        rippler1.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler2.setMaskType(JFXRippler.RipplerMask.RECT);
       
        rippler1.setRipplerFill(Paint.valueOf("#1564C0"));
        rippler2.setRipplerFill(Paint.valueOf("#00AACF"));

        groupOptions.getChildren().addAll(rippler1, rippler2);
    }
     
    @FXML
    public void atualizarListMedicos(){
            listMedicos = new ArrayList<>();
            MedicoRepositorio medRep = new MedicoRepositorio();
            listMedicos = medRep.recuperarTodos();
            nomesMedicos = new ArrayList<String>();
            
            for(Medico med : listMedicos){
             nomesMedicos.add(med.getNome());
            }
         int CountMed = listMedicos.size();
         infoCountMedicos.setText(Integer.toString(CountMed));
    }
    
    @FXML
    public void atualizarListAtendentes(){
            listAtendentes = new ArrayList<>();
            AtendenteRepositorio atenRep = new AtendenteRepositorio();
            listAtendentes = atenRep.recuperarTodos();
            nomesAtendentes = new ArrayList<String>();
            
            for(Atendente atente : listAtendentes){
             nomesAtendentes.add(atente.getNome());
            }
         int CountAtendente = listAtendentes.size();
         infoCountAtendentes.setText(Integer.toString(CountAtendente));
    }
    
    public void setInfoAtendente(Atendente atendente){
        infoNomeAtendente.setText(atendente.getNome());
        infoCPFAtendente.setText(atendente.getCPF());
        infoRGAtendente.setText(atendente.getRG());
        infoBairroAtendente.setText(atendente.getEndereco().getBairro());
        infoRuaAtendente.setText(atendente.getEndereco().getRua());
        infoTelefoneAtendente.setText(atendente.getTelefone().getTelefone());
        infoDataContratacao.setText(atendente.getDataAmissao());
    }
    
    public void setInfoMedico(Medico medico){
        infoNomeUserMed.setText(medico.getNome());
        infoCPFMed.setText(medico.getCPF());
        infoRGMed.setText(medico.getRG());
        infoBairroMed.setText(medico.getEndereco().getBairro());
        infoRuaMed.setText(medico.getEndereco().getRua());
        infoTelefoneMed.setText(medico.getTelefone().getTelefone());
        infoEspecialidade.setText(medico.getEspecializacao().getTitulo());
    }
    
    public void EnablePaneInfo(){
        if(cbxTipoUsers.getSelectionModel().getSelectedItem().equals("MÉDICO")){
          paneInfoMedico.setVisible(true); 
          paneInfoAtendente.setVisible(false);
        }else if(cbxTipoUsers.getSelectionModel().getSelectedItem().equals("ATENDENTE")){
          paneInfoAtendente.setVisible(true);
          paneInfoMedico.setVisible(false);
        }
    }
    
    
}
