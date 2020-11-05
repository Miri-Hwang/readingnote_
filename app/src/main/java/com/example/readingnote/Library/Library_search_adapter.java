package com.example.readingnote.Library;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readingnote.ImageLoadTask;
import com.example.readingnote.R;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Library_search_adapter extends RecyclerView.Adapter<Library_search_adapter.Library_search_ViewHolder> {
    Context context;

    private ArrayList<Library_search_list_item> newbooklist;
    String image;


    //뷰홀더에서 화면과 연결
    public class Library_search_ViewHolder extends RecyclerView.ViewHolder{
        ImageView mNewbook_image;
        TextView mNewbook_title, mNewbook_author, mNewbook_publisher, mNewbook_url;


        public Library_search_ViewHolder(@NonNull View view) {
            super(view);
            this.mNewbook_image = (ImageView) view.findViewById(R.id.Newbook_image);
            this.mNewbook_title = (TextView) view.findViewById(R.id.Newbook_title);
            this.mNewbook_author = (TextView) view.findViewById(R.id.Newbook_author);
            this.mNewbook_publisher = (TextView) view.findViewById(R.id.Newbook_publisher);
            this.mNewbook_url = (TextView) view.findViewById(R.id.Newbook_url);

        }
    }
    //여기까지 뷰홀더에서 화면과 연결

    //생성자
    public Library_search_adapter(ArrayList<Library_search_list_item> newbooklist){
        this.newbooklist = newbooklist;
    }


    @NonNull
    @Override
    public Library_search_ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_library_search_list_item,parent,false);

        final Library_search_ViewHolder viewholder = new Library_search_ViewHolder(view);

        // 책 등록하기 시작
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(),Save_newbook.class);
                intent.putExtra("제목",viewholder.mNewbook_title.getText());
                intent.putExtra("저자",viewholder.mNewbook_author.getText());
                intent.putExtra("출판사",viewholder.mNewbook_publisher.getText());
                intent.putExtra("이미지",viewholder.mNewbook_url.getText());


                parent.getContext().startActivity(intent);

            }
        });

        // 책 등록하기 끝
        return viewholder;

    }

    public void onBindViewHolder(@NonNull Library_search_ViewHolder holder, int position) {
        image = newbooklist.get(position).getNewbook_image().replace("\n", "");

        Log.e("보내는 url", image);
//        holder.mNewbook_image.setImageURI(uri);
//        Glide.with(holder.itemView.getContext()).load(image).override(80,100).into(holder.mNewbook_image);
        ImageLoadTask task = new ImageLoadTask(image, holder.mNewbook_image);
        task.execute();
        holder.mNewbook_url.setText(image);
        holder.mNewbook_url.setVisibility(View.GONE);
        holder.mNewbook_author.setText(newbooklist.get(position).Newbook_author);
        holder.mNewbook_title.setText(newbooklist.get(position).Newbook_title);
        holder.mNewbook_publisher.setText(newbooklist.get(position).Newbook_publisher);
    }



    @Override
    public int getItemCount() {
        return newbooklist.size();
    }




}



