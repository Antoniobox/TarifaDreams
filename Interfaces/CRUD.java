package Interfaces;

import Exceptions.EmptyArrayListException;
import Exceptions.InvalidNumberOfFieldsException;

import java.io.IOException;

public interface CRUD {
    public void cargarBaseDeDatos() throws IOException, InvalidNumberOfFieldsException;
    public void guardarRegistros() throws IOException, EmptyArrayListException;

}
