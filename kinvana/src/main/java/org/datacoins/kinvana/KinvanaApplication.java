package org.datacoins.kinvana;

import org.datacoins.kinvana.domio.service.ClienteService;
import org.datacoins.kinvana.domio.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.datacoins.kinvana.persistence.entity.Cliente;


import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class KinvanaApplication implements CommandLineRunner {

	//inyeccion de dependencias
	@Autowired
	private IClienteService clienteService;
	//Crear objeto (herramienta) loger
	private static final Logger logger = LoggerFactory.getLogger(KinvanaApplication.class);
	//crear un objeto String para saltos de line
	String sl = System.lineSeparator();

	public static void main(String[] args) {
	logger.info("Aqui inicia nuestra aplicacion");
		SpringApplication.run(KinvanaApplication.class, args);
	logger.info("Aqui termuna toda la aplicacion");
	}

	@Override
	public void run(String ... args) throws Exception{
		KinvanaClienteApp();
	}

	private void KinvanaClienteApp() {
		logger.info("+++++++Aplicacion de Registro de CLiente+++++++");
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){

			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(sl);
		}
	}

	private int mostrarMenu(Scanner consola) {
		logger.info("""
				\n ***Aplicacion ***
				1. Listar todos los clientes.
				2. buscar cliente por codigo.
				3. Agregar nuevo Cliente.
				4. Modificar Cliente.
				5. eliminar Cliente.
				6.Salir. 
				
				
				Elije una Opcion: \s""");

		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion) {
		var salir = false;
		switch(opcion){
			case 1 -> {
						logger.info(sl+"**Listado de todos los clientes***");
						List<Cliente> clientes = clienteService.listarClientes();
						clientes.forEach( cliente -> logger.info(cliente.toString()+sl));

						}
			case 2 -> {
				        logger.info(sl + "Buscar Cliente por su código" + sl);
						var codigo = Integer.parseInt(consola.nextLine());
						Cliente cliente = clienteService.buscarClienteProId(codigo);
						if (cliente != null) {
							logger.info("Cliente encontrado: " + sl + cliente + sl);
						} else {
							logger.info("Cliente no encontrado: " + sl + cliente + sl);
							}
						}
			case 3 -> {
							logger.info(sl+"***Agregar nuevo cliente***"+sl);
							logger.info("Ingrese el nombre: ");
							var nombre = consola.nextLine();
							logger.info("Ingrese el apellido: ");
							var apellido = consola.nextLine();
							logger.info("Ingrese el telefono: ");
							var telefono = consola.nextLine();
							logger.info("Ingrese el correo: ");
							var correo = consola.nextLine();
							logger.info("Ingrese el genero: ");
							var genero = consola.nextLine();
							logger.info("Ingrese la edad: ");
							var edad = Integer.parseInt(consola.nextLine());
							var cliente = new Cliente();
							cliente.setNombre(nombre);
							cliente.setApellido(apellido);
							cliente.setTelefono(telefono);
							cliente.setCorreo(correo);
							cliente.setGenero(genero);
							cliente.setEdad(edad);
							clienteService.gurdarCliente(cliente);
							logger.info("Cliente agregado: "+sl +cliente +sl);
						}
			case 4 -> {
							logger.info(sl+"*** Modificar ciente***"+sl);
							logger.info("Ingrese el codigo del cliente a editar: ");
							var codigo = Integer.parseInt(consola.nextLine());
							Cliente cliente = clienteService.buscarClienteProId(codigo);
							if (cliente != null){
								logger.info("Ingrese el nombre: ");
								var nombre = consola.nextLine();
								logger.info("Ingrese el apellido: ");
								var apellido = consola.nextLine();
								logger.info("Ingrese el telefono: ");
								var telefono = consola.nextLine();
								logger.info("Ingrese el correo: ");
								var correo = consola.nextLine();
								logger.info("Ingrese el genero: ");
								var genero = consola.nextLine();
								logger.info("Ingrese la edad: ");
								var edad = Integer.parseInt(consola.nextLine());
								cliente.setNombre(nombre);
								cliente.setApellido(apellido);
								cliente.setTelefono(telefono);
								cliente.setCorreo(correo);
								cliente.setGenero(genero);
								cliente.setEdad(edad);
								clienteService.gurdarCliente(cliente);
								logger.info("Cliente agregado: "+sl +cliente +sl);
								}else{
								logger.info("Cliente NO encontrado: "+sl+cliente+sl);
							}

						}
			case 5 -> {
							logger.info(sl+"***Eliminar Cliente***"+sl);
							logger.info("Ingerese el codigo de cliente a eliminar");
							var codigo = Integer.parseInt(consola.nextLine());
							var cliente = clienteService.buscarClienteProId(codigo);
							if (cliente != null){
								clienteService.eliminarCliente(cliente);
								logger.info("Cliente eliminado, adios: "+sl+cliente+sl);
							}else{
								logger.info("Cliente MO econtrado"+ sl + cliente + sl);
							}
						}


			case 6 -> {
							logger.info("Hasta protonto vaquero" + sl + sl);
							salir = true;
						}

			default -> logger.info("Opcion no validad");
		}
		return salir;
	}

}
