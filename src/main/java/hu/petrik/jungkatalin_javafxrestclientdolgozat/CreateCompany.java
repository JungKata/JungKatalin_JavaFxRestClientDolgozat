package hu.petrik.jungkatalin_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;


    public class CreateCompany extends Controller {
        @FXML
        private TextField ComapnyName;
        @FXML
        private TextField CVV;
        @FXML
        private TextField PhoneNumber;
        @FXML
        private Button submitButton;


        public void submitClick(ActionEvent actionEvent) {
            String name = ComapnyName.getText().trim();
            String phone = PhoneNumber.getPromptText().trim();
            int cvv = CVV.getValue();
            if (name.isEmpty()) {
                warning("Name is required");
                return;
            }
            if (phone.isEmpty()) {
                warning("Email is required");
                return;
            }

        }

        Company company = new Company(0, ComapnyName, CVV, PhoneNumber);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(company);

        try

        {
            Response response = RequestHandler.post(App.BASE_URL, json);
            if (response.getResponseCode() == 201) {
                warning("Person added!");
                ComapnyName.setText("");
                PhoneNumber.setText("");
                CVV.getValueFactory().setValue(30);
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }