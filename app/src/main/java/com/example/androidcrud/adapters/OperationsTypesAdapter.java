package com.example.androidcrud.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcrud.R;
import com.example.androidcrud.addEditActivities.OperationsTypesAeActivity;
import com.example.androidcrud.tables.OperationsTypes;

import java.util.List;

public class OperationsTypesAdapter extends RecyclerView.Adapter<OperationsTypesAdapter.AdapterViewHolder> {
    Context context;
    List<OperationsTypes> list;

    public void search(List<OperationsTypes> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public OperationsTypesAdapter(Context context, List<OperationsTypes> operations) {
        this.context = context;
        this.list = operations;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_operations_types, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).name);
        holder.description.setText(list.get(position).description);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, OperationsTypesAeActivity.class);
            intent.putExtra("id", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView name, description;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_description);
        }
    }
}