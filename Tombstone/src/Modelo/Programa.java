package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Programa extends Figura {

	private int radio;
	
	public void setRadio(int ancho){
		this.radio=ancho;
	}
	
	public int getRadio(){
		return radio;
	}
	
	public Programa(Point posicion, int radio){
		this.posicion=posicion;
		this.radio=radio;
		this.seleccionada=false;  //Deberia estar en el constructor de figura pero por simplicidad
	}
	
	@Override
	public boolean dentroFigura(Point p) {
		if ( radio >= Math.sqrt( Math.pow( p.x - posicion.x, 2 ) + Math.pow(p.y - posicion.y, 2 )))		
				return true;
		else
				return false;
	}

	@Override
	public void dibujar(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval(this.getX(), this.getY(), this.getRadio()+25, this.getRadio()+25);
                g.fillRect(this.getX()+11,this.getY()+50, this.getRadio()+5, this.getRadio()+5);
		if(this.getSeleccionada()){
			g.setColor(Color.CYAN);
			g.drawOval(this.getX()+7, this.getY()+7, this.getRadio()-20, this.getRadio()-20);
		}
	}

}