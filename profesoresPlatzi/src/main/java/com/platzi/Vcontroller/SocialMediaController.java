
package com.platzi.Vcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.platzi.Wservice.SocialMediaService;
import com.platzi.Zmodel.SocialMedia;
import com.platzi.util.ErrorPersonalizado;

@Controller
@RequestMapping("/v1")
public class SocialMediaController {
	
	@Autowired
	private SocialMediaService socialMediaService;

	// metodo obtener las socialMedias, obtener es con estado GET.
	//tambien se puede obtener con este metodo una socialMedia mediante su nombre

	@RequestMapping(value = "/socialMedias", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias(@RequestParam(required = false) String nombre) {
		
		List<SocialMedia> listaSocialMedias = new ArrayList<>();
		
		if(nombre == null){
		
		listaSocialMedias = socialMediaService.buscarTodasLasSocialMedias();
		
		
		if (listaSocialMedias.isEmpty()) {
			
			return new ResponseEntity(new ErrorPersonalizado("La lista esta vacia"), HttpStatus.CONFLICT);
			
		}
		
		return new ResponseEntity<List<SocialMedia>>(listaSocialMedias, HttpStatus.OK);
		}else{
			
			SocialMedia socialMedia =  socialMediaService.encontrarSocialMediaPorNombre(nombre);
			
			if (socialMedia == null) {
				return new ResponseEntity(new ErrorPersonalizado("La socialMedia no existe"), HttpStatus.CONFLICT);
			}
			
			listaSocialMedias.add(socialMedia);
			
			return new ResponseEntity<List<SocialMedia>>(listaSocialMedias, HttpStatus.OK);
			
		}

	}
	
	
	
	//metodo para obtener una socialMedia puntual mediante su id
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> getSocialMediaPorId(@PathVariable("id") int idSocialMedia){
		
		if (idSocialMedia <=0) {
			
			return new ResponseEntity(new ErrorPersonalizado("El id De SocialMedia no puede ser 0 o menor") ,HttpStatus.CONFLICT);
			
		}
		
		SocialMedia socialMedia = socialMediaService.encontrarSocialMediaPorId(idSocialMedia);
		
		if (socialMedia == null) {
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<SocialMedia>(socialMedia, HttpStatus.OK);
		
		
		
	}
	
	
	
	
	
	
	//Crear una socialMedia
	
	@RequestMapping(value="/socialMedias", method = RequestMethod.POST, headers = "Accept=application/json" )
	public ResponseEntity<String> crearSocialMedia(@RequestBody SocialMedia socialMedia, UriComponentsBuilder uriComponentsBuilder){
		
		//si la socialMedia viene vacia, ejemplo se intenta crear sin colocarle un nombre
		if (socialMedia.getName().equals(null) || socialMedia.getName().isEmpty() ) {
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		
		//aca si la socialMedia ya existe, si el nombre es distinto de nulo
		if (socialMediaService.encontrarSocialMediaPorNombre(socialMedia.getName()) != null) {
			
			return new ResponseEntity(HttpStatus.FOUND);
			
		}
		
		//si todo lo anterior es correcto guardo la socialMedia en la base de datos
		socialMediaService.guardarSocialMedia(socialMedia);
		
		//esa socialMedia que guarde ahora la busco para guardarla en otraVariable y poder trabajarla
		SocialMedia socialMediaRecienGuardadaAhoraRecuperada = socialMediaService.encontrarSocialMediaPorNombre(socialMedia.getName());
		
		//creo un objeto HttpHeaders, el cual me brindara los metodos necesarios para poder asignarle una url al recurso creado.
		HttpHeaders httpHeaders = new HttpHeaders();
		
		//seteo la locacion, uriComponetsBuilder.path me permite crear la uri donde se alojara el recursom y buildAndExpand completa el espacio {id} ycomo lo que requiere el metodo es una uri, lo hacermo con .toUri
		httpHeaders.setLocation(uriComponentsBuilder.path("/v1/socialMedias/{id}").buildAndExpand(socialMediaRecienGuardadaAhoraRecuperada.getIdSocialMedia()).toUri());
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
		
		
	}
	
	
	//Actualizar socialMedia
	//aca pido el id y tambien pido el objeto con @RequestBody
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> actualizarSocialMedia(@PathVariable("id") int idSocialMedia, @RequestBody SocialMedia socialMedia){
		
		
		if (idSocialMedia <=0) {
			return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}
		
		if (socialMedia == null) {
			
			return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
			
		}
		
		SocialMedia socialMediaActual = socialMediaService.encontrarSocialMediaPorId(idSocialMedia);
		
		socialMediaActual.setName(socialMedia.getName());
		socialMediaActual.setIcon(socialMedia.getIcon());
		
		socialMediaService.actualizarSocialMedia(socialMediaActual);
		
		
		return new ResponseEntity<SocialMedia>(socialMediaActual, HttpStatus.OK);
		
		
		
		
	}
	
	//Eliminar SocialMedia
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> eliminarSocialMedia(@PathVariable("id") int idSocialMedia){
		
		if (idSocialMedia <=0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		SocialMedia socialMedia = socialMediaService.encontrarSocialMediaPorId(idSocialMedia);
		
		if (socialMedia == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		socialMediaService.eliminarSocialMedia(idSocialMedia);
		
		
		
		
		return new ResponseEntity<SocialMedia>(HttpStatus.OK);
		
		
	}
	
	//obtener socialMedia por nombre
	@RequestMapping(value = "/socialMedias/nombre", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> obtenerSocialMediaPorNombre(@RequestParam(value="nombre", required = false) String nombre){
		
		SocialMedia socialMedia = socialMediaService.encontrarSocialMediaPorNombre(nombre);
		
		
		return new ResponseEntity<SocialMedia>(socialMedia, HttpStatus.OK);
		
	}
	
	
	

}
