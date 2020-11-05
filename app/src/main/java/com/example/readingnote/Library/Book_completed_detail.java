package com.example.readingnote.Library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.readingnote.ImageLoadTask;
import com.example.readingnote.NoteAdapter;
import com.example.readingnote.R;
import com.example.readingnote.SecondNoteAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Book_completed_detail extends AppCompatActivity {
    String i, json, notes;
    int j;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView mCompleted_title, mCompleted_author, mCompleted_date1, mCompleted_date2, mCompleted_review, mCompleted_delete;
    ImageView mCompleted_image;
    RatingBar mBook_complete_ratingbar;
    RecyclerView mCompleted_Note_recyclerview;
    ArrayList<Note_item> notelist;
    SecondNoteAdapter noteAdapter;
    JSONArray jsonArray1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_completed_detail);
        sharedPreferences = getSharedPreferences("책", 0);
        editor = sharedPreferences.edit();
        notelist = new ArrayList<Note_item>();

        //뷰 연결 시작
        mCompleted_title = findViewById(R.id.Completed_title);
        mCompleted_author = findViewById(R.id.Completed_author);
        mCompleted_date1 = findViewById(R.id.Completed_date1);
        mCompleted_date2 = findViewById(R.id.Completed_date2);
        mCompleted_review = findViewById(R.id.Completed_review);
        mCompleted_image = findViewById(R.id.Completed_image);
        mBook_complete_ratingbar = findViewById(R.id.Book_complete_ratingbar);
        mCompleted_Note_recyclerview = findViewById(R.id.Completed_Note_recyclerview);
        mCompleted_delete = findViewById(R.id.Completed_delete);
        //뷰 연결 종료

        //포지션 받기 시작
        Intent intent = getIntent();
        i = intent.getExtras().getString("포지션");
        j = Integer.parseInt(i);
        //포지션 받기 종료

        //종료책목록 가져오기 시작
        json = sharedPreferences.getString("종료책목록",null);
        if (json != null) {
            try {
                jsonArray1 = new JSONArray(json);
                mCompleted_title.setText(jsonArray1.getJSONObject(j).getString("제목"));
                mCompleted_author.setText(jsonArray1.getJSONObject(j).getString("저자"));
                mCompleted_date1.setText(jsonArray1.getJSONObject(j).getString("시작날짜"));
                mCompleted_date2.setText(jsonArray1.getJSONObject(j).getString("종료날짜"));
                mCompleted_review.setText(jsonArray1.getJSONObject(j).getString("리뷰"));
                ImageLoadTask task = new ImageLoadTask(jsonArray1.getJSONObject(j).getString("이미지"),mCompleted_image);
                task.execute();
                mBook_complete_ratingbar.setIsIndicator(true);
                mBook_complete_ratingbar.setRating(Float.parseFloat(jsonArray1.getJSONObject(j).getString("별점")));
                notes = jsonArray1.getJSONObject(j).getString("노트목록");
                Log.e("노트목록", notes );

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //종료책목록 가져오기 종료

        //노트목록 가져오기 시작
        if(notes !=null){
            try {
                JSONArray jsonArray = new JSONArray(notes);
                for(int i=0; i<=jsonArray.length(); i++){
                    String a = jsonArray.getJSONObject(i).getString("글");
                    String b = jsonArray.getJSONObject(i).getString("이미지");
                    String c = jsonArray.getJSONObject(i).getString("날짜");
                    notelist.add(new Note_item(c,b,a));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // 노트목록 가져오기 종료

        // 노트 리사이클러뷰 시작
        noteAdapter = new SecondNoteAdapter(notelist);
        mCompleted_Note_recyclerview.setAdapter(noteAdapter);
        mCompleted_Note_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        // 노트 리사이클러뷰 종료

        //삭제 시작
        mCompleted_delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("");
                builder.setMessage("읽고 있는 책 목록에서 삭제하시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                jsonArray1.remove(j);
                                editor.putString("종료책목록",jsonArray1.toString());
                                editor.apply();
                                finish();
                                Intent intent = new Intent(getApplicationContext(), Complete_list.class);
                                startActivity(intent);

                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

            }
        });
        //삭제 종료
    }
}