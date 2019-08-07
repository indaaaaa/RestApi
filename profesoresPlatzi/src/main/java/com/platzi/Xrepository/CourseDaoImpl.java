package com.platzi.Xrepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.platzi.Zmodel.Course;

@Repository
@Transactional
public class CourseDaoImpl extends AbstractSession implements CourseDao {

	@Override
	public void guardarCurso(Course course) {
		
		obtenerSession().save(course);
		
		
	}

	@Override
	public void eliminarCurso(int idCurso) {
		
		 Course curso = (Course)obtenerSession().get(Course.class, idCurso);
		 
		 if(curso != null){
		 obtenerSession().delete(curso);
		 }
		
	}

	@Override
	public void actualizarCurso(Course course) {
		
		obtenerSession().update(course);
		
		
		
	}

	@Override
	public List<Course> buscarTodosLosCursos() {
		
		
		List<Course> listaDeCursos = obtenerSession().createQuery("from Course").list();
		
		
		
		return listaDeCursos;
	}

	@Override
	public Course encontrarCursoPorId(int idCurso) {
		
		return (Course)obtenerSession().get(Course.class, idCurso);
	}

	
	
	
	//estos metodos a chequear
	@Override
	public Course encontrarCursoPorNombre(String name) {
		
		
		return (Course)obtenerSession().createQuery("from Course where name = :name").setParameter("name", name).uniqueResult();
		
	
	}

	//quede aca video 38 aprox minuto 12, yo no lo hice igual, ver que lo esta usando un join. probar.
	@Override
	public List<Course> encontrarPorIdDelTeacherTodosLosCursosQueDaUnTeacher(int idTeacher) {
		
		
		List<Course> listaDeCursos = obtenerSession().createQuery("from Course c join c.teacher t where t.idTeacher = :idTeacher").setParameter("teacher", idTeacher).list();
		
		return listaDeCursos;
	
		
		
	}

}
