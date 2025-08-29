package org.datacoins.kinvana.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.datacoins.kinvana.domio.service.IClienteService;
import org.datacoins.kinvana.persistence.entity.Cliente;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired
    IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.clientes = this.clienteService.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente(){

        this.clienteSeleccionado = new Cliente();
    }

    public void guardarCliente(){
        logger.info("Cliente a guardar: "+this.clienteSeleccionado);

        if (this.clienteSeleccionado.getCodigoCliente() == null){
            this.clienteService.gurdarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente agregado"));
        }
        else{
            this.clienteService.gurdarCliente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente Actualizado"));
        }

        //oculatr la ventana modal
        PrimeFaces.current().executeScript("PF('VentanaModalCliente').hide()");
        //Actualizar la tabla con un metodo AJAX
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje_emergente",
                                    "formulario-clientes:tabla-clientes");
        //reset dek ckienteSeleccionado
        this.clienteSeleccionado = null;

    }

    public void eliminarCliente(){
        //Mostrar en consola
        logger.info("Cliente a eliminar: "+this.clienteSeleccionado);
        //llamar a nuestra servicio de eliminat de cliente
        this.clienteService.eliminarCliente(clienteSeleccionado);
        //eliminar de la lista cliente
        this.clientes.remove(clienteSeleccionado);
        //limpiar nuestro cliente seleccionado
        this.clienteSeleccionado=null;
        //enviar un mensaje emergente
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente eliminado"));
        //actualizar la tabla con AJAX
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje_emergente",
                "formulario-clientes:tabla-clientes");
    }

}
