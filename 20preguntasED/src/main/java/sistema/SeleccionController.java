package sistema;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SeleccionController {

    public static int preguntas = 0;
    @FXML
    Label cantidadPreguntas;
    
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

    @FXML
    private void next(ActionEvent event) {
        try {
            Stage currentStage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            
            FXMLLoader fxmlLoader = App.loadFXML("inicio");
            Parent root = fxmlLoader.load();
            Scene s = new Scene(root, 600, 400);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icono.png")));
            stage.setTitle("Juego");
            stage.setScene(s);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}