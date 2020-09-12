package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.api.service.MercadoLibreAPIService;
import utn.dds.tpAnual.db.entity.ubicacion.Ciudad;
import utn.dds.tpAnual.db.entity.ubicacion.Estado;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.service.ImportInformacionGeograficaService;
import utn.dds.tpAnual.db.service.PaisService;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
public class ImportInformacionGeograficaServiceTest {

    @Autowired
    private ImportInformacionGeograficaService importInformacionGeograficaService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private MonedaService monedaService;

    @Test
    public void importaArgentina(){
        importInformacionGeograficaService.importPaises();
        Pais pais = paisService.getPrimerPaisByNombre("Argentina");
        assertTrue(pais != null);
    }

    @Test
    public void importaPaisesSinRepetir(){
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importPaises();
        List<Pais> paises = paisService.getPaisesByNombre("Argentina");
        assertTrue(paises.size() == 1);
    }

    @Test
    public void importaCatamarca(){
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importEstados();
        Estado estado = estadoService.getPrimerEstadoByIdAPI("TUxBUENBVGFiY2Fm");
        assertTrue(estado != null);
    }

    @Test
    public void importaAvellaneda(){
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importEstados();
        importInformacionGeograficaService.importCiudades();
        Ciudad ciudad = ciudadService.getPrimeraCiudadByIdAPI("TUxBQ0FWRTc5OTQ1");
        assertTrue(ciudad != null);
    }

    @Test
    public void importaPesoArgentino(){
        importInformacionGeograficaService.importMonedas();
        Moneda moneda = monedaService.getPrimerMonedaByIdAPI("ARS");
        assertTrue(moneda != null);
    }

}