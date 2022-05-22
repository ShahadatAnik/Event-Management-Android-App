package edu.ewubd.eventmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SharedPreferences myPref;
    Button cancel, login;
    EditText userID, password, reEnterPassword;
    CheckBox rememberUserID, rememberUserLogin;
    Boolean reEnter =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        myPref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        cancel = findViewById(R.id.btn_exit);
        login = findViewById(R.id.btn_login);
        userID = findViewById(R.id.et_user_id);
        password = findViewById(R.id.et_pass);
        rememberUserID = findViewById(R.id.checkbox_user_id);
        rememberUserLogin = findViewById(R.id.checkbox_user_pass);
        reEnterPassword = findViewById(R.id.et_re_pass);
        reEnterPassword.setVisibility(View.GONE);
        cancel.setOnClickListener(v->exit());
        login.setOnClickListener(v->login());
        //checking shared pref
        String userid = myPref.getString("userId", null);
        String password = myPref.getString("password", null);
        if(userid==null){
            reEnterPassword.setVisibility(View.VISIBLE);
            reEnter = true;
        }
        System.out.println(userid);
        System.out.println(password);
    }
    void exit(){
        this.finishAffinity();
    }
    void login(){
        String prvUserId = userID.getText().toString().trim();
        String prvPassword = password.getText().toString().trim();
        String prvRePass = reEnterPassword.getText().toString().trim();
        if(rememberUserID.isChecked()){
            myPref.edit().putString("userId", prvUserId).apply();
        }
        if(rememberUserLogin.isChecked()){
            if(prvRePass!=null){
                if(prvRePass.equals(prvPassword)){
                    myPref.edit().putString("password", prvPassword).apply();
                }
            }
            else if(reEnter == false){
                myPref.edit().putString("password", prvPassword).apply();
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String userid = myPref.getString("userId", null);
        String password = myPref.getString("password", null);
        if(userid==null){
            reEnterPassword.setVisibility(View.VISIBLE);
            reEnter = true;
        }
        System.out.println(userid);
        System.out.println(password);
    }
}
