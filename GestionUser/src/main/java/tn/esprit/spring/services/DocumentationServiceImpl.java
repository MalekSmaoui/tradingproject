package tn.esprit.spring.services;

import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Documentation;
import tn.esprit.spring.repositories.DocumentationRepository;



@Service
public class DocumentationServiceImpl implements DocumentationService{
@Autowired
DocumentationRepository documentationRepository;
	@Override
	public void addDocumentation(Documentation d) {
		
		documentationRepository.save(d);
	}

	@Override
	public void EditDocumentation(Documentation d, int iddoc) {
		Documentation doc= documentationRepository.findById(iddoc).orElse(null);

	doc.setDescriptionD(d.getDescription());

	doc.setTitre(d.getTitre());	
	documentationRepository.save(doc);
	}

	@Override
	public void deleteDocumentation(int iddoc) {
		documentationRepository.deleteById(iddoc);
		
	}
	@Override
	public Documentation saveDocumentation(MultipartFile file) throws Exception
	{
		String titre = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(titre.contains("..")){
				throw new Exception("Filename contains invalid path sequence"+titre);
			}
			LocalDate localDate=LocalDate.now();
			Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Documentation doc =new Documentation(titre,file.getContentType(),file.getBytes(),date);
			return documentationRepository.save(doc);
		}catch(Exception e)
		{
			throw new Exception("Could not save file:"+titre);
		}
	}
	
	@Override
	public Documentation getDocumentation(int fileId) throws Exception
	{
		return documentationRepository.findById(fileId).orElseThrow(()-> new Exception("File Not found"));
	}


	@Override
	public List<Documentation> Documentations() {
		// TODO Auto-generated method stub
		return documentationRepository.findAll();
	}

	@Override
	public List<Documentation> search(String keyword) {
		// TODO Auto-generated method stub
		return documentationRepository.search(keyword);
	}

}
