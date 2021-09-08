package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dao_abstract.IAutoDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import dao_abstract.exceptions.UpdateEntityException;
import model.Auto;
import rest.RESTAuto;

@ExtendWith(MockitoExtension.class)

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAutoController {
  
	private final IAutoDAO dao;
	
	private List<Auto> autoLista;
	private Auto auto;
	
	@InjectMocks
	private RESTAuto controller;
	
	public TestAutoController ( @Mock IAutoDAO dao ) {
		this.dao = dao;
	}
	
    
    @BeforeAll
    public void prepareMock() throws ReadEntityException, DeleteEntityException, CreateEntityException, UpdateEntityException {
    	this.crearObjetosNecesariosParaMocking();
    	this.prepareMockExceptions();
    	this.prepareMockRead();
    }
    
    private void crearObjetosNecesariosParaMocking() {
		this.auto = new Auto("AA 23 LF", "Marca", "modelo", null);
		
		this.autoLista = new ArrayList<>(1);
	}

	private void prepareMockRead() {
		Mockito.when( this.dao.readOne( Mockito.isNull() ) ).thenThrow( ReadEntityException.class );  
		
		Mockito.when( this.dao.readAll() ).thenReturn( this.autoLista );
    	Mockito.when( this.dao.readOne( Mockito.anyString() ) ).thenReturn( this.auto );
	}
	
	private void prepareMockExceptions() {
		Mockito.doThrow( DeleteEntityException.class ).when( this.dao ).delete( Mockito.isNull() );
    	Mockito.doThrow( CreateEntityException.class ).when( this.dao ).create( Mockito.isNull() );
    	Mockito.when( this.dao.update( this.auto ) ).thenThrow( UpdateEntityException.class );    	
	}
    
    @Test
    @DisplayName("Buscar un Null - ReadEntityException")
    public void readEntityException () {
    	assertThrows( ReadEntityException.class, () -> {
    		this.controller.getAutoById(null);
    	});
    }
    
    @Test
    @DisplayName( "Insertar un Null - CreateEntityException" )
    public void createEntityException() {
    	assertThrows( CreateEntityException.class, () -> {
    		this.controller.postAuto(null);
    	});
    }
    
    @Test
    @DisplayName( "Update de un Null - UpdateEntityException" )
    public void updateEntityException() {
    	assertThrows( UpdateEntityException.class, () -> {
    		this.controller.updateAuto(this.a, "AA 23 LF");
    	});
    }
    
    @Test
    @DisplayName( "Lectura Correcta de una entidad" )
    public void readEntity() throws ReadEntityException {
    	Auto returned = this.controller.getAutoById("AA 23 LF");
    	assertEquals( this.a, returned );
    }
    
}