package com.example.tabelaFipe.service;

import com.example.tabelaFipe.model.MarcasEntity;
import com.example.tabelaFipe.repository.VeiculoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    String json;
    public String consultarUrl(String endpoint) {
        String dados = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(endpoint, String.class);
        if(responseEntity !=null ){
            dados = responseEntity.getBody();
        }else{
            dados = "Não foi possivel buscar as marcas";
        }
        return dados;
    }

//    public String BuscaMarcas() {
//        return consultarUrl("https://parallelum.com.br/fipe/api/v1/carros/marcas");
//    }

    public String BuscaMarcas() {
        String apiUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        try {
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();

                JSONArray marcasArray = new JSONArray(responseBody);

                for (int i = 0; i < marcasArray.length(); i++) {
                    JSONObject marcaObj = marcasArray.getJSONObject(i);
                    String codigo = marcaObj.getString("codigo");
                    String nome = marcaObj.getString("nome");

                    MarcasEntity marcasEntity = new MarcasEntity();
                    marcasEntity.setCodigo(codigo);
                    marcasEntity.setMarca(nome);

                    veiculoRepository.save(marcasEntity);
                }
                return "Marcas de veículos buscadas e salvas com sucesso.";
            } else {
                return "Falha ao buscar as marcas. Código de status: " + responseEntity.getStatusCodeValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Não foi possível buscar e salvar as marcas de veículos.";
        }
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
