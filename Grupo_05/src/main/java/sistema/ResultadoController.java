/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import TDA.*;
import clases.ResultadoCreator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Pc
 */
public class ResultadoController implements Initializable{
    private static CircularListPRS<VBox> resultados;
    
    @FXML 
    private VBox contenedorRespuesta;
    @FXML 
    private Label info;
    @FXML 
    private Button botonI;
    @FXML 
    private Button botonD;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultados = new CircularListPRS<>();
        ResultadoCreator rc = new ResultadoCreator();
        rc.agregarResultados(JuegoController.arbol);
        resultados.addAll(rc.getResultados());
        if (resultados.size() == 1) presentarUnico();
        else presentarLista();
    }
    private void presentarUnico(){
        info.setText("Su animal es:");
        botonI.setManaged(false);
        botonD.setManaged(false);
        contenedorRespuesta.getChildren().add(resultados.get(0));
    }
    private void presentarLista(){
        info.setText("Los posibles animales pensados son:");
        contenedorRespuesta.getChildren().add(resultados.setPointer(null));
    }
    @FXML
    private void correrIzq(){
        contenedorRespuesta.getChildren().clear();
        contenedorRespuesta.getChildren().add(resultados.previousPointer());
    }
    @FXML
    private void correrDer(){
        contenedorRespuesta.getChildren().clear();
        contenedorRespuesta.getChildren().add(resultados.nextPointer());
    }
    @FXML
    private void playAgain() throws IOException{
        App.setRoot("carga");
    }
    
    @FXML
    private void exit(){
        Stage stage = (Stage) info.getScene().getWindow();
        stage.close();
    }
}
