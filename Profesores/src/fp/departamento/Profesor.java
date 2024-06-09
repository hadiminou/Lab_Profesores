package fp.departamento;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record Profesor(String nombre, 
		LocalDate fechaIngreso, 
		Set<Asignatura> asignaturasCursosAnteriores, 
		Set<Asignatura> asignaturasPrimerCuatri,
		Set<Asignatura> asignaturasSegundoCuatri,
		Double creditosTeoPrimerCuatri,
		Double creditosTeoSegundoCuatri,
		Double creditosLabPrimerCuatri,
		Double creditosLabSegundoCuatri,
		Double Capacidad,
		Set<Asignatura> asignaturasCoordinadas) implements Comparable<Profesor>{
	
	public Profesor{
		if (this.fechaIngreso().isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("fecha de ingreso debe ser anterior a la fecha actual");
		}
		
		if(this.creditosTeoPrimerCuatri()+this.creditosTeoSegundoCuatri()+
				this.creditosLabPrimerCuatri() +this.creditosLabSegundoCuatri() < 0) {
			throw new IllegalArgumentException("la suma de créditos de docencia asignados debe ser"
					+ " mayor o igual que 0");
		}
			
		if ( this.Capacidad() < 0) {
			throw new IllegalArgumentException("la capacidad docente debe ser mayor o igual que 0");
		}
	}
	
	public Integer experiencia() {
		//numero de anos de experiencia a partir de fecha ingreso
		Integer res = LocalDate.now().getYear() - this.fechaIngreso().getYear();
		return res;
	}
	
	public Boolean ocioso() {
		//un profesor se considera ocioso si y sólo si sus créditos asignados en el curso actual 
		//no superan la mitad de su capacidad docente
		Boolean res = false;
		if (this.creditosAsignados() < this.Capacidad()/2) {
			res = true;
		}
		return res;
	}
	
	public Set<Asignatura> asignaturasImpartidas(){
		//todas las asignaturas impartidas por el profesor en el curso actual (considerando los
		//dos cuatrimestres)
		Set<Asignatura> res = new HashSet<Asignatura>();
		res.addAll(this.asignaturasPrimerCuatri());
		res.addAll(this.asignaturasSegundoCuatri());
	return res;	
	}
	
	public Set<Asignatura> totalAsignatura(){
		Set<Asignatura> aux = new HashSet<Asignatura>();
		aux.addAll(this.asignaturasImpartidas());
		aux.addAll(this.asignaturasCursosAnteriores());
		return aux;
	}
	
	public Boolean coordinador() {
		//propiedad cierta si y sólo si el profesor es coordinador de alguna asignatura
		Boolean res = false;
		for(Asignatura a : this.totalAsignatura()) {
			if(this.asignaturasCoordinadas().contains(a)) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Double creditosTeo() {
		//número total de créditos teóricos asignados al profesor (considerando los dos cuatrimestres)
		Double res = null;
		res = this.creditosTeoPrimerCuatri()+this.creditosTeoSegundoCuatri();
		return res;
	}
	
	public Double creditosAsignados() {
		//número total de créditos asignados al profesor (considerando tanto teoría como
		//laboratorio en los dos cuatrimestres)
		Double res = null;
		res = this.creditosTeo() + this.creditosLabPrimerCuatri() + this.creditosLabSegundoCuatri();
		return res;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public int compareTo(Profesor p) {
		//los profesores se ordenan lexicográficamente por su nombre.
		int res = this.nombre().compareTo(p.nombre());
		return res;
	}

	@Override
	public String toString() {
		return "Profesor [nombre=" + nombre + ", fechaIngreso=" + fechaIngreso +
				", asignaturasCursosAnteriores=" + asignaturasCursosAnteriores + 
				", asignaturasPrimerCuatri=" + asignaturasPrimerCuatri + 
				", asignaturasSegundoCuatri=" + asignaturasSegundoCuatri +
				", creditosTeoPrimerCuatri=" + creditosTeoPrimerCuatri + 
				", creditosTeoSegundoCuatri=" + creditosTeoSegundoCuatri + 
				", creditosLabPrimerCuatri=" + creditosLabPrimerCuatri + 
				", creditosLabSegundoCuatri=" + creditosLabSegundoCuatri + 
				", Capacidad=" + Capacidad + ", asignaturasCoordinadas=" + asignaturasCoordinadas + "]";
	}
}
