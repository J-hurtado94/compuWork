package org.compuwork.models;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public abstract class ReporteDesempeno {
    private LocalDateTime fechaGeneracion= LocalDateTime.now();
    private Map<Metrica,Integer> metricas = new EnumMap<>(Metrica.class);

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public abstract String generarReporte();
}
