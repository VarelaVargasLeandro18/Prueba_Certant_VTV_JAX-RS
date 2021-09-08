package model.inspeccion;

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
@Table(name="ESTADOS_INSPECCION")
@XmlRootElement
public class EstadoInspeccion implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    @Column(name="estado", nullable=false)
    private String estado;
    
    @Column(name="veracidad", nullable=false)
    private int veracidad;
    
    public EstadoInspeccion () {}

    public EstadoInspeccion(
    		String estado, 
    		int veracidad
    ) {
        this.estado = estado;
        this.veracidad = veracidad;
    }
    
    public EstadoInspeccion( 
    		EstadoInspeccion estadoInspeccion
    ) {
        this.Id = estadoInspeccion.Id;
        this.estado = estadoInspeccion.estado;
        this.veracidad = estadoInspeccion.veracidad;
    }

    //<editor-fold desc="Getters" defaultstate="collapsed">
    @XmlElement
    public Long getId() {
        return Id;
    }

    @XmlElement
    public String getEstado() {
        return estado;
    }

    @XmlElement
    public int getVeracidad() {
        return veracidad;
    }
    //</editor-fold>

    //<editor-fold desc="Setters" defaultstate="collapsed">
    public EstadoInspeccion setId(Long Id) {
        this.Id = Id;
        return this;
    }

    public EstadoInspeccion setEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public EstadoInspeccion setVeracidad(int veracidad) {
        this.veracidad = veracidad;
        return this;
    }
    //</editor-fold>
    
    @Override 
    public String toString() {
        JSONObject json = new JSONObject();
        
        json.put( "Id", this.Id );
        json.put( "estado", this.estado );
        
        return json.toString(4);        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.Id);
        hash = 53 * hash + Objects.hashCode(this.estado);
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
        final EstadoInspeccion other = (EstadoInspeccion) obj;
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        return true;
    }
    
    
    
}
