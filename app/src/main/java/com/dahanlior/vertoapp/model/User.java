package com.dahanlior.vertoapp.model;

import com.dahanlior.vertoapp.constants.DbSettings;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = DbSettings.TABLE_USER)
public class User {

    public static User create(@NonNull String email, @NonNull String password) {
        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }


    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "email")
    private String email = "";

    @NonNull
    @ColumnInfo(name = "password")
    private String password = "";

    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    @Ignore
    public User(){

    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
