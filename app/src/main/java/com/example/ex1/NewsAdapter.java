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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    ArrayList<NewsVO> array;
    Context context;
    String link;



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
        holder.txtlink.setText(Html.fromHtml(array.get(position).getLink()));
        link = array.get(position).getLink();
    }


    @Override
    public int getItemCount() {
        return array.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

//        URL API = NaverAPI.dbdkfdpf(query, start);
//        String asd = API.toString();
        TextView txttitle,txtcontent,txtlink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=itemView.findViewById(R.id.txttitle);
            txtcontent=itemView.findViewById(R.id.txtcontent);
            txtlink=itemView.findViewById(R.id.txtlink);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{

                    Log.d("test","link"+getAdapterPosition());

                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
                   //최종적으로 NaverAPI에 있는 URL주소값을 가져와서 Uri.parse에 넣은 뒤 각각의 뉴스내용 클릭시 해당하는 뉴스 띄우기

                    context.startActivity(intent);
    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }
    }
}