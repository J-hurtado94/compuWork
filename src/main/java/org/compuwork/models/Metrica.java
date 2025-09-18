package org.compuwork.models;

public enum Metrica {
    PUNTUALIDAD(3),
    PRODUCTIVIDAD(5),
    TRABAJO_EN_EQUIPO(2),
    CAPACITACION(1),
    CUMPLIMIENTO_METAS(4);

    private int valorMetrica;

    Metrica(int valorMetrica) {
        this.valorMetrica = valorMetrica;
    }

    public int getValorMetrica() {
        return valorMetrica;
    }
}
