package com.example.readingnote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readingnote.Library.Book_ing;
import com.example.readingnote.Library.Book_ing_detail;
import com.example.readingnote.Library.Book_ing_main;
import com.example.readingnote.Library.Item;

import java.util.List;

public class Book_ing_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items;
    public Book_ing_adapter(List<Item> items){
        this.items = items;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == 0){
            return new MainViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.activity_book_ing_main,parent,false)
            );
        } else {
            return new Book_ing_ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.activity_book_ing,parent,false)
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 0){
            Book_ing_main book_ing_main = (Book_ing_main) items.get(position).getObject();
            ((MainViewHolder)holder).setMain(book_ing_main);
        } else {
            Book_ing book_ing = (Book_ing) items.get(position).getObject();
            ((Book_ing_ViewHolder)holder).setBook_ing(book_ing);
            ((Book_ing_ViewHolder) holder).mBook_ing_position.setText(Integer.toString(position));

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }


    //여기까지 어답터 기본 메소드
    //메인 클래스 뷰홀더 시작
    static class MainViewHolder extends RecyclerView.ViewHolder{

        private TextView mBook_ing_main_title, mBook_ing_number;


        public MainViewHolder(@NonNull View itemView){
            super(itemView);
            mBook_ing_main_title = itemView.findViewById(R.id.Book_ing_main_title);
            mBook_ing_number = itemView.findViewById(R.id.Book_ing_number);
        }
        void setMain(Book_ing_main book_ing_main){
            mBook_ing_main_title.setText("읽고 있는 책");
            mBook_ing_number.setText(book_ing_main.getBook_ing_number());
        }
    }
    //메인 클래스 뷰홀더 끝

    //읽고 있는 책 뷰홀더 시작
    static class Book_ing_ViewHolder extends RecyclerView.ViewHolder{
        private TextView mBook_ing_title, mBook_ing_date, mBook_ing_note_number, mBook_ing_position;
        private ImageView mBook_ing_image;



        public Book_ing_ViewHolder(@NonNull final View itemView){
            super(itemView);
            mBook_ing_title = itemView.findViewById(R.id.Book_ing_title);
            mBook_ing_date = itemView.findViewById(R.id.Book_ing_date);
            mBook_ing_position = itemView.findViewById(R.id.Book_ing_position);
            mBook_ing_position.setVisibility(View.INVISIBLE);
            mBook_ing_image = itemView.findViewById(R.id.Book_ing_image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext().getApplicationContext(), Book_ing_detail.class);
                    intent.putExtra("포지션",mBook_ing_position.getText().toString());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
        void setBook_ing(Book_ing book_ing){
            ImageLoadTask task = new ImageLoadTask(book_ing.getBook_ing_image(), mBook_ing_image);
            task.execute();
            mBook_ing_title.setText(book_ing.getBook_ing_title());
            mBook_ing_date.setText(book_ing.getBook_ing_date());

        }

    }
    //읽고 있는 책 뷰홀더 끝
}


