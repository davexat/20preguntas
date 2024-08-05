/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistema;

import clases.ArbolCreator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class CargaController implements Initializable {
    
    @FXML
    private Button cargarPreguntas;
    @FXML
    private Button cargarRespuestas;
    @FXML
    private Button continuar;
    
    static File txtFilePreguntas;
    static File txtFileRespuestas;
    @FXML
    private Label lblPreguntas;
    @FXML
    private Label lblRespuestas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void subirArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Stage stage = event.getSource().equals(cargarPreguntas) ? (Stage) cargarPreguntas.getScene().getWindow(): (Stage) cargarRespuestas.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        
        if (file != null) {
            if(event.getSource().equals(cargarPreguntas)){
                lblPreguntas.setText(file.getName());
                txtFilePreguntas = file;
            }else{
                lblRespuestas.setText(file.getName());
                txtFileRespuestas = file;
            }
        } else {
            if(event.getSource().equals(cargarPreguntas)){
                lblPreguntas.setText("No file selected");
                txtFilePreguntas = null;
            }else{
                lblRespuestas.setText("No file selected");
                txtFilePreguntas = null;
            }
        }
    }

    @FXML
    private void next(ActionEvent event) {
        try {
            ArbolCreator ac = new ArbolCreator();
            ac.crearArbol(txtFilePreguntas.getPath(), txtFileRespuestas.getPath());
            if(ac.validarContinuacion()){
                JuegoController.arbol = ac.getArbol();
                
                Stage currentStage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
                
                FXMLLoader fxmlLoader = App.loadFXML("seleccion");
                Parent root = fxmlLoader.load();
                Scene s = new Scene(root, 300, 160);
                Stage stage = new Stage();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icono.png")));
                stage.setTitle("Selección");
                stage.setScene(s);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        }catch (NullPointerException ex){
            Alert a = new Alert(Alert.AlertType.WARNING, "Por favor, seleccione archivos.");
            a.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
    }
    
}
