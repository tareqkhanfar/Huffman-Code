package com.example.huffmancodegui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static com.example.huffmancodegui.BackEnd.Main.*;

public class DecompressController {
    @FXML
    private TextArea huffmanTable_;
    @FXML
    private Label fileName;
 public static String PathCompressedFile , PathOrginalFile ;
 public static String huffmanTable ;

    public DecompressController() {
    }

    @FXML
    void chooseOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("HufMan File", "*.Huff")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {

            PathCompressedFile = selectedFile.getPath();
            fileName.setText(PathCompressedFile);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("is Done");
            alert.show();

        } else {

        }
    }

    @FXML
    void saveFileOnAction(ActionEvent event) {

        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Open Resource File");
            File selectedDirectory  = directoryChooser.showDialog(new Stage());

            if (selectedDirectory != null) {
                PathOrginalFile = selectedDirectory.getAbsolutePath()+"\\";
                fileName.setText(PathOrginalFile);
                Decode();
                this.huffmanTable_.setText(huffmanTable);



            } else {

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
