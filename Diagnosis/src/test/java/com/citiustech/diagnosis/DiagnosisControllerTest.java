package com.citiustech.diagnosis;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.citiustech.diagnosis.controller.DiagnosisController;
import com.citiustech.diagnosis.model.Diagnosis;
import com.citiustech.diagnosis.service.DiagnosisService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DiagnosisController.class)
public class DiagnosisControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DiagnosisService diagnosisService;

	@SuppressWarnings("unused")
	private static ObjectMapper mapper = new ObjectMapper();

	/*
	 * @Test public void
	 * shouldReturnEmployeeWhenGetEmployeeDetailsIsSuccessfullyExecuted() throws
	 * Exception { Diagnosis diagnosis = new Diagnosis("A00","Cholera",true);
	 * diagnosisRepository.save(diagnosis);
	 * 
	 * OngoingStubbing<Optional<Diagnosis>> d =
	 * when(diagnosisRepository.findById("A00")).thenReturn(Optional.of(diagnosis));
	 * System.out.println(d);
	 * 
	 * mockMvc.perform(get("/diagnosis/A00")) .andExpect(status().isOk())
	 * .andExpect(jsonPath("$.diagnosisCode", Matchers.equalTo("A00")))
	 * .andExpect(jsonPath("$.diagnosisDescription", Matchers.equalTo("Cholera")))
	 * .andExpect(jsonPath("$.diagnosisIsDepricated", Matchers.equalTo(true)));
	 * 
	 * }
	 */

	@Test
	public void getAllDiagnosisShouldReturnListOfDiagnosis() throws Exception {

		List<Diagnosis> allDiagnosis = Arrays.asList(new Diagnosis("A00","Cholera",true),
				new Diagnosis("A00.1","Cholera due to Vibrio cholerae 01",true));

		when(diagnosisService.allDiagnosis()).thenReturn(allDiagnosis);


		mockMvc.perform(get("/diagnosis/all")) .andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$", Matchers.hasSize(2)))
		.andExpect(jsonPath("$[0].diagnosisCode", Matchers.equalTo("A00")));

	}
}
