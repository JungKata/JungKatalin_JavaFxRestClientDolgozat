package hu.petrik.jungkatalin_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class UpdateController extends Controller {
    @FXML
    private TextField ComapnyName;
    @FXML
    private Spinner<Integer> CVV;
    @FXML
    private TextField PhoneNumber;
    
    @FXML
    private CheckBox KidHave;
    @FXML
    private Button updateButton;

    private Company company;

    public void setCompany(Company company){
        this.company = company;
        ComapnyName.setText(this.company.getCompanyName());
        PhoneNumber.setText(this.company.getPhoneNumber());
        CVV.getValueFactory().setValue(this.company.getCVV());
        KidHave.isSelected();

    }

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 100);
        CVV.setValueFactory(valueFactory);
    }

    public void updateClick(ActionEvent actionEvent) {
        String name = ComapnyName.getText().trim();
        String phone = PhoneNumber.getText().trim();
        int cvv = CVV.getValue();
        boolean kid = KidHave.isSelected();
        if (name.isEmpty()) {
            warning("Name is required");
            return;
        }
        if (phone.isEmpty()) {
            warning("Phone is required");
            return;
        }

        this.company.setCompanyName(name);
        this.company.setPhoneNumber(phone);
        this.company.setCVV(cvv);
        this.company.setKid(kid);
        Gson converter = new Gson();
        String json = converter.toJson(this.company);
        try {
            String url = App.BASE_URL + "/" + this.company.getId();
            Response response = RequestHandler.put(url, json);
            if (response.getResponseCode() == 200) {
                Stage stage = (Stage) this.updateButton.getScene().getWindow();
                stage.close();
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

