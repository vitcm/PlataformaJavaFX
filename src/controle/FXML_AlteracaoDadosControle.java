package controle;

import dao.AdminDao;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Admin;

public class FXML_AlteracaoDadosControle {

    private String emailAdmin = "";
    private Admin adm = new Admin();

    @FXML
    private Text Subtitulo;

    @FXML
    private Button btnAlterar;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblEmail;

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
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtSobrenome;

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
        FXML_ConfirmacaoAlteracaoControle confirmacaoAlteracaoControle = new FXML_ConfirmacaoAlteracaoControle();
        confirmacaoAlteracaoControle.getEmail(getDadosAlteracao());

        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_ConfirmacaoAlteracao.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    //pega os dados do Perfil para inserir nos campos de texto
    public void setDadosLogin(String email) throws Exception {
        AdminDao admDao = new AdminDao();
        adm = admDao.pesquisaContato(email);
        txtNome.setText(adm.getNomeAdmin());
        txtSobrenome.setText(adm.getSobrenomeAdmin());
        txtEmail.setText(adm.getEmailAdmin());
    }
    
    //pega o email lá do Perfil para repassar para a tela de confirmação. A senha digitada deve ser igual a essa. 
    public String getDadosAlteracao(){
        emailAdmin = txtEmail.getText();
        return emailAdmin;
    }
}
