module com.example.huffmancodegui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.huffmancodegui to javafx.fxml;
    exports com.example.huffmancodegui;

    opens com.example.huffmancodegui.Controllers to javafx.fxml;
    exports com.example.huffmancodegui.Controllers;

    opens com.example.huffmancodegui.BackEnd to javafx.fxml;
    exports com.example.huffmancodegui.BackEnd;
}