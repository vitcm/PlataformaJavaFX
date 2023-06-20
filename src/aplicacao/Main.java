package aplicacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A classe Main é a classe principal da aplicação.
 * Ela inicializa a aplicação JavaFX carregando a tela inicial.
 */
public class Main extends Application {

    /**
     * O método start é chamado quando a aplicação é iniciada.
     * Ele carrega o arquivo FXML da tela inicial e cria a cena correspondente.
     *
     * @param stage o palco principal da aplicação
     * @throws Exception se ocorrer um erro durante o carregamento do arquivo FXML
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Carregando o arquivo FXML da tela inicial
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_BemVinda.fxml"));

        // Criando a cena
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * O método main é o ponto de entrada da aplicação.
     * Ele inicia a aplicação JavaFX.
     *
     * @param args os argumentos de linha de comando
     */
    public static void main(String[] args) {
        launch(args);
    }
}
