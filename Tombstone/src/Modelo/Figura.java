package Modelo;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Figura {
	protected Point posicion;
	protected boolean seleccionada;
	public abstract boolean dentroFigura(Point p);
	public abstract void dibujar(Graphics g);	
	private String programa="",lenguaje="",maquina="";
        public String fuente="",objeto="",implementacion="";
        
        
        public void AtributosCompilador(String fuente,String objeto, String implementacion){
            this.fuente=fuente;
            this.objeto=objeto;
            this.implementacion=implementacion;
        }
        public void AtributosPrograma(String programa,String lenguaje){
            this.programa=programa;
            this.lenguaje=lenguaje;
        }
        public void AtributoMaquina(String maquina){
            this.maquina=maquina;
        }
        public void AtributosInterprete(String lenguaje,String maquina){
            
            this.lenguaje=lenguaje;
            this.maquina=maquina;
        }
         public String getFuente(){
            return this.fuente;
        }
        public String getObjeto(){
            return this.objeto;
        }
        public String getImplementacion(){
            return this.implementacion;
        }
        public String getMaquina(){
            return this.maquina;
        }
        public String getPrograma(){
            return this.programa;
        }
        public String getLenguaje(){
            return this.lenguaje;
        }
        
	public void setPosicion(Point posicion)
	{
		this.posicion=posicion;
	}
	
	public int getX(){
		return posicion.x;
	}
	
	public int getY(){
		return posicion.y;
	}

	Point getPosicion(){
		return posicion;
	}
	
	public boolean getSeleccionada(){
		return seleccionada;
	}

	public void setSeleccionada(boolean sel){
		seleccionada=sel;
	}

}
