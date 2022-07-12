package id.co.nds.catalogue.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SaleIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor ssci,
			Object o) throws HibernateException {
		// TODO Auto-generated method stub
		Connection connection = ssci.connection();
		
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT COUNT(*) AS seq FROM tx_sale ");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int seq = rs.getInt("seq") + 1;
				String code = String.format("S%09d", seq);
				System.out.println("Generated Stock Code: " + code);
				return code;
			}
			else {
				throw new HibernateException("Generator is failed generated id");
			 }
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
}
