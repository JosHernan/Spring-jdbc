package com.curso.spring;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.CannotGetCciConnectionException;

import com.curso.spring.dao.AdminDao;
import com.curso.spring.pojo.Admin;


public class MainApp {
	
	public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
		
		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");

		
		Timestamp ts=new Timestamp(new Date().getTime());
		//Admin admin =new Admin();
		
	/*	admin.setCargo("gerente");
		admin.setNombre("martin");
		admin.setFechaCreacion(ts);
		
	*/
		try {
			//adminDao.save(admin);
			//System.out.println(adminDao.findById(1));
			/*List<Admin> admins=adminDao.findAll();
			
			for (Admin admin2 : admins) {
				System.out.println(admin2);
			}
			*/
			
		/*	System.out.println(adminDao.findById(1));
			System.out.println(adminDao.findById(5));
			
			System.out.println(adminDao.findByNombre("r").toString());*/
			
			
			List<Admin> admins =new ArrayList<Admin>();
			admins.add(new Admin(50,"Raul","jefe de Ing",ts));
			admins.add(new Admin(51,"Roberto","Administracion",ts));
			admins.add(new Admin(32,"Jorge","Contador",ts));
		
			
			int []vals =adminDao.saveAll(admins);
			
			for (int i : vals) {
			System.out.println("Filas afectadas pata esta operacion:"+  i);	
			}
			
			
			
		} catch (CannotGetCciConnectionException ex) {
			ex.printStackTrace();
		}
		catch (DataAccessException ex) {
			ex.printStackTrace();
		}
		
		
		((ClassPathXmlApplicationContext)applicationContext).close();	
	}

}
