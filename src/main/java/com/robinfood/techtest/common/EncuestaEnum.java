package com.robinfood.techtest.common;

import lombok.Getter;

public class EncuestaEnum {

    public enum ENCUESTA_STATUS {

        ACTIVE(0),
        CREATED(1),
        INACTIVE(2),
        FINISHED(3),
        SCHEDULED(4);

        @Getter
        private Integer id;

        ENCUESTA_STATUS(Integer id){
            this.id = id;
        }
    }

    public enum TIPO_RESPUESTA {

        TEXTO("texto"),
        NUMERO("numero");

        @Getter
        private String nombre;

        TIPO_RESPUESTA(String nombre){
            this.nombre = nombre;
        }
    }

    public enum TIPO_PREGUNTA {

        ABIERTA(0),
        OPCION_MULTIPLE(1),
        OPCION_UNICA(2);

        @Getter
        private Integer id;

        TIPO_PREGUNTA(Integer id){
            this.id = id;
        }
    }
}
