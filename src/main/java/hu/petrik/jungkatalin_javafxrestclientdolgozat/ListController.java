package hu.petrik.jungkatalin_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ListController extends Controller {

        @FXML
        private Button insertButton;
        @FXML
        private Button updateButton;
        @FXML
        private Button deleteButton;
        @FXML
        private TableView<Company> companyTable;
        @FXML
        private TableColumn<Company, String> comapanyNameCol;
        @FXML
        private TableColumn<Company, String> phoneNumberCol;
        @FXML
        private TableColumn<Company, Integer> CVVCol;

                private void initialize() {
                        // getName() függvény eredményét írja ki
                        comapanyNameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
                        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                        CVVCol.setCellValueFactory(new PropertyValueFactory<>("CVV"));
                        Platform.runLater(() -> {
                                try {
                                        loadPeopleFromServer();
                                } catch (IOException e) {
                                        error("Couldn't get data from server", e.getMessage());
                                        Platform.exit();
                                }
                        });
                }

        private void loadPeopleFromServer() throws IOException {
                Response response = RequestHandler.get(App.BASE_URL);
                String content = response.getContent();
                Gson converter = new Gson();
                Company[] people = converter.fromJson(content, Company[].class);
                companyTable.getItems().clear();
                for (Company person : people) {
                        companyTable.getItems().add(person);
                }
        }
}
