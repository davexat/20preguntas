package sistema;

import java.io.IOException;
import javafx.fxml.FXML;

public class InicioController {
    
    @FXML
    private void empezarJuego() throws IOException {
        App.setRoot("carga");
    }
}
