package sistema;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void empezarJuego() throws IOException {
        App.setRoot("secondary");
    }
}
