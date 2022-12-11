module lab01 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;	//ctoby fungovalo java.sql.Connection v ScoreDAO
    opens lab to javafx.fxml;
    exports lab;
}