package br.dev.hygino.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestAlbumDto;
import br.dev.hygino.dto.ResponseAlbumDto;
import br.dev.hygino.entities.Album;
import br.dev.hygino.repositories.AlbumRepository;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional
    public ResponseAlbumDto insert(RequestAlbumDto dto) {
        Album entity = new Album(dto.bandName(), dto.title(), dto.releaseYear());
        entity.setCreatedAt(LocalDate.now());
        entity = albumRepository.save(entity);
        return new ResponseAlbumDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<ResponseAlbumDto> findAlbuns(Pageable pageable) {
        return albumRepository.findAll(pageable).map(ResponseAlbumDto::new);
    }
}
