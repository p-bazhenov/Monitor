package monitor.domain;

public class ShortResults {

	private int battlesCount;
	private long lastBattleTime;
	private String dbDate;
	
	public ShortResults() {
		super();
	}
	
	public ShortResults(int battlesCount, long lastBattleTime, String dbDate) {
		super();
		this.battlesCount = battlesCount;
		this.lastBattleTime = lastBattleTime;
		this.dbDate = dbDate;
	}
	
	public int getBattlesCount() {
		return battlesCount;
	}
	public void setBattlesCount(int battlesCount) {
		this.battlesCount = battlesCount;
	}
	public long getLastBattleTime() {
		return lastBattleTime;
	}
	public void setLastBattleTime(long lastBattleTime) {
		this.lastBattleTime = lastBattleTime;
	}

	public String getDbDate() {
		return dbDate;
	}

	public void setDbDate(String dbDate) {
		this.dbDate = dbDate;
	}
	
}
