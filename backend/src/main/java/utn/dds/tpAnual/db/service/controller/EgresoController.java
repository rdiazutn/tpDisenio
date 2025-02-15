package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.service.business.EgresoResourceBean;

@RestController
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/transaccion")
public class EgresoController {

    @Autowired
    private EgresoResourceBean egresoResourceBean;

    @RequestMapping("egreso")
    public PageableResponse<EgresoDTO, Egreso> getEgresos(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                          @RequestParam(name ="itemsPerPage", defaultValue = "20")
                                                                  Long itemsPerPage,
                                                             @RequestParam(name ="categorias", required = false)
                                                                      String categorias){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<EgresoDTO, Egreso> egresos = egresoResourceBean.getEgresos(pageableRequest, categorias);
        return egresos;
    }

    @RequestMapping("egreso/{egresoId}")
    public PageableResponse<EgresoDTO, Egreso> getEgresosById(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                              @RequestParam(name ="itemsPerPage", defaultValue = "20")
                                                                      Long itemsPerPage, @PathVariable("egresoId") Long egresoId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<EgresoDTO, Egreso> egresos = egresoResourceBean.getEgresosById(pageableRequest, egresoId);
        return egresos;
    }

    @PostMapping("egreso")
    public EgresoDTO crearEgreso(@RequestBody EgresoDTO egresoDTO){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        EgresoDTO egresoCreado = egresoResourceBean.crearEgreso(egresoDTO, username);
        return egresoCreado;
    }

    @PostMapping("vincular")
    public String vincularEgresoIngreso(@RequestBody VinculacionEgresoIngresoDTO vinculacion){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        egresoResourceBean.vincular(vinculacion);
        return "Ok";
    }
}
