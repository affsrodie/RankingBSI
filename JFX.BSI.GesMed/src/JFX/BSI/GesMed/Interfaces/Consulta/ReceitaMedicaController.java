/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces.Agenda;

import JFX.BSI.GesMed.Entidades.Agendamento;
import JFX.BSI.GesMed.Entidades.Paciente;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Leoncio Carioca
 */
public class ReceitaMedicaController implements Initializable {
    @FXML
    private JFXTextField tfdMedicamento;

    @FXML
    private JFXTextArea tfdObservacoes;

    @FXML
    private JFXTextField tfdQtdHoras;

    @FXML
    private JFXTreeTableView<MedicamentoFX> tblReceitaMedica;

    @FXML
    private JFXButton btnAdicionar;

    @FXML
    private JFXButton btnGerarReceita;
    
    ObservableList<MedicamentoFX> medicacoesFX = FXCollections.observableArrayList();
    
    private static Paragraph paragrafo;
    
    private Paciente paciente;
    private Agendamento agenda;

    public ReceitaMedicaController() {
    }
    
    public ReceitaMedicaController(Agendamento agenda) {
    this.agenda = agenda;
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarregarTabelaMedicamentos();
    }    
    
    
    public void CarregarTabelaMedicamentos(){
        
        JFXTreeTableColumn<MedicamentoFX,String> tblNome=  new JFXTreeTableColumn<>("MEDICAMENTO");
        tblNome.setMinWidth(150);
        tblNome.setPrefWidth(230);
        tblNome.setMaxWidth(1000);
        tblNome.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<MedicamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<MedicamentoFX, String> param) {
                return param.getValue().getValue().Nome;
            }
        });
        
        JFXTreeTableColumn<MedicamentoFX,String> tblQuantHoras=  new JFXTreeTableColumn<>("Nome do Paciente");
        tblQuantHoras.setMinWidth(80);
        tblQuantHoras.setPrefWidth(100);
        tblQuantHoras.setMaxWidth(200);
        tblQuantHoras.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<MedicamentoFX, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<MedicamentoFX, String> param) {
                return param.getValue().getValue().QtdHoras;
            }
        });
         

        final TreeItem<MedicamentoFX> root = new RecursiveTreeItem<MedicamentoFX>(medicacoesFX, RecursiveTreeObject::getChildren);
        tblReceitaMedica.getColumns().setAll(tblNome, tblQuantHoras);
        tblReceitaMedica.setRoot(root);
        tblReceitaMedica.setShowRoot(false);
        
    }
    
   public void LimparCampos(){
       tfdMedicamento.setText(null);
       tfdObservacoes.setText(null);
       tfdQtdHoras.setText(null);
   }
    
   @FXML
   public void addTabelaMedicamentos(MouseEvent event){
       ValidationFields validFields = new ValidationFields();
       
       if(validFields.checkEmptyFields(tfdMedicamento, tfdObservacoes, tfdQtdHoras)){
       
       MedicamentoFX medFX = new MedicamentoFX();
       medFX.setNome(tfdMedicamento.getText());
       medFX.setObservacoes(tfdObservacoes.getText());
       medFX.setQtdHoras(tfdQtdHoras.getText());
       
       medicacoesFX.add(medFX);
       CarregarTabelaMedicamentos();
       LimparCampos();
       
       }
       
   }
   
   @FXML
   public void GerarReceita(MouseEvent event){
            
          Document document = new Document();
           
          LocalDate dataHoje = LocalDate.now();
            
          try {
              
              PdfWriter.getInstance(document, new FileOutputStream(".\\Receita - "+agenda.getPaciente().getCPF()+" - "+dataHoje.toString()+".pdf"));
              document.open();
              Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
              Font bold2 = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
              Font fontConteudo = new Font(Font.FontFamily.TIMES_ROMAN, 15);
              // adicionando um parágrafo no documento
              paragrafo = new Paragraph("GESMED - Clinica Academicos da UFAC", bold2);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("RECEITA MÉDICA", bold);
              paragrafo.setAlignment(Element.ALIGN_CENTER);
              document.add(paragrafo);
              
              paragrafo = new Paragraph("______________________________________________________________", fontConteudo);
              document.add(paragrafo);
              for(MedicamentoFX receita : medicacoesFX){
                    paragrafo = new Paragraph("\n\nMedicamento: "+receita.getNome()+"\n\n"+"Qtd. Horas: "+receita.getQtdHoras()+"\n\n"+"Observações: "+receita.getObservacoes(), fontConteudo);
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
              
//        HeaderFooter footer = new HeaderFooter(new Phrase("Dt. Emissão " + getDataEmisao()),false);
//       footer.setBorder(Rectangle.TOP);
//       documento.setFooter(footer);
  
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
              Desktop.getDesktop().open(new File(".\\Receita - "+agenda.getPaciente().getCPF()+" - "+dataHoje.toString()+".pdf"));
          } catch (IOException ex) {
              Logger.getLogger(ReceitaMedicaController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
   }
    
   @FXML
   public void tfdQtdHorasKeyRelased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(tfdQtdHoras);
        tff.formatter();
    }
   
    class MedicamentoFX extends RecursiveTreeObject<MedicamentoFX> {
        
        
        StringProperty Nome;
        StringProperty Observacoes;
        StringProperty QtdHoras;

        
        public StringProperty getNome() {
            return Nome;
        }

        public void setNome(String Nome) {
            this.Nome = new SimpleStringProperty(Nome);
        }

        public StringProperty getObservacoes() {
            return Observacoes;
        }

        public void setObservacoes(String Observacoes) {
            this.Observacoes = new SimpleStringProperty(Observacoes);;
        }

        public StringProperty getQtdHoras() {
            return QtdHoras;
        }

        public void setQtdHoras(String QtdHoras) {
            this.QtdHoras = new SimpleStringProperty(QtdHoras);;
        }
        
        
    }
    
}
