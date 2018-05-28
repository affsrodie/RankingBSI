/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;

import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Entidades.Telefone;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class ListContactsController implements Initializable {
    
    @FXML
    private AnchorPane paneContacts;

    @FXML
    private JFXButton btn_AddPhone;

    @FXML
    private Label infoPhonePessoal;

    @FXML
    private Label infoPhoneRecado;

    @FXML
    private Label infoPhoneFixo;

    @FXML
    private Label infoPhoneTrabalho;
    
    
    private Paciente paciente;
    private List<Telefone> listTelefone;

    public ListContactsController() {
    
    }
    
    public ListContactsController(Paciente paciente) {
        this.paciente= paciente;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setInfoPhoneLabel(){
        if(paciente!=null){
            
                if(paciente.getTelefone().getCelular()!=null){
                    infoPhonePessoal.setText(paciente.getTelefone().getCelular());
                }
              
                if(paciente.getTelefone().getTelefone()!=null){
                    infoPhoneRecado.setText(paciente.getTelefone().getTelefone());
                }
                
                if(paciente.getTelefone().getFixo()!=null){
                    infoPhoneFixo.setText(paciente.getTelefone().getFixo());
                }
                
                if(paciente.getTelefone().getTrabalho()!=null){
                    infoPhoneTrabalho.setText(paciente.getTelefone().getTrabalho());
                }
        }
    }
    
}
