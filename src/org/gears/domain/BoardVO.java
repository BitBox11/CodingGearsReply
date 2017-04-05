package org.gears.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {

	
	private Integer bno, gord, parent;
	private String title, content, writer, gno ;
	private Date updatedate, regdate;
	 

	
}
