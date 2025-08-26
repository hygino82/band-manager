package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestMusicDto(
        @NotBlank @Size(max = 100, min = 3, message = "O título deve ter entre 3 e 100 caracteres") String title,
        @NotNull(message = "A duração é obrigatória") Integer duration,
        @NotNull(message = "O número da faixa é obrigatório") Short trackNumber,
        @NotNull(message = "O id é obrigatório") Long albumId) {

}
