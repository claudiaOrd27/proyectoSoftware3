package co.edu.uniquindio.proyectoUnishop.servicios;


import co.edu.uniquindio.proyectoUnishop.dto.ProductoCarrito;
import co.edu.uniquindio.proyectoUnishop.entidades.*;
import co.edu.uniquindio.proyectoUnishop.repositorios.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * este metodo imprime los datos de los productos guardados
 *
 */
@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final UsuarioRepo usuarioRepo;
    private final CategoriaRepo categoriaRepo;
    private final CiudadRepo ciudadRepo;
    private final ComentarioRepo comentarioRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final CompraRepo compraRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, UsuarioRepo usuarioRepo, CategoriaRepo categoriaRepo, CiudadRepo ciudadRepo, ComentarioRepo comentarioRepo, DetalleCompraRepo detalleCompraRepo, CompraRepo compraRepo) {
        this.productoRepo = productoRepo;
        this.usuarioRepo = usuarioRepo;
        this.categoriaRepo = categoriaRepo;
        this.ciudadRepo = ciudadRepo;
        this.comentarioRepo = comentarioRepo;
        this.detalleCompraRepo = detalleCompraRepo;

        this.compraRepo = compraRepo;
    }


    @Override
    public Producto publicarProducto(Producto p) throws Exception {


        try {

            return productoRepo.save(p);

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Producto actualizarProducto(Producto p) throws Exception {


        Optional<Producto> buscado = productoRepo.findById(p.getCodProducto());

        if (buscado.isEmpty()) {

            throw new Exception("El Producto con codigo " + p.getCodProducto() + " no está registrado.");
        }

        return productoRepo.save(p);
    }

    @Override
    public void eliminarProducto(Integer idProdcuto) throws Exception {


        Optional<Producto> buscado = productoRepo.findById(idProdcuto);

        if (buscado.isEmpty()) {
            throw new Exception("El codigo del producto no existe");
        }
        productoRepo.deleteById(idProdcuto);

    }

    @Override
    public Producto buscarProducto(Integer idProducto) throws Exception {

        Optional<Producto> producto = productoRepo.findById(idProducto);

        if (producto.isEmpty()) {

            throw new Exception("No existe un usuario con el id dado");
        }

        return producto.get();
    }

    @Override
    public List<Producto> listarporCategoria(String categoriaNombre) throws Exception {

        Optional<Categoria> categoriaBuscada = categoriaRepo.findByNombreContains(categoriaNombre);

        if (categoriaBuscada.isEmpty()) {
            throw new Exception("La categoria no existe");
        }


        return productoRepo.ListarProductosPorCategoria(categoriaNombre);
    }

    @Override
    public List<Producto> listarporCiudad(String ciudadNombre) throws Exception {

        Optional<Ciudad> ciudadBuscada = ciudadRepo.findByNombre(ciudadNombre);

        if (ciudadBuscada.isEmpty()) {
            throw new Exception("La ciudad no existe");
        }

        return productoRepo.ListarProductosPorCiudad(ciudadNombre);
    }

    @Override
    public List<Producto> listarporDescripcion(String descripcion) throws Exception {
        Optional<Producto> descripcionBuscado = productoRepo.findByDescripcionContains(descripcion);

        if (descripcionBuscado.isEmpty()) {
            throw new Exception("No hay ningun producto con ese precio");
        }
        return productoRepo.ListarProductosPorDescripcion(descripcionBuscado);
    }

    @Override
    public List<Producto> listarTodosporProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDate.now());
        comentarioRepo.save(comentario);

    }

    @Override
    public Float obtenerPromedioProducto(Integer codProducto) {
        return productoRepo.obtenerPromedioCalificacion(codProducto);
    }


    @Override
    public void guardarProductoFavorito(Usuario usuario, Producto producto) throws Exception {

        Optional<Producto> buscado = productoRepo.findById(producto.getCodProducto());
        if (buscado.isEmpty()) {
            throw new Exception("El codigo del producto a añadir no esta registrado en la base de datos.");
        }
        Producto aux = productoRepo.getById(producto.getCodProducto());
        Usuario aux2 = usuarioRepo.getById(usuario.getCodPersona());
        aux2.getListaProductoFavorito().add(aux);
        productoRepo.save(aux);
        usuarioRepo.save(aux2);


    }

    @Override
    public void eliminarProductoFavorito(Usuario usuario, Producto producto) throws Exception {

    }

    @Override
    public List<Producto> buscarProductoFiltro(String nombre, String[] filtro) {
        return productoRepo.listarProductoNombre(nombre);
    }

    @Override
    public Compra compraProductos(Usuario usuarioCompra, ArrayList<ProductoCarrito> productoCarritos, String medioPago) throws Exception {


        try {

            Compra compra = new Compra();
            compra.setFechaPago(LocalDate.now(ZoneId.of("America/Bogota")));
            compra.setUsuarioCompra(usuarioCompra);
            compra.setMedioPago(medioPago);

            Compra compraGuardada = compraRepo.save(compra);


            DetalleCompra dc;
            for (ProductoCarrito p : productoCarritos){


                dc=new DetalleCompra();
                dc.setCompradetalle(compraGuardada);
                dc.setPrecioProducto(p.getPrecio());
                dc.setUnidades(p.getUnidades());
                dc.setProductoDetalle(productoRepo.findById(p.getIdProducto()).get());
                detalleCompraRepo.save(dc);
            }
            return compraGuardada;
        }catch (Exception e){

            throw new Exception(e.getMessage());
        }

    }




}
