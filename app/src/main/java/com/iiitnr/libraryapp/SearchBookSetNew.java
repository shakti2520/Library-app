package com.iiitnr.libraryapp;
import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class SearchBookSetNew extends AppCompatActivity{

    private TextInputLayout editTitle3;
    private TextInputLayout editAuth, editPub;
    private Spinner spinner3;
    private Button button3;
    private String type;
    private CheckBox checkBox;
    AutoCompleteTextView txtAuth, txtTitle;
    AutoCompleteTextView txtPub;
//    String[] languages={"Android ","java","IOS","SQL","JDBC","Web services"};

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }

    private boolean verifyTitle()
    {
        String t=editTitle3.getEditText().getText().toString().trim();
        if(t.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean verifyAuth()
    {
        String b=editAuth.getEditText().getText().toString().trim();
        if(b.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean verifyPub()
    {
        String b=editPub.getEditText().getText().toString().trim();
        if(b.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book_set_new);
        FirebaseApp.initializeApp(this);
        editTitle3=(TextInputLayout)findViewById(R.id.editTitle3);
        editAuth=(TextInputLayout) findViewById(R.id.editAuth);
        editPub=(TextInputLayout) findViewById(R.id.editPub);
        button3=(Button)findViewById(R.id.button3);
        checkBox=(CheckBox)findViewById(R.id.onlyAvailable);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("Books");
        txtTitle=(AutoCompleteTextView)findViewById(R.id.txtTitle);
        txtAuth=(AutoCompleteTextView)findViewById(R.id.txtAuth);
        txtPub=(AutoCompleteTextView)findViewById(R.id.txtPub);
        List<String> titleArray = new ArrayList<>();
        List<String> authArray = new ArrayList<>();
        List<String> pubArray = new ArrayList<>();

        CollectionReference booksRef = db.collection("Books");
        booksRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                for (QueryDocumentSnapshot document : task.getResult()) {
                    String title = document.getString("Title");
                    titleArray.add(title);
                    String author = document.getString("Author");
                    authArray.add(author);
                    String publisher = document.getString("Publisher");
                    pubArray.add(publisher);
                }
                // do something with the list of authors\
                ArrayAdapter adaptertitle = new
                        ArrayAdapter(this,android.R.layout.simple_list_item_1,titleArray );
                txtTitle.setAdapter(adaptertitle);
                txtTitle.setThreshold(1);
                ArrayAdapter adapterauth = new
                        ArrayAdapter(this,android.R.layout.simple_list_item_1,authArray );
                txtAuth.setAdapter(adapterauth);
                txtAuth.setThreshold(1);
                ArrayAdapter adapterpub = new
                        ArrayAdapter(this,android.R.layout.simple_list_item_1,pubArray );
                txtPub.setAdapter(adapterpub);
                txtPub.setThreshold(1);
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
//
//        ArrayAdapter adaptertitle = new
//                ArrayAdapter(this,android.R.layout.simple_list_item_1,titleArray );
//        txtTitle.setAdapter(adaptertitle);
//        txtTitle.setThreshold(1);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(verifyAuth()|verifyTitle()|verifyPub()))
                {
                    Toast.makeText(SearchBookSetNew.this, "Select at least parameter !", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(getApplicationContext(),SearchBook.class);

                if(verifyTitle()&&checkBox.isChecked())
                {

                    intent.putExtra("id",1);
                    intent.putExtra("btitle",editTitle3.getEditText().getText().toString().trim());
                    startActivity(intent);
                }
                else if(verifyTitle()&&!checkBox.isChecked())
                {

                    intent.putExtra("id",2);
                    intent.putExtra("btitle",editTitle3.getEditText().getText().toString().trim());
                    startActivity(intent);

                }
                else if(verifyAuth()&&checkBox.isChecked())
                {

                    intent.putExtra("id",3);
                    intent.putExtra("bauth",editAuth.getEditText().getText().toString().trim());
                    startActivity(intent);

                }
                else if(verifyAuth()&&!checkBox.isChecked())
                {

                    intent.putExtra("id",4);
                    intent.putExtra("bauth",editAuth.getEditText().getText().toString().trim());
                    startActivity(intent);

                }
                else if(verifyPub()&&checkBox.isChecked())
                {

                    intent.putExtra("id",5);
                    intent.putExtra("bpub",editPub.getEditText().getText().toString().trim());
                    startActivity(intent);

                }
                else if(verifyPub()&&!checkBox.isChecked())
                {

                    intent.putExtra("id",6);
                    intent.putExtra("bpub",editPub.getEditText().getText().toString().trim());
                    startActivity(intent);

                }
                else if(verifyTitle()&&verifyAuth()&&checkBox.isChecked())
                {

                    intent.putExtra("id",7);
                    intent.putExtra("btitle",editTitle3.getEditText().getText().toString().trim());
                    intent.putExtra("bauth",editAuth.getEditText().getText().toString().trim());
                    intent.putExtra("btype",type);
                    startActivity(intent);

                }
                else if(verifyTitle()&&verifyAuth()&&!checkBox.isChecked())
                {

                    intent.putExtra("id",8);
                    intent.putExtra("btitle",editTitle3.getEditText().getText().toString().trim());
                    intent.putExtra("bauth",editAuth.getEditText().getText().toString().trim());
                    intent.putExtra("btype",type);
                    startActivity(intent);

                }
                else if(verifyTitle()&&verifyPub()&&checkBox.isChecked())
                {

                    intent.putExtra("id",9);
                    intent.putExtra("btitle",editTitle3.getEditText().getText().toString().trim());
                    intent.putExtra("bpub",editPub.getEditText().getText().toString().trim());
                    intent.putExtra("btype",type);
                    startActivity(intent);

                }
                else if(verifyTitle()&&verifyAuth()&&!checkBox.isChecked())
                {

                    intent.putExtra("id",10);
                    intent.putExtra("btitle",editTitle3.getEditText().getText().toString().trim());
                    intent.putExtra("bpub",editPub.getEditText().getText().toString().trim());
                    intent.putExtra("btype",type);
                    startActivity(intent);

                }
                else if(verifyAuth()&&verifyPub()&&checkBox.isChecked())
                {

                    intent.putExtra("id",11);
                    intent.putExtra("bauth",editAuth.getEditText().getText().toString().trim());
                    intent.putExtra("bpub",editPub.getEditText().getText().toString().trim());
                    intent.putExtra("btype",type);
                    startActivity(intent);

                }
                else if(verifyTitle()&&verifyAuth()&&!checkBox.isChecked())
                {

                    intent.putExtra("id",12);
                    intent.putExtra("bauth",editAuth.getEditText().getText().toString().trim());
                    intent.putExtra("bpub",editPub.getEditText().getText().toString().trim());
                    intent.putExtra("btype",type);
                    startActivity(intent);

                }

            }
        });
    }


}
