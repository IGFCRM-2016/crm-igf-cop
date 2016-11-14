package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="aplicacion_oferta")
public class AplicacionOferta extends Model{

	@Id
	public Long id;

	@ManyToOne
	public Oferta oferta;

	@ManyToOne
	public Categoria categoria;

}