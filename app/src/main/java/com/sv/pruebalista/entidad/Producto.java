package com.sv.pruebalista.entidad;

public class Producto {
    private static int database_version = 1;
    private static String database_name = "fire";
    private static String table_name = "ejemplo";
    private static String coloumnId = "codigo";
    private static String coloumnEstilo = "estilo";
    private static String coloumnCantidad = "cantidad";
    private static String coloumnPrecio = "precio";


    String estilo;
    int cantidad;
    double precio;
    int codigo;


    public Producto(){

    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Producto(String estilo, int cantidad, double precio, int codigo) {
        this.estilo = estilo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigo = codigo;
    }
}
