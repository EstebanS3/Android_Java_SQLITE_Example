package com.example.almacenamientosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_codigo,et_descripcion,et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_codigo=findViewById(R.id.Codigo);
        et_descripcion=findViewById(R.id.Nombre);
        et_precio=findViewById(R.id.Precio);
    }

     public void insertar(View vista){
         AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"AdminSqLiteOpenHelper",null,1);
         SQLiteDatabase baseDatos = admin.getWritableDatabase();
         String codigo = et_codigo.getText().toString();
         String descripcion = et_descripcion.getText().toString();
         String precio = et_precio.getText().toString();
            if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
                ContentValues registro = new ContentValues();
                registro.put("codigo",codigo);
                registro.put("descripcion",descripcion);
                registro.put("precio",precio);
                baseDatos.insert("articulos",null,registro);
                baseDatos.close();
                et_codigo.setText("");
                et_descripcion.setText("");
                et_precio.setText("");
                Toast.makeText(this, "Insercion Correcta", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Debe llenar todos los datos", Toast.LENGTH_SHORT).show();
            }
    }

    public void consultar(View vista){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"AdminSqLiteOpenHelper",null,1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        if (!codigo.isEmpty() ){
            @SuppressLint("Recycle") Cursor fila = baseDatos.rawQuery("select descripcion, precio from articulos where codigo = "
                    + codigo,null);
            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "Datos no encontrados!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debe introducir el codigo para buscar", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();
    }

    public void eliminar(View vista){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"AdminSqLiteOpenHelper",null,1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        if (!codigo.isEmpty() ){
            int cantidad = baseDatos.delete("articulos","codigo= "+codigo,null);
            if (cantidad==1){
                Toast.makeText(this, "Datos borrados exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Datos no encontrados!", Toast.LENGTH_SHORT).show();
            }
            baseDatos.close();
            et_codigo.setText("");
            et_precio.setText("");
            et_descripcion.setText("");
        }else{
            Toast.makeText(this, "Debe indicar un codigo para eliminarlo", Toast.LENGTH_SHORT).show();
        }}

    public void modificar(View vista){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"AdminSqLiteOpenHelper",null,1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();
        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);
            int cantidad = baseDatos.update("articulos",registro,"codigo= "+codigo,null);
            baseDatos.close();
            if (cantidad==1){
                Toast.makeText(this, "Datos modificados exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Articulo no encontrado!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debe llenar todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
}