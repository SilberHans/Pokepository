package Iinterfaces;

public interface Ground {
    void earthquake(); // doble daño si el oponente este bajo tierra
    void dig(); //se hace inmune al daño por este turno y ataca al siguiente (prioridad de ataque)
}
