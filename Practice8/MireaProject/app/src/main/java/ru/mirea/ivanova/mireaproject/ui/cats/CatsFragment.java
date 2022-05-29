package ru.mirea.ivanova.mireaproject.ui.cats;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.ivanova.mireaproject.R;

public class CatsFragment extends Fragment {
    private final List<Cats> cats = new ArrayList<>();
    private CatsViewModel catsViewModel;
    private final CatsAdapter catsAdapter = new CatsAdapter(cats);
    private RecyclerView recyclerView;
    private ActivityResultLauncher<Intent> launcher;
    public static final int ADD_CAT_RESULT_CODE=1;
    public static final String NAME_LABEL="name";
    public static final String COLOUR_LABEL="colour";
    public static final String BREED_LABEL="breed";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cats, container, false);

        if (getActivity() != null){
            catsViewModel = new ViewModelProvider(getActivity()).get(CatsViewModel.class);
            catsViewModel.getCatsLiveData().observe(getActivity(), this::onChanged);
        }
        view.findViewById(R.id.btnAddCat).setOnClickListener(this::addBtn);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.catsRecyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(catsAdapter);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
                    if (result.getResultCode() == ADD_CAT_RESULT_CODE && result.getData() != null){
                        Cats cat = new Cats();
                        cat.name = result.getData().getStringExtra(NAME_LABEL);
                        cat.colour = result.getData().getStringExtra(COLOUR_LABEL);
                        cat.breed = result.getData().getStringExtra(BREED_LABEL);
                        catsViewModel.addCats(cat);
                        catsAdapter.notifyDataSetChanged();
                    }
                });
        return view;
    }

    public void onChanged(List<Cats> catfromDB) {
        if (!cats.isEmpty()){
            cats.clear();
        }
        cats.addAll(catfromDB);
        catsAdapter.notifyDataSetChanged();
    }

    private void addBtn(View view){
        Intent intent = new Intent(getActivity(), CatsView.class);
        launcher.launch(intent);
    }
}