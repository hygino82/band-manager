package br.dev.hygino.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.RequestAlbumDto;
import br.dev.hygino.dto.ResponseAlbumDto;
import br.dev.hygino.services.AlbumService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
