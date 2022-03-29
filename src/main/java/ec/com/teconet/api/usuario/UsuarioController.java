/**
 * 
 */
package ec.com.teconet.api.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fauob
 *
 */
@RestController
@RequestMapping(name = "API USUARIO TELCONET", value = "/v1/telconet")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	UsuarioRepository dao;

	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> obtenerTodosUsarios() {

		return ResponseEntity.of(Optional.of(dao.findAll()));
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	@PostMapping(path = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> crearUsuario(@RequestBody(required = true) Usuario usuario) {

		usuario.setId(null);

		return ResponseEntity.status(HttpStatus.CREATED).body(dao.save(usuario));
	}

	/**
	 * 
	 * @param idUsuario
	 * @param usuario
	 * @return
	 */
	@PutMapping(path = "/usuario/{idUsuario}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizarUsuario(
			@PathVariable(name = "idUsuario", value = "idUsuario") Long idUsuario,
			@RequestBody(required = true) Usuario usuario) {
		
		
		if(!dao.existsById(idUsuario)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ESTE USUARIO NO EXISTE");
		}
	
		usuario.setId(idUsuario);
		
		return  ResponseEntity.ok(dao.save(usuario));
	}

	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	@GetMapping(path = "/usuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerUsuarioPorId(
			@PathVariable(name = "idUsuario", value = "idUsuario") Long idUsuario) {
		
		return ResponseEntity.of(dao.findById(idUsuario));
	}

	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	@DeleteMapping(path = "/usuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminarUsuario(@PathVariable(name = "idUsuario", value = "idUsuario") Long idUsuario) {
		
		if(!dao.existsById(idUsuario)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ESTE USUARIO NO EXISTE");
		}
		
		dao.deleteById(idUsuario);

		return ResponseEntity.ok("USUARIO ELIMINADO");
	}

}
