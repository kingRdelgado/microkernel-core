package co.unicauca.microkernel.presentation;

import co.unicauca.microkernel.business.ProjectService;
import co.unicauca.microkernel.business.ReportService;

import java.util.List;
import java.util.Scanner;
import co.unicauca.microkernel.common.entities.Project;

/**
 * UI de consola para probar el core.
 */
public class Console {

    private final ProjectService projectService;
    private final ReportService reportService;
    private final Scanner scanner;

    public Console() {
        this.projectService = new ProjectService();
        this.reportService = new ReportService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Microkernel - Core (Report generator) demo");
        int option;
        do {
            System.out.println("\n1. Listar proyectos");
            System.out.println("2. Generar reportes (plugins)");
            System.out.println("3. Salir");
            System.out.print("Seleccione opción: ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    listProjects();
                    break;
                case 2:
                    reportService.generateAllReports();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 3);
    }

    private void listProjects() {
        List<Project> list = projectService.getAll();
        System.out.println("Proyectos:");
        for (Project p : list) {
            System.out.println(" - " + p.getId() + " : " + p.getTitle() + " (" + p.getModality() + ")");
        }
    }
}

