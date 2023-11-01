package com.example.lab1_adrnw.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1_adrnw.Dto.PhotoModel;
import com.example.lab1_adrnw.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private List<PhotoModel> list;
    private Context context;

    public PhotoAdapter(List<PhotoModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoModel photoModel = list.get(position);
        holder.txtID.setText(String.valueOf(photoModel.getId()));
        holder.txtTitle.setText(photoModel.getTitle());
        Picasso.get().load(photoModel.getUrl()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView txtID, txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgView);
            txtID = itemView.findViewById(R.id.txtID);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
