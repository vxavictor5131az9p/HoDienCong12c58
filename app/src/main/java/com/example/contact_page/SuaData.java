package com.example.contact_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SuaData extends AppCompatActivity {
    EditText txtName,txtMaSo,ttsd;
    Button btnLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_data);
        txtName=(EditText)findViewById(R.id.txtname_SuaData);
        txtMaSo=(EditText)findViewById(R.id.txtMaSo_SuaData);
        btnLuu=(Button)findViewById(R.id.btnLuu_SuaData);

        final Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("Data");
       final Persion ps =(Persion)bundle.getSerializable("Nguoi");
        txtMaSo.setText(ps.getId()+"");
        txtName.setText(ps.getName());
        txtMaSo.setEnabled(false);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ps.setName(txtName.getText().toString());
                setResult(MainActivity.LuuSua,intent);
                finish();
            }
        });

    }
}