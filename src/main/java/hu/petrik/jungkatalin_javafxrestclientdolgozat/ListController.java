package hu.petrik.jungkatalin_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
                        try {loadPeopleFromServer();}
                        catch (IOException e) {
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

        @FXML
        public void insertClick(ActionEvent actionEvent) {
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-people-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
                        Stage stage = new Stage();
                        stage.setTitle("Create Company");
                        stage.setScene(scene);
                        stage.show();
                        insertButton.setDisable(true);
                        updateButton.setDisable(true);
                        deleteButton.setDisable(true);
                        stage.setOnCloseRequest(event -> {
                                insertButton.setDisable(false);
                                updateButton.setDisable(false);
                                deleteButton.setDisable(false);
                                try {
                                        loadPeopleFromServer();
                                } catch (IOException e) {
                                        error("An error occurred while communicating with the server");
                                }
                        });
                } catch (IOException e) {
                        error("Could not load form", e.getMessage());
                }
        }
}
