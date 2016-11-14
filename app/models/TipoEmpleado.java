package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="tipo_empleado")
public class TipoEmpleado extends Model{
	@Id
	public Long id;

	@Constraints.Required(message="Debe ingresar el codigo")
	public int codigo;

	@Constraints.Required(message="Debe ingresar el nombre")
	public String nombre;

	@Constraints.Required(message="Debe ingresar una breve descripcion")
	public String descripcion;

    public static Finder<Long, TipoEmpleado> find = new Finder<Long,TipoEmpleado>(TipoEmpleado.class);


    public static Map<String,String> options(){
	  	LinkedHashMap<String,String> opciones = new LinkedHashMap<String,String>();
	  	//List<Categoria> categorias=Categoria.find.orderBy("nombre").findList();
	  	for(TipoEmpleado c : TipoEmpleado.find.orderBy("nombre").findList()){
	  		opciones.put(c.id.toString(), c.nombre);
	  	}
	  	return opciones;
  	}

}