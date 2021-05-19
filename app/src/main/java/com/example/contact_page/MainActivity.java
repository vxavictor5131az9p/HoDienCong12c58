package com.example.contact_page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Person;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int ThemMoi=10;
    public static final int Sua=20;
    public static final int LuuThem=30;
    public static final int LuuSua=40;
    EditText txtLayten;
    ListView lv;
    int posselected=-1;
    ArrayList<Persion> list = new ArrayList<Persion>();
    ArrayAdapter<Persion> adapter=null;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contact_menu,menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.them_menu:
                doStartNew();
                break;
            case R.id.sua_menu:
                doStartEdit();
                break;
            case R.id.xoa_menu:
                doDelete();
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void  doDelete(){
        AlertDialog.Builder dl = new AlertDialog.Builder(MainActivity.this);
        dl.setTitle("Bạn có chắc muons xóa ?");
        dl.setMessage("Chắc chắn");
        dl.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                list.remove(posselected);
                adapter.notifyDataSetChanged();
            }
        });
        dl.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dia, int i) {
                dia.dismiss();
            }
        });
        dl.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case MainActivity.ThemMoi :
            {
                if(resultCode == MainActivity.LuuThem){
                    Bundle bundle = data.getBundleExtra("Data");
                    Persion ps = (Persion)bundle.getSerializable("Nguoi");
                    txtLayten.setText(ps.getName());
                    list.add(ps);
                    adapter.notifyDataSetChanged();
                }
                break;
            }
            case MainActivity.Sua:
            {
                if(resultCode==MainActivity.LuuSua){
                    Bundle bundle = data.getBundleExtra("Data");
                    Persion ps = (Persion) bundle.getSerializable("Nguoi");
                    list.set(posselected,ps);
                    adapter.notifyDataSetChanged();
                }

            }break;


        }
    }

    public void doStartEdit(){
        Intent intent= new Intent(MainActivity.this, SuaData.class);
        Persion ps =  list.get(posselected);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Nguoi",ps);
        intent.putExtra("Data",bundle);
       // txtLayten.setText(ps.getName());
        startActivityForResult(intent,MainActivity.Sua);
    }
    public void doStartNew(){
        Intent intent= new Intent(this, ThemMoi.class);
        startActivityForResult(intent,MainActivity.ThemMoi);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(new Persion(1,"Tèo"));
        list.add(new Persion(2,"Tý"));
        list.add(new Persion(3,"Tôm"));
        txtLayten=(EditText)findViewById(R.id.txtName_MainChinh);
        lv=(ListView)findViewById(R.id.lv);
        adapter = new ArrayAdapter<Persion>(this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                posselected=i;
                return false;
            }
        });
        registerForContextMenu(lv);

    }
}