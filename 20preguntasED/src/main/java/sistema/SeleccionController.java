package sistema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SeleccionController implements Initializable{
    
    public static int preguntas = 1;
    @FXML
    Label cantidadPreguntas;
    
    private void setLabelPreguntas(int i){
        cantidadPreguntas.setText(String.valueOf(i));
    }
    
    @FXML
    private void aumentarPreguntas() throws IOException {
        if (JuegoController.preguntas < 20 && JuegoController.preguntas < JuegoController.arbol.getAltura()-1){
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

    @FXML
    private void next(ActionEvent event) {
        try {
            App.setRoot("juego");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabelPreguntas(preguntas);
    }
    
    
}