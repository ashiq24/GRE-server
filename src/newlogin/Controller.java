package newlogin;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
public class Controller {
    server s;
    public Label on;
    public void startaction(ActionEvent e) throws IOException, InterruptedException {
        s=new server(8000);
        on.setText("SERVER IN ON");
    }
    public void closeaction(ActionEvent e) throws IOException {
        Stage homesateg= (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        homesateg.close();
    }
}
