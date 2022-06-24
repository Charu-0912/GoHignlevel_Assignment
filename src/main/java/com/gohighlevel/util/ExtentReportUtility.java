package com.gohighlevel.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportUtility {
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	ExtentTest logger;

	public static ExtentReports createReport() {
		SimpleDateFormat formater = new SimpleDateFormat("MM_dd_yyyy_hh_mm");
		String path = "./Report/Popflow_Automation_Test_Report_" + formater.format(new Date()) + ".html";
		if (extent == null) {
			htmlReporter = new ExtentHtmlReporter(path);
			htmlReporter.config().setDocumentTitle("Cloud Popflow Auomation Reports");
			htmlReporter.config().setReportName("Cloud Popflow Automation Result");
			htmlReporter.config().setTheme(Theme.STANDARD);
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
		}
		return extent;
	}
}
