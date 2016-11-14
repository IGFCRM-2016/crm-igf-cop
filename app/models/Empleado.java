package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.*;

import com.avaje.ebean.*;

@Entity
@Table(name="empleado")
public class Empleado extends Model{
	@Id
	public Long id;

	@Constraints.Required(message="Debe ingresar el codigo")
	public String codigo;

	@Constraints.Required(message="Debe ingresar el nombre")
	public String nombre;

	@Constraints.Required(message="Debe ingresar el correo")
	public String telefono;

	@Constraints.Required(message="Debe ingresar su correo electronico")
	@Constraints.Email
	public String email;

	@Constraints.Required(message="Debe ingresar un nick o username")
	public String username;

	@Constraints.Required(message="Debe ingresar la contraseña")
	public String password;

	@Transient
	public String password_confirmation;

	@ManyToOne
	public TipoEmpleado tipo;

	@OneToMany(mappedBy="empleado")
	public List<Incidencia> resueltas;
	
    public static Finder<Long, Empleado> find = new Finder<Long,Empleado>(Empleado.class);

    public Form<Empleado> getForm(){
    	Empleado e = Empleado.find.byId(this.id);
    	e.password_confirmation=e.password;
    	Form<Empleado> returning=Form.form(Empleado.class).fill(e);
    	return returning;
    }

    public List<ValidationError> validate(){
	   List<ValidationError> errors = new ArrayList<ValidationError>();
	   if(!password.equals(password_confirmation)){
	       errors.add(new ValidationError("password_confirmation", "Las contraseñas deben ser iguales"));
	    }
	    return errors.isEmpty() ? null : errors;
	}


}