package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CreditBB {
	private String sum;
	private String interest;
	private String installment;
	private Double result;

	@Inject
	FacesContext ctx;

	

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getInstallment() {
		return installment;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	
	public boolean doTheMath() {
		try {
			
			double sum = Double.parseDouble(this.sum);
			double interest = Double.parseDouble(this.interest);
			double installment = Double.parseDouble(this.installment);

			if(interest==0) {
				result = sum/installment;
			}else {
				double interest2 = interest/120;
				double installment2 = installment*12;
				double cnt = 1/Math.pow(1.0+interest2,installment2);
				cnt = 1 - cnt;
				cnt = interest2 / cnt;
				result= Math.round((sum*cnt)* 100.0) / 100.0;
				
			}

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana pomyślnie!", null));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata kredytu: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
