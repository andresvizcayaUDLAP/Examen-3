package Examen;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class Lector {
	private int id;
	private int inicio;
	private File archivo = new File("emails.csv");	
	private FileReader lectorArchivo;
	private BufferedReader lectorBufer;
	private String[] nombreColumnas = new String[3000];
	public int[][] matriz = new int[50][3000];
	
	public void ejecutar(int id) {
		this.id = id;
		String[] idArreglo = (""+id).split("");
		String reverseId = "";
		for(int i=(""+id).split("").length-1; i>=0; i--) {
			reverseId += idArreglo[i];
			
		}
		inicio = Integer.parseInt(reverseId.substring(0, 3));
		leer();
		crearArchivo();
	}
	
	private void leer() {
		try {
			lectorArchivo = new FileReader(archivo);
			lectorBufer = new BufferedReader(lectorArchivo);
			guardar();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void guardar() {
		try {
			String[] arregloColumnas = lectorBufer.readLine().split(",");
			for(int n=0; n<matriz[0].length; n++) {
				nombreColumnas[n] = arregloColumnas[n+1];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] cadena;
		int count = 0;
		for(int i=0; i<5172; i++) {
			if(i >= inicio && i<inicio+50) {
				try {
					cadena = lectorBufer.readLine().split(",");
					for(int j=1; j<cadena.length-1;j++) {
						matriz[count][j-1] = Integer.parseInt(cadena[j]);
					}
					count++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(i==inicio+50) {
				break;
			}
		}
	}
	
	private void crearArchivo() {
		try {
			PrintWriter escritor = new PrintWriter(id + ".txt");
			for(int i=0;i<matriz[0].length; i++) {
				int sum = 0;
						for(int j=0; j<matriz.length; j++) {
							sum += matriz[j][i];				
						}
						escritor.println(nombreColumnas[i] + ", " + sum);
			}
			escritor.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
