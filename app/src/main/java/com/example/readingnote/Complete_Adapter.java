package com.example.readingnote;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readingnote.Library.Book_completed;
import com.example.readingnote.Library.Book_completed_detail;
import com.example.readingnote.Library.Book_ing_detail;
import com.example.readingnote.Library.Completed_Book_item;

import java.util.ArrayList;

public class Complete_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class CompleteViewHolder extends RecyclerView.ViewHolder{
        TextView mFinish_title, mFinish_date, mFinish_position;
        ImageView mFinish_image;
        public CompleteViewHolder(@NonNull final View itemView) {
            super(itemView);
            mFinish_date = itemView.findViewById(R.id.Finish_date);
            mFinish_image = itemView.findViewById(R.id.Finish_image);
            mFinish_title = itemView.findViewById(R.id.Finish_title);
            mFinish_position = itemView.findViewById(R.id.Finish_position);
            mFinish_position.setVisibility(View.INVISIBLE);

            //아이템뷰 클릭 시작
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext().getApplicationContext(), Book_completed_detail.class);
                    intent.putExtra("포지션",mFinish_position.getText().toString());
                    itemView.getContext().startActivity(intent);
                }
            });
            //아이템뷰 클릭 종료
        }
    }

    private ArrayList<Completed_Book_item> completedArrayList;
    public Complete_Adapter(ArrayList<Completed_Book_item> completedArrayList){
        this.completedArrayList = completedArrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_complete_book_item, parent, false);

        return new CompleteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CompleteViewHolder viewHolder = (CompleteViewHolder) holder;
        viewHolder.mFinish_title.setText(completedArrayList.get(position).getTtitle());
        viewHolder.mFinish_date.setText(completedArrayList.get(position).getEnddate());
        viewHolder.mFinish_position.setText(Integer.toString(position));
        ImageLoadTask task = new ImageLoadTask(completedArrayList.get(position).getImage(), viewHolder.mFinish_image);
        task.execute();
    }

    @Override
    public int getItemCount() {
        return completedArrayList.size();
    }
}
