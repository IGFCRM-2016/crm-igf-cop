package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="oferta")
public class Oferta extends Model{

	@Id
	public Long id;

	//@Constraints.DateTime(pattern="dd-MM-yyyy - hh:mm")
	@Constraints.Required(message="Debe ingresar fecha de inicio")
	public Date fecha_inicio;

	//@Constraints.DateTime(pattern="dd-MM-yyyy - hh:mm")
	@Constraints.Required(message="Debe ingresar fecha de fin")
	public Date fecha_fin;

	@Constraints.Required(message="Debe ingresar el precio global de la nueva oferta")
	public double precio;

	//no puedo obtener directamente todos los productos involucrados en la oferta pero puedo obtener todas las ProductoJoinOfertas que viene siendo lo mismo
	@OneToMany(mappedBy="oferta")
  	List<ProductoJoinOferta> producto_join_oferta;



	//CRITERIOS DE APLICACION DE LA OFERTA

	@Constraints.Required(message="Debe ingresar el genero del cliente a quien le aparecera la oferta")
	public int genero;

	@Constraints.Required(message="Debe ingresar el password")
	public int compras_minimas;

	//aqui si dejamos que se cree la tabla intermediaria por defecto
  	@OneToMany(mappedBy="oferta")
  	List<AplicacionOferta> aplicaciones_oferta;

  	//FIN CRITERIOS DE APLICACION DE LA OFERTA

    public static Finder<Long, Oferta> find = new Finder<Long,Oferta>(Oferta.class);

}