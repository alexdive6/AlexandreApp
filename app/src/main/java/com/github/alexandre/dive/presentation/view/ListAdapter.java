package com.github.alexandre.dive.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.github.alexandre.dive.R;
import com.github.alexandre.dive.presentation.model.Pokemon;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {
    private  List<Pokemon> values;
    private List<Pokemon> values_copy;
    private  OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Pokemon item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
        TextView txtFooter;
        ImageView img;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            img = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void add(int position, Pokemon item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public ListAdapter(List<Pokemon> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
        values_copy = new ArrayList<>(values);
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pokemon currentPokemon = values.get(position);
        holder.txtHeader.setText(currentPokemon.getName());
        holder.txtFooter.setText(currentPokemon.getUrl());
        holder.img.setImageResource(R.drawable.logo_pokeball);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                listener.onItemClick(currentPokemon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public Filter getFilter(){
        return valuesFilter;
    }

    private Filter valuesFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pokemon> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(values_copy);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Pokemon poke : values_copy){
                    if (poke.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(poke);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
           values.clear();
           values.addAll((List) results.values);
           notifyDataSetChanged();
        }
    };
}