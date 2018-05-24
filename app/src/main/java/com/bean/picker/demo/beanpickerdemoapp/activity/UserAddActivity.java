package com.bean.picker.demo.beanpickerdemoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.bean.picker.demo.beanpickerdemoapp.R;
import com.bean.picker.demo.beanpickerdemoapp.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAddActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.userlistsql.REPLY";
    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_address)
    EditText _addressText;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_mobile)
    EditText _mobileText;
    @BindView(R.id.input_password)
    EditText _passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_save)
    public void addUser() {

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        User user = new User(0, name, address, email, mobile, password);

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, user);
        setResult(RESULT_OK, replyIntent);

        finish();

    }
}
