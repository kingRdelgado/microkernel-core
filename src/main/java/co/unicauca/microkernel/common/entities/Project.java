package co.unicauca.microkernel.common.entities;

/**
 * Entidad POJO simple que representa un proyecto de grado.
 * (Puedes ampliar campos según necesidades del taller).
 */
public class Project {
    private int id;
    private String title;
    private String modality; // e.g. "Investigación", "Práctica"
    private String director;
    private String codirector;
    private String date; // formato simple, ej. "2025-08-18"

    public Project() {}

    public Project(int id, String title, String modality, String director, String codirector, String date) {
        this.id = id;
        this.title = title;
        this.modality = modality;
        this.director = director;
        this.codirector = codirector;
        this.date = date;
    }

    // getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getModality() { return modality; }
    public void setModality(String modality) { this.modality = modality; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getCodirector() { return codirector; }
    public void setCodirector(String codirector) { this.codirector = codirector; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", modality='" + modality + '\'' +
                ", director='" + director + '\'' +
                ", codirector='" + codirector + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

