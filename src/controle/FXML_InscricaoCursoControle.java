package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_InscricaoCursoControle {

    
    @FXML
    private Button btnInscrever;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblDetalhes;

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblSenha;

    @FXML
    private Text lblTitulo;

    @FXML
    private Text lblTitulo1;

    @FXML
    private Line linha;


    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtSenha;

    @FXML
    void btnInscreverOnAction(ActionEvent event) {
        Stage loginStage = (Stage) btnInscrever.getScene().getWindow();
        loginStage.close();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText("Sucesso");
        alert.setContentText("Parabéns pela inscrição!");
        alert.showAndWait();
    }

    @FXML
    void txtLoginOnAction(ActionEvent event) {

    }

    @FXML
    void txtSenhaOnAction(ActionEvent event) {

    }

}
