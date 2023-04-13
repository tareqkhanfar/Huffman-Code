package com.example.huffmancodegui.Controllers;

import com.example.huffmancodegui.BackEnd.HuffmanCoding;
import com.example.huffmancodegui.BackEnd.HufmanTable;
import com.example.huffmancodegui.BackEnd.Main;
import com.example.huffmancodegui.BackEnd.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.example.huffmancodegui.BackEnd.Main.CountFrequency;
import static com.example.huffmancodegui.BackEnd.Main.header__;

public class CompressController {
    public static StringBuilder headerToUi = new StringBuilder() ;
    @FXML
    private TextArea table;
    @FXML
    private Label after;

    @FXML
    private Label before;

    @FXML
    private Label parcentage;

    public  double sizeBefore = 0;



    public  static String PathName , FileName , PathCompressedFile;
    @FXML
    void chooseOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());


        if (selectedFile != null) {

            FileName = selectedFile.getName();


            PathName = selectedFile.getAbsolutePath();
            Path path = Paths.get(PathName);

            sizeBefore = Files.size(path);
            before.setText("File Size "+sizeBefore+"Byte");


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
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.Huff")
            );
            fileChooser.setInitialFileName(FileName);
            File selectedFile = fileChooser.showSaveDialog(new Stage());



            if (selectedFile != null) {

                PathCompressedFile = selectedFile.getPath();
                Main.NodeFromFile nodeFromFile = CountFrequency(PathName) ;
                HuffmanCoding huffmanCoding = new HuffmanCoding();
                Node HuffmanTree = huffmanCoding.HuffmanTreeCoding(nodeFromFile.count , nodeFromFile.value );
                HufmanTable[]hufmanTable = new HufmanTable[256];
                huffmanCoding.CreateHuffmanCoding(HuffmanTree ,hufmanTable , "" );
                String text = HufmanTable.printHuffmanTable(hufmanTable);
                table.setText(text);
                header__(nodeFromFile.Data , hufmanTable);
                Path path = Paths.get(PathCompressedFile);

                long bytes = Files.size(path);

                after.setText("File SizeAfter "+bytes+"Byte");
                 parcentage.setText("P%:"+(100 - (bytes/sizeBefore)*100)+"%");

            } else {

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void header(ActionEvent event) {
        Stage stage = new Stage( );
        BorderPane pane = new BorderPane();
        Label label = new Label("Header") ;
        label.setFont(new Font(30));
        pane.setTop(label);

        TextArea textArea = new TextArea() ;
        textArea.setText(headerToUi.toString());
        pane.setCenter(textArea);
        Scene ss = new Scene(pane , 500 ,500) ;
        stage.setTitle("The header");
        stage.setScene(ss);
        stage.show();
    }


}
