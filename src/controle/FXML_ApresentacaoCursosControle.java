package controle;

import dao.CursoDao;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Curso;

/**
 * Controlador para a interface de apresentação de cursos.
 */
public class FXML_ApresentacaoCursosControle {

    private ArrayList<Curso> cursos; // Lista com todos os cursos

    @FXML
    private Button btnAtualiza;

    @FXML
    private Button btnInscricao;

    @FXML
    private Button btnLimpa;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblDigiteCurso;

    @FXML
    private Text lblTitulo;

    @FXML
    private Text lblTitulo1;

    @FXML
    private Line linha;

    @FXML
    private TableView<Curso> tabela;

    @FXML
    private TableColumn<Curso, Integer> tabelaCHoraria;

    @FXML
    private TableColumn<Curso, Integer> tabelaCod;

    @FXML
    private TableColumn<Curso, String> tabelaCurso;

    @FXML
    private TableColumn<Curso, Double> tabelaValor;

    @FXML
    private TextField txtCurso;

    /**
     * Inicializa o controlador.
     */
    public void initialize() {
        try {
            carregaTabela();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLimpaOnAction(ActionEvent event) {
        txtCurso.clear();
        atualizarTabela(cursos);
    }

    @FXML
    void btnAtualizaOnAction(ActionEvent event) {
        String tituloCurso = txtCurso.getText().trim().toLowerCase(); // Converter para letras minúsculas

        ObservableList<Curso> cursosFiltrados = FXCollections.observableArrayList();
        for (Curso curso : cursos) {
            if (curso.getTitulo().toLowerCase().contains(tituloCurso)) {
                cursosFiltrados.add(curso);
            }
        }
        tabela.setItems(cursosFiltrados); // Define os cursos filtrados na tabela
    }

    @FXML
    void btnInscricaoOnAction(ActionEvent event) throws IOException {
        abreAreaLogin();
    }

    @FXML
    void tabelaOnSort(ActionEvent event) {

    }

    @FXML
    void txtCursoOnAction(ActionEvent event) {

    }

    /**
     * Carrega os dados da tabela de cursos.
     *
     * @throws Exception Exceção caso ocorra um erro durante a operação.
     */
    public void carregaTabela() throws Exception {
        CursoDao cursoDao = new CursoDao();
        cursos = cursoDao.obterCursos();

        ObservableList<Curso> dadosTabela = FXCollections.observableArrayList(cursos);

        tabelaCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tabelaCurso.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tabelaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tabelaCHoraria.setCellValueFactory(new PropertyValueFactory<>("c_horaria"));

        tabela.setItems(dadosTabela);
    }

    /**
     * Abre a área de login.
     *
     * @throws IOException Exceção caso ocorra um erro durante a operação.
     */
    public void abreAreaLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage loginStage = (Stage) btnInscricao.getScene().getWindow();
        loginStage.close();
    }

    private void atualizarTabela(ArrayList<Curso> cursosFiltrados) {
        // Limpa a tabela atual
        tabela.getItems().clear();

        // Adiciona os cursos filtrados à tabela
        tabela.getItems().addAll(cursosFiltrados);
    }

}
