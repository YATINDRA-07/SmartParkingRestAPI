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
import com.example.smartParkingAPI.domain.ParkingLot;

@Repository
public class LotRepository extends JdbcDaoSupport{
	@org.springframework.beans.factory.annotation.Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	protected List<ParkingLot> elements = Collections.synchronizedList(new ArrayList<>());	
	public ParkingLot create(ParkingLot element) {
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			int rs = statement.executeUpdate("Insert into parkinglot values ('"+ element.getId()+"', '"+element.getFloor_no()+"', "+element.isStatus()+", '"+ element.getVehicle_no()+"', "+ element.getDuration()+");");
//            
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 String sql = "INSERT INTO parkinglot " +
				 "VALUES (?, ?, ?, ?, ?)" ;
				     getJdbcTemplate().update(sql, new Object[]{
				     element.getId(),element.getFloor_no(),element.isStatus(),element.getVehicle_no(),element.getDuration()
				 });
		return element;
	}
	
	public List<ParkingLot> findEmpty(){
		List<ParkingLot> empty = Collections.synchronizedList(new ArrayList<>());
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			ResultSet rs = statement.executeQuery("Select * from parkinglot where status = false;");
//            while(rs.next()){
//            	ParkingLot p = new ParkingLot();
//            	p.setId(rs.getString("id"));
//            	p.setFloor_no(rs.getString("floor_no"));
//            	p.setStatus(false);
//            	p.setVehicle_no("");
//            	p.setDuration(rs.getInt("duration"));
//            	empty.add(p);
//            }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sql = "Select * from parkinglot where status = false";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		for(Map<String, Object> row:rows){
			ParkingLot pl = new ParkingLot();
			pl.setId((String)row.get("id"));
			pl.setFloor_no((String)row.get("floor_no"));
			pl.setStatus((boolean)row.get("status"));
			pl.setVehicle_no((String)row.get("vehicle_no"));
			if(row.get("duartion") == null )
				pl.setDuration(0);
			else
				pl.setDuration((int)row.get("duartion"));
			empty.add(pl);
		}
		return empty;
		
	}
	public ParkingLot findNext() {
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			ResultSet rs = statement.executeQuery("Select * from parkinglot where status = false LIMIT 1;");
//            
//            if(rs.next()!=false) {
//            	ParkingLot p = new ParkingLot();
//            	p.setId(rs.getString("id"));
//            	p.setFloor_no(rs.getString("floor_no"));
//            	p.setStatus(false);
//            	p.setVehicle_no("");
//            	p.setDuration(rs.getInt("duration"));
//            	return p;
//            }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sql = "Select * from parkinglot where status = false LIMIT 1";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        if(rows.size()==1) {
        	ParkingLot pl = new ParkingLot();
        	pl.setId((String)rows.get(0).get("id"));
        	pl.setFloor_no((String)rows.get(0).get("floor_no"));
        	pl.setStatus((boolean)rows.get(0).get("status"));
        	pl.setVehicle_no((String)rows.get(0).get("vehicle_no"));
        	if(rows.get(0).get("duration") == null )
				pl.setDuration(0);
			else
				pl.setDuration((int)rows.get(0).get("duartion"));
        	return pl;
        }
        	
		return null;
		
	}


	public boolean update(String id, ParkingLot updatedLot) {
		// TODO Auto-generated method stub
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			ResultSet rs = statement.executeQuery("Select * from parkinglot where id = '" + id + "';");
//			if(rs.next()!=false){
//				statement.executeUpdate("Update parkinglot set status = "+ updatedLot.isStatus() + ", vehicle_no = '" + updatedLot.getVehicle_no()+ "', duration = "+ updatedLot.getDuration()+ " where id = '" + id + "';");
//            	return true;
//            }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sql = "Select count(*) from parkinglot where id = ?";
		int size = getJdbcTemplate().queryForObject(sql, new Object[]{id},Integer.class);
		if(size!=0) {
			sql = "update parkinglot set status = ?, vehicle_no = ?, duartion = ? where id = ?";
			getJdbcTemplate().update(sql,new Object[] {
					updatedLot.isStatus(),updatedLot.getVehicle_no(),updatedLot.getDuration(),id
			});
			return true;
		}
		return false;
	}


	public ParkingLot findById(String id) {
//		try {
//			Statement statement = PostgresConnection.connection.createStatement();
//			ResultSet rs = statement.executeQuery("Select * from parkinglot where id = '"+ id +"';");
//            if(rs.next()!=false) {
//            	ParkingLot p = new ParkingLot();
//            	p.setId(rs.getString("id"));
//            	p.setFloor_no(rs.getString("floor_no"));
//            	p.setStatus(rs.getBoolean("status"));
//            	p.setVehicle_no(rs.getString("vehicle_no"));
//            	p.setDuration(rs.getInt("duration"));
//            	return p;
//            }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        String sql = "select count(*) from parkinglot where id = ?";
        int size = getJdbcTemplate().queryForObject(sql, new Object[]{id}, Integer.class);
        if(size!=0) {
        	sql = "select * from parkinglot where id = ?";
        	return (ParkingLot) getJdbcTemplate().queryForObject(sql, new Object[] {id}, new RowMapper<ParkingLot>() {
        		
        		@Override
        		public ParkingLot mapRow(ResultSet rs, int rowNum) throws SQLException {
        			// TODO Auto-generated method stub
        				ParkingLot pl = new ParkingLot();
        				pl.setId(rs.getString("id"));
        				pl.setFloor_no(rs.getString("floor_no"));
        				pl.setStatus(rs.getBoolean("status"));
        				pl.setVehicle_no(rs.getString("vehicle_no"));
//				if(rs.getInt("duration") == 0 )
//					pl.setDuration(0);
//				else
        				pl.setDuration((int)rs.getInt("duartion"));
        				return pl;
        			
        		}
        	
        	});
        }
        
		return null;
	}
}
