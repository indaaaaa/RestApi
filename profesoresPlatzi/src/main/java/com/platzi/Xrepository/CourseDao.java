package com.platzi.Xrepository;

import java.util.List;

import com.platzi.Zmodel.Course;

public interface CourseDao {
	
	void guardarCurso(Course course);
	
	void eliminarCurso(int idCurso);
	
	void actualizarCurso(Course course);
	
	List<Course> buscarTodosLosCursos();
	
	Course encontrarCursoPorId(int idCurso);
	
	Course encontrarCursoPorNombre(String name);
	
	List<Course> encontrarPorIdDelTeacherTodosLosCursosQueDaUnTeacher(int idTeacher);
	
	
	
	
	

}
