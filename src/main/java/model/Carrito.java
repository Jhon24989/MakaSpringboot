package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Carrito {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        private Usuario usuario;

        @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ItemCarrito> items = new ArrayList<>();

        private double total;

        public Long getId() {
            return id;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        public List<ItemCarrito> getItems() {
            return items;
        }

        public void setItems(List<ItemCarrito> items) {
            this.items = items;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }

}
