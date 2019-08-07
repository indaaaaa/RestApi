package com.platzi.Wservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.Xrepository.CourseDao;
import com.platzi.Zmodel.Course;

//ver el significado de lo que esta dentro del parentesis
@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
	

	@Override
	public void guardarCurso(Course course) {
		
		courseDao.guardarCurso(course);
		
		
	}

	@Override
	public void eliminarCurso(int idCurso) {
		courseDao.eliminarCurso(idCurso);
		
	}

	@Override
	public void actualizarCurso(Course course) {
		courseDao.actualizarCurso(course);
		
	}

	@Override
	public List<Course> buscarTodosLosCursos() {
		return courseDao.buscarTodosLosCursos();
		
	}

	@Override
	public Course encontrarCursoPorId(int idCurso) {
		
		return courseDao.encontrarCursoPorId(idCurso);
	}

	@Override
	public Course encontrarCursoPorNombre(String nombreCurso) {
		
		return courseDao.encontrarCursoPorNombre(nombreCurso);
	}

	@Override
	public List<Course> encontrarPorIdDelTeacherTodosLosCursosQueDaUnTeacher(int idTeacher) {
		return courseDao.encontrarPorIdDelTeacherTodosLosCursosQueDaUnTeacher(idTeacher);
	}

	
	
}
