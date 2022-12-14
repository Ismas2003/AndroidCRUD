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
import com.example.androidcrud.addEditActivities.DoctorsAeActivity;
import com.example.androidcrud.tables.Doctors;

import java.util.List;

public class DoctorsAdminAdapter extends RecyclerView.Adapter<DoctorsAdminAdapter.AdapterViewHolder> {
    Context context;
    List<Doctors> list;

    public void search(List<Doctors> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public DoctorsAdminAdapter(Context context, List<Doctors> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_doctors_admin, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.firstName.setText(list.get(position).firstName);
        holder.lastName.setText(list.get(position).lastName);
        holder.patronymic.setText(list.get(position).patronymic);
        holder.experience.setText(String.valueOf(list.get(position).experience));
        holder.login.setText(list.get(position).login);
        holder.password.setText(list.get(position).password);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DoctorsAeActivity.class);
            intent.putExtra("id", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, patronymic, experience, login, password;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
            patronymic = itemView.findViewById(R.id.tv_patronymic);
            experience = itemView.findViewById(R.id.tv_experience);
            login = itemView.findViewById(R.id.tv_login);
            password = itemView.findViewById(R.id.tv_password);
        }
    }
}