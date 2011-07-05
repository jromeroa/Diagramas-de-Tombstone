/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
/**
 *
 * @author Jhonny
 */
public class Maquina extends Figura{
   
    private int ancho;
    private String lenguaje;
    
    public Maquina(Point posicion, int ancho){
            this.posicion=posicion;
            this.ancho=ancho;
            this.seleccionada=false;  //Deberia estar en el constructor pero por simplicidad
    }

    public Maquina() {}
	
	public void setAncho(int ancho){
		this.ancho=ancho;
	}
	public int getAncho(){
		return ancho;
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
		g.setColor(Color.BLUE);
		int[] x={this.getX(),this.getX(),this.getX()+30,this.getX()+60,this.getX()+60};
                int[] y={this.getY(),this.getY()+40,this.getY()+70,this.getY()+40,this.getY()};
                g.setColor(Color.YELLOW);
                g.fillPolygon(x, y, x.length);
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()-20);
		}
	}
}
