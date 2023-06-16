package controle;

import dao.AdminDao;
import dao.AlunaDao;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Admin;
import modelo.Aluna;

public class FXML_AlteracaoDadosControle {

    private String emailInit = "";
    private String nome = "";
    private String sobrenome = "";
    private String senha = "";

    private enum TipoUsuario {
        ADMIN,
        ALUNA
    }
    
    String tipoUsu="";

    private TipoUsuario tipoUsuario;

    @FXML
    private Text Subtitulo;

    @FXML
    private Button btnAlterar;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSobrenome;

    @FXML
    private Text lblTitulo1;

    @FXML
    private Text lblTituloCopia;

    @FXML
    private Line linha;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSobrenome;
    
    public void initialize(String email, String usuario) {
        try {
            emailInit = email;
            tipoUsu = usuario;
            if (tipoUsu.equals("admin")) {
                setDadosLoginAdmin(emailInit);
            }
            if (tipoUsu.equals("aluna")) {
                setDadosLoginAluna(emailInit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAlterarOnAction(ActionEvent event) throws IOException, Exception {
        abreJanelaConfirmação();
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

    public void abreJanelaConfirmação() throws IOException, Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(txtNome.getScene().getWindow());
        alert.setTitle("Confirmação de Alteração");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja alterar seus dados?");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    realizarAlteracao();
                } catch (Exception e) {
                    exibirMensagemErro();
                }
            }
        });
    }

    public void realizarAlteracao() throws Exception {
        if (tipoUsuario == TipoUsuario.ADMIN) {
            Admin adm = new Admin();
            adm.setNomeAdmin(txtNome.getText());
            adm.setSobrenomeAdmin(txtSobrenome.getText());
            adm.setSenhaAdmin(txtSenha.getText());

            AdminDao admDao = new AdminDao();
            boolean sucesso = admDao.alteraAdmin(adm, emailInit);
            System.out.println("TEEEEESTE"+emailInit);

            if (sucesso) {
                exibirMensagemSucesso();
            } else {
                exibirMensagemErro();
            }
        }
        if (tipoUsuario == TipoUsuario.ALUNA) {
            Aluna aluna = new Aluna();
            aluna.setNomeAluna(txtNome.getText());
            aluna.setSobrenomeAluna(txtSobrenome.getText());
            aluna.setSenhaAluna(txtSenha.getText());

            AlunaDao alunaDao = new AlunaDao();
            boolean sucesso = alunaDao.alteraAluna(aluna, emailInit);
            System.out.println(emailInit);

            if (sucesso) {
                exibirMensagemSucesso();
            } else {
                exibirMensagemErro();
            }
        }

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

    //pega os dados do Perfil para inserir nos campos de texto
    public void setDadosLoginAdmin(String email) throws Exception {
        AdminDao admDao = new AdminDao();
        Admin adm = new Admin();
        adm = admDao.pesquisaContato(email);
        nome = adm.getNomeAdmin();
        sobrenome = adm.getSobrenomeAdmin();
        email = adm.getEmailAdmin();
        senha = adm.getSenhaAdmin();
        System.out.println(nome + sobrenome + email + senha);
        txtNome.setText(nome);
        txtSobrenome.setText(sobrenome);
        txtSenha.setText(senha);
        tipoUsuario = TipoUsuario.ADMIN;
    }

    public void setDadosLoginAluna(String email) throws Exception {
        AlunaDao alDao = new AlunaDao();
        Aluna al = new Aluna();
        al = alDao.pesquisaContato(email);
        nome = al.getNomeAluna();
        sobrenome = al.getSobrenomeAluna();
        email = al.getEmailAluna();
        senha = al.getSenhaAluna();
        System.out.println(nome + sobrenome + email + senha);
        txtNome.setText(nome);
        txtSobrenome.setText(sobrenome);
        txtSenha.setText(senha);
        tipoUsuario = TipoUsuario.ALUNA;
    }
}
