package com.example.tabelaFipe.controller;

import com.example.tabelaFipe.service.VeiculoService;
import org.apache.catalina.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    VeiculoService veiculoService = new VeiculoService();

    @GetMapping("/marcas")
    public String BuscaMarcas(){

        return veiculoService.buscaTodasMarcas();
    }

}
