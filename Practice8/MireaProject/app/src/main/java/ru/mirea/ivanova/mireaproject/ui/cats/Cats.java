package ru.mirea.ivanova.mireaproject.ui.cats;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cats {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String colour;
    public String breed;
}