package sample;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) { launch(args); }

    public Model model = new Model();

    @Override
    public void start(Stage primaryStage) {
        if (model.GetSize() > 9 || model.GetSize() < 2)
        {
            System.out.println("Incorrect size!");
            System.exit(-1);
        }
        Stage window;
        window = primaryStage;
        window.setTitle("2048");

        View v = new View(window, model);
        v.showField();
    }
}
