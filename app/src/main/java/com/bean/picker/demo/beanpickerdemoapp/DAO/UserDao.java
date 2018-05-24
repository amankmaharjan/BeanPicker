package com.bean.picker.demo.beanpickerdemoapp.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.bean.picker.demo.beanpickerdemoapp.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE name  LIKE :first LIMIT 1")
    User findByName(String first);

    @Insert
    void insertAll(User... users);
    @Insert
    void insert(User User);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();

}