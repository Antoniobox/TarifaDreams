package Interfaces;

import Exceptions.EmptyArrayListException;
import Exceptions.InvalidNumberOfFieldsException;

import java.io.IOException;

public interface CRUD {
    public void cargarBaseDeDatos();
    public void guardarRegistros();

}
