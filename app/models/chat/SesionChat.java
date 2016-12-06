package models.chat;
import play.mvc.*;
import play.libs.*;
import play.libs.F.*;
import java.util.*;
import java.lang.Runnable;//instead of play.libs.F.Callback0
import java.util.function.Consumer;//instead of play.libs.F.Callback


public class SesionChat{

	public String id="";

	public List<CustomSocket> endpoints = new ArrayList<CustomSocket>();

	public int notifyAll(String data){
		for(CustomSocket socket : endpoints){
			socket.out.write(data);
		}
		return endpoints.size();
	}

}