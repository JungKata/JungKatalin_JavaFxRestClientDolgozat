package hu.petrik.jungkatalin_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
        private TableColumn<Company, String> companyNameCol;
        @FXML
        private TableColumn<Company, String> phoneNumberCol;
        @FXML
        private TableColumn<Company, Integer> CVVCol;

        @FXML
        private TableColumn<Company, Boolean>KidHaveCol;
        @FXML
        private void initialize() {
                // getName() függvény eredményét írja ki
                companyNameCol.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
                phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
                CVVCol.setCellValueFactory(new PropertyValueFactory<>("CVV"));
                KidHaveCol.setCellValueFactory(new PropertyValueFactory<>("Kid"));
                Platform.runLater(() -> {
                        try {loadPeopleFromServer();
                        }
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
                Company[] companies = converter.fromJson(content, Company[].class);
                companyTable.getItems().clear();
                for (Company company : companies) {
                        companyTable.getItems().add(company);
                }
        }

        @FXML
        public void insertClick(ActionEvent actionEvent) {
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-view.fxml"));
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

        @FXML
        public void updateClick(ActionEvent actionEvent) {
                int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();
                if (selectedIndex == -1) {
                        warning("Please select a person from the list first");
                        return;
                }
                Company selected = companyTable.getSelectionModel().getSelectedItem();
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("update-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
                        Stage stage = new Stage();
                        stage.setTitle("Update Company");
                        stage.setScene(scene);
                        UpdateController controller = fxmlLoader.getController();
                        controller.setCompany(selected);
                        stage.show();
                        insertButton.setDisable(true);
                        updateButton.setDisable(true);
                        deleteButton.setDisable(true);
                        stage.setOnHidden(event -> {
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

        @FXML
        public void deleteClick(ActionEvent actionEvent) {
                int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();
                if (selectedIndex == -1) {
                        warning("Please select a company from the list first");
                        return;
                }

                Company selected = companyTable.getSelectionModel().getSelectedItem();
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setHeaderText(String.format("Are you sure you want to delete %s?", selected.getCompanyName()));
                Optional<ButtonType> optionalButtonType = confirmation.showAndWait();
                if (optionalButtonType.isEmpty()) {
                        System.err.println("Unknown error occurred");
                        return;
                }
                ButtonType clickedButton = optionalButtonType.get();
                if (clickedButton.equals(ButtonType.OK)) {
                        String url = App.BASE_URL + "/" + selected.getId();
                        try {
                                RequestHandler.delete(url);
                                loadPeopleFromServer();
                        } catch (IOException e) {
                                error("An error occurred while communicating with the server");
                        }
                }
        }
}
