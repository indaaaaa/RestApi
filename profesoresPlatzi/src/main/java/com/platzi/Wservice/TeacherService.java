package com.platzi.Wservice;

import java.util.List;

import com.platzi.Zmodel.Teacher;

public interface TeacherService {

	void saveTeacher(Teacher teacher);

	void deleteTeacherByid(int idTeacher);

	void updateTeacher(Teacher teacher);

	List<Teacher> findAllTeachers();

	Teacher encontrarTeacherPorId(int idTeacher);

	Teacher encontrarTeacherPorNombre(String nombreTeacher);

}
