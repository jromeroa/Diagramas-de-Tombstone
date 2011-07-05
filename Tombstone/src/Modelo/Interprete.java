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
public class Interprete extends Figura{
    private int ancho;
    private String lenguaje, maquina;
    
	public Interprete(Point posicion, int ancho){
		this.posicion=posicion;
		this.ancho=ancho;
		this.seleccionada=false;  //Deberia estar en el constructor pero por simplicidad
	}

    public Interprete() {}
	
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
	//Muy rudimentario y solo a modo demostrativo, para uso serio debe ser mejorada
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
                
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()-20);
		}
	}
}
