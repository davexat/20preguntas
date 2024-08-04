/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistema;

import clases.ArbolCreator;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
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
                //lblP.setText("Selected file: " + file.getName());
                txtFilePreguntas = file;
            }else{
                //lblR.setText("Selected file: " + file.getName());
                txtFileRespuestas = file;
            }
        } /*else {
            if(event.getSource().equals(cargarPreguntas))
                lblP.setText("No file selected");
            else
                lblR.setText("No file selected");
        }*/
    }

    @FXML
    private void next(ActionEvent event) {
        ArbolCreator ac = new ArbolCreator();
        ac.crearArbol(txtFilePreguntas.getPath(), txtFileRespuestas.getPath());
        if(ac.validarContinuacion()){
            //llamar al nuevo stage
        }
            
    }
    
}
