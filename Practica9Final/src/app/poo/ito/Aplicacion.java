package app.poo.ito;
import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import Clases.ito.poo.Cuenta_Bancaria;
import Clases.ito.poo.CuentasBancareas;
import Clases.ito.poo.EscritorArchivoTXT;
import Clases.ito.poo.LectorArchivoTXT;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;
public class Aplicacion {


	static CuentasBancareas t;
	static Scanner a=new Scanner(System.in);
	static Cuenta_Bancaria pp;
	static EscritorArchivoTXT archivo;
	static Clases.ito.poo.LectorArchivoTXT archivo2;
	
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
		String opciones="Elige la accion que desees realize:\n 1)Agregar cuenta\n 2)Imprimir cuentas existentes\n 3)Hacer depósito a una cuenta\n "
				+ "4)Hacer retiro a una cuenta\n 5)Dar de baja una cuenta\n 6)Hacer una consulta\n 7)Salir";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:agregarCuenta();break;
		case 2:mostrarCuentas();break;
		case 3:hacerDeposito();break;
		case 4:hacerRetiro();break;
		case 5:borrarCuenta();break;
		case 6:consulta();break;
		case 7:grabaRegistro();ciclo=false;error=false;break;
		default:System.out.println("Ingrese una de las acciones mostradas de favor");
		
		}
			
	}
		}catch(HeadlessException e){
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		}
		}
		
		
	static Cuenta_Bancaria capturarCuenta() throws Exception{
		Cuenta_Bancaria cb=new Cuenta_Bancaria();
		System.out.print("Proporciona tu numero de cuenta:");
		cb.setNumCuenta(a.nextLong());a.nextLine();
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
	
	static void agregarCuenta() throws Exception{
		Cuenta_Bancaria nueva;
		nueva=capturarCuenta();
		t.addItem(nueva);
		System.out.println("Se ah añadido la cuenta correctamente");
			if(t.isFull())
				t.crecerArreglo();
		
		
	}
	
	static void mostrarCuentas() {
		if(!t.isFree())
			System.out.println("Mostrar listas");
		
		for(int i=0;i<t.getSize();i++)
			
		System.out.println(t.getItem(i));
		}
	
	
	static void hacerDeposito() throws HeadlessException, FileNotFoundException  {
		int pos=0;
		float cantidad=0;
		if(t.isFree())
			System.out.println("Aun no existe alguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<t.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(t.getItem(i));
		    System.out.println("Mencione la cuenta al cual desee hacer un deposito\n"+cuentas);
		    if((t.getSize())>=pos&&pos>0) {
		    	System.out.println("Cuanta cantidad desea depositar?");
		    t.HeadlessException (cantidad);
		    t.getItem(pos-1).setSaldoActualizado(t.getItem(pos-1).getSaldo()+cantidad);
		    t.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    System.out.println("Dinero depositado Satisfactoriamente!");
		    bandera=false;
		    }
		    else
		    	System.out.println("La cuenta no es existente");
			}
		}
	}
	
	static void hacerRetiro() throws HeadlessException,FileNotFoundException  {
		int pos=0;
		float cantidad=0;
		if(t.isFree())
			System.out.println("Aun no existe alguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<t.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(t.getItem(i));
		    System.out.println("Mencione la cuenta al cual desee hacer un retiro\n"+cuentas);
		    if((t.getSize())>=pos&&pos>0) {
		    	System.out.println("Cuanta cantidad desea retirar?");
		    t.HeadlessException (cantidad);
		    if(!(t.getItem(pos-1).getSaldo()<cantidad)) {
		     t.getItem(pos-1).setSaldoActualizado(t.getItem(pos-1).getSaldo()-cantidad);
		    t.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    System.out.println("Dinero retirado Satisfactoriamente!");
		    bandera=false;
		    }
		    else {
		    	System.out.println("La cantidad sobrepasa el saldo de la cuenta!");
		    }
		    }
		    else
		    	System.out.println("Esa cuenta no es existente");
			}
		}
	}
	
	static void borrarCuenta() throws HeadlessException, FileNotFoundException  {
		int pos=0;
		if(t.isFree())
			System.out.println("Aun no existe alguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<t.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(t.getItem(i));
		    System.out.println("Mencione la cuenta que desee dar de baja\n"+cuentas);
		    if((t.getSize())>=pos&&pos>0) {
		    	t.HeadlessException (t.getItem(pos-1));
		    	t.clear(t.getItem(pos-1));
		    	System.out.println("La cuenta se ha dado de baja satisfactoriamente!");
		    	bandera=false;
		    }	
		    else
		    	System.out.println("La cuenta no es existente");
		  }
		}
	}
	
	static void consulta() {
		int respuesta=0;
		boolean ciclo=true;
		while(ciclo) {
		String opciones="Elige la accion de consulta que desees realizar:\n 1)Monto total de cuentas\n 2)Monto promedio de cuentas\n"
				+ " 3)Cuentas que existen con saldo superior de los $10,000\n "
				+ "4)Cuenta/s con un saldo máximo\n 5)Cuenta/s con un saldo mínimo\n 6)Salir";
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
			System.out.println("Aun no existe alguna cuenta");
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
			System.out.println("Aun no existe alguna cuenta");
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
			System.out.println("Aun no existe alguna cuenta");
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
			System.out.println("Las cuentas que tienen saldo que sobrepasan a $10,000 son:\n"+cuentas);
		}
	}
	
	static void saldoMax() {
		if(t.isFree())
			System.out.println("Aun no existe alguna cuenta");
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
			System.out.println("las cuentas con maximo saldo son:\n"+cuentas);
		}
		
	}
	
	static void saldoMin() {
		if(t.isFree())
			System.out.println("Aun no existe alguna cuenta");
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
			System.out.println("las cuentas con minimo saldo es son:\n"+cuentas);
		}
	}
	
	
	
	
	static void grabaRegistro() throws FileNotFoundException {
		if (t.isFree()) {
			
		}
		else {
			archivo=new EscritorArchivoTXT("cuentas.txt");
			for(int i=0;i<t.getSize();i++) {
				archivo.writeLong(t.getItem(i).getNumCuenta());
				archivo.writeString(t.getItem(i).getNomCliente());
				archivo.writeFloat(t.getItem(i).getSaldo());
				if(t.getItem(i).getFechaActualizacion()==null) {
					archivo.writeString(t.getItem(i).getFechaApertura().toString());
					archivo.writeStringLn("null");	
				}
				else {
					archivo.writeString(t.getItem(i).getFechaApertura().toString());
					archivo.writeStringLn(t.getItem(i).getFechaActualizacion().toString());	
				}
				
			}
			archivo.close();
		}
					
	}
	
	static void iniciaPrograma() throws Exception  {
		boolean existe=false;
		try {
			archivo2= new LectorArchivoTXT("cuentas.txt");
			existe=true;
		}catch(FileNotFoundException e) {
			System.err.println("Bienvinido al banco azteca");
		}
		if(existe)
			leerTXT();
		
	}
	
	static void leerTXT() throws Exception  {
		
		while(!archivo2.isEOF()) {
			pp=new Cuenta_Bancaria();
			pp.setNumCuenta(archivo2.readLong());
			pp.setNomCliente(archivo2.readString());
			pp.setSaldoActualizado(archivo2.readFloat());
			pp.setFechaApertura(LocalDate.parse(archivo2.readString()));
			String fechaAct;
			fechaAct=archivo2.readString();
			if(fechaAct.equals("null")) 
				pp.setFechaActualizacion(null);
			else
				pp.setFechaActualizacion(LocalDate.parse(fechaAct));
			t.addItem(pp);
			archivo2.isFinLinea();
		
			
		}
	}


}
