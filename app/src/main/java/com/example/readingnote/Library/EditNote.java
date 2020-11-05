package com.example.readingnote.Library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.readingnote.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditNote extends AppCompatActivity {
    private static final String TAG = "EditNote 활동주기";
    ImageView mENote_add_image, mENote_image, mENote_backbtn;
    Uri selectedImage;
    TextView mENote_save;
    EditText mENote_add_contents;
    String contents, imageuri;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<Library_save_list_item> savedbooklist;
    ArrayList<Postnote> notelist;
    SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
    Date time = new Date();
    String json, date, previoustext, previousimage;
    int checkimage = 0;
    String currentPhotoPath;
    Uri photoURI;
    static final int REQUEST_TAKE_PHOTO = 11;


    int noteposition, bookposition;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public EditNote(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EditNote(String contents, String imageuri, String date) {
        this.contents = contents;
        this.imageuri = imageuri;
        this.date = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        savedbooklist = new ArrayList<>();
        notelist = new ArrayList<>();
        sharedPreferences = getSharedPreferences("책", 0);
        editor = sharedPreferences.edit();
        json = sharedPreferences.getString("저장목록", null);
        Log.i(TAG, "onDestroy()");
        //뷰 연결 시작
        mENote_backbtn = findViewById(R.id.ENote_backbtn);
        mENote_add_contents = findViewById(R.id.ENote_add_contents);
        mENote_add_image = findViewById(R.id.ENote_add_image); //이미지 불러오기 버튼
        mENote_image = findViewById(R.id.ENote_image);
        mENote_save = findViewById(R.id.ENote_save);
        if(selectedImage == null){
            mENote_image.setVisibility(View.GONE);
        }

        //뷰 연결 끝


        //뒤로 가기 시작
        mENote_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //뒤로 가기 끝

        //인텐트 받기 from NoteAdapter(수정) 시작
        Intent intent = getIntent();
        previoustext = intent.getExtras().getString("글");
        previousimage = intent.getExtras().getString("이미지");
        if(!previoustext.isEmpty()) {
            mENote_add_contents.setText(previoustext);
        }
        if(!previousimage.isEmpty()) {
            Uri uri = Uri.parse(previousimage);
            mENote_image.setVisibility(View.VISIBLE);
            mENote_image.setImageURI(uri);
            checkimage = 1;
        }
        noteposition = intent.getExtras().getInt("노트포지션");
        bookposition = intent.getExtras().getInt("북포지션");
        Log.e("에딧노트 노트포지션", Integer.toString(noteposition));
        Log.e("에딧노트 북포지션",Integer.toString(bookposition));
        //인텐트 받기 from NoteAdapter(수정) 끝

        //사진 등록하기 시작
        mENote_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSelfPermission();
            }
        });
        //사진 등록하기 끝

        //사진 삭제하기 시작
        mENote_image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                deleteImage();
            }
        });
        //사진 삭제하기 끝

        // 저장하기 시작
        mENote_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v ){
                // 작성 글 String에 담기 시작
                contents = mENote_add_contents.getText().toString();


                // 작성 글 String에 담기 끝
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    String notes = jsonArray.getJSONObject(bookposition).getString("노트목록");
                    Log.e("에딧노트에서 노트목록 보기", notes );
                    JSONArray jsonArray1 = new JSONArray(notes);
                    jsonArray1.getJSONObject(noteposition).put("글",contents);
                    if(!previousimage.isEmpty()){ //기존 사진 있음
                        Log.e("checkimage",Integer.toString(checkimage));
                        if(checkimage==1 && imageuri!=null && photoURI!=null){ //동일
                            Log.e("checkimage","동일");
                        } else if(checkimage==0){//삭제
                            Log.e(TAG, previousimage );
                            Log.e("checkimage","삭제");
                            jsonArray1.getJSONObject(noteposition).put("이미지","");
                        } else if(previousimage!=imageuri && imageuri!=null ){ //새로운 이미지
                            jsonArray1.getJSONObject(noteposition).put("이미지",imageuri);
                            Log.e("checkimage","갤러리 이미지");
                        } else if(photoURI!=null){
                            jsonArray1.getJSONObject(noteposition).put("이미지",photoURI);
                            Log.e("checkimage","카메라 이미지");
                        }
                    } else { //기존 사진 없음
                        if(mENote_image.getVisibility()==View.GONE){
                            Log.e("checkimage","여전히 없음");
                        } else if(imageuri!=null){
                            Log.e("checkimage","생김");
                            jsonArray1.getJSONObject(noteposition).put("이미지",imageuri);
                        } else if(photoURI!=null){
                            jsonArray1.getJSONObject(noteposition).put("이미지",photoURI);
                        }
                    }
                    String notes2 = jsonArray1.toString();
                    Log.e("저장 후 노트목록", notes2 );
                    jsonArray.getJSONObject(bookposition).put("노트목록",notes2);
                    Log.e("저장 후 북목록",jsonArray.toString());
                    editor.putString("저장목록",jsonArray.toString());
                    editor.apply();
                    finish();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("저장 버튼","클릭");
                finish();
            }
        });
        // 저장하기 끝



    }

    // 사진 권한 체크 시작
    public void checkSelfPermission() {
        String temp = "";
        //파일 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
        }  //파일 쓰기 권한 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            temp += Manifest.permission.CAMERA + " ";
