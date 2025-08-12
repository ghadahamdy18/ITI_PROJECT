import java.util.List;

public interface IStudentRepository {
    void saveStudents(List<Student> students);
    List<Student> loadStudents();
}
