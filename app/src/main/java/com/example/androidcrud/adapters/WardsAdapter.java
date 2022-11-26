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
import com.example.androidcrud.addEditActivities.WardsAeActivity;
import com.example.androidcrud.tables.Wards;

import java.util.List;

public class WardsAdapter extends RecyclerView.Adapter<WardsAdapter.AdapterViewHolder> {
    Context context;
    List<Wards> list;

    public void search(List<Wards> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public WardsAdapter(Context context, List<Wards> operations) {
        this.context = context;
        this.list = operations;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_wards, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.capacity.setText(String.valueOf(list.get(position).capacity));
        holder.doctorId.setText(String.valueOf(list.get(position).doctorId));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, WardsAeActivity.class);
            intent.putExtra("id", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView capacity, doctorId;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            capacity = itemView.findViewById(R.id.tv_capacity);
            doctorId = itemView.findViewById(R.id.tv_doctor_id);
        }
    }
}