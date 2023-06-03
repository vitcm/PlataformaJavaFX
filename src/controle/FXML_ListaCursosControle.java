package controle;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_ListaCursosControle {

    @FXML
    private Button btnInscricao;

    @FXML
    private ComboBox<?> cbxCursos;

    @FXML
    private Rectangle fundo;

    @FXML
    private Rectangle header;

    @FXML
    private Label lblSubtitulo;

    @FXML
    private Text lblTitulo;

    @FXML
    private Text lblTitulo1;

    @FXML
    private Line linha;

    @FXML
    private TableView<?> tabela;

    @FXML
    private TableColumn<?, ?> tabelaCHoraria;

    @FXML
    private TableColumn<?, ?> tabelaCod;

    @FXML
    private TableColumn<?, ?> tabelaCurso;

    @FXML
    private TableColumn<?, ?> tabelaValor;
    
    @FXML
    private Label lblDigiteCurso;

    @FXML
    private TextField txtCurso;

    @FXML
    void btnInscricaoOnAction(ActionEvent event) throws IOException {
        abreJanelaInscricao();
    }

    @FXML
    void cbxCursosOnAction(ActionEvent event) {

    }

    @FXML
    void tabelaOnSort(ActionEvent event) {

    }
    
    @FXML
    void txtCursoOnAction(ActionEvent event) {

    }
    
    public void abreJanelaInscricao() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_InscricaoCurso.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
