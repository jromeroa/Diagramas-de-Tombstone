package Modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
	
	private List<Figura> listaFiguras;
        private List<Figura> listaAux;
        private boolean esta, estaAux;
        private String nombre_modelo;
        
        
	public Modelo(){
		listaFiguras = new ArrayList<Figura>();
                listaAux = new ArrayList<Figura>();
                esta=false;
	}
	public List<Figura> getListado(){
		return listaFiguras;
	}
        public List<Figura> getListadoAux(){
		return listaAux;
	}
        public void anyadirFiguraAux(Figura f){
		listaAux.add(f);
	}
	public void anyadirFigura(Figura f){
		listaFiguras.add(f);
	}
        public void ElimminarFiguraAux(Figura f){
            listaAux.remove(f);
        }
	public void ElimminarFigura(Figura f){
            listaFiguras.remove(f);
        }
        public void EliminarLista(){
            listaFiguras.clear();
        }
        public boolean EstaEnLista(Figura f){
            
            if(listaFiguras.contains(f)){
                esta=true;
            }else esta=false;
            return esta;
        }public boolean EstaEnListaAux(Figura f){
            
            if(listaAux.contains(f)){
                estaAux=true;
            }else estaAux=false;
            return estaAux;
        }
	public Figura getFiguraEn(Point p){
		for (Figura elemento : getListado()) {
			if(elemento.dentroFigura(p)){
				elemento.seleccionada=true;
				return elemento;				
			}
		}
		return null;
	}
        
        public void setNombre(String nombre_modelo){
            this.nombre_modelo=nombre_modelo;
        }
        
        public String getNombre(){
            return nombre_modelo;
        }
}
