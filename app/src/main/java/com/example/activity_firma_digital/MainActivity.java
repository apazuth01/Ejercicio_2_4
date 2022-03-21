package com.example.activity_firma_digital;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity_firma_digital.Clases.CaptureImageView;
import com.example.activity_firma_digital.Clases.ConexionSQLite;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private CaptureImageView mSig;
    private EditText editText;
    private Button button,btnclear;
    ConexionSQLite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.B_guardar);
        btnclear=findViewById(R.id.B_limpiar);

        editText=findViewById(R.id.txtdescripcion);
        LinearLayout mContent = (LinearLayout) findViewById(R.id.Firma_View);
        mSig = new CaptureImageView(this, null);
        mContent.addView(mSig, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        dbHelper = new ConexionSQLite(this);

        button.setOnClickListener(view -> {
            Bitmap img=  mSig.getBitmap();

            if(validarCampos()){
                boolean estate=dbHelper.insertSQL(editText.getText().toString(),
                        setFrom(img));
                if(estate){
                    Toast.makeText(this, "La Firma "+editText.getText().toString()+" ha sido agregada Correctamente", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }
        });
        btnclear.setOnClickListener(view -> {
            limpiar();
        });
    }
    public void limpiar(){
        mSig.ClearCanvas();
        editText.setText("");
    }
    public boolean validarCampos(){
        boolean estado=true;
        if(editText.getText().toString().isEmpty() ){
            Toast.makeText(this, "Ingrese la Descricion de ", Toast.LENGTH_SHORT).show();
            estado=false;
            return estado;
        }

        if(!mSig.isStatus()){
            Toast.makeText(this, "Debe llenar la descripcion de la firma", Toast.LENGTH_SHORT).show();
            estado=false;
            return estado;
        }
        return estado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_superior, menu);
        return true;
    }
    public static byte[] setFrom(Bitmap imagen){
        if(imagen!=null){

            ByteArrayOutputStream stream =new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG,70,stream);
            return stream.toByteArray();
        }
        return null;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId()==R.id.lista_item){
            Intent intent=new Intent(this,List_Firmas.class);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}