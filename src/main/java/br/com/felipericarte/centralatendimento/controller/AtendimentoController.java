package br.com.felipericarte.centralatendimento.controller;

import br.com.felipericarte.centralatendimento.controller.request.AtendimentoRequest;
import br.com.felipericarte.centralatendimento.controller.response.Response;
import br.com.felipericarte.centralatendimento.controller.response.ResponseStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atendimento")
@Api("Endpoint para Atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    @ApiOperation("Lista todas os Atendimentos")
    public ResponseEntity<Response<List<AtendimentosResponse>>> buscarTodos(){
        Response<List<Response<T>>> response = new Response<>();
        response.setStatus(ResponseStatusEnum.SUCCESS);
        response.setData(
                atendimentoService.buscarTodos().stream().map(s -> s.toResponse()).collect(Collectors.toList())
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation("Detalhar Atendimento por Id")
    public ResponseEntity<Response<Response<T>>> buscarPorId(@PathVariable Long id) {
        Response<Response<T>> response = new Response<>();
        response.setStatus(ResponseStatusEnum.SUCCESS);
        response.setData(atendimentoService.buscarPorId(id).toResponse());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiOperation("Criar Atendimento")
    public ResponseEntity<Response<Response<T>>> criar(@RequestBody @Valid AtendimentoRequest form,
                                                       UriComponentsBuilder uriBuilder) {

        Atendimento atendimento = atendimentoService.criar(form.toModel());

        Response<Response<T>> response = new Response<>();
        response.setData(atendimento.toResponse());
        response.setMessage(Arrays.asList("Atendimento criada com sucesso."));
        response.setStatus(ResponseStatusEnum.SUCCESS);

        URI uri = uriBuilder.path("/atendimento/{id}").buildAndExpand(atendimento.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}/finalizar")
    @ApiOperation("Atualizar Atendimento por Id")
    public ResponseEntity<Response<Response<T>>> finalizar(@PathVariable Long id) {
        Response<Response<T>> response = new Response<>();
        response.setData(atendimentoService.finalizar(id).toResponse());
        response.setMessage(Arrays.asList("Atendimento finalizada com sucesso."));
        response.setStatus(ResponseStatusEnum.SUCCESS);
        return ResponseEntity.ok(response);
    }

}

