package Modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class Programa extends Figura {

	private int radio;
        private String lenguaje, programa;
        
        public Programa(){}
	public void setRadio(int ancho){
		this.radio=ancho;
        }
	public int getRadio(){
		return radio;
	}
        public String getPrograma(){
            return this.programa;
        }
        public String getLenguaje(){
            return this.lenguaje;
        }
	public Programa(Point posicion, int radio){
		this.posicion=posicion;
		this.radio=radio;
		this.seleccionada=false;
                this.programa="";
                this.lenguaje="";
        }
        public void AtributosPrograma(String programa,String lenguaje){
            this.programa=programa;
            this.lenguaje=lenguaje;
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
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", 1, 12));
                g.drawString(programa, this.getX()+15, this.getY() + 35);
                g.drawString(lenguaje, this.getX()+20, this.getY() + 80);
	}

}
