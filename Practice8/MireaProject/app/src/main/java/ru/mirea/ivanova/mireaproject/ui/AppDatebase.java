package ru.mirea.ivanova.mireaproject.ui;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.ivanova.mireaproject.ui.cats.Cats;
import ru.mirea.ivanova.mireaproject.ui.cats.CatsDao;
import ru.mirea.ivanova.mireaproject.ui.owner.Owner;
import ru.mirea.ivanova.mireaproject.ui.owner.OwnerDao;

@Database(entities = {Owner.class, Cats.class}, version = 2)
public abstract class AppDatebase extends RoomDatabase{
    public abstract OwnerDao ownerDao();
    public abstract CatsDao catsDao();
}