import java.util.Scanner;
import java.util.Vector;

public class RetirementCalculator {

    public static void main(String[] args) {

        System.out.println(" ");
        System.out.println("Retirement Calculator");
        System.out.println(" ");
        
            Scanner scnr = new Scanner(System.in);
        
            System.out.print("Annual household income: ");
            double initial_income = scnr.nextInt();
            System.out.print("Current retirement savings: ");
            double initial_savings = scnr.nextInt();
            System.out.print("Monthly retirement savings: ");
            int monthly_savings = scnr.nextInt();
            double annual_savings = monthly_savings * 12;

            double annual_savings_percentage = (annual_savings / initial_income);
            System.out.print("Current age: ");
            int current_age = scnr.nextInt();
            System.out.print("Age of retirement: ");
            int retire_age = scnr.nextInt();
            int years_til_retire = retire_age - current_age;
            int life_expectancy = 95;
        
            System.out.print("Income percentage of final salary required at retirement (If unsure, 80 is recommended): ");
            double retire_salary_percentage = scnr.nextDouble();
            double retire_salary_rate = retire_salary_percentage / 100;
            double sal_increase_rate = 0.02;
        
            System.out.print("Expected rate of inflation (If unsure, 3 percent is recommended): ");
            double inf_per = scnr.nextInt();
            double inf_rate = inf_per / 100;
            
            Vector<Double> salchg = new Vector<Double>(0);
            salchg.addElement(initial_income);

            int ctr = 1;
            while (ctr < years_til_retire){
                initial_income = (initial_income * sal_increase_rate) + initial_income;
                salchg.addElement(initial_income);
                ctr = ctr + 1;
            
            }
            
            double final_sal = Math.round(salchg.lastElement());
            double sal_savings = 0;
            Vector<Double> sal_per = new Vector<Double>(0);
            int ctr2 = 0;
            double chg_income;
            while (ctr2 < salchg.size()){
                chg_income = salchg.elementAt(ctr2) * annual_savings_percentage;
                sal_per.addElement(chg_income);
                ctr2 = ctr2 + 1;
                sal_savings = sal_savings + chg_income;
            }
            
            double total_savings = Math.round(sal_savings - sal_per.lastElement() + initial_savings);
            double retire_income = Math.round(final_sal * retire_salary_rate);
            int retire_years = life_expectancy - retire_age;

            int ctr3 = 0;
            double retire_savings = total_savings;
            Vector<Double> savchg = new Vector<Double>(0);
            while (ctr3 < sal_per.size()){
                retire_savings = retire_savings - (retire_income * (Math.pow((1 + inf_rate), ctr3)));
                savchg.addElement(retire_savings);
                ctr3 = ctr3 + 1;
                if (retire_savings < 0) {
                  savchg.removeElement(retire_savings);
                } 
            }
            
            int years_after_retire = savchg.size();
            int no_money_at = retire_age + years_after_retire;

            
            System.out.println(" ");
            System.out.println("Retirement savings run out when you are " + no_money_at + " years old.");
            System.out.println(" ");
            System.out.println("Your retirement plan provides $" + total_savings + " when you retire at the age " + retire_age + ". This assumes retirement expenses of $" + retire_income + " annually which is " + retire_salary_percentage + "% of your final year's income of $" + final_sal + "." );


    }
}

        