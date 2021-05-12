package ftnt.qa.autotest.ui.dao.model;

/**
 * 
 * @author Lei.Wu 供应商信息
 *
 */
public class SupplierInfo {

	private String email;

	private String supplier_id;

	private String company_name;

	private int logical_del;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public int getLogical_del() {
		return logical_del;
	}

	public void setLogical_del(int logical_del) {
		this.logical_del = logical_del;
	}
}