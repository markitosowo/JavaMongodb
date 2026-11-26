package mx.edu.itses.marg.basedatosiv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productos")
public class Producto {
    @Id
    private String id;

    private String nombre;
    private Double precio;
    private Integer stock;
    private String categoria;

}
