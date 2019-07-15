package com.sv.pruebalista.Recycler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sv.pruebalista.Database.DatabaseHelper;
import com.sv.pruebalista.MainActivity;
import com.sv.pruebalista.R;
import com.sv.pruebalista.entidad.Producto;

import java.util.List;

public class MyHolderAdapter extends RecyclerView.Adapter<MyHolderAdapter.MyViewHolder> {

    private Context context;
    private List<Producto> listProducto;
    private DatabaseHelper databaseHelper;
    private Color color;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtEstilo,txtPrecio,txtCantidad;
        Button btnEdit;
        public MyViewHolder(View view) {
            super(view);
            txtEstilo=itemView.findViewById(R.id.txtNombre);
            txtPrecio =itemView.findViewById(R.id.txtPrecio);
            txtCantidad =itemView.findViewById(R.id.txtprecio);
            btnEdit=itemView.findViewById(R.id.button2);
          //  txtTotal=itemView.findViewById(R.id.textView);
        }
    }

    public MyHolderAdapter(Context context, List<Producto> listProducto, DatabaseHelper dbhelper){
        this.context = context;
        this.listProducto = listProducto;
        this.databaseHelper = dbhelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_detalle, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Producto diary = listProducto.get(position);
        holder.txtEstilo.setText(diary.getEstilo());
        holder.txtPrecio.setText(String.valueOf(diary.getPrecio()));
        holder.txtCantidad.setText(String.valueOf(diary.getCantidad()));


        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
         else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffca33"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
        //por el momento no estoy editando esa shit
       /*     @Override
            public void onClick(View view) {
                deleteNote(position);
            }
        });*/
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                //updateNote("test",position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Desea quitar una unidad?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Producto producto=listProducto.get(position);
                                producto.setCantidad(producto.getCantidad()-1);
                                databaseHelper.updateData(producto);

                                notifyDataSetChanged();
                               // setData(listProducto);
                                if(context instanceof MainActivity){
                                    ((MainActivity) context).updateTotal(String.valueOf(total()));
                                }
                            }
                        });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void setData(List<Producto> listProducto) {
        this.listProducto = listProducto;
        notifyDataSetChanged();
    }

    public double total(){
        double total=0.0;
        for(Producto producto: listProducto){
            total+=producto.getCantidad()*producto.getPrecio();
        }
        return total;
    }

    @Override
    public int getItemCount() {
        return listProducto.size();
    }
}
