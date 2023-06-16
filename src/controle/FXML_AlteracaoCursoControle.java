package controle;

import dao.AdminDao;
import dao.AlunaDao;
import dao.CursoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Admin;
import modelo.Aluna;
import modelo.Curso;
import negocio.CursoNegocio;

public class FXML_AlteracaoCursoControle {

    private CursoNegocio curson = new CursoNegocio();

    private int codCurso;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblCargaHoraria;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblErroArea;

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
    private TextArea txtPalavraChave;

    @FXML
    private TextField txtTituloCurso;

    @FXML
    private TextField txtValor;

    public void initialize(int codigoCurso) {
        try {
            codCurso = codigoCurso;
            setDadosCurso(codigoCurso);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnCadastrarOnAction(ActionEvent event) throws Exception {
        realizarAlteracao();
    }

    @FXML
    void cbxAreaOnAction(ActionEvent event) {

    }

    @FXML
    void txtCargaHorariaOnAction(ActionEvent event) {

    }

    @FXML
    void txtPalavraChaveOnAction(MouseEvent event) {

    }

    @FXML
    void txtTituloCursoOnAction(ActionEvent event) {

    }

    @FXML
    void txtValorOnAction(ActionEvent event) {

    }

    public void realizarAlteracao() throws Exception {
        String titulo = txtTituloCurso.getText();
        String pchave = txtPalavraChave.getText();
        String choraria = txtCargaHoraria.getText();
        String valor = txtValor.getText();
        int cont = 0;
        if (!curson.verificaNomeVazio(titulo)) {
            txtTituloCurso.setText("Favor, inserir nome para o curso!");
            cont++;
        }
        if (!curson.verificaPalavraChaveVazia(pchave)) {
            txtPalavraChave.setText("Favor, inserir palavras chave para o curso!");
            cont++;
        }
        if (!curson.verificaChoraria(choraria)) {
            txtCargaHoraria.setText("Favor, inserir carga horária válida!");
            cont++;
        }
        if (!curson.verificaValor(valor)) {
            txtValor.setText("Favor, inserir valor válido!");
            cont++;
        }
        if (cont == 0) {
            if (alteraCurso(titulo, pchave, choraria, valor)) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Sucesso");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Alteração realizada com sucesso!");
                successAlert.showAndWait();
                
                Stage loginStage = (Stage) btnCadastrar.getScene().getWindow();
                loginStage.close();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erro");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Falha ao alterar.");
                errorAlert.showAndWait();
            }
        }
    }

    public void setDadosCurso(int codCurso) throws Exception {
        CursoDao cursoDao = new CursoDao();
        Curso curso = new Curso();
        curso = cursoDao.obterCursoPorCodigo(codCurso);
        String titulo = curso.getTitulo();
        String pchave = curso.getPalavra_chave_curso();
        String choraria = String.valueOf(curso.getC_horaria());
        String valor = String.valueOf(curso.getValor());
        txtTituloCurso.setText(titulo);
        txtPalavraChave.setText(pchave);
        txtCargaHoraria.setText(choraria);
        txtValor.setText(valor);
    }

    public void exibirMensagemSucesso() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Os dados foram alterados com sucesso!");
        alert.showAndWait();
    }

    public void exibirMensagemErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Ocorreu um erro ao alterar os dados. Por favor, tente novamente.");
        alert.showAndWait();
    }

    public boolean alteraCurso(String titulo, String pchave, String choraria, String valor) throws Exception {
        Curso curso = new Curso();
        curso.setTitulo(titulo);
        curso.setPalavra_chave_curso(pchave);
        curso.setC_horaria(Integer.parseInt(choraria));
        curso.setValor(Double.parseDouble(valor));

        CursoDao cursoDao = new CursoDao();
        boolean sucesso = cursoDao.alteraCurso(curso, codCurso);
        return sucesso;
    }

}
