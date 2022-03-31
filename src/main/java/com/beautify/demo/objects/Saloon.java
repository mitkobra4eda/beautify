package com.beautify.demo.objects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="saloons")
public class Saloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    @OneToMany(mappedBy = "saloon", cascade = CascadeType.ALL)
    private List<BookingType> services;

    public void addService(BookingType type){
        services.add(type);
    }

    public void removeService(BookingType type){
        services.remove(type);
    }

    public void setServices(List<BookingType> types){
        services.clear();
        services.addAll(types);
    }

    @Override
    public String toString() {
        return owner.getFirstName() + " " + owner.getLastName();
    }
}
