package com.algostyle.todo_app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * - Cette classe est une configuration CORS (Cross-Origin Resource Sharing) pour notre app Spring Boot
 * - Elle permet de configurer les règles pour autoriser les requêtes provenant d'un autre domaine (ou origine) comme notre app Angular qui tourne sur "http://localhost:4200"
 *
 * - Sans cette configuration, le navigateur bloque les requêtes entre http://localhost:4200 (Angular) et http://localhost:8080 (Spring Boot) pour des raisons de sécurité.
 *
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * - Cette classe CorsConfig est essentielle pour permettre à ton application Angular de communiquer avec ton API Spring Boot.
 * - Elle configure les règles CORS pour autoriser les requêtes provenant de http://localhost:4200 et éviter les erreurs de sécurité dans le navigateur
 *
 *
 * ******  ALTERNATIVE  *********
 * Si tu ne veux pas configurer CORS globalement pour toute l'application, tu peux utiliser l'annotation @CrossOrigin directement dans l contrôleur
 */





@Configuration  // cette annotation indique à Spring que cette classe est une classe de configuration. Elle contient des définitions de beans (composants) qui seront utilisés par Spring
public class CorsConfig {


    @Bean  // Cette annotation indique que la méthode corsConfigurer() retourne un objet qui doit être géré par Spring.


    /**
     * Cette méthode retourne une instance de WebMvcConfigurer, qui est une interface fournie par Spring pour configurer des aspects de Spring MVC
     */
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")    // Autoriser les endpoints sous/api
                        .allowedOrigins("http://localhost:4200")  // Autoriser Angular
                        .allowedMethods("GET","POST","PUT","DELETE") // Méthodes autorisée
                        .allowedHeaders("*")  // En-têtes autorisés
                        .allowCredentials(true);  // Autorise les cookies

            }
        };
    }
}
