package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="compra")
public class Compra extends Model{
	@Id
	public Long id;

	//@Constraints.DateTime(pattern="dd-MM-yyyy - hh:mm")
	public Date fecha;

	public double total;

	@ManyToOne
	public Cliente cliente;

	//lineas de productos
	@OneToMany(mappedBy="compra")
	public List<LineaProducto> lineas_producto;

	//lineas de ofertas
	@OneToMany(mappedBy="compra")
	public List<LineaOferta> lineas_oferta;

    public static Finder<Long, Compra> find = new Finder<Long,Compra>(Compra.class);

}