module cindy.example.cindy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens cindy.example.cindy.base to javafx.fxml;
    exports cindy.example.cindy.base;

    opens cindy.example.cindy.laissezfaire to javafx.fxml;
    exports cindy.example.cindy.laissezfaire;
}
