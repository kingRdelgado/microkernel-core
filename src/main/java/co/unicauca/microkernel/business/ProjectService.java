package co.unicauca.microkernel.business;

import co.unicauca.microkernel.common.entities.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio simple que devuelve una lista de proyectos.
 * (Para el taller no usamos persistencia; esta clase simula datos).
 */
public class ProjectService {

    public List<Project> getAll() {
        List<Project> list = new ArrayList<>();
        list.add(new Project(1, "Plataforma IoT para riego", "Investigación", "Dr. Pérez", "Ing. Gómez", "2025-08-01"));
        list.add(new Project(2, "Aplicación móvil para control de asistencia", "Práctica", "Dra. Ramírez", "Ing. Muñoz", "2025-06-20"));
        list.add(new Project(3, "Monitoreo de calidad de aire urbano", "Investigación", "Dr. Castro", null, "2025-07-10"));
        return list;
    }
}
