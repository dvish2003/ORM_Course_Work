package lk.ijse.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.CourseBO;
import lk.ijse.BO.PaymentBO;
import lk.ijse.BO.StudentBO;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Student;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class StudentRegisterController {
    @FXML
    private Label lblDuration;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbCourseName;

    @FXML
    private ComboBox<String> cmbStudentPhoneNumber;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colStudentCourseId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private Label lblCourseID;

    @FXML
    private Label lblDate1;

    @FXML
    private Label lblFee1;

    @FXML
    private Label lblPaymentId1;

    @FXML
    private Label lblStudentCourseId1;

    @FXML
    private Label lblStudentName1;

    @FXML
    private Label lblStudentID;

    @FXML
    private TableView<?> tblStudentCourse;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Course);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Student);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Payment);


    public void initialize() throws SQLException, ClassNotFoundException {
        getCourseIds();
        getStudentIds();
        LocalDate();
    }

    private void LocalDate() {
        Date date = Date.valueOf(LocalDate.now());
        lblDate1.setText(String.valueOf(date));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

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
    void cmbCourseOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String CourseName = cmbCourseName.getValue();
 try {
     Course course = courseBO.searchById(CourseName);
     if (course != null){
         lblCourseID.setText(course.getCourse_id());
         lblFee1.setText(String.valueOf(course.getCourse_fee()));
         lblDuration.setText(course.getDuration());

     } else {
         lblCourseID.setText("Not Found ");
     }
 } catch (Exception e) {
     throw new RuntimeException(e);
 }

    }

    @FXML
    void cmbStudentOnAction(ActionEvent event) {
        String StudentContct = cmbStudentPhoneNumber.getValue();
        try {
            Student student = studentBO.searchByContact(StudentContct);
            if (student != null){
                lblStudentID.setText(student.getStu_id());
                lblStudentName1.setText(student.getStu_name());

            } else {
                lblStudentName1.setText("Not Found ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void getCourseIds() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> CID = courseBO.getIds();

            for (String s : CID) {
                obList.add(s);
            }
            cmbCourseName.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void getStudentIds() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> SID = studentBO.getIds();

            for (String s : SID) {
                obList.add(s);
            }
            cmbStudentPhoneNumber.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
