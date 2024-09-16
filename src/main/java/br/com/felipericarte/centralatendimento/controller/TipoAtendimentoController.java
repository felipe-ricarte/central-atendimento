package br.com.felipericarte.centralatendimento.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoAtendimento")
@Api("Endpoint para Tipo de Atendimento")
public class TipoAtendimentoController {

    @Autowired
    private TipoAtendimentoService tipoAtendimentoService;

    @GetMapping
    @ApiOperation("Lista todas os Tipos de Atendimentos")
    public ResponseEntity<Response<List<TipoAtendimentoResponse>>> findAll(){
        Response<List<TipoAtendimentoResponse>> response = new Response<>();
        response.setStatus(ResponseStatusEnum.SUCCESS);
        response.setData(
                tipoAtendimentoService.buscarTodos().stream().map(s -> s.toResponse()).collect(Collectors.toList())
        );
        return ResponseEntity.ok(response);
    }
}

