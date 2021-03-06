package co.edu.uniquindio.proyectoUnishop.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad Compra
 */
@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Compra implements Serializable {

    @Id //llave primaria de la entidad
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codCompra; //codigo de la compra

    @Column(nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private  LocalDate fechaPago; //fecha de pago de la compra

    @Column(length = 80,nullable = false)
    @NotBlank(message = "el medio de pago no puede estar vacio")
    private String medioPago; //medio de pago de la compra


    // Relacion compra de un producto
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario UsuarioCompra;


    // Relacion con la lista de detalle de la compra
    @ToString.Exclude
    @OneToMany(mappedBy = "compradetalle",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<DetalleCompra>listaDetalleCompra;


    /**
     * Constructor sin parametros de la entidadad Compra
     */
    public Compra(){
    super();
    }

    /**
     * Constructor de la entidad Compra
     * @param fechaPago fecha de pago de la compra
     * @param medioPago medio de pago de la compra
     * @param usuarioCompra usuario a realizar la compra
     */
    public Compra( LocalDate fechaPago, String medioPago, Usuario usuarioCompra) {

        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
        UsuarioCompra = usuarioCompra;

    }
}
