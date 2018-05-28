/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Caixa;

import JFX.BSI.GesMed.Entidades.Conta;
import JFX.BSI.GesMed.Interfaces.LoginController;
import JFX.BSI.GesMed.Repositorios.ContaRepositorio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javax.persistence.PersistenceException;


/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class RelatorioCaixaController implements Initializable {

    @FXML
    private StackPane StackPaneRoot;
    
    @FXML
    private StackPane StackPaneCaixa;

    @FXML
    private JFXButton btnJAN_IO;

    @FXML
    private JFXButton btnFEV_IO;

    @FXML
    private JFXButton btnMAR_IO;

    @FXML
    private JFXButton btnABR_IO;

    @FXML
    private JFXButton btnMAI_IO;

    @FXML
    private JFXButton btnJUN_IO;

    @FXML
    private JFXButton btnJUL_IO;

    @FXML
    private JFXButton btnAGO_IO;

    @FXML
    private JFXButton btnSET_IO;

    @FXML
    private JFXButton btnOUT_IO;

    @FXML
    private JFXButton btnNOV_IO;

    @FXML
    private JFXButton btnDEZ_IO;

    @FXML
    private JFXTreeTableView<ContasFX> tblEntradaSaida;

    @FXML
    private JFXButton btn_GerarRelatorIO;

    @FXML
    private JFXButton btn_VoltarCaixaIO;

    @FXML
    private DatePicker tfdDataRelatorioIO;

    @FXML
    private JFXButton btn_UpdateIO;

    @FXML
    private ToggleButton btn_DIA_ES;

    @FXML
    private ToggleButton btn_MES_ES;

    @FXML
    private ToggleButton btn_ANO_ES;

    @FXML
    private JFXButton btnJAN_I;

    @FXML
    private JFXButton btnFEV_I;

    @FXML
    private JFXButton btnMAR_I;

    @FXML
    private JFXButton btnABR_I;

    @FXML
    private JFXButton btnMAI_I;

    @FXML
    private JFXButton btnJUN_I;

    @FXML
    private JFXButton btnJUL_I;

    @FXML
    private JFXButton btnAGO_I;

    @FXML
    private JFXButton btnSET_I;

    @FXML
    private JFXButton btnOUT_I;

    @FXML
    private JFXButton btnNOV_I;

    @FXML
    private JFXButton btnDEZ_I;

    @FXML
    private JFXTreeTableView<ContasFX> tblEntrada;

    @FXML
    private JFXButton btn_GerarRelatorI;

    @FXML
    private JFXButton btn_VoltarCaixaI;

    @FXML
    private DatePicker tfdDataRelatorioI;

    @FXML
    private JFXButton btn_UpdateI;

    @FXML
    private ToggleButton btn_DIA_E;

    @FXML
    private ToggleButton btn_MES_E;

    @FXML
    private ToggleButton btn_ANO_E;

    @FXML
    private Tab btn_GerarRelatorO;

    @FXML
    private JFXButton btnJAN_O;

    @FXML
    private JFXButton btnFEV_O;

    @FXML
    private JFXButton btnMAR_O;

    @FXML
    private JFXButton btnABR_O;

    @FXML
    private JFXButton btnMAI_O;

    @FXML
    private JFXButton btnJUN_O;

    @FXML
    private JFXButton btnJUL_O;

    @FXML
    private JFXButton btnAGO_O;

    @FXML
    private JFXButton btnSET_O;

    @FXML
    private JFXButton btnOUT_O;

    @FXML
    private JFXButton btnNOV_O;

    @FXML
    private JFXButton btnDEZ_O;

    @FXML
    private JFXTreeTableView<ContasFX> tblSaida;

    @FXML
    private JFXButton btn_VoltarCaixaO;

    @FXML
    private DatePicker tfdDataRelatorioO;

    @FXML
    private JFXButton btn_UpdateO;

    @FXML
    private ToggleButton btn_DIA_S;

    @FXML
    private ToggleButton btn_MES_S;

    @FXML
    private ToggleButton btn_ANO_S;
    
    @FXML
    private Group groupEntradaSaida;
    
    @FXML
    private Group groupEntrada;
    
    @FXML
    private Group groupSaida;
    
    private ObservableList<ContasFX> listContasES = FXCollections.observableArrayList();
    private ObservableList<ContasFX> listContasE = FXCollections.observableArrayList();
    private ObservableList<ContasFX> listContasS = FXCollections.observableArrayList();
    private List<Conta> listContaIO;
    private List<Conta> listContaI;
    private List<Conta> listContaO;
    private final int MES=-1;
    private final int ANO=0;
    private final int DIA=1;
    private int TYPE_QUERY_ES=ANO;
    private int TYPE_QUERY_E=ANO;
    private int TYPE_QUERY_S=ANO;
    
    private static Paragraph paragrafo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_ANO_ES.setSelected(true);
        TYPE_QUERY_ES=ANO;
        TYPE_QUERY_E=ANO;
        TYPE_QUERY_S=ANO;
        btn_ANO_E.setSelected(true);
        btn_ANO_S.setSelected(true);
        
        LocalDate dataHoje = LocalDate.now();
        tfdDataRelatorioIO.setValue(dataHoje);
        tfdDataRelatorioI.setValue(dataHoje);
        tfdDataRelatorioO.setValue(dataHoje);
        SelectTypeQueryRelatorio();
    }    
    
    @FXML
    public void CarregarListFX(){
        RecarregarArrayListQuery();
        ContaRepositorio contaRep = new ContaRepositorio();
        LocalDate DataQueryIO = null;
        
        if(tfdDataRelatorioIO.getValue()!=null){
           DataQueryIO = tfdDataRelatorioIO.getValue(); 
           System.out.println("DATA CONVERTIDA (CarregarListFX): "+DataQueryIO.toString());
        }
        
        if(listContaIO==null){
            listContaIO = new ArrayList<Conta>();
            LocalDate dataSearch = null;
            try {
                dataSearch= DataQueryIO;
            //CONSULTA É CONFORME A ESCOLHA DO TIPO DE OPÇÃO ESCOLHIDA NOS BOTÕES PARA DIA,MES OU ANO;
            switch(TYPE_QUERY_ES)
            {
                case ANO:listContaIO = contaRep.getAnoContasEntradaSaida(dataSearch);
                    break;
                case MES:listContaIO = contaRep.getMesContasEntradaSaida(dataSearch);
                    break;
                case DIA:listContaIO = contaRep.getDiaContasEntradaSaida(dataSearch);
                    break;
            }
            
            TableContasEntradasSaidas();
            } catch(PersistenceException pex){
                Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
                AlertErro.setTitle("Relatório de Caixa");
                AlertErro.setHeaderText("Consulta de Registro de Contas de Entradas e Saídas");
                AlertErro.setContentText("Problemas ao buscar registros com este tipo de busca. Possivelmente, não há registros específicos para a Data solicitada");
                AlertErro.showAndWait();
            }
                
        }
        
        //CONSULTAR SAÍDAS
        LocalDate DataQueryO = null;
        if(tfdDataRelatorioO.getValue()!=null){
           DataQueryO = tfdDataRelatorioO.getValue(); 
        }
        
        if(listContaO==null){
            listContaO = new ArrayList<Conta>();
            LocalDate dataSearch = null;
            try {
                dataSearch= DataQueryO;
            switch(TYPE_QUERY_S)
            {
                case ANO:listContaO = contaRep.getAnoContasSaida(dataSearch);
                    break;
                case MES:listContaO = contaRep.getMesContasSaidas(dataSearch);
                    break;
            } 
            } catch(PersistenceException pex){
                Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
                AlertErro.setTitle("Relatório de Caixa");
                AlertErro.setHeaderText("Consulta de Registro de Contas de Entradas e Saídas");
                AlertErro.setContentText("Problemas ao buscar registros com este tipo de busca. Possivelmente, não há registros específicos para a Data solicitada");
                AlertErro.showAndWait();
            }
        }
        
        LocalDate DataQueryI = null;
        if(tfdDataRelatorioI.getValue()!=null){
           DataQueryI = tfdDataRelatorioI.getValue(); 
        }
        
        if(listContaI==null){
            listContaI = new ArrayList<Conta>();
            LocalDate dataSearch = null;
            try {
                dataSearch = DataQueryI;
            //CONSULTA É CONFORME A ESCOLHA DO TIPO DE OPÇÃO ESCOLHIDA NOS BOTÕES PARA DIA,MES OU ANO;
            switch(TYPE_QUERY_E)
            {
                case ANO: listContaI = contaRep.getAnoContasEntradaSaida(dataSearch);
                    break;
                case MES: listContaI = contaRep.getMesContasEntradas(dataSearch);
                    break;
                case DIA: listContaI = contaRep.getDiaContasEntradas(dataSearch);
                    break;
            } 
            }catch(PersistenceException pex){
                Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
                AlertErro.setTitle("Relatório de Caixa");
                AlertErro.setHeaderText("Consulta de Registro de Contas de Entradas e Saídas");
                AlertErro.setContentText("Problemas ao buscar registros com este tipo de busca. Possivelmente, não há registros específicos para a Data solicitada");
                AlertErro.showAndWait();
            }
            
        }
        
       ConverterListEntradasSaidas(); 
        
    }
    
    @FXML
    public void setSelectBTN_ANO_ES(){
        btn_ANO_ES.setSelected(true);
        btn_MES_ES.setSelected(false);
        btn_DIA_ES.setSelected(false);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void setSelectBTN_MES_ES(){
        btn_ANO_ES.setSelected(false);
        btn_MES_ES.setSelected(true);
        btn_DIA_ES.setSelected(false);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void setSelectBTN_DIA_ES(){
        btn_ANO_ES.setSelected(false);
        btn_MES_ES.setSelected(false);
        btn_DIA_ES.setSelected(true);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void setSelectBTN_ANO_E(){
        btn_ANO_E.setSelected(true);
        btn_MES_E.setSelected(false);
        btn_DIA_E.setSelected(false);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void setSelectBTN_MES_E(){
        btn_ANO_E.setSelected(false);
        btn_MES_E.setSelected(true);
        btn_DIA_E.setSelected(false);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void setSelectBTN_DIA_E(){
        btn_ANO_E.setSelected(false);
        btn_MES_E.setSelected(false);
        btn_DIA_E.setSelected(true);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void setSelectBTN_ANO_S(){
        btn_ANO_S.setSelected(true);
        btn_MES_S.setSelected(false);
        btn_DIA_S.setSelected(false);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void setSelectBTN_MES_S(){
        btn_ANO_S.setSelected(false);
        btn_MES_S.setSelected(true);
        btn_DIA_S.setSelected(false);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void setSelectBTN_DIA_S(){
        btn_ANO_S.setSelected(false);
        btn_MES_S.setSelected(false);
        btn_DIA_S.setSelected(true);
//        SelectTypeQueryRelatorio();
    }
    
    @FXML
    public void SelectTypeQueryRelatorio(){
        if(btn_ANO_ES.isSelected()){
            TYPE_QUERY_ES=ANO;
            btn_MES_ES.setSelected(false);
            btn_DIA_ES.setSelected(false);
        }else if(btn_MES_ES.isSelected()){
            TYPE_QUERY_ES=MES;
            btn_ANO_ES.setSelected(false);
            btn_DIA_ES.setSelected(false);
        }else if(btn_DIA_ES.isSelected()){
            TYPE_QUERY_ES=DIA;
            btn_MES_ES.setSelected(false);
            btn_ANO_ES.setSelected(false);
        }
        
        if(btn_ANO_E.isSelected()){
            TYPE_QUERY_E=ANO;
            btn_MES_E.setSelected(false);
            btn_DIA_E.setSelected(false);
        }else if(btn_MES_E.isSelected()){
            TYPE_QUERY_E=MES;
            btn_ANO_E.setSelected(false);
            btn_DIA_E.setSelected(false);
        }else if(btn_DIA_E.isSelected()){
            TYPE_QUERY_E=DIA;
            btn_MES_E.setSelected(false);
            btn_ANO_E.setSelected(false);
        }
        
        if(btn_ANO_S.isSelected()){
            TYPE_QUERY_S=ANO;
            btn_MES_S.setSelected(false);
            btn_DIA_S.setSelected(false);
        }else if(btn_MES_S.isSelected()){
            TYPE_QUERY_S=MES;
            btn_ANO_S.setSelected(false);
            btn_DIA_S.setSelected(false);
        }else if(btn_DIA_S.isSelected()){
            TYPE_QUERY_S=DIA;
            btn_MES_S.setSelected(false);
            btn_ANO_S.setSelected(false);
        }
        CarregarListFX();//RECARREGAR LISTA APÓS A MODICAÇÃO 
    }
    
    public void RecarregarArrayListQuery(){
        listContaIO=null;
        listContaI=null;
        listContaO=null;
        listContasES.clear();
        listContasES = FXCollections.observableArrayList();
        listContasE.clear();
        listContasE = FXCollections.observableArrayList();
        listContasS.clear();
        listContasS = FXCollections.observableArrayList();
        
        ConverterListEntradasSaidas();
        
    }
    
    public void ConverterListEntradasSaidas(){
        if(listContaIO!=null){
            ContasFX cFX;
            for(Conta c : listContaIO){
                
                cFX=new ContasFX();
                cFX.setIDConta(c.getIDConta());
                cFX.setNome(c.getNome());
                if(c.getCPF()!=null){
                    cFX.setCPF(c.getCPF());
                }
                if(c.getCNPJ()!=null){
                    cFX.setCNPJ(c.getCNPJ());
                }
                if(c.getDataLancamento()!=null){
                    cFX.setDataLancamento(c.getDataLancamento().toString());
                }
                if(c.getDataLancamento()!=null){
                    cFX.setDataLancamento(c.getDataLancamento().toString());
                }
                
                if(c.getDataPagamento()!=null){
                    cFX.setDataPagamento(c.getDataPagamento().toString());
                }
                cFX.setEndereco(c.getEndereco());
                cFX.setStatus(c.getStatus());
                cFX.setTipo(c.getTipo());
                cFX.setValor(Double.toString(c.getValor()));
                cFX.setReferencia(c.getReferencia());
                listContasES.add(cFX);
            }
        TableContasEntradasSaidas();
        TableContasEntradas();
        TableContasSaidas();
        }
        
        if(listContaI!=null){
            ContasFX cFX;
            for(Conta c : listContaI){
                
                cFX=new ContasFX();
                cFX.setIDConta(c.getIDConta());
                cFX.setNome(c.getNome());
                if(c.getCPF()!=null){
                    cFX.setCPF(c.getCPF());
                }
                if(c.getCNPJ()!=null){
                    cFX.setCNPJ(c.getCNPJ());
                }
                if(c.getDataLancamento()!=null){
                    cFX.setDataLancamento(c.getDataLancamento().toString());
                }
                if(c.getDataLancamento()!=null){
                    cFX.setDataLancamento(c.getDataLancamento().toString());
                }
                
                if(c.getDataPagamento()!=null){
                    cFX.setDataPagamento(c.getDataPagamento().toString());
                }
                cFX.setEndereco(c.getEndereco());
                cFX.setStatus(c.getStatus());
                cFX.setTipo(c.getTipo());
                cFX.setValor(Double.toString(c.getValor()));
                cFX.setReferencia(c.getReferencia());
                listContasE.add(cFX);
            }
        }
        
        if(listContaO!=null){
            ContasFX cFX;
            for(Conta c : listContaO){
                
                cFX=new ContasFX();
                cFX.setIDConta(c.getIDConta());
                cFX.setNome(c.getNome());
                if(c.getCPF()!=null){
                    cFX.setCPF(c.getCPF());
                }
                if(c.getCNPJ()!=null){
                    cFX.setCNPJ(c.getCNPJ());
                }
                if(c.getDataLancamento()!=null){
                    cFX.setDataLancamento(c.getDataLancamento().toString());
                }
                if(c.getDataLancamento()!=null){
                    cFX.setDataLancamento(c.getDataLancamento().toString());
                }
                
                if(c.getDataPagamento()!=null){
                    cFX.setDataPagamento(c.getDataPagamento().toString());
                }
                cFX.setEndereco(c.getEndereco());
                cFX.setStatus(c.getStatus());
                cFX.setTipo(c.getTipo());
                cFX.setValor(Double.toString(c.getValor()));
                cFX.setReferencia(c.getReferencia());
                listContasS.add(cFX);
            }
        }
        
    }
   
    public void TableContasEntradasSaidas(){
        
        JFXTreeTableColumn<ContasFX,String> tblRecebidoDe = new JFXTreeTableColumn<>("RECEBIDO DE");
        tblRecebidoDe.setMinWidth(200);
        tblRecebidoDe.setPrefWidth(450);
        tblRecebidoDe.setMaxWidth(1000);
        tblRecebidoDe.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getNome();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblReferencia = new JFXTreeTableColumn<>("REFERENTE A");
        tblReferencia.setMinWidth(200);
        tblReferencia.setPrefWidth(450);
        tblReferencia.setMaxWidth(1000);
        tblReferencia.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getReferencia();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblDataPagamento = new JFXTreeTableColumn<>("DATA DE PAGAMENTO");
        tblDataPagamento.setMinWidth(100);
        tblDataPagamento.setPrefWidth(350);
        tblDataPagamento.setMaxWidth(1000);
        tblDataPagamento.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getDataPagamento();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblTipo = new JFXTreeTableColumn<>("TIPO: E/S");
        tblTipo.setMinWidth(100);
        tblTipo.setPrefWidth(160);
        tblTipo.setMaxWidth(800) ;
        tblTipo.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getTipo();
            } 
        });
     
        final TreeItem<ContasFX> root = new RecursiveTreeItem<ContasFX>(listContasES, RecursiveTreeObject::getChildren);
        tblEntradaSaida.refresh();
        tblEntradaSaida.getColumns().setAll(tblRecebidoDe, tblReferencia, tblDataPagamento, tblTipo);
        tblEntradaSaida.setRoot(root);
        tblEntradaSaida.setShowRoot(false); 
    }
 
  
    public void TableContasEntradas(){
        
        JFXTreeTableColumn<ContasFX,String> tblRecebidoDe = new JFXTreeTableColumn<>("RECEBIDO DE");
        tblRecebidoDe.setMinWidth(100);
        tblRecebidoDe.setPrefWidth(450);
        tblRecebidoDe.setMaxWidth(1000);
        tblRecebidoDe.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getNome();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblReferencia = new JFXTreeTableColumn<>("REFERENTE A");
        tblReferencia.setMinWidth(100);
        tblReferencia.setPrefWidth(450);
        tblReferencia.setMaxWidth(1000);
        tblReferencia.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getReferencia();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblDataPagamento = new JFXTreeTableColumn<>("DATA DE PAGAMENTO");
        tblDataPagamento.setMinWidth(100);
        tblDataPagamento.setPrefWidth(350);
        tblDataPagamento.setMaxWidth(1000);
        tblDataPagamento.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getDataPagamento();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblTipo = new JFXTreeTableColumn<>("TIPO: E/S");
        tblTipo.setMinWidth(100);
        tblTipo.setPrefWidth(160);
        tblTipo.setMaxWidth(400) ;
        tblTipo.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getTipo();
            } 
        });
     
        final TreeItem<ContasFX> root = new RecursiveTreeItem<ContasFX>(listContasE, RecursiveTreeObject::getChildren);
        tblEntrada.refresh();
        tblEntrada.getColumns().setAll(tblRecebidoDe, tblReferencia, tblDataPagamento, tblTipo);
        tblEntrada.setRoot(root);
        tblEntrada.setShowRoot(false); 
    }
    
    
    
        
    public void TableContasSaidas(){
        
        JFXTreeTableColumn<ContasFX,String> tblRecebidoDe = new JFXTreeTableColumn<>("RECEBIDO DE");
        tblRecebidoDe.setMinWidth(100);
        tblRecebidoDe.setPrefWidth(450);
        tblRecebidoDe.setMaxWidth(1000);
        tblRecebidoDe.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getNome();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblReferencia = new JFXTreeTableColumn<>("REFERENTE A");
        tblReferencia.setMinWidth(100);
        tblReferencia.setPrefWidth(450);
        tblReferencia.setMaxWidth(1000);
        tblReferencia.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getValor();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblDataPagamento = new JFXTreeTableColumn<>("DATA DE PAGAMENTO");
        tblDataPagamento.setMinWidth(100);
        tblDataPagamento.setPrefWidth(300);
        tblDataPagamento.setMaxWidth(1000);
        tblDataPagamento.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getDataPagamento();
            } 
        });
        
        JFXTreeTableColumn<ContasFX,String> tblTipo = new JFXTreeTableColumn<>("TIPO: E/S");
        tblTipo.setMinWidth(100);
        tblTipo.setPrefWidth(160);
        tblTipo.setMaxWidth(800);
        tblTipo.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<ContasFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<ContasFX, String> param) {
                return param.getValue().getValue().getTipo();
            } 
        });
     
        final TreeItem<ContasFX> root = new RecursiveTreeItem<ContasFX>(listContasS, RecursiveTreeObject::getChildren);
        tblSaida.refresh();
        tblSaida.getColumns().setAll(tblRecebidoDe, tblReferencia, tblDataPagamento, tblTipo);
        tblSaida.setRoot(root);
        tblSaida.setShowRoot(false); 
    }
 
    @FXML
    public void openMainCaixa() {
        
        try {
        
        FXMLLoader registrarFXML = new FXMLLoader(RelatorioCaixaController.class.getResource("/JFX/BSI/GesMed/Interfaces/Caixa/MainCaixa.fxml"));
        
        StackPaneCaixa = registrarFXML.load();
        
        StackPaneRoot.getChildren().clear();
        StackPaneRoot.getChildren().add(StackPaneCaixa);
        
        StackPaneCaixa.setPrefWidth(1100);
        StackPaneCaixa.setPrefHeight(600);
        StackPaneCaixa.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        
        FadeTransition ft = new FadeTransition();
        ft.setNode(StackPaneCaixa);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   @FXML
   public void GerarReceitaES(MouseEvent event){
        
        if(listContaIO.size()!=0){
          
          Document document = new Document();
           
          LocalDate dataHoje = LocalDate.now();
            
          try {
              
              PdfWriter.getInstance(document, new FileOutputStream(".\\RELATÓRIO DO "+TYPE_QUERY_ES+" - ENTRADAS E SAÍDAS "+" - "+dataHoje.toString()+".pdf"));
              document.open();
              Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
              Font bold2 = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
              Font fontConteudo = new Font(Font.FontFamily.TIMES_ROMAN, 15);
              // adicionando um parágrafo no documento
              paragrafo = new Paragraph("GESMED - Secretaria", bold2);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("RELATÓRIO DO "+TYPE_QUERY_ES+" COM ENTRADAS E SAÍDAS", bold);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("______________________________________________________________", fontConteudo);
              document.add(paragrafo);
              for(Conta conta : listContaIO){
                    String TipoPessoa="CPF";
                    String ValorNumDoc="";
                    if(conta.getCPF()==null){
                        TipoPessoa="CNPJ";
                        ValorNumDoc=conta.getCNPJ();
                    }else{
                        ValorNumDoc=conta.getCPF();
                    }
                    paragrafo = new Paragraph("\nNOME: "+conta.getNome()+"\n"+TipoPessoa+": "+ValorNumDoc+""
                            + "\nNO VALOR DE: "+conta.getValor()+" referente a um(a) "+conta.getReferencia()+"\nCOM LANÇAMENTO EM: "+conta.getDataLancamento().toString()+"\nE PAGO EM: "+conta.getDataPagamento().toString()+""
                            + "\nTIPO DE MOVIMENTAÇÃO: "+conta.getTipo()+". E O STATUS SOBRE A CONTA É: "+conta.getStatus(), fontConteudo);
                    
                    document.add(paragrafo);
              paragrafo = new Paragraph("______________________________________________________________", fontConteudo);
              document.add(paragrafo);
              }
              
              
              String RodapeFinal = "\n" + "__________________________________________________________"+
                                         "\n" +  " Carimbo ou Assinatura do Médico"+"\n\n\n\n\n"+
                                        "Clínica Universitaria - Avenida Ceara nº 345 \n" +
                                            "Rio Branco - Acre";
                      
              paragrafo = new Paragraph("\n\n\n\n\n\n"+RodapeFinal);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
 
  
          }
          catch(DocumentException de) {
              System.err.println(de.getMessage());
          }
          catch(IOException ioe) {
              System.err.println(ioe.getMessage());
          }finally{
               document.close();
               
          }
          
          try {
              Desktop.getDesktop().open(new File(".\\RELATÓRIO DO "+TYPE_QUERY_ES+" - ENTRADAS E SAÍDAS "+" - "+dataHoje.toString()+".pdf"));
          } catch (IOException ex) {
              Logger.getLogger(RelatorioCaixaController.class.getName()).log(Level.SEVERE, null, ex);
          }  
        }else{
                Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
                AlertErro.setTitle("Relatórios de Caixa");
                AlertErro.setHeaderText("Consulta de Registro de Contas de Entradas e Saídas");
                AlertErro.setContentText("Não há como gerar Relatórios em PDF sem nenhum resultado encontrado.");
                AlertErro.showAndWait();
        }
          
          
   }
  
   @FXML
   public void GerarReceitaE(MouseEvent event){
          if(listContaI.size()!=0){
           Document document = new Document();
           
          LocalDate dataHoje = LocalDate.now();
            
          try {
              
              PdfWriter.getInstance(document, new FileOutputStream(".\\RELATÓRIO DO "+TYPE_QUERY_E+" - ENTRADAS"+" - "+dataHoje.toString()+".pdf"));
              document.open();
              Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
              Font bold2 = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
              Font fontConteudo = new Font(Font.FontFamily.TIMES_ROMAN, 15);
              // adicionando um parágrafo no documento
              paragrafo = new Paragraph("GESMED - Secretaria", bold2);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("RELATÓRIO DO "+TYPE_QUERY_E+" COM ENTRADAS", bold);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("______________________________________________________________", fontConteudo);
              document.add(paragrafo);
              for(Conta conta : listContaI){
                    String TipoPessoa="CPF";
                    String ValorNumDoc="";
                    if(conta.getCPF()==null){
                        TipoPessoa="CNPJ";
                        ValorNumDoc=conta.getCNPJ();
                    }else{
                        ValorNumDoc=conta.getCPF();
                    }
                    paragrafo = new Paragraph("\nNOME: "+conta.getNome()+"\n"+TipoPessoa+": "+ValorNumDoc+""
                            + "\nNO VALOR DE: "+conta.getValor()+" referente a um(a) "+conta.getReferencia()+"\nCOM LANÇAMENTO EM: "+conta.getDataLancamento().toString()+"\nE PAGO EM: "+conta.getDataPagamento().toString()+""
                            + "\nTIPO DE MOVIMENTAÇÃO: "+conta.getTipo()+". E O STATUS SOBRE A CONTA É: "+conta.getStatus(), fontConteudo);
                    
                    document.add(paragrafo);
              paragrafo = new Paragraph("______________________________________________________________", fontConteudo);
              document.add(paragrafo);
              }
              
              
              String RodapeFinal = "\n" + "__________________________________________________________"+
                                         "\n" +  " Carimbo ou Assinatura do Médico"+"\n\n\n\n\n"+
                                        "Clínica Universitaria - Avenida Ceara nº 345 \n" +
                                            "Rio Branco - Acre";
                      
              paragrafo = new Paragraph("\n\n\n\n\n\n"+RodapeFinal);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
 
  
          }
          catch(DocumentException de) {
              System.err.println(de.getMessage());
          }
          catch(IOException ioe) {
              System.err.println(ioe.getMessage());
          }finally{
               document.close();
               
          }
          
          try {
              Desktop.getDesktop().open(new File(".\\RELATÓRIO DO "+TYPE_QUERY_E+" - ENTRADAS"+" - "+dataHoje.toString()+".pdf"));
          } catch (IOException ex) {
              Logger.getLogger(RelatorioCaixaController.class.getName()).log(Level.SEVERE, null, ex);
          }   
          }else{
                Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
                AlertErro.setTitle("Relatórios de Caixa");
                AlertErro.setHeaderText("Consulta de Registro de Contas de Entradas");
                AlertErro.setContentText("Não há como gerar Relatório em PDF sem nenhum resultado encontrado.");
                AlertErro.showAndWait();
          }
          
          
   }
      
   @FXML
   public void GerarReceitaS(MouseEvent event){
          if(listContaO.size()!=0){
           Document document = new Document();
           
          LocalDate dataHoje = LocalDate.now();
            
          try {
              
                PdfWriter.getInstance(document, new FileOutputStream(".\\RELATÓRIO DO "+TYPE_QUERY_S+" -SAÍDAS "+" - "+dataHoje.toString()+".pdf"));
                document.open();
                Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
                Font bold2 = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
                Font fontConteudo = new Font(Font.FontFamily.TIMES_ROMAN, 15);
                // adicionando um parágrafo no documento
                paragrafo = new Paragraph("GESMED - Secretaria", bold2);
                paragrafo.setAlignment(Element.ALIGN_CENTER);
                document.add(paragrafo);

                paragrafo = new Paragraph("RELATÓRIO DO "+TYPE_QUERY_S+" COM ENTRADAS E SAÍDAS", bold);
                paragrafo.setAlignment(Element.ALIGN_CENTER);
                document.add(paragrafo);

                paragrafo = new Paragraph("______________________________________________________________", fontConteudo);
                document.add(paragrafo);
                for(Conta conta : listContaO){
                      String TipoPessoa="CPF";
                      String ValorNumDoc="";
                      if(conta.getCPF()==null){
                          TipoPessoa="CNPJ";
                          ValorNumDoc=conta.getCNPJ();
                      }else{
                          ValorNumDoc=conta.getCPF();
                      }
                      paragrafo = new Paragraph("\nNOME: "+conta.getNome()+"\n"+TipoPessoa+": "+ValorNumDoc+""
                            + "\nNO VALOR DE: "+conta.getValor()+" referente a um(a) "+conta.getReferencia()+"\nCOM LANÇAMENTO EM: "+conta.getDataLancamento().toString()+"\nE PAGO EM: "+conta.getDataPagamento().toString()+""
                            + "\nTIPO DE MOVIMENTAÇÃO: "+conta.getTipo()+". E O STATUS SOBRE A CONTA É: "+conta.getStatus(), fontConteudo);
                      
                      document.add(paragrafo);
                paragrafo = new Paragraph("______________________________________________________________", fontConteudo);
                document.add(paragrafo);
                }


                String RodapeFinal = "\n" + "__________________________________________________________"+
                                           "\n" +  " Carimbo ou Assinatura do Médico"+"\n\n\n\n\n"+
                                          "Clínica Universitaria - Avenida Ceara nº 345 \n" +
                                              "Rio Branco - Acre";

                paragrafo = new Paragraph("\n\n\n\n\n\n"+RodapeFinal);
                paragrafo.setAlignment(Element.ALIGN_CENTER);
                document.add(paragrafo);



            }
            catch(DocumentException de) {
                System.err.println(de.getMessage());
            }
            catch(IOException ioe) {
                System.err.println(ioe.getMessage());
            }finally{
                 document.close();

            }

            try {
                Desktop.getDesktop().open(new File(".\\RELATÓRIO DO "+TYPE_QUERY_S+" -SAÍDAS "+" - "+dataHoje.toString()+".pdf"));
            } catch (IOException ex) {
                Logger.getLogger(RelatorioCaixaController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          }else{
                Alert AlertErro = new Alert(Alert.AlertType.INFORMATION);
                AlertErro.setTitle("Relatórios de Caixa");
                AlertErro.setHeaderText("Consulta de Registro de Contas de Saídas");
                AlertErro.setContentText("Não há como gerar Relatório em PDF sem nenhum resultado encontrado.");
                AlertErro.showAndWait();
          }  
          
          
   }
   
    
    class ContasFX extends RecursiveTreeObject<ContasFX> {
        
        private StringProperty IDConta;
        private StringProperty Nome;
        private StringProperty Referencia;
        private StringProperty CPF;
        private StringProperty CNPJ;
        private StringProperty Endereco;
        private StringProperty Valor;
        private StringProperty Tipo;
        private StringProperty DataLancamento;
        private StringProperty DataPagamento;
        private StringProperty Status;

        public StringProperty getIDConta() {
            return IDConta;
        }

        public void setIDConta(int IDConta) {
            this.IDConta = new SimpleStringProperty(Integer.toString(IDConta));
        }

        public StringProperty getNome() {
            return Nome;
        }

        public void setNome(String Nome) {
            this.Nome = new SimpleStringProperty(Nome);
        }

        public StringProperty getReferencia() {
            return Referencia;
        }

        public void setReferencia(String Referencia) {
            this.Referencia = new SimpleStringProperty(Referencia);
        }

        public StringProperty getCPF() {
            return CPF;
        }

        public void setCPF(String CPF) {
            this.CPF = new SimpleStringProperty(CPF);
        }

        public StringProperty getCNPJ() {
            return CNPJ;
        }

        public void setCNPJ(String CNPJ) {
            this.CNPJ = new SimpleStringProperty(CNPJ);
        }

        public StringProperty getEndereco() {
            return Endereco;
        }

        public void setEndereco(String Endereco) {
            this.Endereco = new SimpleStringProperty(Endereco);
        }

        public StringProperty getValor() {
            return Valor;
        }

        public void setValor(String Valor) {
            this.Valor = new SimpleStringProperty(Valor);
        }

        public StringProperty getTipo() {
            return Tipo;
        }

        public void setTipo(String Tipo) {
            this.Tipo = new SimpleStringProperty(Tipo);
        }

        public StringProperty getDataLancamento() {
            return DataLancamento;
        }

        public void setDataLancamento(String DataLancamento) {
            this.DataLancamento = new SimpleStringProperty(DataLancamento);
        }

        public StringProperty getDataPagamento() {
            return DataPagamento;
        }

        public void setDataPagamento(String DataPagamento) {
            this.DataPagamento = new SimpleStringProperty(DataPagamento);
        }

        public StringProperty getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = new SimpleStringProperty(Status);
        }
        
        
        
        
    }
    
    
}
