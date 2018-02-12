package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import modelo.modelo;
import conexion.ConexionBBDD;

public class ControladorLibros extends HttpServlet{
	
	ConexionBBDD conex;
	/*
	 * TODO Decidir tipo de variable que contendrán los registros (filas) 
	 */
	Object [][] tabla;
	int enlaces;
	String html;
	PrintWriter pw;
	modelo mod;
	
	public ControladorLibros(){
		conex = new ConexionBBDD();
		mod = new modelo(conex.getConexion());
		System.out.println("constructor");
	}
	
	
	public void doPOST(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("doPOST");
		try {
			
			System.out.println("pw");			
			
			pw = resp.getWriter();
			crearHTML(req.getAttribute("pagina").toString());
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("doGET");
		try {
			
			pw = resp.getWriter();
			
			System.out.println(req.getQueryString());
			String pagina=req.getParameter("pagina");
			
		
			if(pagina!=null )
			{
				
				System.out.println("Envio número página");
				crearHTML(req.getParameter("pagina"));
			}
			else
			{
				crearHTML();
			}
			
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void crearHTML( String nPagina){
		System.out.println("html");
		/*TODO Nos devuelve un numero de registros segun en 
		 * la pagina donde estemos actualmente;  tabla = modelo.getTitulosAutor();
		 */
		System.out.println("cambio de pagina");
		tabla = mod.getTituloPorAutor(Integer.parseInt(nPagina));
		enlaces = mod.getNumeroRegistros();
		
		
		pw.print("<html><body><table><tr><th>Autor</th><th>Titulo</th></tr>");
		
		//Recorremos los resultados de libros de autores
		for(int i = 0; i < tabla.length; i++){
			pw.print("<tr><td>"+tabla[i][0]+"</td><td>+"+tabla[i][1]+"</td></tr>");
		}
		pw.print("<h1>" + nPagina + "</h1>");
		pw.print("</table><div>");
		
		for(int i=0,  e=1;i<enlaces;i+=2, e++)
		{
			pw.print("<a href='servlet?pagina="+e+"'> Página "+e+"</a>");
		}
				pw.print( "</div></body></html>");
		
		pw.close();

	}
	
	public void crearHTML(){
		crearHTML("1");
	}

}
