package com.bean.picker.demo.beanpickerdemoapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@FieldDefaults( level= AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class User implements Serializable{
    @PrimaryKey(autoGenerate = true)
    int uid;
    String name;
    String  Address;
    String Email;
    String mobileNumber;
    String password;
}
