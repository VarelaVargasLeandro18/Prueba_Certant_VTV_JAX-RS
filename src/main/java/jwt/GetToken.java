package jwt;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import dao.InspectorDAO;
import dao_abstract.exceptions.ReadEntityException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("log-in")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public class GetToken {
	
	private InspectorDAO dao;
	
	public GetToken() {
		this.dao = new InspectorDAO();
	}
	
	// Debería ser generado u obtenido de properties.
	private final String customPassword = "cuandolaultimaflordelcerezocuandolaultimaflordelcerezocuandolaultimaflordelcerezocuandolaultimaflordelcerezocuandolaultimaflordelcerezocuandolaultimaflordelcerezo";
	
	@POST
	public Response getToken ( LoginJSON login ) {
		
		if ( login == null ) return Response.status( Response.Status.BAD_REQUEST ).build();
		
		String usuario = login.getUserName();
		String password = login.getPassword();
		
		try {
			this.dao.ingresar(usuario, password);
		} catch ( ReadEntityException ex ) {
			return Response.status( Response.Status.FORBIDDEN )
					.entity( "Usuario y/o contrasenia no válidos" )
					.build();
		}
		
		JSONObject jsonLogin = new JSONObject();
		jsonLogin.append( "user", usuario );
		jsonLogin.append( "password" , password );
		
		String strJsonLogin = jsonLogin.toString();
		
		return Response.ok().entity(
				Jwts
					.builder()
					.setSubject(strJsonLogin)
					.signWith( SignatureAlgorithm.HS512 , this.customPassword)
					.compact()
				).build();
	}
	
}
