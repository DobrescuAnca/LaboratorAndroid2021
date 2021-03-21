package ro.example.myapplication.lab3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertAll(User... users);

    @Update
    void updateUsers(User... users);
    //room uses the primary key to match passed entity instances to rows in the database

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT firstName FROM user")
    List<String> loadFirstName();

    @Query("SELECT * FROM user WHERE age > :minAge")
    User[] loadAllUsersOlderThan(int minAge);

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE firstName LIKE :search OR lastName LIKE :search LIMIT 1")
    User findUserWithName(String search);
}
