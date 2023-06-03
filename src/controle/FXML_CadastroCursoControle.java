package controle;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_CadastroCursoControle {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCriarArea;

    @FXML
    private ComboBox<?> cbxArea;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblArea;

    @FXML
    private Label lblCadastrarArea;

    @FXML
    private Label lblCargaHoraria;

    @FXML
    private Label lblPalavraChave;

    @FXML
    private Text lblSubtitulo;

    @FXML
    private Text lblTitulo;

    @FXML
    private Label lblTituloCurso;

    @FXML
    private Label lblValor;

    @FXML
    private Line linha;

    @FXML
    private TextField txtCargaHoraria;

    @FXML
    private TextField txtPalavraChave;

    @FXML
    private TextField txtTituloCurso;

    @FXML
    private TextField txtValor;

    @FXML
    void btnCadastrarOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText("Sucesso!");
        alert.setContentText("Parabéns, você cadastrou um novo curso!");
        alert.showAndWait();
    }

    @FXML
    void btnCriarAreaOnAction(ActionEvent event) throws IOException {
        abreCadastroArea();
    }

    @FXML
    void cbxAreaOnAction(ActionEvent event) {

    }

    @FXML
    void txtCargaHorariaOnAction(ActionEvent event) {

    }

    @FXML
    void txtPalavraChaveOnAction(ActionEvent event) {

    }

    @FXML
    void txtTituloCursoOnAction(ActionEvent event) {

    }

    @FXML
    void txtValorOnAction(ActionEvent event) {

    }
    
    public void abreCadastroArea() throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_CadastroArea.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro de área");
            stage.show();

            
            Stage loginStage = (Stage) btnCriarArea.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
