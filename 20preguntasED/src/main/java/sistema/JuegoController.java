/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import TDA.ArbolDecisionPRS;
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
    public static int preguntas = 1;
    public static ArbolDecisionPRS<String,String> arbol = null;
    public static int contador = 1;
    
    @FXML Label pregunta;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        pregunta.setText(arbol.getContenido());
        maximoAlcanzado();
    }
    
    private void mostrarPregunta(){
        pregunta.setText(arbol.getContenido());
    }
    
    @FXML
    private void respuestaSi(){
        arbol = arbol.correrIzq();
        contador++;
        maximoAlcanzado();
        mostrarPregunta();
    }
    
    @FXML
    private void respuestaNo(){
        arbol = arbol.correrDer();
        contador++;
        maximoAlcanzado();
        mostrarPregunta();
    }
    
    private void maximoAlcanzado(){
        if (contador > preguntas){
            // IR A LA SIGUIENTE VISTA
        }
    }
}
