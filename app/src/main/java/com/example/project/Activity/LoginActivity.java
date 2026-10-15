 package com.example.project.Activity;

 import androidx.appcompat.app.AppCompatActivity;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.example.project.Helper.DBHelper;
 import com.example.project.MyProperties;
 import com.example.project.R;
 import com.google.android.material.textfield.TextInputEditText;

 public class LoginActivity extends AppCompatActivity {
     EditText username, password, repassword, name, email, contact, address;
     TextView signin, signup;
     DBHelper DB;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);

         name = (EditText) findViewById(R.id.name);
         email = (EditText) findViewById(R.id.email);
         contact = (EditText) findViewById(R.id.contact);
         address = (EditText) findViewById(R.id.address);

         username = (EditText) findViewById(R.id.username);
         password = (EditText) findViewById(R.id.password);
         repassword = (EditText) findViewById(R.id.repassword);

         signup = (TextView) findViewById(R.id.signupBtn);
         signin = (TextView) findViewById(R.id.signinBtn);

         DB = new DBHelper(this);

         signup.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {

                 String userName = name.getText().toString();
                 String userEmail = email.getText().toString();
                 Long userContact = Long.valueOf(contact.getText().toString());
                 String userAddress = address.getText().toString();

                 String user = username.getText().toString();
                 String pass = password.getText().toString();
                 String repass = repassword.getText().toString();

                 if (user.equals("") || pass.equals("") || repass.equals(""))
                     Toast.makeText(LoginActivity.this, "Fill all required fields", Toast.LENGTH_SHORT).show();
                 else {
                     if (pass.equals(repass)) {
                         Boolean checkuser = DB.checkusername(user);
                         if (checkuser == false) {
                             Boolean insert = DB.insertData(user, pass, userName, userEmail, String.valueOf(userContact), userAddress);
                             if (insert == true) {
                                 MyProperties.getInstance().username = user;
                                 Toast.makeText(LoginActivity.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                                 startActivity(intent);
                                 finish();
                             } else {
                                 Toast.makeText(LoginActivity.this, "Failed Registration", Toast.LENGTH_SHORT).show();
                             }
                         } else {
                             Toast.makeText(LoginActivity.this, "Existing User", Toast.LENGTH_SHORT).show();
                         }
                     } else {
                         Toast.makeText(LoginActivity.this, "Passwords Mismatch", Toast.LENGTH_SHORT).show();
                     }
                 }
             }
         });

         signin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //Intent intent = new Intent(getApplicationContext(), LoginDBActivity.class);
                 Intent intent = new Intent(LoginActivity.this, LoginDBActivity.class);
                 startActivity(intent);
                 finish();
             }
         });

     }
 }