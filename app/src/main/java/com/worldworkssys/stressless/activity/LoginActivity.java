package com.worldworkssys.stressless.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.databinding.ActivityLoginBinding;
import com.worldworkssys.stressless.model.UserAccount;
import com.worldworkssys.stressless.persistance.StressLessRepository;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    StressLessRepository stressLessRepository;
    Context mContext;
    List<UserAccount> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        stressLessRepository = new StressLessRepository(getApplicationContext());
        mContext = LoginActivity.this;

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidation();
            }
        });
    }

    private void loginValidation() {
        String userName = binding.etUserName.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (userName.isEmpty()) {
            Toast.makeText(mContext, "Please enter user name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(mContext, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        users = stressLessRepository.getAllData();
        if(userName.equals( users .get(0).getUserName())&&password.equals( users .get(0).getPassword())){
            Toast.makeText(mContext, "Successfully logged in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(mContext, "Invalid login details", Toast.LENGTH_SHORT).show();
        }
    }
}
