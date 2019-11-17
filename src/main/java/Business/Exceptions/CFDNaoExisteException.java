package Business.Exceptions;

public class CFDNaoExisteException extends Exception {
    public CFDNaoExisteException(int id) {
        super("Não existe um CFD com o id " + id);
    }
}
