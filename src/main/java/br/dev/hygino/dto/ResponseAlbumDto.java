package br.dev.hygino.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import br.dev.hygino.entities.Album;
import br.dev.hygino.entities.Music;

public record ResponseAlbumDto(
        Long id,
        String bandName,
        String title,
        Short releaseYear,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<MinimalResponseMusicDto> musics) {

    public ResponseAlbumDto(Album entity) {
        this(entity.getId(), entity.getBandName(), entity.getTitle(), entity.getReleaseYear(),
                entity.getCreatedAt(), entity.getUpdatedAt(),
                getMusics(entity.getMusics()));
    }

    private static Set<MinimalResponseMusicDto> getMusics(Set<Music> musics) {
        return musics.stream().map(MinimalResponseMusicDto::new).collect(Collectors.toSet());
    }
}