package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    String customers[]=new String[10]; int i=0;
    DBHelper db;
    ListView listView;
    EditText txtamount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        String str=getIntent().getStringExtra("msg");
        int position=Integer.parseInt(str);
        TextView name=(TextView) findViewById(R.id.tv_name);
        txtamount=findViewById(R.id.txt_amount);
        db=new DBHelper(this);
        Cursor res=db.fetch();
        while (res.moveToNext()) {
            if(i==position){
                name.setText("Name: "+res.getString(0)+", Email: "+res.getString(1)+", Current Balance: "+String.valueOf(res.getFloat(2)));
            }
            customers[i] = res.getString(0);
            i++;
        }
        listView=(ListView) findViewById(R.id.lv_transferto);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.item_view,R.id.itemTextView,customers);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position2, long l) {
                senddata(position,position2);
            }
        });
    }
    public void senddata(int position,int position2){
        Float amount=Float.parseFloat(txtamount.getText().toString());
        Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
        intent.putExtra("from",String.valueOf(position));
        intent.putExtra("to",String.valueOf(position2));
        intent.putExtra("amount",String.valueOf(amount));
        startActivity(intent);
    }
}