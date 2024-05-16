package sebastiantrasca;

public enum Type {
    COMPUTER(Computer.class),
    PRINTER(Printer.class),
    ROUTER(Router.class);

    private final Class<?> clazz;

    Type(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
