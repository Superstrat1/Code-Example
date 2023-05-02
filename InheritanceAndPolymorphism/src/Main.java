public class Main {
    public static void main(String[] args) {
        Company company = new Company("Siemens" ,70000);
        company.hireAll(new Operator(company), 180);
        company.hireAll(new TopManager(company), 10);
        company.hireAll(new Manager(company), 80);
        System.out.println(company.getCompanyIncome());

        System.out.println("высокие\r\n");


        for (Employee employee: company.getTopSalaryStaff(15)){
            System.out.println(employee.getMonthSalary());
        }
        System.out.println();
        System.out.println("Низкие\r\n");

        for (Employee employee: company.getLowestSalaryStaff(30)){
            System.out.println(employee.getMonthSalary());
        }

        company.fire(50);
        System.out.println();

        System.out.println("высокие\r\n");


        for (Employee employee: company.getTopSalaryStaff(15)){
            System.out.println(employee.getMonthSalary());
        }
        System.out.println();
        System.out.println("Низкие\n\r" );

        for (Employee employee: company.getLowestSalaryStaff(30)){
            System.out.println(employee.getMonthSalary());
        }


    }
}
