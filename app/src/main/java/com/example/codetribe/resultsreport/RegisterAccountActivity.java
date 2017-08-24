package com.example.codetribe.resultsreport;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.codetribe.resultsreport.R.id.btn_reg;

public class RegisterAccountActivity extends AppCompatActivity {
RadioGroup radioGroup;
    RadioButton teacher , students;
    SQLiteDBHelper openHelper;
    SQLiteDatabase db;
    String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        //To hide AppBar for fullscreen.
        ActionBar ab = getSupportActionBar();
        ab.hide();

        openHelper = new SQLiteDBHelper(this);

        //Referencing EditText widgets and Button placed inside in xml layout file
        final EditText _txtfullname = (EditText) findViewById(R.id.txtname_reg);
        final EditText _txtemail = (EditText) findViewById(R.id.txtemail_reg);
        final EditText _txtpass = (EditText) findViewById(R.id.txtpass_reg);
        final EditText _txtmobile = (EditText) findViewById(R.id.txtmobile_reg);
        Button _btnreg = (Button) findViewById(btn_reg);
        radioGroup =(RadioGroup)findViewById(R.id.radioGroup);
       // teacher=(RadioButton)findViewById(R.id.teacher);
        //students=(RadioButton)findViewById(R.id.students);

        //radioGroup =(RadioGroup) findViewById(R.id.radioGroup);
       // radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          //  @Override
          //  public void onCheckedChanged(RadioGroup radioGroup, int i) {

              //  if (i == R.id.teacher){
                    select = "Teacher";
               // }

               // if (i == R.id.students){
                    select = "Student";
               // }

           // }
       // });


        //Register Button Click Event
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (select == "Teacher"){
                    Intent i = new Intent(RegisterAccountActivity.this,LoginActivity.class);
                    startActivity(i);
                }

                if (select == "Student"){
                    Intent i = new Intent(RegisterAccountActivity.this,LoginActivity.class);
                    startActivity(i);
                }
                db = openHelper.getWritableDatabase();

                String fullname = _txtfullname.getText().toString();
                String email = _txtemail.getText().toString();
                String pass = _txtpass.getText().toString();
                String mobile = _txtmobile.getText().toString();
                if(fullname.length()>0 && email.length()>0 && pass.length()>0 && mobile.length()>0) {

                //Calling InsertData Method - Defined below
                InsertData(fullname, email, pass, mobile);

                //Alert dialog after clicking the Register Account
                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterAccountActivity.this);
                builder.setTitle("Information");
                builder.setMessage("Your Account is Successfully Created.");
                builder.setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Finishing the dialog and removing Activity from stack.
                        dialogInterface.dismiss();
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
                else{
                    Toast.makeText(RegisterAccountActivity.this, "fill all the field to continue", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //Inserting Data into database - Like INSERT INTO QUERY.
    public void InsertData(String fullName, String email, String password, String mobile ) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN_FULLNAME,fullName);
        values.put(SQLiteDBHelper.COLUMN_EMAIL,email);
        values.put(SQLiteDBHelper.COLUMN_PASSWORD,password);
        values.put(SQLiteDBHelper.COLUMN_MOBILE,mobile);
        long id = db.insert(SQLiteDBHelper.TABLE_NAME,null,values);

    }

}