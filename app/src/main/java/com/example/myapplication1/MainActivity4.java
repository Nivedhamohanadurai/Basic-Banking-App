package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    DBHelper db;int i=0;
    DBHelper2 db2;
    String fromName, fromEmail, toName, toEmail;
    Float fromAmount, toAmount;
    TextView tv_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tv_status=findViewById(R.id.tv_status);
        int from=Integer.parseInt(getIntent().getStringExtra("from"));
        int to=Integer.parseInt(getIntent().getStringExtra("to"));
        float amount=Float.parseFloat(getIntent().getStringExtra("amount"));
        db=new DBHelper(this);
        db2=new DBHelper2(this);
        Cursor res=db.fetch();
        while (res.moveToNext()) {
            if(i==from){
                fromName=res.getString(0);
                fromEmail=res.getString(1);
                fromAmount=res.getFloat(2);
            }
            else if(i==to){
                toName=res.getString(0);
                toEmail=res.getString(1);
                toAmount=res.getFloat(2);
            }
            i++;
        }
        if(amount>fromAmount)
            tv_status.setText("Given transfer amount is greater than available amount");
        else{
            fromAmount-=amount;
            toAmount+=amount;
            Boolean check=db.update(fromName,fromEmail,fromAmount);
            Boolean check2=db.update(toName,toEmail,toAmount);
            Boolean check3=db2.insert(fromName,toName,amount);
            if(check==true && check2==true && check3==true) {
                Cursor res2 = db2.fetch();
                while (res2.moveToNext()) {
                    tv_status.setText("Transfer Successful!\n\nFrom:\nName: " + res2.getString(0) + "\nEmail: " + fromEmail + "\nBalance: " + fromAmount +
                                "\n\nTo:\nName: " + res2.getString(1) + "\nEmail: " + toEmail + "\nBalance: " + toAmount);
                }
            }
            else
                tv_status.setText("Transfer Failed");
        }
        Button btnviewcust = findViewById(R.id.btn_viewcust2);
        btnviewcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextact();
            }
        });
    }
    public void nextact(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}