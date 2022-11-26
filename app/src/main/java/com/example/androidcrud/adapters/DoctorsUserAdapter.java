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

public class DoctorsUserAdapter extends RecyclerView.Adapter<DoctorsUserAdapter.AdapterViewHolder> {

    Context context;
    List<Doctors> list;

    public DoctorsUserAdapter(Context context, List<Doctors> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_doctors_user, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.firstName.setText(list.get(position).firstName);
        holder.lastName.setText(list.get(position).lastName);
        holder.patronymic.setText(list.get(position).patronymic);
        holder.experience.setText(String.valueOf(list.get(position).experience));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, patronymic, experience;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
            patronymic = itemView.findViewById(R.id.tv_patronymic);
            experience = itemView.findViewById(R.id.tv_experience);
        }
    }
}