package Modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    private List<Figura> listaFiguras;
    private List<List<Figura>> listaAux;
    private String nombre_modelo;

    public Modelo() {
        listaFiguras = new ArrayList<Figura>();
        listaAux = new ArrayList<List<Figura>>();
    }

    public List<Figura> getListado() {
        return listaFiguras;
    }

    public List<List<Figura>> getListadoAux() {
        return listaAux;
    }
    
    public List<Figura> getListadoAux(int i) {
        return listaAux.get(i);
    }
    
    public int nuevoListadoAux() {
        listaAux.add(new ArrayList<Figura>());
        return listaAux.size()-1;
    }

    public void anyadirFiguraAux(Figura f, int lista) {
        listaAux.get(lista).add(f);
    }

    public void anyadirFigura(Figura f) {
        listaFiguras.add(f);
    }

    public void ElimminarFiguraAux(Figura f, int lista) {
        listaAux.get(lista).remove(f);
    }

    public void ElimminarFigura(Figura f) {
        listaFiguras.remove(f);
    }

    public void EliminarLista() {
        listaFiguras.clear();
    }

    public boolean EstaEnLista(Figura f) {
        boolean esta=false;
        if (listaFiguras.contains(f)) {
            esta = true;
        }
        return esta;
    }

    public int EstaEnListaAux(Figura f) {
        int nro_lista=-1;
        boolean esta=false;
        for (List<Figura> elemento : getListadoAux()) {
            nro_lista++;
            if (elemento.contains(f)) {
                esta=true;
                break;
            }
        }
        if(!esta)
            nro_lista=-1;
        return nro_lista;
    }

    public Figura getFiguraEn(Point p) {
        for (Figura elemento : getListado()) {
            if (elemento.dentroFigura(p)) {
                elemento.seleccionada = true;
                return elemento;
            }
        }
        return null;
    }

    public void setNombre(String nombre_modelo) {
        this.nombre_modelo = nombre_modelo;
    }

    public String getNombre() {
        return nombre_modelo;
    }
}
