import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TransController {

    @FXML
    private TextField transInput;

    @FXML
    private Label transAlert;

    @FXML
    private Button transButton;

    private int modeID;
    private double userBalance;
    private double validatedAmount;

    public void initialize()
    {

    }

    public void passData(int modeID, double userBalance)
    {
        this.modeID = modeID;
        this.userBalance = userBalance;

        validatedAmount = 0;
        transInput.clear();
        transAlert.setText("");

        if (modeID == 1)
        {
            transButton.setText("Withdraw");
        }
        else
        {
            transButton.setText("Deposit");
        }
    }

    public double getValidatedAmount()
    {
        return validatedAmount;
    }

    public void transButtonListener()
    {
        double userDouble;
        String userIn = transInput.getText();
        Stage stage = (Stage) transInput.getScene().getWindow();

        try
        {
            userDouble = new Scanner(userIn).nextDouble();
            if (userDouble < 0)
            {
                transAlert.setText("*Must be non-negative");
            }
            else if (modeID == 1 && userDouble > userBalance)
            {
                transAlert.setText("*Insufficient funds");
            }
            else
            {
                validatedAmount = userDouble;
                stage.close();
            }
        }
        catch (InputMismatchException e)
        {
            transAlert.setText("*Invalid input");
        }
        catch (NoSuchElementException e)
        {
            transAlert.setText("*Please enter an amount");
        }
    }

}
