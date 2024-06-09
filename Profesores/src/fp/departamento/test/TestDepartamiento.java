package fp.departamento.test;

import java.util.List;
import java.util.Map.Entry;

import fp.departamento.Asignatura;
import fp.departamento.Departamento;
import fp.departamento.FactoriaDepartamento;
import fp.departamento.Profesor;

public class TestDepartamiento {

	public static void main(String[] args) {
		Departamento dep = FactoriaDepartamento.leerDepartamento("/Profesores/data/profesores.csv");
		
		System.out.println(dep.profesoresPorAsignatura());
		
		testprofesoresPorAsignatura(dep);
		testprofesoresQueSoloImpartenAsignaturasQueCoordinan(dep);
		testdepartamentoResponsable(dep);
		testordenarProfesoresPorNumeroCreditosTeoricos(dep);
		testanoIncorporacionMasCoordinadores(dep);
	}
	
	private static void testprofesoresPorAsignatura(Departamento d) {
		try {
			System.out.println("\ntestprofesoresPorAsignatura");
			for(Entry <Asignatura,List<Profesor>> par : d.profesoresPorAsignatura().entrySet()) {
				System.out.println(par.getKey()+ " --> " + par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testprofesoresQueSoloImpartenAsignaturasQueCoordinan(Departamento d) {
		try {
			System.out.println("\ntestprofesoresQueSoloImpartenAsignaturasQueCoordinan");
			System.out.println(d.profesoresQueSoloImpartenAsignaturasQueCoordinan());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testdepartamentoResponsable(Departamento d) {
		try {
			System.out.println("\ntestdepartamentoResponsable");
			System.out.println(d.departamentoResponsable());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testordenarProfesoresPorNumeroCreditosTeoricos(Departamento d) {
		try {
			System.out.println("\ntestordenarProfesoresPorNumeroCreditosTeoricos");
			System.out.println(d.ordenarProfesoresPorNumeroCreditosTeoricos());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testanoIncorporacionMasCoordinadores(Departamento d) {
		try {
			System.out.println("\ntestanoIncorporacionMasCoordinadores");
			System.out.println(d.anoIncorporacionMasCoordinadores());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
