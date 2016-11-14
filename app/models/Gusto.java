package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="gusto")
public class Gusto extends Model{

	@Id
	public Long id;

	@ManyToOne
	public Cliente cliente;

	@ManyToOne
	public Categoria categoria;

}