package com.jsp.controle.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManipulacaoData {

	public Date converterStringData(String dataString) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 java.util.Date utilDate = null; 
	     java.sql.Date sqlDate = null;
	     try {
	            utilDate = format.parse(dataString);  
	            sqlDate = new java.sql.Date(utilDate.getTime()); 
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	        return sqlDate;
	}

}
