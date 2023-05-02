public class Manager implements Employee{

    private int income;
    private String companyName;
    private int fixSalaryPart;
    private Company company;
    private int bonusForVariety;

    public Manager(Company company){
        this.companyName = company.getCompanyName();
        this.fixSalaryPart = company.getFixSalary();
        this.company = company;
        generateIncome();
        generateBonusForVariety();
    }

    public int generateBonus() {
        int bonusForManager;
        bonusForManager = income/100 * 5;
        return bonusForManager;
    }

    @Override
    public int getMonthSalary() {
        return getFixSalaryPart() + generateBonus() + bonusForVariety;
    }

    public void generateBonusForVariety () {
        bonusForVariety = (int) Math.round(Math.random()*10_000);
    }

    public void generateIncome() {
        income = (int) Math.round(Math.random()*25_000 + 115_000);
    }

    public int getIncome () {
        return income;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.companyName = company.getCompanyName();
        this.fixSalaryPart = company.getFixSalary();
        this.company = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getFixSalaryPart() {
        return fixSalaryPart;
    }

    public int getBonusForVariety() {
        return bonusForVariety;
    }

}
