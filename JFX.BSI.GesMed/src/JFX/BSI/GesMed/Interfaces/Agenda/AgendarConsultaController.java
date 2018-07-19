/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Repositorios.AgendamentoRepositorio;
import JFX.BSI.GesMed.Repositorios.PacienteRepositorio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class AgendarConsultaController implements Initializable {
    
    @FXML
    private StackPane rootAgendarConsulta;
    
    @FXML
    private JFXComboBox jcbProcedimento;

    @FXML
    private JFXComboBox jcbConvenio;

    @FXML
    private JFXComboBox jcbStatus;

    @FXML
    private JFXDatePicker tfdDataAgenda;

    @FXML
    private JFXTimePicker tfdHrInicio;

    @FXML
    private JFXTimePicker tfdHrFim;

    @FXML
    private JFXButton btnSalvar;
    
    @FXML
    private JFXButton btn_Buscar;

    @FXML
    private JFXButton btnCancelar;
    
    @FXML
    private JFXButton btn_GradeHorario;

    @FXML
    private JFXTextField tfdNome;

    @FXML
    private JFXTextField tfdCPF;

    @FXML
    private JFXTextArea textObservacoes;
    
    private Paciente paciente;
    
    private List<Paciente> listPacientes;
    private List<String> nomesPacientes;
    private List<String> listCPF;
    
    private ObservableList<String> listStatus = FXCollections.observableArrayList("Selecione","Agendado","Confirmado","Chegou","Em Andamento","Finalizado","Cancelado","Faltou");
    private ObservableList<String> listProcedimento = FXCollections.observableArrayList("Selecione","Consulta","Retorno");
    private ObservableList<String> listConvenio = FXCollections.observableArrayList("Selecione","Particular","Cortesia");

    /**
     * Initializes the controller class.
     */

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        inicializarComponentes();
        
        atualizarListPacientes();
        
        String listNomes = nomesPacientes.toString();
        listNomes = listNomes.substring(1,listNomes.length()-1);
        String[] NomesPeview = listNomes.split(",");
        System.out.println(listNomes);
        
        String listCPF = this.listCPF.toString();
        listCPF = listCPF.substring(1,listCPF.length()-1);
        String[] CPFPeview = listCPF.split(",");
        System.out.println(CPFPeview);
        
        TextFields.bindAutoCompletion(tfdNome,NomesPeview);
        TextFields.bindAutoCompletion(tfdCPF,CPFPeview);
        
        tfdNome.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
        @Override
        public void handle(KeyEvent ke)
        {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                ProcurarPacientePorNome();
            }
        }
        });
        tfdCPF.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
              if(event.getCode().equals(KeyCode.ENTER)){
                  ProcurarPacientePorCPF();
              }
            }
        });
    }
    
    public void inicializarComponentes(){
        jcbProcedimento.setItems(listProcedimento);
        jcbProcedimento.setValue("Selecione");
        jcbStatus.setItems(listStatus);
        jcbStatus.setValue("Selecione");
        jcbConvenio.setItems(listConvenio);
        jcbConvenio.setValue("Selecione");
    }
    
    @FXML
    public void atualizarListPacientes(){
        
            listPacientes = new ArrayList<>();
            listCPF = new ArrayList<>();
            PacienteRepositorio pRep = new PacienteRepositorio();
            listPacientes = pRep.recuperarTodos();
            nomesPacientes = new ArrayList<String>();
        for(Paciente p : listPacientes){
           nomesPacientes.add(p.getNome());
           listCPF.add(p.getCPF());
          }
        
    }
    
    @FXML
    public void salvarAgendamento(MouseEvent event){
        
        boolean CamposValidos = false;
        
        boolean CamposValidosComboBox = false;
        ValidationFields validComboBox = new ValidationFields();
        CamposValidosComboBox = validComboBox.checkEmptyFields(jcbConvenio, jcbProcedimento, jcbStatus);
        System.out.println("CamposValidos CheckBox: "+CamposValidosComboBox);
        
//        boolean CamposValidosDatePicker = false;
//        ValidationFields validDatePicker = new ValidationFields();
//        CamposValidosDatePicker = validDatePicker.checkEmptyFields(tfdDataAgenda);
//        System.out.println("CamposValidos DatePicker: "+CamposValidosDatePicker);
//        
//        boolean CamposValidosTimePicker = false;
//        ValidationFields validTimePicker = new ValidationFields();
//        CamposValidosTimePicker = validTimePicker.checkEmptyFields(tfdHrInicio, tfdHrFim);
//        System.out.println("CamposValidos TimePicker: "+CamposValidosDatePicker);
        
        if(CamposValidosComboBox == false){
            CamposValidos = false;
        }else{
            CamposValidos = true;
        }
        AgendamentoRepositorio agenRep = new AgendamentoRepositorio();
        if(CamposValidos==true){
            Agendamento agenda = new Agendamento();
            agenda.setProcedimento(jcbProcedimento.getValue().toString());
            agenda.setStatus(jcbStatus.getValue().toString());
            agenda.setConvenio(jcbConvenio.getValue().toString());
            agenda.setData(setData());
            agenda.setHoraInicio(getHoraInicio());
            agenda.setHoraFim(getHoraFim());
            agenda.setPaciente(paciente);
            
            try{
             agenRep.atualizar(agenda);
              WindowsHide();    
            }catch(Exception exc){
                agenRep.encerrar();
            }finally{
                agenRep.encerrar();
            }
            
        }
    }
    
    public void WindowsHide(){
        Stage currentStage = (Stage) rootAgendarConsulta.getScene().getWindow();
        currentStage.hide();
    }
    
    public void ButtonCancel(MouseEvent event){
        Stage currentStage = (Stage) rootAgendarConsulta.getScene().getWindow();
        currentStage.hide();
    }
    
      public Date setData() {
        LocalDate data = LocalDate.now();
        Date date = new Date();
        
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        
            try {
            date = formatoData.parse(tfdDataAgenda.getValue().toString());
                System.out.println("Data após Converter: "+date.toString());
            } catch (ParseException e) {
            e.printStackTrace();
            }
            return date;
    }
      
    
    public void ProcurarPacientePorNome(){
        
        Paciente paciente = null;
        PacienteRepositorio pRep = new PacienteRepositorio();
        List<Paciente> searchPac = pRep.recuperarPacientesNome(tfdNome.getText().trim());
        if(searchPac.size()==1){
            paciente = searchPac.get(0);
        }
        
        if(paciente!=null)
        System.out.println("NOME DO PACIENTE:"+ paciente.getNome()+" \nCPF" +paciente.getCPF());
            
        if(paciente!=null){
          String CPF = paciente.getCPF();
           tfdCPF.setText(CPF);
           this.paciente = paciente;
        }
        
    }
    
    @FXML
    public void ProcurarPacientePorCPF(){
        
        Paciente paciente = null;
        PacienteRepositorio pRep = new PacienteRepositorio();
        Paciente searchPac = pRep.recuperarPacienteCPF(tfdCPF.getText().trim());
        
        if(paciente!=null)
        System.out.println("NOME DO PACIENTE:"+ paciente.getNome()+" \nCPF" +paciente.getCPF());
            
        if(paciente!=null){
          String NOME = paciente.getNome();
           tfdNome.setText(NOME);
           this.paciente = paciente;
        }else{
            System.err.println("Não encontrado Paciente");
        }
        
    }
    
  
      
    public Date setHora(String Hora) {
    	
        
        Date date = new Date();
        
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm"); 
        try {
			date = formatoHora.parse(Hora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }
     
    public Date getHoraInicio() {
    	
        LocalDate data = LocalDate.now();
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
        try {
          date = formatoHora.parse(tfdHrInicio.getValue().toString());
          System.out.println("HoraInicio após Converter: "+date.toString());
	} catch (ParseException e) {
         e.printStackTrace();
	}
        return date;
    }
    
    
    @FXML
    public void getHora(MouseEvent event){
        LocalTime HoraInicioValue = tfdHrInicio.getValue();
        String HoraInicioString = HoraInicioValue.toString();
        System.out.println("HORA INICIO VALUE: "+HoraInicioValue+ " HORA INICIO[VALUE CONVERTER] STRING: "+HoraInicioString);
        String HoraInicioValueSring = tfdHrInicio.getValue().toString();
        System.out.println("HORA INICIO ValueSring: "+HoraInicioValueSring);
        
        LocalTime HoraInicioValue2 = tfdHrFim.getValue();
        String HoraInicioString2 = HoraInicioValue2.toString();
        System.out.println("HORA FIM VALUE: "+HoraInicioValue2+ " HORA FIM[VALUE CONVERTER] STRING: "+HoraInicioString2);
        String HoraInicioValueSring2 = tfdHrFim.getValue().toString();
        System.out.println("HORA FIM ValueSring: "+HoraInicioValueSring2);
        
        LocalDate data = tfdDataAgenda.getValue();
        
        System.out.println("DATA DE AGENDAMENTO: "+data);
         System.out.println("DATA DE AGENDAMENTO: "+data.toString());
    }
    
    public Date getHoraFim() {
    	
        LocalDate data = LocalDate.now();
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
        try {
          date = formatoHora.parse(tfdHrFim.getValue().toString());
          System.out.println("Data após Converter: "+date.toString());
	} catch (ParseException e) {
         e.printStackTrace();
	}
        return date;
    } 
      
//    @FXML
//    public void salvarAgendamento(MouseEvent event){
//        
//    }
    
    @FXML
    public void tfdCPFKeyRelased(){
        JFX.BSI.GesMed.Interfaces.TextFieldFormatter tff = new JFX.BSI.GesMed.Interfaces.TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdCPF);
        tff.formatter();
    }
    
}
