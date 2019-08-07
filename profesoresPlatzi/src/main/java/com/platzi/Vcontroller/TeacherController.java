package com.platzi.Vcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.platzi.Wservice.TeacherService;

import com.platzi.Zmodel.Teacher;
import com.platzi.util.ErrorPersonalizado;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class TeacherController {

	@Autowired
	private TeacherService _teacherService;

	// OBTENER TODOS LOS TEACHERS
	@RequestMapping(value = "/teachers", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Teacher>> obtenerTodosLosTeachers(
			@RequestParam(value = "name", required = false) String name) {
		
		List<Teacher> listaDeTeachers = new ArrayList<Teacher>();
		
		
		if (name == null) {
			
			listaDeTeachers = _teacherService.findAllTeachers();

			if (listaDeTeachers.isEmpty()) {
				return new ResponseEntity(new ErrorPersonalizado("La lista esta vacia"), HttpStatus.CONFLICT);
			}

			return new ResponseEntity<List<Teacher>>(listaDeTeachers, HttpStatus.OK);
			
		}
		
		Teacher teacher = _teacherService.encontrarTeacherPorNombre(name);
		
		if (teacher == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		listaDeTeachers.add(teacher);
		
		return new ResponseEntity<List<Teacher>>(listaDeTeachers, HttpStatus.OK);
		

	}

	// OBTENER TEACHER ESPECIFICO POR ID
	@RequestMapping(value = "/teachers/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Teacher> encontrarTeacherPorId(@PathVariable("id") int idTeacher) {

		if (idTeacher <= 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Teacher teacher = _teacherService.encontrarTeacherPorId(idTeacher);

		if (teacher == null) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);

	}

	// ELIMINAR TEACHER
	@RequestMapping(value = "/teachers/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Teacher> eliminarCurso(@PathVariable("id") int idTeacher) {

		if (idTeacher <= 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Teacher teacherParaEliminar = _teacherService.encontrarTeacherPorId(idTeacher);

		if (teacherParaEliminar == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		_teacherService.deleteTeacherByid(idTeacher);

		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	
	private static final String RUTA_IMAGENES = "imagenes/teachers/";
	
	//CREAR IMAGEN PARA TEACHER
	@RequestMapping(value = "/teachers/imagen", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<byte[]> subirImagenTeacher(@RequestParam("id_teacher") int idTeacher, @RequestParam("archivo") MultipartFile archivo){
		
		
		if (idTeacher <= 0) {
			return new ResponseEntity(new ErrorPersonalizado("1") , HttpStatus.CONFLICT);
		}
		
		
		if (archivo == null || archivo.isEmpty()) {
			return new ResponseEntity(new ErrorPersonalizado("2") , HttpStatus.CONFLICT);
		}
		
		
		Teacher teacher = _teacherService.encontrarTeacherPorId(idTeacher);
		
		if (teacher == null) {
			return new ResponseEntity(new ErrorPersonalizado("3") , HttpStatus.CONFLICT);
		}
		
		if (teacher.getAvatar() !=null || !teacher.getAvatar().isEmpty()) {
			
			String rutaImagen = teacher.getAvatar();
			
			Path ruta = Paths.get(rutaImagen);
			
			
			File rutaImagenEnFormatoFila = ruta.toFile();
			
			if (rutaImagenEnFormatoFila.exists()) {
				
				rutaImagenEnFormatoFila.delete();
				
			}
			
		}
		
		try {
		Date date = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		String fechaConformadaEnTexto = formato.format(date);
		
		
		String nombreDelArchivoCompleto = String.valueOf(idTeacher) + fechaConformadaEnTexto + "."+ archivo.getContentType().split("/")[1];
		
		
		teacher.setAvatar(RUTA_IMAGENES + nombreDelArchivoCompleto); 
		
		
			byte[] bytes = archivo.getBytes();
			
			Path ruta = Paths.get(RUTA_IMAGENES + nombreDelArchivoCompleto);
			
			Files.write(ruta, bytes);
			
			_teacherService.updateTeacher(teacher);
			
			return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return new ResponseEntity( new ErrorPersonalizado("4") , HttpStatus.CONFLICT);
		
		
		
		
				
		
	}
	
	//OBTENER IMAGEN DE TEACHER
	@RequestMapping(value = "/teachers/{id}/imagen", method = RequestMethod.GET)
	public ResponseEntity<byte[]> mostrarImagenDeTeacher(@PathVariable("id") int idTeacher){
		
		if (idTeacher <=0) {
			return new ResponseEntity(new ErrorPersonalizado("El teacher no puede ser menor o igual que cero") , HttpStatus.CONFLICT);
		}
		
		Teacher teacher = _teacherService.encontrarTeacherPorId(idTeacher);
		
		if (teacher == null) {
			return new ResponseEntity(new ErrorPersonalizado("el teacher es nulo") , HttpStatus.CONFLICT);
		}
		
		
		String rutaDeTeacher = teacher.getAvatar();
		
		try {
		
		Path ruta = Paths.get(rutaDeTeacher);
		
		File rutaAFila = ruta.toFile();
		
		if (!rutaAFila.exists()) {
			return new ResponseEntity(new ErrorPersonalizado("error al mostrar la imagen") , HttpStatus.CONFLICT);
		}
		
		
			byte[] bytesDeImagen = Files.readAllBytes(ruta);
			
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytesDeImagen);
			
		} catch (IOException e) {
			return new ResponseEntity(new ErrorPersonalizado("error al mostrar la imagen") , HttpStatus.CONFLICT);
			
		}
		
		
		
		
	}
	
	
	
	
	//ELIMINAR IMAGEN DE TEACHER
	@RequestMapping(value = "/teachers/{id}/imagen/eliminar", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> eliminarLaImagenDeUnTeacher(@PathVariable("id") int idTeacher) throws IOException{
		
		if (idTeacher <=0) {
			return new ResponseEntity(new ErrorPersonalizado("El teacher no puede ser menor o igual que cero") , HttpStatus.CONFLICT);
		}
		
		Teacher teacher = _teacherService.encontrarTeacherPorId(idTeacher);
		
		if (teacher == null) {
			return new ResponseEntity(new ErrorPersonalizado("el teacher es nulo") , HttpStatus.CONFLICT);
		}
		
		
		String rutaDeTeacher = teacher.getAvatar();
		
		Path ruta = Paths.get(rutaDeTeacher);
		
		File rutaAFila = ruta.toFile();
		
		if (!rutaAFila.exists()) {
			return new ResponseEntity(new ErrorPersonalizado("la imagen no existe") , HttpStatus.CONFLICT);
		}
		
		Files.delete(ruta);
		teacher.setAvatar("avatar");
		
		_teacherService.updateTeacher(teacher);
		
		
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
