package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestAlbumDto(
                @NotBlank(message = "O nome da banda não pode ser vazio") @Size(min = 3, max = 100, message = "O nome da banda deve ter entre 3 e 100 caracteres") String bandName,
                @NotBlank(message = "O título do álbum não pode ser vazio") @Size(min = 3, max = 100, message = "O título do álbum deve ter entre 3 e 100 caracteres") String title,
                @NotNull(message = "O ano deve ser informado") Short releaseYear) {

}
