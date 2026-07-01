package com.heyhey.backend.service;

import com.heyhey.backend.model.TareasEnviadas;
import com.heyhey.backend.repository.TareasEnviadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareasEnviadasService {
    @Autowired
    private TareasEnviadasRepository tareasEnviadasRepository;

    public TareasEnviadas guardarTareasEnviadas(TareasEnviadas tareasEnviadas){
        return tareasEnviadasRepository.save(tareasEnviadas);
    }

    public List<TareasEnviadas> obtenerTodas(){
        return tareasEnviadasRepository.findAll();
    }

    public TareasEnviadas obtenerId(Integer id){
        return tareasEnviadasRepository.findById(id).orElse(null);
    }

    public void elimminarTareaEnviada(Integer id){
        tareasEnviadasRepository.deleteById(id);
    }

    public List<TareasEnviadas> obtenerPorEstado(Integer estado){
        return tareasEnviadasRepository.findByEstado(estado);
    }

    public List<TareasEnviadas> obtenerPorEmpleado(Integer idEmpleado){
        return tareasEnviadasRepository.findByEmpleado_IdEmpleado(idEmpleado);
    }
}
