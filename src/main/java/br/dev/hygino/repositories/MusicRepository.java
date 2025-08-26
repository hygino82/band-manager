package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.entities.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {
    
}
