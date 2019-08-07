package com.platzi.Xrepository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.validator.internal.metadata.descriptor.ElementDescriptorImpl;
import org.springframework.stereotype.Repository;

import com.platzi.Zmodel.Teacher;
import com.platzi.Zmodel.TeacherSocialMedia;

@Repository
@Transactional
public class TeacherDaoImpl extends AbstractSession implements TeacherDao {
	
	
	
	

	public void saveTeacher(Teacher teacher) {

		obtenerSession().save(teacher);

		

	}

	public void deleteTeacherByid(int idTeacher) {

	   Teacher teacher = encontrarTeacherPorId(idTeacher);
	   
	   if (teacher != null) {
		
		  Set<TeacherSocialMedia> listaTeacherSocialMedias =  teacher.getListTeacherSocialMedias();
		   
		  for (TeacherSocialMedia teacherSocialMedia : listaTeacherSocialMedias) {
			
			  obtenerSession().delete(teacherSocialMedia);
			  
		}
		  
		  teacher.getListTeacherSocialMedias().clear();
		   
		   obtenerSession().delete(teacher);
		   
	}
	   

	}

	public List<Teacher> findAllTeachers() {

		// en este Query de HQL cuando decimos "from Teacher", estamos dici endo
		// desde la CLASE Teacher, no desde la entidad, recordar que HQL es
		// orientado a objetos

		return obtenerSession().createQuery("from Teacher").list();
	}
	
	

	public void updateTeacher(Teacher teacher) {

		obtenerSession().update(teacher);
		
	}

	@Override
	public Teacher encontrarTeacherPorId(int idTeacher) {
		
		return (Teacher) obtenerSession().get(Teacher.class, idTeacher);
		
	}

	@Override
	public Teacher encontrarTeacherPorNombre(String name) {
		
		
		return (Teacher)obtenerSession().createQuery("from Teacher where name = :name").setParameter("name", name).uniqueResult();
		
		
		
		
	}

}
