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

import org.json.JSONObject;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
@Entity
@Table(name="OBSERVACIONES")
public class Observacion implements Serializable, IEstadoGeneral {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    @ManyToOne
    @JoinColumn(name="luces", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Luces"), nullable=false)
    private EstadoInspeccion luces;
    
    @ManyToOne
    @JoinColumn(name="patente", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Patente"), nullable=false)
    private EstadoInspeccion patente;
    
    @ManyToOne
    @JoinColumn(name="espejos", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Espejos"), nullable=false)
    private EstadoInspeccion espejos;
    
    @ManyToOne
    @JoinColumn(name="chasis", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Chasis"), nullable=false)
    private EstadoInspeccion chasis;
    
    @ManyToOne
    @JoinColumn(name="vidrios", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Vidrios"), nullable=false)
    private EstadoInspeccion vidrios;
    
    @ManyToOne
    @JoinColumn(name="seguridad", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Seguridad"), nullable=false)
    private EstadoInspeccion seguridad;
    
    @ManyToOne
    @JoinColumn(name="emergencia", referencedColumnName="Id", foreignKey=@ForeignKey(name="FK_Emergencia"), nullable=false)
    private EstadoInspeccion emergencia;

    public Observacion() {}

    public Observacion(
    		Long Id, 
    		EstadoInspeccion luces, 
    		EstadoInspeccion patente, 
    		EstadoInspeccion espejos, 
    		EstadoInspeccion chasis, 
    		EstadoInspeccion vidrios, 
    		EstadoInspeccion seguridad, 
    		EstadoInspeccion emergencia
    ) {
        this.Id = Id;
        this.luces = luces;
        this.patente = patente;
        this.espejos = espejos;
        this.chasis = chasis;
        this.vidrios = vidrios;
        this.seguridad = seguridad;
        this.emergencia = emergencia;
    }

    //<editor-fold desc="Getters" defaultstate="collapsed">
    @XmlElement
    public Long getId() {
        return Id;
    }

    @XmlElement
    public String getLuces() {
        return luces.getEstado();
    }

    @XmlElement
    public String getPatente() {
        return patente.getEstado();
    }

    @XmlElement
    public String getEspejos() {
        return espejos.getEstado();
    }

    @XmlElement
    public String getChasis() {
        return chasis.getEstado();
    }

    @XmlElement
    public String getVidrios() {
        return vidrios.getEstado();
    }

    @XmlElement
    public String getSeguridad() {
        return seguridad.getEstado();
    }

    @XmlElement
    public String getEmergencia() {
        return emergencia.getEstado();
    }
    //</editor-fold>
    
    //<editor-fold desc="Setters" defaultstate="collapsed">
    public Observacion setId(Long Id) {
        this.Id = Id;
        return this;
    }
    
    public Observacion setLuces(EstadoInspeccion luces) {
        this.luces = luces;
        return this;
    }

    public Observacion setPatente(EstadoInspeccion patente) {
        this.patente = patente;
        return this;
    }

    public Observacion setEspejos(EstadoInspeccion espejos) {
        this.espejos = espejos;
        return this;
    }

    public Observacion setChasis(EstadoInspeccion chasis) {
        this.chasis = chasis;
        return this;
    }

    public Observacion setVidrios(EstadoInspeccion vidrios) {
        this.vidrios = vidrios;
        return this;
    }

    public Observacion setSeguridad(EstadoInspeccion seguridad) {
        this.seguridad = seguridad;
        return this;
    }

    public Observacion setEmergencia(EstadoInspeccion emergencia) {
        this.emergencia = emergencia;
        return this;
    }
    //</editor-fold>
    
    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        
        json.put( "Id", this.Id );
        json.put( "luces", this.luces );
        json.put( "patente", this.patente );
        json.put( "espejos", this.espejos );
        json.put( "chasis", this.chasis );
        json.put( "vidrios", this.vidrios );
        json.put( "seguridad", this.seguridad );
        json.put( "emergencia", this.emergencia );
        
        return json.toString(4);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.Id);
        hash = 71 * hash + Objects.hashCode(this.luces);
        hash = 71 * hash + Objects.hashCode(this.patente);
        hash = 71 * hash + Objects.hashCode(this.espejos);
        hash = 71 * hash + Objects.hashCode(this.chasis);
        hash = 71 * hash + Objects.hashCode(this.vidrios);
        hash = 71 * hash + Objects.hashCode(this.seguridad);
        hash = 71 * hash + Objects.hashCode(this.emergencia);
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
        final Observacion other = (Observacion) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        if (!Objects.equals(this.luces, other.luces)) {
            return false;
        }
        if (!Objects.equals(this.patente, other.patente)) {
            return false;
        }
        if (!Objects.equals(this.espejos, other.espejos)) {
            return false;
        }
        if (!Objects.equals(this.chasis, other.chasis)) {
            return false;
        }
        if (!Objects.equals(this.vidrios, other.vidrios)) {
            return false;
        }
        if (!Objects.equals(this.seguridad, other.seguridad)) {
            return false;
        }
        if (!Objects.equals(this.emergencia, other.emergencia)) {
            return false;
        }
        return true;
    }
    
    @Override
    public EstadoInspeccion obtenerEstadoGeneral() {
        List<EstadoInspeccion> estados = new ArrayList<>(7);
        estados.add(this.chasis);
        estados.add(this.emergencia);
        estados.add(this.espejos);
        estados.add(this.luces);
        estados.add(this.patente);
        estados.add(this.seguridad);
        estados.add(this.vidrios);
        
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