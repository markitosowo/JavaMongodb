package mx.edu.itses.marg.basedatosiv.repository;

import java.util.List;
import mx.edu.itses.marg.basedatosiv.dto.ProductoCatalogoDTO;
import mx.edu.itses.marg.basedatosiv.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

    // Fase 4.3: proyección en MongoDB, solo trae los campos que necesita el DTO
    @Query(value = "{}", fields = "{ 'nombre': 1, 'precio': 1, 'categoria': 1, 'stock': 1 }")
    List<ProductoCatalogoDTO> obtenerCatalogo();
}
