package ro.example.myapplication.lab3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity (tableName = "users")
//@Entity (primaryKeys = {"firstName", "lastName"})
@Entity
public class User {
    @PrimaryKey
    public int id;

//    @ColumnInfo(name = "first_name")
    public String firstName;

    @NonNull
    public String lastName;

    public int age;

    public User(int id, String firstName, @NonNull String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}