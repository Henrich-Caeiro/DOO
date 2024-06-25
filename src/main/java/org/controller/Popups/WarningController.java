package org.controller.Popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.Utils.ControllerUtil;

public class WarningController {
    @FXML
    private Button btnOk;
    @FXML
    private Text txtMessage;

    ControllerUtil controllerUtil = new ControllerUtil();
    @FXML
    void confirm(ActionEvent event){
        controllerUtil.closeWindow(event);
    }

    public void setText(String text) {
        txtMessage.setText(text);
    }
}
