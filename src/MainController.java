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
    private Stage transactionWindow;
    private TransController transController;
    private boolean stageflag = true;

    public MainController(SavingsAccount account)
    {
        this.account = account;
    }

    public void initialize() throws IOException
    {
        FXMLLoader loader;
        Image logo = new Image(getClass().getResourceAsStream("/assets/logo.png"));
        logoImage.setImage(logo);

        balanceOutput.setText(money.format(account.getBalance()));
        statusOutput.setText(account.checkStatus() ? "Active" : "Inactive");

        //creates stage for transactions window
        transactionWindow = new Stage();
        loader = new FXMLLoader(getClass().getResource("transactionpage.fxml"));
        transactionWindow.setScene(new Scene(loader.load()));
        transController = loader.getController();
        transactionWindow.initModality(Modality.APPLICATION_MODAL);

    }

    public void transactionButtonListener(ActionEvent event)
    {
        //show and wait
        if (stageflag)
        {
            transactionWindow.initOwner(balanceOutput.getScene().getWindow());
            stageflag = false;
        }

        if (event.getSource() == withdrawButton)
        {
            transactionWindow.setTitle("Withdraw");
            transController.passData(1, account.getBalance());
        }
        else if (event.getSource() == depositButton)
        {
            transactionWindow.setTitle("Deposit");
            transController.passData(2, account.getBalance());
        }
        transactionWindow.showAndWait();
        System.out.println(transController.getValidatedAmount());
        //get controller class, after showandwait we can retrieve text field presumably
        //set label to appropriate statement
        //set the title
    }

}

