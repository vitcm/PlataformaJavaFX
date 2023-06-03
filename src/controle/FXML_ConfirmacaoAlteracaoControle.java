package controle;


import dao.AdminDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Admin;
import negocio.AdminNegocio;

public class FXML_ConfirmacaoAlteracaoControle {
    
    private Admin adm = new Admin();
    String emailAdm = "";

    @FXML
    private Text blbSubtitulo;

    @FXML
    private Button btnAlterar;

    @FXML
    private Rectangle detalhe;

    @FXML
    private Rectangle fundo;

    @FXML
    private Text lblObs1;

    @FXML
    private Text lblObs2;

    @FXML
    private Label lblSenha;

    @FXML
    private TextField txtSenha;
    
    @FXML
    private Text lblEmail;

    @FXML
    void btnAlterarOnAction(ActionEvent event) throws Exception {
        AdminNegocio admNeg = new AdminNegocio();
        String senha = txtSenha.getText();
        admNeg.validaSenhaAdmin(emailAdm, senha);
        
        Stage loginStage = (Stage) btnAlterar.getScene().getWindow();
        loginStage.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Perfil alterado");
        alert.setContentText("Você alterou o seu perfil.");
        alert.showAndWait();
    }

    @FXML
    void txtSenhaOnAction(ActionEvent event) {

    }
    
    private Stage alteracaoDadosStage;
    
    public void setAlteracaoDadosStage(Stage stage) {
        this.alteracaoDadosStage = stage;
    }
    
    //pega os dados do Perfil para inserir nos campos de texto
    public void getEmail(String email){
        lblEmail.setText(email);
    }
    
    public String passaEmail(){
        emailAdm= lblEmail.getText();
        return emailAdm;
    }

}
