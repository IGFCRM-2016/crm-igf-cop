package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="cliente")
public class Cliente extends Model{
	@Id
	public Long id;

	public String codigo;

	@Constraints.Required(message="Debe ingresar el nombre")
	public String nombre;

	@Constraints.Required(message="Debe seleccionar su genero")
	public int genero;

	public String telefono;

	@Constraints.Required(message="Debe ingresar su correo electronico")
	@Constraints.Email
	public String email;

	@Constraints.Required(message="Debe ingresar un nick o username")
	public String username;

	@Constraints.Required(message="Debe ingresar el password")
	public String password;

	@OneToOne(cascade=CascadeType.ALL)
	public Tarjeta tarjeta;

	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
	public List<Gusto> gustos;

	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
	public List<Compra> compras;

	@OneToMany(mappedBy="empleado",cascade=CascadeType.ALL)
	public List<Incidencia> incidencias;

	@OneToMany(mappedBy="cliente",cascade=CascadeType.ALL)
	public List<MensajeIncidencia> mensajes_incidencia;

	
    public static Finder<Long, Cliente> find = new Finder<Long,Cliente>(Cliente.class);

}