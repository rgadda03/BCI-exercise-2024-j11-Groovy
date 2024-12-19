package org.example.exception

abstract class SuperErrorException extends Throwable implements Serializable{

    private static final long serialVersionUID = 1L;

    static long getSerialVersionUID() {
        return serialVersionUID
    }
}
