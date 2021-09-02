package model.inspeccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.json.JSONObject;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
@Entity
@Table(name="MEDICIONES")
@XmlRootElement
@XmlSeeAlso(EstadoInspeccion.class)
public class Medicion implements Serializable, IEstadoGeneral {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    @ManyToOne
    @JoinColumn(name="sist_de_frenos", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_SistDeFrenos"), nullable=false)
    private EstadoInspeccion sistemaDeFrenos;
    
    @ManyToOne
    @JoinColumn(name="suspension", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Suspension"), nullable=false)
    private EstadoInspeccion suspension;
    
    @ManyToOne
    @JoinColumn(name="direccion", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Direccion"), nullable=false)
    private EstadoInspeccion direccion;
    
    @ManyToOne
    @JoinColumn(name="tren_delantero", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_TrenDelantero"), nullable=false)
    private EstadoInspeccion trenDelantero;

    public Medicion() {
    }

    public Medicion(Long Id, EstadoInspeccion sistemaDeFrenos, EstadoInspeccion suspension, EstadoInspeccion direccion, EstadoInspeccion trenDelantero) {
        this.Id = Id;
        this.sistemaDeFrenos = sistemaDeFrenos;
        this.suspension = suspension;
        this.direccion = direccion;
        this.trenDelantero = trenDelantero;
    }
    
    //<editor-fold desc="Getters" defaultstate="collapsed">
    @XmlElement
    public Long getId() {
        return Id;
    }

    @XmlElement
    public String getSistemaDeFrenos() {
        return sistemaDeFrenos.getEstado();
    }

    @XmlElement
    public String getSuspension() {
        return suspension.getEstado();
    }

    @XmlElement
    public String getDireccion() {
        return direccion.getEstado();
    }

    @XmlElement
    public String getTrenDelantero() {
        return trenDelantero.getEstado();
    }
    //</editor-fold>

    //<editor-fold desc="Setters" defaultstate="collapsed">
    public Medicion setId(Long Id) {
        this.Id = Id;
        return this;
    }
    
    public Medicion setSistemaDeFrenos(EstadoInspeccion sistemaDeFrenos) {
        this.sistemaDeFrenos = sistemaDeFrenos;
        return this;
    }

    public Medicion setSuspension(EstadoInspeccion suspension) {
        this.suspension = suspension;
        return this;
    }

    public Medicion setDireccion(EstadoInspeccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public Medicion setTrenDelantero(EstadoInspeccion trenDelantero) {
        this.trenDelantero = trenDelantero;
        return this;
    }
    //</editor-fold>
    
    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        
        json.put( "Id", this.Id );
        json.put( "sistemaDeFrenos", this.sistemaDeFrenos );
        json.put( "suspension", this.suspension );
        json.put( "direccion", this.direccion );
        json.put( "trenDelantero", this.trenDelantero );
        
        return json.toString(4);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.Id);
        hash = 41 * hash + Objects.hashCode(this.sistemaDeFrenos);
        hash = 41 * hash + Objects.hashCode(this.suspension);
        hash = 41 * hash + Objects.hashCode(this.direccion);
        hash = 41 * hash + Objects.hashCode(this.trenDelantero);
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
        final Medicion other = (Medicion) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        if (!Objects.equals(this.sistemaDeFrenos, other.sistemaDeFrenos)) {
            return false;
        }
        if (!Objects.equals(this.suspension, other.suspension)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.trenDelantero, other.trenDelantero)) {
            return false;
        }
        return true;
    }

    @Override
    public EstadoInspeccion obtenerEstadoGeneral() {
        List<EstadoInspeccion> estados = new ArrayList<>(4);
        estados.add(this.direccion);
        estados.add(this.sistemaDeFrenos);
        estados.add(this.suspension);
        estados.add(this.trenDelantero);
        
        int auxiliarVeracidad = Integer.MIN_VALUE;
        EstadoInspeccion ret = null;
        
        for( EstadoInspeccion estado : estados ) {
            
            if ( estado != null && estado.getVeracidad() > auxiliarVeracidad ) {
                auxiliarVeracidad = estado.getVeracidad();
                ret = estado;
            }                
            
        }        
        
        return ret;   
    }
}
