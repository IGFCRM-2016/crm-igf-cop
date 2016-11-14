package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="incidencia")
public class Incidencia extends Model{
	@Id
	public Long id;

	@Constraints.Required(message="Debe ingresar la descripcion del problema")
	public String problema;

	//@Constraints.Required(message="Debe ingresar una breve descripcion")
	public String resolucion;

	@OneToOne
	public boolean cerrada;

	@ManyToOne
	public Empleado empleado;

	@ManyToOne
	public Cliente cliente;

    public static Finder<Long, Incidencia> find = new Finder<Long,Incidencia>(Incidencia.class);

}