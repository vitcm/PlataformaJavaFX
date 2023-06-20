package controle;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import negocio.AdminNegocio;
import negocio.AlunaNegocio;

/**
 * Controlador responsável pela interface de login.
 */
public class FXML_LoginControle {

    private ToggleGroup toggleGroup; //criar um toggleGroup para ajustar o radioButton - selecionar só um por vez.
    AdminNegocio adn = new AdminNegocio();
    AlunaNegocio aln = new AlunaNegocio();

    String tipoUsuario = "";

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnEntrar;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle fundo1;

    @FXML
    private Rectangle fundo11;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblSenha;

    @FXML
    private Text lblSubtitulo;

    @FXML
    private Text lblTitulo1;

    @FXML
    private Text lblTitulo2;

    @FXML
    private RadioButton rbtAdmin;

    @FXML
    private RadioButton rbtEstudante;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    void btnCadastroOnAction(ActionEvent event) throws IOException {
        abreAreaCadastro();
    }

    @FXML
    void btnEntrarOnAction(ActionEvent event) throws IOException, Exception {
//        String email = txtEmail.getText();
//        String senha = txtSenha.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        int cont = 0;
        if (rbtAdmin.isSelected()) {
            tipoUsuario = "admin";
            if (!adn.verificaEmailVazio(email)) {
                txtEmail.setText("Favor, completar o email!");
                cont++;
            }
            if (!adn.verificaSenhaVazio(senha)) {
                txtSenha.setText("Favor, completar a senha!");
                cont++;
            }
            if (cont == 0) {
                abreAreaPerfilAdmin(email, senha, tipoUsuario);
            }
        } else if (rbtEstudante.isSelected()) {
            tipoUsuario = "aluna";
            if (!aln.verificaEmailVazio(email)) {
                txtEmail.setText("Favor, completar o email!");
                cont++;
            }
            if (!aln.verificaSenhaVazio(senha)) {
                txtSenha.setText("Favor, completar a senha!");
                cont++;
            }
            if (cont == 0) {
                abreAreaPerfilAluno(email, senha, tipoUsuario);
            }

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione tipo");
            alert.setContentText("Nenhuma opção foi selecionada. Você é aluno ou administrador?");
            alert.showAndWait();
        }
    }

    @FXML
    void rbtAdminOnAction(ActionEvent event) {
        rbtEstudante.setSelected(false);
    }

    @FXML
    void rbtEstudanteOnAction(ActionEvent event) {
        rbtAdmin.setSelected(false);
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtSenhaOnAction(ActionEvent event) {

    }

    /**
     * Abre a área de cadastro.
     *
     * @throws IOException Se ocorrer um erro ao abrir a área de cadastro.
     */
    public void abreAreaCadastro() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_CadastroPessoa.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            // Fechar a janela de login após abrir a janela do administrador
            Stage loginStage = (Stage) btnEntrar.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre a área de perfil do aluno.
     *
     * @param email O email do aluno.
     * @param senha A senha do aluno.
     * @param tipoUsuario O tipo de usuário (aluno ou administrador).
     * @throws IOException Se ocorrer um erro ao abrir a área de perfil do
     * aluno.
     * @throws Exception Se ocorrer um erro ao validar os dados de login do
     * aluno.
     */
    public void abreAreaPerfilAluno(String email, String senha, String tipoUsuario) throws IOException, Exception {
        try {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alerta");

            if (!aln.validaDadosLogin(email, senha)) {
                alert.setHeaderText("Erro!");
                alert.setContentText("Ops. Email ou senha errados! Favor verificar.");
                alert.showAndWait();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_PerfilAluno.fxml"));
                Parent root = loader.load();

                FXML_PerfilAlunoControle perfilAlnControle = loader.getController();
                perfilAlnControle.initialize(getEmail(), tipoUsuario);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Área do Aluno");
                stage.show();

                // Fechar a janela de login após abrir a janela do administrador
                Stage loginStage = (Stage) btnEntrar.getScene().getWindow();
                loginStage.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre a área de perfil do administrador.
     *
     * @param email O email do administrador.
     * @param senha A senha do administrador.
     * @param tipoUsuario O tipo de usuário (aluno ou administrador).
     * @throws IOException Se ocorrer um erro ao abrir a área de perfil do
     * administrador.
     * @throws Exception Se ocorrer um erro ao validar os dados de login do
     * administrador.
     */
    public void abreAreaPerfilAdmin(String email, String senha, String tipoUsuario) throws IOException, Exception {
        try {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alerta");

            if (!adn.validaDadosLogin(email, senha)) {
                alert.setHeaderText("Erro!");
                alert.setContentText("Ops. Email ou senha errados! Favor verificar.");
                alert.showAndWait();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_PerfilAdm.fxml"));
                Parent root = loader.load();

                FXML_PerfilAdmControle perfilAdmControle = loader.getController();
                perfilAdmControle.initialize(getEmail(), tipoUsuario);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Área do Administrador");
                stage.show();

                // Fechar a janela de login após abrir a janela do administrador
                Stage loginStage = (Stage) btnEntrar.getScene().getWindow();
                loginStage.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém o email digitado no campo de texto.
     *
     * @return O email digitado.
     */
    public String getEmail() {
        return txtEmail.getText();
    }

}
