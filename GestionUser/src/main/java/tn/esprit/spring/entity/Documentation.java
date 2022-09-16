package tn.esprit.spring.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Documentation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
int idDoc;
	String titre;
	String descriptionD;
	Date dateCreation;
	@Lob
	byte[] pieceJointe;

	public int getIdDoc()
	{
		return idDoc;
	}
	public String getTitre()
	{
		return titre;
	}
	public String getDescription()
	{
		return descriptionD;
	}
		public byte[] getData()
		{
			return pieceJointe;
		}
		
	public Documentation(String fileName,String fileType,byte[] data,Date dateCreation)
	{
		this.titre =fileName;
		this.descriptionD=fileType;
		this.pieceJointe=data;
		this.dateCreation=dateCreation;
	}

	
}
