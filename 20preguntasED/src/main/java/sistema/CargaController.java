/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistema;

import clases.ArbolCreator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
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
        txtFilePreguntas = new File("src/main/resources/files/preguntas.txt");
        txtFileRespuestas = new File("src/main/resources/files/respuestas.txt");
        lblPreguntas.setText(txtFilePreguntas.getName());
        lblRespuestas.setText(txtFileRespuestas.getName());
        DatosController.nuevoAnimal = false;
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
                App.setRoot("seleccion");
            }
        }catch (NullPointerException ex){
            Alert a = new Alert(Alert.AlertType.WARNING, "Por favor, seleccione archivos.");
            a.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
    }
    
}
