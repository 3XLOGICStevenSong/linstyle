package example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;

public class PictureHandler {

	public static void main(String[] args) throws Exception {
		PictureHandler rh = new PictureHandler();
		Picture report = new Picture();
		report.setReportId("1");
		// report.setLineColor("#000000");
		//
		// List< Point> point=new ArrayList<Point>();
		// Point point1=new Point();
		// point1.setLatitude("111.2");
		// point1.setLongitude("43.59");
		// point.add(point1);
		// Point point2=new Point();
		// point2.setLatitude("120.5");
		// point2.setLongitude("50.1");
		// point.add(point2);
		// //String list="[{'longitude': '123.6', 'latitude':
		// '42.68'},{'longitude': '123.7', 'latitude': '42.7' },{'longitude':
		// '23.8', 'latitude': '42.8'}]";
		// report.setpList(point);

		// report.setChartId("a9d69513-7ac1-4744-9581-d934c81023ab");
		// report.setLatitude(42.6);
		// report.setLongitude(141.1);
		// report.setMemo("备注");
		// report.setReason_area("hhah");
		// report.setTitle("test1");
		// report.setMemo("momo1");
		// rh.reportSaveHandler(report, null);
		// report.setMethodFlag("0");
		// rh.pictureSaveHandler(report, null);
		rh.pictureQueryHandler(report, null);
	}

	/**
	 * 图片信息添加
	 * 
	 * @param picture
	 * @param context
	 * @return
	 */
	public Result pictureSaveHandler(Picture picture, Context context) {
		// LambdaLogger logger = context.getLogger();
		Result result = new Result();
		try {
			// 认证
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
			DynamoDBMapper mapper = new DynamoDBMapper(client);
			//添加信息
			mapper.save(picture);
			//设置返会码
			result.setCode("0");
			return result;

		} catch (AmazonServiceException ase) {
			System.err.println("Could not complete operation");
			System.err.println("Error Message:  " + ase.getMessage());
			System.err.println("HTTP Status:    " + ase.getStatusCode());
			System.err.println("AWS Error Code: " + ase.getErrorCode());
			System.err.println("Error Type:     " + ase.getErrorType());
			System.err.println("Request ID:     " + ase.getRequestId());
			result.setMsg("失败");
			return result;
		} catch (AmazonClientException e) {
			// logger.log("error : " + e.getMessage());
			result.setMsg("失败");
			return result;
		}
	}

	/**
	 * 所画图型信息删除
	 * 
	 * @param picture
	 * @param context
	 * @return
	 */

	public Result pictureDeleteHandler(Picture picture, Context context) {
		// LambdaLogger logger = context.getLogger();
		Result result = new Result();
		try {
			// 认证
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			DynamoDBMapper mapper = new DynamoDBMapper(client);
			// Report itemRetrieved = mapper.load(Report.class,
			// report.getReportId());
			// 对象映射
			// CommonUtil.reflectClass(report, itemRetrieved);
			mapper.delete(picture);

			result.setCode("0");
			return result;

		} catch (AmazonServiceException ase) {
			System.err.println("Could not complete operation");
			System.err.println("Error Message:  " + ase.getMessage());
			System.err.println("HTTP Status:    " + ase.getStatusCode());
			System.err.println("AWS Error Code: " + ase.getErrorCode());
			System.err.println("Error Type:     " + ase.getErrorType());
			System.err.println("Request ID:     " + ase.getRequestId());
			result.setMsg("失败");
			return result;
		} catch (AmazonClientException e) {
			// logger.log("error : " + e.getMessage());
			result.setMsg("失败");
			return result;
		}
	}

	/**
	 * 所画图形信息检索
	 * 
	 * @param picture
	 * @param context
	 * @return
	 */
	public PictureList pictureQueryHandler(Picture picture, Context context) {
		PictureList result = new PictureList();
		try {
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			// LambdaLogger logger = context.getLogger();
			// logger.log("received : " + report.getTitle());

			DynamoDBMapper mapper = new DynamoDBMapper(client);
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":val1", new AttributeValue().withS(picture.getReportId()));
			// eav.put(":val2", new AttributeValue().withS(""));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
					.withFilterExpression("ReportId = :val1").withExpressionAttributeValues(eav);

			int numberOfThreads = 1;
			List<Picture> scanResult = mapper.parallelScan(Picture.class, scanExpression, numberOfThreads);
			List<Picture> paramList = null;
			if (scanResult != null && scanResult.size() > 0) {
				paramList = new ArrayList<Picture>();
				for (Picture bicycle : scanResult) {
					Picture param = new Picture();
					CommonUtil.reflectClass(bicycle, param);

					paramList.add(param);
				}
				result.setPointList(paramList);
				// mapper.save(report);
			}
			result.setCode("0");
			return result;
		} catch (Exception e) {
			result.setMsg("获取信息失败");
			return result;
		}
	}

}
