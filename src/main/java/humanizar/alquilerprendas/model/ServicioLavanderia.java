package humanizar.alquilerprendas.model;

import java.util.Calendar;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "serviciolavanderia")
@Getter
@Setter
public class ServicioLavanderia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Integer idEnvio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda")
    private Prenda prenda;

    @Column(name = "prioridad")
    private Boolean prioridad;

    @Column(name = "fecha_envio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;

    @Column(name = "fecha_devolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;

    public ServicioLavanderia() {
    }

    public ServicioLavanderia(Prenda prenda, Boolean prioridad) {
        this.prenda = prenda;
        this.prioridad = prioridad;

        this.fechaEnvio = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fechaEnvio);
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        this.fechaDevolucion = calendar.getTime();
    }
}
