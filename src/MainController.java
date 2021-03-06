import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.RoundingMode;
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

    @FXML
    private Label systemOutput;

    private SavingsAccount account;
    private int month = 1;
    private DecimalFormat money = new DecimalFormat("#,##0.00");
    private Stage transactionWindow;
    private Stage statementWindow;
    private Stage monthWindow;
    private TransController transController;
    private StateController stateController;
    private MonthController monthController;
    private boolean transOwner = true;
    private boolean stateOwner = true;
    private boolean monthOwner = true;

    public MainController(SavingsAccount account)
    {
        this.account = account;
        money.setRoundingMode(RoundingMode.DOWN);
    }

    public void initialize() throws IOException
    {
        //set logo on the window
        FXMLLoader loader;
        Image logo = new Image(getClass().getResourceAsStream("/assets/logo.png"));
        logoImage.setImage(logo);

        refreshInfo();

        //creates stage for transactions window
        transactionWindow = new Stage();
        loader = new FXMLLoader(getClass().getResource("fxml/transactionpage.fxml"));
        transactionWindow.setScene(new Scene(loader.load()));
        transController = loader.getController();
        transactionWindow.initModality(Modality.APPLICATION_MODAL);
        transactionWindow.getIcons().add(logo);

        //creates stage for statement window
        statementWindow = new Stage();
        loader = new FXMLLoader(getClass().getResource("fxml/statementpage.fxml"));
        statementWindow.setScene(new Scene(loader.load()));
        statementWindow.setTitle("View Statement");
        stateController = loader.getController();
        statementWindow.initModality(Modality.APPLICATION_MODAL);
        statementWindow.getIcons().add(logo);

        //creates stage for month window
        monthWindow = new Stage();
        loader = new FXMLLoader(getClass().getResource("fxml/monthpage.fxml"));
        monthWindow.setScene(new Scene(loader.load()));
        monthWindow.setTitle("Monthly Report");
        monthController = loader.getController();
        monthWindow.initModality(Modality.APPLICATION_MODAL);
        monthWindow.getIcons().add(logo);
    }

    public void transactionButtonListener(ActionEvent event)
    {
        double userOut = 0;
        int modeID = 0;

        if (transOwner)
        {
            transactionWindow.initOwner(balanceOutput.getScene().getWindow());
            transOwner = false;
        }

        systemOutput.setText("");
        if (event.getSource() == withdrawButton)
        {
            if (account.checkStatus())
            {
                modeID = 1;
                transactionWindow.setTitle("Withdraw");
                transController.passData(modeID, account.getBalance());
                transactionWindow.showAndWait();
                userOut = transController.getValidatedAmount();
            }
            else
            {
                systemOutput.setText("System Warning: Inactive accounts cannot withdraw");
            }
        }
        else if (event.getSource() == depositButton)
        {
            modeID = 2;
            transactionWindow.setTitle("Deposit");
            transController.passData(modeID, account.getBalance());
            transactionWindow.showAndWait();
            userOut = transController.getValidatedAmount();
        }

        if (userOut != 0)
        {
            if (modeID == 1)
            {
                account.withdraw(userOut);
            }
            else
            {
                account.deposit(userOut);
            }
            refreshInfo();
            String sysMsg = "System Message: $";
            sysMsg += money.format(userOut) + ((modeID == 1) ? " withdrawn" : " deposited");
            sysMsg += " successfully";
            systemOutput.setText(sysMsg);
        }
    }

    public void statementButtonListener()
    {
        systemOutput.setText("");
        if (stateOwner)
        {
            statementWindow.initOwner(balanceOutput.getScene().getWindow());
            stateOwner = false;
        }
        stateController.passData(account, money, month);
        statementWindow.showAndWait();
    }

    public void monthButtonListener()
    {
        systemOutput.setText("");
        if (monthOwner)
        {
            monthWindow.initOwner(balanceOutput.getScene().getWindow());
            monthOwner = false;
        }
        monthController.passData(account, money, month++);
        monthWindow.showAndWait();
        systemOutput.setText("System Message: Month " + month + " has begun");
        refreshInfo();
    }

    public void refreshInfo()
    {
        balanceOutput.setText(money.format(account.getBalance()));
        statusOutput.setText(account.checkStatus() ? "Active" : "Inactive");
    }

}

