package com.example.tabelaFipe.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VeiculoService {

    public String consultarUrl(String endpoint) {
        String dados = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(endpoint, String.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            dados = responseEntity.getBody();
        }else{
            dados = "Não foi possivel buscar as marcas";
        }
        return dados;
    }

    public String BuscaMarcas(){
        return consultarUrl("https://parallelum.com.br/fipe/api/v1/carros/marcas");
    }

    public String ConsultaModelos(int id){
        return consultarUrl("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + id + "/modelos");
    }

    public String consultaAno(int marca, int modelo) {
        return consultarUrl("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marca + "/modelos/" +  modelo+ "/anos");
    }

    public String consultaValor(int marca, int modelo, String ano){
        return consultarUrl("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marca + "/modelos/" +  modelo + "/anos/" + ano);
    }
}
