package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.entities.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    
}
