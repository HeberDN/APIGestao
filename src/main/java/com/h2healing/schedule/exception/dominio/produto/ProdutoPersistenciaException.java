package com.h2healing.schedule.exception.dominio.produto;

public class ProdutoPersistenciaException extends RuntimeException{
    public ProdutoPersistenciaException(String message) {
        super(message);
    }

    public ProdutoPersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
