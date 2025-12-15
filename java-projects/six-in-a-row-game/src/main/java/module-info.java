module cn.gobang.plus.gobangplus {
    requires javafx.controls;
    requires javafx.fxml;


    opens cn.sixinarow to javafx.fxml;
    exports cn.sixinarow;
    exports cn.sixinarow.game;
    exports cn.sixinarow.testing;
    opens cn.sixinarow.testing to javafx.fxml;
}
