package sample;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Controller implements EventHandler<KeyEvent>{

    private View view;
    private Model model;
    //public Button restartButton;

    public Controller(View view, Model model){
        this.view = view;
        this.model = model;

        /*restartButton = new Button();
        restartButton.setOnAction(event -> {
            model.reset();
            view.showField();
        });*/
    }

    public void setRestartButton() {
        model.reset();
        view.showField();
    }

    @Override
    public void handle(KeyEvent key) {
        boolean keyPressed = true;

        switch (key.getCode()) {
            case UP:
            case W:
                model.up(0, 0);
                break;
            case DOWN:
            case S:
                model.down(0, 0);
                break;
            case LEFT:
            case A:
                model.left(0, 0);
                break;
            case RIGHT:
            case D:
                model.right(0, 0);
                break;
            default:
                keyPressed = false;
        }

        if(keyPressed) {
            view.showField();

            if (model.checkGameOver()) {
                view.gameOverAlert();
                model.reset();
                view.showField();
            }
        }
    }
}
