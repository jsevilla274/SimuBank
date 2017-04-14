import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class DriverGUI extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("fxml/setuppage.fxml"));
        Scene scene = new Scene(parent);

        stage.setTitle("Opening your Savings Account");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/logo.png")));
        stage.show();
    }
}
