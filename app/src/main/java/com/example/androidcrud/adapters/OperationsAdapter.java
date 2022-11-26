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
import com.example.androidcrud.addEditActivities.OperationsAeActivity;
import com.example.androidcrud.tables.Operations;

import java.util.List;

public class OperationsAdapter extends RecyclerView.Adapter<OperationsAdapter.AdapterViewHolder> {
    Context context;
    List<Operations> list;

    public void search(List<Operations> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public OperationsAdapter(Context context, List<Operations> operations) {
        this.context = context;
        this.list = operations;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_operations, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).name);
        holder.description.setText(list.get(position).description);
        holder.operationTypeId.setText(String.valueOf(list.get(position).operationTypeId));
        holder.doctorId.setText(String.valueOf(list.get(position).doctorId));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, OperationsAeActivity.class);
            intent.putExtra("id", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView name, description, operationTypeId, doctorId;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_description);
            operationTypeId = itemView.findViewById(R.id.tv_operation_type_id);
            doctorId = itemView.findViewById(R.id.tv_doctor_id);
        }
    }
}