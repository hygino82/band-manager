package br.dev.hygino.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.RequestAlbumDto;
import br.dev.hygino.dto.ResponseAlbumDto;
import br.dev.hygino.services.AlbumService;

@RestController
@RequestMapping("api/v1/album")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<ResponseAlbumDto> insert(@RequestBody RequestAlbumDto dto) {
        ResponseAlbumDto responseDto = albumService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseAlbumDto>> findAlbuns(Pageable pageable) {
        try {
            Page<ResponseAlbumDto> albums = albumService.findAlbuns(pageable);
            return ResponseEntity.ok(albums);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAlbumDto> findById(@PathVariable Long id) {
        try {
            ResponseAlbumDto album = albumService.findById(id);
            return ResponseEntity.ok(album);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
