package sistema;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SeleccionController {
    @FXML Label cantidadPreguntas;
    
    private void setLabelPreguntas(int i){
        cantidadPreguntas.setText(String.valueOf(i));
    }
    
    @FXML
    private void aumentarPreguntas() throws IOException {
        if (JuegoController.preguntas < 20 && JuegoController.preguntas < JuegoController.arbol.getAltura()){
            JuegoController.preguntas++;
            setLabelPreguntas(JuegoController.preguntas);
        }
    }
    
    @FXML
    private void disminuirPreguntas() throws IOException {
        if (JuegoController.preguntas > 1){
            JuegoController.preguntas--;
            setLabelPreguntas(JuegoController.preguntas);
        }
    }
}