package sistema;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SeleccionController {
    public static int preguntas = 0;
    @FXML Label cantidadPreguntas;
    
    private void setLabelPreguntas(int i){
        cantidadPreguntas.setText(String.valueOf(i));
    }
    
    @FXML
    private void aumentarPreguntas() throws IOException {
        if (preguntas < 20){
            preguntas++;
            setLabelPreguntas(preguntas);
        }
    }
    
    @FXML
    private void disminuirPreguntas() throws IOException {
        if (preguntas > 0){
            preguntas--;
            setLabelPreguntas(preguntas);
        }
    }
}