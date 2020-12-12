package co.com.cidenet.hulkStore.exceptions;

public class ProductoNoEncontradoException extends ControlledException {

    /**
     * 
     */
    private static final long serialVersionUID = 4351108743874380836L;

    public ProductoNoEncontradoException() {
        super();
    }

    public ProductoNoEncontradoException(String message) {
        super(message);
    }

}
