package com.example.smartParkingAPI.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.smartParkingAPI.domain.Vehicles;

@Repository
public class VehicleRepository extends JdbcDaoSupport{
	@org.springframework.beans.factory.annotation.Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	protected List<Vehicles> elements = Collections.synchronizedList(new ArrayList<>());

	public List<Vehicles> findAll() {
		List<Vehicles> vehicles = Collections.synchronizedList(new ArrayList<>());
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			ResultSet rs = statement.executeQuery("Select * from vehicles;");
//            while(rs.next()){
//            	Vehicles v = new Vehicles();
//            	v.setId(rs.getString("vehicle_no"));
//            	v.setLot_name(rs.getString("lot_name"));
//            	v.setMallId(rs.getString("mallId"));
//            	v.setDuration(rs.getInt("duration"));
//            	vehicles.add(v);
//            }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sql = "select * from vehicles";
		List<Map<String,Object>> rows = getJdbcTemplate().queryForList(sql);
		for(Map<String,Object> row : rows) {
			Vehicles vc = new Vehicles();
			vc.setDuration((int)row.get("duration"));
			vc.setVehicle_no((String)row.get("vehicle_no"));
			vc.setLot_name((String)row.get("lot_name"));
			vc.setMallId((String)row.get("mallid"));
			vehicles.add(vc);
		}
		return vehicles;
	}
	
	public Vehicles create(Vehicles element) {
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			int rs = statement.executeUpdate("Insert into vehicles values ('"+ element.getId()+"', '"+element.getLot_name()+"', '"+ element.getMallId()+"', "+ element.getDuration()+");");
//            
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sql = "insert into vehicles values(?,?,?,?)";
		getJdbcTemplate().update(sql,new Object[] {
			element.getVehicle_no(),element.getLot_name(),element.getMallId(),element.getDuration()	
		});
		return element;
	}
	
	
	public boolean delete(String vehicle_no) {
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			int rs = statement.executeUpdate("Delete from vehicles where vehicle_no = '" + vehicle_no + "';");
//			if(rs>0)
//				return true;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sql = "select count(*) from vehicles where vehicle_no = ?";
		int size = getJdbcTemplate().queryForObject(sql, new Object[] {vehicle_no}, Integer.class);
		if(size!=0) {
			sql = "delete from vehicles where vehicle_no = ?";
			getJdbcTemplate().update(sql, new Object[] {vehicle_no});
			return true;
		}
		return false;
	}
	
	public Vehicles findById(String id) {
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			ResultSet rs = statement.executeQuery("Select * from vehicles where vehicle_no = '"+ id+"';");
//			if(rs.next()!=false){
//            	Vehicles v = new Vehicles();
//            	v.setId(rs.getString("vehicle_no"));
//            	v.setLot_name(rs.getString("lot_name"));
//            	v.setMallId(rs.getString("mallId"));
//            	v.setDuration(rs.getInt("duration"));
//            	return v;
//            }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch blockvehicles
//			e.printStackTrace();
//		}
		String sql = "select count(*) from vehicles where vehicle_no = ?";
		int size = getJdbcTemplate().queryForObject(sql, new Object[] {id}, Integer.class);
		if(size!=0) {
			sql = "select * from vehicles where vehicle_no = ?";
			return (Vehicles) getJdbcTemplate().queryForObject(sql, new Object[] {id}, new RowMapper<Vehicles>() {

				@Override
				public Vehicles mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Vehicles vc = new Vehicles();
					vc.setDuration(rs.getInt("duration"));
					vc.setVehicle_no(rs.getString("vehicle_no"));
					vc.setLot_name(rs.getString("lot_name"));
					vc.setMallId(rs.getString("mallid"));
					return vc;
				}
				
			});
		}
		return null;
	}
	
	public boolean update(String id, Vehicles updated) {
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			ResultSet rs = statement.executeQuery("Select * from vehicles where vehicle_no = '" + id + "';");
//			if(rs.next()!=false){
//				statement.executeUpdate("Update vehicles set lot_name = '"+ updated.getLot_name()+ "' , mallid = '" +updated.getMallId()+ "', duration = "+ updated.getDuration()+ " where vehicle_no = '" + id + "';");
//	        	return true;
//	        }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sql = "select count(*) from vehicles where vehicle_no = ?";
		int size = getJdbcTemplate().queryForObject(sql, new Object[] {id}, Integer.class);
		if(size!=0) {
			sql = "update vehicles set lot_name = ?, mallid = ?, duration = ? where vehicle_no = ?";
			getJdbcTemplate().update(sql, new Object[] {
					updated.getLot_name(),updated.getMallId(),updated.getDuration(),id
			});
			return true;
		}
		return false;
	}
}

