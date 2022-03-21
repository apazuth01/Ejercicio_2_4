package com.example.activity_firma_digital.Clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity_firma_digital.R;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{
    Context context;
    List<com.example.activity_firma_digital.Clases.Firmas> listas;
    RecyclerView recicler;
    final View.OnClickListener onClickListener = new MyOnClickListener();
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView descripcion;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.txtdescripcion);
            image = itemView.findViewById(R.id.imgFirma);

        }
    }

    public Adaptador(Context context, List<com.example.activity_firma_digital.Clases.Firmas> listas, RecyclerView recicler){
        this.context = context;
        this.listas = listas;
        this.recicler = recicler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vistafirma, viewGroup, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        com.example.activity_firma_digital.Clases.Firmas asign = listas.get(i);
        Log.i("mensaje", "onBindViewHolder: "+asign.getFirmadigital());
        viewHolder.descripcion.setText(asign.getId()+" "+asign.getDescripcion());
        byte[] data = asign.getFirmadigital();
        if(data==null){
            Log.i("mensaje", "onBindViewHolder: nulo");
        }
        Bitmap bmp = convertCompressedByteArrayToBitmap(data);
        viewHolder.image.setImageBitmap(bmp);
    }
    public static Bitmap convertCompressedByteArrayToBitmap(byte[] src){
        return BitmapFactory.decodeByteArray(src, 0, src.length);
    }
    @Override
    public int getItemCount() {
        return listas.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = recicler.getChildLayoutPosition(v);
            String item = listas.get(itemPosition).getDescripcion();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
        }
    }
}