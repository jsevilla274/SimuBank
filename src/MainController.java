import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MainController
{

    @FXML
    private TextField balanceOutput;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button statementButton;

    @FXML
    private Button monthButton;

    @FXML
    private TextField statusOutput;

    @FXML
    private ImageView logoImage;

    private SavingsAccount account;

    public MainController(SavingsAccount account)
    {
        this.account = account;
    }

    public void initialize()
    {
        Image logo = new Image(getClass().getResourceAsStream("/assets/logo.png"));
        logoImage.setImage(logo);
    }

    public void withdrawButtonListener() throws IOException
    {
        System.out.println("withdraw was pressed");
    }

}

