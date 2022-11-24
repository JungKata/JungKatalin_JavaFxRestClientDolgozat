package hu.petrik.jungkatalin_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;


    public class CreateCompany extends Controller {
        @FXML
        private TextField CompanyName;
        @FXML
        private Spinner<Integer> CVV;
        @FXML
        private TextField PhoneNumber;
        @FXML
        private Button submitButton;

        @FXML
        private void initialize() {
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 100);
            CVV.setValueFactory(valueFactory);
        }

        @FXML
        public void submitClick(ActionEvent actionEvent) {
            String name = CompanyName.getText().trim();
            String phone = PhoneNumber.getText().trim();
            int cvv = CVV.getValue();
            if (name.isEmpty()) {
                warning("Name is required");
                return;
            }
            if (phone.isEmpty()) {
                warning("Phone is required");
                return;
            }

            Company company = new Company(0, name, cvv, phone);
            Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = converter.toJson(company);

            try {
                Response response = RequestHandler.post(App.BASE_URL, json);
                if (response.getResponseCode() == 201) {
                    warning("Company added!");
                    CompanyName.setText("");
                    PhoneNumber.setText("");
                    CVV.getValueFactory().setValue(30);
                } else {
                    String content = response.getContent();
                    error(content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
