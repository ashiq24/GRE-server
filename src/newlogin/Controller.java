package newlogin;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
public class Controller {
    server s;
    public void startaction(ActionEvent e) throws IOException, InterruptedException {
        s=new server(8000);
    }
    public void closeaction(ActionEvent e) throws IOException {
        Stage homesateg= (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        homesateg.close();
    }
}
