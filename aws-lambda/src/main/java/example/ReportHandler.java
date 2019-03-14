package example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class ReportHandler {

	public static void main(String[] args) throws Exception {
		ReportHandler rh = new ReportHandler();
		Report report = new Report();
		 report.setReportId("1");
		// report.setLatitude(42.6);
		// report.setLongitude(141.1);
		// report.setMemo("备注");
		// report.setReason_area("hhah");
		// report.setTitle("test1");
		// report.setMemo("momo1");
		// report.setStartDate("2017-10-09");
		// report.setEndDate("2017-10-12");
		//report.setStartDate("");
		//report.setEndDate("");
		//report.setUserId("1");
		//rh.reportPcQueryHandler(report, null);
		rh.reportQueryHandler(report, null);
		// rh.reportSaveHandler(report, null);
		// report.setMethodFlag("0");
		// rh.reportQueryHandler(report, null);
	}

	/**
	 * 采访信息录入
	 * 
	 * @param report
	 * @param context
	 * @return
	 */

	public Result reportSaveHandler(Report report, Context context) {
		// LambdaLogger logger = context.getLogger();
		Result result = new Result();
		try {
			// 认证
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
			DynamoDBMapper mapper = new DynamoDBMapper(client);
			//通过ID获取
			Report itemRetrieved = mapper.load(Report.class, report.getReportId());
			// 对象映射
			CommonUtil.reflectClass(report, itemRetrieved);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			itemRetrieved.setUpdate_time(df.format(new Date()));
			//添加信息
			mapper.save(itemRetrieved);
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
	 * 通过reportId获取报告信息
	 * 
	 * @param report
	 * @param context
	 * @return
	 */
	public Report reportQueryHandler(Report report, Context context) {
		Report result = new Report();
		try {
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			// LambdaLogger logger = context.getLogger();
			// logger.log("received : " + report.getTitle());

			DynamoDBMapper mapper = new DynamoDBMapper(client);

			Report item = mapper.load(Report.class, report.getReportId());

			if (item != null) {
				if ("0".equals(report.getMethodFlag())) {
					result.setRequirement_allday(item.getRequirement_allday());
					result.setRequirement_area(item.getRequirement_area());
					result.setRequirement_open(item.getRequirement_open());
					result.setRequirement_wine(item.getRequirement_wine());
					result.setRequirement_tobacco(item.getRequirement_tobacco());
					result.setRequirement_tobacco_dis(item.getRequirement_tobacco_dis());
					result.setRequirement_competitor_count(item.getRequirement_competitor_count());
				} else if ("1".equals(report.getMethodFlag())) {
					result.setRoad_noentry_endTime(item.getRoad_noentry_endTime());
					result.setRoad_noentry_startTime(item.getRoad_noentry_startTime());
					result.setRoad_nostop_endTime(item.getRoad_nostop_endTime());
					result.setRoad_nostop_startTime(item.getRoad_nostop_startTime());
					result.setRoad_nowait_endTime(item.getRoad_nowait_endTime());
					result.setRoad_nowait_startTime(item.getRoad_nowait_startTime());
					result.setRoad_oneway_endTime(item.getRoad_oneway_endTime());
					result.setRoad_oneway_startTime(item.getRoad_oneway_startTime());
				} else if ("2".equals(report.getMethodFlag())) {
					result.setTraffic_car_11_negative(item.getTraffic_car_11_negative());
					result.setTraffic_car_11_positive(item.getTraffic_car_11_positive());
					result.setTraffic_car_12_negative(item.getTraffic_car_12_negative());
					result.setTraffic_car_12_positive(item.getTraffic_car_12_positive());
					result.setTraffic_car_13_negative(item.getTraffic_car_13_negative());
					result.setTraffic_car_13_positive(item.getTraffic_car_13_positive());
					result.setTraffic_car_14_negative(item.getTraffic_car_14_negative());
					result.setTraffic_car_14_positive(item.getTraffic_car_14_positive());
					result.setTraffic_car_21_negative(item.getTraffic_car_21_negative());
					result.setTraffic_car_21_positive(item.getTraffic_car_21_positive());
					result.setTraffic_car_21_negative(item.getTraffic_car_21_negative());
					result.setTraffic_car_22_positive(item.getTraffic_car_22_positive());
					result.setTraffic_car_22_negative(item.getTraffic_car_22_negative());
					result.setTraffic_car_23_positive(item.getTraffic_car_23_positive());
					result.setTraffic_car_23_negative(item.getTraffic_car_23_negative());
					result.setTraffic_car_24_positive(item.getTraffic_car_24_positive());
					result.setTraffic_car_24_negative(item.getTraffic_car_24_negative());
					result.setTraffic_car_31_positive(item.getTraffic_car_31_positive());
					result.setTraffic_car_31_negative(item.getTraffic_car_31_negative());
					result.setTraffic_car_32_positive(item.getTraffic_car_32_positive());
					result.setTraffic_car_32_negative(item.getTraffic_car_32_negative());
					result.setTraffic_car_33_positive(item.getTraffic_car_33_positive());
					result.setTraffic_car_33_negative(item.getTraffic_car_33_negative());
					result.setTraffic_car_34_positive(item.getTraffic_car_34_positive());
					result.setTraffic_car_34_negative(item.getTraffic_car_34_negative());
					result.setTraffic_car_41_positive(item.getTraffic_car_41_positive());
					result.setTraffic_car_41_negative(item.getTraffic_car_41_negative());
					result.setTraffic_car_42_positive(item.getTraffic_car_42_positive());
					result.setTraffic_car_42_negative(item.getTraffic_car_42_negative());
					result.setTraffic_car_43_positive(item.getTraffic_car_43_positive());
					result.setTraffic_car_43_negative(item.getTraffic_car_43_negative());
					result.setTraffic_car_44_positive(item.getTraffic_car_44_positive());
					result.setTraffic_car_44_negative(item.getTraffic_car_44_negative());
				} else if ("3".equals(report.getMethodFlag())) {
					result.setPic_url_1(item.getPic_url_1());
					result.setPic_url_2(item.getPic_url_2());
					result.setPic_url_3(item.getPic_url_3());
					result.setPic_url_4(item.getPic_url_4());
				} else if ("4".equals(report.getMethodFlag())) {
					result.setReason_area(item.getReason_area());
					result.setReason_point(item.getReason_point());
				}
			}
			result.setCode("0");
			return result;
		} catch (Exception e) {
			result.setMsg("获取信息失败");
			return result;
		}
	}

	/**
	 * 通过用户信息检索用户的报告信息
	 * 
	 * @param report
	 * @param context
	 * @return
	 */

	public ReportList reportUserHandler(Report report, Context context) {
		ReportList result = new ReportList();
		try {
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			// LambdaLogger logger = context.getLogger();
			DynamoDBMapper mapper = new DynamoDBMapper(client);
			//设置查询条件
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":val1", new AttributeValue().withS(report.getUserId()));
			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("UserId = :val1")
					.withExpressionAttributeValues(eav);
			//设置进程数
			int numberOfThreads = 1;
			List<Report> scanResult = mapper.parallelScan(Report.class, scanExpression, numberOfThreads);
			//结果赋值
			List<Report> paramList = null;
			if (scanResult != null && scanResult.size() > 0) {
				paramList = new ArrayList<Report>();
				for (Report bicycle : scanResult) {
					Report param = new Report();
					// CommonUtil.reflectClass(bicycle, param);
					param.setReportId(bicycle.getReportId());
					param.setLatitude(bicycle.getLatitude());
					param.setLongitude(bicycle.getLongitude());
					param.setShop_name(bicycle.getShop_name());
					param.setStatus(bicycle.getStatus());
					param.setUpdate_time(bicycle.getUpdate_time());
					paramList.add(param);
				}
				SortListUtil sortList = new SortListUtil();
				sortList.sort(paramList, "reportId", "desc");
				//sortList.sortByMethod(paramList, "GetReportId", "desc");
				//sortList.sortByMethod(paramList, "update_time", "asc");
				//sortList.Sort();
				result.setList(paramList);
			}
			result.setCode("0");
			return result;
		} catch (Exception e) {
			result.setMsg("获取信息失败");
			return result;
		}
	}

	/**
	 * 获取报告的信息，报告的图片，采访记录信息
	 * 
	 * @param report
	 * @param context
	 * @return
	 */
	public Report reportDetailHandler(Report report, Context context) {
		Report result = new Report();
		try {
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			// LambdaLogger logger = context.getLogger();
			// logger.log("received : " + report.getTitle());

			DynamoDBMapper mapper = new DynamoDBMapper(client);

			Report item = mapper.load(Report.class, report.getReportId());

			if (item != null) {
				CommonUtil.reflectClass(item, result);
				// result.setRequirement_allday(item.getRequirement_allday());
				// result.setRequirement_area(item.getRequirement_area());
				// result.setRequirement_open(item.getRequirement_open());
				// result.setRequirement_wine(item.getRequirement_wine());
				// result.setRequirement_tobacco(item.getRequirement_tobacco());
				// result.setRequirement_tobacco_dis(item.getRequirement_tobacco_dis());
				// result.setRequirement_competitor_count(item.getRequirement_competitor_count());
				//
				// result.setRoad_noentry_endTime(item.getRoad_noentry_endTime());
				// result.setRoad_noentry_startTime(item.getRoad_noentry_startTime());
				// result.setRoad_nostop_endTime(item.getRoad_nostop_endTime());
				// result.setRoad_nostop_startTime(item.getRoad_nostop_startTime());
				// result.setRoad_nowait_endTime(item.getRoad_nowait_endTime());
				// result.setRoad_nowait_startTime(item.getRoad_nowait_startTime());
				// result.setRoad_oneway_endTime(item.getRoad_oneway_endTime());
				// result.setRoad_oneway_startTime(item.getRoad_oneway_startTime());
				//
				// result.setTraffic_car_11_negative(item.getTraffic_car_11_negative());
				// result.setTraffic_car_11_positive(item.getTraffic_car_11_positive());
				// result.setTraffic_car_12_negative(item.getTraffic_car_12_negative());
				// result.setTraffic_car_12_positive(item.getTraffic_car_12_positive());
				// result.setTraffic_car_13_negative(item.getTraffic_car_13_negative());
				// result.setTraffic_car_13_positive(item.getTraffic_car_13_positive());
				// result.setTraffic_car_14_negative(item.getTraffic_car_14_negative());
				// result.setTraffic_car_14_positive(item.getTraffic_car_14_positive());
				// result.setTraffic_car_21_negative(item.getTraffic_car_21_negative());
				// result.setTraffic_car_21_positive(item.getTraffic_car_21_positive());
				// result.setTraffic_car_21_negative(item.getTraffic_car_21_negative());
				// result.setTraffic_car_22_positive(item.getTraffic_car_22_positive());
				// result.setTraffic_car_22_negative(item.getTraffic_car_22_negative());
				// result.setTraffic_car_23_positive(item.getTraffic_car_23_positive());
				// result.setTraffic_car_23_negative(item.getTraffic_car_23_negative());
				// result.setTraffic_car_24_positive(item.getTraffic_car_24_positive());
				// result.setTraffic_car_24_negative(item.getTraffic_car_24_negative());
				// result.setTraffic_car_31_positive(item.getTraffic_car_31_positive());
				// result.setTraffic_car_31_negative(item.getTraffic_car_31_negative());
				// result.setTraffic_car_32_positive(item.getTraffic_car_32_positive());
				// result.setTraffic_car_32_negative(item.getTraffic_car_32_negative());
				// result.setTraffic_car_33_positive(item.getTraffic_car_33_positive());
				// result.setTraffic_car_33_negative(item.getTraffic_car_33_negative());
				// result.setTraffic_car_34_positive(item.getTraffic_car_34_positive());
				// result.setTraffic_car_34_negative(item.getTraffic_car_34_negative());
				// result.setTraffic_car_41_positive(item.getTraffic_car_41_positive());
				// result.setTraffic_car_41_negative(item.getTraffic_car_41_negative());
				// result.setTraffic_car_42_positive(item.getTraffic_car_42_positive());
				// result.setTraffic_car_42_negative(item.getTraffic_car_42_negative());
				// result.setTraffic_car_43_positive(item.getTraffic_car_43_positive());
				// result.setTraffic_car_43_negative(item.getTraffic_car_43_negative());
				// result.setTraffic_car_44_positive(item.getTraffic_car_44_positive());
				// result.setTraffic_car_44_negative(item.getTraffic_car_44_negative());
				//
				// result.setPic_url_1(item.getPic_url_1());
				// result.setPic_url_2(item.getPic_url_2());
				// result.setPic_url_3(item.getPic_url_3());
				// result.setPic_url_4(item.getPic_url_4());
				//
				// result.setReason_area(item.getReason_area());
				// result.setReason_point(item.getReason_point());

				// 检索采访记录
				Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
				eav.put(":val1", new AttributeValue().withS(report.getReportId()));
				DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
						.withFilterExpression("ReportId = :val1").withExpressionAttributeValues(eav);
				int numberOfThreads = 1;
				List<Interview> scanResult = mapper.parallelScan(Interview.class, scanExpression, numberOfThreads);
				List<Interview> paramList = null;
				if (scanResult != null && scanResult.size() > 0) {
					paramList = new ArrayList<Interview>();
					for (Interview bicycle : scanResult) {
						Interview param = new Interview();
						CommonUtil.reflectClass(bicycle, param);
						param.setTypeName(bicycle.getType());
						paramList.add(param);
					}
					result.setInterviewList(paramList);
				}
				// 获取图片信息
				List<Picture> queryResult = mapper.parallelScan(Picture.class, scanExpression, numberOfThreads);
				List<Picture> pictureList = null;
				if (queryResult != null && queryResult.size() > 0) {
					pictureList = new ArrayList<Picture>();
					for (Picture bicycle : queryResult) {
						Picture param = new Picture();
						CommonUtil.reflectClass(bicycle, param);
						pictureList.add(param);
					}
					result.setChartList(pictureList);
				}
			}
			result.setCode("0");
			return result;
		} catch (Exception e) {
			result.setMsg("获取信息失败");
			return result;
		}
	}

	/**
	 * PC端检索报告列表
	 * 
	 * @param report
	 * @param context
	 * @return
	 */
	public ReportPcList reportPcQueryHandler(Report report, Context context) {
		ReportPcList result = new ReportPcList();
		try {
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			// LambdaLogger logger = context.getLogger();
			// logger.log("received : " + report.getTitle());

			DynamoDBMapper mapper = new DynamoDBMapper(client);
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			if (!"".equals(report.getStartDate())) {
				eav.put(":val1", new AttributeValue().withS(report.getStartDate()));
			}
			if (!"".equals(report.getEndDate())) {
				eav.put(":val2", new AttributeValue().withS(report.getEndDate()));
			}
			if ("".equals(report.getStartDate()) || "".equals(report.getEndDate())) {

				eav.put(":val1", new AttributeValue().withS("1970-01-01"));
				eav.put(":val2", new AttributeValue().withS("2200-01-01"));
			}
			// DynamoDBQueryExpression<Report> queryExpression = new
			// DynamoDBQueryExpression<Report>()
			/// .withKeyConditionExpression(" CreateTime between :val1 and
			// :val2")
			// .withExpressionAttributeValues(eav);

			// List<Report> queryReplies = mapper.query(Report.class,
			// queryExpression);
			int numberOfThreads = 1;
			List<Report> scanResult = null;

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
					.withFilterExpression("CreateTime between :val1 and :val2").withExpressionAttributeValues(eav);
			scanResult = mapper.parallelScan(Report.class, scanExpression, numberOfThreads);
			// ReportPcList reportPcList = new ReportPcList();
			List<Report> paramList = null;
			List<ReportPc> reportPcListInfo = null;
			if (scanResult != null && scanResult.size() > 0) {

				reportPcListInfo = new ArrayList<ReportPc>();
				String tempDate = "";
				// paramList = new ArrayList<Report>();
				for (Report bicycle : scanResult) {
					Report reportParam = new Report();
					//
					ReportPc param = new ReportPc();

					if ("".equals(tempDate) || !tempDate.equals(bicycle.getCreate_time())) {
						// param = new ReportPc();
						paramList = new ArrayList<Report>();
						tempDate = bicycle.getCreate_time();
						param.setDate(bicycle.getCreate_time());
						reportParam.setReportId(bicycle.getReportId());
						reportParam.setShop_name(bicycle.getShop_name());
						reportParam.setLocation(bicycle.getLocation());
						reportParam.setTime(bicycle.getCreate_time());
						reportParam.setName(bicycle.getName());
						paramList.add(reportParam);
						param.setReportList(paramList);
						reportPcListInfo.add(param);
					} else {
						reportParam.setReportId(bicycle.getReportId());
						reportParam.setShop_name(bicycle.getShop_name());
						reportParam.setLocation(bicycle.getLocation());
						reportParam.setTime(bicycle.getCreate_time());
						reportParam.setName(bicycle.getName());
						paramList.add(reportParam);
						param.setReportList(paramList);
					}
				}
				result.setList(reportPcListInfo);
			}
			result.setCode("0");
			return result;
		} catch (Exception e) {
			result.setMsg("获取信息失败");
			return result;
		}
	}


}