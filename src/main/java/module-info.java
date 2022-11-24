module hu.petrik.jungkatalin_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens hu.petrik.jungkatalin_javafxrestclientdolgozat to javafx.fxml, com.google.gson;
    exports hu.petrik.jungkatalin_javafxrestclientdolgozat;
}