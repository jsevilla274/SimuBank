import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.DecimalFormat;

public class MonthController
{

    @FXML
    private Label chargesOutput;

    @FXML
    private Label interestOutput;

    @FXML
    private Label titleOutput;

    @FXML
    private Button continueButton;

    public void passData(SavingsAccount account, DecimalFormat money, int month)
    {
        int withdrawCount = account.getWithdrawals();
        int charges = (withdrawCount > 4) ? withdrawCount - 4 : 0;

        titleOutput.setText("Month " + month + " has ended");
        chargesOutput.setText("Service charges due: $" +  money.format(charges));
        interestOutput.setText("Interest accrued: $" + money.format(account.monthlyProcess()));
    }

    public void continueButtonListener()
    {
        continueButton.getScene().getWindow().hide();
    }
}
