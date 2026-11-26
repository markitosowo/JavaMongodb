package mx.edu.itses.marg.basedatosiv.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import mx.edu.itses.marg.basedatosiv.dto.ProductoCatalogoDTO;
import mx.edu.itses.marg.basedatosiv.model.Producto;
import mx.edu.itses.marg.basedatosiv.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void obtenerTodosLosProductosRegresaLaLista() {
        when(productoRepository.findAll())
                .thenReturn(List.of(new Producto("1", "Mouse", 850.0, 45, "Accesorios")));
        assertEquals(1, productoService.obtenerTodosLosProductos().size());
    }

    @Test
    void obtenerCatalogoRegresaDTOs() {
        when(productoRepository.obtenerCatalogo())
                .thenReturn(List.of(new ProductoCatalogoDTO("1", "Mouse", 850.0, "Accesorios", 45)));
        assertEquals("Mouse", productoService.obtenerCatalogo().get(0).nombre());
    }

    @Test
    void obtenerPorIdInexistenteRegresaVacio() {
        when(productoRepository.findById("x")).thenReturn(Optional.empty());
        assertTrue(productoService.obtenerPorId("x").isEmpty());
    }
}
