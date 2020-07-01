package com.example.databaseimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtID,edtFname,edtLname;
    Button btnInsert,btnDelete,btnUpdate,btnView,btnSearch;
    SqLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtID = (EditText) findViewById(R.id.edtid);
        edtFname = (EditText) findViewById(R.id.edtfname);
        edtLname = (EditText) findViewById(R.id.edtlname);

        btnInsert = (Button) findViewById(R.id.btninsert);
        btnDelete = (Button) findViewById(R.id.btndelete);
        btnUpdate = (Button) findViewById(R.id.btnupdate);
        btnView = (Button) findViewById(R.id.btnview);
        btnSearch = (Button) findViewById(R.id.btnsearch);

        db = new SqLiteDatabase(this);

        btnInsert.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String firstname,lastname,id;
        Boolean fine;
        firstname = edtFname.getText().toString();
        ;lastname = edtLname.getText().toString();
        id = edtID.getText().toString();
        switch(v.getId()){
            case R.id.btninsert:
                fine=true;
                try{
                    db.insertStudent(id,firstname,lastname);
                }catch(Exception e){
                    fine = false;
                    String error = e.toString();
                    Dialog d =new Dialog(this);
                    d.setTitle("Sorry");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }finally {
                    if(fine){
                        Dialog d=new Dialog(this);
                        d.setTitle("Successful");
                        TextView tv = new TextView(this);
                        tv.setText("Record is inserted successfully");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.btnview:
                Intent it = new Intent(MainActivity.this,SQLView.class);
                startActivity(it);
                break;
            case R.id.btnupdate:
                fine=true;
                try{
                    db.UpdateStudent(id,firstname,lastname);
                }catch(Exception e){
                    fine = false;
                    String error = e.toString();
                    Dialog d =new Dialog(this);
                    d.setTitle("Sorry");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }finally {
                    if(fine){
                        Dialog d=new Dialog(this);
                        d.setTitle("Successful");
                        TextView tv = new TextView(this);
                        tv.setText("Record is updated successfully");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.btndelete:
                fine=true;
                try{
                    db.DeleteStudent(id);
                }catch(Exception e){
                    fine = false;
                    String error = e.toString();
                    Dialog d =new Dialog(this);
                    d.setTitle("Sorry");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }finally {
                    if(fine){
                        Dialog d=new Dialog(this);
                        d.setTitle("Successful");
                        TextView tv = new TextView(this);
                        tv.setText("Record is deleted successfully");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.btnsearch:
                fine=false;
                try{
                    String fname = db.getFirstName(id);
                    String lname = db.getLastName(id);
                    if(fname !=null){
                        edtFname.setText(fname);
                        edtLname.setText(lname);
                        fine = true;
                    }

                }catch(Exception e){
                    Dialog d = new Dialog(this);
                    d.setTitle("Sorry");
                    String error = e.toString();
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }finally{
                    Dialog d = new Dialog(this);
                    TextView tv = new TextView(this);
                    if(fine){
                        d.setTitle("Successful");
                        tv.setText("Recrod found");
                    }
                    else{
                        d.setTitle("Sorry");
                        tv.setText("Record Not found.");
                    }
                    d.setContentView(tv);
                    d.show();
                }
                break;
        }
    }
}
