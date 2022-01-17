import java.util.*; 

public class AgeGen {

    public static void main(String[] args) {
        
        int theirAge;

        System.out.println("What is your age?");
        Scanner scanner = new Scanner(System.in);
        theirAge = scanner.nextInt(); 
        
        if (theirAge >= 18 && theirAge >= 30 && theirAge >= 80) {
            System.out.println("You're old enough to vote! You're old enough to run for Senate. Congratulations, you're part of the greatest generation!");
        }
        
        else if (theirAge >= 60) {
            System.out.println("You're old enough to vote! You're old enough to run for Senate.");
        }
        
        else if (theirAge >= 40) {
            System.out.println("You're old enough to vote! You're old enough to run for Senate.");
        }
        
        else if (theirAge >= 30 && theirAge > 20) {
            System.out.println("You're old enough to vote! You're old enough to run for Senate. You're a millenial.");
        }
        
        else if (theirAge < 30 && theirAge > 20) {
            System.out.println("You're old enough to vote! You're not old enough to run for Senate. You're a millenial.");
        }
        
        else if (theirAge >= 18 && theirAge <= 20 ) {
            System.out.println("You're old enough to vote! You're not old enough to run for Senate. You're an iKid.");
        }
    
        
        else if (theirAge < 18) {
            System.out.println("You're not old enough to vote! You're not old enough to run for Senate. You're just an iKid.");
        }
    
    }
    
}
