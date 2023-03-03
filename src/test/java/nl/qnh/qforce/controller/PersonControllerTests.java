package nl.qnh.qforce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTests {

	@Mock
	private PersonService personService;
	
	@InjectMocks
	private PersonController personController;
	
	@Test
	void findPersonByIdTest() {
		Person person = mock(Person.class);
		
		when(personService.get(1L)).thenReturn(Optional.of(person));
		
		assertEquals(HttpStatus.OK, personController.findPersonById(1).getStatusCode());
	}

	@Test
	void findPersonByIdNotFoundTest() {
		Person person = mock(Person.class);

		when(personService.get(999L)).thenReturn(Optional.empty());

		assertEquals(HttpStatus.NOT_FOUND, personController.findPersonById(999).getStatusCode());
	}
	
//	@Test
//	void searchPeopleByNameTest() {
//		List<Person> personsList = List.of(mock(Person.class));
//
//		when(personService.search("Luke")).thenReturn(personsList);
//		assertEquals(HttpStatus.OK, personController.searchPeopleByName("Luke").getStatusCode());
//	}
}
