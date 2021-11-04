package com.robinfood.techtest.dto;

import com.robinfood.techtest.entity.Encuesta;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@ApiModel(value = "PreguntaDTO")
public class PreguntaDTO implements Serializable {

    @ApiModelProperty()
    private Integer id;

    @ApiModelProperty()
    private String titulo;

    @ApiModelProperty()
    private Integer tipo;

    @ApiModelProperty()
    private Set<OpcionPreguntaDTO> opciones;

}
