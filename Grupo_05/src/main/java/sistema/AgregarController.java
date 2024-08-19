/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Pc
 */
public class AgregarController {
    
    @FXML
    private Label info;
    
    @FXML
    private void playAgain() throws IOException{
        App.setRoot("carga");
    }
    @FXML
    private void exit(){
        Stage stage = (Stage) info.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void agregar() throws IOException{
        if (DatosController.nuevoAnimal){
            Alert a = new Alert(Alert.AlertType.WARNING, "Ya se ha agregado el animal.");
            a.show();
            return;
        }
        Stage stage = (Stage) info.getScene().getWindow();
        Stage ventanaAgregar = new Stage();
        ventanaAgregar.initModality(Modality.WINDOW_MODAL);
        ventanaAgregar.initOwner(stage);
        
        ventanaAgregar.setTitle("Agregar animal");
        ventanaAgregar.getIcons().add(new Image(getClass().getResourceAsStream("/img/icono.png")));
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("datos.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 540);
        ventanaAgregar.setScene(scene);
        ventanaAgregar.setResizable(false);
        ventanaAgregar.show();
    }
}
