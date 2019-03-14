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

public class InterviewHandler {

	public static void main(String[] args) throws Exception {
		InterviewHandler rh = new InterviewHandler();
		Interview report = new Interview();
		report.setReportId("1");
		report.setTitle("ddddd");
		// report.setLatitude(42.6);
		// report.setLongitude(141.1);
		// report.setMemo("备注");
		// report.setReason_area("hhah");
		// report.setTitle("test1");
		// report.setMemo("momo1");
		// rh.interviewQueryHandler(report, null);
		rh.interviewSaveHandler(report, null);
	}

	/**
	 * 采访记录添加
	 * 
	 * @param interview
	 * @param context
	 * @return
	 */
	public Result interviewSaveHandler(Interview interview, Context context) {
		// LambdaLogger logger = context.getLogger();
		Result result = new Result();
		try {
			// 认证
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			DynamoDBMapper mapper = new DynamoDBMapper(client);
			// 创建时间设置
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			interview.setCreateTime(df.format(new Date()));
			mapper.save(interview);
			result.setCode("0");
			return result;

		} catch (AmazonServiceException ase) {
			System.err.println("Could not complete operation");
			System.err.println("Error Message:  " + ase.getMessage());
			System.err.println("HTTP Status:    " + ase.getStatusCode());
			System.err.println("AWS Error Code: " + ase.getErrorCode());
			System.err.println("Error Type:     " + ase.getErrorType());
			System.err.println("Request ID:     " + ase.getRequestId());
			result.setMsg("录入信息失败");
			return result;
		} catch (AmazonClientException e) {
			// logger.log("error : " + e.getMessage());
			result.setMsg("录入信息失败");
			return result;
		}
	}

	/**
	 * 采访信息添加
	 * 
	 * @param interview
	 * @param context
	 * @return
	 */
	public InterviewList interviewQueryHandler(Interview interview, Context context) {
		InterviewList result = new InterviewList();
		try {
			BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIULMLZQDV6V3H5AA",
					"XEuzrGUIYdFJY7EAmzEIjtXjDwulfrrj+mpofKF8");
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

			// LambdaLogger logger = context.getLogger();
			// logger.log("received : " + report.getTitle());

			DynamoDBMapper mapper = new DynamoDBMapper(client);
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":val1", new AttributeValue().withS(interview.getReportId()));
			// eav.put(":val2", new AttributeValue().withS(""));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
					.withFilterExpression("ReportId = :val1").withExpressionAttributeValues(eav);

			int numberOfThreads = 1;
			List<Interview> scanResult = mapper.parallelScan(Interview.class, scanExpression, numberOfThreads);
			List<Interview> paramList = null;
			if (scanResult != null && scanResult.size() > 0) {
				paramList = new ArrayList<Interview>();
				for (Interview bicycle : scanResult) {
					Interview param = new Interview();
					param.setAge(bicycle.getAge());
					param.setColor(bicycle.getColor());
					param.setSex(bicycle.getSex());
					param.setTitle(bicycle.getTitle());
					param.setCreateTime(bicycle.getCreateTime());
					param.setTypeName(bicycle.getType());
					param.setMemo(bicycle.getMemo());
					param.setInterviewId(bicycle.getInterviewId());
					paramList.add(param);
				}
			}
			result.setInterviewList(paramList);

			result.setCode("0");
			return result;
		} catch (Exception e) {
			result.setMsg("获取信息失败");
			return result;
		}
	}

}
