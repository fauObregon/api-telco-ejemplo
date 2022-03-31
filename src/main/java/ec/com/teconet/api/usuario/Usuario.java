/**
 * 
 */
package ec.com.teconet.api.usuario;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author fauob
 *
 */
@Data
@Document
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String nombreCompleto;
	private String email;
	private String telefono;
	private LocalDate cumpleanios;

}
