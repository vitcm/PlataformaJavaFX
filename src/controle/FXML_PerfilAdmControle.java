package controle;

import dao.AdminDao;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Admin;

public class FXML_PerfilAdmControle {

    private Admin admin = new Admin();
    private String emailAdm = "";

    public String pessoa = "adm";

    @FXML
    private Button btnAlterarCurso;

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
    private Label lblTotal;

    @FXML
    private Line linha;

    @FXML
    private TableColumn<?, ?> tblAlunos;

    @FXML
    private TableColumn<?, ?> tblCodigo;

    @FXML
    private TableColumn<?, ?> tblCurso;

    @FXML
    private TableView<?> tblTabela;

    @FXML
    private TableColumn<?, ?> tblValor;

    @FXML
    private TextField txtCodigoAlterar;

    @FXML
    private TextField txtCodigoExcluir;

    @FXML
    private Text txtSubtitulo;

    @FXML
    private Text txtTitulo;

    @FXML
    void btnAlterarPerfilOnAction(ActionEvent event) throws IOException, Exception {
        abreJanelaAlteracao();
    }

    @FXML
    void btnCadastrarCursoOnAction(ActionEvent event) throws IOException {
        abreJanelaCadastroCurso();
    }

    @FXML
    void btnExcluirCursoOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Exclusão de perfil");
        alert.setContentText("Deseja mesmo excluir curso?");
        alert.showAndWait();
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

            if (!admDao.excluiAdmin(getEmail())) {
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

                abreAreaLogin();
            }
        }
    }

    @FXML
    void tblTabelaOnSort(ActionEvent event) {

    }

    @FXML
    void txtCodigoExcluirOnAction(ActionEvent event) {

    }

//    public void abreCadastroAdmin() throws IOException {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML_CadastroPessoa.fxml"));
//            Parent root = loader.load();
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Cadastro");
//            stage.show();
//
//            // Fechar a janela de login após abrir a janela do administrador
//            Stage loginStage = (Stage) btnAlterarPerfil.getScene().getWindow();
//            loginStage.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void abreJanelaCadastroCurso() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_CadastroCurso.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void abreJanelaAlteracao() throws IOException, Exception {
        FXML_AlteracaoDadosControle alteracaoDadosControle = new FXML_AlteracaoDadosControle();
        FXML_ConfirmacaoAlteracaoControle confirmacaoDadosControle = new FXML_ConfirmacaoAlteracaoControle();
        alteracaoDadosControle.setDadosLogin(getEmail());
        confirmacaoDadosControle.getEmail(getEmail());

        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_AlteracaoDados.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void abreAreaLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

//    public void setDados(String email, String senha){
//        if (lblEmail != null) {
//            lblEmail.setText(email);
//        }
//    }
    public String setDadosLogin(String email) {
        lblEmail.setText(email);
        emailAdm = email;
        return emailAdm;
    }

    //pega o email para passar para o alteracao dados
    public String getEmail() {
        return lblEmail.getText();
    }
}
