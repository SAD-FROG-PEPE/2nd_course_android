package ru.mirea.ivanova.mireaproject.ui.cats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.ivanova.mireaproject.R;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.CatsViewHolder> {
    private final List<Cats> cats;

    public CatsAdapter(List<Cats> cats) {
        this.cats = cats;
    }

    @NonNull
    @Override
    public CatsAdapter.CatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cats_list, parent, false);
        return new CatsAdapter.CatsViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatsAdapter.CatsViewHolder holder, int i) {
        Cats cat = cats.get(i);
        holder.name.setText(cat.name);
        holder.colour.setText(cat.colour);
        holder.breed.setText(cat.breed);
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public static class CatsViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView colour;
        public final TextView breed;

        public CatsViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.nameCat);
            colour = view.findViewById(R.id.colourCat);
            breed = view.findViewById(R.id.breedCat);
        }
    }
}