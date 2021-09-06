package jwt;

import java.io.IOException;

import javax.ws.rs.Produces;

//import javax.crypto.KeyGenerator;
//import javax.inject.Inject;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;

/**
 * Middleware para verificar JWT.
 * @author Varela Vargas Leandro
 */
@Provider
@JWTAuthorization
@Produces(MediaType.APPLICATION_JSON)
public class JWTFilter implements ContainerRequestFilter {

	//@Inject
	//private KeyGenerator keyGen;
	//El algoritmo exige que el tamaño de la password sea mayor o igual a 512 bits.
	private final String customPassword = "cuandolaultimaflordelcerezocuandolaultimaflordelcerezocuandolaultimaflordelcerezocuandolaultimaflordelcerezocuandolaultimaflordelcerezocuandolaultimaflordelcerezo";
	
	/**
	 * Esta función permite verificar que el token sea correcto.
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		
		try {
			
			String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
						
			Jwts.parser()
				.setSigningKey(this.customPassword)
				.parseClaimsJws(token);
			
		} catch ( Throwable ex ) {
			ex.printStackTrace();
            requestContext.abortWith(
            		Response
            			.status(Response.Status.UNAUTHORIZED)
            			.entity("No provee un token válido")
            			.build()
            			);
		}
		
	}

}
