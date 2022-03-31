/**
 * 
 */
package ec.com.teconet.api.usuario;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author fauob
 *
 */
public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

}
