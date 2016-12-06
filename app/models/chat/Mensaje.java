package models.chat;

import akka.actor.*;
import java.util.*;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.annotation.*;
import models.chat.*;


public class Mensaje  {


	public String from_username;
	public String from_nombre;
	public String to_username;
	public String to_nombre;
	public String texto;
	public String fecha;

    @JsonIgnore
	Conversacion conversacion;

	public Mensaje(String from_username, String from_nombre, String to_username, String to_nombre, String texto, Conversacion conversacion){
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        this.from_nombre=from_nombre;
		this.from_username=from_username;
		this.to_nombre=to_nombre;
		this.to_username=to_username;
		this.texto=texto;
		this.fecha=formater.format(new Date());
		this.conversacion=conversacion;
	}

}