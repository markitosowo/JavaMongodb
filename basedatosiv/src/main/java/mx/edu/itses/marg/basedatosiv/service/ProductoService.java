package mx.edu.itses.marg.basedatosiv.service;

import java.util.List;
import java.util.Optional;
import mx.edu.itses.marg.basedatosiv.dto.ProductoCatalogoDTO;
import mx.edu.itses.marg.basedatosiv.model.Producto;
import mx.edu.itses.marg.basedatosiv.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Fase 2.4
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Fase 4.4: lo que se expone hacia la capa web
    public List<ProductoCatalogoDTO> obtenerCatalogo() {
        return productoRepository.obtenerCatalogo();
    }

    // Fase 5.3
    public boolean existePorId(String id) {
        return productoRepository.existsById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    // Fase 6.4
    public Optional<Producto> obtenerPorId(String id) {
        return productoRepository.findById(id);
    }
}
