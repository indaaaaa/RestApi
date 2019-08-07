package com.platzi.Vcontroller;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.platzi.Wservice.CourseService;
import com.platzi.Zmodel.Course;
import com.platzi.Zmodel.SocialMedia;
import com.platzi.util.ErrorPersonalizado;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class CourseController {

	@Autowired
	private CourseService _courseService;

	// OBTENER TODOS LOS CURSOS
	@RequestMapping(value = "/courses", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Course>> listaDeCursos() {

		List<Course> listaDeCursos = _courseService.buscarTodosLosCursos();

		if (listaDeCursos.isEmpty()) {
			return new ResponseEntity(new ErrorPersonalizado("La lista esta vacia"), HttpStatus.CONFLICT);
		}

		return new ResponseEntity<List<Course>>(listaDeCursos, HttpStatus.OK);

	}

	// OBTENER CURSO ESPECIFICO POR ID
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Course> encontrarCursoPorId(@PathVariable("id") int idCurso) {

		if (idCurso <= 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Course course = _courseService.encontrarCursoPorId(idCurso);

		if (course == null) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Course>(course, HttpStatus.OK);

	}

	// CREAR CURSO
	@RequestMapping(value = "/courses", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> crearCurso(@RequestBody Course curso, UriComponentsBuilder uriComponentsBuilder) {

		if (curso.getName().equals(null) || curso.getName().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		if (curso == null) {
			return new ResponseEntity(new ErrorPersonalizado("el curso esta incompleto"), HttpStatus.CONFLICT);
		}

		if (_courseService.encontrarCursoPorNombre(curso.getName()) != null) {
			return new ResponseEntity(new ErrorPersonalizado("el curso ya existe"), HttpStatus.CONFLICT);
		}

		_courseService.guardarCurso(curso);

		Course cursoRecienGuardadoRecuperado = _courseService.encontrarCursoPorNombre(curso.getName());

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(uriComponentsBuilder.path("/v1/courses/{id}")
				.buildAndExpand(cursoRecienGuardadoRecuperado.getIdCourse()).toUri());

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// ACTUALIZAR CURSO
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<Course> actualizarCurso(@RequestBody Course curso, @PathVariable("id") int idCurso) {

		if (curso == null || idCurso <= 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Course cursoParaActualizar = _courseService.encontrarCursoPorId(idCurso);

		if (cursoParaActualizar == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		cursoParaActualizar.setName(curso.getName());
		cursoParaActualizar.setThemes(curso.getThemes());
		cursoParaActualizar.setProject(curso.getProject());

		_courseService.actualizarCurso(cursoParaActualizar);

		return new ResponseEntity<Course>(cursoParaActualizar, HttpStatus.OK);

	}
	
	
	//ELIMINAR CURSO
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Course> eliminarCurso(@PathVariable("id") int idCurso){
		
		
		if (idCurso <=0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		
		Course cursoParaEliminar = _courseService.encontrarCursoPorId(idCurso);
		
		if (cursoParaEliminar == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		_courseService.eliminarCurso(idCurso);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}















