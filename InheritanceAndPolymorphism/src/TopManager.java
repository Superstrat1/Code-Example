public class TopManager implements Employee{



    private String companyName;
    private int fixSalaryPart;
    private Company company;
    private int bonusForVariety;


    public TopManager(Company company){
        this.companyName = company.getCompanyName();
        this.fixSalaryPart = company.getFixSalary();
        this.company = company;
        generateBonusForVariety();
    }

    @Override
    public int getMonthSalary() {
        if (company.isBonus()) {
            return getFixSalaryPart() + (int)(getFixSalaryPart()*1.5) + bonusForVariety;
        }
        return getFixSalaryPart() + bonusForVariety;
    }

    public void generateBonusForVariety () {
        bonusForVariety = (int) Math.round(Math.random()*5_000);
    }

    @Override
    public String toString() {
        return "Топ Менеджер компании " + company.getCompanyName() ;
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

    @Override
    public int getIncome() {
        return 0;
    }

}
