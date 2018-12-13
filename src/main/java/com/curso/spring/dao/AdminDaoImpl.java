package com.curso.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.pojo.Admin;
import com.curso.spring.pojo.AdminRowMapper;

@Component("adminDao")
public class AdminDaoImpl implements AdminDao {
    private NamedParameterJdbcTemplate jdbcTemplate;//plantilla que acepta comodines
    
    @Autowired
    private void setDataSource(DataSource dataSource) {
    	this.jdbcTemplate= new  NamedParameterJdbcTemplate(dataSource);
    	  	
    }
    

	public boolean save(Admin admin) {
		// TODO Auto-generated method stub
		
//		MapSqlParameterSource paramMap= new MapSqlParameterSource();
//		paramMap.addValue("nombre", admin.getNombre());
//		paramMap.addValue("cargo", admin.getCargo());
//		paramMap.addValue("fechaCreacion", admin.getFechaCreacion());
		
		BeanPropertySqlParameterSource paramMap=new BeanPropertySqlParameterSource(admin);// es lo mismo de arriba
		
		return jdbcTemplate.
			update("insert into Admin(nombre,cargo,fechaCreacion) values(:nombre, :cargo, :fechaCreacion)",paramMap)==1;
		
	}


	public List<Admin> findAll() {
		
		
		return jdbcTemplate.query("SELECT * FROM Admin",new RowMapper<Admin>() {
			
			public Admin mapRow(ResultSet rs ,int rowNum) throws SQLException{
				 Admin admin =new Admin();
				 admin.setIdAd(rs.getInt("idAd"));
				 admin.setCargo(rs.getString("cargo"));
				 admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				 admin.setNombre(rs.getString("nombre"));
				return admin;
			}
		});
	}


	public Admin findById(int id) {
		
		/*return (Admin) jdbcTemplate.query("SELECT * FROM Admin where idAd=:idAd",
				new MapSqlParameterSource("idAd", id), new AdminRowMapper());*/
		
		return jdbcTemplate.queryForObject("SELECT * FROM Admin where idAd=:idAd",
				
				new MapSqlParameterSource("idAd", id), new AdminRowMapper());
	}


	public List<Admin> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM Admin where nombre like :nombre", new MapSqlParameterSource("nombre", "%"+ nombre +"%"), new AdminRowMapper());
	}


	public boolean update(Admin admin) {
		// TODO Auto-generated method stub
		
		return jdbcTemplate.update("UPDATE Admin set nombre=:nombre,cargo=:cargo,fechaCreacion=:fechaCreacion WHERE idAd:=idAd", 
				
				new BeanPropertySqlParameterSource(admin))==1;
	}


	public boolean delete(int id) {
		// TODO Auto-generated method stub
             return 
            		 jdbcTemplate.update("DELETE FROM Admin WHERE idAd=:idAd", 
				
            				new MapSqlParameterSource("idAd", id))==1;
	}

    @Transactional
	public int[] saveAll(List<Admin> admins) {
		
		SqlParameterSource[] batchArgs=SqlParameterSourceUtils.createBatch(admins.toArray());
		
		
		return jdbcTemplate.batchUpdate("insert into Admin(idAd,nombre,cargo,fechaCreacion) values(:idAd,:nombre, :cargo, :fechaCreacion)", batchArgs);
		
		
	}

}
