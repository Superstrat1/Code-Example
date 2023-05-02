import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {

    private final String companyName;
    private final int fixSalary;
    private int companyIncome;

    private ArrayList<Employee> employees = new ArrayList<>();

    public Company (String name, int fixSalary) {
        this.companyName = name;
        this.fixSalary = fixSalary;
    }

    public void hire(Employee employee) {
        employees.add(employee);
        companyIncome = companyIncome + employee.getIncome();
    }

    public void hireAll(Employee employee, int count) {
        if (employee instanceof TopManager) {
            for (int i = 1; i <= count; i++) {
                employee = new TopManager(this);
                hire(employee);
            }
        }
        if (employee instanceof Manager) {
            for (int i = 1; i <= count; i++) {
                employee = new Manager(this);
                hire(employee);
            }
        }
        if (employee instanceof Operator) {
            for (int i = 1; i <= count; i++) {
                employee = new Operator(this);
                hire(employee);
            }
        }
    }

    public void fire (int percent) {
        if (percent <= 100 && percent > 0) {
            float num = (employees.size() / 100.0f) * percent;
            for (int i = 0; i < num; i++) {
                int randomNum = (int) Math.round(Math.random() * employees.size());
                if (randomNum > employees.size() - 1) {
                    randomNum = randomNum - 2;
                    companyIncome = companyIncome - employees.get(randomNum).getIncome();
                    employees.remove(randomNum);
                }
                companyIncome = companyIncome - employees.get(randomNum).getIncome();
                employees.remove(randomNum);

            }
        }
    }



    public ArrayList<Employee> getTopSalaryStaff(int count) { // пересчет всех зп после увольнения
        if(count > 0 && count < employees.size()) {
            ArrayList<Employee> topSalaryEmployees = new ArrayList<>();
            Collections.sort(employees, new EmployeeComparator());
            for (int i = 0; i < count; i++) {
                topSalaryEmployees.add(employees.get(i));
            }
            return topSalaryEmployees;
        }
        return null;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        if(count > 0 && count < employees.size()) {
            ArrayList <Employee> lowestSalaryEmployees = new ArrayList<>();
            Collections.sort(employees, new EmployeeComparator());
            Collections.reverse(employees);
            for (int i = 0; i < count; i++) {
                lowestSalaryEmployees.add(employees.get(i));
            }

            return lowestSalaryEmployees;
        }
        return null;
    }


    public ArrayList<Employee> getList() {
        return employees;
    }

    public boolean isBonus() {
        if (companyIncome>=10_000_000) {
            return true;
        }
        return false;
    }

    public int getFixSalary() {
        return fixSalary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getCompanyIncome() {
        return companyIncome;
    }
}
