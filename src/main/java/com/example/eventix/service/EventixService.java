package com.example.eventix.service;

import com.example.eventix.model.Eventix;
import com.example.eventix.repository.EventixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventixService {
    @Autowired
    private EventixRepository eventixRepository;

    //guardar - registrar - Post
    public void registrarEvento (Eventix nuevoEvento) throws Exception{
        if (eventixRepository.obtenerPorId(nuevoEvento.getIdEvento()) != null){
            throw new Exception("Error, el id ingresado" + nuevoEvento.getIdEvento() + " ya esta registrado");
        }
        eventixRepository.guardarEvento(nuevoEvento);
    }

    //obtener - leer - get
    public List <Eventix> leerEvento(){
        return eventixRepository.obtenerEventos();
    }

    //obtenerPorId - leerPorId - getPorId
    public Eventix leerEventoPorId (int id){
        return eventixRepository.obtenerPorId(id);
    }

    //actualizar - editar - Put
    public void editarEvento (Eventix eventoEditado) throws Exception{
        Eventix eventoExistente = eventixRepository.obtenerPorId(eventoEditado.getIdEvento());
        if(eventoExistente == null){
            throw new Exception("Error, no puede editar un evento que NO existe");
        }
        eventixRepository.actualizarEvento(eventoEditado);
    }

    //eliminar - borrar - delete
    public void borrarEvento (int id) throws Exception{
        if (eventixRepository.obtenerPorId(id) == null){
            throw new Exception("El evento con el id: " + id + " no existe");
        }
        eventixRepository.borrarEvento(id);
    }

    //filtrar por el tipo de evento
    public List <Eventix> filtrarPorTipoEvento(String tipoDeEvento){
        List<Eventix> listaTipoEventos = new ArrayList<>();
        List<Eventix> listaCompletaEventos = eventixRepository.obtenerEventos();
        for (int i = 0; i < listaCompletaEventos.size(); i++){
            Eventix e = listaCompletaEventos.get(i);
            if (e.getTipoEvento().equals(tipoDeEvento)){
                listaTipoEventos.add(e);
            }
        }
        return listaTipoEventos;
    }
}
