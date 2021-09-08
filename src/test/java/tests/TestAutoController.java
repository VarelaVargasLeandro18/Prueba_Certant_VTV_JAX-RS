package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import dao_abstract.IAutoDAO;
import dao_abstract.IEstadoInspeccionDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import dao_abstract.exceptions.UpdateEntityException;
import model.Auto;
import model.inspeccion.EstadoInspeccion;
import rest.RESTAuto;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TestAutoController {
  
	private final IAutoDAO dao;
	private final IEstadoInspeccionDAO eiDao;
	
	private List<Auto> autoLista;
	private Auto auto;
	private EstadoInspeccion apto;
	
	@InjectMocks
	private RESTAuto controller;
	
	public TestAutoController ( @Mock IAutoDAO dao, @Mock IEstadoInspeccionDAO eiDao ) {
		this.dao = dao;
		this.eiDao = eiDao;
	}
    
	//==========================================================================
    @BeforeAll
    public void prepareMocks() throws ReadEntityException, DeleteEntityException, CreateEntityException, UpdateEntityException {
    	this.prepareNecessaryObjects();
    	this.prepareMockExceptions();
    	
    	this.prepareMockRead();
    	this.prepareMockEspecialMethods();
    }
    
    private void prepareNecessaryObjects() {
		this.auto = new Auto("AA 23 LF", "Marca", "modelo", null);
		this.apto = new EstadoInspeccion( "Apto", 0 );
		this.autoLista = new ArrayList<>(1);
	}

	private void prepareMockRead() throws ReadEntityException {
		Mockito.when( this.dao.readOne( Mockito.isNull() ) ).thenThrow( ReadEntityException.class );  
		
		Mockito.when( this.dao.readAll() ).thenReturn( this.autoLista );
    	Mockito.when( this.dao.readOne( Mockito.anyString() ) ).thenReturn( this.auto );
	}
	
	private void prepareMockExceptions() throws DeleteEntityException, CreateEntityException, UpdateEntityException {
		Mockito.doThrow( DeleteEntityException.class ).when( this.dao ).delete( Mockito.isNull() );
    	Mockito.doThrow( CreateEntityException.class ).when( this.dao ).create( Mockito.isNull() );
    	Mockito.when( this.dao.update( this.auto ) ).thenThrow( UpdateEntityException.class );    	
	}
	
	private void prepareMockEspecialMethods() throws ReadEntityException {
		Mockito.when( this.eiDao.leerPorEstado( "Apto" ) ).thenReturn( this.apto );
		
		Mockito.when( this.dao.buscarPorCondicion(this.apto) ).thenReturn(this.autoLista);
		Mockito.when( this.dao.chequeoVencimiento(this.apto) ).thenReturn( new ArrayList<Auto>() );
		Mockito.when( this.dao.inspeccionadosSemana() ).thenReturn( this.autoLista );
	}
	
	//==========================================================================
    
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
    	assertThrows( UpdateEntityException.class, () -> {
    		this.controller.update(this.auto, "AA 23 LF");
    	});
    }
    
    @Test
    @DisplayName( "Lectura Correcta de una entidad" )
    public void readEntity() throws ReadEntityException {
    	Auto returned = this.controller.get("AA 23 LF");
    	assertEquals( this.auto, returned );
    }
    
    @Test
    @DisplayName( "Lectura de Autos Vencidos" )
    public void readAutosVencidos() throws ReadEntityException {
    	List<Auto> vencidos = this.controller.vencidos();
    	assertTrue( vencidos.isEmpty() );
    }
    
}