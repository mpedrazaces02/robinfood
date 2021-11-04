package com.robinfood.techtest.service;

import com.robinfood.techtest.common.EncuestaEnum;
import com.robinfood.techtest.dto.CreateEncuestaDTO;
import com.robinfood.techtest.dto.EncuestaDTO;
import com.robinfood.techtest.entity.Encuesta;
import com.robinfood.techtest.repository.EncuestaRepository;
import com.robinfood.techtest.util.MappingService;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Assert;

import javax.naming.directory.InvalidAttributesException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EncuestaServiceTest {

    @Mock
    MappingService mappingService;

    @Mock
    EncuestaRepository repository;

    @InjectMocks
    @MockBean
    EncuestaService service;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
            MockitoAnnotations.openMocks(this);
        }

    @Test
    public void getAllEncuestasTest() throws NotFoundException {
        Encuesta encuesta = new Encuesta();
        encuesta.setId(1);
        encuesta.setTitulo("Encuesta de prueba");
        encuesta.setFecha_inicio(new Date());
        encuesta.setFecha_fin(new Date());
        encuesta.setEstado(EncuestaEnum.ENCUESTA_STATUS.ACTIVE.getId());

        Encuesta dto = new Encuesta();
        dto.setId(1);
        dto.setTitulo("Encuesta de prueba");
        dto.setFecha_inicio(new Date());
        dto.setFecha_fin(new Date());
        dto.setEstado(EncuestaEnum.ENCUESTA_STATUS.ACTIVE.getId());

        Mockito.when(repository.findAll()).thenReturn(List.of(encuesta));
        Mockito.when(mappingService.mapList(Mockito.anyList(), Mockito.any())).thenReturn(List.of(dto));

        List<EncuestaDTO> encuestas = service.getAllEncuestas();
        Assert.assertEquals(1, encuestas.size());
    }

    @Test
    public void findAll_shouldThrownNotFoundException() throws NotFoundException {
        exception.expect(NotFoundException.class);
        service.getAllEncuestas();
    }

    @Test
    public void findById() throws NotFoundException {

        Encuesta encuesta = new Encuesta();
        encuesta.setId(1);
        encuesta.setTitulo("Encuesta de prueba");
        encuesta.setFecha_inicio(new Date());
        encuesta.setFecha_fin(new Date());
        encuesta.setEstado(EncuestaEnum.ENCUESTA_STATUS.ACTIVE.getId());

        EncuestaDTO dto = new EncuestaDTO();
        dto.setId(1);
        dto.setTitulo("Encuesta de prueba");
        dto.setFecha_inicio(new Date());
        dto.setFecha_fin(new Date());
        dto.setEstado(EncuestaEnum.ENCUESTA_STATUS.ACTIVE.getId());

        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(encuesta));
        Mockito.when(service.getEncuestaById(Mockito.anyInt())).thenReturn(dto);

        Optional<Encuesta> result = repository.findById(1);

        EncuestaDTO encuestaResult = service.getEncuestaById(1);

        Assert.assertNotNull(result.get());
        Assert.assertEquals(result.get().getId(), encuestaResult.getId());
    }

    @Test
    public void findById_shouldThrownNotFoundException() throws NotFoundException {
        exception.expect(NotFoundException.class);
        service.getEncuestaById(Mockito.anyInt());
    }

    @Test
    public void createEncuesta_InvalidAttributesException() throws InvalidAttributesException {
        CreateEncuestaDTO dto = new CreateEncuestaDTO();
        dto.setTitulo("Encuesta de prueba");
        Date dt = new Date();
        dto.setFecha_inicio(new Date(dt.getTime() + (1000 * 60 * 60 * 24)));
        dto.setFecha_fin(new Date());
        dto.setEstado(EncuestaEnum.ENCUESTA_STATUS.ACTIVE.getId());

        exception.expect(InvalidAttributesException.class);
        service.createEncuesta(dto);
    }

    @Test
    public void createEncuesta() throws InvalidAttributesException {
        CreateEncuestaDTO dto = new CreateEncuestaDTO();
        dto.setTitulo("Encuesta de prueba");
        Date dt = new Date();
        dto.setFecha_inicio(new Date(dt.getTime() - (1000 * 60 * 60 * 24)));
        dto.setFecha_fin(new Date());
        dto.setEstado(EncuestaEnum.ENCUESTA_STATUS.ACTIVE.getId());

        Encuesta encuestaToSave = mappingService.map(dto, Encuesta.class);

        Encuesta encuestaSaved = new Encuesta();
        encuestaSaved.setId(1);
        encuestaSaved.setTitulo("Encuesta de prueba");
        encuestaSaved.setFecha_inicio(new Date(dt.getTime() - (1000 * 60 * 60 * 24)));
        encuestaSaved.setFecha_fin(new Date());
        encuestaSaved.setEstado(EncuestaEnum.ENCUESTA_STATUS.ACTIVE.getId());

        EncuestaDTO encuestaDtoSaved = new EncuestaDTO();
        encuestaDtoSaved.setId(1);
        encuestaDtoSaved.setTitulo("Encuesta de prueba");
        encuestaDtoSaved.setFecha_inicio(new Date(dt.getTime() - (1000 * 60 * 60 * 24)));
        encuestaDtoSaved.setFecha_fin(new Date());
        encuestaDtoSaved.setEstado(EncuestaEnum.ENCUESTA_STATUS.ACTIVE.getId());

        Mockito.when(repository.save(encuestaToSave)).thenReturn(encuestaSaved);
        Mockito.when(service.createEncuesta(dto)).thenReturn(encuestaDtoSaved);

        Assert.assertEquals(encuestaSaved.getId(),encuestaDtoSaved.getId());
    }
}
