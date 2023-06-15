package controle;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXML_BemVindaControle {

      @FXML
    private Button btnLogin;

    @FXML
    private Button btnVerCursos;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle fundo1;

    @FXML
    private Rectangle fundo11;

    @FXML
    private Label lblBemVinda;

    @FXML
    private Label lblSelecione;

    @FXML
    private Text lblSubtitulo;

    @FXML
    private Text lblTitulo1;

    @FXML
    private Text lblTitulo2;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        abreAreaLogin();
    }

    @FXML
    void btnVerCursosOnAction(ActionEvent event) throws IOException {
        abreJanelaCursos();
    }
    
    public void abreJanelaCursos() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_ApresentacaoCursos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void abreAreaLogin() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
