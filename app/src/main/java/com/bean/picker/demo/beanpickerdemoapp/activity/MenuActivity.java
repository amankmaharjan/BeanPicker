package com.bean.picker.demo.beanpickerdemoapp.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.bean.picker.demo.beanpickerdemoapp.R;
import com.bean.picker.demo.beanpickerdemoapp.model.User;
import com.bean.picker.demo.beanpickerdemoapp.recyclerview.UserListAdapter;
import com.bean.picker.demo.beanpickerdemoapp.viewmodel.UserViewModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    public static final int NEW_User_ACTIVITY_REQUEST_CODE = 1;

    private UserViewModel mUserViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadRecyclerView();

        ButterKnife.bind(this);
    }

    private void loadRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserListAdapter adapter = new UserListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> userList) {
                // Update the cached copy of the Users in the adapter.
                for (User u : userList
                        ) {
                    System.out.println(u.getName());
                }
                adapter.setUsers(userList);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_User_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User User = (User) data.getSerializableExtra(UserAddActivity.EXTRA_REPLY);
            mUserViewModel.insert(User);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.userAddBtn)
    public void requestAddUser() {
        Log.d("user add", "RequestAddUser");
        Intent intent = new Intent(getApplicationContext(), UserAddActivity.class);
        startActivityForResult(intent, NEW_User_ACTIVITY_REQUEST_CODE);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    @OnClick(R.id.userDelBtn)
    public void requestRemoveUser() {
        Log.d("user remove", "RequestRemoveUser");
        mUserViewModel.removeAll();
    }
}
