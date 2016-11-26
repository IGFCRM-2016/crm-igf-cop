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
                if(((File)picture.getFile()).length()!=0){
                    String contentType = picture.getContentType(); 
                    File file = (File)picture.getFile();
                    RandomAccessFile raf = new RandomAccessFile(file, "r");
                    nueva.imagen=new byte[(int)raf.length()];
                    raf.readFully(nueva.imagen);
                    nueva.contentTypeImagen=contentType;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        

        //obtener los id de los productos a incluir en la oferta
        ArrayList<Long> cats = new ArrayList<Long>();
        ArrayList<Integer> gens = new ArrayList<Integer>();
        for(Map.Entry<String, String[]> entry : values.entrySet()){
        	
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
            //aqui manejamos manejamos lo de  las categorias y genero de la oferta
            for(Long c : cats){
                AplicacionOferta ao=new AplicacionOferta();
                ao.oferta=nueva;
                ao.categoria=Categoria.find.byId(c);
                nueva.aplicaciones_oferta.add(ao);
            }

            if( gens.contains(Integer.valueOf(1)) && gens.contains(Integer.valueOf(2)) || gens.contains(Integer.valueOf(3)) ){
                //es oferta unisex
                nueva.genero=3;
            }else{
                if(gens.contains(Integer.valueOf(1))){
                    //es oferta para hombre
                    nueva.genero=1;
                }else{
                    //es oferta para mujer
                    System.out.println(gens.contains(Integer.valueOf(2)));
                    nueva.genero=2;
                }
            }


        }

        nueva.save();



        flash("exito","Operacion exitosa!");

        return redirect(routes.AgenteMarketingController.ofertas()); 
	}



	public Result oferta_edit(Long id) {//metodo get
		Oferta oferta = Oferta.find.byId(id);
        List<ProductoJoinOferta> pjos = oferta.producto_join_oferta;
        ArrayList<Producto> productos_selected = new ArrayList<Producto>();

        for(ProductoJoinOferta pjo : pjos){
            productos_selected.add(pjo.producto);
        }

        Form<Oferta> oferta_form = oferta.getForm();


        if(oferta==null){
            return ok("Error oferta nula!");
        }

        List<Producto> productos_list = Producto.find.findList();
       
        
        return ok(oferta_edit.render(oferta_form,productos_list,productos_selected));
	}

    public Result oferta_edit_post(Long id) {//metodo post
        //datos relacionados a la oferta a actualizar
        Oferta oferta = Oferta.find.byId(id);
        Form<Oferta> oferta_form = Form.form(Oferta.class).bindFromRequest();
        Map<String, String[]> values = request().body().asMultipartFormData().asFormUrlEncoded();

        //datos indirectos relacionados con la oferta
        List<ProductoJoinOferta> pjos = oferta.producto_join_oferta;
        ArrayList<Producto> productos_selected = new ArrayList<Producto>();
        List<Producto> productos_list = Producto.find.findList();

            
        for(ProductoJoinOferta pjo : pjos){
            productos_selected.add(pjo.producto);
        }

        //Si hay errores siempre los retornara
        if( oferta_form.hasErrors() ){
            flash("modal","mod-new");
            return badRequest(oferta_edit.render(oferta_form,productos_list,productos_selected));
        }

        Oferta to_modify = oferta_form.get();

        try{
            

            MultipartFormData body = request().body().asMultipartFormData();
            FilePart picture = body.getFile("imagen");
            if (picture != null) {
                if(((File)picture.getFile()).length()!=0){
                    String contentType = picture.getContentType(); 
                    File file = (File)picture.getFile();
                    RandomAccessFile raf = new RandomAccessFile(file, "r");
                    to_modify.imagen=new byte[(int)raf.length()];
                    raf.readFully(to_modify.imagen);
                    to_modify.contentTypeImagen=contentType;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        

        //obtener los id de los productos a incluir en la oferta
        ArrayList<Long> cats = new ArrayList<Long>();
        ArrayList<Integer> gens = new ArrayList<Integer>();

        for(Map.Entry<String, String[]> entry : values.entrySet()){
            if(entry.getKey().startsWith("cantidad")){
                String pid = entry.getKey();
                pid=pid.substring(pid.indexOf("[")+1,pid.indexOf("]"));

                Producto p=Producto.find.byId(Long.valueOf(pid));

                ProductoJoinOferta pjo = new ProductoJoinOferta();
                pjo.producto=p;
                pjo.oferta=to_modify;
                pjo.cantidad=Integer.valueOf(entry.getValue()[0]).intValue();
                to_modify.producto_join_oferta.add(pjo);
                
                if(!cats.contains(p.categoria.id)){
                    cats.add(p.categoria.id);
                }

                if(!gens.contains(p.genero)){
                    gens.add(p.genero);
                }
            }
        }

        //aqui manejamos manejamos lo de  las categorias y genero de la oferta
        for(Long c : cats){
            AplicacionOferta ao=new AplicacionOferta();
            ao.oferta=to_modify;
            ao.categoria=Categoria.find.byId(c);
            to_modify.aplicaciones_oferta.add(ao);
        }

        if( gens.contains(Integer.valueOf(1)) && gens.contains(Integer.valueOf(2)) || gens.contains(Integer.valueOf(3)) ){
            //es oferta unisex
            to_modify.genero=3;
        }else{
            if(gens.contains(Integer.valueOf(1))){
                //es oferta para hombre
                to_modify.genero=1;
            }else{
                //es oferta para mujer
                System.out.println(gens.contains(Integer.valueOf(2)));
                to_modify.genero=2;
            }
        }


        //finalmente
        to_modify.update();



        flash("exito","Operacion exitosa!");

        return redirect(routes.AgenteMarketingController.oferta_edit(to_modify.id)); 
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