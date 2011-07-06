package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;

public class Compilador extends Figura {

	private int ancho;
        private String fuente, objeto, implementacion;
<<<<<<< HEAD
    
=======
        
>>>>>>> b4c6f3749d0e673a9abd3100402bb9024743ef94
	public Compilador(Point posicion, int ancho){
		this.posicion=posicion;
		this.ancho=ancho;
		this.seleccionada=false;  //Deberia estar en el constructor pero por simplicidad
                this.fuente="";
            this.objeto="";
            this.implementacion="";
	}
<<<<<<< HEAD
        public Compilador() {}
=======
	
>>>>>>> b4c6f3749d0e673a9abd3100402bb9024743ef94
        public void AtributosCompilador(String fuente,String objeto, String implementacion){
            this.fuente=fuente;
            this.objeto=objeto;
            this.implementacion=implementacion;
        }
	public void setAncho(int ancho){
		this.ancho=ancho;
	}
	public int getAncho(){
		return ancho;
	}
        public void setFuente(String fuente){
            this.fuente=fuente;
	}
        public void setObjeto(String objeto){
            this.objeto=objeto;
	}
        public void setImplementacion(String implementacion){
            this.implementacion=implementacion;
	}
	public String getFuente(){
            return fuente;
	}
	public String getObjeto(){
            return objeto;
	}
	public String getImplementacion(){
            return implementacion;
	}
	@Override
	public boolean dentroFigura(Point p) {
		int difX=Math.abs(p.x-(posicion.x+(ancho/2)));
		int difY=Math.abs(p.y-(posicion.y+(ancho/2)));
		return ( (difX<ancho/2) && (difY<ancho/2));   
	}
	@Override
	public void dibujar(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(this.getX(), this.getY(), this.getAncho()+100, this.getAncho());
                g.fillRect(this.getX()+40,this.getY()+40,this.getAncho()+20,this.getAncho());
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", 1, 12));
                g.drawString(fuente, this.getX()+2, this.getY() + 20);
                g.drawString(objeto, this.getX()+90, this.getY() + 20);
                g.drawString(implementacion, this.getX()+45, this.getY() + 70);
	}	
}
