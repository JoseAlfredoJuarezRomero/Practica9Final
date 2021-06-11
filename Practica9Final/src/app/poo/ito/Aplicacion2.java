package app.poo.ito;
import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import Clases.ito.poo.Cuenta_Bancaria;
import Clases.ito.poo.CuentasBancareas;
import Clases.ito.poo.EscritorArchivoTXT;
import Clases.ito.poo.LectorArchivoTXT;
import Clases.ito.poo.LectorObjetos;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;
public class Aplicacion2 {


	static CuentasBancareas t;
	static Scanner a=new Scanner(System.in);
	static Cuenta_Bancaria pp;
	static LectorObjetos inputFile=null;
	
	static void menu() throws Exception {
		inicializa();
		iniciaPrograma();
		final JPanel panel=new JPanel();
		boolean error=true;
		while(error) {
		try {
		boolean ciclo=true;
		int respuesta=0;
		while(ciclo) {
		String opciones="Elige la accion que desees realizar:\n 1)Agregar cuenta\n 2)Imprimir cuentas existentes\n 3)Hacer depósito a una cuenta\n "
				+ "4)Hacer retiro a una cuenta\n 5)Dar de baja una cuenta\n 6)Hacer una consulta\n 7)Salir";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:agregarCuenta();break;
		case 2:mostrarCuentas();break;
		case 3:hacerDeposito();break;
		case 4:hacerRetiro();break;
		case 5:borrarCuenta();break;
		case 6:consulta();break;
		case 7:ciclo=false;error=false;break;
		default:System.out.println("Ingrese una de las acciones mencionadas de favor");
		}
		
		
	}
		}catch(HeadlessException e){
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		}
		}
	
	static Cuenta_Bancaria capturarCuenta() throws HeadlessException,FileNotFoundException {
		Cuenta_Bancaria cb=new Cuenta_Bancaria();
		System.out.print("Proporciona tu numero de cuenta:");
		try {
			cb.setNumCuenta(a.nextLong());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}a.nextLine();
		System.out.print("Proporciona tu nombre del cliente:");
		cb.setNomCliente(a.nextLine());
		System.out.print("Proporciona saldo de la cuenta:");
		cb.setSaldo(a.nextFloat());a.nextLine();
		System.out.print("Proporciona la fecha: aaaa-mm-dd");
		String fecha=a.nextLine();
		cb.setFechaApertura(LocalDate.parse(fecha));
		return cb;

	}
	
	static void inicializa() {
		t=new CuentasBancareas();
	}
	
	static void agregarCuenta() throws Exception {
		Cuenta_Bancaria nueva;
		nueva=capturarCuenta();
		t.exepcionCuentaExistente(nueva);
		t.addItem(nueva);
		System.out.println("Se ah añadido la cuenta satisfactoriamente!");
			if(t.isFull())
				t.crecerArreglo();
		
		
	}
	
