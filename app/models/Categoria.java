package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="categoria")
public class Categoria extends Model{
	@Id
	public Long id;

	@Constraints.Required(message="Debe ingresar un identificador numerico para la categoria")
	public int codigo;

	@Constraints.Required(message="Debe ingresar el nombre para la categoria")
	public String nombre;

	@Constraints.Required(message="Debe ingresar una descripcion del alcance de esta categoria")
	public String descripcion;

	//productos que pertenecen a esta categoria
	@OneToMany(mappedBy="categoria")
	List<Producto> productos;

	//ofertas que coinciden (en cuanto a publicacion) con esta categoria
	@OneToMany(mappedBy="categoria")
	List<AplicacionOferta> aplicaciones_oferta;

  public static Finder<Long, Categoria> find = new Finder<Long,Categoria>(Categoria.class);


  public static Map<String,String> options(){
  	LinkedHashMap<String,String> opciones = new LinkedHashMap<String,String>();
  	//List<Categoria> categorias=Categoria.find.orderBy("nombre").findList();
  	for(Categoria c : Categoria.find.orderBy("nombre").findList()){
  		opciones.put(c.id.toString(), c.nombre);
  	}
  	return opciones;
  }

}