import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-patientwelcome',
  templateUrl: './patientwelcome.component.html',
  styleUrls: ['./patientwelcome.component.css']
})
export class PatientwelcomeComponent implements OnInit {

  constructor() { }
  book = {
    bookId: 101,
    imageUrl: 'assets/images/java8_in_action.jpeg',
    title: 'Java 8 in Action',
    authors: [
      { firstName: 'Raoul-Gabriel', lastName: 'Urma' },
      { firstName: 'Mario', lastName: 'Fusco' },
      { firstName: 'Alan', lastName: 'Mycroft' }
    ],
    category: 'programming',
    publisher: 'Wiley',
    noOfPages: 424,
    rating: 4.4,
    edition: 2,
    price: 618,
    releaseDate: new Date(2018, 5, 23)
  };

  visit = {
    visitid: 25,
    patientId: 12,
    visitedAt: "2021/09/01 10:53:52",
    vitalSigns: {
        vitalsignid: 25,
        height: 165,
        weight: 67.0,
        bpSystolic: 78.9,
        bpDiastolic: 89.9,
        respirationRate: 443.0
    },
    medication: [
        {
            applNo: "000159",
            productNo: "001",
            form: "TABLET;ORAL",
            strength: "500MG",
            referenceDrug: "0",
            drugName: "SULFAPYRIDINE",
            activeIngredient: "SULFAPYRIDINE",
            referenceStandard: "0"
        },
        {
            applNo: "000552",
            productNo: "001",
            form: "INJECTABLE;INJECTION",
            strength: "20,000 UNITS/ML",
            referenceDrug: "0",
            drugName: "LIQUAEMIN SODIUM",
            activeIngredient: "HEPARIN SODIUM",
            referenceStandard: "0"
        }
    ],
    procedure: [
        {
            procedureCode: "001607A",
            procedureDescription: "Bypass Cerebral Ventricle to Subgaleal Space with Autologous Tissue Substitute",
            procedureIsDepricated: true,
            approachType: "Open Approach"
        }
    ],
    diagnosis: [
        {
            diagnosisCode: "A00",
            diagnosisDescription: "Cholera",
            diagnosisIsDepricated: true
        },
        {
            diagnosisCode: "A00.1",
            diagnosisDescription: "Cholera due to Vibrio cholerae 01",
            diagnosisIsDepricated: true
        }
    ]
}
  ngOnInit(): void {
  }

}
