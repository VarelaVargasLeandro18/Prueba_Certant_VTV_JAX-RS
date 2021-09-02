package model.personas;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
@Entity
@Table(name="TIPOS_PROPIETARIO")
@XmlRootElement
public class TipoPropietario implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    @Column(name="tipo", nullable=false)
    private String tipo;

    public TipoPropietario() {}
    
    public TipoPropietario(String tipo) {
        this.tipo = tipo;
    }
    
    //<editor-fold desc="Getters" defaultstate="collapsed">
    @XmlElement
    public Long getId() {
        return Id;
    }
    
    @XmlElement
    public String getTipo() {
        return tipo;
    }
    //</editor-fold>

    //<editor-fold desc="Setters" defaultstate="collapsed">
    public TipoPropietario setId(Long Id) {
        this.Id = Id;
        return this;
    }
    
    public TipoPropietario setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }
    //</editor-fold>    
    
    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        
        json.put( "Id", this.Id );
        json.put( "Tipo", this.tipo );
        
        return json.toString(4);        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.Id);
        hash = 47 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoPropietario other = (TipoPropietario) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        return true;
    }
    
}
