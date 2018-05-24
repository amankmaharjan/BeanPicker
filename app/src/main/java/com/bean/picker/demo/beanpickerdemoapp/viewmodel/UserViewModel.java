package com.bean.picker.demo.beanpickerdemoapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.bean.picker.demo.beanpickerdemoapp.model.User;
import com.bean.picker.demo.beanpickerdemoapp.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<User>> mAllUsers;

    public UserViewModel(Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public void insert(User User) {
        mRepository.insert(User);
    }

    public void removeAll() {

        mRepository.removeAll();
    }
}