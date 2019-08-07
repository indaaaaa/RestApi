package com.platzi.Wservice;

import java.util.List;

import com.platzi.Zmodel.Course;

public interface CourseService {
	
	
void guardarCurso(Course course);
	
	void eliminarCurso(int idCurso);
	
	void actualizarCurso(Course course);
	
	List<Course> buscarTodosLosCursos();
	
	Course encontrarCursoPorId(int idCurso);
	
	Course encontrarCursoPorNombre(String nombreCurso);
	
	List<Course> encontrarPorIdDelTeacherTodosLosCursosQueDaUnTeacher(int idTeacher);

}
