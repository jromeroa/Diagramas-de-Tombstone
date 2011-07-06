/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
/**
 *
 * @author Jhonny
 */
public class Interprete extends Figura{
    private int ancho;
    private String lenguaje, maquina;
    
    public Interprete() {}
	public Interprete(Point posicion, int ancho){
		this.posicion=posicion;
		this.ancho=ancho;
		this.seleccionada=false;  
                this.lenguaje="";
                this.maquina="";
	}
        
	public void setAncho(int ancho){
		this.ancho=ancho;
	}
	public int getAncho(){
		return ancho;
	}
        public String getLenguaje(){
            return this.lenguaje;
        }
        public String getMaquina(){
            return this.maquina;
        }
        public void AtributosInterprete(String lenguaje,String maquina){
            
            this.lenguaje=lenguaje;
            this.maquina=maquina;
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
		g.setColor(Color.GREEN);
		g.fillRect(this.getX(), this.getY(), this.getAncho()+20, this.getAncho()+60);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", 1, 12));
                g.drawString(lenguaje, this.getX()+15, this.getY() + 35);
                g.drawString(maquina, this.getX()+15, this.getY() + 80);
	}
}
