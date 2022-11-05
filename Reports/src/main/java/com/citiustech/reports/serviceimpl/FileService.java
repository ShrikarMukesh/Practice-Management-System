package com.citiustech.reports.serviceimpl;

import java.io.File;
import org.springframework.stereotype.Service;
import com.citiustech.reports.model.SummaryResponce;

@Service
public class FileService {


	public SummaryResponce summaryCount(String runId) {

		SummaryResponce summaryResponce = new SummaryResponce();
		File[] pathnames;		
		File f = new File(runId);		
		pathnames = f.listFiles();
		for (File pathname : pathnames) {

			if(pathname.getName().contains("I")){

				File inputFiles = new File(pathname.getAbsolutePath());

				int input = inputFiles.list().length;
				summaryResponce.setInput(Integer.toString(input));
			}

			if(pathname.getName().contains("O")){

				File inputFiles = new File(pathname.getAbsolutePath());

				int output = inputFiles.list().length;
				summaryResponce.setOutput(Integer.toString(output));
			}


		}
		return summaryResponce;
	}
}
