package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dao.AutoDAO;
import dao_abstract.CreateEntityException;
import dao_abstract.DeleteEntityException;
import dao_abstract.ReadEntityException;
import dao_abstract.UpdateEntityException;
import model.Auto;
import rest.RESTAuto;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAutoController {
  
    private AutoDAO dao;
	
	private List<Auto> al = new ArrayList<>(1);
	private Auto a = new Auto("AA 23 LF", "Marca", "modelo", null);
	
	private RESTAuto controller;
    
    @BeforeAll
    public void prepareMock() throws ReadEntityException, DeleteEntityException, CreateEntityException, UpdateEntityException {
    	this.dao = Mockito.mock(AutoDAO.class);
    	this.controller = new RESTAuto(this.dao);
    	this.al.add(this.a);
    	
    	
    	Mockito.when( this.dao.readOne( Mockito.isNull() ) ).thenThrow( ReadEntityException.class );  
    	Mockito.doThrow( DeleteEntityException.class ).when( this.dao ).delete( Mockito.isNull() );
    	Mockito.doThrow( CreateEntityException.class ).when( this.dao ).create( Mockito.isNull() );
    	Mockito.when( this.dao.update( this.a ) ).thenThrow( UpdateEntityException.class );
    	
    	Mockito.when( this.dao.readAll() ).thenReturn( this.al );
    	Mockito.when( this.dao.readOne( Mockito.anyString() ) ).thenReturn( this.a );
    	
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