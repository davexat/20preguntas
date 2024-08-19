/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import TDA.ArbolDecisionPRS;
import TDA.PilaPRS;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Pc
 */
public class JuegoController implements Initializable {
    public static int preguntas;
    public static ArbolDecisionPRS<String,String> arbol = null;
    public static int contador;
    
    @FXML Label pregunta;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contador = 1;
        DatosController.respuestasUsuario = new PilaPRS<>();
        pregunta.setText(arbol.getContenido());
        try{
            verificacion();
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
    
    private void mostrarPregunta(){
        pregunta.setText(arbol.getContenido());
    }
    
    @FXML
    private void respuestaSi() throws IOException{
        arbol = arbol.correrIzq();
        DatosController.respuestasUsuario.add(true);
        contador++;
        verificacion();
        mostrarPregunta();
    }
    
    @FXML
    private void respuestaNo() throws IOException{
        arbol = arbol.correrDer();
        DatosController.respuestasUsuario.add(false);
        contador++;
        verificacion();
        mostrarPregunta();
    }
    
    private void verificacion() throws IOException{
        if (arbol.getRespuestas().isEmpty()){
            App.setRoot("agregar");
        }else if (arbol.getRespuestas().size() == 1 || contador > preguntas){
            App.setRoot("resultado");
        }
    }
}
