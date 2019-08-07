package com.platzi.Zmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="teacher")
public class Teacher implements Serializable {
	
	@Id
	@Column(name="id_teacher")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTeacher;
	
	@Column(name="name")
	private String name;
	
	@Column(name="avatar")
	private String avatar;
	
	
	//(mappedBy="teacher") // este dato es inecesario, con @JoinColumn funciona y queda mas logico. tener en cuenta que ahora no esta igual que la explicacion
	
	@OneToMany
	@JoinColumn(name="id_teacher")
	@JsonIgnore
	private Set<Course> listCourses;
	
	
	//este dato es confuso, se usara de engache?, porque no una lista de socialMedias? porque lista de TeacherSocialMedias
	//al parecer esta lista es la que conecta a teacher y socialMedias
	//aca ponemos el tipo de cascada, ya que si se borra el teacher, se borra tambien su nickname o red social.
	
	
	// aca tengo dudas, no hay ningun campo en la tabla teacher, que haga referencia a teacherSocialMedia y usa su propio campo id_teacher?
	//por otro lado, si lo que se busca es la llave primaria del objeto que estamos anotando, en este caso no deberia ser id_teacher_social_media?
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_teacher") 
	@JsonIgnore
	private Set<TeacherSocialMedia> listTeacherSocialMedias;
	
	

	public Teacher() {
		super();
	}
	
	//agrego este constructor extra hay que ver como esta hecho en el video
	public Teacher(String name, String avatar){
		this.name = name;
		this.avatar = avatar;
		
		
	}

	
	// aca falta inicializar la lista en el constructor
	public Teacher(int idTeacher, String name, String avatar) {
		super();
		this.idTeacher = idTeacher;
		this.name = name;
		this.avatar = avatar;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<Course> getListCourses() {
		return listCourses;
	}

	public void setListCourses(Set<Course> listCourses) {
		this.listCourses = listCourses;
	}

	public Set<TeacherSocialMedia> getListTeacherSocialMedias() {
		return listTeacherSocialMedias;
	}

	public void setListTeacherSocialMedias(Set<TeacherSocialMedia> listTeacherSocialMedias) {
		this.listTeacherSocialMedias = listTeacherSocialMedias;
	}

	
	
	
	

}
