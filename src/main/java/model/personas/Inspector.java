package model.personas;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONObject;

import model.Auto;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
@Entity
@Table(name="INSPECTORES")
public class Inspector extends Persona<Inspector> implements Serializable {
    
    @Column(name="usuario", nullable=false)
    private String usuario;
    
    /* NO ES UNA FORMA SEGURA DE MANEJAR UNA CONTRASEÑA PERO LO UTILIZO A FINES PRÁCTICOS. */
    @Column(name="contrasenia", nullable=false)
    private String contrasenia;    

	public Inspector() {}
    
    public Inspector(String usuario, String contrasenia, Long CUIL, String nombre, String apellido, LocalDateTime fechaNac, String email, String NroTelefono) {
        super(CUIL, nombre, apellido, fechaNac, email, NroTelefono);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
    
    public Inspector( Inspector inspector ) {
        super( inspector.getCUIL(), 
               inspector.getNombre(),
               inspector.getApellido(),
               inspector.getFechaNac(),
               inspector.getEmail(),
               inspector.getNroTelefono()
        ); // Todos Immutable.
    }

    // <editor-fold desc="Getters" defaultstate="collapsed">
    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    // </editor-fold>

    //<editor-fold desc="Setters" defaultstate="collapsed">
    public Inspector setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public Inspector setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
        return this;
    }
    //</editor-fold>
    
    @Override
    public String toString() {
        JSONObject json = new JSONObject(super.toString());
        
        json.put( "usuario" , this.usuario );
        json.put( "contrasenia", this.contrasenia );
        
        return json.toString(4);        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.usuario);
        hash = 97 * hash + Objects.hashCode(this.contrasenia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if ( !super.equals(obj) )
            return false;
        final Inspector other = (Inspector) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.contrasenia, other.contrasenia)) {
            return false;
        }
        return true;
    }
    
    
     
}
