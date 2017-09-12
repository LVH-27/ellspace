package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javafx.scene.control.CheckBox;

public class InsertNewBusinessHours {
	
	private static Time timeBegin;
	private static Time timeEnd;
	
	private static PreparedStatement pstmtBusiness;
	
	public static void insert(Connection con, int libraryID, ArrayList<String> beginTime, ArrayList<String> endTime, ArrayList<CheckBox> checkBoxList) throws SQLException {
		String queryBusiness = "INSERT INTO public.\"BusinessHours\" VALUES(?, ?, ?, ?, ?)";
		
		pstmtBusiness = con.prepareStatement(queryBusiness);
		
		for (int j = 0; j < beginTime.size(); ++j) {
			
			timeBegin = java.sql.Time.valueOf(beginTime.get(j) + ":00");
			timeEnd = java.sql.Time.valueOf(endTime.get(j) + ":00");
			
			pstmtBusiness.setInt(1, libraryID);
			pstmtBusiness.setInt(2, j + 1);
			
			if (checkBoxList.get(j).isSelected()) {
				pstmtBusiness.setBoolean(3, false);
			} else {
				pstmtBusiness.setBoolean(3, true);
			}
			
			pstmtBusiness.setTime(4, timeBegin);
			pstmtBusiness.setTime(5, timeEnd);
			
			pstmtBusiness.executeUpdate(); 
			
		}
	}
}
