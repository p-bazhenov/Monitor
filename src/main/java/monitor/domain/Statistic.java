package monitor.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Statistic {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false)
	private Long gameId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date datestamp;
	
	@Column(nullable=false, length=10)
	private String projectName;
	
	@Column(columnDefinition="JSON",nullable=false)
	private String statisticJSON;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Date getDatestamp() {
		return datestamp;
	}

	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatisticJSON() {
		return statisticJSON;
	}

	public void setStatisticJSON(String statisticJSON) {
		this.statisticJSON = statisticJSON;
	}
	
}
