import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

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
    private DecimalFormat money = new DecimalFormat("#,##0.00");
    private Stage transactionPrompt;
    private boolean stageflag = true;

    public MainController(SavingsAccount account)
    {
        this.account = account;
    }

    public void initialize() throws IOException
    {
        Image logo = new Image(getClass().getResourceAsStream("/assets/logo.png"));
        logoImage.setImage(logo);

        balanceOutput.setText(money.format(account.getBalance()));
        statusOutput.setText(account.checkStatus() ? "Active" : "Inactive");

        transactionPrompt = new Stage();
        transactionPrompt.setScene(new Scene(FXMLLoader.load(getClass().getResource("transactionpage.fxml"))));
        transactionPrompt.initModality(Modality.APPLICATION_MODAL);

    }

    public void transactionButtonListener(ActionEvent event)
    {
        //show and wait
        if (stageflag)
        {
            transactionPrompt.initOwner(balanceOutput.getScene().getWindow());
            stageflag = false;
        }

        if (event.getSource() == withdrawButton)
        {
            transactionPrompt.setTitle("Withdraw");
        }
        else if (event.getSource() == depositButton)
        {
            transactionPrompt.setTitle("Deposit");
        }
        transactionPrompt.showAndWait();
        //set label to appropriate statement
        //set the title
    }

}

