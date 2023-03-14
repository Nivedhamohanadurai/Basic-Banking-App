package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    String customers[]=new String[10]; int i=0;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db=new DBHelper(this);
        Cursor res1=db.fetch();
        if(res1.moveToNext()==false) {
            Boolean check1 = db.insert("AAA", "aaa@gmail.com", 10000);
            Boolean check2 = db.insert("BBB", "bbb@gmail.com", 20000);
            Boolean check3 = db.insert("CCC", "ccc@gmail.com", 70000);
            Boolean check4 = db.insert("DDD", "ddd@gmail.com", 30000);
            Boolean check5 = db.insert("EEE", "eee@gmail.com", 40000);
            Boolean check6 = db.insert("FFF", "fff@gmail.com", 80000);
            Boolean check7 = db.insert("GGG", "ggg@gmail.com", 60000);
            Boolean check8 = db.insert("HHH", "hhh@gmail.com", 5000);
            Boolean check9 = db.insert("III", "iii@gmail.com", 23000);
            Boolean check10 = db.insert("JJJ", "jjj@gmail.com", 100000);
        }
        Cursor res=db.fetch();
        while (res.moveToNext()) {
            customers[i] = res.getString(0);
            i++;
        }
        System.out.println(i);
        listView=(ListView) findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.item_view,R.id.itemTextView,customers);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity2.this, "Clicked on "+customers[position], Toast.LENGTH_SHORT).show();
                senddata(position);
            }
        });
    }
    public void senddata(int position){
        Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
        String str=String.valueOf(position);
        intent.putExtra("msg",str);
        startActivity(intent);
    }
}