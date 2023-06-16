package controle;

import dao.AdminDao;
import dao.AlunaDao;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Admin;
import modelo.Aluna;
import negocio.AdminNegocio;
import negocio.AlunaNegocio;

public class FXML_CadastroPessoaControle {

    AdminNegocio adn = new AdminNegocio();
    AlunaNegocio aln = new AlunaNegocio();

    @FXML
    private Text Subtitulo;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblMensagem;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblSenha;

    @FXML
    private Label lblSobrenome;

    @FXML
    private Text lblTitulo;

    @FXML
    private Line linha;

    @FXML
    private RadioButton rbtEstudante;

    @FXML
    private RadioButton rbtProfessor;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSobrenome;

    @FXML
    void btnCadastrarOnAction(ActionEvent event) throws IOException {
        if (rbtProfessor.isSelected()) {
            String nome = txtNome.getText();
            String sobrenome = txtSobrenome.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();
            int cont = 0;

            if (!adn.verificaEmailVazio(email)) {
                txtEmail.setText("Favor, completar o email!");
                cont++;
            } else {
                if (!adn.verificaValidadeEmail(email)) {
                    txtEmail.setText("Favor, digitar um email válido!");
                    cont++;
                }
            }
            if (!adn.verificaSenhaVazio(senha)) {
                txtSenha.setText("Favor, completar a senha!");
                cont++;
            } else {
                if (!adn.verificaValidadeSenha(senha)) {
                    txtSenha.setText("Favor, digitar uma senha válida - 6 números!");
                    cont++;
                }
            }
            if (!adn.verificaNomeVazio(nome)) {
                txtEmail.setText("Favor, completar o nome!");
                cont++;
            } else {
                if (!adn.verificaValidadeNome(nome)) {
                    txtNome.setText("Favor, inserir um Nome válido.");
                    cont++;
                }
            }
            if (!adn.verificaSobrenomeVazio(sobrenome)) {
                txtSenha.setText("Favor, completar o sobrenome!");
                cont++;
            } else {
                if (!adn.verificaValidadeNome(sobrenome)) {
                    txtSobrenome.setText("Favor, inserir um sobrenome válido.");
                    cont++;
                }
            }
            if (cont == 0) {
                if (adicionaAdmin()) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Sucesso");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Cadastro realizado com sucesso!");
                    successAlert.showAndWait();
                    abreAreaLogin();
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
        if (rbtEstudante.isSelected()) {
            String nome = txtNome.getText();
            String sobrenome = txtSobrenome.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();
            int cont = 0;

            if (!aln.verificaEmailVazio(email)) {
                txtEmail.setText("Favor, completar o email!");
                cont++;
            } else {
                if (!aln.verificaValidadeEmail(email)) {
                    txtEmail.setText("Favor, digitar um email válido!");
                    cont++;
                }
            }
            if (!aln.verificaSenhaVazio(senha)) {
                txtSenha.setText("Favor, completar a senha!");
                cont++;
            } else {
                if (!adn.verificaValidadeSenha(senha)) {
                    txtSenha.setText("Favor, digitar uma senha válida - 6 números!");
                    cont++;
                }
            }
            if (!aln.verificaNomeVazio(nome)) {
                txtNome.setText("Favor, completar o nome!");
                cont++;
            } else {
                if (!aln.verificaValidadeNome(nome)) {
                    txtNome.setText("Favor, inserir um nome válido.");
                    cont++;
                }
            }
            if (!aln.verificaSobrenomeVazio(sobrenome)) {
                txtSobrenome.setText("Favor, completar o sobrenome!");
                cont++;
            } else {
                if (!aln.verificaValidadeNome(sobrenome)) {
                    txtSobrenome.setText("Favor, inserir um sobrenome válido.");
                    cont++;
                }
            }
            if (cont == 0) {
                if (adicionaAluna()) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Sucesso");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Cadastro realizado com sucesso!");
                    successAlert.showAndWait();
                    abreAreaLogin();
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

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione tipo");
            alert.setContentText("Nenhuma opção foi selecionada. Você é aluno ou administrador?");
            alert.showAndWait();
        }
    }

    @FXML
    void rbtEstudanteOnAction(ActionEvent event) {
        rbtProfessor.setSelected(false); //se o botão estudante estiver selecionado, não pode selecionar TAMBÉM o de professor
    }

    @FXML
    void rbtProfessorOnAction(ActionEvent event) {
        rbtEstudante.setSelected(false);//se o botão professor estiver selecionado, não pode selecionar TAMBÉM o de estudante
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

    public void abreAreaLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public boolean adicionaAdmin() {
        boolean retorno = false;
        String nome = txtNome.getText();
        String sobrenome = txtSobrenome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        try {
            Admin adm = new Admin();
            adm.setNomeAdmin(nome);
            adm.setSobrenomeAdmin(sobrenome);
            adm.setEmailAdmin(email);
            adm.setSenhaAdmin(senha);
            AdminDao admDao = new AdminDao();
            retorno = admDao.adicionaAdmin(adm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public boolean adicionaAluna() {
        boolean retorno = false;
        String nome = txtNome.getText();
        String sobrenome = txtSobrenome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        try {
            Aluna al = new Aluna();
            al.setNomeAluna(nome);
            al.setSobrenomeAluna(sobrenome);
            al.setEmailAluna(email);
            al.setSenhaAluna(senha);
            AlunaDao alDao = new AlunaDao();
            retorno = alDao.adicionaAluna(al);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }
}
