package sample;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;

public class View {
    Stage window;
    Model model;
    Controller controller;

        View(Stage win, Model model) {
            this.model = model;
            controller = new Controller(this, model);
            window = win;
        }

        void showField() {
            Scene game;

            Group root = new Group();
            Canvas canvas = new Canvas(440, 520);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            root.getChildren().add(canvas);

            for(int i = 0; i < 4; i++)
                for(int j = 0; j < 4; j++)
                {
                    if(model.field[i][j] != 0) {
                        Text temp = new Text(Integer.toString(model.field[i][j]));
                        temp.setFont(new Font("verdana", 35));
                        temp.setX(60 + 110 * i - (Math.ceil(Math.log10(model.field[i][j])) * 11) );
                        temp.setY(152 + 110 * j);

                        root.getChildren().addAll(temp);

                        double r = Math.log(model.field[i][j] + 1) / Math.log(32768);
                            if(r > 1) r = 1;
                        double g = 1 - Math.log(model.field[i][j] + 1) / Math.log(64);
                            if(g < 0) g = 0;
                        double b = 1 - Math.log(model.field[i][j] + 1) / Math.log(4096);
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
            Text t1 = new Text("Score: " + model.Score);
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

            game = new Scene(root, 450, 532);

            window.setMaxHeight(570);
            window.setMinHeight(570);
            window.setMaxWidth(465);
            window.setMinWidth(465);
            window.setResizable(false);
            window.setScene(game);
            window.show();

            game.setOnKeyPressed(controller);
        }

        void gameOverAlert() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Game over");
            alert.setHeaderText(null);
            alert.setContentText("Final score: " + model.Score);

            alert.showAndWait();
        }
}
