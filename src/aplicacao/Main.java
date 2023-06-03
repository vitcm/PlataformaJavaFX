package aplicacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Carregando o arquivo FXML da tela inicial
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_BemVinda.fxml"));
        //javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        // Criando a cena
        Scene scene = new Scene(root
                //, screenBounds.getWidth(), screenBounds.getHeight()
        );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}