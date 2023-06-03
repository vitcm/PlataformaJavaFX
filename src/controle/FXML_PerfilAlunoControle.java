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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_PerfilAlunoControle {

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnVisualizar;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblMeusCursos;

    @FXML
    private Label lblOla;

    @FXML
    private Text lblSubtitulo;

    @FXML
    private Text lblTitulo;

    @FXML
    private Label lblTotalHoras;

    @FXML
    private Label lblTotalValor;

    @FXML
    private Line linha;

    @FXML
    private TableView<?> tbTabela;

    @FXML
    private TableColumn<?, ?> tblCodigo;

    @FXML
    private TableColumn<?, ?> tblCurso;

    @FXML
    void btnAlterarOnAction(ActionEvent event) throws IOException {
        abreJanelaAlteracao();
    }

    @FXML
    void btnExcluirOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Exclusão de perfil");
        alert.setContentText("Deseja mesmo excluir perfil?");
        alert.showAndWait();
        abreAreaLogin();
    }

    @FXML
    void btnVisualizarOnAction(ActionEvent event) throws IOException {
        abreJanelaCursos();
    }

    @FXML
    void tbTabelaOnSort(ActionEvent event) {

    }
    
    public void abreCadastroAluno() throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_CadastroPessoa.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro");
            stage.show();

            // Fechar a janela de login após abrir a janela do administrador
            Stage loginStage = (Stage) btnAlterar.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void abreJanelaCursos() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_ListaCursos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void abreJanelaAlteracao() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_AlteracaoDados.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void abreAreaLogin() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
