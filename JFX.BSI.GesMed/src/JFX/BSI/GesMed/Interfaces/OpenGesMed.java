/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Interfaces;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author danml
 */
public class OpenGesMed extends Application {
    
    FXMLLoader WecomeFXML = new FXMLLoader(TelaBoasVindasController.class.getResource("TelaBoasVindas.fxml"));
    TelaBoasVindasController telaWecome = new TelaBoasVindasController();
    
    @Override
    public void start(Stage stage) throws Exception {
        WecomeFXML.setController(telaWecome);
        Parent root = WecomeFXML.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(5));
        pauseTransition.setOnFinished(ev -> {
           telaWecome.openStage();

        });
        pauseTransition.play();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
