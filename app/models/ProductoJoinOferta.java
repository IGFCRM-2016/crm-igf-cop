package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="producto_join_oferta")
public class ProductoJoinOferta extends Model{

	@Id
	public Long id;

	@ManyToOne
	public Producto producto;

	@ManyToOne
	public Oferta oferta;

	public int cantidad;

    public static Finder<Long, ProductoJoinOferta> find = new Finder<Long,ProductoJoinOferta>(ProductoJoinOferta.class);

}