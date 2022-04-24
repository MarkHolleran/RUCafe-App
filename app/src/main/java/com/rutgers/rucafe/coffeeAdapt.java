package com.rutgers.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class coffeeAdapt extends RecyclerView.Adapter<coffeeAdapt.coffeeViewHolder> {
    private ArrayList<?> itemArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class coffeeViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView deleteImage;

        public coffeeViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            deleteImage = itemView.findViewById(R.id.deleteButton);

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Remove");
                    alert.setMessage("Would you like to remove this Item?");

                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            if (listener != null) {
                                int position = getAdapterPosition();
                                if (position != RecyclerView.NO_POSITION) {
                                    listener.onDeleteClick(position);
                                }
                            }
                            Toast.makeText(itemView.getContext(), "Item has been removed.", Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Toast.makeText(itemView.getContext(),  "Item has not been removed.", Toast.LENGTH_LONG).show();
                        }
                    });

                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
    }

    public coffeeAdapt(ArrayList<?> list) {
        itemArrayList = list;
    }

    @Override
    public coffeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewss = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        coffeeViewHolder cViewHolder = new coffeeViewHolder(viewss, listener);
        return cViewHolder;
    }

    @Override
    public void onBindViewHolder(coffeeViewHolder holder, int position) {
        //MenuItem currentItem = itemArrayList.get(position);
        holder.textView.setText(itemArrayList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
}
