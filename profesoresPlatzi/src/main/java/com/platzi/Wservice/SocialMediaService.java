package com.platzi.Wservice;


import java.util.List;

import com.platzi.Zmodel.SocialMedia;
import com.platzi.Zmodel.TeacherSocialMedia;

public interface SocialMediaService {
	
	void guardarSocialMedia(SocialMedia socialMedia);
	
	void eliminarSocialMedia(int idSocialMedia);
	
	void actualizarSocialMedia(SocialMedia socialMedia);
	
	List<SocialMedia> buscarTodasLasSocialMedias();
	
	SocialMedia encontrarSocialMediaPorId(int idSocialMedia);
	
	SocialMedia encontrarSocialMediaPorNombre(String nombreSocialMedia);
	
	
	
	TeacherSocialMedia encontrarSocialMediaPorIdYNombre(int idSocialMedia, String nickname);

}
