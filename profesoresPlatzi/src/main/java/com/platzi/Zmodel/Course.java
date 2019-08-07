package com.platzi.Zmodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course")
public class Course implements Serializable {

	@Id
	@Column(name="id_course")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCourse;
	
	@Column(name="name")
	private String name;
	
	@Column(name="themes")
	private String themes;
	
	@Column(name="project")
	private String project;
	
	
	//optional=true hacer referencia a que el teacher es opcional ya que en la BD este campo puede ser nulo, puede o no puede estar rellenado.
	
	//el name en join column, hace referencia a la llaver primaria del objeto donde estamos poniendo la anotacion.
	
	//el tipo de fetch, en este caso EAGER(seria como decir imperativo), al ponerlo en este caso en la clase Course en la referencia a teacher
	//le estoy diciendo, cuando te pida un curso forza tambien a que me traiga el teacher, los datos del teacher.
	
	
	@ManyToOne(optional=true, fetch=FetchType.EAGER) 
	@JoinColumn(name="id_teacher")
	private Teacher teacher;

	
	
	
	public Course() {
		super();
	}

	

	public Course(int idCourse, String name, String themes, String project, Teacher teacher) {
		super();
		this.idCourse = idCourse;
		this.name = name;
		this.themes = themes;
		this.project = project;
		this.teacher = teacher;
	}
	
	



	public Course(String name, String themes, String project) {
		super();
		this.name = name;
		this.themes = themes;
		this.project = project;
	}



	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThemes() {
		return themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
	
	
	
	
}
