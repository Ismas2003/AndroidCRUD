package com.example.androidcrud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcrud.R;
import com.example.androidcrud.tables.Doctors;

import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder> {

    Context context;
    List<Doctors> doctors;

    public DoctorsAdapter(Context context, List<Doctors> doctors) {
        this.context = context;
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View doctorsItems = LayoutInflater.from(context).inflate(R.layout.rv_doctors, parent, false);
        return new DoctorsViewHolder(doctorsItems);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsAdapter.DoctorsViewHolder holder, int position) {
        holder.firstName.setText(doctors.get(position).firstName);
        holder.lastName.setText(doctors.get(position).lastName);
        holder.patronymic.setText(doctors.get(position).patronymic);
        holder.experience.setText(String.valueOf(doctors.get(position).experience));
        holder.login.setText(doctors.get(position).login);
        holder.password.setText(doctors.get(position).password);
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public static final class DoctorsViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, patronymic, experience, login, password;

        public DoctorsViewHolder(@NonNull View itemView) {
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