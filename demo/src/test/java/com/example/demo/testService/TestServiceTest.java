package com.example.demo.testService;

import com.example.demo.model.Test;
import com.example.demo.repository.TestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestServiceTest {

    @Mock
    private TestRepository testRepository;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Test verileri
        List<Test> testList = new ArrayList<>();
        testList.add(new Test(1L, "Ali", "Veli", "password1"));
        testList.add(new Test(2L, "Ayşe", "Fatma", "password2"));

        // Mock davranışı
        when(testRepository.findAll()).thenReturn(testList);

        // Test
        List<Test> result = testService.findAll();
        assertEquals(2, result.size());
        verify(testRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        // Test verisi
        Test test = new Test(1L, "Ali", "Veli", "password1");

        // Mock davranışı
        when(testRepository.findById(1L)).thenReturn(Optional.of(test));

        // Test
        Optional<Test> result = testService.findById(1L);
        assertNotNull(result);
        assertEquals("Ali", result.get().getName());
        verify(testRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {

        Test test = new Test(null, "Ali", "Veli", "password1");


        when(testRepository.save(any(Test.class))).thenReturn(test);


        Test result = testService.save(test);
        assertNotNull(result);
        assertEquals("Ali", result.getName());
        verify(testRepository, times(1)).save(test);
    }

    @Test
    public void testUpdate() {
        // Test verisi
        Test test = new Test(1L, "Ali", "Veli", "password1");

        // Mock davranışı
        when(testRepository.save(any(Test.class))).thenReturn(test);

        // Test
        Test result = testService.update(1L, test);
        assertNotNull(result);
        assertEquals("Ali", result.getName());
        verify(testRepository, times(1)).save(test);
    }

    @Test
    public void testDeleteById() {
        // Test
        testService.deleteById(1L);
        verify(testRepository, times(1)).deleteById(1L);
    }
}