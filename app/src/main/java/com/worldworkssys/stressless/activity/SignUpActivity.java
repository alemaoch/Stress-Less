package com.worldworkssys.stressless.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.databinding.ActivitySignUpBinding;
import com.worldworkssys.stressless.model.UserAccount;
import com.worldworkssys.stressless.persistance.StressLessRepository;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    Context mContext;
    private StressLessRepository stressLessRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mContext = SignUpActivity.this;
        stressLessRepository = new StressLessRepository(getApplicationContext());

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpValidation();
            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void signUpValidation() {

        String userName = binding.etUserName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (userName.isEmpty()) {
            Toast.makeText(mContext, "Please enter user name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(mContext, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(mContext, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        UserAccount userAccount = new UserAccount(userName, email, password);
        insertUser(userAccount);
    }

    private void insertUser(UserAccount userAccount) {
        stressLessRepository.insertUser(userAccount);
        Intent intent = new Intent(mContext, DetailsActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(mContext, "Account Successfully created", Toast.LENGTH_SHORT).show();
    }
}
