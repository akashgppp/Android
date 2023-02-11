package com.seeksolution.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import kotlin.jvm.internal.PropertyReference0Impl;

public class MainActivity extends AppCompatActivity {
    private static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    private static final Pattern PASSWORD_REGEX_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^([A-Za-z]{2,}\\s[A-Za-z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)");



    private EditText editText1, editText2, editText3, editText4, editText5;
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;
    private Button button;
    private AutoCompleteTextView autoCompleteTextView;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String gender="";
    private  static String CHANNEL_ID="MY_CHANNEL";
    private static int MASSAGE_ID=100;

    ArrayList<String> city_data=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        editText1 = (EditText) findViewById(R.id.et_name);
        editText2 = (EditText) findViewById(R.id.et_email);
        editText3 = (EditText) findViewById(R.id.et_phone);
        editText4 = (EditText) findViewById(R.id.et_password);
        editText5 = (EditText) findViewById(R.id.et_CPassword);
        textView1 = (TextView) findViewById(R.id.tv_name);
        textView2 = (TextView) findViewById(R.id.tv_email);
        textView3 = (TextView) findViewById(R.id.tv_phoneNumber);
        textView4 = (TextView) findViewById(R.id.tv_pass);
        textView5 = (TextView) findViewById(R.id.tv_cpass);
        textView6 = (TextView) findViewById(R.id.textView_city);
        textView7 = (TextView) findViewById(R.id.tv_nameRadio);
        button = (Button) findViewById(R.id.register);
        autoCompleteTextView=(AutoCompleteTextView) findViewById(R.id.text_viewCity);
        radioGroup=(RadioGroup)findViewById(R.id.rg_main);
        city_data.add("Lucknow");
                city_data.add("Pratapgarh");
                city_data.add("Prayagraj");
                city_data.add("Mau");
                city_data.add("Sultanpur");
                city_data.add("Ajamgarh");
                city_data.add("Banaras");
                city_data.add("Kanpur");
                city_data.add("Mirjapur");
                city_data.add("Jaunpur");
                ArrayAdapter<String> item=new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_layout,R.id.text_view_custom,city_data);
                autoCompleteTextView.setAdapter(item);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String Name = editText1.getText().toString();
                String email = editText2.getText().toString();
                String Phone = editText3.getText().toString();
                String password = editText4.getText().toString();
                String CPass = editText5.getText().toString();
                String city=autoCompleteTextView.getText().toString();

                if (validateName(Name) & validateEmail(email) & validatePhone(Phone)
                        & validatePassword(password) & validateCPassword(CPass)
                        & validateCity(city) & validateGender(gender)) {
                    Toast.makeText(MainActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getApplicationContext(),LoginMain.class);
                    startActivity(intent);
                    finish();
                    Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.users,null);
                    BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
                    Bitmap largicon=bitmapDrawable.getBitmap();

                    NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification notification=new Notification.Builder(getApplicationContext()).setLargeIcon(largicon)
                            .setSmallIcon(R.drawable.users).setContentText("Registration Successfully")
                            .setChannelId(CHANNEL_ID)
                            .setSubText("Register success").build();
                    notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"new Channel",NotificationManager.IMPORTANCE_HIGH));
                    notificationManager.notify(MASSAGE_ID,notification);
                }

            }
        });

    }

    public void Gender(View view) {
        int RadioId=radioGroup.getCheckedRadioButtonId();
        radioButton=(RadioButton) findViewById(RadioId);
        gender=radioButton.getText().toString();
        Toast.makeText(this, "Your gender is"+gender, Toast.LENGTH_SHORT).show();
    }

    //validation all module
    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            textView2.setText("field is required");
            editText2.setBackgroundResource(R.drawable.red_error);
            return false;
        }
        if (!EMAIL_REGEX_PATTERN.matcher(email).matches()) {
            textView2.setText("Invalid Email");
            return false;
        }
        textView2.setText("");
        editText2.setBackgroundResource(R.drawable.success_error);
        return true;
    }

    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            textView4.setText("field is required");
            editText4.setBackgroundResource(R.drawable.red_error);
            return false;
        }
        if (!PASSWORD_REGEX_PATTERN.matcher(password).matches()) {
            textView4.setText("Invalid Password");
            return false;
        }
        textView4.setText("");
        editText4.setBackgroundResource(R.drawable.success_error);
        return true;
    }

    private boolean validateName(String Name) {
        if (Name.isEmpty()) {
            textView1.setText("field is required");
            editText1.setBackgroundResource(R.drawable.red_error);
            return false;
        }
        if (!NAME_PATTERN.matcher(Name).matches()) {
            textView1.setText("Invalid Name");
            return false;
        }
        textView1.setText("");
        editText1.setBackgroundResource(R.drawable.success_error);
        return true;
    }

    private boolean validatePhone(String Phone) {
        if (Phone.isEmpty()) {
            textView3.setText("field is required");
            editText3.setBackgroundResource(R.drawable.red_error);
            return false;
        }
        textView3.setText("");
        editText3.setBackgroundResource(R.drawable.success_error);
        return true;
    }

    private boolean validateCPassword(String CPass) {
        if (CPass.isEmpty()) {
            textView5.setText("field is required");
            editText5.setBackgroundResource(R.drawable.red_error);
            return false;
        }
        textView5.setText("");
        editText5.setBackgroundResource(R.drawable.success_error);
        return true;
    }
    private boolean validateCity(String city){
        if(city.isEmpty()){
            textView6.setText("this Field is required");
            return false;
        }
        textView6.setText("");
        return true;

    }
    private boolean validateGender(String male){
        if (male.isEmpty()){
            textView7.setText("field is required");
            return false;
        }
        textView7.setText("");
        return true;
    }

}


