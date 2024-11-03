package lk.ijse.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.BO.Impl.BOFactory;
import lk.ijse.BO.Impl.UserBOImpl;
import lk.ijse.BO.UserBO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.util.PasswordEncrypt;
import lk.ijse.util.PasswordVerifier;

import javax.security.auth.kerberos.EncryptionKey;
import java.sql.SQLException;

public class UserController {
    @FXML
    private TextField UserID;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbPosition;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colUserID;


    @FXML
    private TableView<?> tblUsers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

UserBO userBO = (UserBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoType.User);

    public void initialize(){

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws Exception {

    String id = UserID.getText();
    String name = txtName.getText();
    String Address = txtAddress.getText();
    String phone = txtPhone.getText();
    String email = txtEmail.getText();
    String Position = String.valueOf(cmbPosition.getValue());
    String password = txtPassword.getText();


        /*Password encrypt*/
        String encryptPassword = PasswordEncrypt.hashPassword(password);

    try {
        if (PasswordVerifier.verifyPassword(password,encryptPassword)){
            UserDTO userDTO = new UserDTO(id,name,Address,phone,email,Position,encryptPassword);

            boolean isSave = userBO.save(userDTO);
            if(isSave){
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully!").show();

            }
        }
        new Alert(Alert.AlertType.CONFIRMATION, "User not saved successfully!").show();

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbPositionOnAction(ActionEvent actionEvent) {
    }
}