	static void mostrarCuentas() {
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
		String cuentas="";
		for(int i=0;i<t.getSize();i++)
			cuentas=cuentas+"\n"+(t.getItem(i));
		System.out.println(cuentas);
		}
	}
	
	static void hacerDeposito() throws HeadlessException,FileNotFoundException {
		int pos=0;
		float cantidad=0;
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<t.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(t.getItem(i));
		    System.out.println("Mencione la cuenta a la cual desee hacer un deposito\n"+cuentas);
		    if((t.getSize())>=pos&&pos>0) {
		    	System.out.println("Cuanta cantidad desea depositar?");
		    t.exceptionDeposito(cantidad);
		    t.getItem(pos-1).setSaldoActualizado(t.getItem(pos-1).getSaldo()+cantidad);
		    t.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    System.out.println("¡Dinero depositado satisfactoriamente!");
		    bandera=false;
		    }
		    else
		    	System.out.println("Esa cuenta no es existente!");
			}
		}
	}
	
	static void hacerRetiro() throws HeadlessException,FileNotFoundException {
		int pos=0;
		float cantidad=0;
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<t.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(t.getItem(i));
		    System.out.println("Mencione la cuenta a la cual desee hacer un retiro\n"+cuentas);
		    if((t.getSize())>=pos&&pos>0) {
		    	System.out.println("Cuanta cantidad desea retirar?");
		    t.exceptionDeposito(cantidad);
		    if(!(t.getItem(pos-1).getSaldo()<cantidad)) {
		    t.getItem(pos-1).setSaldoActualizado(t.getItem(pos-1).getSaldo()-cantidad);
		    t.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    System.out.println("Dinero retirado satisfactoriamente!");
		    bandera=false;
		    }
		    else {
		    	System.out.println("La cantidad sobrepasa saldo de la cuenta!");
		    }
		    }
		    else
		    	System.out.println("Esa cuenta no es existente!");
			}
		}
	}
	
	static void borrarCuenta() throws HeadlessException,FileNotFoundException {
		int pos=0;
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<t.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(t.getItem(i));
		    System.out.println("Mencione la cuenta que desee dar de baja\n"+cuentas);
		    if((t.getSize())>=pos&&pos>0) {
		    	t.exepcionBorraCuenta(t.getItem(pos-1));
		    	t.clear(t.getItem(pos-1));
		    	System.out.println("¡Cuenta dada de baja satisfactoriamente!");
		    	bandera=false;
		    }	
		    else
		    	System.out.println("Esa cuenta no es existente!");
		  }
		}
	}
	
	static void consulta() {
		int respuesta=0;
		boolean ciclo=true;
		while(ciclo) {
		String opciones="Elige la accion de consulta que desees realizar:\n 1)Monto total de cuentas\n 2)Monto promedio de cuentas\n"
				+ " 3)Cuentas con saldo que sobrepasa de los $10,000\n "
				+ "4)Cuenta/s con saldo maximo\n 5)Cuenta/s con saldo mínimo\n 6)Salir";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:montoTotal();ciclo=false;break;
		case 2:montoPromedio();ciclo=false;break;
		case 3:mayor10mil();ciclo=false;break;
		case 4:saldoMax();ciclo=false;break;
		case 5:saldoMin();ciclo=false;break;
		case 6:ciclo=false;break;
		default:System.out.println("Ingrese una de las acciones mostradas de favor");
		  }
		}
	}
	
	static void montoTotal() {
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
		    float montoTotal=0;
		    for(int i=0;i<t.getSize();i++) 
			    montoTotal=montoTotal+t.getItem(i).getSaldo();
		    System.out.println("El monto totalitario es de: $"+montoTotal);
		}
	}
	
	static void montoPromedio() {
		float montoProm=0;
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
		    float montoTotal=0;
		    for(int i=0;i<t.getSize();i++) 
		        montoTotal=montoTotal+t.getItem(i).getSaldo();
		    montoProm=montoTotal/t.getSize(); 
		    System.out.println("El monto promedial es de: $"+montoProm);
		}
	}
	
	static void mayor10mil() {
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
			int vacio=0;
			Cuenta_Bancaria copia[]=new Cuenta_Bancaria[t.getSize()];
			for(int i=0;i<t.getSize();i++)
				if(t.getItem(i).getSaldo()>10000) 
					copia[i-vacio]=t.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(t.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			System.out.println("Las cuentas que tienen saldo que sobrepasa a $10,000 son:\n"+cuentas);
		}
	}
	
	static void saldoMax() {
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
			int vacio=0;
			float max=t.getItem(0).getSaldo();
			for(int i=0;i<t.getSize();i++)
				if(t.getItem(i).getSaldo()>max)
					max=t.getItem(i).getSaldo();
			Cuenta_Bancaria copia[]=new Cuenta_Bancaria[t.getSize()];
			for(int i=0;i<t.getSize();i++)
				if(t.getItem(i).getSaldo()==max) 
					copia[i-vacio]=t.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(t.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			System.out.println("las cuentas con maximo saldo es son:\n"+cuentas);
		}
		
	}
	
	static void saldoMin() {
		if(t.isFree())
			System.out.println("Todavía no existe alguna cuenta");
		else {
			int vacio=0;
			float min=t.getItem(0).getSaldo();
			for(int i=0;i<t.getSize();i++)
				if(t.getItem(i).getSaldo()<min)
					min=t.getItem(i).getSaldo();
			Cuenta_Bancaria copia[]=new Cuenta_Bancaria[t.getSize()];
			for(int i=0;i<t.getSize();i++)
				if(t.getItem(i).getSaldo()==min) 
					copia[i-vacio]=t.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(t.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			System.out.println("Las cuentas con minimo saldo es son:\n"+cuentas);
		}
	}
	
	static void grabarCuentas() {
		if(t.isFree()) {
			
		}
		else {
			try {
				LectorObjetos outputFile = new LectorObjetos("cuentas.dat");
			    for(int i=0;i<t.getSize();i++)
			    	outputFile.writeObject(t.getItem(i));
			    outputFile.close();
			}catch(Exception e) {
				
			}
		}
	}
	
	static void iniciaPrograma() throws Exception{
		boolean existe=false;
		try {
			inputFile = new LectorObjetos("cuentas.dat");    
		    existe=true;  
		}catch(IOException e) {
			System.err.println("No hay antecedentes de registro, se hara la realizacion en proceso de uno nuevo");
		}
		if(existe)
			grabaRegistro();
	}
	
	static void grabaRegistro() throws Exception {
		try {
		      inputFile = new LectorObjetos("cuentas.dat");
		      while(true) {
			      t.addItem((Cuenta_Bancaria)inputFile.readObject());
		      }
		}catch(IOException e) {
			
			try {
				inputFile.close();
				System.out.println("¡Registro localizado!");
			} catch (IOException e1) {
				
			}
		}
		catch(ClassNotFoundException e) {
			
		}
	}
	
}

