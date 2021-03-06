package co.edu.uniquindio.proyectoUnishop.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * Entidad Categoria
 */
@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Categoria implements Serializable {

    @Id  //Llave primaria de la clase
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codCategoria; //Codigo de la categoria

    @NotBlank(message = "el nombre no puede estar vacio")
    @Column(length = 30,nullable = false,unique = true)
    private String nombre; //nombre de la categoria

    // Relacion con lista de productos
    @ManyToMany(mappedBy = "listaCategoria")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto>listaProductosCategorias;

    /**
     * Metodo constructor sin parámetros de la entidad Categoria
     */
    public Categoria() {
        super();
    }

    /**
     * Metodo constructor de la entidad Categoria
     * @param nombre nombre de la categoria
     */
    public Categoria(String nombre) {
        this.nombre = nombre;

    }
}
