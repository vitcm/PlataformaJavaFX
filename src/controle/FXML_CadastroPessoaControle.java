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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_CadastroPessoaControle {

    @FXML
    private Text Subtitulo;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSobrenome;

    @FXML
    private Text lblTitulo;

    @FXML
    private Line linha;

    @FXML
    private RadioButton rbtEstudante;

    @FXML
    private RadioButton rbtProfessor;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSobrenome;
    
    @FXML
    void btnCadastrarOnAction(ActionEvent event) throws IOException {
        if (rbtProfessor.isSelected() || rbtEstudante.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione tipo");
            alert.setContentText("Sucesso! Bem vinda(o) ao Vitorya Cursos.");
            alert.showAndWait();
            abreAreaLogin();
            Stage loginStage = (Stage) btnCadastrar.getScene().getWindow();
            loginStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione tipo");
            alert.setContentText("Nenhuma opção foi selecionada. Você é aluno ou administrador?");
            alert.showAndWait();
        }
    }

    @FXML
    void rbtEstudanteOnAction(ActionEvent event) {
        rbtProfessor.setSelected(false);
    }

    @FXML
    void rbtProfessorOnAction(ActionEvent event) {
        rbtEstudante.setSelected(false);
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtNomeOnAction(ActionEvent event) {

    }

    @FXML
    void txtSenhaOnAction(ActionEvent event) {

    }

    @FXML
    void txtSobrenomeOnAction(ActionEvent event) {

    }
    
    public void abreAreaLogin() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
