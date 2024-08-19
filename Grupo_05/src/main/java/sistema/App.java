package sistema;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static File txtFilePreguntas = new File("src/main/resources/files/preguntas.txt");
    public static File txtFileRespuestas = new File("src/main/resources/files/respuestas.txt");;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("20preguntas");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icono.png")));
        scene = new Scene(loadFXML("inicio").load(), 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }
    
    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    
    public static void main(String[] args) {
        launch();
    }

}