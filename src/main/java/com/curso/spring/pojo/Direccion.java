package com.curso.spring.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // es una clase que va a hacer inyectada
public class Direccion {
	
	private String calle;
	private String cp;
	
	public Direccion() {
		
	}

	
	public String getCalle() {
		return calle;
	}

	@Autowired
	public void setCalle(@Value("San Pedro garza")String calle) {
		this.calle = calle;
	}


	public String getCp() {
		return cp;
	}

  @Autowired
	public void setCp(@Value("98983")String cp) {
		this.cp = cp;
	}


	public Direccion(String calle, String cp) {
		super();
		this.calle = calle;
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", cp=" + cp + "]";
	}
	
	
	
	

}
