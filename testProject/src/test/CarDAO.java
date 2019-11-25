package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class CarDAO {
	//목록
	/*company 제조사
	 * year 연도
	 * efficiency 연비
	 * money 차가격
	 * name 차이름
	 */
	public Vector listcar() {
		Vector im = new Vector();
		Connection co=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			co=DB.oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("select company,name,year,efficiency,money ");
			sb.append(" from car order by name");
			ps=co.prepareStatement(sb.toString());
			rs=ps.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				String company = rs.getString("company");
				String name = rs.getString("name");
				String year = rs.getString("year");
				int efficiency = rs.getInt("efficiency");
				int money = rs.getInt("money");
				
				row.add(company);
				row.add(name);
				row.add(year);
				row.add(efficiency);
				row.add(money);
				im.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return im;
	}
	
	//수정(편집)
	public int updateCar(CarDTO dto) {
		int result =0;
		Connection co=null;
		PreparedStatement ps=null;
		try {
			co=DB.oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("update car set year=?,efficiency=?,money=? ");
			sb.append(" where name=?");
			ps = co.prepareStatement(sb.toString());
			ps.setString(1, dto.getYear());
			ps.setInt(2, dto.getEfficiency());
			ps.setInt(3, dto.getMoney());
			ps.setString(4, dto.getName());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
	}
	
	//추가
	public int insertCar(CarDTO dto) {
		int result=0;
		Connection co=null;
		PreparedStatement ps=null;
		try {
			co=DB.oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append("insert into car (company,name,year,efficiency,money) ");
			sb.append(" values (?,?,?,?,?)");
			ps = co.prepareStatement(sb.toString());
			ps.setString(1, dto.getCompany());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getYear());
			ps.setInt(4, dto.getEfficiency());
			ps.setInt(5, dto.getMoney());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
	}//insertCar
	
	//삭제(편집)
	public int deleteCar(String name) {
	   int result = 0;
	   Connection co=null;
	   PreparedStatement ps=null;
	   try {
		co=DB.oraConn();
		StringBuilder sb = new StringBuilder();
		sb.append("delete from car where name=?");
		ps=co.prepareStatement(sb.toString());
		ps.setString(1, name);
		result=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(ps!=null) ps.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			if(co!=null) co.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}//finally
	   return result;
	}//deleteCar
	
	//제조회사로조회
	public CarDTO viewCar(String company) {
		CarDTO dto=null;
		Connection co=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			co=DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select company,name,year,efficiency,money ");
			sb.append(" from car");
			sb.append(" where company=?");
			ps=co.prepareStatement(sb.toString());
			ps.setString(1, company);
			rs=ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String year = rs.getString("year");
				int efficiency = rs.getInt("efficiency");
				int money = rs.getInt("money");
				dto=new CarDTO(company, name, year, efficiency, money);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return dto;
	}//viewCar
	public Vector searchCar(String name) {
		Vector it =new Vector();
		Connection co =null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			co=DB.oraConn();
			StringBuilder sb =new StringBuilder();
			sb.append("select company,year,efficiency,money ");
			sb.append(" from car where name like ? order by name");
			ps=co.prepareStatement(sb.toString());
			ps.setString(1, "%"+name+"%");
			rs=ps.executeQuery();
			while(rs.next()) {
				Vector row =new Vector();
				String company =rs.getString("company");
//				String name = rs.getString("name");
				String year =rs.getString("year");
				int efficiency = rs.getInt("efficiency");
				int money =rs.getInt("money");
				
				row.add(company);
				row.add(name);
				row.add(year);
				row.add(efficiency);
				row.add(money);
				it.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return it;
	}

}
