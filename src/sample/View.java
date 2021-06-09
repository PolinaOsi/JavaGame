package sample;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;

import java.io.*;


public class View {
    Stage window;
    Model model;
    Controller controller;
    Group root;

    View(Stage win, Model model) {
        this.model = model;
        controller = new Controller(this, model);
        window = win;
    }

    void GetResults () {
        TextField textNameField = new TextField();
        textNameField.setLayoutX(570);
        textNameField.setLayoutY(770);
        textNameField.setPrefColumnCount(11);
        Button recBut = new Button("CLick to save");
        recBut.setLayoutX(750);
        recBut.setLayoutY(750);
        recBut.setStyle("-fx-background-color: #CC4DFF; -fx-border-width: 1.4; " +
                "-fx-border-color: #000000; -fx-font-size: 20; -fx-text-fill: #000000; ");
        recBut.setFont(new Font("Arial",20));
        recBut.setPrefSize(170, 50);
        recBut.setOnAction(lam -> {
            try {
                FileWriter records = new FileWriter("Record.txt", true);
                String name = textNameField.getText();
                records.write("Name: " + name + " Score: " + model.GetScore() + " Size of field: " + model.GetSize() + "x" + model.GetSize() + "\n");
                records.close();
                this.gameOverAlert();

                model.reset();
                this.showField();
            }
            catch (IOException err) {
                err.printStackTrace();
            }
            recBut.setOnAction(null);
        });
        root.getChildren().add(textNameField);
        root.getChildren().add(recBut);
    }

    void showField() {
        Scene game;

        root = new Group();
        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        for(int i = 0; i < model.GetSize(); i++)
            for(int j = 0; j < model.GetSize(); j++)
            {
                if(model.GetField(i,j) != 0) {
                    Text temp = new Text(Integer.toString(model.GetField(i,j)));
                    temp.setFont(new Font("verdana", 35));
                    temp.setX(60 + 110 * i - (Math.ceil(Math.log10(model.GetField(i,j))) * 11) );
                    temp.setY(152 + 110 * j);

                    root.getChildren().addAll(temp);

                    double r = Math.log(model.GetField(i,j) + 1) / Math.log(32768);
                    if(r > 1) r = 1;
                    double g = 1 - Math.log(model.GetField(i,j) + 1) / Math.log(64);
                    if(g < 0) g = 0;
                    double b = 1 - Math.log(model.GetField(i,j) + 1) / Math.log(4096);
                    if(b < 0) b = 0;

                    gc.setFill(Color.color(r, g, b));
                    gc.fillRect(10 + 110 * i, 90 + 110 * j, 100, 100);
                }
                else {
                    gc.setFill(Color.color(1, 0.8, 1));
                    gc.fillRect(10 + 110 * i, 90 + 110 * j, 100, 100);
                }
            }

        gc.setFill(Color.color(0.8, 0.3, 1));
        gc.fillRoundRect(10,20,170,50, 10, 10);
        gc.setFill(Color.color(0, 0, 0));
        gc.strokeRoundRect(10,20,170,50,10,10);
        Text t1 = new Text("Score: " + model.GetScore());
        t1.setFont(new Font("Arial",20));
        t1.setX(30);
        t1.setY(50);

        Button restart = new Button();
        restart.setOnAction(event -> {
            controller.setRestartButton();
        });
        restart.setText("Restart");
        restart.setLayoutX(270);
        restart.setLayoutY(20);
        restart.setStyle("-fx-background-color: #CC4DFF; -fx-border-width: 1.4; " +
                "-fx-border-color: #000000; -fx-font-size: 20; -fx-text-fill: #000000; ");
        restart.setFont(new Font("Arial",20));
        restart.setPrefSize(170, 50);

        root.getChildren().addAll(t1, restart);

        game = new Scene(root, 930 , 860 );

        Button records = new Button("Show records");
        records.setFont(new Font("Arial",20));
        records.setPrefSize(170, 50);
        records.setStyle("-fx-background-color: #CC4DFF; -fx-border-width: 1.4; " +
                "-fx-border-color: #000000; -fx-font-size: 20; -fx-text-fill: #000000; ");
        records.setLayoutX(750);
        records.setLayoutY(20);
        root.getChildren().add(records);
        records.setOnAction(event -> {
            String recs = new String();
            try {
                File auch = new File("Record.txt");
                FileReader fr = new FileReader(auch);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    recs = recs.concat(line).concat("\n");
                    line = reader.readLine();
                }

            }
            catch (IOException err) {
                err.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Рекорды");
            TextArea area = new TextArea(recs);
            area.setWrapText(true);
            area.setEditable(false);
            alert.getDialogPane().setContent(area);

            alert.showAndWait();

        });

        window.setMaxHeight(570);
        window.setMinHeight(570);
        window.setMaxWidth(465);
        window.setMinWidth(465);
        window.setResizable(true);
        window.setScene(game);
        window.show();

        game.setOnKeyPressed(controller);

    }

    void gameOverAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game over");
        alert.setHeaderText(null);
        alert.setContentText("Final score: " + model.GetScore());

        alert.showAndWait();
    }
}
