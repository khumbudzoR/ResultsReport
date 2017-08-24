package com.example.codetribe.resultsreport;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherActivity extends AppCompatActivity {
    SQLiteDBHelper peopleDB;

    Button btnAddData, btnViewData,btnUpdateData,btnDelete;
    EditText studentno,name,marks,etID,semester,comments,subject,grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        peopleDB = new SQLiteDBHelper(this);

        studentno = (EditText) findViewById(R.id.studentno);
        name = (EditText) findViewById(R.id.name);
        marks = (EditText) findViewById(R.id.marks);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnViewData = (Button) findViewById(R.id.btnViewData);
        btnUpdateData = (Button) findViewById(R.id.btnUpdateData);
        etID = (EditText) findViewById(R.id.etID);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        comments = (EditText) findViewById(R.id.comments);
        grade = (EditText) findViewById(R.id.grade);
        semester = (EditText) findViewById(R.id.semester);
        subject = (EditText) findViewById(R.id.subject);

        AddData();
        ViewData();
        UpdateData();
        DeleteData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentnos = studentno.getText().toString();
                String names = name.getText().toString();
                String grades = grade.getText().toString();
                String mark = marks.getText().toString();
                String subjects = subject.getText().toString();
                String semesters = semester.getText().toString();
                String comment = comments.getText().toString();


                boolean insertData = peopleDB.addData( studentnos,names,grades, mark ,subjects, semesters, comment );

                if (insertData == true) {
                    Toast.makeText(TeacherActivity.this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TeacherActivity.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void ViewData() {
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ResultsClass data = peopleDB.showData();

                StringBuffer buffer = new StringBuffer();{
                buffer.append("student no: " + data.getStudentno() + "\n");
                buffer.append("Name: " + data.getName() + "\n");
                buffer.append("grade: " + data.getGrade() + "\n");
                buffer.append("marks: " + data.getMarks() + "\n");
                buffer.append("subject: " + data.getSubject() + "\n");
                buffer.append("semester: " + data.getSemester() + "\n");
                buffer.append("comments: " + data.getComments() + "\n");

                }


                display("All Stored Data:", buffer.toString());
                }

        });
}

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if (temp > 0) {
                    boolean update = peopleDB.updateData(etID.getText().toString(), studentno.getText(),name.getText().toString() ,toString(), grade.getText().toString(),  marks.getText().toString(), subject.getText().toString(),semester.getText().toString(),
                         comments.getText().toString());
                    if (update == true) {
                        Toast.makeText(TeacherActivity.this, "Successfully Updated Data!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(TeacherActivity.this, "Something Went Wrong :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(TeacherActivity.this, "You Must Enter An ID to Update :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if(temp > 0){
                    Integer deleteRow = peopleDB.deleteData(etID.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(TeacherActivity.this, "Successfully Deleted The Data!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(TeacherActivity.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(TeacherActivity.this, "You Must Enter An ID to Delete :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}