package monitor.domain;

public class ShortResults {

	private int battlesCount;
	private long lastBattleTime;
	
	public ShortResults() {
		super();
	}
	
	public ShortResults(int battlesCount, long lastBattleTime) {
		super();
		this.battlesCount = battlesCount;
		this.lastBattleTime = lastBattleTime;
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
	
	
	
}
