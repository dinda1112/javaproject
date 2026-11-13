import java.time.LocalDate;

// Holds the content for the Healthy Lifestyle app.
// For now it only has the tips for the home screen.
// (This is the old JavaCourseContent, renamed. Learning pages and quiz will be added later.)
public class HealthContent {

    private final String[] tips = {
        "Drink water regularly through the day. Do not wait until you feel thirsty.",
        "Most adults need about 7 to 9 hours of sleep every night.",
        "Try to move for at least 30 minutes a day. A brisk walk counts.",
        "Fill half of your plate with vegetables and fruits at meals.",
        "Stand up and stretch every hour if you sit for long periods.",
        "Swap sugary drinks for plain water or unsweetened tea when you can.",
        "Put your phone away 30 minutes before bed so you fall asleep easier.",
        "Wash your hands with soap before eating and after using the toilet.",
        "Feeling stressed? Take a few slow deep breaths or talk to someone you trust.",
        "Take the stairs instead of the lift when you only need to go a few floors."
    };

    public int getTipCount() {
        return tips.length;
    }

    public String getTip(int index) {
        if (index < 0 || index >= tips.length) return "";
        return tips[index];
    }

    // picks a tip based on today's date, so the tip changes every day
    public int getTodayTipIndex() {
        return LocalDate.now().getDayOfYear() % tips.length;
    }
}