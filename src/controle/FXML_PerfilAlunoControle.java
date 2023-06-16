package controle;

import dao.AlunaDao;
import dao.CursoDao;
import dao.InscricaoDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Aluna;
import modelo.Curso;

public class FXML_PerfilAlunoControle {

    private Aluna aluna = new Aluna();
    private String emailAluna = "";

    String usuario = "";

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnAtualizaTabela;

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
    private Line linha;

    @FXML
    private TableView<Curso> tbTabela;

    @FXML
    private TableColumn<Integer, Curso> tblCodigo;

    @FXML
    private TableColumn<String, Curso> tblCurso;

    public void initialize(String email, String tipoUsuario) {
        try {
            emailAluna = email;
            lblEmail.setText(email);
            usuario = tipoUsuario;
            carregaTabela();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAlterarOnAction(ActionEvent event) throws IOException, Exception {
        abreJanelaAlteracao();
    }

    @FXML
    void btnAtualizaTabelaOnAction(ActionEvent event) throws Exception {
        carregaTabela();
    }

    @FXML
    void btnExcluirOnAction(ActionEvent event) throws IOException, Exception {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmação de Exclusão");
        confirmAlert.setHeaderText("Exclusão de perfil");
        confirmAlert.setContentText("Deseja mesmo excluir o perfil?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            AlunaDao alDao = new AlunaDao();
            InscricaoDao inscDao = new InscricaoDao();

            if (!inscDao.excluiInscricaoAluna(emailAluna)) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erro");
                errorAlert.setHeaderText("Erro ao excluir perfil");
                errorAlert.setContentText("Ops, não foi possível excluir seu perfil ):");
                errorAlert.showAndWait();
            } else {
                if (!alDao.excluiAluna(emailAluna)) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erro");
                    errorAlert.setHeaderText("Erro ao excluir perfil");
                    errorAlert.setContentText("Ops, não foi possível excluir seu perfil ):");
                    errorAlert.showAndWait();
                } else {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Sucesso");
                    successAlert.setHeaderText("Perfil excluído com sucesso");
                    successAlert.setContentText("Seu perfil foi excluído com sucesso!");
                    successAlert.showAndWait();

                    Stage loginStage = (Stage) btnExcluir.getScene().getWindow();
                    loginStage.close();
                    abreAreaLogin();
                }
            }
        }
    }

    @FXML
    void btnVisualizarOnAction(ActionEvent event) throws IOException, Exception {
        abreJanelaCursos();
    }

    @FXML
    void tbTabelaOnSort(ActionEvent event) {

    }

    public void abreJanelaCursos() throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_ListaCursos.fxml"));
        Parent root = loader.load();

        FXML_ListaCursosControle listaCursosControle = loader.getController();
        listaCursosControle.initialize(emailAluna);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void abreJanelaAlteracao() throws IOException, Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_AlteracaoDados.fxml"));
            Parent root = loader.load();

            FXML_AlteracaoDadosControle alteracaoDadosControle = loader.getController();
            alteracaoDadosControle.initialize(emailAluna, usuario);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Alteração");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abreAreaLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void carregaTabela() throws Exception {
        InscricaoDao inscricaoDao = new InscricaoDao();
        CursoDao cursoDao = new CursoDao();

        ArrayList<Integer> codigosCursos = inscricaoDao.obterCodigosCursosDoAluno(emailAluna);
        ArrayList<Curso> cursos = new ArrayList<>();

        for (int codigoCurso : codigosCursos) {
            Curso curso = cursoDao.obterCursoPorCodigo(codigoCurso);
            if (curso != null) {
                cursos.add(curso);
            }
        }

        ObservableList<Curso> dadosTabela = FXCollections.observableArrayList(cursos);

        tblCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tblCurso.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        tbTabela.setItems(dadosTabela);
    }

}
