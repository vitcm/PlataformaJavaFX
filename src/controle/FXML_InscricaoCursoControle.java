package controle;

import dao.AreaDao;
import dao.InscricaoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Area;
import modelo.Curso;
import negocio.AlunaNegocio;

public class FXML_InscricaoCursoControle {

    private String email;
    private int codigoCurso;
    
    AlunaNegocio alunaN = new AlunaNegocio();

    @FXML
    private Button btnInscrever;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblDetalhes;

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblSenha;

    @FXML
    private Text lblTitulo;

    @FXML
    private Text lblTitulo1;

    @FXML
    private Line linha;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtSenha;

    public void initialize(Curso curso, String emailAl) {
        try {
            email = emailAl;
            codigoCurso = curso.getCodigo();
            defineCurso(curso);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnInscreverOnAction(ActionEvent event) throws Exception {
        String email = txtLogin.getText();
        String senha = txtSenha.getText();
        int cont = 0;
        if (!alunaN.verificaEmailVazio(email)) {
            txtLogin.setText("Favor, inserir email!");
            cont++;
        }
        if (!alunaN.verificaSenhaVazio(senha)) {
            txtSenha.setText("Favor, inserir sua senha!");
            cont++;
        }
        if (cont == 0) {
            if (adicionaInscricao()) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Sucesso");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Inscrição realizada com sucesso!");
                successAlert.showAndWait();
                Stage loginStage = (Stage) btnInscrever.getScene().getWindow();
                loginStage.close();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erro");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Falha ao inscrever.");
                errorAlert.showAndWait();
            }
        }
    }

    @FXML
    void txtLoginOnAction(ActionEvent event) {

    }

    @FXML
    void txtSenhaOnAction(ActionEvent event) {

    }

    public void defineCurso(Curso curso) throws Exception {
        System.out.println(curso.getTitulo());
        String titulo = curso.getTitulo();
        int choraria = curso.getC_horaria();
        double valor = curso.getValor();
        String pchave = curso.getPalavra_chave_curso();
        int area = curso.getArea();

//        AreaDao areaDao = new AreaDao();
//        String nomeArea = "";
//        nomeArea = areaDao.pesquisaAreaPorCodigo(area);

        String detalhes = "Curso: " + titulo + "\n" + "Palavras Chave: " + pchave + "\n" + "Valor: R$" + valor
                + "\n" + "Carga Horária: " + choraria;

        lblDetalhes.setText(detalhes);
    }
    
    public boolean adicionaInscricao() throws Exception {
        boolean retorno = false;
        String email = txtLogin.getText();
        String senha = txtSenha.getText();
        try {
            boolean validaDadosLogin = validaDadosLogin(email, senha);
            if (!validaDadosLogin) {
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setTitle("Login errado");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Ops! Seu email ou senha estão errados. Favor, tente novamente.");
                errorAlert.showAndWait();
                return false;
            }
            InscricaoDao inscricaoDao = new InscricaoDao();
            retorno = inscricaoDao.adicionaCurso(codigoCurso, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    private boolean validaDadosLogin(String email, String senha) throws Exception { 
        return (alunaN.validaDadosLogin(email, senha));
    }

}
