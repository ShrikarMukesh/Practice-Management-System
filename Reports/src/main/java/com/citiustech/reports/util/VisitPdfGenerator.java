package com.citiustech.reports.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citiustech.reports.dto.Diagnosis;
import com.citiustech.reports.dto.Medication;
import com.citiustech.reports.dto.Procedure;
import com.citiustech.reports.dto.VisitDetails;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class VisitPdfGenerator {
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	public static ByteArrayInputStream patietvisitPDFReport(VisitDetails visitDetail) throws FileNotFoundException, DocumentException {



		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
		Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
		Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));

		Document document = new Document();
		//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://StylingExample.pdf"));
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
			Paragraph para = new Paragraph( "Last Visit Details", font);
			para.setAlignment(Element.ALIGN_LEFT);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(3);
			// Add PDF Table Header ->
			Stream.of("patientId", "visitId", "visitedAt")
			.forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});


			PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(visitDetail.getPatientId())));
			idCell.setPaddingLeft(4);
			idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(idCell);

			PdfPCell firstNameCell = new PdfPCell(new Phrase(String.valueOf(visitDetail.getVisitid())));
			firstNameCell.setPaddingLeft(4);
			firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(firstNameCell);

			PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(visitDetail.getVisitedAt())));
			lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			lastNameCell.setPaddingRight(4);
			table.addCell(lastNameCell);

			PdfPTable vitalSignstable =  vitalSignsTable(visitDetail);
			PdfPTable medicationtable =  visitmedications(visitDetail);
			PdfPTable proceduretable =  visitprocedures(visitDetail);
			PdfPTable diagnosistable =  visitdiagnosis(visitDetail);

			document.add(table);
			document.add(new Paragraph("Vital Signs"));

			//Medication Table
			Font medicationTitle = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
			Paragraph medicationpara = new Paragraph( "Medications", medicationTitle);
			para.setAlignment(Element.ALIGN_LEFT);
			document.add(medicationpara);
			document.add(Chunk.NEWLINE);
			document.add(medicationtable);

			//Procedure Table
			Font procedureTitle = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
			Paragraph procedurepara = new Paragraph( "Procedures", procedureTitle);
			para.setAlignment(Element.ALIGN_LEFT);
			document.add(procedurepara);
			document.add(Chunk.NEWLINE);
			document.add(proceduretable);
			
			//Diagnosis Table
			Font diagnosisTitle = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
			Paragraph diagnosisPara = new Paragraph( "Diagnosis", diagnosisTitle);
			para.setAlignment(Element.ALIGN_LEFT);
			document.add(diagnosisPara);
			document.add(Chunk.NEWLINE);
			document.add(diagnosistable);

			document.close();
		}catch(DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private static PdfPTable vitalSignsTable(VisitDetails visitDetail) {

		PdfPTable table = new PdfPTable(3);
		// Add PDF Table Header ->
		Stream.of("patientId", "visitId", "visitedAt")
		.forEach(headerTitle -> {
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(headerTitle, headFont));
			table.addCell(header);
		});


		PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(visitDetail.getPatientId())));
		idCell.setPaddingLeft(4);
		idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(idCell);

		PdfPCell firstNameCell = new PdfPCell(new Phrase(String.valueOf(visitDetail.getVisitid())));
		firstNameCell.setPaddingLeft(4);
		firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(firstNameCell);

		PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(visitDetail.getVisitedAt())));
		lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		lastNameCell.setPaddingRight(4);
		table.addCell(lastNameCell);
		return null;
	}

	private static PdfPTable visitmedications(VisitDetails visitDetail) {

		PdfPTable medicationsTable = new PdfPTable(8);
		// Add PDF Table Header ->
		Stream.of("appl_no", "productNo", "form","strength","referenceDrug","drugName","activeIngredient","referenceStandard")
		.forEach(headerTitle -> {
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(headerTitle, headFont));
			medicationsTable.addCell(header);
		});

		for ( Medication medication: visitDetail.getMedication()) {

			PdfPCell applNoCell = new PdfPCell(new Phrase(String.valueOf(medication.getApplNo().toString())));
			applNoCell.setPaddingLeft(4);
			applNoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			applNoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			medicationsTable.addCell(applNoCell);

			PdfPCell prodNo = new PdfPCell(new Phrase(String.valueOf(medication.getProductNo().toString())));
			prodNo.setPaddingLeft(4);
			prodNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
			prodNo.setHorizontalAlignment(Element.ALIGN_CENTER);
			medicationsTable.addCell(prodNo);

			PdfPCell form = new PdfPCell(new Phrase(String.valueOf(medication.getForm().toString())));
			form.setPaddingLeft(4);
			form.setVerticalAlignment(Element.ALIGN_MIDDLE);
			form.setHorizontalAlignment(Element.ALIGN_CENTER);
			medicationsTable.addCell(form);

			PdfPCell strength = new PdfPCell(new Phrase(String.valueOf(medication.getStrength().toString())));
			strength.setPaddingLeft(4);
			strength.setVerticalAlignment(Element.ALIGN_MIDDLE);
			strength.setHorizontalAlignment(Element.ALIGN_CENTER);
			medicationsTable.addCell(strength);

			PdfPCell referenceDrug = new PdfPCell(new Phrase(String.valueOf(medication.getReferenceDrug().toString())));
			referenceDrug.setPaddingLeft(4);
			referenceDrug.setVerticalAlignment(Element.ALIGN_MIDDLE);
			referenceDrug.setHorizontalAlignment(Element.ALIGN_CENTER);
			medicationsTable.addCell(referenceDrug);

			PdfPCell drugNameCell = new PdfPCell(new Phrase(String.valueOf(medication.getDrugName().toString())));
			drugNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			drugNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			drugNameCell.setPaddingRight(4);
			medicationsTable.addCell(drugNameCell);

			PdfPCell activeInGredCell = new PdfPCell(new Phrase(String.valueOf(medication.getActiveIngredient().toString())));
			activeInGredCell.setPaddingLeft(4);
			activeInGredCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			activeInGredCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			medicationsTable.addCell(activeInGredCell);

			PdfPCell referenceStandard = new PdfPCell(new Phrase(String.valueOf(medication.getReferenceStandard().toString())));
			referenceStandard.setPaddingLeft(4);
			referenceStandard.setVerticalAlignment(Element.ALIGN_MIDDLE);
			referenceStandard.setHorizontalAlignment(Element.ALIGN_CENTER);
			medicationsTable.addCell(referenceStandard);

		}
		return medicationsTable;
	}


	private static PdfPTable visitdiagnosis(VisitDetails visitDetail) {

		PdfPTable procedureTable = new PdfPTable(2);
		// Add PDF Table Header ->
		Stream.of("diagnosisCode", "diagnosisDescription")
		.forEach(headerTitle -> {
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(headerTitle, headFont));
			procedureTable.addCell(header);
		});

		for (Diagnosis diagnosis: visitDetail.getDiagnosis()) {

			PdfPCell diagnosisCode = new PdfPCell(new Phrase(String.valueOf(diagnosis.getDiagnosisCode().toString())));
			diagnosisCode.setPaddingLeft(4);
			diagnosisCode.setVerticalAlignment(Element.ALIGN_MIDDLE);
			diagnosisCode.setHorizontalAlignment(Element.ALIGN_CENTER);
			procedureTable.addCell(diagnosisCode);

			PdfPCell diagnosisDescription = new PdfPCell(new Phrase(String.valueOf(diagnosis.getDiagnosisDescription().toString())));
			diagnosisDescription.setPaddingLeft(4);
			diagnosisDescription.setVerticalAlignment(Element.ALIGN_MIDDLE);
			diagnosisDescription.setHorizontalAlignment(Element.ALIGN_CENTER);
			procedureTable.addCell(diagnosisDescription);

		}
		return procedureTable;
	}


	private static PdfPTable visitprocedures(VisitDetails visitDetail) {

		PdfPTable produresTable = new PdfPTable(3);
		// Add PDF Table Header ->
		Stream.of("procedureCode", "procedureDescription","approachType")
		.forEach(headerTitle -> {
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(headerTitle, headFont));
			produresTable.addCell(header);
		});

		for ( Procedure procedure: visitDetail.getProcedure()) {

			PdfPCell procedureCode = new PdfPCell(new Phrase(String.valueOf(procedure.getProcedureCode().toString())));
			procedureCode.setPaddingLeft(4);
			procedureCode.setVerticalAlignment(Element.ALIGN_MIDDLE);
			procedureCode.setHorizontalAlignment(Element.ALIGN_CENTER);
			produresTable.addCell(procedureCode);

			PdfPCell procedureDescription = new PdfPCell(new Phrase(String.valueOf(procedure.getProcedureDescription().toString())));
			procedureDescription.setPaddingLeft(4);
			procedureDescription.setVerticalAlignment(Element.ALIGN_MIDDLE);
			procedureDescription.setHorizontalAlignment(Element.ALIGN_CENTER);
			produresTable.addCell(procedureDescription);

			PdfPCell approachType = new PdfPCell(new Phrase(String.valueOf(procedure.getApproachType().toString())));
			approachType.setVerticalAlignment(Element.ALIGN_MIDDLE);
			approachType.setHorizontalAlignment(Element.ALIGN_CENTER);
			approachType.setPaddingRight(4);
			produresTable.addCell(approachType);
		}
		return produresTable;
	}





}
