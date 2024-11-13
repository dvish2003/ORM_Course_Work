package lk.ijse.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.BO.*;
import lk.ijse.BO.Impl.StudentRegisterBOImpl;
import lk.ijse.DTO.*;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Student;
import lk.ijse.Entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    Student_CourseBO studentCourseBO = (Student_CourseBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Student_Course);


    public void initialize() throws SQLException, ClassNotFoundException {
        getCourseIds();
        getStudentIds();
        generateNextId();
        LocalDate();
    }

    private void generateNextId() throws SQLException, ClassNotFoundException {
       String PayID = paymentBO.generateNextId();
       lblPaymentId1.setText(PayID);

       String Student_course = studentCourseBO.generateNextId();
       lblStudentCourseId1.setText(Student_course);


    }

    private void LocalDate() {
        Date date = Date.valueOf(LocalDate.now());
        lblDate1.setText(String.valueOf(date));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String contact = cmbStudentPhoneNumber.getValue();
        String CourseID = lblCourseID.getText();


        Student studentDTO = studentBO.searchByContact(contact);
        if (studentDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Student not found!").show();
            return;
        }
        StudentDTO student = new StudentDTO(
                studentDTO.getStu_id(),
                studentDTO.getStu_name(),
                studentDTO.getStu_phone(),
                studentDTO.getStu_email(),
                studentDTO.getStu_address(),
                new UserDTO()
        );


        Course courseDTO = courseBO.searchById(CourseID);
        if (courseDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Course not found!").show();
            return;
        }
        CourseDTO course = new CourseDTO(
                courseDTO.getCourse_id(),
                courseDTO.getCourse_name(),
                courseDTO.getDuration(),
                courseDTO.getCourse_fee()
        );

        String Student_courseID = lblStudentCourseId1.getText();
        String PaymentID = lblPaymentId1.getText();
        double Fee = Double.parseDouble(lblFee1.getText());
        Date date = Date.valueOf(lblDate1.getText());

        Student_CourseDTO studentCourseDTO = new Student_CourseDTO(Student_courseID, student, course, date);
        PaymentDTO paymentDTO = new PaymentDTO(PaymentID, date, Fee, studentCourseDTO);

        StudentRegisterPlaceDTO studentRegisterPlaceDTO = new StudentRegisterPlaceDTO(studentCourseDTO, paymentDTO);

        boolean isRegister = StudentRegisterBOImpl.StudentRegisterPlace(studentRegisterPlaceDTO);
        if (isRegister) {
            clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Registration Unsuccessful!").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
         clear();
    }

    private void clear() throws SQLException, ClassNotFoundException {
        cmbStudentPhoneNumber.getSelectionModel().clearSelection();
        cmbCourseName.getSelectionModel().clearSelection();
        lblCourseID.setText("");
        lblFee1.setText("");
        lblStudentID.setText("");
        lblDuration.setText("");
        generateNextId();

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
     Course course = courseBO.searchByName(CourseName);
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
