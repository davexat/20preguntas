package sistema;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioController {

    @FXML
    private Button botonInicio;

    @FXML
    private void empezarJuego() throws IOException {
        App.setRoot("carga");
    }
}
