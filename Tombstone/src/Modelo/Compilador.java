package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;

public class Compilador extends Figura {

	private int ancho;
        private String fuente, objeto, implementacion;
        
	public Compilador(Point posicion, int ancho){
		this.posicion=posicion;
		this.ancho=ancho;
		this.seleccionada=false;  //Deberia estar en el constructor pero por simplicidad
	}

    public Compilador() {}

	
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
	//Muy rudimentario y solo a modo demostrativo, para uso serio debe ser mejorada
	public boolean dentroFigura(Point p) {
		int difX=Math.abs(p.x-(posicion.x+(ancho/2)));
		int difY=Math.abs(p.y-(posicion.y+(ancho/2)));
		return ( (difX<ancho/2) && (difY<ancho/2));   
	}
	
	@Override
	public void dibujar(Graphics g)
	{
		g.setColor(Color.BLACK);
                
                g.setFont(new Font("Arial", 1, 12));
                g.drawString("fuente", this.getX(), this.getY() - 10);
                g.drawString("objeto", this.getX()+100, this.getY() - 10);
                g.drawString("implemet", this.getX()+40, this.getY() + 100);
                
		g.setColor(Color.BLUE);
		g.fillRect(this.getX(), this.getY(), this.getAncho()+100, this.getAncho());
                g.fillRect(this.getX()+40,this.getY()+40,this.getAncho()+20,this.getAncho());
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()-20);
		}
	}	
}
