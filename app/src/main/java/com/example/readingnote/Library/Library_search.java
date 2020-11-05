package com.example.readingnote.Library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.readingnote.R;

public class Library_search extends AppCompatActivity {
    private static final String TAG = "Library_search 생명주기";
    ImageView mSearch_backbtn, mSearch_btn;
    EditText mPut_Text;
    String keyword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_search);
        sharedPreferences = getSharedPreferences("책", 0);

    //책 검색하기

    mSearch_btn = findViewById(R.id.Search_btn);
    mSearch_btn.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            mPut_Text = findViewById(R.id.Put_Text);
            keyword = mPut_Text.getText().toString();
            Intent intent = new Intent(getApplicationContext(), Library_search_list.class);
            intent.putExtra("keyword", keyword);
            Log.e("입력한 키워드", keyword );
            startActivity(intent);

        }
    });

    //책 검색하기 끝


    //뒤로가기 버튼 시작
    mSearch_backbtn = findViewById(R.id.Search_backbtn);
    mSearch_backbtn.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent(getApplicationContext(),Library.class);
            startActivity(intent);
            finish();
        }
    });
    //뒤로가기 버튼 끝


    }

    //액티비티 생명주기 시작
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
        String jsonpause =  sharedPreferences.getString("저장목록",null);
        if(jsonpause!=null)
        Log.e(TAG, jsonpause );
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop()");
        String jsonstop =  sharedPreferences.getString("저장목록",null);
        if(jsonstop!=null)
        Log.e(TAG, jsonstop );
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
        String jsonresume =  sharedPreferences.getString("저장목록",null);
        if(jsonresume!=null)
        Log.e(TAG, jsonresume );
    }

    @Override
    public void onStart(){
        super.onStart();

        Log.i(TAG, "onStart()");
        String jsonstart =  sharedPreferences.getString("저장목록",null);
        if(jsonstart!=null)
        Log.e(TAG, jsonstart );
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart()");
        String jsonrestart =  sharedPreferences.getString("저장목록",null);
        if(jsonrestart!=null)
        Log.e(TAG, jsonrestart );
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        String jsondestroy =  sharedPreferences.getString("저장목록",null);
        if(jsondestroy!=null)
        Log.e(TAG, jsondestroy );
    }
    // 액티비티 생명주기 끝
}