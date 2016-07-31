package Loadingserver;

import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    Server s;
   public  Label on;
    public void startaction(ActionEvent e) throws IOException, InterruptedException {
        s=new Server(9000);
        on.setText("SERVER IS ON");
    }
    public void closeaction(ActionEvent e) throws IOException {
        Stage homesateg= (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        homesateg.close();
    }
}
