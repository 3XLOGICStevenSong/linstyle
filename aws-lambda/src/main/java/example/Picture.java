package example;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Picture")
public class Picture extends Result {

	private String chartId;

	private String type;

	private String lineColor;

	private String fillColor;

	private String reportId;

	private List<Point> pList;

	@DynamoDBHashKey(attributeName = "ChartId")
	@DynamoDBAutoGeneratedKey
	public String getChartId() {
		return chartId;
	}

	public void setChartId(String chartId) {
		this.chartId = chartId;
	}

	@DynamoDBAttribute(attributeName = "LineColor")
	public String getLineColor() {
		return lineColor;
	}

	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}

	@DynamoDBAttribute(attributeName = "FillColor")
	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	@DynamoDBAttribute(attributeName = "Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@DynamoDBAttribute(attributeName = "ReportId")
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public List<Point> getpList() {
		return pList;
	}

	public void setpList(List<Point> pList) {
		this.pList = pList;
	}

	// @DynamoDBAttribute(attributeName = "PointList")
	// public String getpList() {
	// return pList;
	// }
	//
	// public void setpList(String pList) {
	// this.pList = pList;
	// }
	@DynamoDBDocument
	public static class Point {
		private String longitude;

		private String latitude;

		@DynamoDBAttribute(attributeName = "Longitude")
		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		@DynamoDBAttribute(attributeName = "Latitude")
		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

	}
}
