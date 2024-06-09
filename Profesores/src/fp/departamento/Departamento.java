package fp.departamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Departamento {
	private Set<Profesor> profesores;
	
	public Departamento() {
		//C1: no recibe parámetros. Crea un conjunto de profesores inicialmente vacío.
		profesores = Set.copyOf(profesores);
		this.profesores = new HashSet<Profesor>();
	}
	
	public Departamento (Stream<Profesor> strm) {
		//C2: recibe un parámetro de tipo Stream<Profesor> para inicializar el conjunto de profesores.
		profesores = Set.copyOf(profesores);
		this.profesores = strm.collect(Collectors.toSet());
	}
	 
	@Override
	public int hashCode() {
		return Objects.hash(profesores);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(profesores, other.profesores);
	}

	@Override
	public String toString() {
		return "Departamento [profesores=" + profesores + "]";
	}
	
	public void anadirProfesor(Profesor p) {
		this.profesores.add(p);
	}
	
	public void eliminaProfesor(String n) {
		//elimina el profesor con nombre n del conjunto profesores. No tiene efecto si éste no existe
		if (this.profesores.contains(n)) {
			this.profesores.remove(n);
		}
	}
	
	public Map<Asignatura,List<Profesor>> profesoresPorAsignatura(){
		//Relacionar cada asignatura con los profesores que la imparten. Resuélvalo sin usar streams
		Map <Asignatura, List<Profesor>> res = new HashMap<Asignatura, List<Profesor>>();
		for(Profesor p : this.profesores) {
			for(Asignatura a : p.totalAsignatura()) {
				if(!res.containsKey(a)) {
					List<Profesor> aux = new ArrayList<Profesor>();
					aux.add(p);
					res.put(a, aux);
				}
				else {
					List<Profesor> aux = res.get(a);
					aux.add(p);
					res.put(a, aux);
				}
			}
		}
		return res;
	}
	
	public Set<Profesor> profesoresQueSoloImpartenAsignaturasQueCoordinan(){
		//Determinar el conjunto de profesores que sólo imparten clases en las asignaturas que coordinan
		return null;
	}
	
	public Boolean departamentoResponsable() {
		/*
		Decidir si el departamento es responsable. Un departamento es
		responsable si todo profesor que coordina asignatura/s tiene una experiencia mínima de 5 años 
		e imparte tal/es asigntura/s en el curso actual (primer o segundo cuatrimestre)
		 */
		return null;
	}
	
	public List<String> ordenarProfesoresPorNumeroCreditosTeoricos(){
		//Ordenar de forma decreciente los nombres de los profesores que dan teoría por el número 
		//de créditos teóricos asignados en el curso actual
		return null;
	}
	
	public Integer anoIncorporacionMasCoordinadores() {
		//Obtener el año en el que se incorporaron más profesores que actualmente son coordinadores.
		//Si no se puede calcular se eleva NoSuchElementException
		return null;
	}
	
}
