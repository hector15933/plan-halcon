package org.plan.halcon.security.service.securityservice.controller;



import org.plan.halcon.security.service.securityservice.model.entity.Usuario;
import org.plan.halcon.security.service.securityservice.model.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuariosController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("/empleados/listar")
	public List<Usuario> listar() {
		List<Usuario> listas = usuarioService.findAll();
		return listas;
	}
	
	
	//@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Map<String,Object> response = new HashMap<>();
		Usuario empleado = null;
		try{
			 empleado = usuarioService.findById(id);	
		}catch(DataAccessException e){
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(empleado==null) {
			response.put("mensaje", "El Cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(empleado,HttpStatus.OK);		
	}
	
	@PostMapping("/empleados/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Usuario empleado, BindingResult bindingResult) {
		Map<String,Object> response = new HashMap<>();
		
		if(bindingResult.hasErrors()) {
			
			List<String> errors= new ArrayList<>();
			for(FieldError err : bindingResult.getFieldErrors()){
				errors.add("El campo"+err.getField()+" "+err.getDefaultMessage());
			}
			
					response.put("errors", response);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		Usuario empleadoNew = null;
		try {
			empleadoNew= usuarioService.save(empleado);
			
		}catch(DataAccessException e){
			
			response.put("message", "Error al realizar insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Creado correctamente");
		response.put("cliente", empleadoNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);	
	}
	
	
	@PutMapping("/empleados/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> editar(@RequestBody Usuario empleado,@PathVariable Long id){
		Usuario empleadoActual= usuarioService.findById(id);
		Usuario empleadoUpdated = null;

		Map<String,Object> response= new HashMap<>();
		if(empleadoActual == null) {	
			response.put("mensaje","Error: No se puede editar el  ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			/*empleadoActual.setApellidos(empleado.getApellidos());
			empleadoActual.setNombres(empleado.getNombres());
			empleadoActual.setDni(empleado.getDni());
			empleadoActual.setDireccion(empleado.getDireccion());
			empleadoActual.setEmail(empleado.getEmail());*/
			empleadoUpdated= usuarioService.save(empleado);	
		}catch(DataAccessException e) {
			response.put("message", "Error al actualizar datos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
		response.put("message", "Actualizado correctamente");
		response.put("empleado", empleadoUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);	
	}
	
	
	//@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/empleados/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			usuarioService.delete(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al eliminar datos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		response.put("message", "Eliminado correctamente");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);	
	}
	
	@PutMapping("/empleados/eliminar2/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> editar2(@PathVariable Long id){
		Usuario empleadoActual= usuarioService.findById(id);
		Usuario empleadoUpdated = null;

		Map<String,Object> response= new HashMap<>();
		if(empleadoActual == null) {	
			response.put("mensaje","Error: No se puede editar el  ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			/*empleadoActual.setApellidos(empleado.getApellidos());
			empleadoActual.setNombres(empleado.getNombres());
			empleadoActual.setDni(empleado.getDni());
			empleadoActual.setDireccion(empleado.getDireccion());
			empleadoActual.setEmail(empleado.getEmail());*/
			//empleadoActual.setEnabled(false);
			empleadoActual.setEnabled(false);
			empleadoUpdated= usuarioService.save(empleadoActual);	
		}catch(DataAccessException e) {
			response.put("message", "Error al actualizar datos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
		response.put("message", "Eliminado correctamente");
		response.put("empleado", empleadoUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);	
		
	}
	
	
	@PostMapping("/empleados/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,@RequestParam("id") Long id){
		
		
		Map<String,Object> response = new HashMap<>();
		Usuario usuario=usuarioService.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo=UUID.randomUUID().toString()+"_"+ archivo.getOriginalFilename().replace(" ", "");
			
			Path rutaArchivo = Paths.get("uploads/empleados").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				response.put("message", "Error al subir la imagen: "+nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			
			String nombreAnterior=usuario.getFoto();
			
			if(nombreAnterior!=null && nombreAnterior.length()>0) {
				Path rutaArchivoAnterior = Paths.get("uploads/empleados").resolve(nombreAnterior).toAbsolutePath();
				//Convierto el path a un tipo archivo
				File archivoFotoAnterior = rutaArchivoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
				
			}
			
			
			
			usuario.setFoto(nombreArchivo);
			usuarioService.save(usuario);
			
			response.put("usuario", usuario);
			response.put("mensaje","Imagen subida al sistema correctamente :" + nombreArchivo);
		}
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);	
	}
	
	@GetMapping("/empleados/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("uploads/empleados").resolve(nombreFoto).toAbsolutePath();
		
		Resource recurso=null;
		try {
			recurso=new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: "+nombreFoto);
		}
		
		HttpHeaders cabecera= new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+recurso.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	
}