//        }
        if (TextUtils.isEmpty(temp) == false) {
            // 권한 요청
            ActivityCompat.requestPermissions(this, temp.trim().split(" "),1);
        }
        else {
            // 모두 허용 상태
            Toast.makeText(this, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
            showDialog();}
    }

// 사진 권한 체크 끝
// 사진 불러오기 다이얼로그 시작

    private void showDialog() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("갤러리에서 가져오기");
        ListItems.add("카메라로 촬영하기");
        ListItems.add("취소");
        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setTitle("");
        alt_bld.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item==0){
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto,1);
                }else if (item==1){
                    mENote_image.setVisibility(View.VISIBLE);
                    dispatchTakePictureIntent();

                }else if(item==2){
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO: // 카메라
                if (resultCode == RESULT_OK) {
                    mENote_image.setImageURI(photoURI);
                    mENote_image.setVisibility(View.VISIBLE);
                    imageuri = null;
                    //                 Bundle extras = imageReturnedIntent.getExtras();
                    //                  Bitmap imageBitmap = (Bitmap) extras.get("data");
                    //                  mNote_image.setImageBitmap(imageBitmap);
                }

                break;
            case 1: // 갤러리
                if (resultCode == RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    mENote_image.setImageURI(selectedImage);
                    mENote_image.setVisibility(View.VISIBLE);
                    imageuri = selectedImage.toString();
                    photoURI = null;
                    Log.e("이미지 uri", selectedImage.toString() );
                }
                break;
        }
    }


    // 사진 불러오기 다이얼로그 끝
// 권한이 있을 때 작동하는 함수 시작
    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //권한을 허용 했을 경우
        if(requestCode == 1){
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 동의
                    showDialog();
                    Log.d("MainActivity","권한 허용 : " + permissions[i]);
                }
            }
        }
    }
    // 권한이 있을 때 작동하는 함수 끝
    //사진 삭제 메소드 시작
    private void deleteImage(){
        new AlertDialog.Builder(EditNote.this).
                setTitle("").
                setMessage("사진을 삭제할까요?").
                setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mENote_image.setImageDrawable(null);
                        checkimage = 0;
                        mENote_image.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"이미지가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }}).create().show();
    }
    //사진 삭제 메소드 끝
    private File createImageFile() throws IOException {
        //Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+ timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg",storageDir);

        //Save a file : path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Ensure that there's a camera activity to handle the intent
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            //Create the file where the photo should go
            File photoFile = null;
            try{
                photoFile = createImageFile();
            }catch (Exception e){

            }

            if(photoFile != null){
                photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    //액티비티 생명주기 시작
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
        String jsonpause =  sharedPreferences.getString("저장목록",null);
        Log.e(TAG, jsonpause );
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop()");
        String jsonstop =  sharedPreferences.getString("저장목록",null);
        Log.e(TAG, jsonstop );
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
        String jsonresume =  sharedPreferences.getString("저장목록",null);
        Log.e(TAG, jsonresume );
    }

    @Override
    public void onStart(){
        super.onStart();

        Log.i(TAG, "onStart()");

        String jsonstart =  sharedPreferences.getString("저장목록",null);
        Log.e(TAG, jsonstart );
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart()");
        String jsonrestart =  sharedPreferences.getString("저장목록",null);
        Log.e(TAG, jsonrestart );
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        String jsondestroy =  sharedPreferences.getString("저장목록",null);
        Log.e(TAG, jsondestroy );
    }
    // 액티비티 생명주기 끝
}