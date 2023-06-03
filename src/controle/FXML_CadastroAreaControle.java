package controle;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_CadastroAreaControle {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblPalavrasChave;

    @FXML
    private Line linha;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPalavrasChave;

    @FXML
    private Text txtSubtitulo;

    @FXML
    private Text txtTitulo;

    @FXML
    void btnCadastrarOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Sucesso!");
        alert.setContentText("Parabéns, você cadastrou uma nova área.");
        alert.showAndWait();
        Stage loginStage = (Stage) btnCadastrar.getScene().getWindow();
        loginStage.close();
    }

    @FXML
    void txtNomeOnAction(ActionEvent event) {

    }

    @FXML
    void txtPalavrasChaveOnAction(ActionEvent event) {

    }


}
