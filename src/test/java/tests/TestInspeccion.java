 package tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.junit.jupiter.api.TestMethodOrder;

import dao.AutoDAO;
import dao.EstadoInspeccionDAO;
import dao.InspeccionDAO;
import dao.InspectorDAO;
import dao.MedicionDAO;
import dao.ObservacionDAO;
import dao.PropietarioDAO;
import dao.TipoPropietarioDAO;
import dao_abstract.CreateEntityException;
import dao_abstract.DeleteEntityException;
import dao_abstract.ReadEntityException;
import model.Auto;
import model.inspeccion.EstadoInspeccion;
import model.inspeccion.Inspeccion;
import model.inspeccion.Medicion;
import model.inspeccion.Observacion;
import model.personas.Inspector;
import model.personas.Propietario;
import model.personas.TipoPropietario;

/**
 * Test de funcionamiento de DAO de Inspecciones. 
 * @author Varela Vargas Leandro Gastón
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestInspeccion {
    
    private InspectorDAO inspectorDAO;
    private TipoPropietarioDAO tipoPropietarioDAO;
    private PropietarioDAO propietarioDAO;
    private AutoDAO autoDAO;
    private InspeccionDAO inspeccionDAO;
    private MedicionDAO medicionDAO;
    private ObservacionDAO observacionDAO;
    private EstadoInspeccionDAO estadoInspeccionDAO;
    
    private Inspector inspector;
    private Propietario propietario;
    private Auto autoUno;
    private Auto autoDos;
    private TipoPropietario tipoPropietario;
    private Inspeccion inspeccion;
    private Medicion medicion;
    private Observacion observacion;
    private EstadoInspeccion apto;
    private EstadoInspeccion condicional;
    private EstadoInspeccion rechazado;
    private EstadoInspeccion enProceso;
    
    //<editor-fold desc="BeforeAll creación previa de entidades necesarias." defaultstate="collapsed">
    @BeforeAll
    public void creacionDeInspector() {
        try {           
        	this.inspeccionDAO = new InspeccionDAO();
            this.inspectorDAO = Mockito.mock(InspectorDAO.class);
            
            if ( (this.inspector = this.inspectorDAO.readOne(11111111111l)) != null ) return;
            
            this.inspector = new Inspector( 
                    "pedlop", 
                    "pedlop123", 
                    11111111111l, 
                    "Pedro", 
                    "Lopez", 
                    LocalDateTime.of(1989, Month.MARCH, 15, 0, 0),
                    "pedlop@mail.com",
                    "+54 1111111111"
            );
            Mockito.when(this.inspectorDAO.readOne( 11111111111l )).thenReturn(this.inspector);
            Mockito.doNothing().when(this.inspectorDAO).create(inspector);
            Mockito.doThrow(CreateEntityException.class).when(this.inspectorDAO).create( Mockito.isNull() );
            Mockito.doNothing().when(this.inspectorDAO).delete( inspector );
            //Mockito.doThrow(CreateEntityException.class).when(this.inspectorDAO).delete(Mockito.isNull());
        } catch ( CreateEntityException | ReadEntityException | DeleteEntityException ex ) {
            fail(ex);
        }
    }
    
    @BeforeAll
    public void creacionDeAutomoviles() {
       try {
            this.tipoPropietarioDAO = new TipoPropietarioDAO();
            this.propietarioDAO = new PropietarioDAO();
           
            if ( ( this.tipoPropietario = this.tipoPropietarioDAO.buscarPorTipo("Exento") ) == null ) {            
                this.tipoPropietario = new TipoPropietario( "Exento" );
                this.tipoPropietarioDAO.create(this.tipoPropietario);
            }
           
            if ( (this.propietario = this.propietarioDAO.readOne( 22222222222l )) == null ) {
                this.propietarioDAO = new PropietarioDAO();
                this.propietario = new Propietario(
                        this.tipoPropietario,
                        22222222222l,
                        "Juan",
                        "Perez",
                        LocalDateTime.of(1976, Month.DECEMBER, 11, 0, 0),
                        "juanp@mail.com",
                        "+54 1111111111"
                );
                this.propietarioDAO.create(this.propietario);
            }
           
            this.autoDAO = new AutoDAO();
           
            if ( (this.autoUno = this.autoDAO.readOne("Imposible1")) == null ) {
                this.autoUno = new Auto(
                        "Imposible1",
                        "marca",
                        "modelo",
                        this.propietario
                );
                this.autoDAO.create(this.autoUno);
            }
           
            if ( (this.autoDos = this.autoDAO.readOne("Imposible2")) != null ) return;
           
            this.autoDos = new Auto(
                "Imposible2",
                "marca",
                "modelo",
                this.propietario
            );
            this.autoDAO.create(this.autoDos);
           
        } catch (CreateEntityException | ReadEntityException ex) {
            fail(ex);
        }
    }
    
    @BeforeAll
    public void creacionEstadosInspeccion () {
        try {
            this.estadoInspeccionDAO = new EstadoInspeccionDAO();
            
            if ( ( this.apto = this.estadoInspeccionDAO.leerPorEstado("Apto") ) == null ) {
                this.apto = new EstadoInspeccion("Apto", 0);
                this.estadoInspeccionDAO.create(this.apto);
            }
            
            if ( ( this.condicional = this.estadoInspeccionDAO.leerPorEstado("Condicional") ) == null ) {
                this.condicional = new EstadoInspeccion("Condicional", 1);
                this.estadoInspeccionDAO.create(this.condicional);
            }
            
            if ( ( this.rechazado = this.estadoInspeccionDAO.leerPorEstado("Rechazado") ) == null ) {
                this.rechazado = new EstadoInspeccion("Rechazado", 2);
                this.estadoInspeccionDAO.create(this.rechazado);
            }
            
            if ( ( this.enProceso = this.estadoInspeccionDAO.leerPorEstado("En Proceso") ) == null ) {
                this.enProceso = new EstadoInspeccion("En Proceso", 0);
                this.estadoInspeccionDAO.create(this.enProceso);
            }
            
        } catch ( CreateEntityException | ReadEntityException ex ) {
            fail(ex);
        }      
    }
    // </editor-fold>
    
    // <editor-fold desc="Tests">
    @Test
    @DisplayName("Comienzo de una inspección.")
    @Order(1)
    public void empezarInspeccion() {
        
        try {
            this.inspeccionDAO = new InspeccionDAO();
            this.inspeccion = new Inspeccion(
                    LocalDateTime.now(),
                    this.tipoPropietario,
                    this.inspector,
                    this.autoUno,
                    null,
                    null
            ).setEstado(this.enProceso);
            
            this.inspeccionDAO.create( this.inspeccion );
            
            assertNotNull( this.inspeccion.getNumero() );
        } catch ( CreateEntityException ex ) {
            fail(ex);
        }
        
    }
    
    @Test
    @DisplayName("Creacion de Observacion y Medicion y persistencia de Inspeccion.")
    @Order(2)
    public void crearObservacionYMedicionYPersistirInspeccion() {
        
        try {
            this.observacionDAO = new ObservacionDAO();
            this.medicionDAO = new MedicionDAO();
            
            this.observacion = new Observacion()
                    .setChasis( this.apto )
                    .setEmergencia( this.apto )
                    .setEspejos( this.apto )
                    .setLuces( this.apto )
                    .setPatente( this.apto )
                    .setSeguridad( this.apto )
                    .setVidrios( this.apto );
            this.observacionDAO.create(this.observacion);
            
            this.medicion = new Medicion()
                    .setDireccion( this.condicional )
                    .setSistemaDeFrenos( this.apto )
                    .setSuspension( this.apto )
                    .setTrenDelantero( this.rechazado );
            this.medicionDAO.create(this.medicion);
            
            this.inspeccion.setObservacion(this.observacion);
            this.inspeccion.setMedicion(this.medicion);
            this.inspeccionDAO.create(this.inspeccion);
            
            assertAll(
                    () -> {assertNotNull( this.observacion.getId() );},
                    () -> {assertNotNull( this.medicion.getId() );},
                    () -> {assertEquals(this.inspeccion.getEstado(), "Rechazado");}
            );
        } catch ( CreateEntityException ex ) {
            fail(ex);
        }
        
    }
    //</editor-fold>
    
    //<editor-fold desc="AfterAll - Eliminado de Entidades Utilizadas" defaultstate="collapsed">
    @AfterAll
    public void borrarEntidades() {
        try {
            this.inspeccionDAO.delete(this.inspeccion);
            this.medicionDAO.delete(this.medicion);
            this.observacionDAO.delete(this.observacion);
            this.autoDAO.delete(this.autoUno);
            this.autoDAO.delete(this.autoDos);
            this.propietarioDAO.delete(this.propietario);
            this.inspectorDAO.delete(this.inspector);
        } catch ( DeleteEntityException ex ) {
            fail(ex);
        }
    }
    //</editor-fold>
    
}
