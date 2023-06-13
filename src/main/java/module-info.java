module escape_room {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports qu4lizz.escape_room.controller;
    opens qu4lizz.escape_room.controller to javafx.fxml;
    exports qu4lizz.escape_room;
    opens qu4lizz.escape_room to javafx.fxml;
}
