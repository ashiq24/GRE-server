package Loadingserver;

import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    Server s;
    public void startaction(ActionEvent e) throws IOException, InterruptedException {
        s=new Server(9000);
    }
    public void closeaction(ActionEvent e) throws IOException {
        Stage homesateg= (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        homesateg.close();
    }
}
