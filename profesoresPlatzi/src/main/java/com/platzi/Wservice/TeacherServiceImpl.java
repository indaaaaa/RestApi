package com.platzi.Wservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.Xrepository.TeacherDao;
import com.platzi.Zmodel.Teacher;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDao teacherDao;

	@Override
	public void saveTeacher(Teacher teacher) {

		teacherDao.saveTeacher(teacher);

	}

	@Override
	public void deleteTeacherByid(int idTeacher) {

		teacherDao.deleteTeacherByid(idTeacher);

	}

	@Override
	public void updateTeacher(Teacher teacher) {

		teacherDao.updateTeacher(teacher);

	}

	@Override
	public List<Teacher> findAllTeachers() {
		return teacherDao.findAllTeachers();
	}

	@Override
	public Teacher encontrarTeacherPorId(int idTeacher) {
		return teacherDao.encontrarTeacherPorId(idTeacher);
	}

	@Override
	public Teacher encontrarTeacherPorNombre(String nombreTeacher) {
		return teacherDao.encontrarTeacherPorNombre(nombreTeacher);
	}

}
