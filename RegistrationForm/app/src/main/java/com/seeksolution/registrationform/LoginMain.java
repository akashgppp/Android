package com.seeksolution.registrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginMain extends AppCompatActivity {
    private EditText et_email, et_password;
    private TextView error_email, error_password;
    private String email, password;
    public static final Pattern EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PASSWORD_REGEX = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$\"");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        error_email = (TextView) findViewById(R.id.tv_eroor_email);
        error_password = (TextView) findViewById(R.id.tv_password);
    }

    public void submit(View view) {
        email = et_email.getText().toString();
        password = et_password.getText().toString();

        if (validateEmail(email) | validatePassword(password)) {
            Toast.makeText(this, "" + email + password, Toast.LENGTH_SHORT).show();
            if (email.equals("admin@gmail.com") && password.equals("Password@123")) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent=new Intent(getApplicationContext(),DashboardIndex.class);
        startActivity(intent);
        finish();
        //blank validation for email
        //for password

    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            error_email.setText("Email field is Required");
            et_email.setBackgroundResource(R.drawable.red_error);
            return false;
        }
        if (!EMAIL_ADDRESS_REGEX.matcher(email).matches()) {
            error_email.setText("Invalid Email");
            return false;
        }
        et_email.setBackgroundResource(R.drawable.success_error);
        error_email.setText("");
        return true;
    }

    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            error_password.setText("Password field is Required");
            et_password.setBackgroundResource(R.drawable.red_error);
            return false;
        }
        if (!PASSWORD_REGEX.matcher(password).matches()) {
            error_password.setText("Invalid Password");
            return false;
        }
        et_password.setBackgroundResource(R.drawable.success_error);
        error_password.setText("");
        return true;
    }

    public void Change(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
finish();
            }
        }, 500);

    }
}