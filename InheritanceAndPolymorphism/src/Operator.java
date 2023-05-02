public class Operator implements Employee{

    private String companyName;
    private int fixSalaryPart;
    private Company company;
    private int bonusForVariety;

    public Operator(Company company){
        this.companyName = company.getCompanyName();
        this.fixSalaryPart = company.getFixSalary();
        this.company = company;
        generateBonusForVariety();
    }

    public Company getCompany() {
        return company;
    }

    public void generateBonusForVariety () {
        bonusForVariety = (int) Math.round(Math.random()*5_000);
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


    @Override
    public int getMonthSalary() {
        return getFixSalaryPart() + bonusForVariety;
    }

    @Override
    public int getIncome() {
        return 0;
    }

}
