package es.cic.curso25.proy008.exception;

public class ModificationSecurityException extends RuntimeException {

    public ModificationSecurityException() {
        super("Modificación a través de creación.");
    }
    
    public ModificationSecurityException(String message) {
        super(message);
    }

    public ModificationSecurityException(String message, Throwable throable) {
        super(message, throable);
    }
}
