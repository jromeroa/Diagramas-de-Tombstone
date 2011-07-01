package Controlador;

import java.awt.Point;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

import Vista.*;
import Modelo.Programa;
import Modelo.Compilador;
import Modelo.Figura;
import Modelo.Modelo;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	private Figura seleccionada;
        public boolean compilador,programa,maquina,interprete;
	
	public Controlador(Modelo modelo, Vista vista){
		this.modelo=modelo;
		this.vista=vista;
		seleccionada=null;
                compilador=false;
                programa=false;
                maquina=false;
                interprete=false;
	}
	public Figura obtenerFigura(Point posicion){
		ListIterator<Figura> it=modelo.getListado().listIterator();
	    while (it.hasNext()) {
	    	Figura tmp=it.next();
	    		if(tmp.dentroFigura(posicion)){
	    			tmp.setSeleccionada(true);
	    			return tmp;
	    		}
		    }
	    return null;
	}

	public void cambiarPosicion(Figura f, Point p){
		f.setPosicion(p);
	}
	
	public Vista getVista(){
		return vista;
	}
	
	public void anyadirFigura(Figura f){
		modelo.anyadirFigura(f);
	}
	
	public Figura getFiguraEn(Point p){
		return modelo.getFiguraEn(p);
	}
	
	public void eVmousePressed(MouseEvent ev) {
		if(SwingUtilities.isLeftMouseButton(ev)){ 			//Click boton izquierdo selecciona figura
			seleccionada=this.getFiguraEn(ev.getPoint());
                      
		}				
		else if(SwingUtilities.isRightMouseButton(ev) && compilador)//click boton medio a�ade figura tipo circulo
		{
			agaCompilador(ev);
		}
                else if(SwingUtilities.isRightMouseButton(ev) && programa)//click boton medio a�ade figura tipo circulo
		{
			agaPrograma(ev);
		}
                else if(SwingUtilities.isRightMouseButton(ev) && maquina)//click boton medio a�ade figura tipo circulo
		{
			agaMaquina(ev);
		}
                else if(SwingUtilities.isRightMouseButton(ev) && interprete)//click boton medio a�ade figura tipo circulo
		{
			agaInterprete(ev);
		}
		vista.repaint();		
	}
        public void agaCompilador(MouseEvent ev){
            Point po=new Point(100, 100);
            this.anyadirFigura(new Compilador(ev.getPoint(),40));
        }
        public void agaPrograma(MouseEvent ev){
            
        }
        public void agaMaquina(MouseEvent ev){
            
        }
        public void agaInterprete(MouseEvent ev){
            
        }
	public void eVmouseDragged(MouseEvent ev) {
		if(seleccionada!=null){
			//El movimiento puede ser mas fluido recalculando el pto
			this.cambiarPosicion(seleccionada, ev.getPoint());
			vista.repaint();
		}
	}

	public void eVmouseReleased (MouseEvent ev) {
		vista.repaint();
		if(seleccionada!=null){
			seleccionada.setSeleccionada(false);
			seleccionada=null;
		}
	}

}
