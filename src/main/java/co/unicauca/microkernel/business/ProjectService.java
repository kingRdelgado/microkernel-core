package co.unicauca.microkernel.business;

import co.unicauca.microkernel.common.entities.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();

        // Lista de estudiantes de ejemplo
        List<String> estudiantes1 = new ArrayList<>();
        estudiantes1.add("Carlos Martínez");

        List<String> estudiantes2 = new ArrayList<>();
        estudiantes2.add("María Rodríguez");

        List<String> estudiantes3 = new ArrayList<>();
        estudiantes3.add("Andrés López");

        // Crear proyectos con el constructor correcto
        Project p1 = new Project(
                "P001",
                "Sistema de Gestión Académica",
                LocalDate.now(),
                estudiantes1,
                "Dr. Juan Pérez",
                "Investigación",
                "Ingeniería de Sistemas"
        );

        Project p2 = new Project(
                "P002",
                "Aplicación Móvil para Salud",
                LocalDate.now().minusDays(10),
                estudiantes2,
                "Ing. Pedro Torres",
                "Práctica Profesional",
                "Ingeniería Electrónica"
        );

        Project p3 = new Project(
                "P003",
                "Plataforma de Comercio Electrónico",
                LocalDate.now().minusMonths(1),
                estudiantes3,
                "PhD. Jorge Castillo",
                "Investigación",
                "Automática Industrial"
        );

        projects.add(p1);
        projects.add(p2);
        projects.add(p3);

        return projects;
    }
}

