package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dao_abstract.IInspeccionDAO;
import dao_abstract.IPropietarioDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import dao_abstract.exceptions.UpdateEntityException;
import model.Auto;
import model.inspeccion.EstadoInspeccion;
import model.inspeccion.Inspeccion;
import model.inspeccion.Medicion;
import model.inspeccion.Observacion;
import model.personas.Inspector;
import model.personas.Propietario;
import model.personas.TipoPropietario;
import rest.RESTInspeccion;

@ExtendWith( MockitoExtension.class )
@TestInstance(Lifecycle.PER_CLASS)
public class TestInspeccionController {
	

	private final IInspeccionDAO dao;	
	private final IPropietarioDAO pDao;
	
	private List<Inspeccion> listaInspecciones;
	private Inspeccion inspeccion;
	private Propietario propietario;
	
	@InjectMocks
	private RESTInspeccion controller;
	
	public TestInspeccionController( @Mock IInspeccionDAO dao, @Mock IPropietarioDAO pDao ) {
		this.dao = dao;
		this.pDao = pDao;
	}
	
	// =========================================================================
	@BeforeAll
	public void prepareMocks() throws ReadEntityException, DeleteEntityException, CreateEntityException, UpdateEntityException {
    	this.prepareNecessaryObjects();
    	this.prepareMockExceptions();
    	
    	this.prepareMockRead();
    	this.prepareMockEspecialMethods();
    }
	
	private void prepareNecessaryObjects() {
		this.listaInspecciones = new ArrayList<Inspeccion>(1);
		
		LocalDateTime fecha = LocalDateTime.now();
		Inspector inspector = new Inspector(
				"usuario",
				"contrasenia",
				11111111111l,
				"Juan",
				"Perez",
				fecha,
				"email@email.com",
				"+54 1111111111"
		);
		TipoPropietario tipo = new TipoPropietario( 
				"Excento" 
		);
		this.propietario = new Propietario(
				tipo,
				11111111111l,
				"Juan",
				"Perez",
				fecha,
				"email@email.com",
				"+54 1111111111"
		);
		Auto inspeccionado = new Auto(
				"AA 000 AA",
				"Marca",
				"Modelo",
				this.propietario
		);
		EstadoInspeccion ok = new EstadoInspeccion(
				"ok",
				0
		);
		Observacion observacion = new Observacion(
				1l,
				ok,
				ok,
				ok,
				ok,
				ok,
				ok,
				ok
		);
		Medicion medicion = new Medicion(
				1l,
				ok,
				ok,
				ok,
				ok
		);
		
		this.inspeccion = new Inspeccion( 
				fecha,
				tipo,
				inspector,
				inspeccionado,
				observacion,
				medicion
		);
		this.listaInspecciones.add(this.inspeccion);
	}
	
	private void prepareMockRead() throws ReadEntityException {
		Mockito.when( this.dao.readOne( Mockito.isNull() ) ).thenThrow( ReadEntityException.class );  
		
		Mockito.when( this.dao.readAll() ).thenReturn( this.listaInspecciones );
    	Mockito.when( this.dao.readOne( Mockito.anyLong() ) ).thenReturn( this.inspeccion );
    	
    	Mockito.when( this.pDao.readOne( Mockito.anyLong() ) ).thenReturn( this.propietario );
	}

	private void prepareMockExceptions() throws DeleteEntityException, CreateEntityException, UpdateEntityException {
		Mockito.doThrow( DeleteEntityException.class ).when( this.dao ).delete( Mockito.isNull() );
    	Mockito.doThrow( CreateEntityException.class ).when( this.dao ).create( Mockito.isNull() );
    	Mockito.when( this.dao.update( this.inspeccion ) ).thenThrow( UpdateEntityException.class );    	
	}
	
	private void prepareMockEspecialMethods() throws ReadEntityException {
		Mockito.when( this.dao.obtenerInspeccionesDePersonaSiTieneMasDeUnAuto( this.propietario ) ).thenReturn(this.listaInspecciones);
		Mockito.when( this.dao.obtenerInspeccionesUltimosTresDias() ).thenReturn( this.listaInspecciones );	
	}
	// =========================================================================
	
	@Test
    @DisplayName("Buscar un Null - ReadEntityException")
    public void readEntityException () {
    	assertThrows( ReadEntityException.class, () -> {
    		this.controller.get(null);
    	});
    }
	
    @Test
    @DisplayName( "Insertar un Null - CreateEntityException" )
    public void createEntityException() {
    	assertThrows( CreateEntityException.class, () -> {
    		this.controller.post(null);
    	});
    }
    
    @Test
    @DisplayName( "Update de un Null - UpdateEntityException" )
    public void updateEntityException() {
    	Long Id = 1111111111l;
    	assertThrows( UpdateEntityException.class, () -> {
    		this.controller.update(null, Id);
    	});
    }
    
    @Test
    @DisplayName( "Lectura Correcta de una entidad" )
    public void readEntity() throws ReadEntityException {
    	Long Id = 1111111111l;
    	Inspeccion returned = this.controller.get(Id);
    	assertEquals( this.inspeccion, returned );
    }
	
    @Test
    @DisplayName( "Lectura de Inspecciones en los Ultimos Tres Dias" )
    public void readInspeccionesUltimosTresDias() throws ReadEntityException {
    	List<Inspeccion> returned = this.controller.inspeccionesUltimosTresDias();
    	assertEquals( this.listaInspecciones, returned );
    }
    
    @Test
    @DisplayName( "Lectura de Inspecciones de una persona con Mas de Un Auto" )
    public void readInspeccionesDePersonaConMasDeUnAuto() throws ReadEntityException {
    	List<Inspeccion> returned = this.controller.inspeccionesDePersonaConMasDeUnAuto( 1l );
    	assertEquals( this.listaInspecciones, returned );
    }
    
}
