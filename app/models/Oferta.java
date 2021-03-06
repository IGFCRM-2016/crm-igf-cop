package models;

import java.util.*;
import java.io.File;
import java.io.File;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.*;
import com.avaje.ebean.*;


@Entity
@Table(name="oferta")
public class Oferta extends Model{

	@Id
	public Long id;

	@Constraints.Required(message="Debe ingresar un codigo de oferta")
	public String codigo;

	@Constraints.Required(message="Debe ingresar un nombre para la oferta")
	public String nombre;

	@Formats.DateTime(pattern="dd/MM/yyyy")
	@Constraints.Required(message="Debe ingresar fecha de inicio")
	public Date fecha_inicio;

	@Formats.DateTime(pattern="dd/MM/yyyy")
	@Constraints.Required(message="Debe ingresar fecha de fin")
	public Date fecha_fin;

	@Constraints.Required(message="Debe ingresar el precio global de la nueva oferta")
	public double precio;

	@Constraints.Required(message="Debe ingresar una descripcion para la oferta")
	public String descripcion;

	@Lob
	public byte[] imagen;

	//@Column(length=3)
	public String contentTypeImagen;

	//no puedo obtener directamente todos los productos involucrados en la oferta pero puedo obtener todas las ProductoJoinOfertas que viene siendo lo mismo
	@OneToMany(mappedBy="oferta",cascade=CascadeType.ALL)
  	public List<ProductoJoinOferta> producto_join_oferta;



	//CRITERIOS DE APLICACION DE LA OFERTA

	//@Constraints.Required(message="Debe ingredirect(routes.AgenteMarketingController.ofertas());resar el genero del cliente a quien le aparecera la oferta")
	public Integer genero;//1,masculino/2,femenino/3,unisex/

	public boolean active=true;
	// //@Constraints.Required(message="Debe ingresar el password")
	// public int compras_minimas;

	//aqui si dejamos que se cree la tabla intermediaria por defecto
  	@OneToMany(mappedBy="oferta",cascade=CascadeType.ALL)
  	public List<AplicacionOferta> aplicaciones_oferta;

  	//FIN CRITERIOS DE APLICACION DE LA OFERTA

    public static Finder<Long, Oferta> find = new Finder<Long,Oferta>(Oferta.class);



    public Form<Oferta> getForm(){
    	Oferta o = Oferta.find.byId(this.id);
    	Form<Oferta> returning=Form.form(Oferta.class).fill(o);
    	return returning;

	}//Fin getForm

}