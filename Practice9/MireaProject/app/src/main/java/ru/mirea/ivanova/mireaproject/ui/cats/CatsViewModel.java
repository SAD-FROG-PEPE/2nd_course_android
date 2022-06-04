package ru.mirea.ivanova.mireaproject.ui.cats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.ivanova.mireaproject.ui.App;
import ru.mirea.ivanova.mireaproject.ui.AppDatebase;

public class CatsViewModel extends ViewModel {
    private final LiveData<List<Cats>> cats;
    private final AppDatebase appDatabase = App.instance.getDatabase();
    private final CatsDao catsDao = appDatabase.catsDao();

    public CatsViewModel() {
        cats = catsDao.getAllCats();
    }

    public void addCats(Cats cats) {
        catsDao.insert(cats);
    }

    public LiveData<List<Cats>> getCatsLiveData() {
        return cats;
    }

    public List<Cats> getCats() {
        return cats.getValue();
    }
}