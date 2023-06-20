package controle;

import conexao.Conexao;
import dao.AlunaDao;
import dao.AreaDao;
import dao.CursoDao;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JDialog;
import modelo.Aluna;
import modelo.Curso;


/**
 * Classe de controle para a interface de listagem de cursos.
 */
public class FXML_ListaCursosControle {

    private String emailAl = "";

    private ArrayList<Curso> cursos; // Lista com todos os cursos

    @FXML
    private Button btnAtualiza;

    @FXML
    private Button btnInscricao;

    @FXML
    private Button btnLimpa;

    @FXML
    private Button btnRelatorio;

    @FXML
    private ComboBox<String> cbxCursos;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblSubtitulo;

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
    private Label lblDigiteCurso;

    @FXML
    private TextField txtCurso;

//    @FXML
//    void btnRelatorioOnAction(ActionEvent event) throws Exception {
//        getPdf();
//    }

    @FXML
    void btnAtualizaOnAction(ActionEvent event) throws Exception {
        txtCursoOnAction(null);
    }

    @FXML
    void btnLimpaOnAction(ActionEvent event) {
        cbxCursos.getSelectionModel().clearSelection();
        txtCurso.clear();
        atualizarTabela(cursos); // Restaurar a lista original de cursos
    }

    /**
     * Inicializa a interface de listagem de cursos com o email da aluna.
     *
     * @param email O email da aluna.
     */
    public void initialize(String email) {
        try {
            emailAl = email;
            carregaTabela();
            preencherComboBoxAreas();

            // Adicionar listener na ComboBox
            cbxCursos.valueProperty().addListener((observable, oldValue, newValue) -> {
                atualizarBtnAtualiza();
            });

            // Adicionar listener no TextField
            txtCurso.textProperty().addListener((observable, oldValue, newValue) -> {
                atualizarBtnAtualiza();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnInscricaoOnAction(ActionEvent event) throws IOException {
        Curso cursoSelecionado = tabela.getSelectionModel().getSelectedItem();
        if (cursoSelecionado != null) {
            abreJanelaInscricao(cursoSelecionado, emailAl);
        }
    }

    @FXML
    void cbxCursosOnAction(ActionEvent event) throws Exception {

    }

    @FXML
    void tabelaOnSort(ActionEvent event) {

    }

    @FXML
    void txtCursoOnAction(ActionEvent event) {
        String criterioSelecionado = cbxCursos.getValue();
        String pesquisa = txtCurso.getText().trim().toLowerCase(); // Converter para letras minúsculas

        if (criterioSelecionado != null && !pesquisa.isEmpty()) {
            ArrayList<Curso> cursosFiltrados = new ArrayList<>();

            for (Curso curso : cursos) {
                boolean cursoCorrespondeAoCriterio = false;

                if (criterioSelecionado.equals("Código") && String.valueOf(curso.getCodigo()).equals(pesquisa)) {
                    cursoCorrespondeAoCriterio = true;
                } else if (criterioSelecionado.equals("Título") && curso.getTitulo().toLowerCase().contains(pesquisa)) {
                    cursoCorrespondeAoCriterio = true;
                }
                if (cursoCorrespondeAoCriterio) {
                    cursosFiltrados.add(curso);
                }
            }
            atualizarTabela(cursosFiltrados);
        }
    }

    /**
     * Abre a janela de inscrição em um curso.
     *
     * @param curso O curso selecionado.
     * @param emailAl O email da aluna.
     * @throws IOException Se ocorrer um erro ao abrir a janela de inscrição.
     */
    public void abreJanelaInscricao(Curso curso, String emailAl) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_InscricaoCurso.fxml"));
        Parent root = loader.load();

        FXML_InscricaoCursoControle inscricaoCurso = loader.getController();
        inscricaoCurso.initialize(curso, emailAl);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Carrega a tabela de cursos.
     *
     * @throws Exception Se ocorrer um erro ao carregar a tabela.
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
     * Atualiza a tabela de cursos com os cursos filtrados.
     *
     * @param cursosFiltrados Os cursos filtrados.
     */
    private void atualizarTabela(ArrayList<Curso> cursosFiltrados) {
        // Limpa a tabela atual
        tabela.getItems().clear();

        // Adiciona os cursos filtrados à tabela
        tabela.getItems().addAll(cursosFiltrados);
    }

    /**
     * Preenche a ComboBox de critérios de pesquisa de cursos.
     *
     * @throws Exception Se ocorrer um erro ao preencher a ComboBox.
     */
    // combobox para limitar os tipos de pesquisa 
    public void preencherComboBoxAreas() throws Exception {
        ObservableList<String> criterios = FXCollections.observableArrayList(
                "Código",
                "Título"
        );
        cbxCursos.setItems(criterios);
    }

    /**
     * Atualiza o estado do botão "Atualizar".
     */
    private void atualizarBtnAtualiza() {
        String criterioSelecionado = cbxCursos.getValue();
        String pesquisa = txtCurso.getText().trim();

        // Verifica se a ComboBox e o TextField estão preenchidos
        boolean isPreenchido = criterioSelecionado != null && !pesquisa.isEmpty();

        // o botão só funciona se estiver a cbx e o campo de texto preenchidos
        btnAtualiza.setDisable(!isPreenchido);
    }

    //RELATÓRIO
//    private static final String path = "src\\view\\Simples.jasper";
//
//    public static void getPdf() throws Exception {
//
//        Connection con = Conexao.getConnection();
//        JasperPrint jp = JasperFillManager.fillReport(path, null, con);
//        JasperViewer.viewReport(jp, false);
//    }
}
