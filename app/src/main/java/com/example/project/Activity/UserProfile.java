package com.example.project.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Domain.UserProfileDomain;
import com.example.project.Helper.DBHelper;
import com.example.project.MyProperties;
import com.example.project.R;

public class UserProfile extends AppCompatActivity {

    private TextView usernameTxt, nameTxt, emailTxt, contactTxt, addressTxt;
    private UserProfileDomain object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        initView();
        getUserInfo();

    }

    private void getUserInfo() {
//        object = (UserProfileDomain) getIntent().getSerializableExtra("object");
//        if(object == null)
//            return;
//        usernameTxt.setText(object.getUsername());

        MyProperties props = MyProperties.getInstance();
        usernameTxt.setText(props.username);
        UserProfileDomain object = (new DBHelper(this.getApplicationContext())).getUser(props.username);
        nameTxt.setText(object.getName());
        emailTxt.setText(object.getEmail());
        contactTxt.setText(object.getContact());
        addressTxt.setText(object.getAddress());
    }

    private void initView() {
        usernameTxt = findViewById(R.id.usernameTxt);
        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.userEmailTxt);
        contactTxt = findViewById(R.id.userContactTxt);
        addressTxt = findViewById(R.id.userAddressTxt);
    }

}

