package com.example.contact_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Integer.parseInt;

public class ThemMoi extends AppCompatActivity {
    EditText txtName,txtMaSo,ttsd;
    Button btnLuu;
    String name;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi);
        txtName =(EditText)findViewById(R.id.txtName_ThemMoi);
        ttsd =(EditText)findViewById(R.id.editTextTextPersonName2);
        txtMaSo =(EditText)findViewById(R.id.txtMaSo_ThemMoi);
        btnLuu=(Button)findViewById(R.id.btnLuu_ThemMoi);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name= txtName.getText().toString();
                Intent intent = getIntent();
                id = parseInt(txtMaSo.getText().toString());
                Bundle bundle = new Bundle();
                Persion ps = new Persion(id,name);
                bundle.putSerializable("Nguoi",ps);
                intent.putExtra("Data",bundle);
                setResult(MainActivity.LuuThem,intent);
                ttsd.setText(name);
               finish();
            }
        });
    }

}