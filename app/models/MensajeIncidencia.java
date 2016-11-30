package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="mensaje_incidencia")
public class MensajeIncidencia extends Model{
	@Id
	public Long id;

	@ManyToOne
	public Incidencia incidencia;

	@ManyToOne
	public Cliente cliente;

	@ManyToOne
	public Empleado empleado;

	public boolean leido=false;

	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date fecha;

	@Column(length=1000)	
	@Constraints.Required(message="No ha ingresado ningun mensaje")
	public String texto;

    public static Finder<Long, MensajeIncidencia> find = new Finder<Long,MensajeIncidencia>(MensajeIncidencia.class);

}