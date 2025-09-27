package org.compuwork.models;

import java.util.UUID;

public class Usuario {
    private final String idUsuario; // único para todos los usuarios

    private String nombre;
    private Rol rol;

    public Usuario(String nombre, Rol rol) {
        this.idUsuario = UUID.randomUUID().toString(); // se genera siempre
        this.nombre = nombre;
        this.rol = rol;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public String getNombre() { return nombre; }
    public Rol getRol() { return rol; }
}
