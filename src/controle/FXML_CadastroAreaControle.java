package controle;

import dao.AreaDao;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Area;
import negocio.AreaNegocio;

public class FXML_CadastroAreaControle {

    AreaNegocio arean = new AreaNegocio();

    @FXML
    private Button btnCadastrar;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblPalavrasChave;

    @FXML
    private Line linha;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPalavrasChave;

    @FXML
    private Text txtSubtitulo;

    @FXML
    private Text txtTitulo;

    @FXML
    void btnCadastrarOnAction(ActionEvent event) throws IOException {
        String nome = txtNome.getText();
        String pchave = txtPalavrasChave.getText();
        int cont = 0;
        if (!arean.verificaNomeVazio(nome)) {
            txtNome.setText("Favor, inserir nome para a área!");
            cont++;
        }
        if (!arean.verificaPalavraChaveVazia(pchave)) {
            txtPalavrasChave.setText("Favor, inserir palavras chave!");
            cont++;
        }
        if (cont == 0) {
            if (adicionaArea()) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Sucesso");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Cadastro realizado com sucesso!");
                successAlert.showAndWait();
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

    @FXML
    void txtNomeOnAction(ActionEvent event) {

    }

    @FXML
    void txtPalavrasChaveOnAction(ActionEvent event) {

    }

    public boolean verificaAreaExistente(String nome) {
        boolean areaExistente = false;

        try {
            // Criar uma instância do DAO da Área (supondo que exista uma classe AreaDao com métodos de acesso ao banco de dados)
            AreaDao areaDao = new AreaDao();

            // Verificar se a área com o nome fornecido já existe
            areaExistente = areaDao.verificaAreaExistente(nome);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return areaExistente;
    }

    public boolean adicionaArea() {
        boolean retorno = false;
        String nome = txtNome.getText(); // Converte para maiúsculas
        String pchave = txtPalavrasChave.getText(); // Converte para maiúsculas

        try {
            boolean areaExistente = verificaAreaExistente(nome);

            if (areaExistente) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Área já existe");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Ops! Essa área já existe, não é possível duplicar.");
                successAlert.showAndWait();
                return false;
            }
            Area area = new Area();
            area.setNome(nome);
            area.setPalavra_chave(pchave);

            AreaDao areaDao = new AreaDao();
            retorno = areaDao.adicionaArea(area);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public void abreAreaCadastroCurso() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_CadastroCurso.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
