package com.example.eventix.controller;

import com.example.eventix.model.Eventix;
import com.example.eventix.service.EventixService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventixController {
    @Autowired
    private EventixService eventixService;

    //guardar - registrar - Post
    @PostMapping
    public ResponseEntity<?> postEvento(@Valid @RequestBody Eventix eventoNuevoPost){
        try{
            eventixService.registrarEvento(eventoNuevoPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(eventoNuevoPost);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //obtener - leer - get
    @GetMapping
    public ResponseEntity<List<Eventix>> getEventos(){
        return ResponseEntity.ok(eventixService.leerEvento());
    }

    //obtenerPorId - leerPorId - getPorId
    @GetMapping("{id}")
    public Eventix getEventoId (@PathVariable int id){
        return eventixService.leerEventoPorId(id);
    }

    //actualizar - editar - Put
    @PutMapping("{id}")
    public ResponseEntity<?> putEvento (@PathVariable int id, @Valid @RequestBody Eventix eventoEditadoPut){
        try{
            eventixService.editarEvento(eventoEditadoPut);
            return ResponseEntity.ok(eventoEditadoPut);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //eliminar - borrar - delete
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvento (@PathVariable int id){
        try{
            eventixService.borrarEvento(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //filtrar por el tipo de evento
    @GetMapping("/buscar/tipo/evento")
    public ResponseEntity <List<Eventix>> buscarPorTipoEvento (@RequestParam String tipoEvento){
        return ResponseEntity.ok(eventixService.filtrarPorTipoEvento(tipoEvento));
    }

    //Manejador de errores de validacion
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manejarErroresValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return errores;
    }
}
