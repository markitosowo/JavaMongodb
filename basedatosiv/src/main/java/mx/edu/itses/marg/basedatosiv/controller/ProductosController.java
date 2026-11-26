package mx.edu.itses.marg.basedatosiv.controller;

import mx.edu.itses.marg.basedatosiv.model.Producto;
import mx.edu.itses.marg.basedatosiv.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductosController {

    private final ProductoService productoService;

    public ProductosController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Fase 3.4 + 4.5: lista de DTOs hacia la vista
    @GetMapping
    public String index(Model model) {
        model.addAttribute("productos", productoService.obtenerCatalogo());
        return "productos/index";
    }

    // Fase 5.3: recibe el formulario de la ventana modal
    @PostMapping("/nuevo")
    public String nuevoProducto(@ModelAttribute Producto producto, RedirectAttributes flash) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()
                || producto.getPrecio() == null || producto.getPrecio() < 0
                || producto.getStock() == null || producto.getStock() < 0
                || producto.getCategoria() == null || producto.getCategoria().isBlank()) {
            flash.addFlashAttribute("error", "Datos inválidos: revisa nombre, precio, categoría y stock.");
            return "redirect:/productos";
        }

        // Si el id viene vacío, MongoDB genera el ObjectId automáticamente
        if (producto.getId() == null || producto.getId().isBlank()) {
            producto.setId(null);
        } else if (productoService.existePorId(producto.getId().trim())) {
            flash.addFlashAttribute("error", "Ya existe un producto con el id " + producto.getId() + ".");
            return "redirect:/productos";
        } else {
            producto.setId(producto.getId().trim());
        }

        productoService.guardar(producto);
        flash.addFlashAttribute("exito", "Producto \"" + producto.getNombre() + "\" guardado correctamente.");
        return "redirect:/productos";
    }

    // Fase 6.4
    @GetMapping("/ver/{id}")
    public String verProducto(@PathVariable String id, Model model, RedirectAttributes flash) {
        return productoService.obtenerPorId(id)
                .map(producto -> {
                    model.addAttribute("producto", producto);
                    return "productos/ver/detalle";
                })
                .orElseGet(() -> {
                    flash.addFlashAttribute("error", "No se encontró el producto con id " + id + ".");
                    return "redirect:/productos";
                });
    }
}
