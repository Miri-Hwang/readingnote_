package com.example.readingnote.Library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.readingnote.Complete_Adapter;
import com.example.readingnote.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Complete_list extends AppCompatActivity {
    RecyclerView mComplete_recyclerview;
    RecyclerView.LayoutManager mLayoutManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_list);
        sharedPreferences = getSharedPreferences("책", 0);
        editor = sharedPreferences.edit();

        mComplete_recyclerview = findViewById(R.id.complete_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mComplete_recyclerview.setLayoutManager(mLayoutManager);

        ArrayList<Completed_Book_item> completelist = new ArrayList<Completed_Book_item>();
        //리스트에 저장된 것 불러오기 시작
        json = sharedPreferences.getString("종료책목록", null);
        if(json!=null){
            try{
                JSONArray jsonArray = new JSONArray(json);
                for(int i=0; i<jsonArray.length(); i++){
                    String title = jsonArray.getJSONObject(i).getString("제목");
                    String image= jsonArray.getJSONObject(i).getString("이미지");
                    String author = jsonArray.getJSONObject(i).getString("저자");
                    String publisher = jsonArray.getJSONObject(i).getString("출판사");
                    String startdate = jsonArray.getJSONObject(i).getString("시작날짜");
                    String notelist = jsonArray.getJSONObject(i).getString("노트목록");
                    String rate = jsonArray.getJSONObject(i).getString("별점");
                    String review = jsonArray.getJSONObject(i).getString("리뷰");
                    String enddate = jsonArray.getJSONObject(i).getString("종료날짜");
                    completelist.add(new Completed_Book_item(title, image, author, publisher, startdate, notelist, rate, review, enddate));

                }
            }catch (Exception e){

            }


        }

        // 불러오기 끝

        Complete_Adapter completeadapter = new Complete_Adapter(completelist);
        mComplete_recyclerview.setAdapter(completeadapter);

    }
}