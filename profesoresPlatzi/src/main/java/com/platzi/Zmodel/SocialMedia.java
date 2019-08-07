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
@Table(name="social_media")// falta no se la sintaxis
public class SocialMedia implements Serializable {
	
	//ojo aca con este long porque el video esta con L mayuscula, usando clase Long y no valor primitivo long
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_social_media")
	private int idSocialMedia;
	
	
	@Column(name="name")
	private String name;
	
	@Column(name="icon")
	private String icon;
	
	
	//aca uso el json ignore para que este dato no sea obligatorio al solicitar las socialMedias. sino da una exception
	@OneToMany
	@JoinColumn(name="id_social_media")
	@JsonIgnore
	private Set<TeacherSocialMedia> listTeacherSocialMedias;

	
	
	
	
	public SocialMedia() {
		
	}
	
	

	// aca falta inicializar la lista en el constructor
	public SocialMedia(int idSocialMedia, String name, String icon) {
		super();
		this.idSocialMedia = idSocialMedia;
		this.name = name;
		this.icon = icon;
	}



	public int getIdSocialMedia() {
		return idSocialMedia;
	}

	public void setIdSocialMedia(int idSocialMedia) {
		this.idSocialMedia = idSocialMedia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}



	public Set<TeacherSocialMedia> getListTeacherSocialMedias() {
		return listTeacherSocialMedias;
	}



	public void setListTeacherSocialMedias(Set<TeacherSocialMedia> listTeacherSocialMedias) {
		this.listTeacherSocialMedias = listTeacherSocialMedias;
	}
	
	
	
	
	

}
