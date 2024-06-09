package fp.departamento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FactoriaDepartamento {
	public static Departamento leerDepartamento(String rutaFichero) {
		//lee un fichero con los profesores del departamento y construye un objeto de tipo Departamento
		Departamento d = null;
		try {
			List<String> lineas = Files.readAllLines(Path.of(rutaFichero));
			d = new Departamento (lineas.stream().map(FactoriaDepartamento::parsearProfesor));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	private static Profesor parsearProfesor(String lineaCSV) {
		//crea un objeto de tipo Profesor a partir de una cadena de caracteres. La cadena de 
		//caracteres debe tener el mismo formato que las líneas del fichero CSV.
		if(lineaCSV == null) {
			throw new IllegalArgumentException("ruta es nulo");
		}
		String [] trozos = lineaCSV.split(",");
		if (trozos.length!=11) {
			throw new IllegalArgumentException("Formato incorrecto");
		}
		String nombre =  trozos[0].trim();
		LocalDate fechaIngreso =  LocalDate.parse(trozos[1].trim(),
				DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		Set<Asignatura> asignaturasCursosAnteriores =  parsearConjAsignaturas(trozos[2].trim());
		Set<Asignatura> asignaturasPrimerCuatri = parsearConjAsignaturas(trozos[3].trim());
		Set<Asignatura> asignaturasSegundoCuatri = parsearConjAsignaturas(trozos[4].trim());
		Double creditosTeoPrimerCuatri = Double.valueOf(trozos[5].trim());
		Double creditosTeoSegundoCuatri = Double.valueOf(trozos[6].trim());
		Double creditosLabPrimerCuatri = Double.valueOf(trozos[7].trim());
		Double creditosLabSegundoCuatri = Double.valueOf(trozos[8].trim());
		Double Capacidad = Double.valueOf(trozos[9].trim());
		Set<Asignatura> asignaturasCoordinadas = parsearConjAsignaturas(trozos[10].trim());
		return new Profesor(nombre, fechaIngreso, asignaturasCursosAnteriores, asignaturasPrimerCuatri,
				asignaturasSegundoCuatri, creditosTeoPrimerCuatri, creditosTeoSegundoCuatri,
				creditosLabPrimerCuatri, creditosLabSegundoCuatri, Capacidad, asignaturasCoordinadas);
	}
	
	private static Set<Asignatura> parsearConjAsignaturas(String campo){
		Set<Asignatura> res = new HashSet<Asignatura>();
		String [] trozos = campo.substring(1, campo.length()-1).split(";");
		for(String str : trozos) {
			Asignatura a = Asignatura.valueOf(str.trim());
			res.add(a);
		}
		return res;
	}
	
}
