package tn.esprit.spring.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Documentation;


public interface DocumentationService {
	public void addDocumentation(Documentation d);
	public void EditDocumentation(Documentation d,int iddoc);
	public void deleteDocumentation(int iddoc);
	public List<Documentation> Documentations();
	public List<Documentation> search(String keyword);
	public Documentation saveDocumentation(MultipartFile file) throws Exception;
	public Documentation getDocumentation(int fileId) throws Exception;

}
