package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.*;

import com.avaje.ebean.*;

@Entity
@Table(name="incidencia")
public class Incidencia extends Model{
	@Id
	public Long id;

	public String codigo;//no se esta usando

	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date fecha=new Date();

	@Constraints.Required(message="Debe ingresar un titulo para la incidencia")
	public String titulo;

	@Column(length=1500)
	@Constraints.Required(message="Debe ingresar la descripcion del problema")
	public String problema;

	//@Constraints.Required(message="Debe ingresar una breve descripcion")
	@Column(length=1000)
	public String resolucion;

	@OneToOne
	public Integer estado=1;//1:abierta,2:resolviendo,3:cerrada,

	@ManyToOne
	public Empleado empleado;

	@ManyToOne
	public Cliente cliente;

	@ManyToOne
	public Compra compra;


	@OneToMany(mappedBy="incidencia",cascade=CascadeType.ALL)
  	public List<MensajeIncidencia> mensajes;


    public static Finder<Long, Incidencia> find = new Finder<Long,Incidencia>(Incidencia.class);


    public Form<Incidencia> getForm(){
    	Incidencia e = Incidencia.find.byId(this.id);
    	Form<Incidencia> returning=Form.form(Incidencia.class).fill(e);
    	return returning;
    }
}