package edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin.service;

import edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin.model.Message;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoadBalancerService {

    // Lista de URLs de las instancias del servidor REST
    private final List<String> serviceUrls = List.of(
            "http://LogService1:6000/v1/messages",
            "http://LogService2:6000/v1/messages",
            "http://LogService3:6000/v1/messages"
    );


    private final AtomicInteger counter = new AtomicInteger(0);

    private final RestTemplate restTemplate = new RestTemplate();

    // Método para balancear la solicitud
    public String getNextServiceUrl() {
        int index = counter.getAndIncrement() % serviceUrls.size();
        return serviceUrls.get(index);
    }

    // Método para obtener todos los usuarios
    public List<Message> getAllMessages() {
        // Obtiene la URL del siguiente servicio
        String serviceUrl = getNextServiceUrl();
        String requestUrl = serviceUrl + "/";

        // Realiza la solicitud GET para obtener la lista de usuarios
        ResponseEntity<List<Message>> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Message>>() {}
        );

        // Obtiene el cuerpo de la respuesta
        List<Message> messages = response.getBody();

        // Imprime la respuesta para depuración
        System.out.println(messages);

        return messages; // Devuelve la lista de usuarios
    }

    public void addMessage(String log, String message) {
        String serviceUrl = getNextServiceUrl();

        // Construye la URL con los parámetros
        String urlWithParams = UriComponentsBuilder.fromHttpUrl(serviceUrl+"/")
                .queryParam("log", log)
                .queryParam("message", message)
                .toUriString();
        System.out.println(urlWithParams);
        // Configura los encabezados (puedes omitir esto si no es necesario)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        // Crea la entidad con los encabezados (puedes omitir esto si no es necesario)
        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            // Envía la solicitud POST al servicio REST usando RestTemplate
            ResponseEntity<String> response = restTemplate.exchange(urlWithParams, HttpMethod.POST, request, String.class);
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            // Maneja el error si la URL no existe o hay un problema con la solicitud
            System.err.println("Error: " + e.getMessage());
        }
    }


    // Método para buscar un usuario por correo
    public Message getMessageByLog(String log) {
        String serviceUrl = getNextServiceUrl();
        String requestUrl = serviceUrl + "/" + log; // GET /v1/users/{mail}

        return restTemplate.getForObject(requestUrl, Message.class);
    }
}


