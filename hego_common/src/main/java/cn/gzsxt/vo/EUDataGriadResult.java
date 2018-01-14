package cn.gzsxt.vo;

import java.util.List;

public class EUDataGriadResult {
	// 分页信息中的结果总数
	private long total;
	// 分页信息中的所有结果
	private List<?> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
