package com.aluracursos.desafio.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ConsumAPI {

    public String obtenerDatos(String url) {
        try {
            System.out.println("🔍 Intentando conectar con: " + url);
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(java.time.Duration.ofSeconds(30))
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                System.out.println("✅ Conexión exitosa con la API");
                return response.body();
            } else {
                System.err.println("❌ Error HTTP: " + response.statusCode());
                return null;
            }
            
        } catch (java.net.ConnectException e) {
            System.err.println("❌ Error de conexión: No se puede conectar con la API");
            System.err.println("💡 Posibles causas:");
            System.err.println("   - Sin conexión a internet");
            System.err.println("   - Firewall bloqueando la conexión");
            System.err.println("   - API temporalmente no disponible");
            return null;
        } catch (java.nio.channels.UnresolvedAddressException e) {
            System.err.println("❌ Error: No se puede resolver la dirección de la API");
            System.err.println("💡 Verifica tu conexión DNS o internet");
            return null;
        } catch (IOException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.err.println("❌ Error: La petición fue interrumpida");
            Thread.currentThread().interrupt();
            return null;
        } catch (Exception e) {
            System.err.println("❌ Error inesperado: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
