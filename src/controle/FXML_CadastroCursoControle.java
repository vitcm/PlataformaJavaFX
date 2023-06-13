package controle;

import dao.AreaDao;
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
import modelo.Area;
import modelo.Curso;
import negocio.CursoNegocio;

public class FXML_CadastroCursoControle {
    
    private String emailAdmin ="";

    CursoNegocio curson = new CursoNegocio();

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCriarArea;

    @FXML
    private ComboBox<String> cbxArea;

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
    private Label lblErroArea;

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
    void btnCadastrarOnAction(ActionEvent event) throws Exception {
        String titulo = txtTituloCurso.getText();
        String pchave = txtPalavraChave.getText();
        int choraria = Integer.parseInt(txtCargaHoraria.getText());
        double valor = Double.parseDouble(txtValor.getText());
        int cont = 0;
        if (!curson.verificaNomeVazio(titulo)) {
            txtTituloCurso.setText("Favor, inserir nome para o curso!");
            cont++;
        }
        if (!curson.verificaPalavraChaveVazia(pchave)) {
            txtPalavraChave.setText("Favor, inserir palavras chave para o curso!");
            cont++;
        }
        if (!curson.verificaChorariaVazia(choraria)) {
            txtCargaHoraria.setText("Favor, inserir carga horária válida!");
            cont++;
        }
        if (!curson.verificaValorVazio(valor)) {
            txtValor.setText("Favor, inserir valor válido!");
            cont++;
        }
        if (cbxArea.getSelectionModel().isEmpty()) {
            cont++;
            lblErroArea.setText("Escolha uma área!");
        }
        if (cont == 0) {
            if (adicionaCurso()) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Sucesso");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Cadastro realizado com sucesso!");
                successAlert.showAndWait();
                Stage loginStage = (Stage) btnCadastrar.getScene().getWindow();
                loginStage.close();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erro");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Falha ao cadastrar.");
                errorAlert.showAndWait();
            }
        }
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

    public void abreCadastroArea() throws IOException {
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

    public boolean adicionaCurso() throws Exception {
        boolean retorno = false;
        String nome = txtTituloCurso.getText();
        String pchave = txtPalavraChave.getText();
        int choraria = Integer.parseInt(txtCargaHoraria.getText());
        double valor = Double.parseDouble(txtValor.getText());
        int codigoArea = recuperaCodigoArea();

        try {
            boolean cursoExistente = verificaCursoExistente(nome);
            if (cursoExistente) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Curso já existe");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Ops! Esse curso já existe, não é possível duplicar.");
                successAlert.showAndWait();
                return false;
            }
            Curso curso = new Curso();
            curso.setTitulo(nome);
            curso.setPalavra_chave_curso(pchave);
            curso.setC_horaria(choraria);
            curso.setValor(valor);

            CursoDao cursoDao = new CursoDao();
            retorno = cursoDao.adicionaCurso(curso, codigoArea, emailAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public int recuperaCodigoArea() throws Exception {
        String nomeAreaSelecionada = cbxArea.getValue();
        AreaDao areaDao = new AreaDao();
        int codigoArea = areaDao.recuperarCodigoArea(nomeAreaSelecionada);
        return codigoArea;
    }

    public boolean verificaCursoExistente(String nome) {
        boolean areaExistente = false;
        try {
            CursoDao cursoDao = new CursoDao();
            areaExistente = cursoDao.verificaCursoExistente(nome);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return areaExistente;
    }

    public void preencherComboBoxAreas() throws Exception {
        AreaDao areadao = new AreaDao();
        ArrayList<String> nomesAreas = areadao.obterNomesAreas();
        ObservableList<String> listaNomesAreas = FXCollections.observableArrayList(nomesAreas);
        cbxArea.setItems(listaNomesAreas);
    }

    public void getEmail(String emailAdm) {
        emailAdmin = emailAdm;
    }
    
    public void initialize() {
        try {
            preencherComboBoxAreas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
