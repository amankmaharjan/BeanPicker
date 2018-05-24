package com.bean.picker.demo.beanpickerdemoapp.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.bean.picker.demo.beanpickerdemoapp.room.database.AppDatabase;
import com.bean.picker.demo.beanpickerdemoapp.DAO.UserDao;
import com.bean.picker.demo.beanpickerdemoapp.model.User;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> mALlusers;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        mALlusers = userDao.getAll();
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<User>> getAllUsers() {
        return mALlusers;
    }
    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (User user) {
        new insertAsyncTask(userDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    public void removeAll(){
        new deleteAsyncTask(userDao).execute();

    }
    private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        deleteAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

}
