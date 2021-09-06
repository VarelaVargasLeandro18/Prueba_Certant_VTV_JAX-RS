package model.personas;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.json.JSONObject;

import model.Auto;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
@Entity
@Table(name="PROPIETARIOS")
@XmlRootElement
@XmlSeeAlso(
		{TipoPropietario.class,
		Auto.class}
)
public class Propietario extends Persona<Propietario> implements Serializable {
    
    @ManyToOne
    @JoinColumn(name="tipo", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_TipoPropietario"), nullable=false)
    private TipoPropietario tipo;
    
    @OneToMany(mappedBy="propietario")
    private List<Auto> autos;

    public Propietario() {}

    public Propietario(TipoPropietario tipo, Long CUIL, String nombre, String apellido, LocalDateTime fechaNac, String email, String NroTelefono) {
        super(CUIL, nombre, apellido, fechaNac, email, NroTelefono);
        this.tipo = tipo;
    }
    
    @XmlElement
    public TipoPropietario getTipo() {
        return tipo;
    }

    @XmlElement
    public Propietario setTipo(TipoPropietario tipo) {
        this.tipo = tipo;
        return this;
    }
    
    @XmlElement
    public Propietario setAutos( List<Auto> autos ) {
    	this.autos = autos;
    	return this;
    }
    
    @XmlElement
    public List<Auto> getAutos() {
    	return this.autos;
    }
    
    @Override
    public String toString() {
        JSONObject json = new JSONObject(super.toString());
        
        json.put("tipo", this.tipo);
        
        return json.toString(4);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) 
            return false;
        final Propietario other = (Propietario) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return true;
    }
    
}
