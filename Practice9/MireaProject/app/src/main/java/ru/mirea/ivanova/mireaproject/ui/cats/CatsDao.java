package ru.mirea.ivanova.mireaproject.ui.cats;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CatsDao {
    @Query("SELECT * FROM Cats")
    LiveData<List<Cats>> getAllCats();

    @Insert
    void insert(Cats cats);

    @Update
    void update(Cats cats);

    @Delete
    void delete(Cats cats);
}