package Modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
	
	private List<Figura> listaFiguras;
        private boolean esta;
	public Modelo(){
		listaFiguras = new ArrayList<Figura>();
                esta=false;
	}
	public List<Figura> getListado(){
		return listaFiguras;
	}
	public void anyadirFigura(Figura f){
		listaFiguras.add(f);
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
}
