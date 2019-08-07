package com.platzi.Xrepository;

import java.util.List;

import com.platzi.Zmodel.Course;
import com.platzi.Zmodel.SocialMedia;
import com.platzi.Zmodel.TeacherSocialMedia;

public interface SocialMediaDao {
	
	void guardarSocialMedia(SocialMedia socialMedia);
	
	void eliminarSocialMedia(int idSocialMedia);
	
	void actualizarSocialMedia(SocialMedia socialMedia);
	
	List<SocialMedia> buscarTodasLasSocialMedias();
	
	SocialMedia encontrarSocialMediaPorId(int idSocialMedia);
	
	SocialMedia encontrarSocialMediaPorNombre(String name);
	
	
	
	TeacherSocialMedia encontrarSocialMediaPorIdYNombre(int idSocialMedia, String nickname);

}
