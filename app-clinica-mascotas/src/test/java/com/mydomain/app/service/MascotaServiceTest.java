package com.mydomain.app.service;

import com.mydomain.app.repository.MascotaRepository;
import com.mydomain.app.model.Mascota;
import com.mydomain.app.model.Propietario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MascotaServiceTest {

    @InjectMocks
    MascotaService mascotaService;

    @Mock
    MascotaRepository mascotaRepository;

    @Mock
    ExternalService externalService;

//Registro de mascota
    @Test
    void registrarMascota() {

        Propietario propietario = new Propietario("Alfredo García", "San José", "88589099");
        Mascota mascota = new Mascota();
        mascota.setNombre("Bola de Nieve");
        mascota.setPropietario(propietario);


        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(true);
        when(externalService.verificarRegistroMunicipal(any(Mascota.class))).thenReturn(true);
        when(mascotaRepository.findById(any())).thenReturn(Optional.empty());
        when(mascotaRepository.guardar(any(Mascota.class))).thenReturn(mascota);


        Mascota registrada = mascotaService.registrarMascota(mascota);


        assertNotNull(registrada, "La mascota es null.");
        assertEquals("Bola de Nieve", registrada.getNombre(), "El nombre de la mascota registrada no coincide.");
    }

    @Test
    void registrarMascotaNombreNull() {

        Propietario propietario = new Propietario("Alfredo García", "San José", "88589099");
        Mascota mascota = new Mascota();
        mascota.setPropietario(propietario);


        Exception exception = assertThrows(IllegalArgumentException.class, () -> mascotaService.registrarMascota(mascota));


        assertEquals("El nombre de la mascota no puede estar vacío.",exception.getMessage());
    }

    @Test
    void registrarMascotaSinNombre() {

        Propietario propietario = new Propietario("Alfredo García", "San José", "88589099");
        Mascota mascota = new Mascota();
        mascota.setPropietario(propietario);
        mascota.setNombre(" ");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mascotaService.registrarMascota(mascota));

        assertEquals("El nombre de la mascota no puede estar vacío.",exception.getMessage());
    }

    @Test
    void registrarMascotaPropietarioNull() {

        Mascota mascota = new Mascota();
        mascota.setNombre("Bola de Nieve");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mascotaService.registrarMascota(mascota));

        assertEquals("La mascota debe tener un propietario.",exception.getMessage());
    }

    @Test
    void registrarMascotaPropietarioSinNombre() {

        Propietario propietario = new Propietario("", "San José", "88589099");
        Mascota mascota = new Mascota();
        mascota.setNombre("Bola de Nieve");
        mascota.setPropietario(propietario);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mascotaService.registrarMascota(mascota));

        assertEquals("La mascota debe tener un propietario.",exception.getMessage());
    }

    @Test
    void registrarMascotaPropiertarioTelefonoNull(){

        Propietario propietario = new Propietario("Alfredo García", "San José", null);
        Mascota mascota = new Mascota();
        mascota.setNombre("Bola de Nieve");
        mascota.setPropietario(propietario);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mascotaService.registrarMascota(mascota));

        assertEquals("El propietario debe tener un teléfono registrado.",exception.getMessage());
    }

    @Test
    void registrarMascotaPropiertarioSinTelefono(){

        Propietario propietario = new Propietario("Alfredo García", "San José", "");
        Mascota mascota = new Mascota();
        mascota.setNombre("Bola de Nieve");
        mascota.setPropietario(propietario);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mascotaService.registrarMascota(mascota));

        assertEquals("El propietario debe tener un teléfono registrado.",exception.getMessage());
    }

    @Test
    void registrarMascotaSinVacunas(){
        Propietario propietario = new Propietario("Alfredo García", "San José", "88589099");
        Mascota mascota = new Mascota();
        mascota.setNombre("Bola de Nieve");
        mascota.setPropietario(propietario);

        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(false);

        Exception exception = assertThrows(IllegalStateException.class, () -> mascotaService.registrarMascota(mascota));

        assertEquals("La mascota no tiene todas las vacunas al día.",exception.getMessage());
    }

//Buscar mascota
    @Test
    void buscarMascotaPorId() {
        Propietario propietario = new Propietario("Alfredo García", "San José", "88589099");
        Mascota mascota = new Mascota();
        mascota.setId(540);
        mascota.setNombre("Bola de Nieve");
        mascota.setPropietario(propietario);

        when(mascotaRepository.findById(mascota.getId())).thenReturn(Optional.of(mascota));

        Optional<Mascota> result = mascotaService.buscarMascotaPorId(540);

        assertTrue(result.isPresent(), "No se encontro la mascota");
        assertEquals("Bola de Nieve", result.get().getNombre(), "No se encuentra una mascota con este nombre");
        assertEquals("Alfredo García", result.get().getPropietario().getNombre(),"No se encuentra una mascota con este propietario");
        assertEquals("San José", result.get().getPropietario().getCiudad());
        assertEquals("88589099", result.get().getPropietario().getTelefono());

    }

//Eliminar mascota
    @Test
    void eliminarMascotaPorId() {

        Mascota mascota = new Mascota();
        when(mascotaRepository.findById(1)).thenReturn(Optional.of(mascota));

        mascotaService.eliminarMascotaPorId(1);
        when(mascotaRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Mascota> result = mascotaService.buscarMascotaPorId(1);

        assertFalse(result.isPresent(), "Mascota eliminada");
    }
}