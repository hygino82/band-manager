package br.dev.hygino.dto;

import java.time.LocalDateTime;

import br.dev.hygino.entities.Music;

public record ResponseMusicDto(
                Long id,
                String title,
                Integer duration,
                String album,
                String artist,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

        public ResponseMusicDto(Music entity) {
                this(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getDuration(),
                        entity.getAlbum().getTitle(),
                        entity.getAlbum().getBandName(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt()
                );
        }
}
