package com.sv.pruebalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sv.pruebalista.Database.DatabaseHelper;
import com.sv.pruebalista.entidad.Producto;

public class AgregarActivity extends AppCompatActivity {

    TextView estilo;
    TextView codigo;
    TextView precio;
    TextView cantidad;
    Button button;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        estilo=findViewById(R.id.txtestilo);
        codigo=findViewById(R.id.txtcodigo);
        precio=findViewById(R.id.txtprecio);
        cantidad=findViewById(R.id.txtcantidad);
        db = new DatabaseHelper(this);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estilo=findViewById(R.id.txtestilo);
                codigo=findViewById(R.id.txtcodigo);
                precio=findViewById(R.id.txtprecio);
                cantidad=findViewById(R.id.txtcantidad);

                Producto producto=new Producto();
                producto.setEstilo(estilo.getText().toString());
                producto.setCodigo(Integer.parseInt(codigo.getText().toString()));
                producto.setPrecio(Double.parseDouble(precio.getText().toString()));
                producto.setCantidad(Integer.parseInt(cantidad.getText().toString()));
                long x=db.insertData(producto);
                Intent intent = new Intent(AgregarActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
