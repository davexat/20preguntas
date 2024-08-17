/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import TDA.PilaPRS;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import manejoArchivos.LectorPreguntas;

/**
 *
 * @author Pc
 */
public class DatosController implements Initializable {
    public static PilaPRS<Boolean> respuestasUsuario;
    public static boolean nuevoAnimal = false;
    private static int cantidadRespuestas;
    private File imageFile;
    @FXML 
    private VBox cajaPreguntas;
    @FXML
    private TextField nombreAnimal;
    @FXML
    private Label respuestasActuales;
    @FXML
    private Label respuestasAdicionales;
    @FXML
    private ImageView imagenAnimal;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cantidadRespuestas = 0;
        cargarPreguntas();
        cargarRespuestas();
    }
    @FXML
    private void resetearNombre(){
        nombreAnimal.clear();
        imagenAnimal.setImage(null);
    }
    private void cargarPreguntas(){
        cajaPreguntas.getChildren().clear();
        PilaPRS<String> preguntas = new LectorPreguntas().leerArchivo(CargaController.txtFilePreguntas.getPath());
        preguntas.pop();
        for (String p: preguntas){
            Label l = new Label(p);
            l.setStyle("-fx-font-size: 17px; -fx-font-family: 'monospace';");
            cajaPreguntas.getChildren().add(l);
        }
    }
    private void cargarRespuestas(){
        String cadenaRespuestas = "";
        for (boolean b: respuestasUsuario){
            if (b) cadenaRespuestas += "1 ";
            else cadenaRespuestas += "0 ";
        }
        respuestasActuales.setText(cadenaRespuestas);
    }
    @FXML
    private void agregarSi(){
        if (20 - (cantidadRespuestas + respuestasUsuario.size()) > 0){
            cantidadRespuestas++;
            String actual = respuestasAdicionales.getText();
            actual += "1 ";
            respuestasAdicionales.setText(actual);
        }
    }
    @FXML
    private void agregarNo(){
        if (20 - (cantidadRespuestas + respuestasUsuario.size()) > 0){
            cantidadRespuestas++;
            String actual = respuestasAdicionales.getText();
            actual += "0 ";
            respuestasAdicionales.setText(actual);
        }
    }
    @FXML
    private void reset(){
        cantidadRespuestas = 0;
        respuestasAdicionales.setText("");
    }
    @FXML
    private void subirImagen(){
        if (nombreAnimal.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.WARNING, "Por favor, escriba primero el nombre del animal.");
            a.show();
        }else {
            FileChooser fc = new FileChooser();
            fc.setTitle("Seleccione una imagen para el animal");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PNG (*.png)", "*.png"));
            imageFile = fc.showOpenDialog(cajaPreguntas.getScene().getWindow());
            if (imageFile != null){
                imagenAnimal.setImage(new Image(imageFile.toURI().toString()));
            }else if (imagenAnimal.getImage() == null) {
                Alert a = new Alert(Alert.AlertType.WARNING, "Por favor, seleccione una imagen.");
                a.show();
            }
        }
    }
    @FXML 
    private void continuar(){
        if (!nombreAnimal.getText().equals("") && imagenAnimal.getImage() != null){
            String linea = nombreAnimal.getText().replace(" ", "_")+" ";
            linea += respuestasActuales.getText()+respuestasAdicionales.getText();
            Path destino = Paths.get("animales/" + nombreAnimal.getText().replace(" ", "_")+".png");
            //Path.of("/src/main/resources/animales/", nombreAnimal.getText()+".png");
            try {
                Files.copy(imageFile.toPath(),destino,StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            nuevoAnimal = true;
            agregarAlArchivo(linea.strip());
            Stage stage = (Stage) cajaPreguntas.getScene().getWindow();
            stage.close();
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING, "Por favor, rellene los campos.");
            a.show();
        }
    }
    private void agregarAlArchivo(String s){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(CargaController.txtFileRespuestas, true))){
            bw.write("\n"+s);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void cancelar(){
        Stage stage = (Stage) cajaPreguntas.getScene().getWindow();
        stage.close();
    }
}
