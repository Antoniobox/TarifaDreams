package Interfaces;

import Exceptions.EmptyArrayListException;

import java.io.IOException;

public interface CRUD {
    public void cargarBaseDeDatos() throws IOException;
    public void guardarRegistros() throws IOException, EmptyArrayListException;
}
