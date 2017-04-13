import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public void saveButtonListener() throws IOException
    {
        FileChooser saveLocation = new FileChooser();

        //filters out extensions to only .txt
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        saveLocation.getExtensionFilters().add(filter);

        //show save file dialog
        File file = saveLocation.showSaveDialog(saveButton.getScene().getWindow());

        if (file != null)
        {
            FileWriter writer = new FileWriter(file);
            writer.write(info);
            writer.close();
        }
    }

}
