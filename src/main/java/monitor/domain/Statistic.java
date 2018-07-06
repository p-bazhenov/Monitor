package monitor.domain;

import javax.persistence.*;

@Entity
public class Statistic {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false)
	private Long gameId;
	
	@Column(nullable=false)
	private String datestamp;
	
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

	public String getDatestamp() {
		return datestamp;
	}

	public void setDatestamp(String datestamp) {
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
