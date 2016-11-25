package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.util.*;
import java.io.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import views.html.agente_marketing.*;

import models.*;


public class AgenteMarketingController extends Controller {

	public Result ofertas(){
		Form<Oferta> oferta_form = Form.form(Oferta.class);
        List<Producto> productos_list = Producto.find.findList();
		List<Oferta> ofertas_list = Oferta.find.findList();
		
		return ok(ofertas.render(oferta_form,ofertas_list,productos_list));
	}


	public Result oferta_new() {
		Form<Oferta> oferta_form = Form.form(Oferta.class).bindFromRequest();
        Map<String, String[]> values = request().body().asMultipartFormData().asFormUrlEncoded();
        List<Producto> productos_list = Producto.find.findList();
		List<Oferta> ofertas_list = Oferta.find.findList();

        //System.out.println(values.size());
            
        //Si hay errores siempre los retornara
        if( oferta_form.hasErrors() ){
            flash("modal","mod-new");
            return badRequest(ofertas.render(oferta_form,ofertas_list,productos_list));
        }

        Oferta nueva = oferta_form.get();

        try{
            

            MultipartFormData body = request().body().asMultipartFormData();
            FilePart picture = body.getFile("imagen");
              if (picture != null) {
                String contentType = picture.getContentType(); 
                File file = (File)picture.getFile();
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                nueva.imagen=new byte[(int)raf.length()];
                raf.readFully(nueva.imagen);
                nueva.contentTypeImagen=contentType;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        

        //obtener los id de los productos a incluir en la oferta
        for(Map.Entry<String, String[]> entry : values.entrySet()){
        	ArrayList<Long> cats = new ArrayList<Long>();
        	ArrayList<Integer> gens = new ArrayList<Integer>();
        	if(entry.getKey().startsWith("cantidad")){
        		String id = entry.getKey();
        		id=id.substring(id.indexOf("[")+1,id.indexOf("]"));

        		Producto p=Producto.find.byId(Long.valueOf(id));

        		ProductoJoinOferta pjo = new ProductoJoinOferta();
        		pjo.producto=p;
        		pjo.oferta=nueva;
        		pjo.cantidad=Integer.valueOf(entry.getValue()[0]).intValue();
        		nueva.producto_join_oferta.add(pjo);
        		
        		if(!cats.contains(p.categoria.id)){
        			cats.add(p.categoria.id);
        		}

        		if(!gens.contains(p.genero)){
        			gens.add(p.genero);
        		}

        		
        	}

        	//aqui mismo manejamos lo de  las categorias y genero de la oferta

        	for(Long c : cats){
        		AplicacionOferta ao=new AplicacionOferta();
        		ao.oferta=nueva;
        		ao.categoria=Categoria.find.byId(c);
        		nueva.aplicaciones_oferta.add(ao);
        	}

        	if( gens.contains(1) && gens.contains(2) || gens.contains(3) ){
        		//es oferta unisex
        		nueva.genero=3;
        	}else{
        		if(gens.contains(1)){
        			//es oferta para hombre
        			nueva.genero=1;
        		}else{
        			//es oferta para mujer
        			nueva.genero=2;
        		}
        	}
        }

        nueva.save();



        flash("exito","Operacion exitosa!");

        return redirect(routes.AgenteMarketingController.ofertas()); 
	}



	public Result oferta_edit(Long id) {
		return redirect(routes.AgenteMarketingController.ofertas());
	}

	public Result oferta_remove(Long id){
		return redirect(routes.AgenteMarketingController.ofertas());
	}

	public Result getOfertaImage(Long id){

        try {
            Oferta prod = Oferta.find.byId(id);
            InputStream input = new ByteArrayInputStream(prod.imagen);
            java.awt.image.BufferedImage image =  javax.imageio.ImageIO.read(input);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(image,prod.contentTypeImagen.split("/")[1],baos);

            return ok(baos.toByteArray()).as(prod.contentTypeImagen);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return ok();
    }

	public Result estadisticas(){
		List<Oferta> ofertas_list = Oferta.find.findList();
		return ok(estadisticas.render(ofertas_list));
	}
	

  //   public Result index() {
		// if(session("connected")==null){
		// 	return ok(index.render());
		// }else{
		// 	return redirect(routes.HomeController.home());
		// }     
  //   }

}