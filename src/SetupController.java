import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SetupController
{

    @FXML
    private TextField balanceInput;

    @FXML
    private TextField rateInput;

    @FXML
    private Button registerButton;

    @FXML
    private Label balanceAlert;

    @FXML
    private Label rateAlert;

    public void registerButtonListener() throws IOException
    {
        double balance;     //Starting balance
        double annualRate;  //Annual interest rate
        double tempBalance;
        double tempRate;

        //Reads starting balance from text field
        try
        {
            tempBalance = new Scanner(balanceInput.getText()).nextDouble();
            if (tempBalance < 25)
            {
                balanceAlert.setText("*Balance must be $25 or more");
                balance = -1;
            }
            else
            {
                balanceAlert.setText("");
                balance = tempBalance;
            }
        }
        catch (InputMismatchException e)
        {
            balanceAlert.setText("*Invalid input");
            balance = -1;
        }
        catch (NoSuchElementException e)
        {
            balanceAlert.setText("*Please enter a balance");
            balance = -1;
        }

        //Reads annual rate from text field
        try
        {
            tempRate = new Scanner(rateInput.getText()).nextDouble();

            if (tempRate < 0)
            {
                rateAlert.setText("*Must be non-negative");
                annualRate = -1;
            }
            else
            {
                rateAlert.setText("");
                annualRate = tempRate/100; //divided by 100 to retrieve decimal from percent
            }
        }
        catch (InputMismatchException e)
        {
            rateAlert.setText("*Invalid input");
            annualRate = -1;
        }
        catch (NoSuchElementException e)
        {
            rateAlert.setText("*Please enter a rate");
            annualRate = -1;
        }

        if (balance > 25 && annualRate > 0)
        {
            //change scene
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.hide();

            //creating a controller from class (allows us to pass in parameter)
            MainController controller = new MainController(new SavingsAccount(balance, annualRate));
            //creating a loader instead of using static loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainpage.fxml"));
            //assigning controller class to our loaded fxml scene
            loader.setController(controller);
            //loading in the new fxml scene to the stage and change name
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("SimuBank Banking Software v1.0.4.7");
            //show stage
            stage.show();
        }
    }
}
