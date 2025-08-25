package br.dev.hygino.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome da banda não pode ser vazio")
    @Size(min = 3, max = 100, message = "O nome da banda deve ter entre 3 e 100 caracteres")
    private String bandName;

    @NotBlank(message = "O título do álbum não pode ser vazio")
    @Size(min = 3, max = 100, message = "O título do álbum deve ter entre 3 e 100 caracteres")
    private String title;

    @NotNull(message = "O ano deve ser informado")
    private Short releaseYear;

    @OneToMany(mappedBy = "album")
    private Set<Music> musics = new HashSet<>();

    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Album() {
    }

    public Album(String bandName, String title, Short releaseYear) {
        this.bandName = bandName;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public Set<Music> getMusics() {
        return musics;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Album other = (Album) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
