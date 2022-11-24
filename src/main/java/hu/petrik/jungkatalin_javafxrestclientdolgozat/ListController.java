package hu.petrik.jungkatalin_javafxrestclientdolgozat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

        public class ListController extends Controller {

        @FXML
        private Button insertButton;
        @FXML
        private Button updateButton;
        @FXML
        private Button deleteButton;
        @FXML
        private TableView<Company> peopleTable;
        @FXML
        private TableColumn<Company, String> nameCol;
        @FXML
        private TableColumn<Company, String> emailCol;
        @FXML
        private TableColumn<Company, Integer> ageCol;

    }