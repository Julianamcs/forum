package br.com.alura.forum.controller;

import br.com.alura.forum.DTO.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {


    @RequestMapping("/topicos")
    public List<TopicoDto> Lista(){
        Topico topico = new Topico("Dúvidas", "Dúvidas com Spring", new Curso("Spring", "Programaçao"));

        return TopicoDto.converter(Arrays.asList(topico, topico, topico));
    }
}
