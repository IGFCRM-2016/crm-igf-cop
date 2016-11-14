package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
@Table(name="tarjeta")
public class Tarjeta extends Model{
	 @Id
	 public Long id;

	 @Constraints.Required(message="Debe ingresar el numero de tarjeta")
	 public String numero;

	 @Constraints.Required(message="")
	 public double saldo=100;

	 @OneToOne
	 public Cliente cliente;

     public static Finder<Long, Tarjeta> find = new Finder<Long,Tarjeta>(Tarjeta.class);

}