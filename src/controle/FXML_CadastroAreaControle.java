package controle;

import dao.AreaDao;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ResourceBundle resourceBundle;

    @FXML
    private ComboBox<String> cbxIdioma;

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

    public void initialize() {
        // Inicializar a ComboBox com as opções de idioma
        ObservableList<String> languages = FXCollections.observableArrayList(
                "Português",
                "Inglês",
                "Espanhol"
        );
        cbxIdioma.setItems(languages);

        // Configurar o evento de seleção da ComboBox
        cbxIdioma.setOnAction(this::handleLanguageSelection);
    }

    @FXML
    void cbxIdiomaOnAction(ActionEvent event) {
        ObservableList<String> idiomas = FXCollections.observableArrayList(
                "Português",
                "Inglês",
                "Espanhol"
        );
        cbxIdioma.setItems(idiomas);
    }

    @FXML
    void btnCadastrarOnAction(ActionEvent event) throws IOException {
        String nome = txtNome.getText();
        String pchave = txtPalavrasChave.getText();
        int cont = 0;
        if (!arean.verificaNomeVazio(nome)) {
            Alert alertaNomeVazio = new Alert(Alert.AlertType.WARNING);
            alertaNomeVazio.setTitle(resourceBundle.getString("alertaErro1"));
            alertaNomeVazio.setHeaderText(null);
            alertaNomeVazio.setContentText(resourceBundle.getString("alertaNomeVazio"));
            alertaNomeVazio.showAndWait();
            cont++;
        }
        if (!arean.verificaPalavraChaveVazia(pchave)) {
            Alert alertaPChaveVazio = new Alert(Alert.AlertType.WARNING);
            alertaPChaveVazio.setTitle(resourceBundle.getString("alertaErro1"));
            alertaPChaveVazio.setHeaderText(null);
            alertaPChaveVazio.setContentText(resourceBundle.getString("alertaPChaveVazio"));
            alertaPChaveVazio.showAndWait();
            cont++;
        }
        if (cont == 0) {
            if (adicionaArea()) {
                Alert alertaSucesso = new Alert(Alert.AlertType.INFORMATION);
                alertaSucesso.setTitle(resourceBundle.getString("alertaSucesso1"));
                alertaSucesso.setHeaderText(null);
                alertaSucesso.setContentText(resourceBundle.getString("alertaSucesso2"));
                alertaSucesso.showAndWait();
                Stage loginStage = (Stage) btnCadastrar.getScene().getWindow();
                loginStage.close();
            } else {
                Alert alertaErro = new Alert(Alert.AlertType.ERROR);
                alertaErro.setTitle(resourceBundle.getString("alertaErro1"));
                alertaErro.setHeaderText(null);
                alertaErro.setContentText(resourceBundle.getString("alertaErro2"));
                alertaErro.showAndWait();
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
                Alert alertaExistente = new Alert(Alert.AlertType.INFORMATION);
                alertaExistente.setTitle(resourceBundle.getString("alertaExistente1"));
                alertaExistente.setHeaderText(null);
                alertaExistente.setContentText(resourceBundle.getString("alertaExistente2"));
                alertaExistente.showAndWait();
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

    private void handleLanguageSelection(ActionEvent event) {
        String selectedLanguage = cbxIdioma.getSelectionModel().getSelectedItem();
        Locale locale;

        if (selectedLanguage.equals("Português")) {
            locale = new Locale("pt");
        } else if (selectedLanguage.equals("Inglês")) {
            locale = new Locale("en");
        } else {
            locale = new Locale("es");
        }

        ResourceBundle.Control control = ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_PROPERTIES);
        resourceBundle = ResourceBundle.getBundle("resources.mensagens", locale, control);
        updateResourceBundle();
    }

    private void updateResourceBundle() {
        // Atualizar as mensagens dos elementos da interface
        txtTitulo.setText(resourceBundle.getString("titulo"));
        txtSubtitulo.setText(resourceBundle.getString("subtitulo"));
        lblNome.setText(resourceBundle.getString("lblNome"));
        lblPalavrasChave.setText(resourceBundle.getString("lblPChave"));
        btnCadastrar.setText(resourceBundle.getString("btnCadastrar"));
    }
}
