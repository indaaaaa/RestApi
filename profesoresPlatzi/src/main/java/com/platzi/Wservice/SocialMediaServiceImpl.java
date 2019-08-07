package com.platzi.Wservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.Xrepository.SocialMediaDao;
import com.platzi.Zmodel.SocialMedia;
import com.platzi.Zmodel.TeacherSocialMedia;

@Service("socialMediaService")
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {

	@Autowired
	private SocialMediaDao socialMediaDao;

	@Override
	public void eliminarSocialMedia(int idSocialMedia) {

		socialMediaDao.eliminarSocialMedia(idSocialMedia);

	}

	@Override
	public void actualizarSocialMedia(SocialMedia socialMedia) {

		socialMediaDao.actualizarSocialMedia(socialMedia);

	}

	@Override
	public List<SocialMedia> buscarTodasLasSocialMedias() {

		return socialMediaDao.buscarTodasLasSocialMedias();
		
	}

	@Override
	public SocialMedia encontrarSocialMediaPorId(int idSocialMedia) {
		
		return socialMediaDao.encontrarSocialMediaPorId(idSocialMedia);
		
	}

	@Override
	public SocialMedia encontrarSocialMediaPorNombre(String name) {
		
		return socialMediaDao.encontrarSocialMediaPorNombre(name);
		
	}

	@Override
	public TeacherSocialMedia encontrarSocialMediaPorIdYNombre(int idSocialMedia, String nickname) {
		
		return socialMediaDao.encontrarSocialMediaPorIdYNombre(idSocialMedia, nickname);
		
	}

	@Override
	public void guardarSocialMedia(SocialMedia socialMedia) {
		
		socialMediaDao.guardarSocialMedia(socialMedia);
	}

}
