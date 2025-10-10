package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/institucional")
@CrossOrigin
public class InstitucionalController {

    @GetMapping
    public Map<String, String> obtenerInfo() {
        return Map.of(
                "Nombre", "Café Maka – Sabores que conectan",

                "Mision", """
                        Brindar a nuestros clientes una experiencia única de café,
                        elaborada con granos de alta calidad, seleccionados de manera responsable y sostenible,
                        ofreciendo productos frescos, auténticos y llenos de sabor,
                        mientras apoyamos a los caficultores locales y fomentamos el bienestar de nuestra comunidad.""",

                "Vision", """
                        Ser una marca reconocida nacional e internacionalmente por la excelencia de nuestro café,
                        la innovación en nuestros productos y el compromiso con la sostenibilidad,
                        convirtiéndonos en el punto de encuentro preferido para quienes buscan calidad, inspiración y conexión en cada taza.""",

                "Contacto", "info@maka.com"
        );
    }
}


