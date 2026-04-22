package com.example.eventix.repository;

import com.example.eventix.model.Eventix;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;

@Repository
public class EventixRepository {
    private List <Eventix> listaEventos = new ArrayList<>();
    private int contadorId = 6;

    public EventixRepository (){
        listaEventos.add(new Eventix(1, "Fiesta Pepe", "cumpleaños", "enero - 2020", "Santiago", 500));
        listaEventos.add(new Eventix(2, "Fiesta pipe", "aniversario", "marzo - 2020", "Santiago", 500));
        listaEventos.add(new Eventix(3, "Fiesta jenny", "boda", "diciembre - 2020", "Santiago", 500));
        listaEventos.add(new Eventix(4, "Fiesta marta", "despedida de soltero", "octubre - 2020", "Santiago", 500));
        listaEventos.add(new Eventix(5, "Fiesta victor", "cumpleaños", "noviembre - 2020", "Santiago", 500));
    }

    //guardar - registrar - Post, metodo que guarda un evento nuevo
    public Eventix guardarEvento (Eventix evento){
        evento.setIdEvento(contadorId++);
        listaEventos.add(evento);
        return evento;
    }

    //obtener - leer - Get, metodo que obtiene toda la lista
    public List <Eventix> obtenerEventos(){
        return listaEventos;
    }

    //obtenerPorId - leerPorId - getPorId, metodo que busca por id
    public Eventix obtenerPorId(int id){
        for (Eventix evento : listaEventos){
            if (evento.getIdEvento() == id){
                return evento;
            }
        }
        return null;
    }

    //editar - actualizar - Put, metodo que actualiza un evento
    public Eventix actualizarEvento(Eventix evento){
        int id = 0;
        int idPosicion = 0;
        for (int i = 0; i < listaEventos.size(); i++){
            if(listaEventos.get(i).getIdEvento() == evento.getIdEvento()){
                id = evento.getIdEvento();
                idPosicion = i;
            }
        }
        Eventix eventix1 =new Eventix();
        eventix1.setIdEvento(id);
        eventix1.setNombreEvento(evento.getNombreEvento());
        eventix1.setTipoEvento(evento.getTipoEvento());
        eventix1.setFechaEvento(evento.getFechaEvento());
        eventix1.setUbicacionEvento(evento.getUbicacionEvento());
        eventix1.setCapacidadEvento(evento.getCapacidadEvento());
        listaEventos.set(idPosicion, eventix1);
        return eventix1;
    }

    //borrar - eliminar - delete, metodo que elimina un evento
    public void borrarEvento(int id){
        Eventix evento = obtenerPorId(id);
        if (evento != null){
            listaEventos.remove(evento);
        }
    }

}
