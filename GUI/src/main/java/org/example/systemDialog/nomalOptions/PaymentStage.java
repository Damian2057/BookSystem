package org.example.systemDialog.nomalOptions;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.systemDialog.AdminOptionWindow;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentStage implements Initializable {
    public Text amount = new Text();
    public Button okbutton;
    private double sum;

    public PaymentStage() {
    }

    public void setSum(double sum) {
        this.sum = sum;
        amount.setText(String.valueOf(sum));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  amount.setText(String.valueOf(sum));
    }

    public void finalize(ActionEvent actionEvent) {
        Stage stage = (Stage) okbutton.getScene().getWindow();
        stage.close();
        AdminOptionWindow.removeOrder.close();
        AdminOptionWindow.removeOrder = null;
    }
}
