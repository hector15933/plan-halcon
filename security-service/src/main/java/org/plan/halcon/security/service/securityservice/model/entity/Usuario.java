package org.plan.halcon.security.service.securityservice.model.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombres;
	@Column(nullable=false)
	private String apellidos;
	@Column(unique = true,length=40)
	private String username;
	@Column(length=60)
	private String password;
	
	private Boolean enabled;
	private String foto;
	private String firma;
	@Column(nullable=false)
	private Integer dni;
	@Email
	@Column(unique=true)
	private String email;
	private Integer telefono;
	@Column(nullable=false)
	private String nacionalidad;
	private Boolean sexo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_nacimiento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_ingreso;
	private Double salario;
	private String direccion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	@Transient
	private Integer toggle;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Role> roles;
	
	@PrePersist
	void preInsert() {
	   if (this.createAt == null)
	       this.createAt = new Date();
	   if (this.username == null)
	       this.username = this.email;
	   if (this.password == null)
	       this.password = String.valueOf(this.dni);
	   if(this.foto==null)
		   this.foto="";
	   if(this.enabled==null)
		   this.enabled=true;
	   
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	public Boolean getSexo() {
		return sexo;
	}
	public void setSexo(Boolean sexo) {
		this.sexo = sexo;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
		
	public Integer getToggle() {
		return 0;
		
	}

}
