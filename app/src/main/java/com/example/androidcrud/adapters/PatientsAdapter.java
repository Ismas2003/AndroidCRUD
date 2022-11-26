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
import com.example.androidcrud.addEditActivities.PatientsAeActivity;
import com.example.androidcrud.tables.Patients;

import java.util.List;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.AdapterViewHolder> {
    Context context;
    List<Patients> list;

    public void search(List<Patients> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public PatientsAdapter(Context context, List<Patients> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_patients, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.firstName.setText(list.get(position).firstName);
        holder.lastName.setText(list.get(position).lastName);
        holder.patronymic.setText(list.get(position).patronymic);
        holder.address.setText(list.get(position).address);
        holder.wardId.setText(String.valueOf(list.get(position).wardId));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PatientsAeActivity.class);
            intent.putExtra("id", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, patronymic, address, wardId;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
            patronymic = itemView.findViewById(R.id.tv_patronymic);
            address = itemView.findViewById(R.id.tv_address);
            wardId = itemView.findViewById(R.id.tv_ward_id);
        }
    }
}