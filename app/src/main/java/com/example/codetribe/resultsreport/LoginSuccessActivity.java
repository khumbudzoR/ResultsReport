package com.example.codetribe.resultsreport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {

    TextView name,studentno,subject,semester,comments,grade,etID,marks;
    SQLiteDBHelper peopleDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        peopleDB = new SQLiteDBHelper(getApplicationContext());

        //To hide AppBar for fullscreen.
        ActionBar ab = getSupportActionBar();
        ab.hide();

        TextView txtname = (TextView) findViewById(R.id.txt_success_name);
        TextView txtemail = (TextView) findViewById(R.id.txt_success_email);
        Button _btnlogout = (Button) findViewById(R.id.btn_logout);
         comments =(TextView) findViewById(R.id.textComments);
        grade =(TextView) findViewById(R.id.textGrade);
        etID =(TextView) findViewById(R.id.etID);
        semester =(TextView) findViewById(R.id.textSemester);
        subject =(TextView) findViewById(R.id.textSubject);
       marks =(TextView) findViewById(R.id.textMarks);
     name =(TextView) findViewById(R.id.textname);
      studentno =(TextView) findViewById(R.id.textstudentno);

        Intent intent = getIntent();

        String loginName = intent.getStringExtra("fullname");
        String loginEmail = intent.getStringExtra("email");
        txtname.setText("Welcome, " + loginName);
        txtemail.setText(loginEmail);

        _btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginSuccessActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Do you want to logout ??");
                builder.setPositiveButton("Take me away!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(LoginSuccessActivity.this, LoginActivity.class);
                        startActivity(intent);

                        finish();

                    }
                });

                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        ResultsClass user =(ResultsClass) peopleDB.showData();

       // etNewName.setText("student no"+user.getEtNewName));
        studentno.setText(" Student no "+user.getStudentno());
        name.setText("Name "+user.getName());
        grade.setText("Grade "+user.getGrade());
        marks.setText("Marks "+user.getMarks());
        subject.setText("subject"+user.getSubject());
        semester.setText("semester"+user.getSemester());
        comments.setText("Comments"+user.getComments());
//        etID.setText(""+user.getEtId());


    }
}






