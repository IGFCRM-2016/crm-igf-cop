package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

//ENTIDAD INTERNA

@Entity
@Table(name="linea_producto")
public class LineaProducto extends Model{

	@Id
	public Long id;

	@ManyToOne
	public Producto producto;

	@ManyToOne
	public Compra compra;

	public int cantidad;

	public double precio_compra;

	public double subtotal;

    public static Finder<Long, LineaProducto> find = new Finder<Long,LineaProducto>(LineaProducto.class);

}