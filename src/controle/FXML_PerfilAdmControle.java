package controle;

import dao.AdminDao;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Admin;
import modelo.Curso;

public class FXML_PerfilAdmControle {

    private Admin admin = new Admin();
    private String emailAdm = "";

    public String pessoa = "adm";

    String usuario = "";

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnAtualizaTabela;

    @FXML
    private Button btnAlterarPerfil;

    @FXML
    private Button btnCadastrarCurso;

    @FXML
    private Button btnExcluirCurso;

    @FXML
    private Button btnExcluirPerfil;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAlteracao;

    @FXML
    private Label lblOla;

    @FXML
    private Label lblPublicados;

    @FXML
    private Label lblAlteracao1;

    @FXML
    private Line linha;

    @FXML
    private TableColumn<Integer, Curso> tblCodigo;

    @FXML
    private TableColumn<String, Curso> tblCurso;

    @FXML
    private TableView<Curso> tblTabela;

    @FXML
    private TableColumn<Double, Curso> tblValor;

    @FXML
    private TextField txtAlteracao;

    @FXML
    private Text txtSubtitulo;

    @FXML
    private Text txtTitulo;

    public void initialize(String email, String tipoUsuario) {
        try {
            emailAdm = email;
            lblEmail.setText(email);
            usuario = tipoUsuario;
            carregaTabela();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAlterarOnAction(ActionEvent event) {
        int codigoCurso = Integer.parseInt(txtAlteracao.getText());
        abreJanelaAlteracaoCurso(codigoCurso);
    }

    @FXML
    void btnAlterarPerfilOnAction(ActionEvent event) throws IOException, Exception {
        abreJanelaAlteracao();
    }

    @FXML
    void btnCadastrarCursoOnAction(ActionEvent event) throws IOException {
        abreJanelaCadastroCurso();
    }

    @FXML
    void btnAtualizaTabelaOnAction(ActionEvent event) throws Exception {
        carregaTabela();
    }

    @FXML
    void btnExcluirCursoOnAction(ActionEvent event) throws Exception {
        Curso cursoSelecionado = tblTabela.getSelectionModel().getSelectedItem();
        if (cursoSelecionado != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmação de Exclusão");
            confirmAlert.setHeaderText("Exclusão de curso");
            confirmAlert.setContentText("Deseja mesmo excluir o curso " + cursoSelecionado.getTitulo() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                InscricaoDao inscricaoDao = new InscricaoDao();
                CursoDao cursoDao = new CursoDao();

                if (!inscricaoDao.excluiInscricao(cursoSelecionado.getCodigo())) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erro");
                    errorAlert.setHeaderText("Erro ao excluir curso");
                    errorAlert.setContentText("Ops, não foi possível excluir esse curso");
                    errorAlert.showAndWait();
                } else {
                    if (!cursoDao.excluiCurso(cursoSelecionado.getCodigo())) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Erro");
                        errorAlert.setHeaderText("Erro ao excluir curso");
                        errorAlert.setContentText("Ops, não foi possível excluir esse curso");
                        errorAlert.showAndWait();
                    } else {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Sucesso");
                        successAlert.setHeaderText("Curso excluído com sucesso");
                        successAlert.setContentText("Esse curso foi excluído com sucesso!");
                        successAlert.showAndWait();
                    }
                }
            }
        }

    }

    @FXML
    void btnExcluirPerfilOnAction(ActionEvent event) throws IOException, Exception {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmação de Exclusão");
        confirmAlert.setHeaderText("Exclusão de perfil");
        confirmAlert.setContentText("Deseja mesmo excluir o perfil?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            AdminDao admDao = new AdminDao();
            InscricaoDao inscricaoDao = new InscricaoDao();
            CursoDao cursoDao = new CursoDao();

            // Obtém a lista de cursos do admin
            ArrayList<Curso> cursosDoAdmin = cursoDao.obterCursosDoAdministrador(emailAdm);

            // Exclui as inscrições nos cursos do admin
            for (Curso curso : cursosDoAdmin) {
                if (!inscricaoDao.excluiInscricao(curso.getCodigo())) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erro");
                    errorAlert.setHeaderText("Erro ao excluir perfil");
                    errorAlert.setContentText("Ops, não foi possível excluir seu perfil ):");
                    errorAlert.showAndWait();
                    return; // Para interromper a exclusão do perfil em caso de erro na exclusão das inscrições
                }
            }

            // Exclui os cursos do admin
            for (Curso curso : cursosDoAdmin) {
                if (!cursoDao.excluiCurso(curso.getCodigo())) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erro");
                    errorAlert.setHeaderText("Erro ao excluir perfil");
                    errorAlert.setContentText("Ops, não foi possível excluir seu perfil ):");
                    errorAlert.showAndWait();
                    return; // Para interromper a exclusão do perfil em caso de erro na exclusão dos cursos
                }
            }

            // Exclui o perfil do admin
            if (!admDao.excluiAdmin(emailAdm)) {
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

                Stage loginStage = (Stage) btnExcluirPerfil.getScene().getWindow();
                loginStage.close();

                abreAreaLogin();
            }
        }
    }

    @FXML
    void tblTabelaOnSort(ActionEvent event) {

    }

    public void abreJanelaCadastroCurso() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_CadastroCurso.fxml"));
            Parent root = loader.load();

            FXML_CadastroCursoControle cadastroCursoControle = loader.getController();
            cadastroCursoControle.getEmail(emailAdm);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Alteração");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abreJanelaAlteracao() throws IOException, Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_AlteracaoDados.fxml"));
            Parent root = loader.load();

            FXML_AlteracaoDadosControle alteracaoDadosControle = loader.getController();
            alteracaoDadosControle.initialize(emailAdm, usuario);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro Curso");
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
        CursoDao cursoDao = new CursoDao();
        ArrayList<Curso> cursos = cursoDao.obterCursosDoAdministrador(emailAdm);

        ObservableList<Curso> dadosTabela = FXCollections.observableArrayList(cursos);

        System.out.println(emailAdm);

        tblCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tblCurso.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tblValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tblTabela.setItems(dadosTabela);
    }

    public void abreJanelaAlteracaoCurso(int codigoCurso) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_AlteracaoCurso.fxml"));
            Parent root = loader.load();

            FXML_AlteracaoCursoControle alteracaoCursoControle = loader.getController();
            alteracaoCursoControle.initialize(codigoCurso);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Alteracai Curso");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
