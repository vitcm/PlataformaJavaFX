package controle;

import dao.AlunaDao;
import dao.AreaDao;
import dao.CursoDao;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
import modelo.Aluna;
import modelo.Curso;

public class FXML_ListaCursosControle {
    
    private String emailAl = "";

    private ArrayList<Curso> cursos; // Lista com todos os cursos

    @FXML
    private Button btnAtualiza;

    @FXML
    private Button btnInscricao;

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

    @FXML
    void btnAtualizaOnAction(ActionEvent event) throws Exception {
        txtCursoOnAction(null);
    }

    public void initialize(String email) {
        try {
            emailAl = email;
            carregaTabela();
            preencherComboBoxAreas();
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

//    @FXML
//    void tabelaOnMouseClicked(MouseEvent event) {
////        if (event.getClickCount() == 1) { // Verifica se foi um clique simples
////            Curso cursoSelecionado = tabela.getSelectionModel().getSelectedItem();
////            if (cursoSelecionado != null) {
////                btnInscricao.setDisable(false); // Habilita o botão de inscrição
////            } else {
////                btnInscricao.setDisable(true); // Desabilita o botão de inscrição se nenhum curso estiver selecionado
////            }
////        }
//    }

    @FXML
    void tabelaOnSort(ActionEvent event) {

    }

    @FXML
    void txtCursoOnAction(ActionEvent event) {
        String criterioSelecionado = cbxCursos.getValue();
        String pesquisa = txtCurso.getText().trim();

        if (criterioSelecionado != null && !pesquisa.isEmpty()) {
            ArrayList<Curso> cursosFiltrados = new ArrayList<>();

            for (Curso curso : cursos) {
                boolean cursoCorrespondeAoCriterio = false;

                if (criterioSelecionado.equals("Código") && String.valueOf(curso.getCodigo()).equals(pesquisa)) {
                    cursoCorrespondeAoCriterio = true;
                } else if (criterioSelecionado.equals("Título") && curso.getTitulo().contains(pesquisa)) {
                    cursoCorrespondeAoCriterio = true;
                }
                if (cursoCorrespondeAoCriterio) {
                    cursosFiltrados.add(curso);
                }
            }
            atualizarTabela(cursosFiltrados);
        }
    }

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

    private void atualizarTabela(ArrayList<Curso> cursosFiltrados) {
        // Limpa a tabela atual
        tabela.getItems().clear();

        // Adiciona os cursos filtrados à tabela
        tabela.getItems().addAll(cursosFiltrados);
    }

    public void preencherComboBoxAreas() throws Exception {
        ObservableList<String> criterios = FXCollections.observableArrayList(
                "Código",
                "Título"
        );
        cbxCursos.setItems(criterios);
    }
}
