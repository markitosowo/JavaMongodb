package mx.edu.itses.marg.basedatosiv.dto;

public record ProductoCatalogoDTO(
        String id,
        String nombre,
        Double precio,
        String categoria,
        Integer stock) {
}
