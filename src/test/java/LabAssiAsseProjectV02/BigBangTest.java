package LabAssiAsseProjectV02;

import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import LabAssiAsseProjectV02.domain.Student;
import LabAssiAsseProjectV02.domain.Tema;
import LabAssiAsseProjectV02.domain.Nota;
import LabAssiAsseProjectV02.repository.NotaXMLRepo;
import LabAssiAsseProjectV02.repository.StudentXMLRepo;
import LabAssiAsseProjectV02.repository.TemaXMLRepo;
import LabAssiAsseProjectV02.service.Service;
import LabAssiAsseProjectV02.validation.NotaValidator;
import LabAssiAsseProjectV02.validation.StudentValidator;
import LabAssiAsseProjectV02.validation.TemaValidator;

import java.time.LocalDate;

public class BigBangTest {
    @Test
    public void testAddStudent(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        Student student = new Student("unique_id", "Unique Little Snowflake",
                935, "snflk@mail.com");

        Student foundStudent = service.findStudent("unique_id");

        assertNull(foundStudent);

        service.addStudent(student);
        foundStudent = service.findStudent("unique_id");

        assertNotNull(foundStudent);
        assertEquals(foundStudent.getNume(), "Unique Little Snowflake");
        assertEquals(foundStudent.getID(), "unique_id");
        assertEquals(foundStudent.getGrupa(), 935);
        assertEquals(foundStudent.getEmail(), "snflk@mail.com");

        service.deleteStudent("unique_id");
    }

    @Test
    public void testAddAssignment(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        Tema assignment = new Tema("unique_tema_nr", "Assignment description", 14, 12);

        Tema foundAssignment = service.findTema("unique_tema_nr");

        assertNull(foundAssignment);

        service.addTema(assignment);
        foundAssignment = service.findTema("unique_tema_nr");

        assertNotNull(foundAssignment);
        assertEquals(foundAssignment.getID(), "unique_tema_nr");
        assertEquals(foundAssignment.getDescriere(), "Assignment description");
        assertEquals(foundAssignment.getDeadline(), 14);
        assertEquals(foundAssignment.getPrimire(), 12);

        service.deleteTema("unique_tema_nr");
    }

    @Test
    public void testAddGrade(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        Student student = new Student("unique_id", "Unique Little Snowflake",
                935, "snflk@mail.com");
        Tema assignment = new Tema("unique_tema_nr", "Assignment description", 14, 12);
        service.addStudent(student);
        service.addTema(assignment);
        Nota grade = new Nota("unique_grade_id", "unique_id", "unique_tema_nr",
                10, LocalDate.of(2019, 4, 17));

        Nota foundGrade = service.findNota("unique_grade_id");

        assertNull(foundGrade);

        service.addNota(grade, "Feedback");
        foundGrade = service.findNota("unique_grade_id");

        assertNotNull(foundGrade);
        assertEquals(foundGrade.getID(), "unique_grade_id");
        assertEquals(foundGrade.getIdStudent(), "unique_id");
        assertEquals(foundGrade.getIdTema(), "unique_tema_nr");
        assertEquals(foundGrade.getNota(), 10, 0.1);
        assertEquals(foundGrade.getData(), LocalDate.of(2019, 4, 17));

        service.deleteNota("unique_grade_id");
        service.deleteStudent("unique_id");
        service.deleteTema("unique_tema_nr");
    }

    @Test
    public void testBigBang(){
        this.testAddStudent();
        this.testAddAssignment();
        this.testAddGrade();
    }
}
