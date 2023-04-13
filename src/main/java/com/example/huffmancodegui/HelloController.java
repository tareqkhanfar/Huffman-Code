package com.example.huffmancodegui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button compress;

    @FXML
    private Button decompress;

    @FXML
    void compressOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage() ;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("compress.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void decompressOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage() ;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("decompress.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
