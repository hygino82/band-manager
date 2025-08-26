package br.dev.hygino.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestMusicDto;
import br.dev.hygino.dto.ResponseMusicDto;
import br.dev.hygino.entities.Album;
import br.dev.hygino.entities.Music;
import br.dev.hygino.repositories.AlbumRepository;
import br.dev.hygino.repositories.MusicRepository;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final AlbumRepository albumRepository;

    public MusicService(MusicRepository musicRepository, AlbumRepository albumRepository) {
        this.musicRepository = musicRepository;
        this.albumRepository = albumRepository;
    }

    @Transactional
    public ResponseMusicDto insert(RequestMusicDto dto) {
        Album album = albumRepository.findById(dto.albumId())
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        Music music = new Music();
        music.setCreatedAt(LocalDateTime.now());

        dtoToEntity(dto, music, album);

        music = musicRepository.save(music);
        return new ResponseMusicDto(music);
    }

    private void dtoToEntity(RequestMusicDto dto, Music music, Album album) {
        music.setTitle(dto.title());
        music.setDuration(dto.duration());
        music.setAlbum(album);
        music.setTrackNumber(dto.trackNumber());
    }
}
