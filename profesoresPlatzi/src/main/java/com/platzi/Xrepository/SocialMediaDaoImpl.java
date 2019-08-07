package com.platzi.Xrepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.platzi.Zmodel.Course;
import com.platzi.Zmodel.SocialMedia;
import com.platzi.Zmodel.TeacherSocialMedia;

@Repository
@Transactional
public class SocialMediaDaoImpl extends AbstractSession implements SocialMediaDao {

	@Override
	public void guardarSocialMedia(SocialMedia socialMedia) {
		
		obtenerSession().save(socialMedia);
		
	}

	@Override
	public void eliminarSocialMedia(int idSocialMedia) {
		
		SocialMedia socialMedia = (SocialMedia)obtenerSession().get(SocialMedia.class, idSocialMedia);
		 
		if(socialMedia != null){
		 obtenerSession().delete(socialMedia);
		}
		
	}

	@Override
	public void actualizarSocialMedia(SocialMedia socialMedia) {
		obtenerSession().update(socialMedia);
		
	}

	@Override
	public List<SocialMedia> buscarTodasLasSocialMedias() {

		
		List<SocialMedia> listaDeSocialMedia = obtenerSession().createQuery("from SocialMedia").list();
		
		
		
		return listaDeSocialMedia;
	}

	@Override
	public SocialMedia encontrarSocialMediaPorId(int idSocialMedia) {
		
		return (SocialMedia)obtenerSession().get(SocialMedia.class, idSocialMedia);		
	}

	
	//quede aca el error estaba en que estaba mal el string para mapear las clases del modelo . video 43 min 12:10, falta probar en el metodo
	//si la forma mia con el foreach resulta igual que el create query.
	@Override
	public SocialMedia encontrarSocialMediaPorNombre(String name) {
		
		return (SocialMedia)obtenerSession().createQuery("from SocialMedia where name = :name").setParameter("name", name).uniqueResult();
		
		/*List<SocialMedia> listaDeSocialMedia = obtenerSession().createQuery("from SocialMedia").list();
		
		for (SocialMedia socialMedia : listaDeSocialMedia) {
			
			
			
			if (socialMedia.getName() == name) {
				return socialMedia;
			}
			
		}
		
		return null;*/
		
	}

	
	//la idea es encontrar la socialMedia de un profesor, y devovlver los datos del profesor y los datos de la socialMedia
	@Override
	public TeacherSocialMedia encontrarSocialMediaPorIdYNombre(int idSocialMedia, String nickname) {
		
		
		List<Object[]> objetos =  obtenerSession().createQuery("from TeacherSocialMedia tsm join tsm.SocialMedia sm where sm.idSocialMedia = :idSocialMedia and tsm.nickname"
				+ "= :nickname").setParameter("idSocialmedia", idSocialMedia).setParameter("nickname", nickname).list();
		
		if(objetos.size() > 0){
		
		for (Object[] objects : objetos) {
			
			for (Object object : objects) {
				
				if (object instanceof TeacherSocialMedia) {
					
					return (TeacherSocialMedia) object;
					
				}
				
				
			}
			
		}
		
	}
		
		
		return null;
	}

}
