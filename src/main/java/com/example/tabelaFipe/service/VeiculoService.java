package com.example.tabelaFipe.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VeiculoService {

    String Marcas = "";
    public String buscaTodasMarcas() {
        String apiAllMarca = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiAllMarca, String.class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            apiAllMarca = responseEntity.getBody();
        }else{
            apiAllMarca = "Não foi possivel buscar as marcas";
        }

        return apiAllMarca;


    }
}
