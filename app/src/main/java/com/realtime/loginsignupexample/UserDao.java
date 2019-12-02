package com.realtime.loginsignupexample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao
{
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE email IN (:email)")
    List<User> getUserByEmail(String email);

    @Insert
    void insertUser(User users);

}
