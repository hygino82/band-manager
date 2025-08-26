package br.dev.hygino.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestMusicDto;
import br.dev.hygino.dto.ResponseMusicDto;
import br.dev.hygino.entities.Album;
import br.dev.hygino.entities.Music;
import br.dev.hygino.repositories.AlbumRepository;
import br.dev.hygino.repositories.MusicRepository;
import jakarta.persistence.EntityNotFoundException;

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

    @Transactional(readOnly = true)
    public Page<ResponseMusicDto> getAll(Pageable pageable) {
        return musicRepository.findAll(pageable).map(ResponseMusicDto::new);
    }

    @Transactional(readOnly = true)
    public ResponseMusicDto getById(Long id) {
        final Music music = musicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Music not found"));
        return new ResponseMusicDto(music);
    }

    @Transactional
    public ResponseMusicDto update(Long id, RequestMusicDto dto) {
        final Album album = albumRepository.findById(dto.albumId())
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        try {
            Music music = musicRepository.getReferenceById(id);
            dtoToEntity(dto, music, album);
            music.setUpdatedAt(LocalDateTime.now());
            music = musicRepository.save(music);

            return new ResponseMusicDto(music);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Music not found");
        }
    }

    public void delete(Long id) {
        Music music = musicRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Music not found"));
        musicRepository.delete(music);
    }
}
