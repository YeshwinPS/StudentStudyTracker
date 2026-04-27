package Connection;
import ui.MainMenuUI;
public class MainApp {
    public static void main(String[] args) {
    	new MainMenuUI();
        AddStudent.addStudent("Sanjana", 6);
        AddStudyLog.addLog(4, 3);

        System.out.println("Data inserted successfully");
    }
}
