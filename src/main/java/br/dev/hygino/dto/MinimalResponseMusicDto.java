package br.dev.hygino.dto;

import br.dev.hygino.entities.Music;

public record MinimalResponseMusicDto(
                Long id,
                String title,
                Integer duration) {

        public MinimalResponseMusicDto(Music entity) {
                this(entity.getId(), entity.getTitle(), entity.getDuration());
        }
}
