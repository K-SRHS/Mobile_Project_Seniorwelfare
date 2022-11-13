package com.example.ex1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    ArrayList<NewsVO> array;
    Context context;

    public NewsAdapter(ArrayList<NewsVO> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttitle.setText(Html.fromHtml(array.get(position).getTitle()));
        holder.txtcontent.setText(Html.fromHtml(array.get(position).getContent()));
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttitle,txtcontent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("test","dsa"+getAdapterPosition());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com/"));
                }
            });
            txttitle=itemView.findViewById(R.id.txttitle);
            txtcontent=itemView.findViewById(R.id.txtcontent);
        }
    }
}