import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class StateController {

    @FXML
    private Text statementOutput;

    @FXML
    private Button saveButton;

    private String info;

    public void passData(SavingsAccount account, DecimalFormat money, int month)
    {
        info = "******ACCOUNT STATEMENT******"
                + "\nMonth: " + month
                + "\nBalance: " + money.format(account.getBalance())
                + "\nStatus: " + ((account.checkStatus()) ? "Active" : "Inactive")
                + "\nAnnual Rate: " + account.getAnnualRate()
                + "\nWithdrawals this month: " + account.getWithdrawals()
                + "\nDeposits this month: " + account.getDeposits()
                + "\n*************************************";
        statementOutput.setText(info);
    }


}
