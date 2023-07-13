package br.com.alura.forum.controller;

import br.com.alura.forum.DTO.TopicoDto;
import br.com.alura.forum.form.TopicoForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    CursoRepository cursoRepository;

    /**
    @RequestMapping("/topicos")
    public List<TopicoDto> Lista(){
        List<Topico> topicos = topicoRepository.findAll();
        System.out.println(topicos);
        return TopicoDto.converter(topicos);
    }**/


     @GetMapping
     public List<TopicoDto> Lista(String nomeCurso){

         if (nomeCurso == null) {
             List<Topico> topicos = topicoRepository.findAll();
             System.out.println(topicos);
             return TopicoDto.converter(topicos);
         }else{
             List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
             System.out.println(nomeCurso);
             return TopicoDto.converter(topicos);
         }
     }

    @PostMapping
     public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder){
         Topico topico = form.converter(cursoRepository);
         topicoRepository.save(topico);
         //validacao
         URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
         return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

}
