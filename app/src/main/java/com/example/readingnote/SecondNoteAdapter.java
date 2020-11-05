package com.example.readingnote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readingnote.Library.EditNote;
import com.example.readingnote.Library.Note_item;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SecondNoteAdapter extends RecyclerView.Adapter<SecondNoteAdapter.Note_item_ViewHolder>{
    ArrayList<Note_item> NoteList;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor ediotr;


    //생성자
    public SecondNoteAdapter(ArrayList<Note_item> noteList) {
        NoteList = noteList;

    }

    // 뷰홀더 클래스 시작
    static class Note_item_ViewHolder extends RecyclerView.ViewHolder{
        private TextView mSavednote_date, mSavednote_contents;
        private ImageView mSavednote_image, mSavednote_delete, mSavednote_edit;

        public Note_item_ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSavednote_date = itemView.findViewById(R.id.Savednote_date);
            mSavednote_image = itemView.findViewById(R.id.Savednote_image);
            mSavednote_contents = itemView.findViewById(R.id.Savednote_contents);
            mSavednote_delete = itemView.findViewById(R.id.Note_delete);
            mSavednote_edit = itemView.findViewById(R.id.Note_edit);
            mSavednote_delete.setVisibility(View.INVISIBLE);
            mSavednote_edit.setVisibility(View.INVISIBLE);

        }


    }
    // 뷰홀더 클래스 끝
    //어댑터 연결 시작
    @NonNull
    @Override
    public SecondNoteAdapter.Note_item_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        sharedPreferences = parent.getContext().getSharedPreferences("책",0);
        ediotr=sharedPreferences.edit();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_note_item, parent, false);
        SecondNoteAdapter.Note_item_ViewHolder holder = new SecondNoteAdapter.Note_item_ViewHolder(view);

        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull final SecondNoteAdapter.Note_item_ViewHolder holder, final int position) {
        holder.mSavednote_date.setText(NoteList.get(position).Savednote_date);
        holder.mSavednote_contents.setText(NoteList.get(position).Savednote_contents);
        Log.e("이미지 값 확인",NoteList.get(position).Savednote_contents );
        if(NoteList.get(position).Savednote_image!=null && !NoteList.get(position).Savednote_image.isEmpty()){
            Log.e("이미지 값 확인",NoteList.get(position).Savednote_image );
            Uri uri = Uri.parse(NoteList.get(position).Savednote_image);
            holder.mSavednote_image.setImageURI(uri);

        }else{
            Log.e("이미지 값 확인", "이미지 값이 없다구" );
            holder.mSavednote_image.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return NoteList.size();
    }
    //어댑터 연결 끝
}
